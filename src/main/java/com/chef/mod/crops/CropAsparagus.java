/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.BlockCrops
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.Item
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
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class CropAsparagus
extends BlockCrops {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] iconArray;
    private Random r = new Random();

    @SideOnly(value=Side.CLIENT)
    public Item getItem(World worldIn, int x, int y, int z) {
        return MyItems.raw_asparagus;
    }

    public Item getItemDropped(int i, Random rand, int fortune) {
        return MyItems.raw_asparagus;
    }

    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Crop;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList result = super.getDrops(world, x, y, z, metadata, fortune);
        Random rand = world instanceof World ? world.rand : new Random();
        int amount = rand.nextInt(3) + 1;
        int plusOneChance = rand.nextInt(75);
        int plusTwoChance = rand.nextInt(20);
        if (plusOneChance < fortune) {
            ++amount;
        }
        if (plusTwoChance < fortune) {
            amount += 2;
        }
        for (int i = 0; i < amount; ++i) {
            switch (metadata) {
                case 7: {
                    result.add(new ItemStack(MyItems.raw_asparagus));
                }
            }
        }
        if (metadata >= 5) {
            result.add(new ItemStack(MyItems.raw_asparagus));
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
        if (stage < 7) {
            if (stage == 6) {
                stage = 5;
            }
            return this.iconArray[stage >> 1];
        }
        return this.iconArray[3];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.iconArray = new IIcon[4];
        for (int i = 0; i < this.iconArray.length; ++i) {
            this.iconArray[i] = iconRegister.registerIcon("chef:" + this.getUnlocalizedName().substring(5) + "_stage" + i);
        }
    }
}

