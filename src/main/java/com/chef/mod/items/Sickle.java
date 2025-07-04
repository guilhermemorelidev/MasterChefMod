/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.world.World
 *  org.lwjgl.input.Keyboard
 */
package com.chef.mod.items;

import com.chef.mod.Chef;
import com.chef.mod.init.MyBlocks;
import com.chef.mod.init.MyItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

public class Sickle
extends Item {
    public Sickle() {
        this.setMaxDamage(100);
        this.maxStackSize = 1;
        this.setCreativeTab(Chef.tabChef);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        Block block = worldIn.getBlock(x, y, z);
        Random rand = new Random();
        if (block != MyBlocks.bell_peppers && block != MyBlocks.blueberries && block != MyBlocks.grapes && block != MyBlocks.rices && block != MyBlocks.strawberries && block != MyBlocks.tomatoes) return false;
        int age = worldIn.getBlockMetadata(x, y, z);
        if (age != 7) return false;
        if (block != MyBlocks.bell_peppers) return true;
        return age == 6 || age == 5;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
        if (Keyboard.isKeyDown((int)42) || Keyboard.isKeyDown((int)54)) {
            tooltip.add(EnumChatFormatting.RED + "The sickle is used to harvest crops without breaking them.");
            tooltip.add(EnumChatFormatting.RED + "Use " + EnumChatFormatting.BLUE + "Right Click " + EnumChatFormatting.RED + "to harvest.");
            tooltip.add(EnumChatFormatting.RED + "This only works with some crops.");
        } else {
            tooltip.add("Press shift to see information about this item.");
        }
    }

    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return toRepair.getItem() == MyItems.sickle && repair.getItem() == Items.iron_ingot;
    }
}

