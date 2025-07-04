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
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumFacing
 */
package com.chef.mod.tileentity;

import com.chef.mod.blocks.SauceMaker;
import com.chef.mod.container.ContainerSauceMaker;
import com.chef.mod.crafting.SauceMakerRecipes;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileEntitySauceMaker
extends TileEntity
implements ISidedInventory {
    private static final int[] slotsTop = new int[]{0, 1};
    private static final int[] slotsBottom = new int[]{3};
    private static final int[] slotsSides = new int[]{2};
    private ItemStack[] slots = new ItemStack[4];
    private String customName;
    public int burnTime;
    public int currentItemBurnTime;
    public int cookTime;
    public static final int sauceMakerSpeed = 250;
    private String furnaceCustomName;

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

    public String getName() {
        return this.hasCustomName() ? this.furnaceCustomName : "container.sauceMaker";
    }

    public boolean hasCustomName() {
        return this.furnaceCustomName != null && this.furnaceCustomName.length() > 0;
    }

    public void setCustomInventoryName(String string) {
        this.furnaceCustomName = string;
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
        this.currentItemBurnTime = TileEntitySauceMaker.getItemBurnTime(this.slots[1]);
        if (compound.hasKey("CustomName", 8)) {
            this.furnaceCustomName = compound.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setShort("BurnTime", (short)this.burnTime);
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
        if (this.hasCustomName()) {
            compound.setString("CustomName", this.furnaceCustomName);
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
        if (this.isBurning()) {
            --this.burnTime;
        }
        if (!this.worldObj.isRemote) {
            if (this.burnTime == 0 && this.canJuice()) {
                this.currentItemBurnTime = this.burnTime = TileEntitySauceMaker.getItemBurnTime(this.slots[2]);
                if (this.isBurning()) {
                    flag1 = true;
                    if (this.slots[2] != null) {
                        --this.slots[2].stackSize;
                        if (this.slots[2].stackSize == 0) {
                            this.slots[2] = this.slots[2].getItem().getContainerItem(this.slots[2]);
                        }
                    }
                }
            }
            if (this.isBurning() && this.canJuice()) {
                ++this.cookTime;
                if (this.cookTime == 250) {
                    this.cookTime = 0;
                    this.juiceItem();
                    flag1 = true;
                }
            } else {
                this.cookTime = 0;
            }
            if (flag != this.isBurning()) {
                flag1 = true;
                SauceMaker.updateBlockState(this.burnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }
        if (flag1) {
            this.markDirty();
        }
    }

    public int furnaceSpeed(ItemStack itemstack) {
        return 250;
    }

    private boolean canJuice() {
        if (this.slots[0] == null || this.slots[1] == null) {
            return false;
        }
        ItemStack itemstack = SauceMakerRecipes.getJuicingResult(this.slots[0].getItem(), this.slots[1].getItem());
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

    public void juiceItem() {
        if (this.canJuice()) {
            ItemStack itemstack = SauceMakerRecipes.getJuicingResult(this.slots[0].getItem(), this.slots[1].getItem());
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
        return TileEntitySauceMaker.getItemBurnTime(itemstack) > 0;
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
    }

    public void openInventory(EntityPlayer player) {
    }

    public void closeInventory(EntityPlayer player) {
    }

    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return i == 2 ? false : (i == 1 ? TileEntitySauceMaker.isItemFuel(itemstack) : true);
    }

    public int[] getSlotsForFace(EnumFacing side) {
        return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
    }

    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        Item item;
        return direction != EnumFacing.DOWN || index != 1 || (item = stack.getItem()) == Items.water_bucket || item == Items.bucket;
    }

    public int getBurnTimeRemainingScaled(int i) {
        if (this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = 250;
        }
        return this.burnTime * i / this.currentItemBurnTime;
    }

    public int getCookProgressScaled(int i) {
        return this.cookTime * i / 250;
    }

    public String getGuiID() {
        return "chef:sauceMaker";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerSauceMaker(playerInventory, this);
    }

    public void clear() {
        for (int i = 0; i < this.slots.length; ++i) {
            this.slots[i] = null;
        }
    }

    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.customName : "container.sauceMaker";
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

