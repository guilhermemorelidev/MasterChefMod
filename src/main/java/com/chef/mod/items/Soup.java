/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package com.chef.mod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Soup
extends ItemFood {
    public Soup(int i, float f, boolean b) {
        super(i, b);
    }

    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
        return new ItemStack(Items.bowl);
    }

    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.drink;
    }
}

