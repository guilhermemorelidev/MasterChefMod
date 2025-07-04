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

import com.chef.mod.blocks.CookingFurnace;
import com.chef.mod.container.ContainerCookingFurnace;
import com.chef.mod.crafting.CookingFurnaceRecipes;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileEntityCookingFurnace
extends TileEntity
implements ISidedInventory {
    private static final int[] slotsTop = new int[]{0, 1};
    private static final int[] slotsBottom = new int[]{3};
    private static final int[] slotsSides = new int[]{2};
    private ItemStack[] slots = new ItemStack[6];
    private String customName;
    public int bakeItem = 0;
    public int burnTime;
    public int currentItemBurnTime;
    public int bakeTime;
    public int currentItemBakeTime;
    public int cookTime;
    public static final int cookingFurnaceSpeed = 155;
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
        this.bakeTime = compound.getShort("BakeTime");
        this.cookTime = compound.getShort("CookTime");
        this.bakeItem = compound.getShort("BakeItem");
        this.currentItemBurnTime = TileEntityCookingFurnace.getItemBurnTime(this.slots[2]);
        this.currentItemBakeTime = TileEntityCookingFurnace.getItemBakeTime(this.slots[3]);
        if (compound.hasKey("CustomName", 8)) {
            this.furnaceCustomName = compound.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setShort("BurnTime", (short)this.burnTime);
        compound.setShort("BakeTime", (short)this.bakeTime);
        compound.setShort("CookTime", (short)this.cookTime);
        compound.setShort("BakeItem", (short)this.bakeItem);
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
            compound.setString("CustomName", this.furnaceCustomName);
        }
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

    public void updateEntity() {
        boolean flag = this.burnTime > 0;
        boolean flag2 = this.bakeTime > 0;
        boolean flag3 = false;
        if (this.isBurning()) {
            --this.burnTime;
        }
        if (this.canCook() && this.isBaking() && this.isBurning() && CookingFurnaceRecipes.doesRequireButter(this.slots[1].getItem())) {
            --this.bakeTime;
        }
        if (!this.worldObj.isRemote) {
            if (this.burnTime == 0 && this.containsGreese() && this.canCook()) {
                this.currentItemBurnTime = this.burnTime = TileEntityCookingFurnace.getItemBurnTime(this.slots[2]);
                if (this.isBurning()) {
                    flag3 = true;
                    if (this.slots[2] != null) {
                        --this.slots[2].stackSize;
                        if (this.slots[2].stackSize == 0) {
                            this.slots[2] = this.slots[2].getItem().getContainerItem(this.slots[2]);
                        }
                    }
                }
            }
            if (this.bakeTime == 0) {
                if (this.bakeItem == 1 || this.bakeItem == 2) {
                    this.giveEmptyBottle();
                    this.bakeItem = 0;
                }
                if (this.canCook()) {
                    switch (TileEntityCookingFurnace.getBakeItem(this.slots[3])) {
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
                    this.currentItemBakeTime = this.bakeTime = TileEntityCookingFurnace.getItemBakeTime(this.slots[3]);
                    if (this.isBaking()) {
                        flag3 = true;
                        if (this.slots[3] != null) {
                            --this.slots[3].stackSize;
                            if (this.slots[3].stackSize == 0) {
                                this.slots[3] = this.slots[3].getItem().getContainerItem(this.slots[3]);
                            }
                        }
                    }
                }
            }
        }
        if (this.canCook() && this.isBurning() && (this.isBaking() || !CookingFurnaceRecipes.doesRequireButter(this.slots[1].getItem()))) {
            ++this.cookTime;
            if (this.cookTime >= 155) {
                this.cookTime = 0;
                this.cookItem();
                flag3 = true;
            }
        } else {
            this.cookTime = 0;
        }
        if (flag != this.isBurning()) {
            flag3 = true;
            CookingFurnace.updateBlockState(this.burnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
        }
        if (flag3) {
            this.markDirty();
        }
    }

    private boolean containsGreese() {
        Item item = null;
        if (this.slots[3] != null) {
            item = this.slots[3].getItem();
        }
        if (item != null) {
            return item == MyItems.butter || item == MyItems.fish_oil || item == MyItems.olive_oil;
        }
        return false;
    }

    private boolean canCook() {
        int result;
        if (this.slots[1] == null) {
            return false;
        }
        if (this.slots[0] == null) {
            ItemStack itemstack = CookingFurnaceRecipes.getSingleItemCookingResult(this.slots[1].getItem());
            if (itemstack == null) {
                return false;
            }
            if (itemstack.getItem() == Items.cooked_fished) {
                if (this.slots[1].getItemDamage() == 0 || this.slots[1].getItemDamage() == 1) {
                    itemstack = new ItemStack(Items.cooked_fished, 1, this.slots[1].getItemDamage());
                } else {
                    return false;
                }
            }
            if (this.slots[4] == null) {
                return true;
            }
            if (!this.slots[4].isItemEqual(itemstack)) {
                return false;
            }
            result = this.slots[4].stackSize + itemstack.stackSize;
        } else {
            ItemStack itemstack2 = CookingFurnaceRecipes.getDoubleItemCookingResult(this.slots[0].getItem(), this.slots[1].getItem());
            if (itemstack2 == null) {
                return false;
            }
            if (itemstack2.getItem() == MyItems.fried_fish) {
                if (this.slots[1].getItemDamage() == 0) {
                    itemstack2 = new ItemStack(MyItems.fried_fish);
                } else if (this.slots[1].getItemDamage() == 1) {
                    itemstack2 = new ItemStack(MyItems.fried_salmon);
                } else {
                    return false;
                }
            }
            if (this.slots[4] == null) {
                return true;
            }
            if (!this.slots[4].isItemEqual(itemstack2)) {
                return false;
            }
            result = this.slots[4].stackSize + itemstack2.stackSize;
        }
        if (this.slots[5] == null) {
            return true;
        }
        if (this.slots[5].stackSize >= 64 && (this.bakeItem == 1 || this.bakeItem == 2)) {
            return false;
        }
        return result <= this.getInventoryStackLimit() && result <= this.slots[4].getMaxStackSize();
    }

    public void cookItem() {
        if (this.canCook()) {
            if (this.slots[0] == null) {
                ItemStack itemstack = CookingFurnaceRecipes.getSingleItemCookingResult(this.slots[1].getItem());
                if (itemstack.getItem() == Items.cooked_fished) {
                    itemstack = new ItemStack(Items.cooked_fished, 1, this.slots[1].getItemDamage());
                }
                if (this.slots[4] == null) {
                    this.slots[4] = itemstack.copy();
                } else if (this.slots[4].getItem() == itemstack.getItem()) {
                    this.slots[4].stackSize += itemstack.stackSize;
                }
                if (this.slots[1].stackSize <= 0) {
                    this.slots[1] = new ItemStack(this.slots[1].getItem().setFull3D());
                } else {
                    --this.slots[1].stackSize;
                }
                if (this.slots[1].stackSize <= 0) {
                    this.slots[1] = null;
                }
            } else {
                ItemStack itemstack2 = CookingFurnaceRecipes.getDoubleItemCookingResult(this.slots[0].getItem(), this.slots[1].getItem());
                if (itemstack2.getItem() == MyItems.fried_fish) {
                    if (this.slots[1].getItemDamage() == 0) {
                        itemstack2 = new ItemStack(MyItems.fried_fish);
                    } else if (this.slots[1].getItemDamage() == 1) {
                        itemstack2 = new ItemStack(MyItems.fried_salmon);
                    }
                }
                if (this.slots[4] == null) {
                    this.slots[4] = itemstack2.copy();
                } else if (this.slots[4].getItem() == itemstack2.getItem()) {
                    this.slots[4].stackSize += itemstack2.stackSize;
                }
                if (this.slots[0].stackSize <= 0) {
                    this.slots[0] = new ItemStack(this.slots[0].getItem().setFull3D());
                } else {
                    --this.slots[0].stackSize;
                }
                if (this.slots[0].stackSize <= 0) {
                    this.slots[0] = null;
                }
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
    }

    public void giveEmptyBottle() {
        ItemStack itemstack = new ItemStack(Items.glass_bottle, 1);
        if (this.slots[5] == null) {
            this.slots[5] = itemstack.copy();
        } else if (this.slots[5].getItem() == itemstack.getItem()) {
            this.slots[5].stackSize += itemstack.stackSize;
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
        return TileEntityCookingFurnace.getItemBurnTime(itemstack) > 0;
    }

    public static boolean isItemGrease(ItemStack itemstack) {
        return TileEntityCookingFurnace.getItemBakeTime(itemstack) > 0;
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
    }

    public void openInventory(EntityPlayer player) {
    }

    public void closeInventory(EntityPlayer player) {
    }

    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return i == 2 ? false : (i == 1 ? TileEntityCookingFurnace.isItemFuel(itemstack) : true);
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
            this.currentItemBurnTime = 155;
        }
        return this.burnTime * i / this.currentItemBurnTime;
    }

    public int getBakeTimeRemainingScaled(int i) {
        if (this.currentItemBakeTime == 0) {
            this.currentItemBakeTime = 155;
        }
        return this.bakeTime * i / this.currentItemBakeTime;
    }

    public int getCookProgressScaled(int i) {
        return this.cookTime * i / 155;
    }

    public String getGuiID() {
        return "chef:cookingFurnace";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerCookingFurnace(playerInventory, this);
    }

    public void clear() {
        for (int i = 0; i < this.slots.length; ++i) {
            this.slots[i] = null;
        }
    }

    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.customName : "container.cookingFurnace";
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

