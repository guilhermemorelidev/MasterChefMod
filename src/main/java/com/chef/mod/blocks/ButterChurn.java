/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package com.chef.mod.blocks;

import com.chef.mod.Chef;
import com.chef.mod.init.MyBlocks;
import com.chef.mod.init.MyItems;
import com.chef.mod.tileentity.TileEntityButterChurn;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ButterChurn
extends BlockContainer {
    @SideOnly(value=Side.CLIENT)
    public static IIcon iconInner;
    @SideOnly(value=Side.CLIENT)
    public static IIcon iconTop;
    @SideOnly(value=Side.CLIENT)
    public static IIcon iconBottom;
    @SideOnly(value=Side.CLIENT)
    public static IIcon[] iconMilkArray;

    public ButterChurn() {
        super(Material.wood);
        this.setBlockBounds(0.19f, 0.0f, 0.19f, 0.81f, 1.0f, 0.81f);
        this.setCreativeTab(Chef.tabChef);
        this.setStepSound(soundTypeWood);
    }

    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityButterChurn();
    }

    public int getRenderType() {
        return -1;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer playerIn, int side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        }
        ItemStack itemstack = playerIn.getCurrentEquippedItem();
        if (itemstack == null) {
            return false;
        }
        int level = worldIn.getBlockMetadata(x, y, z);
        Item item = itemstack.getItem();
        if (item == Items.milk_bucket) {
            if (level == 0) {
                if (!playerIn.capabilities.isCreativeMode) {
                    playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, new ItemStack(Items.bucket));
                }
                this.setMilkLevel(worldIn, x, y, z, 1);
            }
            return true;
        }
        Random rand = new Random();
        if (item == MyItems.stamper) {
            if (level != 0 && level < 5) {
                this.setMilkLevel(worldIn, x, y, z, level + 1);
                if (!playerIn.capabilities.isCreativeMode) {
                    itemstack.damageItem(1, (EntityLivingBase)playerIn);
                    if (itemstack.getItemDamage() >= itemstack.getMaxDamage()) {
                        --itemstack.stackSize;
                    }
                }
            } else if (level == 6) {
                if (!playerIn.capabilities.isCreativeMode) {
                    ItemStack itemstack1;
                    itemstack.damageItem(1, (EntityLivingBase)playerIn);
                    if (itemstack.getItemDamage() >= itemstack.getMaxDamage()) {
                        --itemstack.stackSize;
                    }
                    if (!playerIn.inventory.addItemStackToInventory(itemstack1 = new ItemStack(MyItems.butter, 5 + rand.nextInt(6), 0))) {
                        worldIn.spawnEntityInWorld((Entity)new EntityItem(worldIn, (double)x + 0.5, (double)x + 1.5, (double)x + 0.5, itemstack1));
                    } else if (playerIn instanceof EntityPlayerMP) {
                        ((EntityPlayerMP)playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
                    }
                }
                this.setMilkLevel(worldIn, x, y, z, 0);
            }
            return true;
        }
        return false;
    }

    public void setMilkLevel(World worldIn, int x, int y, int z, int level) {
        worldIn.setBlockMetadataWithNotify(x, y, z, MathHelper.clamp_int((int)level, (int)0, (int)6), 2);
    }

    public Item getItemDropped(int metadata, Random rand, int fortune) {
        return Item.getItemFromBlock((Block)MyBlocks.butter_churn);
    }

    @SideOnly(value=Side.CLIENT)
    public Item getItem(World worldIn, int x, int y, int z) {
        return Item.getItemFromBlock((Block)MyBlocks.butter_churn);
    }

    public boolean hasComparatorInputOverride() {
        return true;
    }

    public int getComparatorInputOverride(World worldIn, int x, int y, int z) {
        return worldIn.getBlockMetadata(x, y, z);
    }
}

