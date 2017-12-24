package com.ajt.karatsuba.utils;

public class LongUtils {
    // long.MAX is 9,223,372,036,854,775,807 19 digits
    public static int getBase10Length(final long number) {
        // idea taken from http://www.baeldung.com/java-number-of-digits-in-int
        if (number < 100000000) {
            if (number < 10000) {
                if (number < 100) {
                    if (number < 10) {
                        return 1;
                    } else {
                        return 2;
                    }
                } else {
                    if (number < 1000) {
                        return 3;
                    } else {
                        return 4;
                    }
                }
            } else {
                if (number < 1000000) {
                    if (number < 100000) {
                        return 5;
                    } else {
                        return 6;
                    }
                } else {
                    if (number < 10000000) {
                        return 7;
                    } else {
                        return 8;
                    }
                }
            }
        } else {
            if (number < 10000000000000000L) {
                if (number< 1000000000000L) {
                    if (number < 10000000000L) {
                        if (number < 1000000000) {
                            return 9;
                        } else {
                            return 10;
                        }
                    } else {
                        if (number < 100000000000L) {
                            return 11;
                        } else {
                            return 12;
                        }
                    }
                } else {
                    if (number < 100000000000000L) {
                        if (number < 10000000000000L) {
                            return 13;
                        } else {
                            return 14;
                        }
                    } else {
                        if (number < 1000000000000000L) {
                            return 15;
                        } else {
                            return 16;
                        }
                    }
                }
            } else {
                if (number < 1000000000000000000L) {
                    if (number < 100000000000000000L) {
                        return 17;
                    } else {
                        return 18;
                    }
                } else {
                    return 19;
                }
            }
        }
    }
}
