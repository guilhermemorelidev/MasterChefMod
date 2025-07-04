/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockCrops
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.EnumPlantType
 */
package com.chef.mod.crops;

import com.chef.mod.init.MyBlocks;
import com.chef.mod.init.MyItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class MangoPlant
extends BlockCrops {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] iconArray;
    private Random r = new Random();
    private boolean broke = true;

    @SideOnly(value=Side.CLIENT)
    public Item getItem(World worldIn, int x, int y, int z) {
        return MyItems.mango_pit;
    }

    public int quantityDropped(Random rand) {
        return 0;
    }

    public int getRenderType() {
        return 1;
    }

    public void updateTick(World worldIn, int x, int y, int z, Random rand) {
        super.updateTick(worldIn, x, y, z, rand);
        if (worldIn.getBlockMetadata(x, y, z) == 7 && rand.nextInt(5) == 0) {
            this.broke = false;
            worldIn.setBlock(x, y - 1, z, (Block)Blocks.grass);
            worldIn.setBlock(x, y, z, MyBlocks.sapling);
            worldIn.setBlockMetadataWithNotify(x, y, z, 0, 2);
        }
    }

    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Crop;
    }

    protected Item func_149866_i() {
        return null;
    }

    protected Item func_149865_P() {
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int stage) {
        if (stage >= 0 && stage < 3) {
            return this.iconArray[0];
        }
        if (stage >= 3 && stage < 6) {
            return this.iconArray[1];
        }
        if (stage >= 6 && stage < 8) {
            return this.iconArray[2];
        }
        return this.iconArray[0];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.iconArray = new IIcon[3];
        for (int i = 0; i < this.iconArray.length; ++i) {
            this.iconArray[i] = iconRegister.registerIcon("chef:" + this.getUnlocalizedName().substring(5) + "_stage" + i);
        }
    }

    public boolean func_149851_a(World worldIn, int x, int y, int z, boolean p_149851_5_) {
        return true;
    }

    public void func_149863_m(World worldIn, int x, int y, int z) {
        int stage = worldIn.getBlockMetadata(x, y, z) + MathHelper.getRandomIntegerInRange((Random)worldIn.rand, (int)2, (int)5);
        if (stage > 7) {
            this.broke = false;
            worldIn.setBlock(x, y - 1, z, Blocks.dirt);
            worldIn.setBlock(x, y, z, MyBlocks.sapling);
            worldIn.setBlockMetadataWithNotify(x, y, z, 0, 2);
        } else {
            worldIn.setBlockMetadataWithNotify(x, y, z, stage, 2);
        }
    }

    public void breakBlock(World worldIn, int x, int y, int z, Block block, int flag) {
        super.breakBlock(worldIn, x, y, z, block, flag);
        this.broke = true;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        Random rand;
        ArrayList result = super.getDrops(world, x, y, z, metadata, fortune);
        Random random = rand = world instanceof World ? world.rand : new Random();
        if (this.broke) {
            result.add(new ItemStack(MyItems.mango_pit));
        }
        return result;
    }
}

