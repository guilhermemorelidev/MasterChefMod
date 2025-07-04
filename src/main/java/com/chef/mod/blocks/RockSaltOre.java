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

public class RockSaltOre
extends Block {
    public RockSaltOre(Material materialIn) {
        super(materialIn);
        this.setHarvestLevel("pickaxe", 1);
        this.setStepSound(soundTypeStone);
        this.setHardness(2.5f);
        this.setResistance(4.0f);
    }

    public Item getItemDropped(int metadata, Random rand, int fortune) {
        return MyItems.rock_salt;
    }

    public int quantityDropped(Random random) {
        return 1 + random.nextInt(3);
    }
}

