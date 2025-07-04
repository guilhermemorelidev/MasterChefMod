/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.chef.mod.blocks;

import com.chef.mod.blocks.Pizza;
import com.chef.mod.init.MyBlocks;
import com.chef.mod.tileentity.TileEntityRawPizza;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class RawPizza
extends Pizza {
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, int x, int y, int z) {
        float f = 0.0625f;
        int bites = worldIn.getBlockMetadata(x, y, z);
        float f2 = 0.5f;
        if (bites == 0 || bites == 1) {
            this.setBlockBounds(0.0625f, 0.0f, 0.0625f, 0.935f, 0.0625f, 0.935f);
        } else if (bites == 2) {
            this.setBlockBounds(0.5f, 0.0f, 0.0625f, 0.9375f, 0.0625f, 0.9375f);
        } else if (bites == 3) {
            this.setBlockBounds(0.5f, 0.0f, 0.0625f, 0.9375f, 0.0625f, 0.5f);
        } else {
            this.setBlockBounds(0.0625f, 0.0f, 0.0625f, 0.935f, 0.0625f, 0.935f);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityRawPizza();
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = this == MyBlocks.pizza_dough ? iconRegister.registerIcon("chef:" + this.getUnlocalizedName().substring(5)) : iconRegister.registerIcon("chef:" + this.getUnlocalizedName().substring(5) + "_top");
    }
}

