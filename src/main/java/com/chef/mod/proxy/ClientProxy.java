/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.item.Item
 *  net.minecraft.world.World
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.MinecraftForgeClient
 */
package com.chef.mod.proxy;

import com.chef.mod.init.MyBlocks;
import com.chef.mod.particle.EntityParticleFXVapor;
import com.chef.mod.proxy.CommonProxy;
import com.chef.mod.renderer.ButterChurnRenderer;
import com.chef.mod.renderer.DehydrationPlateRenderer;
import com.chef.mod.renderer.ItemPizzaRenderer;
import com.chef.mod.renderer.MilkBarrelRenderer;
import com.chef.mod.renderer.OliveLeavesRenderer;
import com.chef.mod.renderer.PizzaRenderer;
import com.chef.mod.renderer.RawPizzaRenderer;
import com.chef.mod.renderer.WaffleIronPlateRenderer;
import com.chef.mod.renderer.WaffleMakerRenderer;
import com.chef.mod.renderer.itemrenderer.ItemButterChurnRenderer;
import com.chef.mod.renderer.itemrenderer.ItemRawPizzaRenderer;
import com.chef.mod.renderer.itemrenderer.ItemWaffleMakerRenderer;
import com.chef.mod.tileentity.TileEntityButterChurn;
import com.chef.mod.tileentity.TileEntityPizza;
import com.chef.mod.tileentity.TileEntityRawPizza;
import com.chef.mod.tileentity.TileEntityWaffleMaker;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy
extends CommonProxy {
    public static int milkBarrelRenderType;
    public static int oliveLeavesRenderType;
    public static int waffleIronPlateRenderType;
    public static int dehydrationPlateRenderType;
    public static int renderPass;

    @Override
    public void registerProxies() {
    }

    @SideOnly(value=Side.CLIENT)
    public static void setCustomRenderers() {
        milkBarrelRenderType = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new MilkBarrelRenderer());
        waffleIronPlateRenderType = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new WaffleIronPlateRenderer());
        dehydrationPlateRenderType = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new DehydrationPlateRenderer());
        oliveLeavesRenderType = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new OliveLeavesRenderer());
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void registerRenderThings() {
        WaffleMakerRenderer renderWaffleMaker = new WaffleMakerRenderer();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWaffleMaker.class, (TileEntitySpecialRenderer)renderWaffleMaker);
        MinecraftForgeClient.registerItemRenderer((Item)Item.getItemFromBlock((Block)MyBlocks.waffle_maker), (IItemRenderer)new ItemWaffleMakerRenderer(renderWaffleMaker, new TileEntityWaffleMaker()));
        ButterChurnRenderer renderButterChurn = new ButterChurnRenderer();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityButterChurn.class, (TileEntitySpecialRenderer)renderButterChurn);
        MinecraftForgeClient.registerItemRenderer((Item)Item.getItemFromBlock((Block)MyBlocks.butter_churn), (IItemRenderer)new ItemButterChurnRenderer(renderButterChurn, new TileEntityButterChurn()));
        RawPizzaRenderer renderRawPizza = new RawPizzaRenderer();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRawPizza.class, (TileEntitySpecialRenderer)renderRawPizza);
        MinecraftForgeClient.registerItemRenderer((Item)Item.getItemFromBlock((Block)MyBlocks.pizza_dough), (IItemRenderer)new ItemRawPizzaRenderer(MyBlocks.pizza_dough, renderRawPizza, new TileEntityRawPizza()));
        MinecraftForgeClient.registerItemRenderer((Item)Item.getItemFromBlock((Block)MyBlocks.raw_pizza_with_tomato_sauce), (IItemRenderer)new ItemRawPizzaRenderer(MyBlocks.raw_pizza_with_tomato_sauce, renderRawPizza, new TileEntityRawPizza()));
        MinecraftForgeClient.registerItemRenderer((Item)Item.getItemFromBlock((Block)MyBlocks.raw_pizza_margharita), (IItemRenderer)new ItemRawPizzaRenderer(MyBlocks.raw_pizza_margharita, renderRawPizza, new TileEntityRawPizza()));
        MinecraftForgeClient.registerItemRenderer((Item)Item.getItemFromBlock((Block)MyBlocks.raw_pizza_onion), (IItemRenderer)new ItemRawPizzaRenderer(MyBlocks.raw_pizza_onion, renderRawPizza, new TileEntityRawPizza()));
        MinecraftForgeClient.registerItemRenderer((Item)Item.getItemFromBlock((Block)MyBlocks.raw_pizza_bacon), (IItemRenderer)new ItemRawPizzaRenderer(MyBlocks.raw_pizza_bacon, renderRawPizza, new TileEntityRawPizza()));
        MinecraftForgeClient.registerItemRenderer((Item)Item.getItemFromBlock((Block)MyBlocks.raw_pizza_mushroom), (IItemRenderer)new ItemRawPizzaRenderer(MyBlocks.raw_pizza_mushroom, renderRawPizza, new TileEntityRawPizza()));
        PizzaRenderer renderPizza = new PizzaRenderer();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPizza.class, (TileEntitySpecialRenderer)renderPizza);
        MinecraftForgeClient.registerItemRenderer((Item)Item.getItemFromBlock((Block)MyBlocks.pizza_margharita), (IItemRenderer)new ItemPizzaRenderer(MyBlocks.pizza_margharita, renderPizza, new TileEntityPizza()));
        MinecraftForgeClient.registerItemRenderer((Item)Item.getItemFromBlock((Block)MyBlocks.pizza_onion), (IItemRenderer)new ItemPizzaRenderer(MyBlocks.pizza_onion, renderPizza, new TileEntityPizza()));
        MinecraftForgeClient.registerItemRenderer((Item)Item.getItemFromBlock((Block)MyBlocks.pizza_bacon), (IItemRenderer)new ItemPizzaRenderer(MyBlocks.pizza_bacon, renderPizza, new TileEntityPizza()));
        MinecraftForgeClient.registerItemRenderer((Item)Item.getItemFromBlock((Block)MyBlocks.pizza_mushroom), (IItemRenderer)new ItemPizzaRenderer(MyBlocks.pizza_mushroom, renderPizza, new TileEntityPizza()));
        MinecraftForgeClient.registerItemRenderer((Item)Item.getItemFromBlock((Block)MyBlocks.pizza_dough), (IItemRenderer)new ItemRawPizzaRenderer(MyBlocks.pizza_dough, renderRawPizza, new TileEntityRawPizza()));
        MinecraftForgeClient.registerItemRenderer((Item)Item.getItemFromBlock((Block)MyBlocks.pizza_dough), (IItemRenderer)new ItemRawPizzaRenderer(MyBlocks.pizza_dough, renderRawPizza, new TileEntityRawPizza()));
        MinecraftForgeClient.registerItemRenderer((Item)Item.getItemFromBlock((Block)MyBlocks.pizza_dough), (IItemRenderer)new ItemRawPizzaRenderer(MyBlocks.pizza_dough, renderRawPizza, new TileEntityRawPizza()));
        MinecraftForgeClient.registerItemRenderer((Item)Item.getItemFromBlock((Block)MyBlocks.pizza_dough), (IItemRenderer)new ItemRawPizzaRenderer(MyBlocks.pizza_dough, renderRawPizza, new TileEntityRawPizza()));
    }

    @Override
    public void generateVaporParticles(World world, int x, int y, int z) {
        double motionX = world.rand.nextGaussian() * 0.01;
        double motionY = world.rand.nextGaussian() * 0.05;
        double motionZ = world.rand.nextGaussian() * 0.01;
        EntityParticleFXVapor particleVapor = new EntityParticleFXVapor(world, (double)x + 0.5, (double)y + 1.2, (double)z + 0.5, motionX, motionY, motionZ, 1.0f);
        Minecraft.getMinecraft().effectRenderer.addEffect((EntityFX)particleVapor);
    }
}

