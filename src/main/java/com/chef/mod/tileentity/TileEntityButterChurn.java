/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 */
package com.chef.mod.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityButterChurn
extends TileEntity {
    private int butterTime = 0;

    public void updateEntity() {
        int level = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
        if (level == 5) {
            if (this.butterTime < 12000) {
                ++this.butterTime;
            } else {
                this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, level + 1, 2);
            }
        } else {
            this.butterTime = 0;
        }
    }

    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.butterTime = compound.getShort("ButterTime");
    }

    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setShort("ButterTime", (short)this.butterTime);
    }
}

