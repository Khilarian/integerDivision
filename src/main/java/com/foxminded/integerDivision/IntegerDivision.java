package com.foxminded.integerDivision;

public class IntegerDivision {
    
    public void drawDivision(int dividend, int divider) {
        if (divider == 0) {
            throw new ArithmeticException("divider can't be zero.");
        }
        String dividendString = Integer.toString(dividend);
        String modulo = "";
        StringBuilder result = new StringBuilder();
        int printPosition = 0;
        int numberPosition = 0;
        int nextNumberPosition  = 1;
        
        for (int i = 0; nextNumberPosition <= dividendString.length(); i++) {
            String part = calculatePart(dividendString,divider,modulo,numberPosition,nextNumberPosition);
            String subtrahend = calculateSubtrahend(part,divider);        
            modulo = Integer.toString(Integer.parseInt(part) - Integer.parseInt(subtrahend));
                if (i == 0) {
                    result.append("_" + dividend + "|" + divider + "\n");
                } else {
                    result.append(addSpaces(printPosition));
                    result.append("_" + part + "\n");
                }
                printPosition += part.length() - subtrahend.length() + 1;
                result.append(addSpaces(printPosition));
                result.append(subtrahend);
                
                if ( i == 0) {
                    result.append(addSpaces(dividendString.length() - part.length()));
                    result.append("|");
                    result.append(addLine(Integer.toString(dividend/divider)));
                }
                result.append("\n");
                
                result.append(addSpaces(printPosition + subtrahend.length() - part.length()));
                result.append(addLine(part));
                if ( i == 0) {
                    result.append(addSpaces(dividendString.length() - part.length()));
                    result.append("|");
                    result.append(dividend / divider);
                }
                result.append("\n");
                
                if (i == 0) {
                    numberPosition+=part.length();
                } else {
                    numberPosition++;
                }
                nextNumberPosition = numberPosition + 1;
                
                if(modulo.equals("0")) {
                    printPosition++;
                }
                if (subtrahend.equals("0") ) {
                    printPosition -= part.length();
                }
                if (numberPosition >= dividendString.length()) {
                    result.append(addSpaces(printPosition + part.length() - subtrahend.length()));
                    result.append(modulo);
                }
            }
        System.out.print(result.toString());
    }
    
    private String calculatePart(String dividend, int divider, String modulo, int numberPosition, int nextNumberPosition) {
        String result = "0"; 
        if (numberPosition == 0) {
            while(nextNumberPosition <= dividend.length() && Integer.parseInt(result) < divider) {
                    result = dividend.substring(numberPosition,nextNumberPosition);
                nextNumberPosition++;
            }
        } else {
            if (modulo.equals("0")) {
                result = dividend.substring(numberPosition,nextNumberPosition);
            } else {
                result = modulo + dividend.substring(numberPosition,nextNumberPosition);
            }
        }
        return result;
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
    
    private char[] addLine(String divider) {
        char[] result = new char[divider.length()];
        for (int i = 0; i < divider.length(); i++) {
            result[i] = '-';
        }
        return result;
    }
}
