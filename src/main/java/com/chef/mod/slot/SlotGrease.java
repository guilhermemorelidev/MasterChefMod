/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package com.chef.mod.slot;

import com.chef.mod.init.MyItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotGrease
extends Slot {
    public SlotGrease(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    public boolean isItemValid(ItemStack itemstack) {
        Item item = itemstack.getItem();
        if (item == MyItems.butter) {
            return true;
        }
        if (item == MyItems.fish_oil) {
            return true;
        }
        return item == MyItems.olive_oil;
    }
}

