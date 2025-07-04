/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 */
package com.chef.mod.renderer;

import com.chef.mod.blocks.OliveLeaves;
import com.chef.mod.init.MyBlocks;
import com.chef.mod.proxy.ClientProxy;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class OliveLeavesRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.instance;
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0f, -1.0f, 0.0f);
        renderer.renderFaceYNeg(block, 0.0, 0.0, 0.0, renderer.getBlockIconFromSide(block, 0));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0f, 1.0f, 0.0f);
        renderer.renderFaceYPos(block, 0.0, 0.0, 0.0, renderer.getBlockIconFromSide(block, 1));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0f, 0.0f, -1.0f);
        renderer.renderFaceZNeg(block, 0.0, 0.0, 0.0, renderer.getBlockIconFromSide(block, 2));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0f, 0.0f, 1.0f);
        renderer.renderFaceZPos(block, 0.0, 0.0, 0.0, renderer.getBlockIconFromSide(block, 3));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0f, 0.0f, 0.0f);
        renderer.renderFaceXNeg(block, 0.0, 0.0, 0.0, renderer.getBlockIconFromSide(block, 4));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0f, 0.0f, 0.0f);
        renderer.renderFaceXPos(block, 0.0, 0.0, 0.0, renderer.getBlockIconFromSide(block, 5));
        tessellator.draw();
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        if (ClientProxy.renderPass == 0) {
            if (world.getBlock(x, y, z) == MyBlocks.olive_leaves_stage1) {
                this.drawStage(block, x, y, z, 1.0f, 1.0f, 1.0f, renderer, OliveLeaves.olivesIcon[0]);
            } else if (world.getBlock(x, y, z) == MyBlocks.olive_leaves_stage2) {
                this.drawStage(block, x, y, z, 1.0f, 1.0f, 1.0f, renderer, OliveLeaves.olivesIcon[1]);
            } else if (world.getBlock(x, y, z) == MyBlocks.olive_leaves_stage3) {
                this.drawStage(block, x, y, z, 1.0f, 1.0f, 1.0f, renderer, OliveLeaves.olivesIcon[2]);
            }
        } else if (world.getBlock(x, y, z) == MyBlocks.olive_leaves_stage0) {
            renderer.renderStandardBlock(MyBlocks.olive_leaves_stage0, x, y, z);
        } else if (world.getBlock(x, y, z) == MyBlocks.olive_leaves_stage1) {
            renderer.renderStandardBlock(MyBlocks.olive_leaves_stage1, x, y, z);
        } else if (world.getBlock(x, y, z) == MyBlocks.olive_leaves_stage2) {
            renderer.renderStandardBlock(MyBlocks.olive_leaves_stage2, x, y, z);
        } else if (world.getBlock(x, y, z) == MyBlocks.olive_leaves_stage3) {
            renderer.renderStandardBlock(MyBlocks.olive_leaves_stage3, x, y, z);
        }
        return true;
    }

    public void drawStage(Block block, int x, int y, int z, float r, float g, float b, RenderBlocks renderer, IIcon iicon) {
        renderer.enableAO = false;
        Tessellator tessellator = Tessellator.instance;
        boolean flag = false;
        float f3 = 0.5f;
        float f4 = 1.0f;
        float f5 = 0.8f;
        float f6 = 0.6f;
        float f7 = f4 * r;
        float f8 = f4 * g;
        float f9 = f4 * b;
        float f10 = f3;
        float f11 = f5;
        float f12 = f6;
        float f13 = f3;
        float f14 = f5;
        float f15 = f6;
        float f16 = f3;
        float f17 = f5;
        float f18 = f6;
        f10 = f3 * r;
        f11 = f5 * r;
        f12 = f6 * r;
        f13 = f3 * g;
        f14 = f5 * g;
        f15 = f6 * g;
        f16 = f3 * b;
        f17 = f5 * b;
        f18 = f6 * b;
        int l = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);
        if (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x, y - 1, z, 0)) {
            tessellator.setBrightness(renderer.renderMinY > 0.0 ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z));
            tessellator.setColorOpaque_F(f10, f13, f16);
            renderer.renderFaceYNeg(block, (double)x, (double)y, (double)z, iicon);
        }
        if (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x, y + 1, z, 1)) {
            tessellator.setBrightness(renderer.renderMaxY < 1.0 ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z));
            tessellator.setColorOpaque_F(f7, f8, f9);
            renderer.renderFaceYPos(block, (double)x, (double)y, (double)z, iicon);
        }
        if (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x, y, z - 1, 2)) {
            tessellator.setBrightness(renderer.renderMinZ > 0.0 ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1));
            tessellator.setColorOpaque_F(f11, f14, f17);
            renderer.renderFaceZNeg(block, (double)x, (double)y, (double)z, iicon);
        }
        if (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x, y, z + 1, 3)) {
            tessellator.setBrightness(renderer.renderMaxZ < 1.0 ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1));
            tessellator.setColorOpaque_F(f11, f14, f17);
            renderer.renderFaceZPos(block, (double)x, (double)y, (double)z, iicon);
        }
        if (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x - 1, y, z, 4)) {
            tessellator.setBrightness(renderer.renderMinX > 0.0 ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z));
            tessellator.setColorOpaque_F(f12, f15, f18);
            renderer.renderFaceXNeg(block, (double)x, (double)y, (double)z, iicon);
        }
        if (renderer.renderAllFaces || block.shouldSideBeRendered(renderer.blockAccess, x + 1, y, z, 5)) {
            tessellator.setBrightness(renderer.renderMaxX < 1.0 ? l : block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z));
            tessellator.setColorOpaque_F(f12, f15, f18);
            renderer.renderFaceXPos(block, (double)x, (double)y, (double)z, iicon);
        }
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return ClientProxy.oliveLeavesRenderType;
    }
}

