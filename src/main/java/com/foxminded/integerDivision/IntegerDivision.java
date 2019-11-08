package com.foxminded.integerDivision;

public class IntegerDivision {
    
    public String drawDivision(int dividend, int divider) {
        if (divider == 0) {
            throw new IllegalArgumentException("divider can't be zero.");
        }
        DivisionArguments arguments = new DivisionArguments(dividend, divider);
        DivisionParts divisionParts = new DivisionParts();
        StringBuilder result = new StringBuilder();
        int indend = 1;
        int numberPosition = 0;
        
        if (dividend < 0) {
            indend++;
        }
        
        for (int i = 0; numberPosition <= arguments.dividend.length - 1; i++) {
            divisionParts.calculateDivisionParts(arguments, numberPosition);
            result.append(createDividendLine(i, indend, arguments, divisionParts))
                    .append(createSubtrahendLine(i, indend, arguments, divisionParts))
                    .append(createSubLine(i, indend, arguments, divisionParts));
            numberPosition += calculateNumberPosition(i, arguments, divisionParts);
            if (numberPosition >= arguments.dividend.length) {
                result.append(createLastLine(indend, divisionParts));
            }
            indend += calculatePrintPosition(divisionParts);
        }
        return result.toString();
    }
    
    private String createDividendLine(int iteration, int printPosition, DivisionArguments arguments,
            DivisionParts divisionParts) {
        if (iteration == 0) {
            return "_" + arguments.dividend.value + "|" + arguments.divider.value + "\n";
        } else {
            return repeatChars(' ', printPosition - 1) + "_" + divisionParts.dividendPart + "\n";
        }
    }
    
    private String createSubtrahendLine(int iteration, int printPosition, DivisionArguments arguments,
            DivisionParts divisionParts) {
        StringBuilder result = new StringBuilder();
        printPosition += divisionParts.dividendPart.length() - divisionParts.subtrahend.length();
        result.append(repeatChars(' ', printPosition)).append(divisionParts.subtrahend);
        if (iteration == 0) {
            result.append(repeatChars(' ',
                    Integer.toString(Math.abs(arguments.dividend.value)).length()
                            - divisionParts.dividendPart.length()))
                    .append("|").append(repeatChars('-',
                            Math.max(Integer.toString(arguments.resultOfDivision).length(), arguments.divider.length)));
        }
        result.append("\n");
        return result.toString();
    }
    
    private String createSubLine(int iteration, int printPosition, DivisionArguments arguments,
            DivisionParts divisionParts) {
        StringBuilder result = new StringBuilder();
        result.append(repeatChars(' ', printPosition)).append(repeatChars('-', divisionParts.dividendPart.length()));
        if (iteration == 0) {
            result.append(
                    repeatChars(' ',
                            Integer.toString(Math.abs(arguments.dividend.value)).length()
                                    - divisionParts.dividendPart.length()))
                    .append("|").append(arguments.resultOfDivision);
        }
        result.append("\n");
        return result.toString();
    }
    
    private String repeatChars(char symbol, int length) {
        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = symbol;
        }
        return new String(result);
    }
    
    private int calculateNumberPosition(int iteration, DivisionArguments arguments, DivisionParts divisionParts) {
        int result = 0;
        if (arguments.dividend.value < 0 && iteration == 0) {
            result++;
        }
        if (iteration == 0) {
            result += divisionParts.dividendPart.length();
        } else {
            result++;
        }
        return result;
    }
    
    private int calculatePrintPosition(DivisionParts divisionParts) {
        int result = 0;
        if (divisionParts.modulo.equals("0") && divisionParts.subtrahend.equals("0")) {
            result++;
        }
        if (divisionParts.modulo.equals("0") && !divisionParts.subtrahend.equals("0")) {
            result += divisionParts.subtrahend.length();
        }
        if (!divisionParts.modulo.equals("0") && !divisionParts.subtrahend.equals("0")) {
            result += divisionParts.dividendPart.length() - divisionParts.modulo.length();
        }
        return result;
    }
    
    private String createLastLine(int printPosition, DivisionParts divisionParts) {
        return repeatChars(' ', printPosition + divisionParts.dividendPart.length() - divisionParts.modulo.length())
                + divisionParts.modulo;
    }
}
