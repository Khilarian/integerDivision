package com.foxminded.integerDivision;

public class IntegerDivision {
    
    public IntegerDivision() {
    }
    
    public String drawDivision(int dividend, int divider) {
        if (divider == 0) {
            throw new IllegalArgumentException("divider can't be zero.");
        }
        StringBuilder result = new StringBuilder();
        int modulo = 0;
        int printPosition = 1;
        int numberPosition = 0;
        
        for (int i = 0; numberPosition <= getLength(dividend) - 1; i++) {
            int dividendPart = calculateDividendPart(dividend, divider, modulo, numberPosition);
            int subtrahend = calculateSubtrahend(divider, dividendPart);
            modulo = dividendPart - subtrahend;
            result.append(createDividendLine(dividend, divider, dividendPart, i, printPosition));
            result.append(createSubtrahendLine(dividend, divider, dividendPart, subtrahend, i, printPosition));
            result.append(createSubLine(dividend, divider, dividendPart, i, printPosition)); 
            numberPosition += calculateNumberPosition(i, dividendPart);
            if (numberPosition >= Integer.toString(dividend).length()) {
                result.append(createLastLine(dividendPart, subtrahend, modulo, printPosition));
            }
            printPosition += calculatePrintPosition(dividendPart,subtrahend,modulo);
        }
        return result.toString();
    }
    
    private int calculateDividendPart(int dividend, int divider, int modulo, int startPosition) {
        int endPosition = startPosition + 1;
        int result = 0;
        if (startPosition == 0) {
            while(endPosition <= getLength(dividend) && result < divider) {
                result = Integer.parseInt(String.valueOf(dividend).substring(startPosition,endPosition));
                endPosition++;
            }
        } else if (endPosition <= getLength(dividend)){
                result = Integer.parseInt(String.valueOf(dividend).substring(startPosition,endPosition));
                if (startPosition != 0 && modulo != 0) {
                result = Integer.parseInt(String.valueOf(modulo) + String.valueOf(result));
            }
        }
        return result;
    }
    
    private String createDividendLine(int dividend, int divider, int dividendPart, int iteration, int printPosition) {
        StringBuilder result = new StringBuilder();
        if (iteration == 0) {
            result.append("_");
            result.append(dividend);
            result.append("|");
            result.append(divider);
            result.append("\n");
        } else {
            result.append(repeatChars(' ', printPosition - 1));
            result.append("_");
            result.append(dividendPart);
            result.append("\n");
        }
        return result.toString();
    }
    
    private String createSubtrahendLine(int dividend, int divider, int dividendPart, int subtrahend, int iteration, int printPosition) {
        StringBuilder result = new StringBuilder();
        printPosition += getLength(dividendPart) - getLength(subtrahend);
        result.append(repeatChars(' ', printPosition));
        result.append(subtrahend);
        if ( iteration == 0) {
            result.append(repeatChars(' ', getLength(dividend) - getLength(dividendPart)));
            result.append("|");
            result.append(repeatChars('-', Math.max(getLength(dividend/divider), getLength(divider))));
        }
        result.append("\n");
        return result.toString();
    }
    
    private String createSubLine(int dividend, int divider, int dividendPart, int iteration, int printPosition) {
        StringBuilder result = new StringBuilder();
        result.append(repeatChars(' ', printPosition));
        result.append(repeatChars('-', getLength(dividendPart)));
        if ( iteration == 0) {
            result.append(repeatChars(' ', getLength(dividend) - getLength(dividendPart)));
            result.append("|");
            result.append(dividend / divider);
        }
        result.append("\n");
        return result.toString();
    }
    
    private int calculateSubtrahend(int divider, int dividendPart) {
        return dividendPart / divider * divider;
    }   
    
    private char[] repeatChars(char symbol, int length) {
        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = symbol;
        }
        return result;
    }
    
    private int calculateNumberPosition(int iteration, int dividendPart) {
        int result = 0;
        if (iteration == 0) {
            result+=getLength(dividendPart);
        } else {
            result++;
        }
        return result;
    }
    
    private int calculatePrintPosition(int dividendPart, int subtrahend, int modulo) {
        int result = 0;
        if(modulo == 0 && subtrahend == 0) {
            result++;
        }
        if(modulo == 0 && subtrahend != 0) {
            result += getLength(subtrahend);
        }
        if (modulo != 0 && subtrahend != 0) {
            result += getLength(dividendPart) - getLength(modulo);
        }
        return result;
    }
    
    private String createLastLine(int dividendPart, int subtrahend, int modulo, int printPosition) {
        StringBuilder result = new StringBuilder();
        result.append(repeatChars(' ', printPosition + getLength(dividendPart) - getLength(dividendPart - subtrahend)));
        result.append(modulo);
        return result.toString();
    }
    
    private int getLength(int number) {
        return String.valueOf(number).length();
    }
    
    private String getString(int number) {
        return String.valueOf(number);
    }
}
