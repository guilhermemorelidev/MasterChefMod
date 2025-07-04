/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.BlockCake
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.world.World
 */
package com.chef.mod.blocks;

import com.chef.mod.init.MyBlocks;
import com.chef.mod.init.MyItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockCake;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class MyCake
extends BlockCake {
    public MyCake() {
        this.setStepSound(soundTypeCloth);
        this.setHardness(0.5f);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        this.eatCake(world, x, y, z, player);
        return true;
    }

    public void onBlockClicked(World worldIn, int x, int y, int z, EntityPlayer playerIn) {
        this.eatCake(worldIn, x, y, z, playerIn);
    }

    private void eatCake(World worldIn, int x, int y, int z, EntityPlayer player) {
        if (player.canEat(false)) {
            MyCake block = this;
            if (this == MyBlocks.chocolate_cake) {
                player.getFoodStats().addStats(3, 0.2f);
            } else if (this == MyBlocks.strawberry_cake) {
                player.getFoodStats().addStats(2, 0.2f);
            } else if (this == MyBlocks.blueberry_cake || block == MyBlocks.grape_cake) {
                player.getFoodStats().addStats(2, 0.2f);
            } else if (this == MyBlocks.apple_cake) {
                player.getFoodStats().addStats(2, 0.2f);
            } else if (this == MyBlocks.caramel_cake) {
                player.getFoodStats().addStats(3, 0.3f);
            } else if (this == MyBlocks.carrot_cake) {
                player.getFoodStats().addStats(2, 0.2f);
            } else if (this == MyBlocks.fruit_cake) {
                player.getFoodStats().addStats(3, 0.3f);
            } else if (this == MyBlocks.pumpkin_cake) {
                player.getFoodStats().addStats(3, 0.3f);
            } else if (this == MyBlocks.cheese_cake) {
                player.getFoodStats().addStats(3, 0.3f);
            } else if (this == MyBlocks.cheese_block) {
                player.getFoodStats().addStats(4, 0.5f);
            } else {
                player.getFoodStats().addStats(0, 0.0f);
            }
            int l = worldIn.getBlockMetadata(x, y, z) + 1;
            if (l >= 6) {
                worldIn.setBlockToAir(x, y, z);
            } else {
                worldIn.setBlockMetadataWithNotify(x, y, z, l, 2);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public Item getItem(World worldIn, int x, int y, int z) {
        if (this == MyBlocks.chocolate_cake) {
            return MyItems.chocolate_cake_reed;
        }
        if (this == MyBlocks.strawberry_cake) {
            return MyItems.strawberry_cake_reed;
        }
        if (this == MyBlocks.blueberry_cake) {
            return MyItems.blueberry_cake_reed;
        }
        if (this == MyBlocks.apple_cake) {
            return MyItems.apple_cake_reed;
        }
        if (this == MyBlocks.caramel_cake) {
            return MyItems.caramel_cake_reed;
        }
        if (this == MyBlocks.carrot_cake) {
            return MyItems.carrot_cake_reed;
        }
        if (this == MyBlocks.fruit_cake) {
            return MyItems.fruit_cake_reed;
        }
        if (this == MyBlocks.pumpkin_cake) {
            return MyItems.pumpkin_cake_reed;
        }
        if (this == MyBlocks.grape_cake) {
            return MyItems.grape_cake_reed;
        }
        if (this == MyBlocks.cheese_cake) {
            return MyItems.cheese_cake_reed;
        }
        if (this == MyBlocks.cheese_block) {
            return MyItems.cheese_block_reed;
        }
        return null;
    }
}

