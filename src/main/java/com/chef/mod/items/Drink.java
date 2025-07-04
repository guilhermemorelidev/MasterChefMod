/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 */
package com.chef.mod.items;

import com.chef.mod.Chef;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class Drink
extends ItemFood {
    public Drink(int i, float f, boolean b) {
        super(i, b);
        this.setCreativeTab(Chef.tabChef);
    }

    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.drink;
    }
}

