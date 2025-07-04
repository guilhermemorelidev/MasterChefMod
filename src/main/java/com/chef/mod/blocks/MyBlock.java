/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 */
package com.chef.mod.blocks;

import com.chef.mod.Chef;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class MyBlock
extends Block {
    public MyBlock(Material materialIn) {
        super(materialIn);
        this.setCreativeTab(Chef.tabChef);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }
}

