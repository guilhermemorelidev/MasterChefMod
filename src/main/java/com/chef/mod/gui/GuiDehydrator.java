/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.chef.mod.gui;

import com.chef.mod.container.ContainerDehydrator;
import com.chef.mod.tileentity.TileEntityDehydrator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GuiDehydrator
extends GuiContainer {
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("chef:textures/gui/container/dehydrator.png");
    private final InventoryPlayer playerInventory;
    public TileEntityDehydrator dehydrator;

    public GuiDehydrator(InventoryPlayer playerInv, TileEntityDehydrator teDehydrator) {
        super((Container)new ContainerDehydrator(playerInv, teDehydrator));
        this.playerInventory = playerInv;
        this.dehydrator = teDehydrator;
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String displayName = "Dehy.";
        this.fontRendererObj.drawString(displayName, this.xSize / 2 - this.fontRendererObj.getStringWidth(displayName) / 2 + 68, 8, 0x404040);
        this.fontRendererObj.drawString("Inv.", 8, this.ySize - 96 + 4, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        if (this.dehydrator.isBurning()) {
            int time = this.dehydrator.getBurnTimeRemainingScaled(13);
            this.drawTexturedModalRect(this.guiLeft + 148, this.guiTop + 18 + 12 - time, 176, 12 - time, 14, time + 1);
        }
        int time2 = this.dehydrator.getFirstCookProgressScaled(30);
        int time3 = this.dehydrator.getSecondCookProgressScaled(30);
        int time4 = this.dehydrator.getThirdCookProgressScaled(30);
        int time5 = this.dehydrator.getFourthCookprogressScaled(30);
        this.drawTexturedModalRect(this.guiLeft + 38, this.guiTop + 27, 176, 14, 12, 29 - time2);
        this.drawTexturedModalRect(this.guiLeft + 66, this.guiTop + 27, 176, 14, 12, 29 - time3);
        this.drawTexturedModalRect(this.guiLeft + 94, this.guiTop + 27, 176, 14, 12, 29 - time4);
        this.drawTexturedModalRect(this.guiLeft + 122, this.guiTop + 27, 176, 14, 12, 29 - time5);
    }
}

