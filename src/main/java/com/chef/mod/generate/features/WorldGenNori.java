/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package com.chef.mod.generate.features;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenNori
extends WorldGenerator {
    Block block;
    int minHeight;
    int maxHeight;

    public WorldGenNori(Block block, int minHeight, int maxHeight) {
        this.block = block;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    public boolean generate(World world, Random random, int x, int y, int z) {
        for (int l = 0; l < 20; ++l) {
            int k1;
            int j1;
            int i1 = x + random.nextInt(4) - random.nextInt(4);
            if (world.getBlock(i1, j1 = y, k1 = z + random.nextInt(4) - random.nextInt(4)) != Blocks.water) continue;
            int j = this.minHeight + random.nextInt(random.nextInt(this.maxHeight) + 1);
            for (int k = 0; k < j; ++k) {
                if (!this.block.canPlaceBlockAt(world, i1, j1, k1)) continue;
                world.setBlock(i1, j1 + k, k1, this.block, 0, 2);
            }
        }
        return true;
    }
}

