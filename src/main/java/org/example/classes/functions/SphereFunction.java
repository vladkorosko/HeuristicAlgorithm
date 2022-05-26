package org.example.classes.functions;

import org.example.classes.entities.Point;

public class SphereFunction extends TestOptimizationFunction{
    public Long calculateResult(Point p) {
        long result = 0;
        for (Double i : p.getCoordinates()) {
            result += Math.ceil(i * i);
        }
        return result;
    }
}
