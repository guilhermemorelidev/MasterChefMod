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
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.chef.mod.blocks;

import com.chef.mod.Chef;
import com.chef.mod.init.MyBlocks;
import com.chef.mod.tileentity.TileEntityWaffleMaker;
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
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class WaffleMaker
extends BlockContainer {
    private final boolean isBurning;
    private static boolean keepInventory;
    private static int particleTickSpeed;
    private Random rand = new Random();

    public WaffleMaker(boolean isBurning) {
        super(Material.iron);
        this.isBurning = isBurning;
        this.setHardness(2.0f);
        this.setResistance(10.0f);
        this.setStepSound(soundTypeMetal);
        this.setTickRandomly(false);
        if (this.isBurning) {
            this.setTickRandomly(true);
        }
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        int dir = world.getBlockMetadata(x, y, z);
        if (dir == 4 || dir == 5) {
            this.setBlockBounds(0.125f, 0.0f, 0.0f, 0.875f, 0.9375f, 1.0f);
        } else {
            this.setBlockBounds(0.0f, 0.0f, 0.125f, 1.0f, 0.9375f, 0.875f);
        }
    }

    public int getRenderType() {
        return -1;
    }

    public Item getItemDropped(int metadata, Random rand, int fortune) {
        return Item.getItemFromBlock((Block)MyBlocks.waffle_maker);
    }

    public static String getFurnaceName(int x, int y, int z) {
        return x + "/" + y + "/" + z;
    }

    public void onBlockAdded(World worldIn, int x, int y, int z) {
        super.onBlockAdded(worldIn, x, y, z);
        this.setDefaultFacing(worldIn, x, y, z);
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
    public void randomDisplayTick(World worldIn, int x, int y, int z, Random random) {
        if (this.isBurning) {
            int direction = worldIn.getBlockMetadata(x, y, z);
            double d0 = (double)x + 0.7;
            double d1 = (double)y + random.nextDouble() * 4.0 / 16.0;
            double d2 = (double)z + 0.7;
            double d3 = 0.52;
            double d4 = random.nextDouble() * 0.2 - 0.3;
            double d5 = (double)x + 0.82;
            double d6 = (double)y + random.nextDouble() * 4.0 / 16.0;
            double d7 = (double)z + 0.7;
            double d8 = 0.52;
            double d9 = random.nextDouble() * 0.2 - 0.3;
            double d10 = (double)x + 0.7;
            double d11 = (double)y + random.nextDouble() * 4.0 / 16.0;
            double d12 = (double)z + 0.82;
            double d13 = 0.15;
            double d14 = random.nextDouble() * 0.2 - 0.3;
            double d15 = (double)x + 0.7;
            double d16 = (double)y + random.nextDouble() * 4.0 / 16.0;
            double d17 = (double)z + 0.82;
            double d18 = 0.52;
            double d19 = random.nextDouble() * 0.2 - 0.3;
            if (direction == 5) {
                worldIn.spawnParticle("smoke", d0, d1, d2 + d4, 0.0, 0.0, 0.0);
                worldIn.spawnParticle("flame", d0, d1, d2 + d4, 0.0, 0.0, 0.0);
            }
            if (direction == 4) {
                worldIn.spawnParticle("smoke", d5 - d8, d6, d7 + d9, 0.0, 0.0, 0.0);
                worldIn.spawnParticle("flame", d5 - d8, d6, d7 + d9, 0.0, 0.0, 0.0);
            }
            if (direction == 3) {
                worldIn.spawnParticle("smoke", d10 + d14, d11, d12 - d13, 0.0, 0.0, 0.0);
                worldIn.spawnParticle("flame", d10 + d14, d11, d12 - d13, 0.0, 0.0, 0.0);
            }
            if (direction == 2) {
                worldIn.spawnParticle("smoke", d15 + d19, d16, d17 - d18, 0.0, 0.0, 0.0);
                worldIn.spawnParticle("flame", d15 + d19, d16, d17 - d18, 0.0, 0.0, 0.0);
            }
        }
    }

    public boolean canPlaceBlockAt(World worldIn, int x, int y, int z) {
        return super.canPlaceBlockAt(worldIn, x, y, z) ? this.canBlockStay(worldIn, x, y, z) : false;
    }

    public boolean canBlockStay(World worldIn, int x, int y, int z) {
        return worldIn.getBlock(x, y - 1, z).getMaterial().isSolid();
    }

    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer playerIn, int side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        }
        TileEntity tileentity = worldIn.getTileEntity(x, y, z);
        if (tileentity instanceof TileEntityWaffleMaker) {
            playerIn.openGui((Object)Chef.instance, 4, worldIn, x, y, z);
        }
        return true;
    }

    public static void updateBlockState(boolean active, World worldIn, int x, int y, int z) {
        int i = worldIn.getBlockMetadata(x, y, z);
        TileEntity tileentity = worldIn.getTileEntity(x, y, z);
        keepInventory = true;
        if (active) {
            worldIn.setBlock(x, y, z, MyBlocks.waffle_maker_on);
            worldIn.setBlock(x, y, z, MyBlocks.waffle_maker_on);
        } else {
            worldIn.setBlock(x, y, z, MyBlocks.waffle_maker);
            worldIn.setBlock(x, y, z, MyBlocks.waffle_maker);
        }
        keepInventory = false;
        worldIn.setBlockMetadataWithNotify(x, y, z, i, 2);
        if (tileentity != null) {
            tileentity.validate();
            worldIn.setTileEntity(x, y, z, tileentity);
        }
    }

    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityWaffleMaker();
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

    public void breakBlock(World world, int x, int y, int z, Block oldblock, int oldMetadata) {
        TileEntityWaffleMaker tileentity;
        if (!keepInventory && (tileentity = (TileEntityWaffleMaker)world.getTileEntity(x, y, z)) != null) {
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
        return Item.getItemFromBlock((Block)MyBlocks.waffle_maker);
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon("chef:" + this.getUnlocalizedName().substring(5));
    }

    static {
        particleTickSpeed = 0;
    }
}

