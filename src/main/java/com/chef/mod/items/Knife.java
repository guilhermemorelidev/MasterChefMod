/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package com.chef.mod.items;

import com.chef.mod.Chef;
import com.chef.mod.init.MyItems;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Knife
extends Item {
    public Knife() {
        this.setMaxDamage(100);
        this.maxStackSize = 1;
        this.setCreativeTab(Chef.tabChef);
    }

    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return toRepair.getItem() == MyItems.sickle && repair.getItem() == Items.iron_ingot;
    }
}

