package com.foxminded.integerDivision;

public class IntegerDivision {
    private int dividend;
    private int divider;
    
    public IntegerDivision(int dividend, int divider) {
        this.dividend = dividend;
        this.divider = divider;
    }
    
    public String drawDivision() {
        if (divider == 0) {
            throw new IllegalArgumentException("divider can't be zero.");
        }
        StringBuilder result = new StringBuilder();
        String modulo = "";
        int printPosition = 1;
        int numberPosition = 0;
        
        if (dividend < 0) {
            printPosition++;
        }
        
        for (int i = 0; numberPosition <= Integer.toString(dividend).length() - 1; i++) {
            String dividendPart = calculateDividendPart(modulo, numberPosition);
            String subtrahend = calculateSubtrahend(dividendPart);
            modulo = Integer.toString(Integer.parseInt(dividendPart) - Integer.parseInt(subtrahend));
            result.append(createDividendLine(i, printPosition, dividendPart));
            result.append(createSubtrahendLine(i, printPosition, dividendPart, subtrahend));
            result.append(createSubLine(i, printPosition, dividendPart)); 
            numberPosition += calculateNumberPosition(i, dividendPart);
            if (numberPosition >= Integer.toString(dividend).length()) {
                result.append(createLastLine(printPosition, dividendPart, subtrahend, modulo));
            }
            printPosition += calculatePrintPosition(dividendPart,subtrahend,modulo);
        }
        return result.toString();
    }
    
    private String calculateDividendPart(String modulo, int startPosition) {
        int endPosition = startPosition + 1;
        String result = "0";
        if (Integer.parseInt(Integer.toString(dividend)) < 0 && startPosition == 0) {
            endPosition++;
        }       
        if (startPosition == 0) {
            while(endPosition <= Integer.toString(dividend).length() && Math.abs(Integer.parseInt(result)) < Math.abs(divider)) {
                result = Integer.toString(dividend).substring(startPosition,endPosition);
                endPosition++;
            }
        } else if (endPosition <= Integer.toString(dividend).length()){
                result = Integer.toString(dividend).substring(startPosition, endPosition);
                if (!modulo.equals("0")) {
                result = modulo + result;
            }
        }
        if (dividend < 0 && startPosition == 0) {
            result = result.substring(1);
        }
        return result;
    }
    
    private String createDividendLine(int iteration, int printPosition, String dividendPart) {
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
    
    private String createSubtrahendLine(int iteration, int printPosition, String dividendPart, String subtrahend) {
        StringBuilder result = new StringBuilder();
        printPosition += dividendPart.length() - subtrahend.length();
        result.append(repeatChars(' ', printPosition));
        result.append(subtrahend);
        if ( iteration == 0) {
            result.append(repeatChars(' ', Integer.toString(Math.abs(dividend)).length() - dividendPart.length()));
            result.append("|");
            result.append(repeatChars('-', Math.max(Integer.toString(dividend/divider).length(), Integer.toString(divider).length())));
        }
        result.append("\n");
        return result.toString();
    }
    
    private String createSubLine(int iteration, int printPosition, String dividendPart) {
        StringBuilder result = new StringBuilder();
        result.append(repeatChars(' ', printPosition));
        result.append(repeatChars('-', dividendPart.length()));
        if ( iteration == 0) {
            result.append(repeatChars(' ', Integer.toString(Math.abs(dividend)).length() - dividendPart.length()));
            result.append("|");
            result.append(dividend / divider);
        }
        result.append("\n");
        return result.toString();
    }
    
    private String calculateSubtrahend(String dividendPart) {
        return Integer.toString(Math.abs(Integer.parseInt(dividendPart)) / divider * divider);
    }   
    
    private char[] repeatChars(char symbol, int length) {
        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = symbol;
        }
        return result;
    }
    
    private int calculateNumberPosition(int iteration, String dividendPart) {
        int result = 0;
        if (dividend < 0 && iteration == 0) {
            result++;
        }
        if (iteration == 0) {
            result+=dividendPart.length();
        } else {
            result++;
        }
        return result;
    }
    
    private int calculatePrintPosition(String dividendPart, String subtrahend, String modulo) {
        int result = 0;
        if(modulo.equals("0") && subtrahend.equals("0")) {
            result++;
        }
        if(modulo.equals("0") && !subtrahend.equals("0")) {
            result += subtrahend.length();
        }
        if (!modulo.equals("0") && !subtrahend.equals("0")) {
            result += dividendPart.length() - modulo.length();
        }
        return result;
    }
    
    private String createLastLine(int printPosition, String dividendPart, String subtrahend, String modulo) {
        StringBuilder result = new StringBuilder();
        result.append(repeatChars(' ', printPosition + dividendPart.length() - Integer.toString(Integer.parseInt(dividendPart)-Integer.parseInt(subtrahend)).length()));
        result.append(modulo);
        return result.toString();
    }
}
