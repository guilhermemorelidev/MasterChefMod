/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.IWorldGenerator
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.Mod$Instance
 *  cpw.mods.fml.common.SidedProxy
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.network.IGuiHandler
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.common.MinecraftForge
 */
package com.chef.mod;

import com.chef.mod.crafting.FurnaceRecipes;
import com.chef.mod.crafting.WorkbenchRecipes;
import com.chef.mod.event.BlockDrops;
import com.chef.mod.event.MobDrops;
import com.chef.mod.event.PlayerLoggedIn;
import com.chef.mod.generate.WorldGeneration;
import com.chef.mod.handler.CraftingHandler;
import com.chef.mod.handler.GuiHandler;
import com.chef.mod.init.MyBlocks;
import com.chef.mod.init.MyItems;
import com.chef.mod.items.MaxStackSizes;
import com.chef.mod.proxy.ClientProxy;
import com.chef.mod.proxy.CommonProxy;
import com.chef.mod.tileentity.TileEntityBoiler;
import com.chef.mod.tileentity.TileEntityCookingFurnace;
import com.chef.mod.tileentity.TileEntityDehydrator;
import com.chef.mod.tileentity.TileEntityIceCreamMaker;
import com.chef.mod.tileentity.TileEntitySauceMaker;
import com.chef.mod.tileentity.TileEntityWaffleMaker;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid="chef", name="Master Chef", version="5.1")
public class Chef {
    @SidedProxy(clientSide="com.chef.mod.proxy.ClientProxy", serverSide="com.chef.mod.proxy.CommonProxy")
    public static CommonProxy proxy;
    @Mod.Instance(value="chef")
    public static Chef instance;
    WorldGeneration eventWorldGen = new WorldGeneration();
    public static CreativeTabs tabChef;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MyBlocks.init();
        MyBlocks.register();
        MyItems.init();
        MyItems.register();
        MaxStackSizes.settings();
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)this, (IGuiHandler)new GuiHandler());
        FMLCommonHandler.instance().bus().register((Object)new PlayerLoggedIn());
        GameRegistry.registerTileEntity(TileEntityCookingFurnace.class, (String)"CookingFurnace");
        GameRegistry.registerTileEntity(TileEntitySauceMaker.class, (String)"SauceMaker");
        GameRegistry.registerTileEntity(TileEntityIceCreamMaker.class, (String)"IceCreamMaker");
        GameRegistry.registerTileEntity(TileEntityBoiler.class, (String)"Boiler");
        GameRegistry.registerTileEntity(TileEntityDehydrator.class, (String)"Dehydrator");
        GameRegistry.registerTileEntity(TileEntityWaffleMaker.class, (String)"WaffleMaker");
        MinecraftForge.addGrassSeed((ItemStack)new ItemStack(MyItems.lettuce_seeds), (int)1);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        FMLCommonHandler.instance().bus().register((Object)new CraftingHandler());
        proxy.registerProxies();
        WorkbenchRecipes.recipes();
        FurnaceRecipes.recipes();
        MinecraftForge.EVENT_BUS.register((Object)new MobDrops());
        MinecraftForge.EVENT_BUS.register((Object)new BlockDrops());
        GameRegistry.registerWorldGenerator((IWorldGenerator)this.eventWorldGen, (int)0);
        ClientProxy.setCustomRenderers();
        proxy.registerRenderThings();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }

    static {
        tabChef = new CreativeTabs("tabChef"){

            public Item getTabIconItem() {
                return new ItemStack(MyItems.caramel_ice_cream_with_chocolate_streaks).getItem();
            }
        };
    }
}

