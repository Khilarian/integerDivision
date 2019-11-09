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
=======
    
    DivisionNumber dividend;
    DivisionNumber divider;
    int resultOfDivision;
    
    public DivisionArguments(int dividend, int divider) {
        this.dividend = new DivisionNumber(dividend);
        this.divider = new DivisionNumber(divider);
        resultOfDivision = dividend / divider;
    }
    
>>>>>>> 7f38e654dad54131e11cf0f0b87e4b8b9855078c
}
