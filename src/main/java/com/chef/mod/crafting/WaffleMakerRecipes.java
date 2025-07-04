/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package com.chef.mod.crafting;

import com.chef.mod.init.MyItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class WaffleMakerRecipes {
    public static ItemStack getBakingResult(Item item) {
        return WaffleMakerRecipes.getOutput(item);
    }

    public static ItemStack getOutput(Item item) {
        if (item == MyItems.dough) {
            return new ItemStack(MyItems.waffle, 1);
        }
        return null;
    }

    public static boolean isItemDough(ItemStack itemstack) {
        return itemstack.getItem() == MyItems.dough;
    }
}

