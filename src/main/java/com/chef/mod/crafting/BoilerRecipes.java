/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package com.chef.mod.crafting;

import com.chef.mod.init.MyItems;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BoilerRecipes {
    public static ItemStack getBoilingResult(Item item) {
        return BoilerRecipes.getOutput(item);
    }

    public static ItemStack getOutput(Item item) {
        if (item == Items.egg) {
            return new ItemStack(MyItems.boiled_egg);
        }
        if (item == MyItems.raw_squid) {
            return new ItemStack(MyItems.boiled_squid);
        }
        if (item == Items.carrot) {
            return new ItemStack(MyItems.boiled_carrot);
        }
        if (item == Items.egg) {
            return new ItemStack(MyItems.boiled_egg);
        }
        if (item == MyItems.bowl_with_rice) {
            return new ItemStack(MyItems.boiled_rice);
        }
        if (item == MyItems.raw_corn) {
            return new ItemStack(MyItems.boiled_corn);
        }
        return null;
    }

    public static boolean isItemIngredient(ItemStack itemstack) {
        Item item = itemstack.getItem();
        return BoilerRecipes.getBoilingResult(item) != null;
    }
}

