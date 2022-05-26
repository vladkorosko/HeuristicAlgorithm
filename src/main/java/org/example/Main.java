package org.example;


import org.example.classes.algorithm.GeneticAlgorithm;
import org.example.classes.functions.RastriginFunction;
import org.example.classes.functions.RosenbrockFunction;
import org.example.classes.functions.SphereFunction;
import org.example.classes.functions.TestOptimizationFunction;

public class Main {
    public static void main(String[] args) {
//        ParticleSwarmOrganization solver = new ParticleSwarmOrganization(1000);
//        System.out.println("Best result: " + solver.findBestResult(1000));
        TestOptimizationFunction function = new RastriginFunction();
        GeneticAlgorithm a = new GeneticAlgorithm(100, 4, 10.24,
                0.5, 0.7,
                1.0, 0.5,
                2.0, 0.5,
                10, 1000,
                function);
        System.out.println("Best result: " + a.findBestResult());


    }
}