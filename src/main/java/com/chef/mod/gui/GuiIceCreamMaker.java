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

import com.chef.mod.container.ContainerIceCreamMaker;
import com.chef.mod.tileentity.TileEntityIceCreamMaker;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GuiIceCreamMaker
extends GuiContainer {
    private static final ResourceLocation iceMakerGuiTextures = new ResourceLocation("chef:textures/gui/container/iceCreamMaker.png");
    private final InventoryPlayer playerInventory;
    public TileEntityIceCreamMaker iceCreamMaker;

    public GuiIceCreamMaker(InventoryPlayer playerInv, TileEntityIceCreamMaker teIceCreamMaker) {
        super((Container)new ContainerIceCreamMaker(playerInv, teIceCreamMaker));
        this.playerInventory = playerInv;
        this.iceCreamMaker = teIceCreamMaker;
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String displayName = this.iceCreamMaker.hasCustomInventoryName() ? this.iceCreamMaker.getInventoryName() : I18n.format((String)this.iceCreamMaker.getInventoryName(), (Object[])new Object[0]);
        this.fontRendererObj.drawString(displayName, this.xSize / 2 - this.fontRendererObj.getStringWidth(displayName) / 2 + 40, 6, 0x404040);
        this.fontRendererObj.drawString(I18n.format((String)"container.inventory", (Object[])new Object[0]), 28, this.ySize - 96 + 2, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        int time;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(iceMakerGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        if (this.iceCreamMaker.hasIce() && this.iceCreamMaker.ice <= 9950) {
            time = this.iceCreamMaker.getIceRemainingScaled(47);
            this.drawTexturedModalRect(this.guiLeft + 8, this.guiTop + 58 - time, 176, 90 - time, 16, time);
        } else if (this.iceCreamMaker.hasIce() && this.iceCreamMaker.ice > 9950 && this.iceCreamMaker.ice < 10000) {
            time = this.iceCreamMaker.getIceRemainingScaled(48);
            this.drawTexturedModalRect(this.guiLeft + 8, this.guiTop + 58 - time, 176, 90 - time, 16, time);
        } else {
            time = this.iceCreamMaker.getIceRemainingScaled(47);
            this.drawTexturedModalRect(this.guiLeft + 8, this.guiTop + 58 - time, 176, 90 - time, 16, time);
        }
        if (this.iceCreamMaker.isIcing()) {
            time = this.iceCreamMaker.getIceMakerProgressScaled(51);
            this.drawTexturedModalRect(this.guiLeft + 64, this.guiTop + 13, 176, 0, time + 1, 43);
        }
    }
}

