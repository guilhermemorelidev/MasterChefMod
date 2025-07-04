/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumFacing
 */
package com.chef.mod.tileentity;

import com.chef.mod.blocks.IceCreamMaker;
import com.chef.mod.container.ContainerIceCreamMaker;
import com.chef.mod.crafting.IceCreamMakerRecipes;
import com.chef.mod.init.MyItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileEntityIceCreamMaker
extends TileEntity
implements ISidedInventory {
    private static final int[] slotsTop = new int[]{0, 1};
    private static final int[] slotsBottom = new int[]{3};
    private static final int[] slotsSides = new int[]{2};
    private ItemStack[] slots = new ItemStack[4];
    public int ice;
    public int cookTime;
    public static final int maxIce = 10000;
    public static final int iceCreamMakerSpeed = 125;
    private String customName;
    public int scaledIceLevel;

    public int getSizeInventory() {
        return this.slots.length;
    }

    public ItemStack getStackInSlot(int index) {
        return this.slots[index];
    }

    public ItemStack decrStackSize(int index, int count) {
        if (this.slots[index] != null) {
            if (this.slots[index].stackSize <= count) {
                ItemStack itemstack = this.slots[index];
                this.slots[index] = null;
                return itemstack;
            }
            ItemStack itemstack = this.slots[index].splitStack(count);
            if (this.slots[index].stackSize == 0) {
                this.slots[index] = null;
            }
            return itemstack;
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(int index) {
        if (this.slots[index] != null) {
            ItemStack itemstack = this.slots[index];
            this.slots[index] = null;
            return itemstack;
        }
        return null;
    }

    public void setInventorySlotContents(int i, ItemStack itemstack) {
        this.slots[i] = itemstack;
        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
    }

    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.slots = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");
            if (b0 < 0 || b0 >= this.slots.length) continue;
            this.slots[b0] = ItemStack.loadItemStackFromNBT((NBTTagCompound)nbttagcompound1);
        }
        this.ice = compound.getShort("PowerTime");
        this.cookTime = compound.getShort("CookTime");
        if (compound.hasKey("CustomName", 8)) {
            this.customName = compound.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setShort("PowerTime", (short)this.ice);
        compound.setShort("CookTime", (short)this.cookTime);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.slots.length; ++i) {
            if (this.slots[i] == null) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setByte("Slot", (byte)i);
            this.slots[i].writeToNBT(nbttagcompound1);
            nbttaglist.appendTag((NBTBase)nbttagcompound1);
        }
        compound.setTag("Items", (NBTBase)nbttaglist);
        if (this.hasCustomInventoryName()) {
            compound.setString("CustomName", this.customName);
        }
    }

    public void readFromNBTForSync(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.ice = compound.getShort("IceTime");
    }

    public void writeToNBTForSync(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setShort("IceTime", (short)this.ice);
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound tagCompound = new NBTTagCompound();
        this.writeToNBTForSync(tagCompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tagCompound);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound tag = pkt.func_148857_g();
        this.readFromNBTForSync(tag);
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean hasIce() {
        return this.ice > 0;
    }

    public boolean isIcing() {
        return this.cookTime > 0;
    }

    public void updateEntity() {
        boolean flag = this.hasIce();
        boolean flag1 = false;
        this.scaledIceLevel = Math.round(this.ice / 1000);
        if (this.hasIce() && this.isIcing()) {
            --this.ice;
        }
        if (!this.worldObj.isRemote) {
            if (TileEntityIceCreamMaker.hasItemIceTime(this.slots[2])) {
                if (this.ice <= 10000 - TileEntityIceCreamMaker.getItemIceTime(this.slots[2])) {
                    this.ice += TileEntityIceCreamMaker.getItemIceTime(this.slots[2]);
                    if (this.slots[2] != null) {
                        flag1 = true;
                        --this.slots[2].stackSize;
                        if (this.slots[2].stackSize == 0) {
                            this.slots[2] = this.slots[2].getItem().getContainerItem(this.slots[2]);
                        }
                    }
                }
            }
            flag1 = true;
            IceCreamMaker.updateBlockState(this.isIcing(), this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            if (this.hasIce() && this.canIce()) {
                ++this.cookTime;
                if (this.cookTime == 125) {
                    this.cookTime = 1;
                    this.iceItem();
                    flag1 = true;
                }
            } else {
                this.cookTime = 0;
            }
        }
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        this.markDirty();
        if (flag1) {
            this.markDirty();
        }
    }

    private boolean canIce() {
        if (this.slots[0] == null || this.slots[1] == null) {
            return false;
        }
        ItemStack itemstack = IceCreamMakerRecipes.getIceCreamResult(this.slots[0].getItem(), this.slots[1].getItem());
        if (itemstack == null) {
            return false;
        }
        if (this.slots[3] == null) {
            return true;
        }
        if (!this.slots[3].isItemEqual(itemstack)) {
            return false;
        }
        int result = this.slots[3].stackSize + itemstack.stackSize;
        return result <= this.getInventoryStackLimit() && result <= itemstack.getMaxStackSize();
    }

    public void iceItem() {
        if (this.canIce()) {
            ItemStack itemstack = IceCreamMakerRecipes.getIceCreamResult(this.slots[0].getItem(), this.slots[1].getItem());
            if (this.slots[3] == null) {
                this.slots[3] = itemstack.copy();
            } else if (this.slots[3].getItem() == itemstack.getItem()) {
                this.slots[3].stackSize += itemstack.stackSize;
            }
            for (int i = 0; i < 2; ++i) {
                if (this.slots[i].stackSize <= 0) {
                    this.slots[i] = new ItemStack(this.slots[i].getItem().setFull3D());
                } else {
                    --this.slots[i].stackSize;
                }
                if (this.slots[i].stackSize > 0) continue;
                this.slots[i] = null;
            }
        }
    }

    public static int getItemIceTime(ItemStack itemstack) {
        if (itemstack == null) {
            return 0;
        }
        Item item = itemstack.getItem();
        if (item == MyItems.ice_shard) {
            return 50;
        }
        return 0;
    }

    public static boolean hasItemIceTime(ItemStack itemstack) {
        return TileEntityIceCreamMaker.getItemIceTime(itemstack) > 0;
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
    }

    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return i == 2 ? false : (i == 1 ? TileEntityIceCreamMaker.hasItemIceTime(itemstack) : true);
    }

    public int[] getSlotsForFace(EnumFacing side) {
        return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
    }

    public int getIceMakerProgressScaled(int i) {
        return this.cookTime * i / 125;
    }

    public int getIceRemainingScaled(int i) {
        return this.ice * i / 10000;
    }

    public String getGuiID() {
        return "chef:iceCreamMaker";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerIceCreamMaker(playerInventory, this);
    }

    public int getField(int id) {
        switch (id) {
            case 0: {
                return this.ice;
            }
            case 1: {
                return this.cookTime;
            }
        }
        return 0;
    }

    public void setField(int id, int value) {
        switch (id) {
            case 0: {
                this.ice = value;
                break;
            }
            case 1: {
                this.cookTime = value;
            }
        }
    }

    public int getFieldCount() {
        return 3;
    }

    public void clear() {
        for (int i = 0; i < this.slots.length; ++i) {
            this.slots[i] = null;
        }
    }

    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.customName : "container.iceCreamMaker";
    }

    public boolean hasCustomInventoryName() {
        return this.customName != null && this.customName.length() > 0;
    }

    public void openInventory() {
    }

    public void closeInventory() {
    }

    public int[] getAccessibleSlotsFromSide(int i) {
        if (i == 0) {
            return slotsBottom;
        }
        if (i == 1) {
            return slotsTop;
        }
        return slotsSides;
    }

    public boolean canInsertItem(int i, ItemStack itemstack, int j) {
        return this.isItemValidForSlot(i, itemstack);
    }

    public boolean canExtractItem(int i, ItemStack itemstack, int j) {
        return j != 0 || i != 1 || itemstack.getItem() == Items.bucket;
    }
}

