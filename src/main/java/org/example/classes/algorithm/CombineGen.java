package org.example.classes.algorithm;

import org.example.classes.entities.BinaryNumber;
import org.example.classes.entities.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface CombineGen {

    private static List<Double> getRange(List<Point> list){
        Double minimum = list.get(0).getCoordinates().get(0);
        Double maximum = list.get(0).getCoordinates().get(0);
        for (Point point: list){
            for (Double coordinate: point.getCoordinates()){
                if(minimum > coordinate)
                    minimum = coordinate;
                if(maximum < coordinate)
                    maximum = coordinate;
            }
        }
        return List.of(minimum,maximum);
    }
    static List<Point> newGeneration(Point parent1, Point parent2) {
        List<Double> gens1 = parent1.getCoordinates();
        List<Double> gens2 = parent2.getCoordinates();
        BinaryNumber iterator = new BinaryNumber(0);
        iterator.convertNumberToNBits(gens1.size());
        List<Point> result = new ArrayList<>();
        Random random = new Random();
        int chance = 10/gens1.size();
        List<Double> range = CombineGen.getRange(List.of(parent1,parent2));
        while (gens1.size() == iterator.getNumber().size())
        {
            List<Double> newPointCoordinates = new ArrayList<>();
            for (int i = 0; i < gens1.size(); i++) {
                int mutation = Math.abs(random.nextInt())%100;
                if(mutation<=chance) {
                    //newPointCoordinates.add(0.0);
                    newPointCoordinates.add(random.nextDouble() * (range.get(1) - range.get(0)) - range.get(0));
                }
                else {
                    if (iterator.getNumber().get(i) == 1)
                        newPointCoordinates.add(gens1.get(i));
                    else newPointCoordinates.add(gens2.get(i));
                }
            }
            result.add(new Point(newPointCoordinates, parent1.getRange()));
            iterator.numberPlusOne();
        }
        return result;
    }

}
