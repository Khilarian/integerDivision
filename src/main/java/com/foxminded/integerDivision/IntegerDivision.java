package com.foxminded.integerDivision;

public class IntegerDivision {
    private int dividend;
    private int divider;
    
    public IntegerDivision(int dividend, int divider) {
        this.dividend = dividend;
        this.divider = divider;
    }
    
    public void drawDivision() {
        if (divider == 0) {
            throw new IllegalArgumentException("divider can't be zero.");
        }
        StringBuilder result = new StringBuilder();
        String dividendString = Integer.toString(dividend);
        String modulo = "";
        int printPosition = 1;
        int numberPosition = 0;
        
        if (dividend < 0) {
            printPosition++;
        }
        
        for (int i = 0; numberPosition <= dividendString.length() - 1; i++) {
            String part = calculatePart(dividendString, modulo, numberPosition);
            String subtrahend = calculateSubtrahend(part);
            modulo = Integer.toString(Integer.parseInt(part) - Integer.parseInt(subtrahend));
            result.append(addDividendLine(i, printPosition, part));
            result.append(addSubtrahendLine(i, printPosition, part, subtrahend));
            result.append(addSubLine(i, printPosition, part, subtrahend)); 
            numberPosition += calculateNumberPosition(i, part);
            if (numberPosition >= dividendString.length()) {
                result.append(addLastLine(printPosition, part, subtrahend, modulo));
            }
            printPosition += calculatePrintPosition(part,subtrahend,modulo);
        }
        System.out.print(result.toString());
    }
    
    private String calculatePart(String dividend, String modulo, int startPosition) {
        int endPosition = startPosition + 1;
        String result = "0";
        if (Integer.parseInt(dividend) < 0 && startPosition == 0) {
            endPosition++;
        }       
        if (startPosition == 0) {
            while(endPosition <= dividend.length() && Math.abs(Integer.parseInt(result)) < Math.abs(divider)) {
                result = dividend.substring(startPosition,endPosition);
                endPosition++;
            }
        } else if (endPosition <= dividend.length()){
                result = dividend.substring(startPosition, endPosition);
                if (!modulo.equals("0")) {
                result = modulo + result;
            }
        }
        if (Integer.parseInt(dividend) < 0 && startPosition == 0) {
            result = result.substring(1);
        }
        return result;
    }
    
    private String addDividendLine(int i, int printPosition, String part) {
        StringBuilder result = new StringBuilder();
        if (i == 0) {
            result.append("_" + dividend + "|" + divider + "\n");
        } else {
            result.append(addLineOfChars(printPosition - 1, ' '));
            result.append("_" + part + "\n");
        }
        return result.toString();
    }
    
    private String addSubtrahendLine(int i, int printPosition, String part, String subtrahend) {
        StringBuilder result = new StringBuilder();
        printPosition += part.length() - subtrahend.length();
        result.append(addLineOfChars(printPosition, ' '));
        result.append(subtrahend);
        if ( i == 0) {
            result.append(addLineOfChars(Integer.toString(Math.abs(dividend)).length() - part.length(), ' '));
            result.append("|");
            result.append(addLineOfChars(Math.max(Integer.toString(dividend/divider).length(), Integer.toString(divider).length()), '-'));
        }
        result.append("\n");
        return result.toString();
    }
    
    private String addSubLine(int i, int printPosition, String part, String subtrahend) {
        StringBuilder result = new StringBuilder();
        result.append(addLineOfChars(printPosition, ' '));
        result.append(addLineOfChars(part.length(), '-'));
        if ( i == 0) {
            result.append(addLineOfChars(Integer.toString(Math.abs(dividend)).length() - part.length(), ' '));
            result.append("|");
            result.append(dividend / divider);
        }
        result.append("\n");
        return result.toString();
    }
    
    private String calculateSubtrahend(String part) {
        return Integer.toString(Math.abs(Integer.parseInt(part)) / divider * divider);
    }   
    
    /*private char[] addSpaces(int printPosition) {
        char[] spaces = new char[printPosition];
        for (int i = 0; i < printPosition; i++) {
            spaces[i] = ' ';
        }
        return spaces;
    }
   
    private char[] addLine(int length) {
        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = '-';
        }
        return result;
    }
    */
    private char[] addLineOfChars(int length, char symbol) {
        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = symbol;
        }
        return result;
    }
    
    private int calculateNumberPosition(int i, String part) {
        int result = 0;
        if (dividend < 0 && i == 0) {
            result++;
        }
        if (i == 0) {
            result+=part.length();
        } else {
            result++;
        }
        return result;
    }
    
    private int calculatePrintPosition(String part, String subtrahend, String modulo) {
        int result = 0;
        if(modulo.equals("0") && subtrahend.equals("0")) {
            result++;
        }
        if(modulo.equals("0") && !subtrahend.equals("0")) {
            result += subtrahend.length();
        }
        if (!modulo.equals("0") && !subtrahend.equals("0")) {
            result += part.length() - modulo.length();
        }
        return result;
    }
    
    private String addLastLine(int printPosition, String part, String subtrahend, String modulo) {
        StringBuilder result = new StringBuilder();
        result.append(addLineOfChars(printPosition + part.length() - Integer.toString(Integer.parseInt(part)-Integer.parseInt(subtrahend)).length(), ' '));
        result.append(modulo);
        return result.toString();
    }
}
