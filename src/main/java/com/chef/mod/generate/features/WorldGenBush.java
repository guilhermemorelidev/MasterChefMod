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

public class WorldGenBush
extends WorldGenerator {
    Block block;
    Block spawnsOn;

    public WorldGenBush(Block block, Block spawnsOn) {
        this.block = block;
        this.spawnsOn = spawnsOn;
    }

    public boolean generate(World world, Random random, int x, int y, int z) {
        int z1;
        int y1;
        int x1 = x + random.nextInt(8) - random.nextInt(8);
        if (world.isAirBlock(x1, y1 = y + random.nextInt(4) - random.nextInt(4), z1 = z + random.nextInt(8) - random.nextInt(8)) && world.getBlock(x1, y1 - 1, z1) == Blocks.grass && this.block.canPlaceBlockAt(world, x1, y1, z1)) {
            world.setBlock(x1, y1, z1, this.block, random.nextInt(4), 2);
        }
        return true;
    }
}

