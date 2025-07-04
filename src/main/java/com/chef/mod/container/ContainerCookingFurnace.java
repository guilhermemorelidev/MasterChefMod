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
 *  net.minecraft.item.ItemStack
 */
package com.chef.mod.container;

import com.chef.mod.crafting.CookingFurnaceRecipes;
import com.chef.mod.slot.SlotCookingFurnaceBottle;
import com.chef.mod.slot.SlotGrease;
import com.chef.mod.tileentity.TileEntityCookingFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

public class ContainerCookingFurnace
extends Container {
    private TileEntityCookingFurnace cookingFurnace;
    public int lastBakeItem;
    public int lastBurnTime;
    public int lastCurrentItemBurnTime;
    public int lastBakeTime;
    public int lastCurrentItemBakeTime;
    public int lastCookTime;

    public ContainerCookingFurnace(InventoryPlayer invPlayer, TileEntityCookingFurnace tileentity) {
        int i;
        this.cookingFurnace = tileentity;
        this.addSlotToContainer(new Slot((IInventory)tileentity, 0, 32, 19));
        this.addSlotToContainer(new Slot((IInventory)tileentity, 1, 56, 19));
        this.addSlotToContainer(new Slot((IInventory)tileentity, 2, 56, 53));
        this.addSlotToContainer(new SlotGrease((IInventory)tileentity, 3, 32, 53));
        this.addSlotToContainer((Slot)new SlotFurnace(invPlayer.player, (IInventory)tileentity, 4, 116, 35));
        this.addSlotToContainer(new SlotCookingFurnaceBottle(invPlayer.player, (IInventory)tileentity, 5, 143, 35));
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
        icrafting.sendProgressBarUpdate((Container)this, 0, this.cookingFurnace.bakeItem);
        icrafting.sendProgressBarUpdate((Container)this, 1, this.cookingFurnace.burnTime);
        icrafting.sendProgressBarUpdate((Container)this, 2, this.cookingFurnace.currentItemBurnTime);
        icrafting.sendProgressBarUpdate((Container)this, 3, this.cookingFurnace.bakeTime);
        icrafting.sendProgressBarUpdate((Container)this, 4, this.cookingFurnace.currentItemBakeTime);
        icrafting.sendProgressBarUpdate((Container)this, 5, this.cookingFurnace.cookTime);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);
            if (this.lastBakeItem != this.cookingFurnace.bakeItem) {
                icrafting.sendProgressBarUpdate((Container)this, 0, this.cookingFurnace.bakeItem);
            }
            if (this.lastBurnTime != this.cookingFurnace.burnTime) {
                icrafting.sendProgressBarUpdate((Container)this, 1, this.cookingFurnace.burnTime);
            }
            if (this.lastCurrentItemBurnTime != this.cookingFurnace.currentItemBurnTime) {
                icrafting.sendProgressBarUpdate((Container)this, 2, this.cookingFurnace.currentItemBurnTime);
            }
            if (this.lastBakeTime != this.cookingFurnace.bakeTime) {
                icrafting.sendProgressBarUpdate((Container)this, 3, this.cookingFurnace.bakeTime);
            }
            if (this.lastCurrentItemBakeTime != this.cookingFurnace.currentItemBakeTime) {
                icrafting.sendProgressBarUpdate((Container)this, 4, this.cookingFurnace.currentItemBakeTime);
            }
            if (this.lastCookTime == this.cookingFurnace.cookTime) continue;
            icrafting.sendProgressBarUpdate((Container)this, 5, this.cookingFurnace.cookTime);
        }
        this.lastBakeItem = this.cookingFurnace.bakeItem;
        this.lastBurnTime = this.cookingFurnace.burnTime;
        this.lastCurrentItemBurnTime = this.cookingFurnace.currentItemBurnTime;
        this.lastBakeTime = this.cookingFurnace.bakeTime;
        this.lastCurrentItemBakeTime = this.cookingFurnace.currentItemBakeTime;
        this.lastCookTime = this.cookingFurnace.cookTime;
    }

    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            this.cookingFurnace.bakeItem = par2;
        }
        if (par1 == 1) {
            this.cookingFurnace.burnTime = par2;
        }
        if (par1 == 2) {
            this.cookingFurnace.currentItemBurnTime = par2;
        }
        if (par1 == 3) {
            this.cookingFurnace.bakeTime = par2;
        }
        if (par1 == 4) {
            this.cookingFurnace.currentItemBakeTime = par2;
        }
        if (par1 == 5) {
            this.cookingFurnace.cookTime = par2;
        }
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        ItemStack itemstack = null;
        Slot containerslot = (Slot)this.inventorySlots.get(slot);
        Object item = null;
        Object item2 = null;
        if (containerslot != null && containerslot.getHasStack()) {
            ItemStack itemstack1 = containerslot.getStack();
            itemstack = itemstack1.copy();
            if (slot == 3) {
                if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                    return null;
                }
                containerslot.onSlotChange(itemstack1, itemstack);
            } else if (slot == 2) {
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
            } else if (slot != 1 && slot != 0 ? (CookingFurnaceRecipes.isItemBreadCrumbs(itemstack1) ? !this.mergeItemStack(itemstack1, 0, 1, false) : (CookingFurnaceRecipes.isItemIngredient(itemstack1) ? !this.mergeItemStack(itemstack1, 1, 2, false) : (TileEntityCookingFurnace.isItemFuel(itemstack1) ? !this.mergeItemStack(itemstack1, 2, 3, false) : (TileEntityCookingFurnace.isItemGrease(itemstack1) ? !this.mergeItemStack(itemstack1, 3, 4, false) : (slot >= 4 && slot < 30 ? !this.mergeItemStack(itemstack1, 30, 39, false) : slot >= 30 && slot < 39 && !this.mergeItemStack(itemstack1, 6, 30, false)))))) : !this.mergeItemStack(itemstack1, 3, 39, false)) {
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

