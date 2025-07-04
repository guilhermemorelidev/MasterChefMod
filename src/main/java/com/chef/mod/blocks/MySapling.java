/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSapling
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenTrees
 *  net.minecraft.world.gen.feature.WorldGenerator
 *  net.minecraftforge.event.terraingen.TerrainGen
 */
package com.chef.mod.blocks;

import com.chef.mod.Chef;
import com.chef.mod.generate.features.WorldGenTree;
import com.chef.mod.init.MyBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class MySapling
extends BlockSapling {
    public static final String[] saplings = new String[]{"mango", "olive"};
    private static final IIcon[] iconArray = new IIcon[saplings.length];

    public MySapling() {
        float f = 0.4f;
        this.setBlockBounds(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, f * 2.0f, 0.5f + f);
        this.setCreativeTab(Chef.tabChef);
        this.setStepSound(soundTypeGrass);
    }

    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (!world.isRemote) {
            super.updateTick(world, x, y, z, rand);
            if (world.getBlockLightValue(x, y + 1, z) >= 9 && rand.nextInt(7) == 0) {
                this.func_149879_c(world, x, y, z, rand);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return iconArray[MathHelper.clamp_int((int)(meta &= 7), (int)0, (int)(iconArray.length - 1))];
    }

    public void func_149879_c(World world, int x, int y, int z, Random rand) {
        int l = world.getBlockMetadata(x, y, z);
        if ((l & 8) == 0) {
            world.setBlockMetadataWithNotify(x, y, z, l | 8, 4);
        } else {
            this.func_149878_d(world, x, y, z, rand);
        }
    }

    public void func_149878_d(World world, int x, int y, int z, Random rand) {
        Object object;
        if (!TerrainGen.saplingGrowTree((World)world, (Random)rand, (int)x, (int)y, (int)z)) {
            return;
        }
        int l = world.getBlockMetadata(x, y, z) & 7;
        int i1 = 0;
        int j1 = 0;
        boolean flag = false;
        switch (l) {
            case 0: {
                object = new WorldGenTree(6, Blocks.log, 3, MyBlocks.mango_leaves);
                break;
            }
            case 1: {
                object = new WorldGenTree(5, Blocks.log2, 0, MyBlocks.olive_leaves_stage0);
                break;
            }
            default: {
                object = new WorldGenTrees(true);
            }
        }
        Block block = Blocks.air;
        if (flag) {
            world.setBlock(x + i1, y, z + j1, block, 0, 4);
            world.setBlock(x + i1 + 1, y, z + j1, block, 0, 4);
            world.setBlock(x + i1, y, z + j1 + 1, block, 0, 4);
            world.setBlock(x + i1 + 1, y, z + j1 + 1, block, 0, 4);
        } else {
            world.setBlock(x, y, z, block, 0, 4);
        }
        if (!((WorldGenerator)object).generate(world, rand, x + i1, y, z + j1)) {
            if (flag) {
                world.setBlock(x + i1, y, z + j1, (Block)this, l, 4);
                world.setBlock(x + i1 + 1, y, z + j1, (Block)this, l, 4);
                world.setBlock(x + i1, y, z + j1 + 1, (Block)this, l, 4);
                world.setBlock(x + i1 + 1, y, z + j1 + 1, (Block)this, l, 4);
            } else {
                world.setBlock(x, y, z, (Block)this, l, 4);
            }
        }
    }

    public boolean func_149880_a(World world, int x, int y, int z, int p_149880_5_) {
        return world.getBlock(x, y, z) == this && (world.getBlockMetadata(x, y, z) & 7) == p_149880_5_;
    }

    public int damageDropped(int i) {
        return MathHelper.clamp_int((int)(i & 7), (int)0, (int)5);
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < saplings.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        for (int i = 0; i < iconArray.length; ++i) {
            MySapling.iconArray[i] = iconRegister.registerIcon("chef:" + saplings[i] + "_" + this.getUnlocalizedName().substring(5));
        }
    }

    public boolean func_149851_a(World world, int x, int y, int z, boolean p_149851_5_) {
        return true;
    }

    public boolean func_149852_a(World world, Random rand, int x, int y, int z) {
        return (double)world.rand.nextFloat() < 0.45;
    }

    public void func_149853_b(World world, Random rand, int x, int y, int z) {
        this.func_149879_c(world, x, y, z, rand);
    }
}

