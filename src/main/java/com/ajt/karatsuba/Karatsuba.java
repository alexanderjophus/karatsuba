package com.ajt.karatsuba;

import com.ajt.karatsuba.utils.LongUtils;
import org.apache.log4j.Logger;

import java.util.Collections;

import static java.lang.Long.max;

class Karatsuba {
    final private Logger LOGGER = Logger.getLogger(this.getClass());
    private final long num1;
    private final long num2;
    private Karatsuba kh;
    private Karatsuba kl;
    private Karatsuba km;
    private NumberSplit numberSplitNum1;
    private NumberSplit numberSplitNum2;
    private long highRes;
    private long lowRes;
    private long midRes;
    private long retValue;
    private long mid;

    Karatsuba(long num1, long num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    long multiply() {
        if (this.num1 < 10 || this.num2 < 10) return this.num1*this.num2;
        LOGGER.info("Num1: " + this.num1);
        LOGGER.info("Num2: " + this.num2);

        mid = max(LongUtils.getBase10Length(this.num1), LongUtils.getBase10Length(this.num2))/2;
        LOGGER.trace("Mid: " + mid);

        numberSplitNum1 = NumberSplit.getHighLow(this.num1, mid);
        LOGGER.trace("High: " + this.numberSplitNum1.getHigh() + ", Low: " + this.numberSplitNum1.getLow());

        numberSplitNum2 = NumberSplit.getHighLow(this.num2, mid);
        LOGGER.trace("High: " + this.numberSplitNum2.getHigh() + ", Low: " + this.numberSplitNum2.getLow());

        kh = new Karatsuba(this.numberSplitNum1.getHigh(), this.numberSplitNum2.getHigh());
        kl = new Karatsuba(this.numberSplitNum1.getLow(), this.numberSplitNum2.getLow());
        km = new Karatsuba(this.numberSplitNum1.getSummation(),this.numberSplitNum2.getSummation());
        highRes = kh.multiply();
        lowRes = kl.multiply();
        midRes = km.multiply() - (highRes + lowRes);
        LOGGER.info("High Result: " + highRes + "* 10 ^ " + mid*2);
        LOGGER.info("Mid Result: " + midRes + "* 10 ^ " + mid);
        LOGGER.info("Low Result: " + lowRes + "* 10 ^ 0");

        retValue = (highRes * (long) Math.pow(10, mid * 2)) + (midRes * (long) Math.pow(10, mid)) + lowRes;
        LOGGER.info("Result: " + retValue);
        return retValue;
    }

    // TODO Look into way of enforcing this dependency
    /**
     * Call multiply first
     */
    public String output() {
        return output(new StringBuilder(), 0).toString();
    }

    // TODO: Think about taking in formatter to allow for different styles of output
    // i.e. HTML formatter allowing collapsing of nested calls
    // i.e. LaTeX formatter
    private StringBuilder output(final StringBuilder sb, final int depth) {
        multiply();
        if (this.num1 < 10 || this.num2 < 10) return sb;
        sb.append(lineBuilder(depth,"Number 1: ", num1));
        sb.append(lineBuilder(depth,"Number 2: ", num2));

        sb.append(lineBuilder(depth,"Number 1 High: ", this.numberSplitNum1.getHigh()));
        sb.append(lineBuilder(depth,"Number 2 High: ", this.numberSplitNum2.getHigh()));
        kh.output(sb, depth+1);
        sb.append(lineBuilder(depth,
                "High Value [", this.numberSplitNum1.getHigh(), "*", this.numberSplitNum2.getHigh(), "*10^", this.mid*2, "]: ", this.highRes));


        sb.append(lineBuilder(depth,"Number 1 Low: ", this.numberSplitNum1.getLow()));
        sb.append(lineBuilder(depth,"Number 2 Low: ", this.numberSplitNum2.getLow()));
        kl.output(sb, depth+1);
        sb.append(lineBuilder(depth,
                "Low Value [", this.numberSplitNum1.getLow(), "*", this.numberSplitNum2.getLow(), "]: ", this.lowRes, "*10^0"));


        sb.append(lineBuilder(depth,"Number 1 Summation: (", this.numberSplitNum1.getHigh(), "+",
                this.numberSplitNum1.getLow(), ") ", this.numberSplitNum1.getSummation()));
        sb.append(lineBuilder(depth,"Number 2 Summation: (", this.numberSplitNum2.getHigh(), "+",
                this.numberSplitNum2.getLow(), ") ", this.numberSplitNum2.getSummation()));
        km.output(sb, depth+1);
        sb.append(lineBuilder(depth,
                "Mid Value [(", this.numberSplitNum1.getSummation(), "*", this.numberSplitNum2.getSummation(),
                ") - (", this.highRes, "+", this.lowRes, ")]: ", this.midRes, "*10^", this.mid));

        sb.append(lineBuilder(depth, "Returning value: ", this.retValue));
        return sb;
    }

    private String lineBuilder(final int depth, final Object... input) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.join("", Collections.nCopies(depth, ">")));
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