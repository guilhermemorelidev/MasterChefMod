/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.IWorldGenerator
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBeach
 *  net.minecraft.world.biome.BiomeGenForest
 *  net.minecraft.world.biome.BiomeGenHills
 *  net.minecraft.world.biome.BiomeGenJungle
 *  net.minecraft.world.biome.BiomeGenOcean
 *  net.minecraft.world.biome.BiomeGenPlains
 *  net.minecraft.world.biome.BiomeGenRiver
 *  net.minecraft.world.biome.BiomeGenSavanna
 *  net.minecraft.world.biome.BiomeGenTaiga
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.gen.feature.WorldGenMinable
 */
package com.chef.mod.generate;

import com.chef.mod.generate.features.WorldGenBush;
import com.chef.mod.generate.features.WorldGenCorn;
import com.chef.mod.generate.features.WorldGenNori;
import com.chef.mod.generate.features.WorldGenTree;
import com.chef.mod.init.MyBlocks;
import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBeach;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.biome.BiomeGenHills;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraft.world.biome.BiomeGenPlains;
import net.minecraft.world.biome.BiomeGenRiver;
import net.minecraft.world.biome.BiomeGenSavanna;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class WorldGeneration
implements IWorldGenerator {
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.dimensionId) {
            case 1: {
                this.generateEnd(world, random, chunkX * 16, chunkZ * 16);
                break;
            }
            case 0: {
                this.generateOverworld(world, random, chunkX * 16, chunkZ * 16);
                break;
            }
            case -1: {
                this.generateNether(world, random, chunkX * 16, chunkZ * 16);
            }
        }
    }

    public void generateEnd(World world, Random rand, int x, int z) {
    }

    public void generateOverworld(World world, Random random, int x, int z) {
        this.addOreSpawn(MyBlocks.sea_salt_ore, (Block)Blocks.sand, "seaSaltBiomes", world, random, x, z, 16, 16, 2 + random.nextInt(5), 500, 30, 100);
        this.addOreSpawn(MyBlocks.rock_salt_ore, Blocks.stone, "rockSaltBiomes", world, random, x, z, 16, 16, 2 + random.nextInt(8), 25, 60, 85);
        this.addBushSpawn(MyBlocks.strawberry_bush, (Block)Blocks.grass, "strawberryBiomes", world, random, x, z, 16, 16, 3, 50, 100);
        this.addBushSpawn(MyBlocks.blueberry_bush, (Block)Blocks.grass, "blueberryBiomes", world, random, x, z, 16, 16, 3, 50, 100);
        this.addBushSpawn(MyBlocks.rice_bush, (Block)Blocks.grass, "riceBiomes", world, random, x, z, 16, 16, 3, 50, 100);
        this.addBushSpawn(MyBlocks.tomato_bush, (Block)Blocks.grass, "tomatoBiomes", world, random, x, z, 16, 16, 3, 50, 100);
        this.addBushSpawn(MyBlocks.onion_bush, (Block)Blocks.grass, "onionBiomes", world, random, x, z, 16, 16, 2, 50, 100);
        this.addBushSpawn(MyBlocks.asparagus_bush, (Block)Blocks.grass, "asparagusBiomes", world, random, x, z, 16, 16, 2, 50, 100);
        this.addBushSpawn(MyBlocks.bell_pepper_bush, (Block)Blocks.grass, "bellPepperBiomes", world, random, x, z, 16, 16, 1, 50, 100);
        this.addBushSpawn(MyBlocks.grape_bush, (Block)Blocks.grass, "grapeBiomes", world, random, x, z, 16, 16, 1, 50, 100);
        this.addCornSpawn(MyBlocks.corns, 1, 3, "plains", world, random, x, z, 16, 16, 5, 50, 100);
        this.addNoriSpawn(MyBlocks.nori, 1, 3, "ocean", world, random, x, z, 16, 16, 15, 45, 100);
        this.addTreeSpawn(world, 6, Blocks.log, 3, MyBlocks.mango_leaves, "jungle", random, x, z, 16, 16, 5, 50, 100);
        this.addTreeSpawn(world, 5, Blocks.log2, 0, MyBlocks.olive_leaves_stage0, "savanna", random, x, z, 16, 16, 5, 50, 100);
    }

    public void generateNether(World world, Random rand, int x, int z) {
    }

    public void addOreSpawn(Block block, Block spawnsIn, String biomeString, World world, Random random, int blockXpos, int blockZpos, int maxX, int maxZ, int maxVeinSize, int chanceToSpawn, int minY, int maxY) {
        for (int i = 0; i < chanceToSpawn; ++i) {
            int posX = blockXpos + random.nextInt(maxX);
            int posY = minY + random.nextInt(maxY - minY);
            int posZ = blockZpos + random.nextInt(maxZ);
            BiomeGenBase biome = world.getBiomeGenForCoords(posX, posZ);
            if (biomeString.equalsIgnoreCase("seaSaltBiomes")) {
                if (!(biome instanceof BiomeGenRiver) && !(biome instanceof BiomeGenBeach)) continue;
                new WorldGenMinable(block, maxVeinSize, spawnsIn).generate(world, random, posX, posY, posZ);
                continue;
            }
            if (biomeString.equalsIgnoreCase("rockSaltBiomes")) {
                if (!(biome instanceof BiomeGenHills)) continue;
                new WorldGenMinable(block, maxVeinSize, spawnsIn).generate(world, random, posX, posY, posZ);
                continue;
            }
            new WorldGenMinable(block, maxVeinSize, spawnsIn).generate(world, random, posX, posY, posZ);
        }
    }

    public void addBushSpawn(Block block, Block spawnsOn, String biomeString, World world, Random random, int blockXpos, int blockZpos, int maxX, int maxZ, int chanceToSpawn, int minY, int maxY) {
        for (int i = 0; i < chanceToSpawn; ++i) {
            int posX = blockXpos + random.nextInt(maxX);
            int posY = minY + random.nextInt(maxY - minY);
            int posZ = blockZpos + random.nextInt(maxZ);
            BiomeGenBase biome = world.getBiomeGenForCoords(posX, posZ);
            if (biomeString.equalsIgnoreCase("strawberryBiomes")) {
                if (!(biome instanceof BiomeGenTaiga) && !(biome instanceof BiomeGenForest)) continue;
                new WorldGenBush(block, spawnsOn).generate(world, random, posX, posY, posZ);
                continue;
            }
            if (biomeString.equalsIgnoreCase("blueberryBiomes")) {
                if (!(biome instanceof BiomeGenTaiga)) continue;
                new WorldGenBush(block, spawnsOn).generate(world, random, posX, posY, posZ);
                continue;
            }
            if (biomeString.equalsIgnoreCase("bellPepperBiomes") || biomeString.equalsIgnoreCase("tomatoBiomes")) {
                if (!(biome instanceof BiomeGenHills)) continue;
                new WorldGenBush(block, spawnsOn).generate(world, random, posX, posY, posZ);
                continue;
            }
            if (biomeString.equalsIgnoreCase("asparagusBiomes")) {
                if (biome != BiomeGenBase.roofedForest) continue;
                new WorldGenBush(block, spawnsOn).generate(world, random, posX, posY, posZ);
                continue;
            }
            if (biomeString.equalsIgnoreCase("onionBiomes")) {
                if (biome != BiomeGenBase.birchForest) continue;
                new WorldGenBush(block, spawnsOn).generate(world, random, posX, posY, posZ);
                continue;
            }
            if (biomeString.equalsIgnoreCase("riceBiomes")) {
                if (!(biome instanceof BiomeGenJungle)) continue;
                new WorldGenBush(block, spawnsOn).generate(world, random, posX, posY, posZ);
                continue;
            }
            if (biomeString.equalsIgnoreCase("grapeBiomes")) {
                if (biome != BiomeGenBase.plains) continue;
                new WorldGenBush(block, spawnsOn).generate(world, random, posX, posY, posZ);
                continue;
            }
            new WorldGenBush(block, spawnsOn).generate(world, random, posX, posY, posZ);
        }
    }

    public void addTreeSpawn(World world, int minTreeHeight, Block log, int meta, Block leaves, String biomeString, Random random, int blockXpos, int blockZpos, int maxX, int maxZ, int chanceToSpawn, int minY, int maxY) {
        for (int i = 0; i < chanceToSpawn; ++i) {
            int posX = blockXpos + random.nextInt(maxX);
            int posY = minY + random.nextInt(maxY - minY);
            int posZ = blockZpos + random.nextInt(maxZ);
            BiomeGenBase biome = world.getBiomeGenForCoords(posX, posZ);
            if (biomeString.equalsIgnoreCase("savanna")) {
                if (!(biome instanceof BiomeGenSavanna)) continue;
                new WorldGenTree(minTreeHeight, log, meta, leaves).generate(world, random, posX, posY, posZ);
                continue;
            }
            if (biomeString.equalsIgnoreCase("jungle")) {
                if (!(biome instanceof BiomeGenJungle)) continue;
                new WorldGenTree(minTreeHeight, log, meta, leaves).generate(world, random, posX, posY, posZ);
                continue;
            }
            new WorldGenTree(minTreeHeight, log, meta, leaves).generate(world, random, posX, posY, posZ);
        }
    }

    public void addCornSpawn(Block block, int minHeight, int maxHeight, String biomeString, World world, Random random, int blockXpos, int blockZpos, int maxX, int maxZ, int chanceToSpawn, int minY, int maxY) {
        for (int i = 0; i < chanceToSpawn; ++i) {
            int posX = blockXpos + random.nextInt(maxX);
            int posY = minY + random.nextInt(maxY - minY);
            int posZ = blockZpos + random.nextInt(maxZ);
            BiomeGenBase biome = world.getBiomeGenForCoords(posX, posZ);
            if (!biomeString.equalsIgnoreCase("plains") || !(biome instanceof BiomeGenPlains)) continue;
            new WorldGenCorn(block, minHeight, maxHeight).generate(world, random, posX, posY, posZ);
        }
    }

    public void addNoriSpawn(Block block, int minHeight, int maxHeight, String biomeString, World world, Random random, int blockXpos, int blockZpos, int maxX, int maxZ, int chanceToSpawn, int minY, int maxY) {
        for (int i = 0; i < chanceToSpawn; ++i) {
            int posX = blockXpos + random.nextInt(maxX);
            int posY = minY + random.nextInt(maxY - minY);
            int posZ = blockZpos + random.nextInt(maxZ);
            BiomeGenBase biome = world.getBiomeGenForCoords(posX, posZ);
            if (!biomeString.equalsIgnoreCase("ocean") || !(biome instanceof BiomeGenOcean)) continue;
            new WorldGenNori(block, minHeight, maxHeight).generate(world, random, posX, posY, posZ);
        }
    }
}

