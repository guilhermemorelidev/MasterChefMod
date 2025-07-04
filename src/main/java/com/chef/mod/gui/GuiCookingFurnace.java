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

import com.chef.mod.container.ContainerCookingFurnace;
import com.chef.mod.tileentity.TileEntityCookingFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GuiCookingFurnace
extends GuiContainer {
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("chef:textures/gui/container/cookingFurnace.png");
    private final InventoryPlayer playerInventory;
    public TileEntityCookingFurnace cookingFurnace;

    public GuiCookingFurnace(InventoryPlayer playerInv, TileEntityCookingFurnace teCookingFurnace) {
        super((Container)new ContainerCookingFurnace(playerInv, teCookingFurnace));
        this.playerInventory = playerInv;
        this.cookingFurnace = teCookingFurnace;
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String displayName = this.cookingFurnace.hasCustomInventoryName() ? this.cookingFurnace.getInventoryName() : I18n.format((String)this.cookingFurnace.getInventoryName(), (Object[])new Object[0]);
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
        if (this.cookingFurnace.isBurning()) {
            time = this.cookingFurnace.getBurnTimeRemainingScaled(13);
            this.drawTexturedModalRect(this.guiLeft + 56, this.guiTop + 36 + 12 - time, 176, 12 - time, 14, time + 1);
        }
        if (this.cookingFurnace.isBaking()) {
            time = this.cookingFurnace.getBakeTimeRemainingScaled(12);
            if (this.cookingFurnace.bakeItem == 1) {
                this.drawTexturedModalRect(this.guiLeft + 21, this.guiTop + 49 - time, 176, 55 - time, 9, time + 1);
            } else if (this.cookingFurnace.bakeItem == 2) {
                this.drawTexturedModalRect(this.guiLeft + 21, this.guiTop + 37 + 12 - time, 176, 68 - time, 9, time + 1);
            } else if (this.cookingFurnace.bakeItem == 3) {
                this.drawTexturedModalRect(this.guiLeft + 32, this.guiTop + 39 + 11 - time, 176, 42 - time, 16, time + 1);
            }
        }
        time = this.cookingFurnace.getCookProgressScaled(24);
        this.drawTexturedModalRect(this.guiLeft + 79, this.guiTop + 34, 176, 14, time + 1, 16);
    }
}

