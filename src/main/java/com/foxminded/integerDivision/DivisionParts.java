package com.foxminded.integerDivision;

public class DivisionParts {
    String dividendPart;
    String subtrahend;
    String modulo;
    
    public DivisionParts() {
        dividendPart = "0";
        subtrahend = "";
        modulo = "";
    }
    
    public void calculateDivisionParts(DivisionArguments arguments, int startPosition) {
        calculateDividendPart(arguments, startPosition);
        calculateSubtrahend(arguments);
        calculateModulo();
    }
    
    private void calculateDividendPart(DivisionArguments arguments, int startPosition) {
        int endPosition = startPosition + 1;
        if (arguments.dividend.value < 0 && startPosition == 0) {
            endPosition++;
        }
        if (startPosition == 0) {
            while (endPosition <= arguments.dividend.length
                    && Math.abs(Integer.parseInt(dividendPart)) < Math.abs(arguments.divider.value)) {
                dividendPart = arguments.dividend.string.substring(startPosition, endPosition);
                endPosition++;
            }
        } else if (endPosition <= arguments.dividend.length) {
            dividendPart = arguments.dividend.string.substring(startPosition, endPosition);
            if (!modulo.equals("0")) {
                dividendPart = modulo + dividendPart;
            }
        }
        if (arguments.dividend.value < 0 && startPosition == 0) {
            dividendPart = dividendPart.substring(1);
        }
    }
    
    private void calculateSubtrahend(DivisionArguments arguments) {
        subtrahend = Integer
                .toString(Math.abs(Integer.parseInt(dividendPart)) / arguments.divider.value * arguments.divider.value);
    }
    
    private void calculateModulo() {
        modulo = Integer.toString(Integer.parseInt(dividendPart) - Integer.parseInt(subtrahend));
    }
}
