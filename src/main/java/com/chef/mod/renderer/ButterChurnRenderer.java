/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.chef.mod.renderer;

import com.chef.mod.model.ModelButterChurn;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ButterChurnRenderer
extends TileEntitySpecialRenderer {
    private ResourceLocation texture = new ResourceLocation("chef:textures/model/butterchurn/ModelButterChurnMilk.png");
    private ModelButterChurn model = new ModelButterChurn();

    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float f) {
        Block block = entity.getWorldObj().getBlock(entity.xCoord, entity.yCoord, entity.zCoord);
        int metadata = entity.getBlockMetadata();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y + 1.5f), (float)((float)z + 0.5f));
        GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        switch (metadata) {
            case 2: {
                this.texture = new ResourceLocation("chef:textures/model/butterchurn/ModelButterChurnLevel2.png");
                break;
            }
            case 3: {
                this.texture = new ResourceLocation("chef:textures/model/butterchurn/ModelButterChurnLevel3.png");
                break;
            }
            case 4: {
                this.texture = new ResourceLocation("chef:textures/model/butterchurn/ModelButterChurnLevel4.png");
                break;
            }
            case 5: {
                this.texture = new ResourceLocation("chef:textures/model/butterchurn/ModelButterChurnLevel5.png");
                break;
            }
            case 6: {
                this.texture = new ResourceLocation("chef:textures/model/butterchurn/ModelButterChurnLevel5.png");
                break;
            }
            default: {
                this.texture = new ResourceLocation("chef:textures/model/butterchurn/ModelButterChurnMilk.png");
            }
        }
        this.bindTexture(this.texture);
        GL11.glPushMatrix();
        if (metadata == 0) {
            this.model.renderModel(0.0625f, false);
        } else {
            this.model.renderModel(0.0625f, true);
        }
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    public void renderItemTileEntityAt(TileEntity entity, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y + 1.3f), (float)((float)z + 0.5f));
        GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        this.bindTexture(this.texture);
        GL11.glPushMatrix();
        this.model.renderModel(0.0625f, false);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}

