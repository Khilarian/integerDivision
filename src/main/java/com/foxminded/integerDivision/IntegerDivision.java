package com.foxminded.integerDivision;

public class IntegerDivision {
    
    public IntegerDivision() {
    }
    
    public String drawDivision(int dividend, int divider) {
        if (divider == 0) {
            throw new IllegalArgumentException("divider can't be zero.");
        }
        StringBuilder result = new StringBuilder();
        DivisionArguments arguments = new DivisionArguments(dividend, divider);
        LineCalculator lineCalculator = new LineCalculator();
        int printPosition = 1;
        int numberPosition = 0;
        
        for (int i = 0; numberPosition <= getLength(dividend) - 1; i++) {
            lineCalculator.calculateLine(arguments, numberPosition);
            result.append(createDividendLine(arguments, lineCalculator, i, printPosition));
            result.append(createSubtrahendLine(arguments, lineCalculator, i, printPosition));
            result.append(createSubLine(arguments, lineCalculator, i, printPosition));
            numberPosition += calculateNumberPosition(i, lineCalculator);
            if (numberPosition >= Integer.toString(dividend).length()) {
                result.append(createLastLine(lineCalculator, printPosition));
            }
            printPosition += calculatePrintPosition(lineCalculator);
        }
        return result.toString();
    }
    
    private String createDividendLine(DivisionArguments arguments, LineCalculator lineCalculator, int iteration,
            int printPosition) {
        StringBuilder result = new StringBuilder();
        if (iteration == 0) {
            result.append("_");
            result.append(arguments.dividend);
            result.append("|");
            result.append(arguments.divider);
            result.append("\n");
        } else {
            result.append(repeatChars(' ', printPosition - 1));
            result.append("_");
            result.append(lineCalculator.dividendPart);
            result.append("\n");
        }
        return result.toString();
    }
    
    private String createSubtrahendLine(DivisionArguments arguments, LineCalculator lineCalculator, int iteration,
            int printPosition) {
        StringBuilder result = new StringBuilder();
        printPosition += getLength(lineCalculator.dividendPart) - getLength(lineCalculator.subtrahend);
        result.append(repeatChars(' ', printPosition));
        result.append(lineCalculator.subtrahend);
        if (iteration == 0) {
            result.append(repeatChars(' ', getLength(arguments.dividend) - getLength(lineCalculator.dividendPart)));
            result.append("|");
            result.append(repeatChars('-', Math.max(getLength(arguments.quotient), getLength(arguments.divider))));
        }
        result.append("\n");
        return result.toString();
    }
    
    private String createSubLine(DivisionArguments arguments, LineCalculator lineCalculator, int iteration,
            int printPosition) {
        StringBuilder result = new StringBuilder();
        result.append(repeatChars(' ', printPosition));
        result.append(repeatChars('-', getLength(lineCalculator.dividendPart)));
        if (iteration == 0) {
            result.append(repeatChars(' ', getLength(arguments.dividend) - getLength(lineCalculator.dividendPart)));
            result.append("|");
            result.append(arguments.quotient);
        }
        result.append("\n");
        return result.toString();
    }
    
    private char[] repeatChars(char symbol, int length) {
        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = symbol;
        }
        return result;
    }
    
    private int calculateNumberPosition(int iteration, LineCalculator lineCalculator) {
        int result = 0;
        if (iteration == 0) {
            result += getLength(lineCalculator.dividendPart);
        } else {
            result++;
        }
        return result;
    }
    
    private int calculatePrintPosition(LineCalculator lineCalculator) {
        int result = 0;
        if (lineCalculator.modulo == 0 && lineCalculator.subtrahend == 0) {
            result++;
        }
        if (lineCalculator.modulo == 0 && lineCalculator.subtrahend != 0) {
            result += getLength(lineCalculator.subtrahend);
        }
        if (lineCalculator.modulo != 0 && lineCalculator.subtrahend != 0) {
            result += getLength(lineCalculator.dividendPart) - getLength(lineCalculator.modulo);
        }
        return result;
    }
    
    private String createLastLine(LineCalculator lineCalculator, int printPosition) {
        StringBuilder result = new StringBuilder();
        result.append(repeatChars(' ',
                printPosition + getLength(lineCalculator.dividendPart) - getLength(lineCalculator.modulo)));
        result.append(lineCalculator.modulo);
        return result.toString();
    }
    
    private int getLength(int number) {
        return String.valueOf(number).length();
    }
    
}
