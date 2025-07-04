/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemHoe
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.item.ItemTool
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

import com.chef.mod.blocks.Boiler;
import com.chef.mod.container.ContainerBoiler;
import com.chef.mod.crafting.BoilerRecipes;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileEntityBoiler
extends TileEntity
implements ISidedInventory {
    private static final int[] slotsTop = new int[]{0, 1};
    private static final int[] slotsBottom = new int[]{3};
    private static final int[] slotsSides = new int[]{2};
    private ItemStack[] slots = new ItemStack[4];
    public int burnTime;
    public int currentItemBurnTime;
    public int heath;
    public int water;
    public int cookTime;
    public int bubbleTime;
    public int boilerSpeed = 1350;
    public static final int maxWater = 40000;
    private String customName;

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
        this.burnTime = compound.getShort("BurnTime");
        this.cookTime = compound.getShort("CookTime");
        this.heath = compound.getShort("Heath1");
        this.water = compound.getInteger("Water");
        this.currentItemBurnTime = TileEntityBoiler.getItemBurnTime(this.slots[1]);
        if (compound.hasKey("CustomName", 8)) {
            this.customName = compound.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setShort("BurnTime", (short)this.burnTime);
        compound.setShort("CookTime", (short)this.cookTime);
        compound.setShort("Heath1", (short)this.heath);
        compound.setInteger("Water", this.water);
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
        this.heath = compound.getShort("Heath2");
    }

    public void writeToNBTForSync(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setShort("Heath2", (short)this.heath);
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

    public boolean isBurning() {
        return this.burnTime > 0;
    }

    public boolean hasWater() {
        return this.water > 0;
    }

    public boolean isWarm() {
        return this.heath >= 300;
    }

    public boolean isHot() {
        return this.heath >= 600;
    }

    public boolean isBoiling() {
        return this.heath >= 1200;
    }

    public boolean isHeating() {
        return this.isBurning() && !this.isBoiling() && this.hasWater();
    }

    public boolean isCooling() {
        return !this.isBurning() && this.hasWater() && this.heath > 0;
    }

    public boolean isEvaporating() {
        return this.hasWater() && this.isBurning() && this.isHot();
    }

    public void updateEntity() {
        boolean flag = this.burnTime > 0;
        boolean flag2 = this.water > 0;
        boolean flag3 = false;
        if (this.isBurning()) {
            --this.burnTime;
        }
        if (this.isEvaporating()) {
            this.water -= Math.round(this.heath / 400);
        }
        if (!this.worldObj.isRemote) {
            if (!this.isBurning() && this.hasWater()) {
                this.currentItemBurnTime = this.burnTime = TileEntityBoiler.getItemBurnTime(this.slots[1]);
                if (this.isBurning()) {
                    flag3 = true;
                    if (this.slots[1] != null) {
                        --this.slots[1].stackSize;
                        if (this.slots[1].stackSize == 0) {
                            this.slots[1] = this.slots[1].getItem().getContainerItem(this.slots[1]);
                        }
                    }
                }
            }
            if (this.isCooling()) {
                if (Math.round(this.water / 10000) <= 0) {
                    --this.heath;
                } else if (this.worldObj.rand.nextInt(Math.round(this.water / 10000)) == 0) {
                    --this.heath;
                }
            }
            if (this.isHeating()) {
                if (Math.round(this.water / 5000) <= 0) {
                    ++this.heath;
                } else if (this.worldObj.rand.nextInt(Math.round(this.water / 5000)) == 0) {
                    ++this.heath;
                }
            }
            if (!this.hasWater()) {
                this.heath = 0;
            }
            if (TileEntityBoiler.isItemWater(this.slots[2])) {
                this.water += TileEntityBoiler.getItemWater(this.slots[2]);
                if (this.water > 40000) {
                    this.water = 40000;
                }
                if (this.heath < 0) {
                    this.heath = 0;
                }
                if (this.heath > 0) {
                    this.heath -= 300;
                }
                if (this.slots[2] != null) {
                    flag3 = true;
                    --this.slots[2].stackSize;
                    if (this.slots[2].stackSize == 0) {
                        this.slots[2] = this.slots[2].getItem().getContainerItem(this.slots[2]);
                    }
                }
            }
            if (this.isHot() && this.canBoil()) {
                ++this.cookTime;
                ++this.bubbleTime;
                if (this.cookTime >= this.boilerSpeed - this.heath) {
                    this.cookTime = 0;
                    this.boilItem();
                    flag3 = true;
                }
                if (this.bubbleTime == 20) {
                    this.bubbleTime = 0;
                }
            } else {
                this.cookTime = 0;
                this.bubbleTime = 0;
            }
            Boiler.updateBlockState(this.hasWater(), this.isBurning(), this.worldObj, this.xCoord, this.yCoord, this.zCoord);
        }
        if (flag3) {
            this.markDirty();
        }
    }

    private boolean canBoil() {
        if (this.slots[0] == null) {
            return false;
        }
        ItemStack itemstack = BoilerRecipes.getBoilingResult(this.slots[0].getItem());
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
        return result <= this.getInventoryStackLimit() && result <= this.slots[3].getMaxStackSize();
    }

    public void boilItem() {
        if (this.canBoil()) {
            ItemStack itemstack = BoilerRecipes.getBoilingResult(this.slots[0].getItem());
            if (this.slots[3] == null) {
                this.slots[3] = itemstack.copy();
            } else if (this.slots[3].getItem() == itemstack.getItem()) {
                this.slots[3].stackSize += itemstack.stackSize;
            }
            if (this.slots[0].stackSize <= 0) {
                this.slots[0] = new ItemStack(this.slots[0].getItem().setFull3D());
            } else {
                --this.slots[0].stackSize;
            }
            if (this.slots[0].stackSize <= 0) {
                this.slots[0] = null;
            }
        }
    }

    public static int getItemBurnTime(ItemStack itemstack) {
        if (itemstack == null) {
            return 0;
        }
        Item item = itemstack.getItem();
        if (item instanceof ItemBlock && Block.getBlockFromItem((Item)item) != Blocks.air) {
            Block block = Block.getBlockFromItem((Item)item);
            if (block == Blocks.wooden_slab) {
                return 150;
            }
            if (block.getMaterial() == Material.wood) {
                return 300;
            }
            if (block == Blocks.coal_block) {
                return 16000;
            }
        }
        if (item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) {
            return 200;
        }
        if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) {
            return 200;
        }
        if (item instanceof ItemHoe && ((ItemHoe)item).getToolMaterialName().equals("WOOD")) {
            return 200;
        }
        if (item == Items.stick) {
            return 100;
        }
        if (item == Items.coal) {
            return 1600;
        }
        if (item == Items.lava_bucket) {
            return 20000;
        }
        if (item == Item.getItemFromBlock((Block)Blocks.sapling)) {
            return 100;
        }
        if (item == Items.blaze_rod) {
            return 2400;
        }
        return GameRegistry.getFuelValue((ItemStack)itemstack);
    }

    public static boolean isItemFuel(ItemStack itemstack) {
        return TileEntityBoiler.getItemBurnTime(itemstack) > 0;
    }

    public static int getItemWater(ItemStack itemstack) {
        if (itemstack == null) {
            return 0;
        }
        Item item = itemstack.getItem();
        if (item == Items.water_bucket) {
            return 10000;
        }
        return GameRegistry.getFuelValue((ItemStack)itemstack);
    }

    public static boolean isItemWater(ItemStack itemstack) {
        return TileEntityBoiler.getItemWater(itemstack) > 0;
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
    }

    public void openInventory(EntityPlayer player) {
    }

    public void closeInventory(EntityPlayer player) {
    }

    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return i == 2 ? false : (i == 1 ? TileEntityBoiler.isItemFuel(itemstack) : true);
    }

    public int[] getSlotsForFace(EnumFacing side) {
        return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
    }

    public int getBurnTimeRemainingScaled(int i) {
        if (this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = this.boilerSpeed;
        }
        return this.burnTime * i / this.currentItemBurnTime;
    }

    public int getWaterRemainingScaled(int i) {
        return this.water * i / 40000;
    }

    public int getCookProgressScaled(int i) {
        return this.cookTime * i / (this.boilerSpeed - this.heath);
    }

    public int getBubbleProgressScaled(int i) {
        return this.bubbleTime * i / 10;
    }

    public String getGuiID() {
        return "chef:boiler";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerBoiler(playerInventory, this);
    }

    public void clear() {
        for (int i = 0; i < this.slots.length; ++i) {
            this.slots[i] = null;
        }
    }

    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.customName : "container.boiler";
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

