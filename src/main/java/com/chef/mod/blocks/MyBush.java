/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.material.Material
 *  net.minecraft.item.Item
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.EnumPlantType
 */
package com.chef.mod.blocks;

import com.chef.mod.init.MyBlocks;
import com.chef.mod.init.MyItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class MyBush
extends BlockBush {
    public int renderType;

    public MyBush(int renderType) {
        super(Material.plants);
        float f = 0.5f;
        this.setBlockBounds(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, 0.25f, 0.5f + f);
        this.setStepSound(soundTypeGrass);
        this.setCreativeTab(null);
        this.renderType = renderType;
    }

    @SideOnly(value=Side.CLIENT)
    public Item getItem(World worldIn, int x, int y, int z) {
        if (this == MyBlocks.strawberry_bush) {
            return MyItems.strawberry_seeds;
        }
        if (this == MyBlocks.blueberry_bush) {
            return MyItems.blueberry_seeds;
        }
        if (this == MyBlocks.grape_bush) {
            return MyItems.grape_seeds;
        }
        if (this == MyBlocks.asparagus_bush) {
            return MyItems.raw_asparagus;
        }
        if (this == MyBlocks.bell_pepper_bush) {
            return MyItems.bell_pepper_seeds;
        }
        if (this == MyBlocks.onion_bush) {
            return MyItems.raw_onion;
        }
        if (this == MyBlocks.tomato_bush) {
            return MyItems.tomato_seeds;
        }
        if (this == MyBlocks.rice_bush) {
            return MyItems.rice;
        }
        return null;
    }

    public Item getItemDropped(int i, Random rand, int fortune) {
        if (this == MyBlocks.strawberry_bush) {
            return MyItems.perfect_strawberry;
        }
        if (this == MyBlocks.blueberry_bush) {
            return MyItems.blueberry;
        }
        if (this == MyBlocks.grape_bush) {
            return MyItems.grape;
        }
        if (this == MyBlocks.asparagus_bush) {
            return MyItems.raw_asparagus;
        }
        if (this == MyBlocks.bell_pepper_bush) {
            return MyItems.bell_pepper_seeds;
        }
        if (this == MyBlocks.onion_bush) {
            return MyItems.raw_onion;
        }
        if (this == MyBlocks.tomato_bush) {
            return MyItems.tomato;
        }
        if (this == MyBlocks.rice_bush) {
            return MyItems.rice;
        }
        return null;
    }

    public int quantityDropped(Random random) {
        return 1;
    }

    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Plains;
    }

    public Block getPlant(IBlockAccess world, int x, int y, int z) {
        return this;
    }

    public int getRenderType() {
        return this.renderType;
    }
}

