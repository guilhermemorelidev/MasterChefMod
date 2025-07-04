/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.util.IIcon
 */
package com.chef.mod.blocks;

import com.chef.mod.Chef;
import com.chef.mod.proxy.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class WaffleIronPlate
extends Block {
    @SideOnly(value=Side.CLIENT)
    public static IIcon iconTop;
    @SideOnly(value=Side.CLIENT)
    public static IIcon iconBottom;

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return side == 1 ? iconTop : (side == 0 ? iconBottom : this.blockIcon);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        iconTop = iconRegister.registerIcon(this.getTextureName() + "_top");
        iconBottom = iconRegister.registerIcon(this.getTextureName() + "_bottom");
        this.blockIcon = iconRegister.registerIcon(this.getTextureName() + "_side");
    }

    public WaffleIronPlate(Material materialIn) {
        super(materialIn);
        this.setCreativeTab(Chef.tabChef);
        this.setHardness(2.0f);
        this.setResistance(10.0f);
        this.setStepSound(soundTypeMetal);
        this.setBlockBounds(0.0f, 0.0f, 0.125f, 1.0f, 0.0625f, 0.875f);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return ClientProxy.waffleIronPlateRenderType;
    }
}

