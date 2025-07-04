/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package com.chef.mod.blocks;

import com.chef.mod.init.MyItems;
import com.chef.mod.proxy.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class MilkBarrel
extends Block {
    @SideOnly(value=Side.CLIENT)
    public static IIcon iconInner;
    @SideOnly(value=Side.CLIENT)
    public static IIcon iconTop;
    @SideOnly(value=Side.CLIENT)
    public static IIcon iconBottom;
    @SideOnly(value=Side.CLIENT)
    public static IIcon iconMilk;

    public MilkBarrel() {
        super(Material.wood);
        this.setStepSound(soundTypeWood);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return side == 1 ? iconTop : (side == 0 ? iconBottom : this.blockIcon);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        iconInner = iconRegister.registerIcon(this.getTextureName() + "_inner");
        iconTop = iconRegister.registerIcon(this.getTextureName() + "_top");
        iconBottom = iconRegister.registerIcon(this.getTextureName() + "_bottom");
        this.blockIcon = iconRegister.registerIcon(this.getTextureName() + "_side");
        iconMilk = iconRegister.registerIcon("chef:milk_still");
    }

    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity collidingEntity) {
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.3125f, 1.0f);
        super.addCollisionBoxesToList(world, x, y, z, mask, list, collidingEntity);
        float f = 0.125f;
        this.setBlockBounds(0.0f, 0.0f, 0.0f, f, 1.0f, 1.0f);
        super.addCollisionBoxesToList(world, x, y, z, mask, list, collidingEntity);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, f);
        super.addCollisionBoxesToList(world, x, y, z, mask, list, collidingEntity);
        this.setBlockBounds(1.0f - f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        super.addCollisionBoxesToList(world, x, y, z, mask, list, collidingEntity);
        this.setBlockBounds(0.0f, 0.0f, 1.0f - f, 1.0f, 1.0f, 1.0f);
        super.addCollisionBoxesToList(world, x, y, z, mask, list, collidingEntity);
        this.setBlockBoundsForItemRender();
    }

    @SideOnly(value=Side.CLIENT)
    public static IIcon getMilkBarrelIcon(String s) {
        return s.equals("inner") ? iconInner : (s.equals("bottom") ? iconBottom : null);
    }

    @SideOnly(value=Side.CLIENT)
    public static IIcon getMilkIcon() {
        return iconMilk;
    }

    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    public int getRenderType() {
        return ClientProxy.milkBarrelRenderType;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public void onEntityCollidedWithBlock(World worldIn, int x, int y, int z, Entity entity) {
        int l = worldIn.getBlockMetadata(x, y, z);
        float f = (float)y + (6.0f + (float)(3 * l)) / 16.0f;
        if (!worldIn.isRemote && entity.isBurning() && l > 0 && entity.boundingBox.minY <= (double)f) {
            entity.extinguish();
            this.setMilkLevel(worldIn, x, y, z, l - 1);
        }
    }

    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer playerIn, int side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        }
        ItemStack itemstack = playerIn.inventory.getCurrentItem();
        if (itemstack == null) {
            return true;
        }
        int i = worldIn.getBlockMetadata(x, y, z);
        Item item = itemstack.getItem();
        if (item == Items.bucket) {
            if (i == 5) {
                if (!playerIn.capabilities.isCreativeMode) {
                    playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, new ItemStack(Items.milk_bucket));
                }
                this.setMilkLevel(worldIn, x, y, z, 0);
            }
        } else if (item == MyItems.milk_bowl && i < 5) {
            if (!playerIn.capabilities.isCreativeMode) {
                playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, new ItemStack(Items.bowl));
            }
            this.setMilkLevel(worldIn, x, y, z, i + 1);
        }
        if (item == Items.milk_bucket) {
            if (i < 5) {
                if (!playerIn.capabilities.isCreativeMode) {
                    playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, new ItemStack(Items.bucket));
                }
                this.setMilkLevel(worldIn, x, y, z, 5);
            }
            return true;
        }
        if (item == Items.bowl) {
            if (i > 0) {
                if (!playerIn.capabilities.isCreativeMode) {
                    ItemStack itemstack1 = new ItemStack(MyItems.milk_bowl, 1, 0);
                    if (!playerIn.inventory.addItemStackToInventory(itemstack1)) {
                        worldIn.spawnEntityInWorld((Entity)new EntityItem(worldIn, (double)x + 0.5, (double)y + 1.5, (double)z + 0.5, itemstack1));
                    } else if (playerIn instanceof EntityPlayerMP) {
                        ((EntityPlayerMP)playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
                    }
                    --itemstack.stackSize;
                    if (itemstack.stackSize <= 0) {
                        playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, (ItemStack)null);
                    }
                }
                this.setMilkLevel(worldIn, x, y, z, i - 1);
            }
            return true;
        }
        return false;
    }

    public void setMilkLevel(World worldIn, int x, int y, int z, int level) {
        worldIn.setBlockMetadataWithNotify(x, y, z, MathHelper.clamp_int((int)level, (int)0, (int)5), 2);
        worldIn.func_147453_f(x, y, z, (Block)this);
    }

    public Item getItemDropped(int chance, Random rand, int fortune) {
        return MyItems.milk_barrel_reed;
    }

    @SideOnly(value=Side.CLIENT)
    public Item getItem(World worldIn, int x, int y, int z) {
        return MyItems.milk_barrel_reed;
    }

    public boolean hasComparatorInputOverride() {
        return true;
    }

    public int getComparatorInputOverride(World worldIn, int x, int y, int z, int p_149736_5_) {
        int i1 = worldIn.getBlockMetadata(x, y, z);
        return i1;
    }

    @SideOnly(value=Side.CLIENT)
    public static float getRenderLiquidLevel(int i) {
        int j = MathHelper.clamp_int((int)i, (int)0, (int)5);
        return (float)(6 + 5 * j) / 33.0f;
    }
}

