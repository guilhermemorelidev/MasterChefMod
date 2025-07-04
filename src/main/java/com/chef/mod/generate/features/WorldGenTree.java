/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSapling
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.chef.mod.generate.features;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenTree
extends WorldGenAbstractTree {
    private final int minTreeHeight;
    private final Block log;
    private final int meta;
    private final Block leaves;

    public WorldGenTree(int minTreeHeight, Block log, int meta, Block leaves) {
        super(false);
        this.minTreeHeight = minTreeHeight;
        this.log = log;
        this.meta = meta;
        this.leaves = leaves;
    }

    public boolean generate(World worldIn, Random random, int x, int y, int z) {
        int l = random.nextInt(3) + this.minTreeHeight;
        boolean flag = true;
        if (y >= 1 && y + l + 1 <= 256) {
            Block block;
            int k1;
            int b0;
            for (int i1 = y; i1 <= y + 1 + l; ++i1) {
                b0 = 1;
                if (i1 == y) {
                    b0 = 0;
                }
                if (i1 >= y + 1 + l - 2) {
                    b0 = 2;
                }
                for (int j1 = x - b0; j1 <= x + b0 && flag; ++j1) {
                    for (k1 = z - b0; k1 <= z + b0 && flag; ++k1) {
                        if (i1 >= 0 && i1 < 256) {
                            block = worldIn.getBlock(j1, i1, k1);
                            if (this.isReplaceable(worldIn, j1, i1, k1)) continue;
                            flag = false;
                            continue;
                        }
                        flag = false;
                    }
                }
            }
            if (!flag) {
                return false;
            }
            Block block2 = worldIn.getBlock(x, y - 1, z);
            boolean isSoil = block2.canSustainPlant((IBlockAccess)worldIn, x, y - 1, z, ForgeDirection.UP, (IPlantable)((BlockSapling)Blocks.sapling));
            if (isSoil && y < 256 - l - 1) {
                block2.onPlantGrow(worldIn, x, y - 1, z, x, y, z);
                b0 = 3;
                int b1 = 0;
                for (k1 = y - b0 + l; k1 <= y + l; ++k1) {
                    int i3 = k1 - (y + l);
                    int l1 = b1 + 1 - i3 / 2;
                    for (int i2 = x - l1; i2 <= x + l1; ++i2) {
                        int j2 = i2 - x;
                        for (int k2 = z - l1; k2 <= z + l1; ++k2) {
                            Block block1;
                            int l2 = k2 - z;
                            if (Math.abs(j2) == l1 && Math.abs(l2) == l1 && (random.nextInt(2) == 0 || i3 == 0) || !(block1 = worldIn.getBlock(i2, k1, k2)).isAir((IBlockAccess)worldIn, i2, k1, k2) && !block1.isLeaves((IBlockAccess)worldIn, i2, k1, k2)) continue;
                            this.buildBlock(worldIn, i2, k1, k2, this.leaves, 1);
                        }
                    }
                }
                for (k1 = 0; k1 < l; ++k1) {
                    block = worldIn.getBlock(x, y + k1, z);
                    if (!block.isAir((IBlockAccess)worldIn, x, y + k1, z) && !block.isLeaves((IBlockAccess)worldIn, x, y + k1, z)) continue;
                    this.buildBlock(worldIn, x, y + k1, z, this.log, this.meta);
                }
                return true;
            }
            return false;
        }
        return false;
    }

    private void buildBlock(World world, int x, int y, int z, Block block, int meta) {
        if (world.isAirBlock(x, y, z) || world.getBlock(x, y, z).isLeaves((IBlockAccess)world, x, y, z)) {
            world.setBlock(x, y, z, block, meta, 2);
        }
    }
}

