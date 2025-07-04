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
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.chef.mod.blocks;

import com.chef.mod.Chef;
import com.chef.mod.init.MyBlocks;
import com.chef.mod.tileentity.TileEntityIceCreamMaker;
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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class IceCreamMaker
extends BlockContainer {
    private final boolean isActive;
    @SideOnly(value=Side.CLIENT)
    private IIcon[] iconFrontOff;
    @SideOnly(value=Side.CLIENT)
    private IIcon[] iconFrontActive;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconTop;
    private static boolean keepInventory;
    private Random rand = new Random();

    public IceCreamMaker(boolean isActive) {
        super(Material.iron);
        this.setTickRandomly(false);
        this.isActive = isActive;
        this.setHarvestLevel("pickaxe", 1);
        this.setStepSound(soundTypeMetal);
        this.setHardness(5.0f);
        this.setResistance(10.0f);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        int i;
        this.blockIcon = iconRegister.registerIcon("minecraft:iron_block");
        this.iconTop = iconRegister.registerIcon("minecraft:iron_block");
        this.iconFrontOff = new IIcon[11];
        this.iconFrontActive = new IIcon[11];
        for (i = 0; i < this.iconFrontActive.length; ++i) {
            this.iconFrontActive[i] = iconRegister.registerIcon("chef:IceCreamMaker/ice_cream_maker_front_on_ice" + i);
        }
        for (i = 0; i < this.iconFrontOff.length; ++i) {
            this.iconFrontOff[i] = iconRegister.registerIcon("chef:IceCreamMaker/ice_cream_maker_front_ice" + i);
        }
    }

    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityIceCreamMaker();
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        int metadata = world.getBlockMetadata(x, y, z);
        if (side == 1) {
            return this.iconTop;
        }
        if (side == 0) {
            return this.iconTop;
        }
        TileEntity tile = world.getTileEntity(x, y, z);
        int level = 0;
        if (tile instanceof TileEntityIceCreamMaker) {
            level = ((TileEntityIceCreamMaker)tile).ice > 9500 ? 10 : Math.round(((TileEntityIceCreamMaker)tile).ice / 1000);
        }
        if (side == metadata) {
            if (this.isActive) {
                return this.iconFrontActive[level];
            }
            return this.iconFrontOff[level];
        }
        return this.blockIcon;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return metadata == 0 && side == 3 ? this.iconFrontOff[0] : this.blockIcon;
    }

    public Item getItemDropped(int i, Random random, int j) {
        return Item.getItemFromBlock((Block)MyBlocks.ice_cream_maker);
    }

    public static String getFurnaceName(int x, int y, int z) {
        return x + "/" + y + "/" + z;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            FMLNetworkHandler.openGui((EntityPlayer)player, (Object)Chef.instance, (int)2, (World)world, (int)x, (int)y, (int)z);
        }
        return true;
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

    public static void updateBlockState(boolean isActive, World worldObj, int xCoord, int yCoord, int zCoord) {
        int metadata = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);
        keepInventory = true;
        if (isActive) {
            worldObj.setBlock(xCoord, yCoord, zCoord, MyBlocks.ice_cream_maker_on);
        } else {
            worldObj.setBlock(xCoord, yCoord, zCoord, MyBlocks.ice_cream_maker);
        }
        keepInventory = false;
        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, metadata, 2);
        if (tileentity != null) {
            tileentity.validate();
            worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
        }
    }

    public void breakBlock(World world, int x, int y, int z, Block oldblock, int oldMetadata) {
        TileEntityIceCreamMaker tileentity;
        if (!keepInventory && (tileentity = (TileEntityIceCreamMaker)world.getTileEntity(x, y, z)) != null) {
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
        return Item.getItemFromBlock((Block)MyBlocks.ice_cream_maker);
    }
}

