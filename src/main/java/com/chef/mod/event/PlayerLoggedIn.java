/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerLoggedInEvent
 */
package com.chef.mod.event;

import com.chef.mod.checker.MessageChecker;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class PlayerLoggedIn {
    @SubscribeEvent
    public void onJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.player.worldObj.isRemote) {
            MessageChecker.checkVersion(event.player);
            MessageChecker.checkExtraInfo(event.player);
        }
    }
}

