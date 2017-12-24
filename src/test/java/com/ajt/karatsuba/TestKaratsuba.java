package com.ajt.karatsuba;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;


import org.testng.annotations.Test;

public class TestKaratsuba {

    @Test
    public void testBaseCase() {
        assertThat(new Karatsuba(4,7).multiply(), is(equalTo(28L)));
        assertThat(new Karatsuba(2,14).multiply(), is(equalTo(28L)));
    }

    @Test
    public void testDoubleDigits() {
        assertThat(new Karatsuba(12,12).multiply(), is(equalTo(144L)));
        assertThat(new Karatsuba(20,14).multiply(), is(equalTo(280L)));
    }

    @Test
    public void testLargeNumbers() {
        assertThat(new Karatsuba(32563,243543).multiply(), is(equalTo(7930490709L)));
        assertThat(new Karatsuba(16777216,274877906944L).multiply(), is(equalTo(4611686018427387904L)));
    }

    @Test
    public void testNumbersWithDifferingMultitudes() {
        assertThat(new Karatsuba(10,123456789).multiply(), is(equalTo(1234567890L)));
        assertThat(new Karatsuba(11,1223334444).multiply(), is(equalTo(13456678884L)));
    }
}
