package com.ajt.karatsuba;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;


import org.testng.annotations.Test;

public class TestKaratsuba {

    @Test
    public void testBaseCase() {
        assertThat(Karatsuba.multiply(4,7).getReturnValue(), is(equalTo(28L)));
        assertThat(Karatsuba.multiply(2, 14).getReturnValue(), is(equalTo(28L)));
    }

    @Test
    public void testDoubleDigits() {
        assertThat(Karatsuba.multiply(12,12).getReturnValue(), is(equalTo(144L)));
        assertThat(Karatsuba.multiply(20, 14).getReturnValue(), is(equalTo(280L)));
    }

    @Test
    public void testLargeNumbers() {
        assertThat(Karatsuba.multiply(32563,243543).getReturnValue(), is(equalTo(7930490709L)));
        assertThat(Karatsuba.multiply(16777216,274877906944L).getReturnValue(), is(equalTo(4611686018427387904L)));
    }

    @Test
    public void testNumbersWithDifferingMultitudes() {
        assertThat(Karatsuba.multiply(10,123456789).getReturnValue(), is(equalTo(1234567890L)));
        assertThat(Karatsuba.multiply(11,1223334444).getReturnValue(), is(equalTo(13456678884L)));
    }
}
