package org.example.classes;

public class RastriginFunction {
    private static final Integer A = 10;

    public static Long calculateResult(Point p) {
        long result = (long) A * p.getCoordinates().size();
        for (Double i : p.getCoordinates()) {
            result += (long) (i * i - A * Math.cos(2 * Math.PI * i));
        }
        return result;
    }
}
