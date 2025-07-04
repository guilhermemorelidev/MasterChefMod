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

public class DehydratorRecipes {
    public static ItemStack getDryingResult(Item item) {
        return DehydratorRecipes.getOutput(item);
    }

    public static ItemStack getOutput(Item item) {
        if (item == MyItems.grape) {
            return new ItemStack(MyItems.raisin, 1);
        }
        if (item == MyItems.apple_slice) {
            return new ItemStack(MyItems.dried_apple_slice, 1);
        }
        if (item == MyItems.blueberry) {
            return new ItemStack(MyItems.dried_blueberry, 1);
        }
        if (item == MyItems.strawberry || item == MyItems.frost_strawberry || item == MyItems.perfect_strawberry) {
            return new ItemStack(MyItems.dried_strawberry, 1);
        }
        if (item == MyItems.tomato) {
            return new ItemStack(MyItems.dried_tomato, 1);
        }
        if (item == MyItems.bread_slice) {
            return new ItemStack(MyItems.dried_bread_slice, 1);
        }
        if (item == MyItems.milk_bowl) {
            return new ItemStack(MyItems.cheese, 1);
        }
        return null;
    }

    public static boolean isItemIngredient(ItemStack itemstack) {
        return DehydratorRecipes.getDryingResult(itemstack.getItem()) != null;
    }
}

