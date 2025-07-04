/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ICrafting
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.inventory.SlotFurnace
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package com.chef.mod.container;

import com.chef.mod.crafting.BoilerRecipes;
import com.chef.mod.slot.SlotWater;
import com.chef.mod.tileentity.TileEntityBoiler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerBoiler
extends Container {
    private TileEntityBoiler boiler;
    public int lastBurnTime;
    public int lastCurrentItemBurnTime;
    public int lastHeath;
    public int lastWater;
    public int lastCookTime;
    public int lastBubbleTime;

    public ContainerBoiler(InventoryPlayer invPlayer, TileEntityBoiler tileentity) {
        int i;
        this.boiler = tileentity;
        this.addSlotToContainer(new Slot((IInventory)tileentity, 0, 70, 51));
        this.addSlotToContainer(new Slot((IInventory)tileentity, 1, 17, 64));
        this.addSlotToContainer(new SlotWater((IInventory)tileentity, 2, 43, 24));
        this.addSlotToContainer((Slot)new SlotFurnace(invPlayer.player, (IInventory)tileentity, 3, 138, 51));
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)invPlayer, i, 8 + i * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting icrafting) {
        super.addCraftingToCrafters(icrafting);
        icrafting.sendProgressBarUpdate((Container)this, 0, this.boiler.burnTime);
        icrafting.sendProgressBarUpdate((Container)this, 1, this.boiler.currentItemBurnTime);
        icrafting.sendProgressBarUpdate((Container)this, 2, this.boiler.heath);
        icrafting.sendProgressBarUpdate((Container)this, 3, this.boiler.water);
        icrafting.sendProgressBarUpdate((Container)this, 4, this.boiler.cookTime);
        icrafting.sendProgressBarUpdate((Container)this, 5, this.boiler.bubbleTime);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);
            if (this.lastBurnTime != this.boiler.burnTime) {
                icrafting.sendProgressBarUpdate((Container)this, 0, this.boiler.burnTime);
            }
            if (this.lastCurrentItemBurnTime != this.boiler.currentItemBurnTime) {
                icrafting.sendProgressBarUpdate((Container)this, 1, this.boiler.currentItemBurnTime);
            }
            if (this.lastHeath != this.boiler.heath) {
                icrafting.sendProgressBarUpdate((Container)this, 2, this.boiler.heath);
            }
            if (this.lastWater != this.boiler.water) {
                icrafting.sendProgressBarUpdate((Container)this, 3, this.boiler.water);
            }
            if (this.lastCookTime != this.boiler.cookTime) {
                icrafting.sendProgressBarUpdate((Container)this, 4, this.boiler.cookTime);
            }
            if (this.lastBubbleTime == this.boiler.bubbleTime) continue;
            icrafting.sendProgressBarUpdate((Container)this, 5, this.boiler.bubbleTime);
        }
        this.lastBurnTime = this.boiler.burnTime;
        this.lastCurrentItemBurnTime = this.boiler.currentItemBurnTime;
        this.lastHeath = this.boiler.heath;
        this.lastWater = this.boiler.water;
        this.lastCookTime = this.boiler.cookTime;
        this.lastBubbleTime = this.boiler.bubbleTime;
    }

    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            this.boiler.burnTime = par2;
        }
        if (par1 == 1) {
            this.boiler.currentItemBurnTime = par2;
        }
        if (par1 == 2) {
            this.boiler.heath = par2;
        }
        if (par1 == 3) {
            this.boiler.water = par2;
        }
        if (par1 == 4) {
            this.boiler.cookTime = par2;
        }
        if (par1 == 5) {
            this.boiler.bubbleTime = par2;
        }
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        ItemStack itemstack = null;
        Slot containerslot = (Slot)this.inventorySlots.get(slot);
        Item item = null;
        Object item2 = null;
        if (containerslot != null && containerslot.getHasStack()) {
            ItemStack itemstack1 = containerslot.getStack();
            itemstack = itemstack1.copy();
            if (slot == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }
                containerslot.onSlotChange(itemstack1, itemstack);
            } else if (slot == 1) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }
                containerslot.onSlotChange(itemstack1, itemstack);
            } else if (slot == 0) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }
                containerslot.onSlotChange(itemstack1, itemstack);
            } else if (slot != 1 && slot != 0) {
                if (BoilerRecipes.getBoilingResult(item) != null) {
                    return null;
                }
                if (BoilerRecipes.isItemIngredient(itemstack1) ? !this.mergeItemStack(itemstack1, 0, 1, false) : (TileEntityBoiler.isItemFuel(itemstack1) ? !this.mergeItemStack(itemstack1, 1, 2, false) : (TileEntityBoiler.isItemWater(itemstack1) ? !this.mergeItemStack(itemstack1, 2, 3, false) : (slot >= 3 && slot < 30 ? !this.mergeItemStack(itemstack1, 30, 39, false) : slot >= 30 && slot < 39 && !this.mergeItemStack(itemstack1, 4, 30, false))))) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
                return null;
            }
            if (itemstack1.stackSize == 0) {
                containerslot.putStack((ItemStack)null);
            } else {
                containerslot.onSlotChanged();
            }
            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }
            containerslot.onPickupFromSlot(player, itemstack1);
        }
        return itemstack;
    }
}

