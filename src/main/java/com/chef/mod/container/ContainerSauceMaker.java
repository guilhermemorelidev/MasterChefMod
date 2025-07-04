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

import com.chef.mod.crafting.SauceMakerRecipes;
import com.chef.mod.slot.SlotSauceMakerBowl;
import com.chef.mod.tileentity.TileEntitySauceMaker;
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

public class ContainerSauceMaker
extends Container {
    private TileEntitySauceMaker sauceMaker;
    public int lastBurnTime;
    public int lastCurrentItemBurnTime;
    public int lastCookTime;

    public ContainerSauceMaker(InventoryPlayer invPlayer, TileEntitySauceMaker teSauceMaker) {
        int i;
        this.sauceMaker = teSauceMaker;
        this.addSlotToContainer(new Slot((IInventory)teSauceMaker, 0, 21, 9));
        this.addSlotToContainer(new SlotSauceMakerBowl((IInventory)teSauceMaker, 1, 49, 51));
        this.addSlotToContainer(new Slot((IInventory)teSauceMaker, 2, 21, 51));
        this.addSlotToContainer((Slot)new SlotFurnace(invPlayer.player, (IInventory)teSauceMaker, 3, 120, 29));
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
        icrafting.sendProgressBarUpdate((Container)this, 0, this.sauceMaker.burnTime);
        icrafting.sendProgressBarUpdate((Container)this, 1, this.sauceMaker.currentItemBurnTime);
        icrafting.sendProgressBarUpdate((Container)this, 2, this.sauceMaker.cookTime);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);
            if (this.lastBurnTime != this.sauceMaker.burnTime) {
                icrafting.sendProgressBarUpdate((Container)this, 0, this.sauceMaker.burnTime);
            }
            if (this.lastCurrentItemBurnTime != this.sauceMaker.currentItemBurnTime) {
                icrafting.sendProgressBarUpdate((Container)this, 1, this.sauceMaker.currentItemBurnTime);
            }
            if (this.lastCookTime == this.sauceMaker.cookTime) continue;
            icrafting.sendProgressBarUpdate((Container)this, 2, this.sauceMaker.cookTime);
        }
        this.lastBurnTime = this.sauceMaker.burnTime;
        this.lastCurrentItemBurnTime = this.sauceMaker.currentItemBurnTime;
        this.lastCookTime = this.sauceMaker.cookTime;
    }

    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            this.sauceMaker.burnTime = par2;
        }
        if (par1 == 1) {
            this.sauceMaker.currentItemBurnTime = par2;
        }
        if (par1 == 2) {
            this.sauceMaker.cookTime = par2;
        }
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        ItemStack itemstack = null;
        Slot containerslot = (Slot)this.inventorySlots.get(slot);
        Item item = null;
        Item item2 = null;
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
                if (SauceMakerRecipes.getJuicingResult(item, item2) != null) {
                    return null;
                }
                if (SauceMakerRecipes.isItemIngredient(itemstack1) ? !this.mergeItemStack(itemstack1, 0, 1, false) : (SauceMakerRecipes.isItemBowl(itemstack1) ? !this.mergeItemStack(itemstack1, 1, 2, false) : (TileEntitySauceMaker.isItemFuel(itemstack1) ? !this.mergeItemStack(itemstack1, 2, 3, false) : (slot >= 3 && slot < 30 ? !this.mergeItemStack(itemstack1, 30, 39, false) : slot >= 30 && slot < 39 && !this.mergeItemStack(itemstack1, 4, 30, false))))) {
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

