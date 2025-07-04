/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.chef.mod.gui;

import com.chef.mod.container.ContainerBoiler;
import com.chef.mod.tileentity.TileEntityBoiler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GuiBoiler
extends GuiContainer {
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("chef:textures/gui/container/boiler.png");
    private final InventoryPlayer playerInventory;
    public TileEntityBoiler boiler;

    public GuiBoiler(InventoryPlayer playerInv, TileEntityBoiler teBoiler) {
        super((Container)new ContainerBoiler(playerInv, teBoiler));
        this.playerInventory = playerInv;
        this.boiler = teBoiler;
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String status = this.boiler.isHeating() ? (this.boiler.heath < 1200 && this.boiler.heath >= 600 ? "Hot (Heating)" : (this.boiler.heath >= 200 && this.boiler.heath < 600 ? "Lukewarm (Heating)" : (this.boiler.heath >= 0 && this.boiler.heath < 200 ? "Cold (Heating)" : "Cold (Heating)"))) : (this.boiler.isCooling() ? (this.boiler.heath < 1200 && this.boiler.heath >= 600 ? "Hot (Cooling)" : (this.boiler.heath >= 200 && this.boiler.heath < 600 ? "Lukewarm (Cooling)" : (this.boiler.heath >= 0 && this.boiler.heath < 200 ? "Cold (Cooling)" : "Cold (Cooling)"))) : (this.boiler.isBoiling() ? "Boiling" : (!this.boiler.hasWater() ? "No water" : "Cold")));
        this.fontRendererObj.drawString("Water: " + status, this.xSize / 2 - this.fontRendererObj.getStringWidth("Water") / 2 - 34, 6, 0x404040);
        this.fontRendererObj.drawString(I18n.format((String)"container.inventory", (Object[])new Object[0]), 42, this.ySize - 96 + 3, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        int time;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        if (this.boiler.isBurning()) {
            time = this.boiler.getBurnTimeRemainingScaled(13);
            this.drawTexturedModalRect(this.guiLeft + 18, this.guiTop + 48 + 12 - time, 176, 12 - time, 14, time + 1);
        }
        if (this.boiler.hasWater()) {
            time = this.boiler.getWaterRemainingScaled(25);
            this.drawTexturedModalRect(this.guiLeft + 16, this.guiTop + 44 - time, 176, 39 - time, 21, time);
        }
        time = this.boiler.getCookProgressScaled(24);
        this.drawTexturedModalRect(this.guiLeft + 98, this.guiTop + 49, 176, 39, time + 1, 16);
        int time2 = this.boiler.getBubbleProgressScaled(24);
        this.drawTexturedModalRect(this.guiLeft + 98, this.guiTop + 44, 176, 55, time2 + 1, 4);
    }
}

