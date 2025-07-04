/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLeaves
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.ColorizerFoliage
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$TempCategory
 *  net.minecraft.world.biome.BiomeGenDesert
 *  net.minecraft.world.biome.BiomeGenJungle
 *  net.minecraft.world.biome.BiomeGenSavanna
 *  net.minecraft.world.biome.BiomeGenTaiga
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.chef.mod.blocks;

import com.chef.mod.init.MyBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenSavanna;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraftforge.common.util.ForgeDirection;

public class MangoLeaves
extends BlockLeaves {
    int[] fieldA;
    @SideOnly(value=Side.CLIENT)
    private IIcon[] iconArray;
    private boolean recentlyGrownMango = false;
    private int recentlyGrownMangoTime = 0;

    @SideOnly(value=Side.CLIENT)
    public int getRenderColor(int i) {
        return (i & 3) == 1 ? ColorizerFoliage.getFoliageColorPine() : ((i & 3) == 2 ? ColorizerFoliage.getFoliageColorBirch() : super.getRenderColor(i));
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

    public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
        Color c = new Color(world.getBiomeGenForCoords(x, z).getBiomeFoliageColor(x, y, z));
        return c.brighter().getRGB();
    }

    public int getDamageValue(World world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        this.setGraphicsLevel(Minecraft.getMinecraft().gameSettings.fancyGraphics);
        return this.iconArray[this.field_150127_b];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.iconArray = new IIcon[2];
        this.iconArray[0] = iconRegister.registerIcon("chef:" + this.getUnlocalizedName().substring(5));
        this.iconArray[1] = iconRegister.registerIcon("chef:" + this.getUnlocalizedName().substring(5) + "_opaque");
    }

    public void updateTick(World worldIn, int x, int y, int z, Random rand) {
        int metadata = worldIn.getBlockMetadata(x, y, z);
        super.updateTick(worldIn, x, y, z, rand);
        if (worldIn.getBlockLightValue(x, y, z) >= 5 && metadata == 1) {
            if (this.canGrowMango(worldIn, x, y, z, rand) && rand.nextInt(this.calculateGrowthSpeed(worldIn, x, y, z, rand)) == 0) {
                this.growMango(worldIn, x, y - 1, z, rand);
                this.recentlyGrownMangoTime = 200;
            }
            if (this.recentlyGrownMangoTime > 0) {
                this.recentlyGrownMango = true;
                --this.recentlyGrownMangoTime;
            } else {
                this.recentlyGrownMango = false;
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
                biomeExtraSpeed += 5;
            } else {
                biome.getTempCategory();
                if (tempCategory == BiomeGenBase.TempCategory.WARM) {
                    biomeExtraSpeed -= 2;
                }
            }
        }
        if (biome instanceof BiomeGenJungle) {
            biomeExtraSpeed += 3;
        } else if (biome instanceof BiomeGenTaiga) {
            biomeExtraSpeed -= 2;
        } else if (biome instanceof BiomeGenDesert) {
            biomeExtraSpeed -= 2;
        } else if (biome instanceof BiomeGenSavanna) {
            --biomeExtraSpeed;
        }
        if (worldIn.isRaining()) {
            extraGrowthSpeed += 3;
        }
        extraGrowthSpeed = extraSpeedLightValue + biomeExtraSpeed;
        return growthSpeed -= extraGrowthSpeed;
    }

    public void onBlockPlacedBy(World worldIn, int x, int y, int z, EntityLivingBase livingBase, ItemStack itemstack) {
        worldIn.setBlockMetadataWithNotify(x, y, z, 15, 2);
    }

    private void growMango(World worldIn, int x, int y, int z, Random rand) {
        worldIn.setBlock(x, y, z, MyBlocks.mangoes);
    }

    private boolean canGrowMango(World worldIn, int x, int y, int z, Random rand) {
        Block block = worldIn.getBlock(x, y - 1, z);
        if (this.isConnectedToTree(worldIn, x, y, z) && block == Blocks.air && worldIn.getBlock(x - 1, y - 1, z - 1) == Blocks.air && worldIn.getBlock(x - 1, y - 1, z) == Blocks.air && worldIn.getBlock(x - 1, y - 1, z + 1) == Blocks.air && worldIn.getBlock(x, y - 1, z - 1) == Blocks.air && worldIn.getBlock(x, y - 1, z) == Blocks.air && worldIn.getBlock(x, y - 1, z + 1) == Blocks.air && worldIn.getBlock(x + 1, y - 1, z - 1) == Blocks.air && worldIn.getBlock(x + 1, y - 1, z) == Blocks.air && worldIn.getBlock(x + 1, y - 1, z + 1) == Blocks.air) {
            return !this.recentlyGrownMango;
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

    public int damageDropped(int i) {
        return 0;
    }

    public Item getItemDropped(int i, Random rand, int fortune) {
        return Item.getItemFromBlock((Block)MyBlocks.sapling);
    }
}

