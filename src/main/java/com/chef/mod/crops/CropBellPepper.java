/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockCrops
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemDye
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.EnumPlantType
 */
package com.chef.mod.crops;

import com.chef.mod.init.MyItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class CropBellPepper
extends BlockCrops {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] iconArray;
    private Random r = new Random();

    @SideOnly(value=Side.CLIENT)
    public Item getItem(World worldIn, int x, int y, int z) {
        return MyItems.bell_pepper_seeds;
    }

    public Item getItemDropped(int i, Random rand, int fortune) {
        return MyItems.bell_pepper_seeds;
    }

    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Crop;
    }

    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer playerIn, int side, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = playerIn.getCurrentEquippedItem();
        int age = worldIn.getBlockMetadata(x, y, z);
        if (worldIn.isRemote) {
            return false;
        }
        if (itemstack != null) {
            if (itemstack.getItem() == MyItems.sickle) {
                Random rand = new Random();
                if (age >= 5) {
                    worldIn.setBlock(x, y, z, (Block)this, 1, 2);
                    switch (age) {
                        case 5: {
                            EntityItem entityItem = new EntityItem(worldIn, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, new ItemStack(MyItems.green_bell_pepper, 1 + rand.nextInt(3)));
                            worldIn.spawnEntityInWorld((Entity)entityItem);
                            break;
                        }
                        case 6: {
                            EntityItem entityItem = new EntityItem(worldIn, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, new ItemStack(MyItems.yellow_bell_pepper, 1 + rand.nextInt(3)));
                            worldIn.spawnEntityInWorld((Entity)entityItem);
                            break;
                        }
                        case 7: {
                            EntityItem entityItem = new EntityItem(worldIn, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, new ItemStack(MyItems.red_bell_pepper, 1 + rand.nextInt(3)));
                            worldIn.spawnEntityInWorld((Entity)entityItem);
                        }
                    }
                    itemstack.damageItem(1, (EntityLivingBase)playerIn);
                    if (itemstack.getItemDamage() >= itemstack.getMaxDamage()) {
                        --itemstack.stackSize;
                    }
                }
            } else if (itemstack.getItem() instanceof ItemDye && itemstack.getItemDamage() == 15) {
                if (ItemDye.applyBonemeal((ItemStack)itemstack, (World)worldIn, (int)x, (int)y, (int)z, (EntityPlayer)playerIn)) {
                    worldIn.playAuxSFX(2005, x, y, z, 0);
                    return true;
                }
                if (!playerIn.capabilities.isCreativeMode) {
                    --itemstack.stackSize;
                }
            }
        }
        return true;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int stage, int fortune) {
        ArrayList result = super.getDrops(world, x, y, z, stage, fortune);
        int amount = world.rand.nextInt(3) + 1;
        int plusOne = 1;
        int plusTwo = 2;
        int plusOneChance = world.rand.nextInt(75);
        int plusTwoChance = world.rand.nextInt(25);
        if (plusOneChance < fortune) {
            amount += plusOne;
        }
        if (plusTwoChance < fortune) {
            amount += plusTwo;
        }
        block5: for (int i = 0; i < amount; ++i) {
            switch (stage) {
                case 4: {
                    result.add(new ItemStack(MyItems.green_bell_pepper));
                    continue block5;
                }
                case 5: {
                    result.add(new ItemStack(MyItems.yellow_bell_pepper));
                    continue block5;
                }
                case 7: {
                    result.add(new ItemStack(MyItems.red_bell_pepper));
                }
            }
        }
        if (stage == 4 || stage == 5 || stage == 7) {
            result.add(new ItemStack(MyItems.bell_pepper_seeds));
        }
        return result;
    }

    protected Item func_149866_i() {
        return null;
    }

    protected Item func_149865_P() {
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int stage) {
        if (stage < 0 || stage > 7) {
            stage = 7;
        }
        return this.iconArray[stage];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.iconArray = new IIcon[8];
        for (int i = 0; i < this.iconArray.length; ++i) {
            this.iconArray[i] = iconRegister.registerIcon("chef:" + this.getUnlocalizedName().substring(5) + "_stage" + i);
        }
    }
}

