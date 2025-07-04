/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package com.chef.mod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EatBowl
extends ItemFood {
    public EatBowl(int i, float f, boolean b) {
        super(i, b);
        this.setContainerItem(Items.bowl);
    }

    public ItemStack onEaten(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        super.onEaten(stack, worldIn, playerIn);
        return new ItemStack(Items.bowl);
    }
}

