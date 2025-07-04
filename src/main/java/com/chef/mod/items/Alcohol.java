/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.World
 */
package com.chef.mod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class Alcohol
extends ItemFood {
    public Alcohol(int i, float f, boolean b) {
        super(i, b);
    }

    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 900, 0));
            player.addPotionEffect(new PotionEffect(Potion.confusion.id, 800, 0));
        }
    }

    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.drink;
    }
}

