package org.example;


import org.example.classes.algorithm.GeneticAlgorithm;

public class Main {
    public static void main(String[] args) {
//        ParticleSwarmOrganization solver = new ParticleSwarmOrganization(1000);
//        System.out.println("Best result: " + solver.findBestResult(1000));
        GeneticAlgorithm a = new GeneticAlgorithm(1000, 4, 10.24,
                0.5, 0.7,
                1.0, 0.5,
                2.0, 0.5,
                10, 1000);
        System.out.println("Best result: " + a.findBestResult());


    }
}