package org.example.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParticleSwarmOrganization {
    private final List<Particle> swarm;
    private final Double selfCoefficient;
    private final Double swarmCoefficient;
    private Point bestSwarmPosition;
    private Long bestSwarmResult;
    private Double maxInertia;
    private final Double minInertia;
    private Double selfDeviation;
    private Double swarmDeviation;

    public ParticleSwarmOrganization(int size) {
        this.swarm = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            this.swarm.add(new Particle(4, 10.24));
        }
        this.bestSwarmPosition = swarm.get(0).getBestPosition();
        this.bestSwarmResult = swarm.get(0).getBestResult();
        this.minInertia = 0.5;
        this.maxInertia = 0.9;
        this.selfCoefficient = 1.0;
        this.swarmCoefficient = 2.0;
        this.selfDeviation = 0.5;
        this.swarmDeviation = 0.5;
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
        this.minInertia = minInertia;
        this.maxInertia = maxInertia;
        this.selfCoefficient = selfCoefficient;
        this.swarmCoefficient = swarmCoefficient;
        this.selfDeviation = selfDeviation;
        this.swarmDeviation = swarmDeviation;
    }

    public ParticleSwarmOrganization(List<Point> startPoints, Double range){
        this.swarm = new ArrayList<>();
        for (Point p : startPoints) {
            this.swarm.add(new Particle(p, range));
        }
        this.bestSwarmPosition = swarm.get(0).getBestPosition();
        this.bestSwarmResult = swarm.get(0).getBestResult();
        this.minInertia = 0.5;
        this.maxInertia = 0.9;
        this.selfCoefficient = 1.0;
        this.swarmCoefficient = 2.0;
        this.selfDeviation = 0.5;
        this.swarmDeviation = 0.5;
    }

    public ParticleSwarmOrganization(List<Point> startPoints, Double range,
                                     Double minInertia, Double maxInertia,
                                     Double selfCoefficient, Double selfDeviation,
                                     Double swarmCoefficient,Double swarmDeviation) {
        this.swarm = new ArrayList<>();
        for (Point p : startPoints) {
            this.swarm.add(new Particle(p, range));
        }
        this.bestSwarmPosition = swarm.get(0).getBestPosition();
        this.bestSwarmResult = swarm.get(0).getBestResult();
        this.minInertia = minInertia;
        this.maxInertia = maxInertia;
        this.selfCoefficient = selfCoefficient;
        this.swarmCoefficient = swarmCoefficient;
        this.selfDeviation = selfDeviation;
        this.swarmDeviation = swarmDeviation;
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
        this.selfDeviation = random.nextDouble();
        this.swarmDeviation = random.nextDouble();
        for (Particle particle : swarm) {
            particle.step(bestSwarmPosition, maxInertia,
                    selfCoefficient, selfDeviation,
                    swarmCoefficient, swarmDeviation);
        }
        updateBestResult();
    }

    public Long findBestResult(int numberOfSteps) {
        double delta = (maxInertia - minInertia) / numberOfSteps;
        updateBestResult();
        int i = 0;
        long previousResult = bestSwarmResult;
        while (i < numberOfSteps) {
            nextStep();
            if (previousResult != bestSwarmResult) {
                maxInertia -= delta;
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
