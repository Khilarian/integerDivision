package com.foxminded.integerDivision;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IntegerDivisionTest {
    
    @Test
    void drawDivisionReturnIllegalArgumentExceptionWhenDividerIsZero() {
        IntegerDivision id = new IntegerDivision();
        assertThrows(IllegalArgumentException.class, () -> id.drawDivision(3, 0));
    }
    
    @Test
    void drawDivisionReturnPositiveResultAfterDivisionTwoPositiveNumbers() {
        IntegerDivision id = new IntegerDivision();
        String expected = "_2|1\n" + " 2|-\n" + " -|2\n" + " 0";
        String actual = id.drawDivision(2, 1);
        assertEquals(expected, actual);
    }
    
    @Test
    void drawDivisionReturnZeroWhenFirstNumberIsZero() {
        IntegerDivision id = new IntegerDivision();
        String expected = "_0|987987\n" + " 0|------\n" + " -|0\n" + " 0";
        String actual = id.drawDivision(0, 987987);
        assertEquals(expected, actual);
    }
    
    @Test
    void drawDivisionReturnNumberWithRemainderOfDivisionWhenTwoInputsNumbersDoNotHaveCommonFactor() {
        IntegerDivision id = new IntegerDivision();
        String expected = "_563|4\n" + " 4  |---\n" + " -  |140\n" + "_16\n" + " 16\n" + " --\n" + "  _3\n" + "   0\n"
                + "   -\n" + "   3";
        String actual = id.drawDivision(563, 4);
        assertEquals(expected, actual);
    }
    
    @Test
    void drawDivisionReturnZeroWithRemainderOfDivisionWhenDividerGreaterThanDivided() {
        IntegerDivision id = new IntegerDivision();
        String expected = "_4|128\n" + " 0|---\n" + " -|0\n" + " 4";
        String actual = id.drawDivision(4, 128);
        assertEquals(expected, actual);
    }
    
    @Test
    void drawDivisionReturnLotsOfSameNumeralIfDividedContainsOfLotsSameNumeralAndDividedIsOneNumeralThatHaveCommonFactor() {
        IntegerDivision id = new IntegerDivision();
        String expected = "_999|3\n" + " 9  |---\n" + " -  |333\n" + " _9\n" + "  9\n" + "  -\n" + "  _9\n" + "   9\n"
                + "   -\n" + "   0";
        String actual = id.drawDivision(999, 3);
        assertEquals(expected, actual);
    }
    
    @Test
    void drawDivisionReturnNumberWithRemainderOfDivisionWithBigDividedWhenTwoInputsNumbersDoNotHaveCommonFactor() {
        IntegerDivision id = new IntegerDivision();
        String expected = "_1892222|9223\n" + " 18446  |----\n" + " -----  |205\n" + "  _4762\n" + "      0\n"
                + "   ----\n" + "  _47622\n" + "   46115\n" + "   -----\n" + "    1507";
        String actual = id.drawDivision(1892222, 9223);
        assertEquals(expected, actual);
    }
}
