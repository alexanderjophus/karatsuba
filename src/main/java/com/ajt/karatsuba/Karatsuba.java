package com.ajt.karatsuba;

import com.ajt.karatsuba.utils.LongUtils;

import java.util.Collections;

import static java.lang.Long.max;

class Karatsuba {
    private long returnValue;
    private StringBuilder sb;

    private Karatsuba(final long returnValue, final StringBuilder sb) {
        this.returnValue = returnValue;
        this.sb = sb;
    }

    // FIXME: parameter should include the output format, string builder, depth
    // TODO: Look at logging static methods
    private static Karatsuba multiply(final long num1, final long num2, final StringBuilder sb, final int depth) {
        if (num1 < 10 || num2 < 10) return new Karatsuba(num1*num2, sb);

        sb.append(lineBuilder(depth,"Number 1: ", num1));
        sb.append(lineBuilder(depth,"Number 2: ", num2));

        long midPower = (long) Math.ceil((double)max(LongUtils.getBase10Length(num1), LongUtils.getBase10Length(num2))/2);
        NumberSplit numberSplitNum1 = NumberSplit.getHighLow(num1, midPower);
        NumberSplit numberSplitNum2 = NumberSplit.getHighLow(num2, midPower);

        // TODO: Extract these into a common method passing in the high or low values - current violation of DRY
        long highRes = getHighRes(numberSplitNum1, numberSplitNum2, sb, depth);
        long lowRes = getLowRes(numberSplitNum1, numberSplitNum2, sb, depth);
        long midRes = getMidRes(numberSplitNum1, numberSplitNum2, sb, depth, highRes, lowRes);

        long retValue = (highRes * (long) Math.pow(10, midPower * 2)) + (midRes * (long) Math.pow(10, midPower)) + lowRes;
        sb.append(lineBuilder(depth, "Returning value: (", highRes, "*10^", midPower*2, ") + (", midRes, "*10^", midPower, ") + ", lowRes, " = ", retValue));
        return new Karatsuba(retValue, sb);
    }

    private static long getHighRes(NumberSplit numberSplitNum1, NumberSplit numberSplitNum2, StringBuilder sb, int depth) {
        sb.append(lineBuilder(depth,"Number 1 High: ", numberSplitNum1.getHigh()));
        sb.append(lineBuilder(depth,"Number 2 High: ", numberSplitNum2.getHigh()));
        long highRes = multiply(numberSplitNum1.getHigh(), numberSplitNum2.getHigh(), sb, depth+1).getReturnValue();
        sb.append(lineBuilder(depth,
                "High Value [", numberSplitNum1.getHigh(), "*", numberSplitNum2.getHigh(), "]: ", highRes));
        return highRes;
    }

    private static long getLowRes(NumberSplit numberSplitNum1, NumberSplit numberSplitNum2, StringBuilder sb, int depth) {
        sb.append(lineBuilder(depth,"Number 1 Low: ", numberSplitNum1.getLow()));
        sb.append(lineBuilder(depth,"Number 2 Low: ", numberSplitNum2.getLow()));
        long lowRes = multiply(numberSplitNum1.getLow(), numberSplitNum2.getLow(), sb, depth+1).getReturnValue();
        sb.append(lineBuilder(depth,
                "Low Value [", numberSplitNum1.getLow(), "*", numberSplitNum2.getLow(), "]: ", lowRes));
        return lowRes;
    }

    private static long getMidRes(NumberSplit numberSplitNum1, NumberSplit numberSplitNum2, StringBuilder sb, int depth, long highRes, long lowRes) {
        sb.append(lineBuilder(depth,"Number 1 Summation: (", numberSplitNum1.getHigh(), "+",
                numberSplitNum1.getLow(), ") ", numberSplitNum1.getSummation()));
        sb.append(lineBuilder(depth,"Number 2 Summation: (", numberSplitNum2.getHigh(), "+",
                numberSplitNum2.getLow(), ") ", numberSplitNum2.getSummation()));
        long midRes = multiply(numberSplitNum1.getSummation(),numberSplitNum2.getSummation(),sb, depth+1).getReturnValue() - (highRes + lowRes);
        sb.append(lineBuilder(depth,
                "Mid Value [(", numberSplitNum1.getSummation(), "*", numberSplitNum2.getSummation(),
                ") - (", highRes, "+", lowRes, ")]: ", midRes));
        return midRes;
    }

    static Karatsuba multiply(final long num1, final long num2) {
        return multiply(num1, num2, new StringBuilder(), 0);
    }

    long getReturnValue() {
        return returnValue;
    }

    StringBuilder getStringBuilder() {
        return sb;
    }


    // TODO: Look into polymorphism to allow multiple output formats
    private static String lineBuilder(final int depth, final Object... input) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.join("", Collections.nCopies(depth, "\t")));
        for (Object i: input) {
            sb.append(i.toString());
        }
        return sb.append("\n").toString();
    }

    private static class NumberSplit {
        private final long high;
        private final long low;

        NumberSplit(long high, long low) {
            this.high = high;
            this.low = low;
        }

        // method makes more sense in language supporting tuples (Python / Go)
        // then no need to create encapsulating object
        private static NumberSplit getHighLow(final long num, final long split) {
            final long high = num / (long)Math.pow(10, split);
            final long low = num % (long)Math.pow(10, split);
            return new NumberSplit(high, low);
        }

        private long getSummation() {
            return high+low;
        }

        private long getHigh() {
            return high;
        }

        private long getLow() {
            return low;
        }
    }
}