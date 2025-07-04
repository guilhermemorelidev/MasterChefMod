/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.world.IBlockAccess
 */
package com.chef.mod.renderer;

import com.chef.mod.blocks.DehydrationPlate;
import com.chef.mod.proxy.ClientProxy;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class DehydrationPlateRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        this.drawDehydrationPlate(world, x, y, z, (DehydrationPlate)block, modelId, renderer);
        return true;
    }

    private void drawDehydrationPlate(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        renderer.setRenderBounds(0.125, 0.0, 0.125, 0.875, 0.0625, 0.875);
        renderer.renderStandardBlock(block, x, y, z);
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return ClientProxy.dehydrationPlateRenderType;
    }
}

