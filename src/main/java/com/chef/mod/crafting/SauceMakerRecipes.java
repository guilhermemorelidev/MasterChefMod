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

public class SauceMakerRecipes {
    public static ItemStack getJuicingResult(Item item, Item item2) {
        return SauceMakerRecipes.getOutput(item, item2);
    }

    public static ItemStack getOutput(Item item, Item item2) {
        if (item2 == Items.bowl) {
            if (item == MyItems.chocolate) {
                return new ItemStack(MyItems.chocolate_sauce, 1);
            }
            if (item == MyItems.strawberry || item == MyItems.perfect_strawberry || item == MyItems.frost_strawberry) {
                return new ItemStack(MyItems.strawberry_sauce, 1);
            }
            if (item == MyItems.blueberry) {
                return new ItemStack(MyItems.blueberry_sauce, 1);
            }
            if (item == MyItems.caramel) {
                return new ItemStack(MyItems.caramel_sauce, 1);
            }
            if (item == Items.melon) {
                return new ItemStack(MyItems.melon_sauce, 1);
            }
            if (item == MyItems.mango) {
                return new ItemStack(MyItems.mango_sauce, 1);
            }
            if (item == MyItems.tomato) {
                return new ItemStack(MyItems.tomato_sauce, 1);
            }
            if (item == Items.apple) {
                return new ItemStack(MyItems.apple_sauce, 1);
            }
        }
        return null;
    }

    public static boolean isItemIngredient(ItemStack itemstack) {
        return SauceMakerRecipes.getJuicingResult(itemstack.getItem(), Items.bowl) != null;
    }

    public static boolean isItemBowl(ItemStack itemstack) {
        return itemstack.getItem() == Items.bowl;
    }
}

