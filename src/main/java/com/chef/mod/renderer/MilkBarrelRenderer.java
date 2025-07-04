/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 */
package com.chef.mod.renderer;

import com.chef.mod.blocks.MilkBarrel;
import com.chef.mod.proxy.ClientProxy;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class MilkBarrelRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        this.drawMilkBarrel(world, x, y, z, (MilkBarrel)block, modelId, renderer);
        return true;
    }

    public void drawMilkBarrel(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        float f4;
        renderer.renderStandardBlock(block, x, y, z);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        int l = block.colorMultiplier(world, x, y, z);
        float f = (float)(l >> 16 & 0xFF) / 255.0f;
        float f1 = (float)(l >> 8 & 0xFF) / 255.0f;
        float f2 = (float)(l & 0xFF) / 255.0f;
        if (EntityRenderer.anaglyphEnable) {
            float f3 = (f * 30.0f + f1 * 59.0f + f2 * 11.0f) / 100.0f;
            f4 = (f * 30.0f + f1 * 70.0f) / 100.0f;
            float f5 = (f * 30.0f + f2 * 70.0f) / 100.0f;
            f = f3;
            f1 = f4;
            f2 = f5;
        }
        tessellator.setColorOpaque_F(f, f1, f2);
        IIcon iicon1 = MilkBarrel.getMilkBarrelIcon("inner");
        f4 = 0.125f;
        renderer.renderFaceXPos(block, (double)((float)x - 1.0f + f4), (double)y, (double)z, iicon1);
        renderer.renderFaceXNeg(block, (double)((float)x + 1.0f - f4), (double)y, (double)z, iicon1);
        renderer.renderFaceZPos(block, (double)x, (double)y, (double)((float)z - 1.0f + f4), iicon1);
        renderer.renderFaceZNeg(block, (double)x, (double)y, (double)((float)z + 1.0f - f4), iicon1);
        IIcon iicon2 = MilkBarrel.getMilkBarrelIcon("inner");
        renderer.renderFaceYPos(block, (double)x, (double)((float)y - 1.0f + 0.25f), (double)z, iicon2);
        renderer.renderFaceYNeg(block, (double)x, (double)((float)y + 1.0f - 0.75f), (double)z, iicon2);
        int i1 = world.getBlockMetadata(x, y, z);
        if (i1 > 0) {
            IIcon iicon = MilkBarrel.getMilkIcon();
            renderer.renderFaceYPos(block, (double)x, (double)((float)y - 1.0f + MilkBarrel.getRenderLiquidLevel(i1)), (double)z, iicon);
        }
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return ClientProxy.milkBarrelRenderType;
    }
}

