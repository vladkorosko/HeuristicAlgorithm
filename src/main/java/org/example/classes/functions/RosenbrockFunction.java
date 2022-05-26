package org.example.classes.functions;

import org.example.classes.entities.Point;

public class RosenbrockFunction extends TestOptimizationFunction{
    public Long calculateResult(Point p){
        long result = 0;
        for (int i = 0; i < p.getCoordinates().size() - 1; i++) {
            result += (long) Math.ceil(100.0 * (p.getCoordinates().get(i+1) - p.getCoordinates().get(i) * p.getCoordinates().get(i)) *
                    (p.getCoordinates().get(i+1) - p.getCoordinates().get(i) * p.getCoordinates().get(i)) +
                    (1 - p.getCoordinates().get(i)) * (1 - p.getCoordinates().get(i)));
        }
        return result;
    }
}
