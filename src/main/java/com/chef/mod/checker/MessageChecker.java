/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.event.ClickEvent
 *  net.minecraft.event.ClickEvent$Action
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 */
package com.chef.mod.checker;

import com.chef.mod.Debugger;
import com.chef.mod.checker.JavaGetUrl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class MessageChecker {
    static JavaGetUrl getUrl = new JavaGetUrl("1.7.10");

    public static void checkVersion(EntityPlayer player) {
        try {
            JavaGetUrl.main(JavaGetUrl.Subject.VERSION);
            String newVersion = JavaGetUrl.getVersion();
            if (newVersion == null || newVersion.isEmpty()) return;

            String[] newVersionArray = newVersion.split("\\.");
            String[] currentVersionArray = "5.1".split("\\.");
            int[] newV = new int[3];
            int[] curV = new int[3];

            for (int i = 0; i < 3; i++) {
                newV[i] = i < newVersionArray.length ? Integer.parseInt(newVersionArray[i]) : 0;
                curV[i] = i < currentVersionArray.length ? Integer.parseInt(currentVersionArray[i]) : 0;
            }

            boolean hasNewVersion = false;
            for (int i = 0; i < 3; i++) {
                if (newV[i] > curV[i]) {
                    hasNewVersion = true;
                    break;
                } else if (newV[i] < curV[i]) {
                    break;
                }
            }

            if (hasNewVersion) {
                ClickEvent openUrl = new ClickEvent(ClickEvent.Action.OPEN_URL, "http://www.minetronic.com/masterchef_downloads.php");
                ChatComponentText link = new ChatComponentText(EnumChatFormatting.YELLOW + "www.minetronic.com/masterchef_downloads.php");
                link.getChatStyle().setChatClickEvent(openUrl);

                player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "New version available! (V" + newVersion + ")."));
                player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Please update the master chef mod."));
                player.addChatMessage(link);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkExtraInfo(EntityPlayer player) {
        try {
            JavaGetUrl.main(JavaGetUrl.Subject.EXTRA_INFO);
            if (JavaGetUrl.extraInfoArray == null) return;

            for (String msg : JavaGetUrl.extraInfoArray) {
                if (msg != null && !msg.trim().isEmpty()) {
                    String formatted = translateAlternateColorCodes(msg);
                    player.addChatMessage(new ChatComponentText(formatted));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String translateAlternateColorCodes(String unColoredString) {
        return unColoredString.replaceAll("&", "\u00A7"); // ou outro código apropriado
    }
}

