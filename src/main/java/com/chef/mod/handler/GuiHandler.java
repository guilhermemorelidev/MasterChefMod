/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.IGuiHandler
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package com.chef.mod.handler;

import com.chef.mod.container.ContainerBoiler;
import com.chef.mod.container.ContainerCookingFurnace;
import com.chef.mod.container.ContainerDehydrator;
import com.chef.mod.container.ContainerIceCreamMaker;
import com.chef.mod.container.ContainerSauceMaker;
import com.chef.mod.container.ContainerWaffleMaker;
import com.chef.mod.gui.GuiBoiler;
import com.chef.mod.gui.GuiCookingFurnace;
import com.chef.mod.gui.GuiDehydrator;
import com.chef.mod.gui.GuiIceCreamMaker;
import com.chef.mod.gui.GuiSauceMaker;
import com.chef.mod.gui.GuiWaffleMaker;
import com.chef.mod.tileentity.TileEntityBoiler;
import com.chef.mod.tileentity.TileEntityCookingFurnace;
import com.chef.mod.tileentity.TileEntityDehydrator;
import com.chef.mod.tileentity.TileEntityIceCreamMaker;
import com.chef.mod.tileentity.TileEntitySauceMaker;
import com.chef.mod.tileentity.TileEntityWaffleMaker;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler
implements IGuiHandler {
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x, y, z);
        if (entity != null) {
            switch (ID) {
                case 0: {
                    if (!(entity instanceof TileEntityCookingFurnace)) break;
                    return new ContainerCookingFurnace(player.inventory, (TileEntityCookingFurnace)entity);
                }
                case 1: {
                    if (!(entity instanceof TileEntitySauceMaker)) break;
                    return new ContainerSauceMaker(player.inventory, (TileEntitySauceMaker)entity);
                }
                case 2: {
                    if (!(entity instanceof TileEntityIceCreamMaker)) break;
                    return new ContainerIceCreamMaker(player.inventory, (TileEntityIceCreamMaker)entity);
                }
                case 3: {
                    if (!(entity instanceof TileEntityBoiler)) break;
                    return new ContainerBoiler(player.inventory, (TileEntityBoiler)entity);
                }
                case 5: {
                    if (!(entity instanceof TileEntityDehydrator)) break;
                    return new ContainerDehydrator(player.inventory, (TileEntityDehydrator)entity);
                }
                case 4: {
                    if (!(entity instanceof TileEntityWaffleMaker)) break;
                    return new ContainerWaffleMaker(player.inventory, (TileEntityWaffleMaker)entity);
                }
            }
        }
        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x, y, z);
        if (entity != null) {
            switch (ID) {
                case 0: {
                    if (!(entity instanceof TileEntityCookingFurnace)) break;
                    return new GuiCookingFurnace(player.inventory, (TileEntityCookingFurnace)entity);
                }
                case 1: {
                    if (!(entity instanceof TileEntitySauceMaker)) break;
                    return new GuiSauceMaker(player.inventory, (TileEntitySauceMaker)entity);
                }
                case 2: {
                    if (!(entity instanceof TileEntityIceCreamMaker)) break;
                    return new GuiIceCreamMaker(player.inventory, (TileEntityIceCreamMaker)entity);
                }
                case 3: {
                    if (!(entity instanceof TileEntityBoiler)) break;
                    return new GuiBoiler(player.inventory, (TileEntityBoiler)entity);
                }
                case 5: {
                    if (!(entity instanceof TileEntityDehydrator)) break;
                    return new GuiDehydrator(player.inventory, (TileEntityDehydrator)entity);
                }
                case 4: {
                    if (!(entity instanceof TileEntityWaffleMaker)) break;
                    return new GuiWaffleMaker(player.inventory, (TileEntityWaffleMaker)entity);
                }
            }
        }
        return null;
    }
}

