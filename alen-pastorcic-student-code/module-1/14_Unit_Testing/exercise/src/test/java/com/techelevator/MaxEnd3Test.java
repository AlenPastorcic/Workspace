package com.techelevator;

import org.junit.Test;

import java.util.Arrays;

public class MaxEnd3Test {

    @Test
    public void makeArray_largest_number_test(){
        MaxEnd3 highest = new MaxEnd3();
        assert Arrays.equals(highest.makeArray(new int[] {50, 65, 500}), new int[] {500, 500, 500});
        assert Arrays.equals(highest.makeArray(new int[] {400, 75, 234}), new int[] {400, 400, 400});
    }
}
