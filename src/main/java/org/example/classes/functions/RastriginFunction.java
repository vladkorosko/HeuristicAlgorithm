package org.example.classes.functions;

import org.example.classes.entities.Point;

public class RastriginFunction extends TestOptimizationFunction{
    private static final Integer A = 10;

    public Long calculateResult(Point p) {
        long result = (long) A * p.getCoordinates().size();
        for (Double i : p.getCoordinates()) {
            result += (long) (i * i - A * Math.cos(2 * Math.PI * i));
        }
        return result;
    }
}
