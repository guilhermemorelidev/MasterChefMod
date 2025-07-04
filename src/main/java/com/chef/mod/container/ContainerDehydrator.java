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

import com.chef.mod.crafting.DehydratorRecipes;
import com.chef.mod.tileentity.TileEntityDehydrator;
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

public class ContainerDehydrator
extends Container {
    private TileEntityDehydrator dehydrator;
    public int lastBurnTime;
    public int lastCurrentItemBurnTime;
    public int lastFirstCookTime;
    public int lastSecondCookTime;
    public int lastThirdCookTime;
    public int lastFourthCookTime;

    public ContainerDehydrator(InventoryPlayer invPlayer, TileEntityDehydrator teDehydrator) {
        int i;
        this.dehydrator = teDehydrator;
        this.addSlotToContainer(new Slot((IInventory)teDehydrator, 0, 36, 62));
        this.addSlotToContainer(new Slot((IInventory)teDehydrator, 1, 64, 62));
        this.addSlotToContainer(new Slot((IInventory)teDehydrator, 2, 92, 62));
        this.addSlotToContainer(new Slot((IInventory)teDehydrator, 3, 120, 62));
        this.addSlotToContainer((Slot)new SlotFurnace(invPlayer.player, (IInventory)teDehydrator, 4, 36, 5));
        this.addSlotToContainer((Slot)new SlotFurnace(invPlayer.player, (IInventory)teDehydrator, 5, 64, 5));
        this.addSlotToContainer((Slot)new SlotFurnace(invPlayer.player, (IInventory)teDehydrator, 6, 92, 5));
        this.addSlotToContainer((Slot)new SlotFurnace(invPlayer.player, (IInventory)teDehydrator, 7, 120, 5));
        this.addSlotToContainer(new Slot((IInventory)teDehydrator, 8, 148, 33));
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
        icrafting.sendProgressBarUpdate((Container)this, 0, this.dehydrator.burnTime);
        icrafting.sendProgressBarUpdate((Container)this, 1, this.dehydrator.currentItemBurnTime);
        icrafting.sendProgressBarUpdate((Container)this, 2, this.dehydrator.firstCookTime);
        icrafting.sendProgressBarUpdate((Container)this, 3, this.dehydrator.secondCookTime);
        icrafting.sendProgressBarUpdate((Container)this, 4, this.dehydrator.thirdCookTime);
        icrafting.sendProgressBarUpdate((Container)this, 5, this.dehydrator.fourthCookTime);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);
            if (this.lastBurnTime != this.dehydrator.burnTime) {
                icrafting.sendProgressBarUpdate((Container)this, 0, this.dehydrator.burnTime);
            }
            if (this.lastCurrentItemBurnTime != this.dehydrator.currentItemBurnTime) {
                icrafting.sendProgressBarUpdate((Container)this, 1, this.dehydrator.currentItemBurnTime);
            }
            if (this.lastFirstCookTime != this.dehydrator.firstCookTime) {
                icrafting.sendProgressBarUpdate((Container)this, 2, this.dehydrator.firstCookTime);
            }
            if (this.lastSecondCookTime != this.dehydrator.secondCookTime) {
                icrafting.sendProgressBarUpdate((Container)this, 3, this.dehydrator.secondCookTime);
            }
            if (this.lastThirdCookTime != this.dehydrator.thirdCookTime) {
                icrafting.sendProgressBarUpdate((Container)this, 4, this.dehydrator.thirdCookTime);
            }
            if (this.lastFourthCookTime == this.dehydrator.fourthCookTime) continue;
            icrafting.sendProgressBarUpdate((Container)this, 5, this.dehydrator.fourthCookTime);
        }
        this.lastBurnTime = this.dehydrator.burnTime;
        this.lastCurrentItemBurnTime = this.dehydrator.currentItemBurnTime;
        this.lastFirstCookTime = this.dehydrator.firstCookTime;
        this.lastSecondCookTime = this.dehydrator.secondCookTime;
        this.lastThirdCookTime = this.dehydrator.thirdCookTime;
        this.lastFourthCookTime = this.dehydrator.fourthCookTime;
    }

    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            this.dehydrator.burnTime = par2;
        }
        if (par1 == 1) {
            this.dehydrator.currentItemBurnTime = par2;
        }
        if (par1 == 2) {
            this.dehydrator.firstCookTime = par2;
        }
        if (par1 == 3) {
            this.dehydrator.secondCookTime = par2;
        }
        if (par1 == 4) {
            this.dehydrator.thirdCookTime = par2;
        }
        if (par1 == 5) {
            this.dehydrator.fourthCookTime = par2;
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
            if (slot == 0 || slot == 1 || slot == 2 || slot == 3 || slot == 4 || slot == 5 || slot == 6 || slot == 7 || slot == 8) {
                if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                    return null;
                }
                containerslot.onSlotChange(itemstack1, itemstack);
            } else if (slot != 1 && slot != 0 ? (DehydratorRecipes.isItemIngredient(itemstack1) ? !this.mergeItemStack(itemstack1, 0, 1, false) && !this.mergeItemStack(itemstack1, 1, 2, false) && !this.mergeItemStack(itemstack1, 2, 3, false) && !this.mergeItemStack(itemstack1, 3, 4, false) : (TileEntityDehydrator.isItemFuel(itemstack1) ? !this.mergeItemStack(itemstack1, 8, 9, false) : (slot >= 4 && slot != 8 && slot < 30 ? !this.mergeItemStack(itemstack1, 30, 39, false) : slot >= 30 && slot < 39 && !this.mergeItemStack(itemstack1, 4, 30, false)))) : !this.mergeItemStack(itemstack1, 3, 39, false)) {
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

