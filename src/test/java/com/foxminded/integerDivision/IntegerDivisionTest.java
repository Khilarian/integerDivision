package com.foxminded.integerDivision;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IntegerDivisionTest {
    
    
    @Test
    void drawDivisionReturnIllegalArgumentExceptionWhenDividerIsZero() {
        IntegerDivision id = new IntegerDivision(3,0);
        assertThrows(IllegalArgumentException.class, () -> id.drawDivision());
    }   
    
    @Test
    void drawDivisionReturnPositiveResultAfterDivisionTwoPositiveNumbers() {
        IntegerDivision id = new IntegerDivision(2,1);
        String expected = "_2|1\n"
                +         " 2|-\n"
                +         " -|2\n"
                +         " 0";
        String actual = id.drawDivision();
        assertEquals(expected, actual);
    }   
    
    @Test
    void drawDivisionReturnNegativeResultAfterDivisionNegativeAndPositiveNumbers() {
        IntegerDivision id = new IntegerDivision(-2,1);
        String expected = "_-2|1\n"
                +         "  2|--\n"
                +         "  -|-2\n"
                +         "  0";
        String actual = id.drawDivision();
        assertEquals(expected, actual);
    } 
    
    @Test
    void drawDivisionReturnNegativeResultAfterDivisionPositiveandNegativeNumbers() {
        IntegerDivision id = new IntegerDivision(2,-1);
        String expected = "_2|-1\n"
                +         " 2|--\n"
                +         " -|-2\n"
                +         " 0";
        String actual = id.drawDivision();
        assertEquals(expected, actual);
    }  
    
    @Test
    void drawDivisionReturnPositiveResultAfterDivisionTwoNegativeNumbers() {
        IntegerDivision id = new IntegerDivision(-2,-1);
        String expected = "_-2|-1\n"
                +         "  2|--\n"
                +         "  -|2\n"
                +         "  0";
        String actual = id.drawDivision();
        assertEquals(expected, actual);
    } 
    
    @Test
    void drawDivisionReturnZeroWhenFirstNumberIsZero() {
        IntegerDivision id = new IntegerDivision(0,-1);
        String expected = "_0|-1\n"
                +         " 0|--\n"
                +         " -|0\n"
                +         " 0";
        String actual = id.drawDivision();
        assertEquals(expected, actual);
    } 
    
    @Test
    void drawDivisionReturnNumberWithRemainderOfDivisionWhenTwoInputsNumbersDoNotHaveCommonFactor() {
        IntegerDivision id = new IntegerDivision(563,4);
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
        String actual = id.drawDivision();
        assertEquals(expected, actual);
    } 
    
    @Test
    void drawDivisionReturnLotsOfSameNumeralIfDividedContainsOfLotsSameNumeralAndDividedIsOneNumeralThatHaveCommonFactor() {
        IntegerDivision id = new IntegerDivision(999,3);
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
        String actual = id.drawDivision();
        assertEquals(expected, actual);
    } 
    
    @Test
    void drawDivisionReturnNumberWithRemainderOfDivisionWithBigDividedWhenTwoInputsNumbersDoNotHaveCommonFactor() {
        IntegerDivision id = new IntegerDivision(1892222,9223);
        String expected = "_1892222|9223\n"
                +         " 18446  |----\n"
                +         " -----  |205\n"
                +         "  _4762\n"
                +         "      0\n"
                +         "   ----\n"
                +         "  _47622\n"
                +         "   46115\n"
                +         "   -----\n"
                +         "    1507";
        String actual = id.drawDivision();
        assertEquals(expected, actual);
    } 
}
