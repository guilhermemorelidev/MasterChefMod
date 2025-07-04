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
import com.chef.mod.model.ModelPizzaBites0;
import com.chef.mod.model.ModelPizzaBites1;
import com.chef.mod.model.ModelPizzaBites2;
import com.chef.mod.model.ModelPizzaBites3;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class PizzaRenderer
extends TileEntitySpecialRenderer {
    private ResourceLocation texture = new ResourceLocation("chef:textures/model/pizzas/baked/ModelPizzaMargharita.png");
    private ModelPizzaBites0 modelBites0 = new ModelPizzaBites0();
    private ModelPizzaBites1 modelBites1 = new ModelPizzaBites1();
    private ModelPizzaBites2 modelBites2 = new ModelPizzaBites2();
    private ModelPizzaBites3 modelBites3 = new ModelPizzaBites3();

    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float f) {
        Block block = entity.getWorldObj().getBlock(entity.xCoord, entity.yCoord, entity.zCoord);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y + 1.5f), (float)((float)z + 0.5f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        if (block == MyBlocks.pizza_margharita) {
            this.texture = new ResourceLocation("chef:textures/model/pizzas/baked/ModelPizzaMargharita.png");
        } else if (block == MyBlocks.pizza_onion) {
            this.texture = new ResourceLocation("chef:textures/model/pizzas/baked/ModelPizzaOnion.png");
        } else if (block == MyBlocks.pizza_bacon) {
            this.texture = new ResourceLocation("chef:textures/model/pizzas/baked/ModelPizzaBacon.png");
        } else if (block == MyBlocks.pizza_mushroom) {
            this.texture = new ResourceLocation("chef:textures/model/pizzas/baked/ModelPizzaMushroom.png");
        }
        this.bindTexture(this.texture);
        GL11.glPushMatrix();
        switch (entity.getBlockMetadata()) {
            case 0: {
                this.modelBites0.renderModel(0.0625f);
                break;
            }
            case 1: {
                this.modelBites1.renderModel(0.0625f);
                break;
            }
            case 2: {
                this.modelBites2.renderModel(0.0625f);
                break;
            }
            case 3: {
                this.modelBites3.renderModel(0.0625f);
                break;
            }
            default: {
                this.modelBites0.renderModel(0.0625f);
            }
        }
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    public void renderItemTileEntityAt(Block block, TileEntity entity, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y + 1.77f), (float)((float)z + 0.5f));
        GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        if (block == MyBlocks.pizza_margharita) {
            this.texture = new ResourceLocation("chef:textures/model/pizzas/baked/ModelPizzaMargharita.png");
        } else if (block == MyBlocks.pizza_onion) {
            this.texture = new ResourceLocation("chef:textures/model/pizzas/baked/ModelPizzaOnion.png");
        } else if (block == MyBlocks.pizza_bacon) {
            this.texture = new ResourceLocation("chef:textures/model/pizzas/baked/ModelPizzaBacon.png");
        } else if (block == MyBlocks.pizza_mushroom) {
            this.texture = new ResourceLocation("chef:textures/model/pizzas/baked/ModelPizzaMushroom.png");
        }
        this.bindTexture(this.texture);
        GL11.glPushMatrix();
        this.modelBites0.renderModel(0.0625f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}

