package org.example.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryNumber {
    private final List<Integer> number;

    public BinaryNumber(Integer dec) {
        this.number = new ArrayList<>();
        while(dec > 1){
            int newNumber = dec % 2;
            dec /= 2;
            this.number.add(newNumber);
        }
        this.number.add(dec);
    }

    public BinaryNumber(BinaryNumber number) {
        this.number = new ArrayList<>(number.number);
    }

    public List<Integer> getNumber() {
        List<Integer> result = new ArrayList<>(number);
        Collections.reverse(result);
        return result;
    }

    public void convertNumberToNBits(int n){
        for (int i = number.size(); i < n; i++) {
            this.number.add(0);
        }
        while (number.size() > n){
            this.number.remove(number.size()-1);
        }
    }

    public void numberPlusOne(){
        for (int i = 0; i < number.size(); i++) {
            number.set(i, number.get(i) + 1);
            if(number.get(i) == 2){
                number.set(i,0);
                if (i == number.size()-1){
                    number.add(1);
                    break;
                }
            }
            else{
                break;
            }
        }
    }
}
