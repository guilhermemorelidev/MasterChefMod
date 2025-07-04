/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package com.chef.mod.renderer.itemrenderer;

import com.chef.mod.renderer.ButterChurnRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemButterChurnRenderer
implements IItemRenderer {
    ButterChurnRenderer render;
    private TileEntity entity;

    public ItemButterChurnRenderer(ButterChurnRenderer render, TileEntity entity) {
        this.render = render;
        this.entity = entity;
    }

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        if (type == IItemRenderer.ItemRenderType.ENTITY) {
            GL11.glTranslatef((float)-0.5f, (float)0.0f, (float)-0.5f);
        }
        this.render.renderItemTileEntityAt(this.entity, 0.0, 0.0, 0.0, 0.0f);
    }
}

