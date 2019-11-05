package com.foxminded.integerDivision;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IntegerDivisionTest {
    
    IntegerDivision id = new IntegerDivision();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    
    @Test
    void drawDivisionReturnArithmeticExceptionWhenDividerIsZero() {
        assertThrows(ArithmeticException.class, () -> id.drawDivision(3,0));
    }   
    
    @Test
    void drawDivisionReturnPositiveResultAfterDivisionTwoPositiveNumbers() {
        String expected = "_2|1\n"
                +         " 2|-\n"
                +         " -|2\n"
                +         " 0";
        id.drawDivision(2, 1);
        assertEquals(expected, outContent.toString());
    }   
    
    @Test
    void drawDivisionReturnNegativeResultAfterDivisionNegativeAndPositiveNumbers() {
        String expected = "_-2|1\n"
                +         "  2|--\n"
                +         "  -|-2\n"
                +         "  0";
        id.drawDivision(-2, 1);
        assertEquals(expected, outContent.toString());
    } 
    
    @Test
    void drawDivisionReturnNegativeResultAfterDivisionPositiveandNegativeNumbers() {
        String expected = "_2|-1\n"
                +         " 2|--\n"
                +         " -|-2\n"
                +         " 0";
        id.drawDivision(2, -1);
        assertEquals(expected, outContent.toString());
    }  
    
    @Test
    void drawDivisionReturnPositiveResultAfterDivisionTwoNegativeNumbers() {
        String expected = "_-2|-1\n"
                +         "  2|--\n"
                +         "  -|2\n"
                +         "  0";
        id.drawDivision(-2, -1);
        assertEquals(expected, outContent.toString());
    } 
    
    @Test
    void drawDivisionReturnZeroWhenFirstNumberIsZero() {
        String expected = "_0|-1\n"
                +         " 0|--\n"
                +         " -|0\n"
                +         " 0";
        id.drawDivision(0, -1);
        assertEquals(expected, outContent.toString());
    } 
    
    void drawDivisionReturnNumberWithRemainderOfDivisionWhenTwoInputsNumbersDoNotHaveCommonFactor() {
        String expected = "_563|4\n"
                +         " 4  |---\n"
                +         " -  |140\n"
                +         "_16\n"
                +         " 16\n"
                +         " --\n"
                +         "  _3\n"
                +         "   0\n"
                +         "   -\n"
                +         "   3";
        id.drawDivision(563, 4);
        assertEquals(expected, outContent.toString());
    } 
    
    void drawDivisionReturnLotsOfSameNumeralIfDividedContainsOfLotsSameNumeralAndDividedIsOneNumeralThatHaveCommonFactor() {
        String expected = "_999|3\n"
                +         " 9  |---\n"
                +         " -  |333\n"
                +         " _9\n"
                +         "  9\n"
                +         "  -\n"
                +         "  _9\n"
                +         "   9\n"
                +         "   -\n"
                +         "   0";
        id.drawDivision(999, 3);
        assertEquals(expected, outContent.toString());
    } 
}
