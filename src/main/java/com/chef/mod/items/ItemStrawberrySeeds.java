/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenTaiga
 *  net.minecraftforge.common.EnumPlantType
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.chef.mod.items;

import com.chef.mod.init.MyBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemStrawberrySeeds
extends Item
implements IPlantable {
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (side != 1) {
            return false;
        }
        if (playerIn.canPlayerEdit(x, y, z, side, stack) && playerIn.canPlayerEdit(x, y + 1, z, side, stack)) {
            if (this.canSustainPlant((IBlockAccess)worldIn, x, y, z, ForgeDirection.UP, this) && worldIn.isAirBlock(x, y + 1, z)) {
                BiomeGenBase biome = worldIn.getBiomeGenForCoords(x, z);
                if (biome instanceof BiomeGenTaiga || y > 90) {
                    worldIn.setBlock(x, y + 1, z, MyBlocks.frost_strawberries);
                } else {
                    worldIn.setBlock(x, y + 1, z, MyBlocks.strawberries);
                }
                --stack.stackSize;
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
        return world.getBlock(x, y, z) == Blocks.farmland;
    }

    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Crop;
    }

    @SideOnly(value=Side.CLIENT)
    public Block getPlant(IBlockAccess world, int x, int y, int z) {
        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
        if (biome instanceof BiomeGenTaiga || y > 90) {
            return MyBlocks.frost_strawberries;
        }
        return MyBlocks.strawberries;
    }

    public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
        return 0;
    }
}

