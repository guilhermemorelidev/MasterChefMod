/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.World
 */
package com.chef.mod.items;

import com.chef.mod.init.MyItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class Speed
extends ItemFood {
    public Speed(int i, float f, boolean b) {
        super(i, b);
    }

    protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            Item item = itemstack.getItem();
            if (item == MyItems.grape_sugar) {
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 100, 0));
            } else if (item == MyItems.strong_grape_sugar) {
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 110, 1));
            } else if (item == MyItems.chocolate_bar) {
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 150, 1));
            }
        }
    }
}

