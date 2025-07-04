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

import com.chef.mod.container.ContainerWaffleMaker;
import com.chef.mod.tileentity.TileEntityWaffleMaker;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GuiWaffleMaker
extends GuiContainer {
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("chef:textures/gui/container/waffleMaker.png");
    private final InventoryPlayer playerInventory;
    public TileEntityWaffleMaker waffleMaker;

    public GuiWaffleMaker(InventoryPlayer playerInv, TileEntityWaffleMaker teWaffleMaker) {
        super((Container)new ContainerWaffleMaker(playerInv, teWaffleMaker));
        this.playerInventory = playerInv;
        this.waffleMaker = teWaffleMaker;
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String displayName = this.waffleMaker.hasCustomInventoryName() ? this.waffleMaker.getInventoryName() : I18n.format((String)this.waffleMaker.getInventoryName(), (Object[])new Object[0]);
        this.fontRendererObj.drawString(displayName, 105, 75, 0x404040);
        this.fontRendererObj.drawString(I18n.format((String)"container.inventory", (Object[])new Object[0]), 8, this.ySize - 96 + 4, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        int time;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        if (this.waffleMaker.isBurning()) {
            time = this.waffleMaker.getBurnTimeRemainingScaled(13);
            this.drawTexturedModalRect(this.guiLeft + 10, this.guiTop + 36 + 12 - time, 176, 12 - time, 14, time + 1);
        }
        if (this.waffleMaker.firstCookTime > 0 && this.waffleMaker.firstCookTime <= 100) {
            this.drawTexturedModalRect(this.guiLeft + 72, this.guiTop + 32, 176, 14, 14, 22);
        } else if (this.waffleMaker.firstCookTime > 0 && this.waffleMaker.firstCookTime <= 250) {
            this.drawTexturedModalRect(this.guiLeft + 72, this.guiTop + 32, 176, 37, 14, 22);
        } else if (this.waffleMaker.firstCookTime > 0 && this.waffleMaker.firstCookTime <= 350) {
            this.drawTexturedModalRect(this.guiLeft + 72, this.guiTop + 32, 176, 60, 14, 22);
        }
        if (this.waffleMaker.secondCookTime > 0 && this.waffleMaker.secondCookTime <= 100) {
            this.drawTexturedModalRect(this.guiLeft + 88, this.guiTop + 32, 176, 14, 14, 22);
        } else if (this.waffleMaker.secondCookTime > 0 && this.waffleMaker.secondCookTime <= 250) {
            this.drawTexturedModalRect(this.guiLeft + 88, this.guiTop + 32, 176, 37, 14, 22);
        } else if (this.waffleMaker.secondCookTime > 0 && this.waffleMaker.secondCookTime <= 350) {
            this.drawTexturedModalRect(this.guiLeft + 88, this.guiTop + 32, 176, 60, 14, 22);
        }
        if (this.waffleMaker.isBaking()) {
            time = this.waffleMaker.getBakeTimeRemainingScaled(12);
            if (this.waffleMaker.bakeItem == 1) {
                this.drawTexturedModalRect(this.guiLeft + 139, this.guiTop + 51 - time, 176, 109 - time, 9, time + 1);
            } else if (this.waffleMaker.bakeItem == 2) {
                this.drawTexturedModalRect(this.guiLeft + 139, this.guiTop + 51 - time, 176, 122 - time, 9, time + 1);
            } else if (this.waffleMaker.bakeItem == 3) {
                this.drawTexturedModalRect(this.guiLeft + 150, this.guiTop + 51 - time, 176, 96 - time, 16, time + 1);
            }
        }
    }
}

