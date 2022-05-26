package org.example.classes.algorithm;

import org.example.classes.algorithm.ParametersForPSO;
import org.example.classes.entities.Particle;
import org.example.classes.entities.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParticleSwarmOrganization {
    protected List<Particle> swarm;
    protected Point bestSwarmPosition;
    protected Long bestSwarmResult;

    private final ParametersForPSO parameters;

    public ParticleSwarmOrganization(int size) {
        this.swarm = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            this.swarm.add(new Particle(4, 10.24));
        }
        this.bestSwarmPosition = swarm.get(0).getBestPosition();
        this.bestSwarmResult = swarm.get(0).getBestResult();
        this.parameters = new ParametersForPSO(0.9, 0.5,
                1.0,0.5,
                2.0,0.5);
    }

    public ParticleSwarmOrganization(int size, int numberOfDimentions, Double range,
                                     Double minInertia, Double maxInertia,
                                     Double selfCoefficient, Double selfDeviation,
                                     Double swarmCoefficient,Double swarmDeviation) {
        this.swarm = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            this.swarm.add(new Particle(numberOfDimentions, range));
        }
        this.bestSwarmPosition = swarm.get(0).getBestPosition();
        this.bestSwarmResult = swarm.get(0).getBestResult();
        this.parameters = new ParametersForPSO(maxInertia, minInertia,
                selfCoefficient, selfDeviation,
                swarmCoefficient, swarmDeviation);
    }

    public ParticleSwarmOrganization(List<Point> startPoints, Double range){
        this.swarm = new ArrayList<>();
        for (Point p : startPoints) {
            this.swarm.add(new Particle(p, range));
        }
        this.bestSwarmPosition = swarm.get(0).getBestPosition();
        this.bestSwarmResult = swarm.get(0).getBestResult();
        this.parameters = new ParametersForPSO(0.9, 0.5,
                1.0,0.5,
                2.0,0.5);
    }

    public ParticleSwarmOrganization(List<Point> startPoints, Double range,
                                     Double minInertia, Double maxInertia,
                                     Double selfCoefficient, Double selfDeviation,
                                     Double swarmCoefficient, Double swarmDeviation) {
        this.swarm = new ArrayList<>();
        for (Point p : startPoints) {
            this.swarm.add(new Particle(p, range));
        }
//        System.out.println();
//        System.out.println(startPoints);
//        System.out.println();
        this.bestSwarmPosition = swarm.get(0).getBestPosition();
        this.bestSwarmResult = swarm.get(0).getBestResult();
        this.parameters = new ParametersForPSO(maxInertia, minInertia,
                selfCoefficient, selfDeviation,
                swarmCoefficient, swarmDeviation);
    }

    public Point getBestSwarmPosition() {
        return bestSwarmPosition;
    }

    public Long getBestSwarmResult() {
        return bestSwarmResult;
    }

    public void updateBestResult() {
        for (Particle particle : swarm) {
            if (bestSwarmResult >= particle.getBestResult()) {
                this.bestSwarmPosition = particle.getBestPosition();
                this.bestSwarmResult = particle.getBestResult();
            }
        }
    }

    public void nextStep() {
        Random random = new Random();
        this.parameters.selfDeviation = random.nextDouble();
        this.parameters.swarmDeviation = random.nextDouble();
        for (Particle particle : swarm) {
            particle.step(bestSwarmPosition, parameters.maxInertia,
                    parameters.selfCoefficient, parameters.selfDeviation,
                    parameters.swarmCoefficient, parameters.swarmDeviation);
        }
        updateBestResult();
    }

    public Long findBestResult(int numberOfSteps) {
        double delta = (parameters.maxInertia - parameters.minInertia) / numberOfSteps;
        updateBestResult();
        int i = 0;
        long previousResult = 0;
        while (i < numberOfSteps) {
            nextStep();
            if (previousResult != bestSwarmResult) {
                parameters.maxInertia -= delta;
                previousResult = bestSwarmResult;
                System.out.println("Step: " + i);
                System.out.println("Best swarm position: " + bestSwarmPosition);
                System.out.println("Best swarm result: " + bestSwarmResult);
                System.out.println("---------------------------------------------------------");
            }
            i++;
        }
        return bestSwarmResult;
    }
}
