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

public class IceCreamMakerRecipes {
    public static ItemStack getIceCreamResult(Item item, Item item2) {
        return IceCreamMakerRecipes.getOutput(item, item2);
    }

    public static ItemStack getOutput(Item item, Item item2) {
        if (item == Items.sugar) {
            if (item2 == Items.apple) {
                return new ItemStack(MyItems.apple_ice_cream_ball, 1);
            }
            if (item2 == Items.melon) {
                return new ItemStack(MyItems.melon_ice_cream_ball, 1);
            }
            if (item2 == MyItems.strawberry || item2 == MyItems.perfect_strawberry || item2 == MyItems.frost_strawberry) {
                return new ItemStack(MyItems.strawberry_ice_cream_ball, 1);
            }
            if (item2 == MyItems.blueberry) {
                return new ItemStack(MyItems.blueberry_ice_cream_ball, 1);
            }
            if (item2 == MyItems.caramel) {
                return new ItemStack(MyItems.caramel_ice_cream_ball, 1);
            }
            if (item2 == MyItems.chocolate) {
                return new ItemStack(MyItems.chocolate_ice_cream_ball, 1);
            }
            if (item2 == MyItems.mango_slice) {
                return new ItemStack(MyItems.mango_ice_cream_ball, 1);
            }
        } else if (item2 == Items.sugar) {
            if (item == Items.apple) {
                return new ItemStack(MyItems.apple_ice_cream_ball, 1);
            }
            if (item == Items.melon) {
                return new ItemStack(MyItems.melon_ice_cream_ball, 1);
            }
            if (item == MyItems.strawberry || item == MyItems.perfect_strawberry || item == MyItems.frost_strawberry) {
                return new ItemStack(MyItems.strawberry_ice_cream_ball, 1);
            }
            if (item == MyItems.blueberry) {
                return new ItemStack(MyItems.blueberry_ice_cream_ball, 1);
            }
            if (item == MyItems.caramel) {
                return new ItemStack(MyItems.caramel_ice_cream_ball, 1);
            }
            if (item == MyItems.chocolate) {
                return new ItemStack(MyItems.chocolate_ice_cream_ball, 1);
            }
            if (item == MyItems.mango_slice) {
                return new ItemStack(MyItems.mango_ice_cream_ball, 1);
            }
        }
        return null;
    }

    public static boolean isItemIngredient(ItemStack itemstack) {
        if (IceCreamMakerRecipes.getIceCreamResult(Items.sugar, itemstack.getItem()) != null) {
            return true;
        }
        if (IceCreamMakerRecipes.getIceCreamResult(itemstack.getItem(), Items.sugar) != null) {
            return true;
        }
        return itemstack.getItem() == Items.sugar;
    }
}

