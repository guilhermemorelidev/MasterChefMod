/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.chef.mod.crops;

import com.chef.mod.Chef;
import com.chef.mod.init.MyItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class OliveLeavesTwo
extends Block {
    private boolean isFancy;
    @SideOnly(value=Side.CLIENT)
    private IIcon[] iconArray;

    public OliveLeavesTwo() {
        super(Material.leaves);
        this.setTickRandomly(true);
        this.setCreativeTab(Chef.tabChef);
        this.setHardness(0.2f);
        this.setLightOpacity(1);
        this.setStepSound(soundTypeGrass);
    }

    public void updateTick(World worldIn, int x, int y, int z, Random rand) {
        super.updateTick(worldIn, x, y, z, rand);
        int age = worldIn.getBlockMetadata(x, y, z);
        if (rand.nextInt(50) == 25) {
            if (age <= 3) {
                worldIn.setBlockMetadataWithNotify(x, y, z, age + 1, 2);
            } else {
                worldIn.setBlockMetadataWithNotify(x, y, z, 3, 2);
            }
        }
    }

    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer playerIn, int side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return false;
        }
        int age = worldIn.getBlockMetadata(x, y, z);
        Random rand = new Random();
        if (age > 1) {
            EntityItem entityItem = age == 2 ? new EntityItem(worldIn, (double)x, (double)(y + 1), (double)z, new ItemStack(MyItems.green_olive, 2 + rand.nextInt(5))) : new EntityItem(worldIn, (double)x, (double)(y + 1), (double)z, new ItemStack(MyItems.black_olive, 2 + rand.nextInt(5)));
            worldIn.setBlockMetadataWithNotify(x, y, z, 0, 2);
            worldIn.spawnEntityInWorld((Entity)entityItem);
            return true;
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int stage) {
        if (Minecraft.getMinecraft().gameSettings.fancyGraphics) {
            if (stage > 3) {
                return this.iconArray[3];
            }
            return this.iconArray[stage];
        }
        if (stage > 3) {
            return this.iconArray[7];
        }
        return this.iconArray[stage + 4];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        int i;
        this.iconArray = new IIcon[8];
        for (i = 0; i < this.iconArray.length / 2; ++i) {
            this.iconArray[i] = iconRegister.registerIcon("chef:" + this.getUnlocalizedName().substring(5) + "_stage" + i);
        }
        for (i = 4; i < this.iconArray.length; ++i) {
            this.iconArray[i] = iconRegister.registerIcon("chef:" + this.getUnlocalizedName().substring(5) + "_stage" + (i - 4) + "_fast");
        }
    }

    public boolean isOpaqueCube() {
        return false;
    }
}

