/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 */
package com.chef.mod.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemLeafBlocks
extends ItemBlock {
    public static final String[] leaves = new String[]{"mango"};

    public ItemLeafBlocks(Block block) {
        super(block);
        this.setHasSubtypes(true);
    }

    public String getUnlocalizedName(ItemStack itemstack) {
        int i = itemstack.getItemDamage();
        if (i < 0 || i >= leaves.length) {
            i = 0;
        }
        return super.getUnlocalizedName() + "." + leaves[i];
    }

    public int getMetadata(int meta) {
        return meta;
    }
}

