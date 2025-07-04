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

public class ModelRawPizzaBites3
extends ModelBase {
    ModelRenderer Base;

    public ModelRawPizzaBites3() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Base = new ModelRenderer((ModelBase)this, 7, 7);
        this.Base.addBox(0.0f, 0.0f, 0.0f, 7, 1, 7);
        this.Base.setRotationPoint(-7.0f, 23.0f, -7.0f);
        this.Base.setTextureSize(64, 32);
        this.Base.mirror = true;
        this.setRotation(this.Base, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Base.render(f5);
    }

    public void renderModel(float f) {
        this.Base.render(f);
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

