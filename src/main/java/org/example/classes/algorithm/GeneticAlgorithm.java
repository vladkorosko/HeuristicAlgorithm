package org.example.classes.algorithm;

import org.example.classes.entities.Point;
import org.example.classes.functions.TestOptimizationFunction;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class GeneticAlgorithm {
    private final Integer generationSize;
    private final Integer numberOfGenerations;
    private final Integer numberOfSteps;
    private final ParametersForPSO parameters;
    private final Double range;
    private final TestOptimizationFunction function;
    private PSOWithGA solver;
    private Long bestResult;
    private Point bestResultPoint;

    public GeneticAlgorithm(int size, int numberOfDimentions, Double range,
                            Double minInertia, Double maxInertia,
                            Double selfCoefficient, Double selfDeviation,
                            Double swarmCoefficient, Double swarmDeviation,
                            Integer numberOfGenerations, Integer numberOfSteps,
                            TestOptimizationFunction function) {
        this.function = function;
        this.generationSize = (int) Math.pow(2, numberOfDimentions);
        this.solver = new PSOWithGA((int) Math.pow(2, Math.ceil(Math.log(size) / Math.log(2))),
                numberOfDimentions, range,
                minInertia, maxInertia,
                selfCoefficient, selfDeviation,
                swarmCoefficient, swarmDeviation, generationSize, function);
        this.bestResult = solver.getBestSwarmResult();
        this.bestResultPoint = solver.getBestSwarmPosition();
        this.numberOfSteps = numberOfSteps;
        this.numberOfGenerations = numberOfGenerations;
        this.parameters = new ParametersForPSO(maxInertia, minInertia,
                selfCoefficient, selfDeviation,
                swarmCoefficient, swarmDeviation);
        this.range = range;
    }

    public void nextGeneration() {
        this.bestResult = solver.findBestResult(numberOfSteps);
        this.bestResultPoint = solver.bestSwarmPosition;
        ArrayDeque<Point> generation = solver.getBestPoints();
        List<Point> newGeneration = new ArrayList<>();
        for (int i = 0; i < generation.size(); ) {
            newGeneration.addAll(CombineGen.newGeneration(generation.pollFirst(), generation.pollFirst()));
        }
        this.solver = new PSOWithGA(newGeneration, range,
                parameters.minInertia, parameters.maxInertia,
                parameters.selfCoefficient, parameters.selfDeviation,
                parameters.swarmCoefficient, parameters.swarmDeviation, generationSize, function);
    }

    public Long findBestResult() {
        int i = 0;
        while (i < numberOfGenerations) {
            i++;
            nextGeneration();
            System.out.println("Number of generation: " + i);
            System.out.println("Best generation position: " + bestResultPoint);
            System.out.println("Best generation result: " + bestResult);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
        return bestResult;
    }
}
