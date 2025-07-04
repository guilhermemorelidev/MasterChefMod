/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.InputEvent$KeyInputEvent
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.settings.KeyBinding
 *  org.lwjgl.input.Keyboard
 */
package com.chef.mod.handler;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeyHandler {
    public static final int CHEF_BODY_KEY = 0;
    private static final String[] keyDesc = new String[]{"key.chef.body.desc"};
    private static final int[] keyValues = new int[]{46};
    private final KeyBinding[] keys = new KeyBinding[keyValues.length];

    public KeyHandler() {
        for (int i = 0; i < keyValues.length; ++i) {
            this.keys[i] = new KeyBinding(keyDesc[i], keyValues[i], "key.chef.category");
            ClientRegistry.registerKeyBinding((KeyBinding)this.keys[i]);
        }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (!FMLClientHandler.instance().isGUIOpen(GuiChat.class)) {
            int key = Keyboard.getEventKey();
            boolean isDown = Keyboard.getEventKeyState();
            if (!isDown || key == keyValues[0]) {
                // empty if block
            }
        }
    }
}

