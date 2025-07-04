/*
 * Decompiled with CFR 0.152.
 */
package com.chef.mod;

public class UnixTime {
    public static long MilliTimeNow() {
        return System.currentTimeMillis();
    }

    public static int TimeNow() {
        return (int)(System.currentTimeMillis() / 1000L);
    }
}

