/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.chef.mod.blocks;

import com.chef.mod.Chef;
import com.chef.mod.init.MyBlocks;
import com.chef.mod.init.MyItems;
import com.chef.mod.tileentity.TileEntityPizza;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Pizza
extends BlockContainer {
    public Pizza() {
        super(Material.cake);
        this.setTickRandomly(true);
        this.setCreativeTab(Chef.tabChef);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, int x, int y, int z) {
        float f = 0.0625f;
        int bites = worldIn.getBlockMetadata(x, y, z);
        float f2 = 0.5f;
        if (bites == 0 || bites == 1) {
            this.setBlockBounds(0.0625f, 0.0f, 0.0625f, 0.935f, 0.125f, 0.935f);
        } else if (bites == 2) {
            this.setBlockBounds(0.5f, 0.0f, 0.0625f, 0.9375f, 0.125f, 0.9375f);
        } else if (bites == 3) {
            this.setBlockBounds(0.5f, 0.0f, 0.0625f, 0.9375f, 0.125f, 0.5f);
        } else {
            this.setBlockBounds(0.0625f, 0.0f, 0.0625f, 0.935f, 0.125f, 0.935f);
        }
    }

    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityPizza();
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
        ItemStack itemstack = playerIn.getCurrentEquippedItem();
        int bites = worldIn.getBlockMetadata(x, y, z);
        if (worldIn.isRemote) {
            return false;
        }
        if (itemstack == null) {
            this.eatPizza(worldIn, x, y, z, playerIn);
        } else {
            Item item = itemstack.getItem();
            if (item == MyItems.tomato_sauce) {
                if (worldIn.getBlock(x, y, z) == MyBlocks.pizza_dough) {
                    if (!playerIn.capabilities.isCreativeMode) {
                        --itemstack.stackSize;
                    }
                    worldIn.setBlock(x, y, z, MyBlocks.raw_pizza_with_tomato_sauce);
                    worldIn.setBlockMetadataWithNotify(x, y, z, bites, 2);
                } else {
                    this.eatPizza(worldIn, x, y, z, playerIn);
                }
            } else if (item == MyItems.cutting_knife) {
                if (!playerIn.capabilities.isCreativeMode) {
                    itemstack.damageItem(1, (EntityLivingBase)playerIn);
                    if (itemstack.getItemDamage() >= itemstack.getMaxDamage()) {
                        --itemstack.stackSize;
                    }
                }
                if (bites == 3) {
                    worldIn.setBlockToAir(x, y, z);
                } else {
                    worldIn.setBlockMetadataWithNotify(x, y, z, bites + 1, 2);
                }
                Object entityItem = worldIn.getBlock(x, y, z) == MyBlocks.pizza_dough ? new EntityItem(worldIn, (double)x, (double)((float)y + 0.5f), (double)z, new ItemStack(MyItems.pizza_dough_slice, 1)) : (worldIn.getBlock(x, y, z) == MyBlocks.raw_pizza_with_tomato_sauce ? new EntityItem(worldIn, (double)x, (double)((float)y + 0.5f), (double)z, new ItemStack(MyItems.raw_pizza_with_tomato_sauce_slice, 1)) : (worldIn.getBlock(x, y, z) == MyBlocks.raw_pizza_margharita ? new EntityItem(worldIn, (double)x, (double)((float)y + 0.5f), (double)z, new ItemStack(MyItems.raw_pizza_margharita_slice, 1)) : (worldIn.getBlock(x, y, z) == MyBlocks.raw_pizza_onion ? new EntityItem(worldIn, (double)x, (double)((float)y + 0.5f), (double)z, new ItemStack(MyItems.raw_pizza_onion_slice, 1)) : (worldIn.getBlock(x, y, z) == MyBlocks.raw_pizza_bacon ? new EntityItem(worldIn, (double)x, (double)((float)y + 0.5f), (double)z, new ItemStack(MyItems.raw_pizza_bacon_slice, 1)) : (worldIn.getBlock(x, y, z) == MyBlocks.raw_pizza_mushroom ? new EntityItem(worldIn, (double)x, (double)((float)y + 0.5f), (double)z, new ItemStack(MyItems.raw_pizza_mushroom_slice, 1)) : (worldIn.getBlock(x, y, z) == MyBlocks.pizza_margharita ? new EntityItem(worldIn, (double)x, (double)((float)y + 0.5f), (double)z, new ItemStack(MyItems.pizza_margharita_slice, 1)) : (worldIn.getBlock(x, y, z) == MyBlocks.pizza_onion ? new EntityItem(worldIn, (double)x, (double)((float)y + 0.5f), (double)z, new ItemStack(MyItems.pizza_onion_slice, 1)) : (worldIn.getBlock(x, y, z) == MyBlocks.pizza_bacon ? new EntityItem(worldIn, (double)x, (double)((float)y + 0.5f), (double)z, new ItemStack(MyItems.pizza_bacon_slice, 1)) : (worldIn.getBlock(x, y, z) == MyBlocks.pizza_mushroom ? new EntityItem(worldIn, (double)x, (double)((float)y + 0.5f), (double)z, new ItemStack(MyItems.pizza_mushroom_slice, 1)) : null)))))))));
                worldIn.spawnEntityInWorld((Entity)entityItem);
            } else {
                this.eatPizza(worldIn, x, y, z, playerIn);
            }
        }
        return true;
    }

    public void onBlockClicked(World worldIn, int x, int y, int z, EntityPlayer playerIn) {
        this.eatPizza(worldIn, x, y, z, playerIn);
    }

    private void eatPizza(World worldIn, int x, int y, int z, EntityPlayer player) {
        if (player.canEat(false)) {
            if (this == MyBlocks.pizza_dough) {
                player.getFoodStats().addStats(1, 0.1f);
            } else if (this == MyBlocks.raw_pizza_with_tomato_sauce) {
                player.getFoodStats().addStats(1, 0.3f);
            } else if (this == MyBlocks.raw_pizza_margharita) {
                player.getFoodStats().addStats(2, 0.4f);
            } else if (this == MyBlocks.raw_pizza_mushroom) {
                player.getFoodStats().addStats(2, 0.4f);
            } else if (this == MyBlocks.raw_pizza_onion) {
                player.getFoodStats().addStats(2, 0.4f);
            } else if (this == MyBlocks.raw_pizza_bacon) {
                player.getFoodStats().addStats(2, 0.4f);
            } else if (this == MyBlocks.pizza_margharita) {
                player.getFoodStats().addStats(3, 0.5f);
            } else if (this == MyBlocks.pizza_mushroom) {
                player.getFoodStats().addStats(4, 0.6f);
            } else if (this == MyBlocks.pizza_onion) {
                player.getFoodStats().addStats(5, 0.7f);
            } else if (this == MyBlocks.pizza_bacon) {
                player.getFoodStats().addStats(5, 0.8f);
            }
            int bites = worldIn.getBlockMetadata(x, y, z);
            if (bites < 3) {
                worldIn.setBlockMetadataWithNotify(x, y, z, bites + 1, 3);
            } else {
                worldIn.setBlockToAir(x, y, z);
            }
        }
    }

    public boolean canPlaceBlockAt(World worldIn, int x, int y, int z) {
        return super.canPlaceBlockAt(worldIn, x, y, z) ? this.canBlockStay(worldIn, x, y, z) : false;
    }

    public void onNeighborBlockChange(World worldIn, int x, int y, int z, Block neighborBlock) {
        if (!this.canBlockStay(worldIn, x, y, z)) {
            worldIn.setBlockToAir(x, y, z);
        }
    }

    public boolean canBlockStay(World worldIn, int x, int y, int z) {
        return worldIn.getBlock(x, y - 1, z).getMaterial().isSolid();
    }

    public int quantityDropped(Random random) {
        return 0;
    }

    public Item getItemDropped(int metadata, Random rand, int fortune) {
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    public Item getItem(World worldIn, int x, int y, int z) {
        return Item.getItemFromBlock((Block)this);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon("chef:" + this.getUnlocalizedName().substring(5) + "_top");
    }
}

