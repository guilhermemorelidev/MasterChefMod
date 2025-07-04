/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.util.EnumFacing
 */
package com.chef.mod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.EnumFacing;

@SideOnly(value=Side.CLIENT)
public class SwitchEnumFacing {
    public static final int[] FACING_LOOKUP = new int[EnumFacing.values().length];

    static {
        try {
            SwitchEnumFacing.FACING_LOOKUP[EnumFacing.WEST.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            SwitchEnumFacing.FACING_LOOKUP[EnumFacing.EAST.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            SwitchEnumFacing.FACING_LOOKUP[EnumFacing.NORTH.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            SwitchEnumFacing.FACING_LOOKUP[EnumFacing.SOUTH.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

