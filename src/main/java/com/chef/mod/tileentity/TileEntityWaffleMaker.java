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

import com.chef.mod.blocks.WaffleMaker;
import com.chef.mod.container.ContainerWaffleMaker;
import com.chef.mod.crafting.WaffleMakerRecipes;
import com.chef.mod.init.MyItems;
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

public class TileEntityWaffleMaker
extends TileEntity
implements ISidedInventory {
    private static final int[] slotsTop = new int[]{0, 1};
    private static final int[] slotsBottom = new int[]{3};
    private static final int[] slotsSides = new int[]{2};
    private ItemStack[] slots = new ItemStack[9];
    public int bakeItem = 0;
    public int burnTime;
    public int currentItemBurnTime;
    public int bakeTime;
    public int currentItemBakeTime;
    public int firstCookTime;
    public int secondCookTime;
    public int firstDelayTime = 0;
    public int secondDelayTime = 0;
    public static final int waffleMakerSpeed = 350;
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
        this.bakeItem = compound.getShort("BakeItem");
        this.burnTime = compound.getShort("BurnTime");
        this.bakeTime = compound.getShort("BakeTime");
        this.currentItemBurnTime = TileEntityWaffleMaker.getItemBurnTime(this.slots[4]);
        this.currentItemBakeTime = TileEntityWaffleMaker.getItemBakeTime(this.slots[5]);
        this.readSyncableDataFromNBT(compound);
        if (compound.hasKey("CustomName", 8)) {
            this.customName = compound.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setShort("BakeItem", (short)this.bakeItem);
        compound.setShort("BurnTime", (short)this.burnTime);
        compound.setShort("BakeTime", (short)this.bakeTime);
        this.writeSyncableDataToNBT(compound);
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

    public void readSyncableDataFromNBT(NBTTagCompound compound) {
        this.firstCookTime = compound.getShort("FirstCookTime");
        this.secondCookTime = compound.getShort("SecondCookTime");
    }

    public void writeSyncableDataToNBT(NBTTagCompound compound) {
        compound.setShort("FirstCookTime", (short)this.firstCookTime);
        compound.setShort("SecondCookTime", (short)this.secondCookTime);
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound tagCompound = new NBTTagCompound();
        this.writeSyncableDataToNBT(tagCompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tagCompound);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound tag = pkt.func_148857_g();
        this.readSyncableDataFromNBT(tag);
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isBurning() {
        return this.burnTime > 0;
    }

    public boolean isBaking() {
        return this.bakeTime > 0;
    }

    public int getWaffleStage(int index) {
        if (index == 1) {
            if (this.firstCookTime > 0 && this.firstCookTime <= 100) {
                return 1;
            }
            if (this.firstCookTime > 100 && this.firstCookTime <= 250) {
                return 2;
            }
            if (this.firstCookTime > 250 && this.firstCookTime <= 350) {
                return 3;
            }
            return 0;
        }
        if (index == 2) {
            if (this.secondCookTime > 0 && this.secondCookTime <= 100) {
                return 1;
            }
            if (this.secondCookTime > 100 && this.secondCookTime <= 250) {
                return 2;
            }
            if (this.secondCookTime > 250 && this.secondCookTime <= 350) {
                return 3;
            }
            return 0;
        }
        System.out.println("Wrong index, there is no " + index + " waffle");
        return -1;
    }

    public boolean isFirstCooking() {
        return this.firstCookTime > 0;
    }

    public boolean isSecondCooking() {
        return this.secondCookTime > 0;
    }

    public void updateEntity() {
        boolean flag = this.burnTime > 0;
        boolean flag1 = false;
        if (this.isBurning()) {
            --this.burnTime;
        }
        if (this.canFirstBake() | this.canSecondBake() && this.isBaking() && this.isBurning()) {
            --this.bakeTime;
        }
        if (!this.worldObj.isRemote) {
            if (this.burnTime == 0 && this.isBaking() && (this.hasWaffleOn(1) || this.hasWaffleOn(2) || this.canFirstBake() || this.canSecondBake())) {
                this.currentItemBurnTime = this.burnTime = TileEntityWaffleMaker.getItemBurnTime(this.slots[4]);
                if (this.isBurning()) {
                    flag1 = true;
                    if (this.slots[4] != null) {
                        --this.slots[4].stackSize;
                        if (this.slots[4].stackSize == 0) {
                            this.slots[4] = this.slots[4].getItem().getContainerItem(this.slots[4]);
                        }
                    }
                }
            }
            if (this.bakeTime == 0) {
                if (this.bakeItem == 1 || this.bakeItem == 2) {
                    this.giveEmptyBottle();
                    this.bakeItem = 0;
                }
                if (this.canFirstBake() || this.canSecondBake()) {
                    switch (TileEntityWaffleMaker.getBakeItem(this.slots[5])) {
                        case 1: {
                            this.bakeItem = 1;
                            break;
                        }
                        case 2: {
                            this.bakeItem = 2;
                            break;
                        }
                        case 3: {
                            this.bakeItem = 3;
                            break;
                        }
                        default: {
                            this.bakeItem = 0;
                        }
                    }
                    this.currentItemBakeTime = this.bakeTime = TileEntityWaffleMaker.getItemBakeTime(this.slots[5]);
                    if (this.isBaking()) {
                        flag1 = true;
                        if (this.slots[5] != null) {
                            --this.slots[5].stackSize;
                            if (this.slots[5].stackSize == 0) {
                                this.slots[5] = this.slots[5].getItem().getContainerItem(this.slots[5]);
                            }
                        }
                    }
                }
            }
            if (!this.canFirstBake() && !this.hasWaffleOn(1)) {
                this.firstDelayTime = 0;
                this.firstCookTime = 0;
            }
            if (this.isBurning() && this.isBaking() && this.canFirstBake()) {
                if (this.firstCookTime - this.firstDelayTime <= 0) {
                    ++this.firstCookTime;
                }
                if (this.firstCookTime - this.firstDelayTime == 1) {
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
            if (this.isBurning() && this.isBaking() && this.hasWaffleOn(1)) {
                ++this.firstCookTime;
                if (this.firstCookTime == 350) {
                    this.firstDelayTime = 20;
                    this.firstCookTime = 0 - this.firstDelayTime;
                    this.giveFirstBowl();
                    this.bakeFirstWaffle();
                    flag1 = true;
                }
            }
            if (!this.canSecondBake() && !this.hasWaffleOn(2)) {
                this.secondDelayTime = 0;
                this.secondCookTime = 0;
            }
            if (this.isBurning() && this.isBaking() && this.canSecondBake()) {
                if (this.secondCookTime - this.secondDelayTime <= 0) {
                    ++this.secondCookTime;
                }
                if (this.secondCookTime - this.secondDelayTime == 1) {
                    if (this.slots[1].stackSize <= 0) {
                        this.slots[1] = new ItemStack(this.slots[1].getItem().setFull3D());
                    } else {
                        --this.slots[1].stackSize;
                    }
                    if (this.slots[1].stackSize <= 0) {
                        this.slots[1] = null;
                    }
                }
            }
            if (this.isBurning() && this.isBaking() && this.hasWaffleOn(2)) {
                ++this.secondCookTime;
                if (this.secondCookTime == 350) {
                    this.secondDelayTime = 20;
                    this.secondCookTime = 0 - this.secondDelayTime;
                    this.giveSecondBowl();
                    this.bakeSecondWaffle();
                    flag1 = true;
                }
            }
            if (flag != this.isBurning()) {
                flag1 = true;
                WaffleMaker.updateBlockState(this.isBurning(), this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        this.markDirty();
        if (flag1) {
            this.markDirty();
        }
    }

    private boolean hasWaffleOn(int index) {
        if (index == 1) {
            return this.firstCookTime > 0;
        }
        if (index == 2) {
            return this.secondCookTime > 0;
        }
        System.out.println("No such index exists");
        return false;
    }

    private boolean containsGreese() {
        ItemStack itemstack = this.slots[5];
        return itemstack != null && (itemstack.getItem() == MyItems.butter || itemstack.getItem() == MyItems.fish_oil || itemstack.getItem() == MyItems.olive_oil);
    }

    private void giveEmptyBottle() {
        ItemStack itemstack = new ItemStack(Items.glass_bottle, 1);
        if (this.slots[8] == null) {
            this.slots[8] = itemstack.copy();
        } else if (this.slots[8].getItem() == itemstack.getItem()) {
            this.slots[8].stackSize += itemstack.stackSize;
        }
    }

    public static int getBakeItem(ItemStack itemstack) {
        if (itemstack == null) {
            return 0;
        }
        Item item = itemstack.getItem();
        if (item == MyItems.fish_oil) {
            return 1;
        }
        if (item == MyItems.olive_oil) {
            return 2;
        }
        if (item == MyItems.butter) {
            return 3;
        }
        return 0;
    }

    public void giveFirstBowl() {
        ItemStack itemstack = new ItemStack(Items.bowl, 1);
        if (this.slots[6] == null) {
            this.slots[6] = itemstack.copy();
        } else if (this.slots[6].getItem() == itemstack.getItem()) {
            this.slots[6].stackSize += itemstack.stackSize;
        }
    }

    public void giveSecondBowl() {
        ItemStack itemstack = new ItemStack(Items.bowl, 1);
        if (this.slots[7] == null) {
            this.slots[7] = itemstack.copy();
        } else if (this.slots[7].getItem() == itemstack.getItem()) {
            this.slots[7].stackSize += itemstack.stackSize;
        }
    }

    private boolean canFirstBake() {
        if (this.slots[0] == null) {
            return false;
        }
        ItemStack itemstack = WaffleMakerRecipes.getBakingResult(this.slots[0].getItem());
        if (itemstack == null) {
            return false;
        }
        if (this.slots[2] == null) {
            return true;
        }
        if (!this.slots[2].isItemEqual(itemstack)) {
            return false;
        }
        int result = this.slots[2].stackSize + itemstack.stackSize;
        return result <= this.getInventoryStackLimit() && result <= itemstack.getMaxStackSize();
    }

    private boolean canSecondBake() {
        if (this.slots[1] == null) {
            return false;
        }
        ItemStack itemstack = WaffleMakerRecipes.getBakingResult(this.slots[1].getItem());
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

    public void bakeFirstWaffle() {
        ItemStack itemstack = new ItemStack(MyItems.waffle);
        if (this.slots[2] == null) {
            this.slots[2] = itemstack.copy();
        } else if (this.slots[2].getItem() == itemstack.getItem()) {
            this.slots[2].stackSize += itemstack.stackSize;
        }
    }

    public void bakeSecondWaffle() {
        ItemStack itemstack = new ItemStack(MyItems.waffle);
        if (this.slots[3] == null) {
            this.slots[3] = itemstack.copy();
        } else if (this.slots[3].getItem() == itemstack.getItem()) {
            this.slots[3].stackSize += itemstack.stackSize;
        }
    }

    public static int getItemBakeTime(ItemStack itemstack) {
        if (itemstack == null) {
            return 0;
        }
        Item item = itemstack.getItem();
        if (item == MyItems.fish_oil) {
            return 1100;
        }
        if (item == MyItems.olive_oil) {
            return 800;
        }
        if (item == MyItems.butter) {
            return 700;
        }
        return GameRegistry.getFuelValue((ItemStack)itemstack);
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
        return TileEntityWaffleMaker.getItemBurnTime(itemstack) > 0;
    }

    public static boolean isItemGreese(ItemStack itemstack) {
        return TileEntityWaffleMaker.getItemBakeTime(itemstack) > 0;
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
    }

    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return i == 2 ? false : (i == 1 ? TileEntityWaffleMaker.isItemFuel(itemstack) : true);
    }

    public int[] getSlotsForFace(EnumFacing side) {
        return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
    }

    public int getBurnTimeRemainingScaled(int i) {
        if (this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = 350;
        }
        return this.burnTime * i / this.currentItemBurnTime;
    }

    public int getBakeTimeRemainingScaled(int i) {
        if (this.currentItemBakeTime == 0) {
            this.currentItemBakeTime = 350;
        }
        return this.bakeTime * i / this.currentItemBakeTime;
    }

    public int getCookProgressScaled(int i) {
        return this.firstCookTime * i / 350;
    }

    public String getGuiID() {
        return "chef:waffleMaker";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerWaffleMaker(playerInventory, this);
    }

    public void clear() {
        for (int i = 0; i < this.slots.length; ++i) {
            this.slots[i] = null;
        }
    }

    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.customName : "container.waffleMaker";
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

