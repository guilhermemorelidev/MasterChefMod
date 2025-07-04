/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGlass
 *  net.minecraft.block.BlockStainedGlass
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
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumFacing
 */
package com.chef.mod.tileentity;

import com.chef.mod.blocks.Dehydrator;
import com.chef.mod.container.ContainerDehydrator;
import com.chef.mod.crafting.DehydratorRecipes;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockStainedGlass;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileEntityDehydrator
extends TileEntity
implements ISidedInventory {
    private static final int[] slotsTop = new int[]{0, 1};
    private static final int[] slotsBottom = new int[]{3};
    private static final int[] slotsSides = new int[]{2};
    private ItemStack[] slots = new ItemStack[9];
    public int burnTime;
    public int currentItemBurnTime;
    public int firstCookTime;
    public int secondCookTime;
    public int thirdCookTime;
    public int fourthCookTime;
    public int extraSpeed = 0;
    public boolean hasExtraBurnSpeed;
    public static final int dehydratorSpeed = 3000;
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
        this.firstCookTime = compound.getShort("FirstCookTime");
        this.secondCookTime = compound.getShort("SecondCookTime");
        this.thirdCookTime = compound.getShort("ThirdCookTime");
        this.fourthCookTime = compound.getShort("FourthCookTime");
        this.currentItemBurnTime = TileEntityDehydrator.getItemBurnTime(this.slots[8]);
        if (compound.hasKey("CustomName", 8)) {
            this.customName = compound.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setShort("BurnTime", (short)this.burnTime);
        compound.setShort("FirstCookTime", (short)this.firstCookTime);
        compound.setShort("SecondCookTime", (short)this.secondCookTime);
        compound.setShort("ThirdCookTime", (short)this.secondCookTime);
        compound.setShort("FourthCookTime", (short)this.fourthCookTime);
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

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isBurning() {
        return this.burnTime > 0;
    }

    public void updateEntity() {
        boolean flag = this.burnTime > 0;
        boolean flag1 = false;
        this.extraSpeed = this.calculateExtraSpeed();
        if (this.isBurning()) {
            --this.burnTime;
            this.hasExtraBurnSpeed = true;
        }
        if (!this.worldObj.isRemote) {
            if (this.burnTime == 0 && this.canFirstBake() | this.canSecondBake() | this.canThirdBake() | this.canFourthBake()) {
                this.currentItemBurnTime = this.burnTime = TileEntityDehydrator.getItemBurnTime(this.slots[8]);
                if (this.isBurning()) {
                    flag1 = true;
                    if (this.slots[8] != null) {
                        --this.slots[8].stackSize;
                        if (this.slots[8].stackSize == 0) {
                            this.slots[8] = this.slots[8].getItem().getContainerItem(this.slots[8]);
                        }
                    }
                }
            }
            if (this.canFirstBake()) {
                this.firstCookTime = this.isBurning() ? (this.firstCookTime += 3 + this.extraSpeed) : (this.firstCookTime += 1 + this.extraSpeed);
                if (this.firstCookTime >= 3000) {
                    this.bakeFirstItem();
                    this.firstCookTime = 0;
                    flag1 = true;
                }
                if (this.firstCookTime < 0) {
                    this.firstCookTime = 0;
                }
            } else {
                this.firstCookTime = 0;
            }
            if (this.canSecondBake()) {
                this.secondCookTime = this.isBurning() ? (this.secondCookTime += 3 + this.extraSpeed) : (this.secondCookTime += 1 + this.extraSpeed);
                if (this.secondCookTime >= 3000) {
                    this.bakeSecondItem();
                    this.secondCookTime = 0;
                    flag1 = true;
                }
                if (this.secondCookTime < 0) {
                    this.secondCookTime = 0;
                }
            } else {
                this.secondCookTime = 0;
            }
            if (this.canThirdBake()) {
                this.thirdCookTime = this.isBurning() ? (this.thirdCookTime += 3 + this.extraSpeed) : (this.thirdCookTime += 1 + this.extraSpeed);
                if (this.thirdCookTime >= 3000) {
                    this.bakeThirdItem();
                    this.thirdCookTime = 0;
                    flag1 = true;
                }
                if (this.thirdCookTime < 0) {
                    this.thirdCookTime = 0;
                }
            } else {
                this.thirdCookTime = 0;
            }
            if (this.canFourthBake()) {
                this.fourthCookTime = this.isBurning() ? (this.fourthCookTime += 3 + this.extraSpeed) : (this.fourthCookTime += 1 + this.extraSpeed);
                if (this.fourthCookTime >= 3000) {
                    this.bakeFourthItem();
                    this.fourthCookTime = 0;
                    flag1 = true;
                }
                if (this.fourthCookTime < 0) {
                    this.fourthCookTime = 0;
                }
            } else {
                this.fourthCookTime = 0;
            }
            if (flag != this.isBurning()) {
                flag1 = true;
                Dehydrator.updateBlockState(this.burnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }
        if (flag1) {
            this.markDirty();
        }
    }

    private int calculateExtraSpeed() {
        int extraSpeed = 0;
        boolean glassCover = false;
        if (this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord)) {
            if (this.worldObj.isRaining()) {
                for (int i = 1; i < 300; ++i) {
                    Block block = this.worldObj.getBlock(this.xCoord, this.yCoord + i, this.zCoord);
                    if (block == Blocks.air || !(block instanceof BlockGlass) && !(block instanceof BlockStainedGlass)) continue;
                    glassCover = true;
                }
                if (!glassCover) {
                    extraSpeed -= 2;
                }
            } else if (this.worldObj.isDaytime()) {
                ++extraSpeed;
            }
        }
        return extraSpeed;
    }

    private boolean canFirstBake() {
        if (this.slots[0] == null) {
            return false;
        }
        ItemStack itemstack = DehydratorRecipes.getDryingResult(this.slots[0].getItem());
        if (itemstack == null) {
            return false;
        }
        if (this.slots[4] == null) {
            return true;
        }
        if (!this.slots[4].isItemEqual(itemstack)) {
            return false;
        }
        int result = this.slots[4].stackSize + itemstack.stackSize;
        return result <= this.getInventoryStackLimit() && result <= itemstack.getMaxStackSize();
    }

    private boolean canSecondBake() {
        if (this.slots[1] == null) {
            return false;
        }
        ItemStack itemstack = DehydratorRecipes.getDryingResult(this.slots[1].getItem());
        if (itemstack == null) {
            return false;
        }
        if (this.slots[5] == null) {
            return true;
        }
        if (!this.slots[5].isItemEqual(itemstack)) {
            return false;
        }
        int result = this.slots[5].stackSize + itemstack.stackSize;
        return result <= this.getInventoryStackLimit() && result <= itemstack.getMaxStackSize();
    }

    private boolean canThirdBake() {
        if (this.slots[2] == null) {
            return false;
        }
        ItemStack itemstack = DehydratorRecipes.getDryingResult(this.slots[2].getItem());
        if (itemstack == null) {
            return false;
        }
        if (this.slots[6] == null) {
            return true;
        }
        if (!this.slots[6].isItemEqual(itemstack)) {
            return false;
        }
        int result = this.slots[6].stackSize + itemstack.stackSize;
        return result <= this.getInventoryStackLimit() && result <= itemstack.getMaxStackSize();
    }

    private boolean canFourthBake() {
        if (this.slots[3] == null) {
            return false;
        }
        ItemStack itemstack = DehydratorRecipes.getDryingResult(this.slots[3].getItem());
        if (itemstack == null) {
            return false;
        }
        if (this.slots[7] == null) {
            return true;
        }
        if (!this.slots[7].isItemEqual(itemstack)) {
            return false;
        }
        int result = this.slots[7].stackSize + itemstack.stackSize;
        return result <= this.getInventoryStackLimit() && result <= itemstack.getMaxStackSize();
    }

    public void bakeFirstItem() {
        if (this.canFirstBake()) {
            ItemStack itemstack = DehydratorRecipes.getDryingResult(this.slots[0].getItem());
            if (this.slots[4] == null) {
                this.slots[4] = itemstack.copy();
            } else if (this.slots[4].getItem() == itemstack.getItem()) {
                this.slots[4].stackSize += itemstack.stackSize;
            }
            if (this.slots[0].stackSize <= 0) {
                this.slots[0] = new ItemStack(this.slots[0].getItem().setFull3D());
            } else if (this.slots[0].getItem().getContainerItem() == null) {
                --this.slots[0].stackSize;
            } else {
                this.slots[0] = this.slots[0].getItem().getContainerItem(this.slots[0]);
            }
            if (this.slots[0].stackSize <= 0) {
                this.slots[0] = null;
            }
        }
    }

    public void bakeSecondItem() {
        if (this.canSecondBake()) {
            ItemStack itemstack = DehydratorRecipes.getDryingResult(this.slots[1].getItem());
            if (this.slots[5] == null) {
                this.slots[5] = itemstack.copy();
            } else if (this.slots[5].getItem() == itemstack.getItem()) {
                this.slots[5].stackSize += itemstack.stackSize;
            }
            if (this.slots[1].stackSize <= 0) {
                this.slots[1] = new ItemStack(this.slots[1].getItem().setFull3D());
            } else if (this.slots[1].getItem().getContainerItem() == null) {
                --this.slots[1].stackSize;
            } else {
                this.slots[1] = this.slots[1].getItem().getContainerItem(this.slots[1]);
            }
            if (this.slots[1].stackSize <= 0) {
                this.slots[1] = null;
            }
        }
    }

    public void bakeThirdItem() {
        if (this.canThirdBake()) {
            ItemStack itemstack = DehydratorRecipes.getDryingResult(this.slots[2].getItem());
            if (this.slots[6] == null) {
                this.slots[6] = itemstack.copy();
            } else if (this.slots[6].getItem() == itemstack.getItem()) {
                this.slots[6].stackSize += itemstack.stackSize;
            }
            if (this.slots[2].stackSize <= 0) {
                this.slots[2] = new ItemStack(this.slots[2].getItem().setFull3D());
            } else if (this.slots[2].getItem().getContainerItem() == null) {
                --this.slots[2].stackSize;
            } else {
                this.slots[2] = this.slots[2].getItem().getContainerItem(this.slots[2]);
            }
            if (this.slots[2].stackSize <= 0) {
                this.slots[2] = null;
            }
        }
    }

    public void bakeFourthItem() {
        if (this.canFourthBake()) {
            ItemStack itemstack = DehydratorRecipes.getDryingResult(this.slots[3].getItem());
            if (this.slots[7] == null) {
                this.slots[7] = itemstack.copy();
            } else if (this.slots[7].getItem() == itemstack.getItem()) {
                this.slots[7].stackSize += itemstack.stackSize;
            }
            if (this.slots[3].stackSize <= 0) {
                this.slots[3] = new ItemStack(this.slots[3].getItem().setFull3D());
            } else if (this.slots[3].getItem().getContainerItem() == null) {
                --this.slots[3].stackSize;
            } else {
                this.slots[3] = this.slots[3].getItem().getContainerItem(this.slots[3]);
            }
            if (this.slots[3].stackSize <= 0) {
                this.slots[3] = null;
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
        return TileEntityDehydrator.getItemBurnTime(itemstack) > 0;
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
    }

    public void openInventory(EntityPlayer player) {
    }

    public void closeInventory(EntityPlayer player) {
    }

    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return i == 2 ? false : (i == 1 ? TileEntityDehydrator.isItemFuel(itemstack) : true);
    }

    public int[] getSlotsForFace(EnumFacing side) {
        return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
    }

    public int getBurnTimeRemainingScaled(int i) {
        if (this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = 3000;
        }
        return this.burnTime * i / this.currentItemBurnTime;
    }

    public int getFirstCookProgressScaled(int i) {
        return this.firstCookTime * i / 3000;
    }

    public int getSecondCookProgressScaled(int i) {
        return this.secondCookTime * i / 3000;
    }

    public int getThirdCookProgressScaled(int i) {
        return this.thirdCookTime * i / 3000;
    }

    public int getFourthCookprogressScaled(int i) {
        return this.fourthCookTime * i / 3000;
    }

    public String getGuiID() {
        return "chef:dehydrator";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerDehydrator(playerInventory, this);
    }

    public void clear() {
        for (int i = 0; i < this.slots.length; ++i) {
            this.slots[i] = null;
        }
    }

    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.customName : "container.dehydrator";
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

