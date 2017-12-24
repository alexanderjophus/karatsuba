package com.ajt.karatsuba.utils;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class TestLongUtils {

    @Test
    public void testLargeNumbers() {
        assertThat(LongUtils.getBase10Length(1), is(equalTo(1)));
        assertThat(LongUtils.getBase10Length(12), is(equalTo(2)));
        assertThat(LongUtils.getBase10Length(123), is(equalTo(3)));
        assertThat(LongUtils.getBase10Length(1234), is(equalTo(4)));
        assertThat(LongUtils.getBase10Length(12345), is(equalTo(5)));
        assertThat(LongUtils.getBase10Length(123456), is(equalTo(6)));
        assertThat(LongUtils.getBase10Length(1234567), is(equalTo(7)));
        assertThat(LongUtils.getBase10Length(12345678), is(equalTo(8)));
        assertThat(LongUtils.getBase10Length(123456789), is(equalTo(9)));
        assertThat(LongUtils.getBase10Length(1234567890), is(equalTo(10)));
        assertThat(LongUtils.getBase10Length(12345678901L), is(equalTo(11)));
        assertThat(LongUtils.getBase10Length(123456789012L), is(equalTo(12)));
        assertThat(LongUtils.getBase10Length(1234567890123L), is(equalTo(13)));
        assertThat(LongUtils.getBase10Length(12345678901234L), is(equalTo(14)));
        assertThat(LongUtils.getBase10Length(123456789012345L), is(equalTo(15)));
        assertThat(LongUtils.getBase10Length(1234567890123456L), is(equalTo(16)));
        assertThat(LongUtils.getBase10Length(12345678901234567L), is(equalTo(17)));
        assertThat(LongUtils.getBase10Length(123456789012345678L), is(equalTo(18)));
        assertThat(LongUtils.getBase10Length(1234567890123456789L), is(equalTo(19)));
        assertThat(LongUtils.getBase10Length(Long.MAX_VALUE), is(equalTo(19)));
    }
}
