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

import com.chef.mod.crafting.WaffleMakerRecipes;
import com.chef.mod.tileentity.TileEntityWaffleMaker;
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

public class ContainerWaffleMaker
extends Container {
    private TileEntityWaffleMaker waffleMaker;
    public int lastBakeItem;
    public int lastBurnTime;
    public int lastCurrentItemBurnTime;
    public int lastBakeTime;
    public int lastCurrentItemBakeTime;
    public int lastFirstCookTime;
    public int lastSecondCookTime;

    public ContainerWaffleMaker(InventoryPlayer invPlayer, TileEntityWaffleMaker teWaffleMaker) {
        int i;
        this.waffleMaker = teWaffleMaker;
        this.addSlotToContainer(new Slot((IInventory)teWaffleMaker, 0, 66, 5));
        this.addSlotToContainer(new Slot((IInventory)teWaffleMaker, 1, 94, 5));
        this.addSlotToContainer((Slot)new SlotFurnace(invPlayer.player, (IInventory)teWaffleMaker, 2, 44, 33));
        this.addSlotToContainer((Slot)new SlotFurnace(invPlayer.player, (IInventory)teWaffleMaker, 3, 116, 33));
        this.addSlotToContainer(new Slot((IInventory)teWaffleMaker, 4, 10, 55));
        this.addSlotToContainer(new Slot((IInventory)teWaffleMaker, 5, 150, 55));
        this.addSlotToContainer(new Slot((IInventory)teWaffleMaker, 6, 44, 58));
        this.addSlotToContainer(new Slot((IInventory)teWaffleMaker, 7, 116, 58));
        this.addSlotToContainer(new Slot((IInventory)teWaffleMaker, 8, 79, 61));
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
        icrafting.sendProgressBarUpdate((Container)this, 0, this.waffleMaker.bakeItem);
        icrafting.sendProgressBarUpdate((Container)this, 1, this.waffleMaker.burnTime);
        icrafting.sendProgressBarUpdate((Container)this, 2, this.waffleMaker.currentItemBurnTime);
        icrafting.sendProgressBarUpdate((Container)this, 3, this.waffleMaker.bakeTime);
        icrafting.sendProgressBarUpdate((Container)this, 4, this.waffleMaker.currentItemBakeTime);
        icrafting.sendProgressBarUpdate((Container)this, 5, this.waffleMaker.firstCookTime);
        icrafting.sendProgressBarUpdate((Container)this, 6, this.waffleMaker.secondCookTime);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);
            if (this.lastBurnTime != this.waffleMaker.bakeItem) {
                icrafting.sendProgressBarUpdate((Container)this, 0, this.waffleMaker.bakeItem);
            }
            if (this.lastBurnTime != this.waffleMaker.burnTime) {
                icrafting.sendProgressBarUpdate((Container)this, 1, this.waffleMaker.burnTime);
            }
            if (this.lastCurrentItemBurnTime != this.waffleMaker.currentItemBurnTime) {
                icrafting.sendProgressBarUpdate((Container)this, 2, this.waffleMaker.currentItemBurnTime);
            }
            if (this.lastCurrentItemBurnTime != this.waffleMaker.bakeTime) {
                icrafting.sendProgressBarUpdate((Container)this, 3, this.waffleMaker.bakeTime);
            }
            if (this.lastCurrentItemBurnTime != this.waffleMaker.currentItemBakeTime) {
                icrafting.sendProgressBarUpdate((Container)this, 4, this.waffleMaker.currentItemBakeTime);
            }
            if (this.lastFirstCookTime != this.waffleMaker.firstCookTime) {
                icrafting.sendProgressBarUpdate((Container)this, 5, this.waffleMaker.firstCookTime);
            }
            if (this.lastSecondCookTime == this.waffleMaker.secondCookTime) continue;
            icrafting.sendProgressBarUpdate((Container)this, 6, this.waffleMaker.secondCookTime);
        }
        this.lastBakeItem = this.waffleMaker.bakeItem;
        this.lastBurnTime = this.waffleMaker.burnTime;
        this.lastCurrentItemBurnTime = this.waffleMaker.currentItemBurnTime;
        this.lastBakeTime = this.waffleMaker.bakeTime;
        this.lastCurrentItemBakeTime = this.waffleMaker.currentItemBakeTime;
        this.lastFirstCookTime = this.waffleMaker.firstCookTime;
        this.lastSecondCookTime = this.waffleMaker.secondCookTime;
    }

    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            this.waffleMaker.bakeItem = par2;
        }
        if (par1 == 1) {
            this.waffleMaker.burnTime = par2;
        }
        if (par1 == 2) {
            this.waffleMaker.currentItemBurnTime = par2;
        }
        if (par1 == 3) {
            this.waffleMaker.bakeTime = par2;
        }
        if (par1 == 4) {
            this.waffleMaker.currentItemBakeTime = par2;
        }
        if (par1 == 5) {
            this.waffleMaker.firstCookTime = par2;
        }
        if (par1 == 6) {
            this.waffleMaker.secondCookTime = par2;
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
            if (slot == 4 ? !this.mergeItemStack(itemstack1, 30, 39, false) : (slot != 1 && slot != 0 ? (WaffleMakerRecipes.isItemDough(itemstack1) ? !this.mergeItemStack(itemstack1, 0, 1, false) && !this.mergeItemStack(itemstack1, 1, 2, false) : (TileEntityWaffleMaker.isItemFuel(itemstack1) ? !this.mergeItemStack(itemstack1, 4, 5, false) : (slot >= 2 && slot != 4 && slot < 30 ? !this.mergeItemStack(itemstack1, 30, 39, false) : slot >= 30 && slot < 39 && !this.mergeItemStack(itemstack1, 4, 30, false)))) : !this.mergeItemStack(itemstack1, 3, 39, false))) {
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

