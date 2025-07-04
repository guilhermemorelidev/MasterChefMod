/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package com.chef.mod.crafting;

import com.chef.mod.init.MyBlocks;
import com.chef.mod.init.MyItems;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CookingFurnaceRecipes {
    public static ItemStack getSingleItemCookingResult(Item item) {
        return CookingFurnaceRecipes.getSingleItemOutput(item);
    }

    public static ItemStack getDoubleItemCookingResult(Item item, Item item2) {
        return CookingFurnaceRecipes.getDoubleOutputItem(item, item2);
    }

    public static ItemStack getSingleItemOutput(Item item) {
        Item cocoaBeans = new ItemStack(Items.dye, 1, 3).getItem();
        Object fishtype = null;
        if (item == Items.porkchop) {
            return new ItemStack(Items.cooked_porkchop, 1);
        }
        if (item == Items.beef) {
            return new ItemStack(Items.cooked_beef, 1);
        }
        if (item == Items.chicken) {
            return new ItemStack(Items.cooked_chicken, 1);
        }
        if (item == Items.potato) {
            return new ItemStack(Items.baked_potato, 1);
        }
        if (item == Items.fish) {
            return new ItemStack(Items.cooked_fished);
        }
        if (item == Items.egg) {
            return new ItemStack(MyItems.fried_egg, 1);
        }
        if (item == MyItems.bread_slice) {
            return new ItemStack(MyItems.toast, 1);
        }
        if (item == MyItems.tomato) {
            return new ItemStack(MyItems.baked_tomato, 1);
        }
        if (item == Items.sugar) {
            return new ItemStack(MyItems.caramel, 1);
        }
        if (item == MyItems.chocolate_milk) {
            return new ItemStack(MyItems.hot_chocolate, 1);
        }
        if (item == cocoaBeans) {
            return new ItemStack(MyItems.dark_chocolate, 1);
        }
        if (item == MyItems.milk_bowl) {
            return new ItemStack(MyItems.hot_milk, 1);
        }
        if (item == MyItems.green_bell_pepper) {
            return new ItemStack(MyItems.baked_green_bell_pepper, 1);
        }
        if (item == MyItems.yellow_bell_pepper) {
            return new ItemStack(MyItems.baked_yellow_bell_pepper, 1);
        }
        if (item == MyItems.red_bell_pepper) {
            return new ItemStack(MyItems.baked_red_bell_pepper, 1);
        }
        if (item == MyItems.onion_slice) {
            return new ItemStack(MyItems.baked_onion_slice, 1);
        }
        if (item == MyItems.vegetable_soup) {
            return new ItemStack(MyItems.hot_vegetable_soup, 1);
        }
        if (item == MyItems.carrot_soup) {
            return new ItemStack(MyItems.hot_carrot_soup, 1);
        }
        if (item == MyItems.bell_pepper_soup) {
            return new ItemStack(MyItems.hot_bell_pepper_soup, 1);
        }
        if (item == MyItems.onion_soup) {
            return new ItemStack(MyItems.hot_onion_soup, 1);
        }
        if (item == MyItems.asparagus_soup) {
            return new ItemStack(MyItems.hot_asparagus_soup, 1);
        }
        if (item == MyItems.chicken_soup) {
            return new ItemStack(MyItems.hot_chicken_soup, 1);
        }
        if (item == MyItems.mutton) {
            return new ItemStack(MyItems.cooked_mutton, 1);
        }
        if (item == MyItems.raw_asparagus) {
            return new ItemStack(MyItems.baked_asparagus, 1);
        }
        if (item == MyItems.bowl_with_corn) {
            return new ItemStack(MyItems.popcorn, 1);
        }
        if (item == MyItems.corn_bread) {
            return new ItemStack(MyItems.baked_corn_bread, 1);
        }
        if (item == MyItems.apple_slice) {
            return new ItemStack(MyItems.baked_apple_slice, 1);
        }
        if (item == MyItems.bowl_with_rice) {
            return new ItemStack(MyItems.baked_rice, 1);
        }
        if (item == Items.carrot) {
            return new ItemStack(MyItems.baked_carrot, 1);
        }
        if (item == MyItems.hamburger_bread) {
            return new ItemStack(MyItems.toasted_hamburger_bread, 1);
        }
        if (item == MyItems.raw_pizza_margharita_slice) {
            return new ItemStack(MyItems.pizza_margharita_slice, 1);
        }
        if (item == MyItems.raw_pizza_onion_slice) {
            return new ItemStack(MyItems.pizza_onion_slice, 1);
        }
        if (item == MyItems.raw_pizza_bacon_slice) {
            return new ItemStack(MyItems.pizza_bacon_slice, 1);
        }
        if (item == MyItems.raw_pizza_mushroom_slice) {
            return new ItemStack(MyItems.pizza_mushroom_slice, 1);
        }
        if (item == MyItems.tomato) {
            return new ItemStack(MyItems.baked_tomato, 1);
        }
        if (item == MyItems.apple_slice) {
            return new ItemStack(MyItems.baked_apple_slice, 1);
        }
        if (item == Item.getItemFromBlock((Block)MyBlocks.raw_pizza_margharita)) {
            return new ItemStack(MyBlocks.pizza_margharita, 1);
        }
        if (item == Item.getItemFromBlock((Block)MyBlocks.raw_pizza_onion)) {
            return new ItemStack(MyBlocks.pizza_onion, 1);
        }
        if (item == Item.getItemFromBlock((Block)MyBlocks.raw_pizza_bacon)) {
            return new ItemStack(MyBlocks.pizza_bacon, 1);
        }
        if (item == Item.getItemFromBlock((Block)MyBlocks.raw_pizza_mushroom)) {
            return new ItemStack(MyBlocks.pizza_mushroom, 1);
        }
        return null;
    }

    public static ItemStack getDoubleOutputItem(Item item, Item item2) {
        Object fishtype = null;
        if (item == MyItems.bread_crumbs) {
            if (item2 == MyItems.apple_ice_cream_ball) {
                return new ItemStack(MyItems.fried_ice_cream_ball, 1);
            }
            if (item2 == MyItems.melon_ice_cream_ball) {
                return new ItemStack(MyItems.fried_ice_cream_ball, 1);
            }
            if (item2 == MyItems.strawberry_ice_cream_ball) {
                return new ItemStack(MyItems.fried_ice_cream_ball, 1);
            }
            if (item2 == MyItems.blueberry_ice_cream_ball) {
                return new ItemStack(MyItems.fried_ice_cream_ball, 1);
            }
            if (item2 == MyItems.chocolate_ice_cream_ball) {
                return new ItemStack(MyItems.fried_ice_cream_ball, 1);
            }
            if (item2 == MyItems.caramel_ice_cream_ball) {
                return new ItemStack(MyItems.fried_ice_cream_ball, 1);
            }
            if (item2 == MyItems.mango_ice_cream_ball) {
                return new ItemStack(MyItems.fried_ice_cream_ball, 1);
            }
            if (item2 == Items.potato) {
                return new ItemStack(MyItems.croquette, 1);
            }
            if (item2 == Items.fish) {
                return new ItemStack(MyItems.fried_fish);
            }
            if (item2 == Items.chicken) {
                return new ItemStack(MyItems.fried_chicken, 1);
            }
            if (item2 == MyItems.strawberry) {
                return new ItemStack(MyItems.fried_strawberry, 1);
            }
            if (item2 == MyItems.frost_strawberry) {
                return new ItemStack(MyItems.fried_strawberry, 1);
            }
            if (item2 == MyItems.perfect_strawberry) {
                return new ItemStack(MyItems.fried_strawberry, 1);
            }
            if (item2 == Items.carrot) {
                return new ItemStack(MyItems.fried_carrot, 1);
            }
            if (item2 == MyItems.green_bell_pepper || item2 == MyItems.yellow_bell_pepper || item2 == MyItems.red_bell_pepper) {
                return new ItemStack(MyItems.fried_bell_pepper, 1);
            }
            if (item2 == MyItems.apple_slice) {
                return new ItemStack(MyItems.fried_apple_slice, 1);
            }
            if (item2 == MyItems.raw_squid) {
                return new ItemStack(MyItems.fried_calamari, 1);
            }
        }
        return null;
    }

    public static boolean isItemIngredient(ItemStack itemstack) {
        if (CookingFurnaceRecipes.getSingleItemCookingResult(itemstack.getItem()) != null) {
            return true;
        }
        return CookingFurnaceRecipes.getDoubleItemCookingResult(MyItems.bread_crumbs, itemstack.getItem()) != null;
    }

    public static boolean isItemBreadCrumbs(ItemStack itemstack) {
        return itemstack.getItem() == MyItems.bread_crumbs;
    }

    public static boolean isItemBottle(ItemStack itemstack) {
        return itemstack.getItem() == Items.glass_bottle;
    }

    public static boolean doesRequireButter(Item item) {
        if (item == MyItems.hamburger_bread) {
            return false;
        }
        if (item == MyItems.raw_pizza_margharita_slice) {
            return false;
        }
        if (item == MyItems.raw_pizza_onion_slice) {
            return false;
        }
        if (item == MyItems.raw_pizza_bacon_slice) {
            return false;
        }
        if (item == MyItems.raw_pizza_mushroom_slice) {
            return false;
        }
        if (item == MyItems.bread_slice) {
            return false;
        }
        if (item == MyItems.corn_bread) {
            return false;
        }
        if (item == Item.getItemFromBlock((Block)MyBlocks.raw_pizza_margharita)) {
            return false;
        }
        if (item == Item.getItemFromBlock((Block)MyBlocks.raw_pizza_onion)) {
            return false;
        }
        if (item == Item.getItemFromBlock((Block)MyBlocks.raw_pizza_bacon)) {
            return false;
        }
        return item != Item.getItemFromBlock((Block)MyBlocks.raw_pizza_mushroom);
    }
}

