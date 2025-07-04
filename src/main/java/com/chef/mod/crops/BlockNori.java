/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.chef.mod.crops;

import com.chef.mod.init.MyBlocks;
import com.chef.mod.init.MyItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockNori
extends Block {
    float f = 0.375f;

    public BlockNori() {
        super(Material.water);
        this.setStepSound(soundTypeGrass);
        this.setBlockBounds(0.5f - this.f, 0.0f, 0.5f - this.f, 0.5f + this.f, 1.0f, 0.5f + this.f);
        this.setTickRandomly(true);
    }

    public boolean canPlaceBlockAt(World worldIn, int x, int y, int z) {
        Block ground = worldIn.getBlock(x, y - 1, z);
        Block above = worldIn.getBlock(x, y + 1, z);
        return (ground == Blocks.gravel || ground == Blocks.dirt || ground == Blocks.sand || ground == MyBlocks.nori) && above == Blocks.water;
    }

    public void onNeighborBlockChange(World worldIn, int x, int y, int z, Block neighborBlock) {
        this.checkForDrop(worldIn, x, y, z);
    }

    public void breakBlock(World worldIn, int x, int y, int z) {
        worldIn.setBlock(x, y, z, Blocks.water);
    }

    protected final boolean checkForDrop(World worldIn, int x, int y, int z) {
        if (this.canBlockStay(worldIn, x, y, z)) {
            return true;
        }
        this.dropBlockAsItem(worldIn, x, y, z, worldIn.getBlockMetadata(x, y, z), 0);
        worldIn.setBlock(x, y, z, Blocks.water);
        return false;
    }

    public boolean canBlockStay(World world, int x, int y, int z) {
        return this.canPlaceBlockAt(world, x, y, z);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World worldIn, int x, int y, int z) {
        return null;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return 1;
    }

    public void updateTick(World worldIn, int x, int y, int z, Random rand) {
        if (worldIn.getBlock(x, y, z) == MyBlocks.nori && worldIn.getBlock(x, y + 2, z) == Blocks.water) {
            int i = 1;
            while (worldIn.getBlock(x, y - i, z) == this) {
                ++i;
            }
            if (i < 5) {
                int level = worldIn.getBlockMetadata(x, y, z);
                if (level == 15) {
                    worldIn.setBlock(x, y + 1, z, (Block)this);
                    worldIn.setBlockMetadataWithNotify(x, y, z, 0, 4);
                } else {
                    worldIn.setBlockMetadataWithNotify(x, y, z, level + 1, 4);
                }
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return MyItems.nori_leaf;
    }

    public Item getItemDropped(int metadata, Random rand, int fortune) {
        return MyItems.nori_leaf;
    }
}

