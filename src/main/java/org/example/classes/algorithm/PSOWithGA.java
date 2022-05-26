package org.example.classes.algorithm;

import org.example.classes.algorithm.ParticleSwarmOrganization;
import org.example.classes.entities.Particle;
import org.example.classes.entities.Point;

import java.util.ArrayDeque;
import java.util.List;

public class PSOWithGA extends ParticleSwarmOrganization {
    private final ArrayDeque<Point> bestPoints;

    public ArrayDeque<Point> getBestPoints() {
        return bestPoints;
    }

    public PSOWithGA(int size, int numberOfDimentions, Double range,
                     Double minInertia, Double maxInertia,
                     Double selfCoefficient, Double selfDeviation,
                     Double swarmCoefficient, Double swarmDeviation, Integer generationSize){
        super(size, numberOfDimentions, range,
                minInertia, maxInertia,
                selfCoefficient, selfDeviation,
                swarmCoefficient, swarmDeviation);
        bestPoints = new ArrayDeque<>();
        for (int i = 0; i < generationSize; i++) {
            bestPoints.addFirst(swarm.get(i).getBestPosition());
        }
    }

    public PSOWithGA(List<Point> startPoints, Double range,
                     Double minInertia, Double maxInertia,
                     Double selfCoefficient, Double selfDeviation,
                     Double swarmCoefficient, Double swarmDeviation, Integer generationSize){
        super(startPoints, range,
                minInertia, maxInertia,
                selfCoefficient, selfDeviation,
                swarmCoefficient, swarmDeviation);
        bestPoints = new ArrayDeque<>();
        for (int i = 0; i < generationSize; i++) {
            bestPoints.addFirst(swarm.get(i).getBestPosition());
        }
    }

    @Override
    public void updateBestResult() {
        for (Particle particle : swarm) {
            if (bestSwarmResult >= particle.getBestResult()) {
                this.bestSwarmPosition = particle.getBestPosition();
                this.bestSwarmResult = particle.getBestResult();
                bestPoints.pollLast();
                bestPoints.addFirst(particle.getBestPosition());
            }
        }
    }

}
