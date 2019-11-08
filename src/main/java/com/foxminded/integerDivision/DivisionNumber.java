package com.foxminded.integerDivision;

public class DivisionNumber {
    int value;
    String string;
    int length;
    
    public DivisionNumber(int number) {
        this.value = number;
        this.string = Integer.toString(number);
        this.length = string.length();
    }
    
}
