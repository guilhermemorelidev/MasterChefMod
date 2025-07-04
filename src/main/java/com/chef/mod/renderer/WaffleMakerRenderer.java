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

import com.chef.mod.init.MyBlocks;
import com.chef.mod.model.ModelWaffleMakerWaffles0;
import com.chef.mod.model.ModelWaffleMakerWaffles1Left;
import com.chef.mod.model.ModelWaffleMakerWaffles1Right;
import com.chef.mod.model.ModelWaffleMakerWaffles2;
import com.chef.mod.tileentity.TileEntityWaffleMaker;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class WaffleMakerRenderer
extends TileEntitySpecialRenderer {
    private ResourceLocation texture = new ResourceLocation("chef:textures/model/waffleMaker/WaffleMakerFrontOff.png");
    private ModelWaffleMakerWaffles0 modelWaffles0 = new ModelWaffleMakerWaffles0();
    private ModelWaffleMakerWaffles1Right modelWaffles1Right = new ModelWaffleMakerWaffles1Right();
    private ModelWaffleMakerWaffles1Left modelWaffles1Left = new ModelWaffleMakerWaffles1Left();
    private ModelWaffleMakerWaffles2 modelWaffles2 = new ModelWaffleMakerWaffles2();

    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float f) {
        int dir = entity.getBlockMetadata();
        Block block = entity.getWorldObj().getBlock(entity.xCoord, entity.yCoord, entity.zCoord);
        TileEntity tile = entity.getWorldObj().getTileEntity(entity.xCoord, entity.yCoord, entity.zCoord);
        TileEntityWaffleMaker tileWaffleMaker = (TileEntityWaffleMaker)tile;
        boolean isActive = false;
        String isActiveString = "Off";
        if (block == MyBlocks.waffle_maker_on) {
            isActive = true;
            isActiveString = "On";
        } else {
            isActive = false;
            isActiveString = "Off";
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y + 1.5f), (float)((float)z + 0.5f));
        switch (dir) {
            case 5: {
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case 4: {
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case 3: {
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)360.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case 2: {
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            default: {
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            }
        }
        if (tileWaffleMaker.getWaffleStage(1) > 0) {
            this.texture = tileWaffleMaker.getWaffleStage(2) > 0 ? new ResourceLocation("chef:textures/model/waffleMaker/WaffleMakerFront" + isActiveString + "Stage" + tileWaffleMaker.getWaffleStage(1) + "-" + tileWaffleMaker.getWaffleStage(2) + ".png") : new ResourceLocation("chef:textures/model/waffleMaker/WaffleMakerFront" + isActiveString + "Stage" + tileWaffleMaker.getWaffleStage(1) + ".png");
        }
        if (tileWaffleMaker.getWaffleStage(2) > 0) {
            this.texture = tileWaffleMaker.getWaffleStage(1) > 0 ? new ResourceLocation("chef:textures/model/waffleMaker/WaffleMakerFront" + isActiveString + "Stage" + tileWaffleMaker.getWaffleStage(1) + "-" + tileWaffleMaker.getWaffleStage(2) + ".png") : new ResourceLocation("chef:textures/model/waffleMaker/WaffleMakerFront" + isActiveString + "Stage" + tileWaffleMaker.getWaffleStage(2) + ".png");
        }
        if (tileWaffleMaker.getWaffleStage(1) <= 0 && tileWaffleMaker.getWaffleStage(2) <= 0) {
            this.texture = new ResourceLocation("chef:textures/model/waffleMaker/WaffleMakerFront" + isActiveString + "Stage1.png");
        }
        this.bindTexture(this.texture);
        GL11.glPushMatrix();
        if (tileWaffleMaker.isFirstCooking()) {
            if (tileWaffleMaker.isSecondCooking()) {
                this.modelWaffles2.renderModel(0.0625f);
            } else {
                this.modelWaffles1Left.renderModel(0.0625f);
            }
        }
        if (tileWaffleMaker.isSecondCooking()) {
            if (tileWaffleMaker.isFirstCooking()) {
                this.modelWaffles2.renderModel(0.0625f);
            } else {
                this.modelWaffles1Right.renderModel(0.0625f);
            }
        }
        if (!tileWaffleMaker.isFirstCooking() && !tileWaffleMaker.isSecondCooking()) {
            this.modelWaffles0.renderModel(0.0625f);
        }
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    public void renderItemTileEntityAt(TileEntity entity, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y + 1.3f), (float)((float)z + 0.5f));
        GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        this.bindTexture(new ResourceLocation("chef:textures/model/waffleMaker/WaffleMakerFrontOff.png"));
        GL11.glPushMatrix();
        this.modelWaffles0.renderModel(0.0625f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}

