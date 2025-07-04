/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.item.Item
 */
package com.chef.mod.blocks;

import com.chef.mod.init.MyItems;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class SeaSaltOre
extends Block {
    public SeaSaltOre(Material materialIn) {
        super(materialIn);
        this.setHarvestLevel("shovel", 1);
        this.setStepSound(soundTypeSand);
        this.setHardness(0.5f);
    }

    public Item getItemDropped(int metadata, Random rand, int fortune) {
        return MyItems.sea_salt;
    }

    public int quantityDropped(Random random) {
        return 1;
    }
}

