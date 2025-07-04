/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.IGrowable
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$TempCategory
 *  net.minecraft.world.biome.BiomeGenDesert
 *  net.minecraft.world.biome.BiomeGenJungle
 *  net.minecraft.world.biome.BiomeGenSavanna
 *  net.minecraft.world.biome.BiomeGenTaiga
 */
package com.chef.mod.crops;

import com.chef.mod.blocks.MangoLeaves;
import com.chef.mod.init.MyBlocks;
import com.chef.mod.init.MyItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenSavanna;
import net.minecraft.world.biome.BiomeGenTaiga;

public class Mango
extends Block
implements IGrowable {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] iconArray;
    private Random r = new Random();
    private int[] fieldA;

    public Mango() {
        super(Material.plants);
        this.setStepSound(soundTypeGrass);
        this.setTickRandomly(true);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, int x, int y, int z) {
        float f = 0.0625f;
        int stage = worldIn.getBlockMetadata(x, y, z);
        float f2 = 0.5f;
        switch (stage) {
            case 0: {
                this.setBlockBounds(0.225f, 0.2f, 0.18f, 0.725f, 1.0f, 0.67f);
                break;
            }
            default: {
                this.setBlockBounds(0.225f, 0.0f, 0.18f, 0.825f, 1.0f, 0.67f);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public Item getItem(World worldIn, int x, int y, int z) {
        return MyItems.mango;
    }

    public Item getItemDropped(int i, Random rand, int fortune) {
        return null;
    }

    public int getRenderType() {
        return 1;
    }

    public void updateTick(World worldIn, int x, int y, int z, Random rand) {
        int stage;
        super.updateTick(worldIn, x, y, z, rand);
        if (!this.canBlockStay(worldIn, x, y, z)) {
            this.dropBlockAsItem(worldIn, x, y, z, worldIn.getBlockMetadata(x, y, z), 0);
            worldIn.setBlock(x, y, z, Mango.getBlockById((int)0), 0, 2);
        } else if (worldIn.rand.nextInt(this.calculateGrowthSpeed(worldIn, x, y, z, rand)) == 0 && this.canGrowMango(worldIn, x, y, z, rand) && (stage = worldIn.getBlockMetadata(x, y, z)) < 3) {
            worldIn.setBlockMetadataWithNotify(x, y, z, stage + 1, 2);
        }
    }

    private boolean canGrowMango(World worldIn, int x, int y, int z, Random rand) {
        return worldIn.getBlock(x, y + 1, z) instanceof MangoLeaves && this.isConnectedToTree(worldIn, x, y + 1, z, rand);
    }

    private boolean isConnectedToTree(World worldIn, int x, int y, int z, Random rand) {
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
        return growthSpeed += extraGrowthSpeed;
    }

    public boolean canBlockStay(World worldIn, int x, int y, int z) {
        Block block = worldIn.getBlock(x, y + 1, z);
        return block == MyBlocks.mango_leaves;
    }

    public boolean canPlaceBlockAt(World worldIn, int x, int y, int z) {
        Block block = worldIn.getBlock(x, y + 1, z);
        return block == MyBlocks.mango_leaves;
    }

    public void onNeighborBlockChange(World worldIn, int x, int y, int z, Block block) {
        if (!this.canBlockStay(worldIn, x, y, z)) {
            this.dropBlockAsItem(worldIn, x, y, z, worldIn.getBlockMetadata(x, y, z), 0);
            worldIn.setBlock(x, y, z, Mango.getBlockById((int)0), 0, 2);
        }
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        Random rand;
        ArrayList result = super.getDrops(world, x, y, z, metadata, fortune);
        Random random = rand = world instanceof World ? world.rand : new Random();
        if (metadata == 3) {
            result.add(new ItemStack(MyItems.mango));
        }
        return result;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        if (metadata > 3) {
            metadata = 3;
        }
        return this.iconArray[metadata];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.iconArray = new IIcon[4];
        for (int i = 0; i < this.iconArray.length; ++i) {
            this.iconArray[i] = iconRegister.registerIcon("chef:" + this.getUnlocalizedName().substring(5) + "_stage" + i);
        }
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean func_149851_a(World worldIn, int x, int y, int z, boolean p_149851_5_) {
        return worldIn.getBlockMetadata(x, y, z) < 3;
    }

    public boolean func_149852_a(World worldIn, Random rand, int x, int y, int z) {
        return true;
    }

    public void func_149853_b(World worldIn, Random rand, int x, int y, int z) {
        this.grow(worldIn, x, y, z);
    }

    public void grow(World worldIn, int x, int y, int z) {
        int stage = worldIn.getBlockMetadata(x, y, z) + MathHelper.getRandomIntegerInRange((Random)worldIn.rand, (int)1, (int)2);
        if (stage > 3) {
            stage = 3;
        }
        worldIn.setBlockMetadataWithNotify(x, y, z, stage, 2);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int wx, int wy, int wz) {
        return null;
    }
}

