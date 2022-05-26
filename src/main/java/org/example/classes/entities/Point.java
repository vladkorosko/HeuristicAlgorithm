package org.example.classes.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Point {
    private final List<Double> coordinates;
    private final Double range;

    public Point(Integer size, Double range) {
        this.range = range / 2.0;
        Random random = new Random();
        this.coordinates = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            double d = random.nextDouble() * range - range;
            d = roundCoordinate(d);
            this.coordinates.add(d);
        }
    }

    public Point(List<Double> coordinates, Double range) {
        this.coordinates = coordinates;
        this.range = range;
    }

    public static Point addPoint(Point lhs, Point rhs) {
        List<Double> newPoint = new ArrayList<>();
        for (int i = 0; i < lhs.coordinates.size(); i++) {
            newPoint.add(lhs.coordinates.get(i) + rhs.coordinates.get(i));
        }
        return new Point(newPoint, lhs.range);
    }

    public static Point subtractPoint(Point lhs, Point rhs) {
        List<Double> newPoint = new ArrayList<>();
        for (int i = 0; i < lhs.coordinates.size(); i++) {
            newPoint.add(lhs.coordinates.get(i) - rhs.coordinates.get(i));
        }
        return new Point(newPoint, lhs.range);
    }

    public static Point scaleCoordinates(Point point, double scale) {
        List<Double> newPoint = new ArrayList<>();
        for (int i = 0; i < point.coordinates.size(); i++) {
            newPoint.add(point.coordinates.get(i) * scale);
        }
        return new Point(newPoint, point.range);
    }

    private double roundCoordinate(double number) {
        return ((int) Math.round(number * 10000.0)) / 10000.0;
    }

    public void normaliseCoordinates() {
        for (int i = 0; i < coordinates.size(); i++) {
            if (coordinates.get(i) > range)
                coordinates.set(i, coordinates.get(i) - 2.0 * range);
            else if (coordinates.get(i) < (-1) * range) {
                coordinates.set(i, coordinates.get(i) + 2.0 * range);
            }
            coordinates.set(i, roundCoordinate(coordinates.get(i)));
        }
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public Double getRange() {
        return range;
    }

    @Override
    public String toString() {
        return "Point{" +
                "coordinates=" + coordinates +
                '}';
    }
}
