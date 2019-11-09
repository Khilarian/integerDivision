package com.foxminded.integerDivision;

public class LineCalculator {
    int dividendPart;
    int subtrahend;
    int modulo;
    
    public LineCalculator() {
        this.dividendPart = 0;
        this.subtrahend = 0;
        this.subtrahend = 0;
    }
    
    public void calculateLine(DivisionArguments arguments, int startPosition) {
        calculateDividendPart(arguments, startPosition);
        calculateSubtrahend(arguments);
        calculateModulo();
    }
    
    private void calculateDividendPart(DivisionArguments arguments, int startPosition) {
        int endPosition = startPosition + 1;
        int result = 0;
        if (startPosition == 0) {
            while (endPosition <= getLength(arguments.dividend) && result < arguments.divider) {
                result = Integer.parseInt(String.valueOf(arguments.dividend).substring(startPosition, endPosition));
                endPosition++;
            }
        } else if (endPosition <= getLength(arguments.dividend)) {
            result = Integer.parseInt(String.valueOf(arguments.dividend).substring(startPosition, endPosition));
            if (startPosition != 0 && modulo != 0) {
                result = Integer.parseInt(String.valueOf(modulo) + String.valueOf(result));
            }
        }
        dividendPart = result;
    }
    
    private void calculateSubtrahend(DivisionArguments arguments) {
        subtrahend = dividendPart / arguments.divider * arguments.divider;
    }
    
    private void calculateModulo() {
        modulo = dividendPart - subtrahend;
    }
    
    private int getLength(int number) {
        return String.valueOf(number).length();
    }
}
