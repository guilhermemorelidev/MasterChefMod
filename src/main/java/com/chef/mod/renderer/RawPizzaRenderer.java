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
import com.chef.mod.model.ModelRawPizzaBites0;
import com.chef.mod.model.ModelRawPizzaBites1;
import com.chef.mod.model.ModelRawPizzaBites2;
import com.chef.mod.model.ModelRawPizzaBites3;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RawPizzaRenderer
extends TileEntitySpecialRenderer {
    private ResourceLocation texture = new ResourceLocation("chef:textures/model/pizzas/raw/ModelRawPizzaWithTomatoSauce.png");
    private ModelRawPizzaBites0 modelBites0 = new ModelRawPizzaBites0();
    private ModelRawPizzaBites1 modelBites1 = new ModelRawPizzaBites1();
    private ModelRawPizzaBites2 modelBites2 = new ModelRawPizzaBites2();
    private ModelRawPizzaBites3 modelBites3 = new ModelRawPizzaBites3();

    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float f) {
        Block block = entity.getWorldObj().getBlock(entity.xCoord, entity.yCoord, entity.zCoord);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y + 1.5f), (float)((float)z + 0.5f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        if (block == MyBlocks.pizza_dough) {
            this.texture = new ResourceLocation("chef:textures/model/pizzas/raw/ModelPizzaDough.png");
        } else if (block == MyBlocks.raw_pizza_with_tomato_sauce) {
            this.texture = new ResourceLocation("chef:textures/model/pizzas/raw/ModelRawPizzaWithTomatoSauce.png");
        } else if (block == MyBlocks.raw_pizza_margharita) {
            this.texture = new ResourceLocation("chef:textures/model/pizzas/raw/ModelRawPizzaMargharita.png");
        } else if (block == MyBlocks.raw_pizza_onion) {
            this.texture = new ResourceLocation("chef:textures/model/pizzas/raw/ModelRawPizzaOnion.png");
        } else if (block == MyBlocks.raw_pizza_bacon) {
            this.texture = new ResourceLocation("chef:textures/model/pizzas/raw/ModelRawPizzaBacon.png");
        } else if (block == MyBlocks.raw_pizza_mushroom) {
            this.texture = new ResourceLocation("chef:textures/model/pizzas/raw/ModelRawPizzaMushroom.png");
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
        if (block == MyBlocks.pizza_dough) {
            this.texture = new ResourceLocation("chef:textures/model/pizzas/raw/ModelPizzaDough.png");
        } else if (block == MyBlocks.raw_pizza_with_tomato_sauce) {
            this.texture = new ResourceLocation("chef:textures/model/pizzas/raw/ModelRawPizzaWithTomatoSauce.png");
        } else if (block == MyBlocks.raw_pizza_margharita) {
            this.texture = new ResourceLocation("chef:textures/model/pizzas/raw/ModelRawPizzaMargharita.png");
        } else if (block == MyBlocks.raw_pizza_onion) {
            this.texture = new ResourceLocation("chef:textures/model/pizzas/raw/ModelRawPizzaOnion.png");
        } else if (block == MyBlocks.raw_pizza_bacon) {
            this.texture = new ResourceLocation("chef:textures/model/pizzas/raw/ModelRawPizzaBacon.png");
        } else if (block == MyBlocks.raw_pizza_mushroom) {
            this.texture = new ResourceLocation("chef:textures/model/pizzas/raw/ModelRawPizzaMushroom.png");
        }
        this.bindTexture(this.texture);
        GL11.glPushMatrix();
        this.modelBites0.renderModel(0.0625f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}

