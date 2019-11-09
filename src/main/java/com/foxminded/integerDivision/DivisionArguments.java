package com.foxminded.integerDivision;

public class DivisionArguments {
    int dividend;
    int divider;
    int quotient;
    
    public DivisionArguments(int dividend, int divider) {
        this.dividend = dividend;
        this.divider = divider;
        this.quotient = dividend / divider;
    }
}
