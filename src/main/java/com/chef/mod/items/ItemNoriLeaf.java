/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package com.chef.mod.items;

import com.chef.mod.init.MyBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemNoriLeaf
extends ItemFood {
    public ItemNoriLeaf(int healAmount, float saturation) {
        super(healAmount, saturation, false);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (side != 1) {
            return false;
        }
        if (!playerIn.canPlayerEdit(x, y, z, side, stack)) {
            return false;
        }
        if (worldIn.getBlock(x, y, z) == Blocks.gravel || worldIn.getBlock(x, y, z) == Blocks.sand || worldIn.getBlock(x, y, z) == Blocks.dirt && worldIn.getBlock(x, y + 1, z) == Blocks.water) {
            worldIn.setBlock(x, y + 1, z, MyBlocks.nori);
            --stack.stackSize;
            return true;
        }
        return false;
    }
}

