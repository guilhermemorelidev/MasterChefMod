/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.world.World
 */
package com.chef.mod.items;

import com.chef.mod.Chef;
import com.chef.mod.init.MyBlocks;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class Stamper
extends Item {
    public Stamper() {
        this.setMaxDamage(100);
        this.maxStackSize = 1;
        this.setCreativeTab(Chef.tabChef);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, int x, int y, int z, EnumFacing side, float hitX, float hitY, float hitZ) {
        Random rand = new Random();
        if (worldIn.getBlock(x, y, z) == MyBlocks.butter_churn) {
            int level = worldIn.getBlockMetadata(x, y, z);
            return level > 0;
        }
        return false;
    }
}

