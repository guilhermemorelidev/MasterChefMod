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

import com.chef.mod.container.ContainerSauceMaker;
import com.chef.mod.tileentity.TileEntitySauceMaker;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GuiSauceMaker
extends GuiContainer {
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("chef:textures/gui/container/sauceMaker.png");
    private final InventoryPlayer playerInventory;
    public TileEntitySauceMaker sauceMaker;

    public GuiSauceMaker(InventoryPlayer playerInv, TileEntitySauceMaker teSauceMaker) {
        super((Container)new ContainerSauceMaker(playerInv, teSauceMaker));
        this.playerInventory = playerInv;
        this.sauceMaker = teSauceMaker;
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String displayName = this.sauceMaker.hasCustomInventoryName() ? this.sauceMaker.getInventoryName() : I18n.format((String)this.sauceMaker.getInventoryName(), (Object[])new Object[0]);
        this.fontRendererObj.drawString(displayName, this.xSize / 2 - this.fontRendererObj.getStringWidth(displayName) / 2, 6, 0x404040);
        this.fontRendererObj.drawString(I18n.format((String)"container.inventory", (Object[])new Object[0]), 8, this.ySize - 96 + 2, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        int time;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        if (this.sauceMaker.isBurning()) {
            time = this.sauceMaker.getBurnTimeRemainingScaled(22);
            this.drawTexturedModalRect(this.guiLeft + 23, this.guiTop + 49 - time, 176, 22 - time, 16, time);
        }
        time = this.sauceMaker.getCookProgressScaled(68);
        this.drawTexturedModalRect(this.guiLeft + 45, this.guiTop + 30, 176, 22, time + 1, 13);
    }
}

