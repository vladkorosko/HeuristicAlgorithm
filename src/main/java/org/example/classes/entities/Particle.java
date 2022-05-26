package org.example.classes.entities;

import org.example.classes.functions.RastriginFunction;

public class Particle {
    private Point position;
    private Point bestPosition;
    private Point velocity;
    private Long bestResult;

    public Particle(Integer size, Double range) {
        this.position = new Point(size, range);
        this.velocity = new Point(size, range / 2.0);
        this.bestPosition = this.position;
        this.bestResult = RastriginFunction.calculateResult(this.position);
    }

    public Particle(Point p, Double range){
        this.position = p;
        this.velocity = new Point(p.getCoordinates().size(), range / 2.0);
        this.bestPosition = this.position;
        this.bestResult = RastriginFunction.calculateResult(this.position);
    }

    private void calculateNewResult() {
        Long newResult = RastriginFunction.calculateResult(position);
        if (bestResult > newResult) {
            bestPosition = position;
            bestResult = newResult;
        }
    }

    public Point getBestPosition() {
        return bestPosition;
    }

    public Long getBestResult() {
        return bestResult;
    }

    public void calculateNewVelocity(Point bestSwarmResult, Double inertia,
                                     Double selfCoefficient, Double selfDeviation,
                                     Double swarmCoefficient, Double swarmDeviation) {
        Point newVelocity = Point.scaleCoordinates(velocity, inertia);
        Point selfInfluence = Point.scaleCoordinates(Point.subtractPoint(bestPosition, position),
                selfCoefficient * selfDeviation);
        Point swarmInfluence = Point.scaleCoordinates(Point.subtractPoint(bestSwarmResult, position),
                swarmCoefficient * swarmDeviation);
        this.velocity = Point.addPoint(Point.addPoint(selfInfluence, swarmInfluence), newVelocity);
        this.velocity.normaliseCoordinates();
    }

    public void step(Point bestSwarmResult, Double inertia,
              Double selfCoefficient, Double selfDeviation,
              Double swarmCoefficient, Double swarmDeviation) {
        this.position = Point.addPoint(position, velocity);
        this.position.normaliseCoordinates();
        calculateNewResult();
        calculateNewVelocity(bestSwarmResult, inertia,
                selfCoefficient, selfDeviation,
                swarmCoefficient, swarmDeviation);
    }

    public Point getPosition() {
        return position;
    }

    public Point getVelocity() {
        return velocity;
    }
}