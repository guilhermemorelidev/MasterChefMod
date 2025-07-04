/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.internal.FMLNetworkHandler
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
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package com.chef.mod.blocks;

import com.chef.mod.Chef;
import com.chef.mod.init.MyBlocks;
import com.chef.mod.tileentity.TileEntitySauceMaker;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class SauceMaker
extends BlockContainer {
    private final boolean isBurning;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconFront;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconTop;
    private static boolean keepInventory;
    private Random rand = new Random();

    public SauceMaker(boolean isBurning) {
        super(Material.rock);
        this.setTickRandomly(false);
        if (this.isBurning) {
            this.setTickRandomly(true);
        }
        this.isBurning = isBurning;
        this.setHarvestLevel("pickaxe", 1);
        this.setStepSound(soundTypeStone);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon("chef:sauce_maker_side");
        this.iconFront = this.isBurning ? iconRegister.registerIcon("chef:sauce_maker_front_on") : iconRegister.registerIcon("chef:sauce_maker_front_off");
        this.iconTop = iconRegister.registerIcon("chef:sauce_maker_top");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return metadata == 0 && side == 3 ? this.iconFront : (side == 1 ? this.iconTop : (side == 0 ? this.iconTop : (side == metadata ? this.iconFront : this.blockIcon)));
    }

    public Item getItemDropped(int i, Random random, int j) {
        return Item.getItemFromBlock((Block)MyBlocks.sauce_maker);
    }

    public static String getFurnaceName(int x, int y, int z) {
        return x + "/" + y + "/" + z;
    }

    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        this.setDefaultFacing(world, x, y, z);
    }

    private void setDefaultFacing(World world, int x, int y, int z) {
        if (!world.isRemote) {
            Block b1 = world.getBlock(x, y, z - 1);
            Block b2 = world.getBlock(x, y, z + 1);
            Block b3 = world.getBlock(x - 1, y, z);
            Block b4 = world.getBlock(x + 1, y, z);
            int b0 = 3;
            if (b1.func_149730_j() && !b2.func_149730_j()) {
                b0 = 3;
            }
            if (b2.func_149730_j() && !b1.func_149730_j()) {
                b0 = 2;
            }
            if (b3.func_149730_j() && !b4.func_149730_j()) {
                b0 = 5;
            }
            if (b4.func_149730_j() && !b3.func_149730_j()) {
                b0 = 4;
            }
            world.setBlockMetadataWithNotify(x, y, z, b0, 2);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        if (this.isBurning) {
            int l = world.getBlockMetadata(x, y, z);
            float f = (float)x + 0.5f;
            float f1 = (float)y + 0.0f + random.nextFloat() * 6.0f / 16.0f;
            float f2 = (float)z + 0.5f;
            float f3 = 0.52f;
            float f4 = random.nextFloat() * 0.6f - 0.3f;
            if (l == 4) {
                world.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0, 0.0, 0.0);
                world.spawnParticle("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0, 0.0, 0.0);
            } else if (l == 5) {
                world.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0, 0.0, 0.0);
                world.spawnParticle("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0, 0.0, 0.0);
            } else if (l == 2) {
                world.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0, 0.0, 0.0);
                world.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0, 0.0, 0.0);
            } else if (l == 3) {
                world.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0, 0.0, 0.0);
                world.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0, 0.0, 0.0);
            }
        }
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            FMLNetworkHandler.openGui((EntityPlayer)player, (Object)Chef.instance, (int)1, (World)world, (int)x, (int)y, (int)z);
        }
        return true;
    }

    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntitySauceMaker();
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityplayer, ItemStack itemstack) {
        int l = MathHelper.floor_double((double)((double)(entityplayer.rotationYaw * 4.0f / 360.0f) + 0.5)) & 3;
        if (l == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }
        if (l == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }
        if (l == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }
        if (l == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }
        if (itemstack.hasDisplayName()) {
            // empty if block
        }
    }

    public static void updateBlockState(boolean isBurning, World worldObj, int xCoord, int yCoord, int zCoord) {
        int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);
        keepInventory = true;
        if (isBurning) {
            worldObj.setBlock(xCoord, yCoord, zCoord, MyBlocks.sauce_maker_on);
        } else {
            worldObj.setBlock(xCoord, yCoord, zCoord, MyBlocks.sauce_maker);
        }
        keepInventory = false;
        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);
        if (tileentity != null) {
            tileentity.validate();
            worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
        }
    }

    public void breakBlock(World world, int x, int y, int z, Block oldblock, int oldMetadata) {
        TileEntitySauceMaker tileentity;
        if (!keepInventory && (tileentity = (TileEntitySauceMaker)world.getTileEntity(x, y, z)) != null) {
            for (int i = 0; i < tileentity.getSizeInventory(); ++i) {
                ItemStack itemstack = tileentity.getStackInSlot(i);
                if (itemstack == null) continue;
                float f = this.rand.nextFloat() * 0.8f + 0.1f;
                float f1 = this.rand.nextFloat() * 0.8f + 0.1f;
                float f2 = this.rand.nextFloat() * 0.8f + 0.1f;
                while (itemstack.stackSize > 0) {
                    int j = this.rand.nextInt(21) + 10;
                    if (j > itemstack.stackSize) {
                        j = itemstack.stackSize;
                    }
                    itemstack.stackSize -= j;
                    EntityItem item = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));
                    if (itemstack.hasTagCompound()) {
                        item.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                    }
                    world.spawnEntityInWorld((Entity)item);
                }
            }
            world.func_147453_f(x, y, z, oldblock);
        }
        super.breakBlock(world, x, y, z, oldblock, oldMetadata);
    }

    public Item getItem(World world, int x, int y, int z) {
        return Item.getItemFromBlock((Block)MyBlocks.sauce_maker);
    }
}

