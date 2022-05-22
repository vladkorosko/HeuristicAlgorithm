package org.example.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Point {
    private final List<Double> coordinates;

    public Point(Integer size, Double range) {
        Random random = new Random();
        this.coordinates = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            double d = random.nextDouble() * range - range;
            this.coordinates.add(d);
        }
    }

    public Point(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public static Point addPoint(Point lhs, Point rhs) {
        List<Double> newPoint = new ArrayList<>();
        for (int i = 0; i < lhs.coordinates.size(); i++) {
            newPoint.add(lhs.coordinates.get(i) + rhs.coordinates.get(i));
        }
        return new Point(newPoint);
    }

    public static Point subtractPoint(Point lhs, Point rhs) {
        List<Double> newPoint = new ArrayList<>();
        for (int i = 0; i < lhs.coordinates.size(); i++) {
            newPoint.add(lhs.coordinates.get(i) - rhs.coordinates.get(i));
        }
        return new Point(newPoint);
    }

    public static Point scaleCoordinates(Point point, double scale) {
        List<Double> newPoint = new ArrayList<>();
        for (int i = 0; i < point.coordinates.size(); i++) {
            newPoint.add(point.coordinates.get(i) * scale);
        }
        return new Point(newPoint);
    }

    @Override
    public String toString() {
        return "Point{" +
                "coordinates=" + coordinates +
                '}';
    }
}
