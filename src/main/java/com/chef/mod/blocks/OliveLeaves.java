/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLeaves
 *  net.minecraft.block.IGrowable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemDye
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.ColorizerFoliage
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$TempCategory
 *  net.minecraft.world.biome.BiomeGenDesert
 *  net.minecraft.world.biome.BiomeGenForest
 *  net.minecraft.world.biome.BiomeGenJungle
 *  net.minecraft.world.biome.BiomeGenSavanna
 *  net.minecraft.world.biome.BiomeGenTaiga
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.chef.mod.blocks;

import com.chef.mod.init.MyBlocks;
import com.chef.mod.init.MyItems;
import com.chef.mod.proxy.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.IGrowable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenSavanna;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraftforge.common.util.ForgeDirection;

public class OliveLeaves
extends BlockLeaves
implements IGrowable {
    int[] fieldA;
    @SideOnly(value=Side.CLIENT)
    private IIcon[][] iconArray;
    public static IIcon[] olivesIcon;
    private boolean recentlyGrownOlives = false;
    private int recentlyGrownOlivesTime = 0;
    private int stage = 0;
    Random rand = new Random();

    public OliveLeaves(int stage) {
        this.stage = stage;
        this.setCreativeTab(null);
    }

    @SideOnly(value=Side.CLIENT)
    public int getRenderColor(int i) {
        return (i & 3) == 1 ? ColorizerFoliage.getFoliageColorPine() : ((i & 3) == 2 ? ColorizerFoliage.getFoliageColorBirch() : super.getRenderColor(i));
    }

    @SideOnly(value=Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
        Color c = new Color(world.getBiomeGenForCoords(x, z).getBiomeFoliageColor(x, y, z));
        return c.brighter().getRGB();
    }

    protected int func_150123_b(int metadata) {
        return 100;
    }

    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 30;
    }

    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 60;
    }

    public int getDamageValue(World world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z);
    }

    public IIcon getIcon(int side, int meta) {
        Minecraft.getMinecraft();
        this.setGraphicsLevel(Minecraft.isFancyGraphicsEnabled());
        return this.iconArray[this.stage][this.field_150127_b];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        String opaque = "";
        this.iconArray = new IIcon[4][2];
        olivesIcon = new IIcon[3];
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 2; ++j) {
                opaque = j == 1 ? "_opaque" : "";
                OliveLeaves.olivesIcon[0] = iconRegister.registerIcon("chef:olives_blossom");
                OliveLeaves.olivesIcon[1] = iconRegister.registerIcon("chef:olives_green");
                OliveLeaves.olivesIcon[2] = iconRegister.registerIcon("chef:olives_black");
                this.iconArray[i][j] = iconRegister.registerIcon("chef:" + this.getUnlocalizedName().substring(5) + opaque);
            }
        }
    }

    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer playerIn, int side, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = playerIn.getCurrentEquippedItem();
        if (worldIn.isRemote) {
            return false;
        }
        if (itemstack == null || !(itemstack.getItem() instanceof ItemDye)) {
            if (this.stage > 1) {
                EntityItem entityItem = this.stage == 2 ? new EntityItem(worldIn, (double)x, (double)((float)y - 0.5f), (double)z, new ItemStack(MyItems.green_olive, 1 + this.rand.nextInt(4))) : new EntityItem(worldIn, (double)x, (double)((float)y - 0.5f), (double)z, new ItemStack(MyItems.black_olive, 1 + this.rand.nextInt(4)));
                worldIn.setBlock(x, y, z, MyBlocks.olive_leaves_stage0);
                worldIn.spawnEntityInWorld((Entity)entityItem);
                return true;
            }
        } else if (itemstack.getItem() instanceof ItemDye && itemstack.getItemDamage() == 15) {
            if (ItemDye.applyBonemeal((ItemStack)itemstack, (World)worldIn, (int)x, (int)y, (int)z, (EntityPlayer)playerIn)) {
                worldIn.playAuxSFX(2005, x, y, z, 0);
                return true;
            }
            if (!playerIn.capabilities.isCreativeMode) {
                --itemstack.stackSize;
            }
        }
        return false;
    }

    public void updateTick(World worldIn, int x, int y, int z, Random rand) {
        int metadata = worldIn.getBlockMetadata(x, y, z);
        super.updateTick(worldIn, x, y, z, rand);
        if (worldIn.getBlockLightValue(x, y, z) >= 5 && metadata == 1) {
            if (this.canGrowOlives(worldIn, x, y, z, rand) && rand.nextInt(this.calculateGrowthSpeed(worldIn, x, y, z, rand)) == 0) {
                this.growOlives(worldIn, x, y, z, rand);
                this.recentlyGrownOlivesTime = 200;
            }
            if (this.recentlyGrownOlivesTime > 0) {
                this.recentlyGrownOlives = true;
                --this.recentlyGrownOlivesTime;
            } else {
                this.recentlyGrownOlives = false;
            }
        }
    }

    public int calculateGrowthSpeed(World worldIn, int x, int y, int z, Random rand) {
        BiomeGenBase biome = worldIn.getBiomeGenForCoords(x, z);
        BiomeGenBase.TempCategory tempCategory = biome.getTempCategory();
        int growthSpeed = 20;
        int extraGrowthSpeed = 0;
        int extraSpeedLightValue = Math.round(worldIn.getBlockLightValue(x, y, z) / 3);
        int biomeExtraSpeed = Math.round(biome.temperature);
        biome.getTempCategory();
        if (tempCategory == BiomeGenBase.TempCategory.COLD) {
            biomeExtraSpeed -= 3;
        } else {
            biome.getTempCategory();
            if (tempCategory == BiomeGenBase.TempCategory.MEDIUM) {
                ++biomeExtraSpeed;
            } else {
                biome.getTempCategory();
                if (tempCategory == BiomeGenBase.TempCategory.WARM) {
                    biomeExtraSpeed += 2;
                }
            }
        }
        if (biome instanceof BiomeGenJungle) {
            --biomeExtraSpeed;
        } else if (biome instanceof BiomeGenTaiga) {
            biomeExtraSpeed -= 2;
        } else if (biome instanceof BiomeGenDesert) {
            --biomeExtraSpeed;
        } else if (biome instanceof BiomeGenSavanna) {
            biomeExtraSpeed += 5;
        } else if (biome instanceof BiomeGenForest) {
            biomeExtraSpeed += 2;
        }
        if (worldIn.isRaining()) {
            ++extraGrowthSpeed;
        }
        extraGrowthSpeed = extraSpeedLightValue + biomeExtraSpeed;
        return growthSpeed -= extraGrowthSpeed;
    }

    public void onBlockPlacedBy(World worldIn, int x, int y, int z, EntityLivingBase livingBase, ItemStack itemstack) {
        worldIn.setBlockMetadataWithNotify(x, y, z, 15, 2);
    }

    private void growOlives(World worldIn, int x, int y, int z, Random rand) {
        switch (this.stage) {
            case 0: {
                worldIn.setBlock(x, y, z, MyBlocks.olive_leaves_stage1, 1, 2);
                break;
            }
            case 1: {
                worldIn.setBlock(x, y, z, MyBlocks.olive_leaves_stage2, 1, 2);
                break;
            }
            case 2: {
                worldIn.setBlock(x, y, z, MyBlocks.olive_leaves_stage3, 1, 2);
            }
        }
    }

    private boolean canGrowOlives(World worldIn, int x, int y, int z, Random rand) {
        Block block = worldIn.getBlock(x, y - 1, z);
        if (this.stage < 3 && this.isConnectedToTree(worldIn, x, y, z) && block == Blocks.air) {
            return !this.recentlyGrownOlives;
        }
        return false;
    }

    private boolean isConnectedToTree(World worldIn, int x, int y, int z) {
        int l1;
        int b0 = 4;
        int i1 = b0 + 1;
        int b1 = 32;
        int j1 = b1 * b1;
        int k1 = b1 / 2;
        if (this.fieldA == null) {
            this.fieldA = new int[b1 * b1 * b1];
        }
        if (worldIn.checkChunksExist(x - i1, y - i1, z - i1, x + i1, y + i1, z + i1)) {
            int j2;
            int i2;
            for (l1 = -b0; l1 <= b0; ++l1) {
                for (i2 = -b0; i2 <= b0; ++i2) {
                    for (j2 = -b0; j2 <= b0; ++j2) {
                        Block block = worldIn.getBlock(x + l1, y + i2, z + j2);
                        if (!block.canSustainLeaves((IBlockAccess)worldIn, x + l1, y + i2, z + j2)) {
                            if (block.isLeaves((IBlockAccess)worldIn, x + l1, y + i2, z + j2)) {
                                this.fieldA[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -2;
                                continue;
                            }
                            this.fieldA[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -1;
                            continue;
                        }
                        this.fieldA[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = 0;
                    }
                }
            }
            for (l1 = 1; l1 <= 4; ++l1) {
                for (i2 = -b0; i2 <= b0; ++i2) {
                    for (j2 = -b0; j2 <= b0; ++j2) {
                        for (int k2 = -b0; k2 <= b0; ++k2) {
                            if (this.fieldA[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1] != l1 - 1) continue;
                            if (this.fieldA[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2) {
                                this.fieldA[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
                            }
                            if (this.fieldA[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2) {
                                this.fieldA[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
                            }
                            if (this.fieldA[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] == -2) {
                                this.fieldA[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] = l1;
                            }
                            if (this.fieldA[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] == -2) {
                                this.fieldA[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] = l1;
                            }
                            if (this.fieldA[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] == -2) {
                                this.fieldA[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] = l1;
                            }
                            if (this.fieldA[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] != -2) continue;
                            this.fieldA[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] = l1;
                        }
                    }
                }
            }
        }
        return (l1 = this.fieldA[k1 * j1 + k1 * b1 + k1]) >= 0;
    }

    public String[] func_150125_e() {
        return null;
    }

    public boolean isOpaqueCube() {
        return Blocks.leaves.isOpaqueCube();
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int damageDropped(int i) {
        return 1;
    }

    public Item getItemDropped(int i, Random rand, int fortune) {
        return Item.getItemFromBlock((Block)MyBlocks.sapling);
    }

    public boolean func_149851_a(World worldIn, int x, int y, int z, boolean bool) {
        return this.canGrowOlives(worldIn, x, y, z, this.rand);
    }

    public boolean func_149852_a(World worldIn, Random rand, int x, int y, int z) {
        return true;
    }

    public void func_149853_b(World worldIn, Random rand, int x, int y, int z) {
        this.grow(worldIn, x, y, z);
    }

    public void grow(World worldIn, int x, int y, int z) {
        if (this.stage > 3) {
            this.stage = 3;
        }
        switch (this.stage) {
            case 0: {
                if (this.rand.nextInt(2) == 0) {
                    worldIn.setBlock(x, y, z, MyBlocks.olive_leaves_stage2);
                    break;
                }
                worldIn.setBlock(x, y, z, MyBlocks.olive_leaves_stage1);
                break;
            }
            case 1: {
                if (this.rand.nextInt(2) == 0) {
                    worldIn.setBlock(x, y, z, MyBlocks.olive_leaves_stage3);
                    break;
                }
                worldIn.setBlock(x, y, z, MyBlocks.olive_leaves_stage2);
                break;
            }
            case 2: {
                worldIn.setBlock(x, y, z, MyBlocks.olive_leaves_stage3);
            }
        }
    }

    public int getRenderType() {
        return ClientProxy.oliveLeavesRenderType;
    }

    public boolean canRenderInPass(int pass) {
        ClientProxy.renderPass = pass;
        return true;
    }

    public int getRenderBlockPass() {
        return 1;
    }
}

