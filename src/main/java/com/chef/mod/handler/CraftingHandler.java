/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$ItemCraftedEvent
 *  net.minecraft.item.ItemStack
 */
package com.chef.mod.handler;

import com.chef.mod.init.MyItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.item.ItemStack;

public class CraftingHandler {
    @SubscribeEvent
    public void onCrafting(PlayerEvent.ItemCraftedEvent event) {
        Object craftMatrix = null;
        for (int i = 0; i < event.craftMatrix.getSizeInventory(); ++i) {
            if (event.craftMatrix.getStackInSlot(i) == null) continue;
            ItemStack item0 = event.craftMatrix.getStackInSlot(i);
            if (item0 != null && item0.getItem() == MyItems.cutting_knife) {
                ItemStack k = new ItemStack(MyItems.cutting_knife, 2, item0.getItemDamage() + 1);
                if (k.getItemDamage() >= k.getMaxDamage()) {
                    --k.stackSize;
                }
                event.craftMatrix.setInventorySlotContents(i, k);
            }
            ItemStack itemStack = event.craftMatrix.getStackInSlot(i);
        }
    }
}

