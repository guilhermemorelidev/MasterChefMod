/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.event.world.BlockEvent$HarvestDropsEvent
 */
package com.chef.mod.event;

import com.chef.mod.init.MyItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

public class BlockDrops {
    public static double rand;
    public Random r = new Random();

    @SubscribeEvent
    public void onBlockDestroyed(BlockEvent.HarvestDropsEvent event) {
        if (event.block == Blocks.ice) {
            event.drops.add(new ItemStack(MyItems.ice_shard, 2 + this.r.nextInt(4)));
        } else if (event.block == Blocks.packed_ice) {
            event.drops.add(new ItemStack(MyItems.ice_shard, 4 + this.r.nextInt(8)));
        }
    }
}

