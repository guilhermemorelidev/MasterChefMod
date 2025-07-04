/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.chef.mod.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRawPizzaBites1
extends ModelBase {
    ModelRenderer BigPiece;
    ModelRenderer SmallPiece;

    public ModelRawPizzaBites1() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.BigPiece = new ModelRenderer((ModelBase)this, 0, 0);
        this.BigPiece.addBox(0.0f, 0.0f, 0.0f, 7, 1, 14);
        this.BigPiece.setRotationPoint(-7.0f, 23.0f, -7.0f);
        this.BigPiece.setTextureSize(64, 32);
        this.BigPiece.mirror = true;
        this.setRotation(this.BigPiece, 0.0f, 0.0f, 0.0f);
        this.SmallPiece = new ModelRenderer((ModelBase)this, -7, 15);
        this.SmallPiece.addBox(0.0f, 0.0f, 0.0f, 7, 1, 7);
        this.SmallPiece.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.SmallPiece.setTextureSize(64, 32);
        this.SmallPiece.mirror = true;
        this.setRotation(this.SmallPiece, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.BigPiece.render(f5);
        this.SmallPiece.render(f5);
    }

    public void renderModel(float f) {
        this.BigPiece.render(f);
        this.SmallPiece.render(f);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}

