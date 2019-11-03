package com.foxminded.integerDivision;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IntegerDivisionTest {
    
    IntegerDivision id = new IntegerDivision();
    
    @Test
    void test() {
        assertThrows(ArithmeticException.class, () -> id.drawDivision(3,0));
    }    
}
