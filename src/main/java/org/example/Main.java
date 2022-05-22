package org.example;

import org.example.classes.ParticleSwarmOrganization;

public class Main {
    public static void main(String[] args) {
        ParticleSwarmOrganization solver = new ParticleSwarmOrganization(100);
        System.out.println("Best result: " + solver.findBestResult(100));
    }
}