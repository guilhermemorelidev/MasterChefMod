/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package com.chef.mod.items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCorn
extends ItemFood {
    private Block block;

    public ItemCorn(int healAmount, float saturation, Block block) {
        super(healAmount, saturation, false);
        this.block = block;
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        int i1;
        Block block = worldIn.getBlock(x, y, z);
        if (block == Blocks.snow_layer && (worldIn.getBlockMetadata(x, y, z) & 7) < 1) {
            side = 1;
        } else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush) {
            if (side == 0) {
                --y;
            }
            if (side == 1) {
                ++y;
            }
            if (side == 2) {
                --z;
            }
            if (side == 3) {
                ++z;
            }
            if (side == 4) {
                --x;
            }
            if (side == 5) {
                ++x;
            }
        }
        if (!playerIn.canPlayerEdit(x, y, z, side, stack)) {
            return false;
        }
        if (stack.stackSize == 0) {
            return false;
        }
        if (worldIn.canPlaceEntityOnSide(this.block, x, y, z, false, side, (Entity)null, stack) && worldIn.setBlock(x, y, z, this.block, i1 = this.block.onBlockPlaced(worldIn, x, y, z, side, hitX, hitY, hitZ, 0), 3)) {
            if (worldIn.getBlock(x, y, z) == this.block) {
                this.block.onBlockPlacedBy(worldIn, x, y, z, (EntityLivingBase)playerIn, stack);
                this.block.onPostBlockPlaced(worldIn, x, y, z, i1);
            }
            worldIn.playSoundEffect((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), this.block.stepSound.func_150496_b(), (this.block.stepSound.getVolume() + 1.0f) / 2.0f, this.block.stepSound.getPitch() * 0.8f);
            --stack.stackSize;
        }
        return true;
    }
}

