package com.ajt.karatsuba;

public class Application {
    public static void main(String[] args) {
        final Karatsuba karatsuba = Karatsuba.multiply(128, 256);
        System.out.println("Return value is: " + karatsuba.getReturnValue());
        System.out.println(karatsuba.getStringBuilder().toString());
    }
}
