package com.foxminded.integerDivision;

public class DivisionArguments {
    
    DivisionNumber dividend;
    DivisionNumber divider;
    int resultOfDivision;
    
    public DivisionArguments(int dividend, int divider) {
        this.dividend = new DivisionNumber(dividend);
        this.divider = new DivisionNumber(divider);
        resultOfDivision = dividend / divider;
    }
    
}
