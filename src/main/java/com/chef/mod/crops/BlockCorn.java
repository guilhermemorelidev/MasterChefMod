/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.item.Item
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.EnumPlantType
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.chef.mod.crops;

import com.chef.mod.init.MyBlocks;
import com.chef.mod.init.MyItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockCorn
extends Block
implements IPlantable {
    public BlockCorn() {
        super(Material.plants);
        float f = 0.375f;
        this.setBlockBounds(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, 1.0f, 0.5f + f);
        this.setTickRandomly(true);
        this.setStepSound(soundTypeGrass);
    }

    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
        Block block = plantable.getPlant(world, x, y + 1, z);
        EnumPlantType plantType = plantable.getPlantType(world, x, y + 1, z);
        return block == this;
    }

    public void updateTick(World world, int x, int y, int z, Random rand) {
        if ((world.getBlock(x, y - 1, z) == MyBlocks.corns || this.checkForDrop(world, x, y, z)) && world.isAirBlock(x, y + 1, z)) {
            int l = 1;
            while (world.getBlock(x, y - l, z) == this) {
                ++l;
            }
            if (l < 3) {
                int i1 = world.getBlockMetadata(x, y, z);
                if (i1 == 15) {
                    world.setBlock(x, y + 1, z, (Block)this);
                    world.setBlockMetadataWithNotify(x, y, z, 0, 4);
                } else {
                    world.setBlockMetadataWithNotify(x, y, z, i1 + 1, 4);
                }
            }
        }
    }

    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y - 1, z);
        return block.canSustainPlant((IBlockAccess)world, x, y - 1, z, ForgeDirection.UP, (IPlantable)this);
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        this.checkForDrop(world, x, y, z);
    }

    protected final boolean checkForDrop(World world, int x, int y, int z) {
        if (!this.canBlockStay(world, x, y, z)) {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
            return false;
        }
        return true;
    }

    public boolean canBlockStay(World world, int x, int y, int z) {
        return this.canPlaceBlockAt(world, x, y, z);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

    public Item getItemDropped(int metadata, Random rand, int fortune) {
        return MyItems.raw_corn;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return 1;
    }

    @SideOnly(value=Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return MyItems.raw_corn;
    }

    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Plains;
    }

    public Block getPlant(IBlockAccess world, int x, int y, int z) {
        return this;
    }

    public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z);
    }
}

