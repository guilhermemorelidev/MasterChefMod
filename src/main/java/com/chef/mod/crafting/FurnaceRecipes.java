/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package com.chef.mod.crafting;

import com.chef.mod.init.MyBlocks;
import com.chef.mod.init.MyItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FurnaceRecipes {
    public static void recipes() {
        GameRegistry.addSmelting((ItemStack)new ItemStack(Items.dye, 1, 3), (ItemStack)new ItemStack(MyItems.dark_chocolate), (float)0.35f);
        GameRegistry.addSmelting((ItemStack)new ItemStack(MyItems.bread_slice), (ItemStack)new ItemStack(MyItems.toast), (float)0.35f);
        GameRegistry.addSmelting((ItemStack)new ItemStack(MyItems.tomato, 1), (ItemStack)new ItemStack(MyItems.dried_tomato, 1), (float)0.35f);
        GameRegistry.addSmelting((ItemStack)new ItemStack(Items.sugar, 1), (ItemStack)new ItemStack(MyItems.caramel, 1), (float)0.35f);
        GameRegistry.addSmelting((ItemStack)new ItemStack(MyItems.chocolate_milk, 1), (ItemStack)new ItemStack(MyItems.hot_chocolate), (float)0.35f);
        GameRegistry.addSmelting((ItemStack)new ItemStack(MyItems.milk_bowl), (ItemStack)new ItemStack(MyItems.hot_milk), (float)0.35f);
        GameRegistry.addSmelting((ItemStack)new ItemStack(MyItems.green_bell_pepper, 1), (ItemStack)new ItemStack(MyItems.baked_green_bell_pepper, 1), (float)0.35f);
        GameRegistry.addSmelting((ItemStack)new ItemStack(MyItems.yellow_bell_pepper, 1), (ItemStack)new ItemStack(MyItems.baked_yellow_bell_pepper, 1), (float)0.35f);
        GameRegistry.addSmelting((ItemStack)new ItemStack(MyItems.red_bell_pepper, 1), (ItemStack)new ItemStack(MyItems.baked_red_bell_pepper, 1), (float)0.35f);
        GameRegistry.addSmelting((ItemStack)new ItemStack(MyItems.onion_slice, 1), (ItemStack)new ItemStack(MyItems.baked_onion_slice, 1), (float)0.35f);
        GameRegistry.addSmelting((ItemStack)new ItemStack(MyItems.vegetable_soup, 1), (ItemStack)new ItemStack(MyItems.hot_vegetable_soup, 1), (float)0.35f);
        GameRegistry.addSmelting((ItemStack)new ItemStack(MyItems.carrot_soup, 1), (ItemStack)new ItemStack(MyItems.hot_carrot_soup), (float)0.35f);
        GameRegistry.addSmelting((ItemStack)new ItemStack(MyItems.tomato_soup, 1), (ItemStack)new ItemStack(MyItems.hot_tomato_soup, 1), (float)0.35f);
        GameRegistry.addSmelting((ItemStack)new ItemStack(MyItems.bell_pepper_soup, 1), (ItemStack)new ItemStack(MyItems.hot_bell_pepper_soup, 1), (float)0.35f);
        GameRegistry.addSmelting((ItemStack)new ItemStack(MyItems.onion_soup, 1), (ItemStack)new ItemStack(MyItems.hot_onion_soup, 1), (float)0.35f);
        GameRegistry.addSmelting((ItemStack)new ItemStack(MyItems.asparagus_soup, 1), (ItemStack)new ItemStack(MyItems.hot_asparagus_soup, 1), (float)0.35f);
        GameRegistry.addSmelting((ItemStack)new ItemStack(MyItems.chicken_soup, 1), (ItemStack)new ItemStack(MyItems.hot_chicken_soup, 1), (float)0.35f);
        GameRegistry.addSmelting((ItemStack)new ItemStack(MyItems.raw_asparagus, 1), (ItemStack)new ItemStack(MyItems.baked_asparagus, 1), (float)0.35f);
        GameRegistry.addSmelting((ItemStack)new ItemStack(MyItems.bowl_with_corn), (ItemStack)new ItemStack(MyItems.popcorn), (float)0.35f);
        GameRegistry.addSmelting((ItemStack)new ItemStack(MyItems.sugared_popcorn), (ItemStack)new ItemStack(MyItems.caramelized_popcorn), (float)0.35f);
        GameRegistry.addSmelting((ItemStack)new ItemStack(MyItems.corn_bread), (ItemStack)new ItemStack(MyItems.baked_corn_bread), (float)0.35f);
        GameRegistry.addSmelting((ItemStack)new ItemStack(MyItems.mutton, 1), (ItemStack)new ItemStack(MyItems.cooked_mutton, 1), (float)0.35f);
        GameRegistry.addSmelting((ItemStack)new ItemStack(MyItems.rock_salt), (ItemStack)new ItemStack(MyBlocks.rock_salt_ore), (float)0.35f);
        GameRegistry.addSmelting((ItemStack)new ItemStack(MyItems.sea_salt), (ItemStack)new ItemStack(MyBlocks.sea_salt_ore), (float)0.35f);
        GameRegistry.addSmelting((Item)Items.carrot, (ItemStack)new ItemStack(MyItems.baked_carrot, 1), (float)0.35f);
        GameRegistry.addSmelting((Item)MyItems.hamburger_bread, (ItemStack)new ItemStack(MyItems.toasted_hamburger_bread, 1), (float)0.35f);
        GameRegistry.addSmelting((Item)MyItems.tomato, (ItemStack)new ItemStack(MyItems.baked_tomato, 1), (float)0.35f);
        GameRegistry.addSmelting((Item)MyItems.apple_slice, (ItemStack)new ItemStack(MyItems.baked_apple_slice, 1), (float)0.35f);
        GameRegistry.addSmelting((Item)MyItems.bowl_with_corn, (ItemStack)new ItemStack(MyItems.popcorn, 1), (float)0.35f);
        GameRegistry.addSmelting((Item)MyItems.bowl_with_rice, (ItemStack)new ItemStack(MyItems.baked_rice, 1), (float)0.35f);
        GameRegistry.addSmelting((Item)MyItems.raw_pizza_margharita_slice, (ItemStack)new ItemStack(MyItems.pizza_margharita_slice, 1), (float)0.125f);
        GameRegistry.addSmelting((Item)MyItems.raw_pizza_bacon_slice, (ItemStack)new ItemStack(MyItems.pizza_bacon_slice, 1), (float)0.125f);
        GameRegistry.addSmelting((Item)MyItems.raw_pizza_onion_slice, (ItemStack)new ItemStack(MyItems.pizza_onion_slice, 1), (float)0.125f);
        GameRegistry.addSmelting((Item)MyItems.raw_pizza_mushroom_slice, (ItemStack)new ItemStack(MyItems.pizza_mushroom_slice, 1), (float)0.125f);
        GameRegistry.addSmelting((Block)MyBlocks.raw_pizza_margharita, (ItemStack)new ItemStack(MyBlocks.pizza_margharita, 1), (float)0.5f);
        GameRegistry.addSmelting((Block)MyBlocks.raw_pizza_bacon, (ItemStack)new ItemStack(MyBlocks.pizza_bacon, 1), (float)0.5f);
        GameRegistry.addSmelting((Block)MyBlocks.raw_pizza_onion, (ItemStack)new ItemStack(MyBlocks.pizza_onion, 1), (float)0.5f);
        GameRegistry.addSmelting((Block)MyBlocks.raw_pizza_mushroom, (ItemStack)new ItemStack(MyBlocks.pizza_mushroom, 1), (float)0.5f);
    }
}

