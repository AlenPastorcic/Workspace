package com.techelevator;

import java.math.BigDecimal;

public class BD {
    public static void main(String[] args) {

        BigDecimal pi = new BigDecimal("3.1416");
        System.out.println(pi);
        BigDecimal point3 = new BigDecimal(.1 + .2);
        System.out.println(point3);

        // constants that can be used to initialize a BigDecimal
        BigDecimal subtotal = BigDecimal.ZERO;
        BigDecimal ten = BigDecimal.TEN;
        BigDecimal one = BigDecimal.ONE;

        // doing math
        // BigDecimal is immutable
//        System.out.println(subtotal + 1);

        System.out.println(subtotal.add(BigDecimal.ONE));
        System.out.println(subtotal.subtract(BigDecimal.ONE));
        System.out.println(subtotal);
        // x = x + 10
        subtotal = subtotal.add(BigDecimal.TEN);
        System.out.println(subtotal);

        // comparing
        // subtotal = 1;
        subtotal = BigDecimal.ONE;
        System.out.println(subtotal.equals(BigDecimal.ONE));
        System.out.println(subtotal.compareTo(BigDecimal.ONE)); // ==> 0 means equal
        System.out.println(subtotal.compareTo(BigDecimal.TEN)); // ==> -1 because 1 < 10
        System.out.println(subtotal.compareTo(BigDecimal.ZERO)); // ==> 1 because 1 > 0

        BigDecimal balance = BigDecimal.TEN;
        if (subtotal.compareTo(balance) < 0) {
            // can but the product because
            // the subtotal is more than the balance
        }

    }
}
