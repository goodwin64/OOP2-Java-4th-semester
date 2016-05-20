package ua.kpi.fict.oop2.tests.variant03;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static ua.kpi.fict.oop2.classes.variant03.Lab1_var03.*;

/**
 * Created by Max Donchenko (https://github.com/goodwin64) on 17.04.2016.
 */
public class Lab1_var03Test {
    final double EPSILON = 0.001;
    final char op1 = '-';
    final char op2 = '+';
    final short DENOMINATOR_CONSTANT = 2;

    @Test (timeout = 50L)
    public void testMakeOperationWithoutExceptions() {
        short a = 5;
        short b = 2;

        assertEquals(7.0, makeOperation(a, b, '+'), EPSILON);       // 5 + 2 = 7.0
        assertEquals(10.0, makeOperation(a, b, '*'), EPSILON);      // 5 * 2 = 10.0
        assertEquals(3.0, makeOperation(a, b, '-'), EPSILON);       // 5 - 2 = 3.0
        assertEquals(2.0, makeOperation(a, b, '/'), EPSILON);       // 5 / 2 = 2.0 (with remainder = 1)
    }
    @Test(expected = IllegalArgumentException.class)
    public void testMakeOperationWithExceptions() {
        short a = 5;
        short b = 2;

        makeOperation(a, b, 'c');
        makeOperation(a, b, ';');
    }

    @Test
    public void testDetermineOp1WithoutExceptions() {
        assertEquals('+', determineOp1(0));
        assertEquals('-', determineOp1(1));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testDetermineOp1WithExceptions() {
        determineOp1(2);
        determineOp1(-999);
    }

    @Test
    public void calculateSumWithoutExceptions() {
        assertEquals(
                18.166666666666664,
                calculateSum((short) 4, (short) 5, (short) 6, (short) 7, op1, op2, DENOMINATOR_CONSTANT),
                EPSILON
        );
        assertEquals(
                88.29761904761904,
                calculateSum((short) 9, (short) 11, (short) 4, (short) 15, op1, op2, DENOMINATOR_CONSTANT),
                EPSILON
        );
        assertEquals(
                -5.0,
                calculateSum((short) 1, (short) 1, (short) 4, (short) 4, op1, op2, DENOMINATOR_CONSTANT),
                EPSILON
        );
    }
    @Test(expected = ArithmeticException.class)
    public void calculateSumWithExceptions() {
        calculateSum((short) 1, (short) 2, (short) 4, (short) 4, op1, op2, DENOMINATOR_CONSTANT);
    }
}
