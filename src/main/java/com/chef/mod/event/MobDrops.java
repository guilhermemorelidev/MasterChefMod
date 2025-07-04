/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.passive.EntitySquid
 *  net.minecraftforge.event.entity.living.LivingDropsEvent
 */
package com.chef.mod.event;

import com.chef.mod.init.MyItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.Random;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class MobDrops {
    public static double rand;
    public Random r = new Random();

    @SubscribeEvent
    public void onEntityDrop(LivingDropsEvent event) {
        if (event.entityLiving instanceof EntitySquid) {
            event.entityLiving.dropItem(MyItems.raw_squid, 1 + this.r.nextInt(1));
        }
        if (event.entityLiving instanceof EntitySheep) {
            event.entityLiving.dropItem(MyItems.mutton, 1 + this.r.nextInt(2));
        }
    }
}

