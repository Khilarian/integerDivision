package com.foxminded.integerDivision;

public class IntegerDivision {
    
    public void drawDivision(int dividend, int divider) {
        if (divider == 0) {
            throw new ArithmeticException("divider can't be zero.");
        }
        StringBuilder result = new StringBuilder();
        String dividendString = Integer.toString(dividend);
        String modulo = "";
        int printPosition = 1;
        int numberPosition = 0;
        int printCorrection = 0;
        
        if (dividendString.charAt(0) == '-') {
            dividendString = dividendString.substring(1);
            printPosition++;
            printCorrection--;
        }
        
        for (int i = 0; numberPosition <= dividendString.length() - 1; i++) {
            String part = calculatePart(dividendString, divider, modulo, numberPosition);
            String subtrahend = calculateSubtrahend(part, divider);        
            modulo = Integer.toString(Integer.parseInt(part) - Integer.parseInt(subtrahend));
            
            result.append(addDividendLine(i, printPosition, dividend, divider, part));
            result.append(addSubtrahendLine(i, printPosition, printCorrection, dividend, divider, part, subtrahend));
            result.append(addSubLine(i, printPosition, printCorrection, dividend, divider, part, subtrahend));   
            numberPosition += calculateNumberPosition(i, part);
            
            if (numberPosition >= dividendString.length()) {
                result.append(addLastLine(printPosition, part, subtrahend, modulo));
            }
            printPosition += calculatePrintPosition(part,subtrahend,modulo);
        }
        System.out.print(result.toString());
    }
    
    private String calculatePart(String dividend, int divider, String modulo, int numberPosition) {
        String result = "0"; 
        int nextNumberPosition = numberPosition + 1;
        if (numberPosition == 0) {
            while(nextNumberPosition <= dividend.length() && Integer.parseInt(result) < Math.abs(divider)) {
                result = dividend.substring(numberPosition,nextNumberPosition);
                nextNumberPosition++;
            }
        } else {
                result = dividend.substring(numberPosition, nextNumberPosition);
                if (!modulo.equals("0")) {
                result = modulo + dividend.substring(numberPosition,nextNumberPosition);
            }
        }
        return result;
    }
    
    private String addDividendLine(int i, int printPosition, int dividend, int divider, String part) {
        StringBuffer result = new StringBuffer();
        if (i == 0) {
            result.append("_" + dividend + "|" + divider + "\n");
        } else {
            result.append(addSpaces(printPosition - 1));
            result.append("_" + part + "\n");
        }
        return result.toString();
    }
    
    private String addSubtrahendLine(int i, int printPosition, int printCorrection, int dividend, int divider, String part, String subtrahend) {
        StringBuffer result = new StringBuffer();
        printPosition += part.length() - subtrahend.length();
        result.append(addSpaces(printPosition));
        result.append(subtrahend);
        if ( i == 0) {
            result.append(addSpaces(Integer.toString(dividend).length() - part.length() + printCorrection));
            result.append("|");
            result.append(addLine(Math.max(Integer.toString(dividend/divider).length(), Integer.toString(divider).length())));
        }
        result.append("\n");
        return result.toString();
    }
    
    private String addSubLine(int i, int printPosition, int printCorrection, int dividend, int divider, String part, String subtrahend) {
        StringBuffer result = new StringBuffer();
        result.append(addSpaces(printPosition + subtrahend.length() - part.length()));
        result.append(addLine(part.length()));
        if ( i == 0) {
            result.append(addSpaces(Integer.toString(dividend).length() - part.length() + printCorrection));
            result.append("|");
            result.append(dividend / divider);
        }
        result.append("\n");
        return result.toString();
    }
    
    private char[] addSpaces(int printPosition) {
        char[] spaces = new char[printPosition];
        for (int i = 0; i < printPosition; i++) {
            spaces[i] = ' ';
        }
        return spaces;
    }
    
    private String calculateSubtrahend(String dividend, int divider) {
        return Integer.toString(Integer.parseInt(dividend) / divider * divider);
    }
    
    private char[] addLine(int length) {
        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = '-';
        }
        return result;
    }
    
    private int calculateNumberPosition(int i, String part) {
        int result = 0;
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
        if (!modulo.equals("0") && subtrahend.equals("0") ) {
            result -= part.length();
        }
        if (!modulo.equals("0") && !subtrahend.equals("0")) {
            result += part.length() - modulo.length();
        }
        return result;
    }
    
    private String addLastLine(int printPosition, String part, String subtrahend, String modulo) {
        StringBuilder result = new StringBuilder();
        result.append(addSpaces(printPosition + subtrahend.length() - Integer.toString(Integer.parseInt(part)- Integer.parseInt(subtrahend)).length()));
        result.append(modulo);
        return result.toString();
    }
}
