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

public class ModelPizzaBites1
extends ModelBase {
    ModelRenderer Base;
    ModelRenderer Border1;
    ModelRenderer Border2;
    ModelRenderer Border3;
    ModelRenderer Border4;
    ModelRenderer Shape1;

    public ModelPizzaBites1() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Base = new ModelRenderer((ModelBase)this, 0, 0);
        this.Base.addBox(0.0f, 0.0f, 0.0f, 7, 1, 14);
        this.Base.setRotationPoint(-7.0f, 23.0f, -7.0f);
        this.Base.setTextureSize(64, 32);
        this.Base.mirror = true;
        this.setRotation(this.Base, 0.0f, 0.0f, 0.0f);
        this.Border1 = new ModelRenderer((ModelBase)this, -1, 15);
        this.Border1.addBox(0.0f, 0.0f, 0.0f, 1, 1, 7);
        this.Border1.setRotationPoint(6.0f, 22.0f, 0.0f);
        this.Border1.setTextureSize(64, 32);
        this.Border1.mirror = true;
        this.setRotation(this.Border1, 0.0f, 0.0f, 0.0f);
        this.Border2 = new ModelRenderer((ModelBase)this, 13, 0);
        this.Border2.addBox(0.0f, 0.0f, 0.0f, 1, 1, 14);
        this.Border2.setRotationPoint(-7.0f, 22.0f, -7.0f);
        this.Border2.setTextureSize(64, 32);
        this.Border2.mirror = true;
        this.setRotation(this.Border2, 0.0f, 0.0f, 0.0f);
        this.Border3 = new ModelRenderer((ModelBase)this, 14, 13);
        this.Border3.addBox(0.0f, 0.0f, 0.0f, 6, 1, 1);
        this.Border3.setRotationPoint(-6.0f, 22.0f, -7.0f);
        this.Border3.setTextureSize(64, 32);
        this.Border3.mirror = true;
        this.setRotation(this.Border3, 0.0f, 0.0f, 0.0f);
        this.Border4 = new ModelRenderer((ModelBase)this, 14, 13);
        this.Border4.addBox(0.0f, 0.0f, 0.0f, 12, 1, 1);
        this.Border4.setRotationPoint(-6.0f, 22.0f, 6.0f);
        this.Border4.setTextureSize(64, 32);
        this.Border4.mirror = true;
        this.setRotation(this.Border4, 0.0f, 0.0f, 0.0f);
        this.Shape1 = new ModelRenderer((ModelBase)this, -7, 15);
        this.Shape1.addBox(0.0f, 0.0f, 0.0f, 7, 1, 7);
        this.Shape1.setRotationPoint(0.0f, 23.0f, 0.0f);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Base.render(f5);
        this.Border1.render(f5);
        this.Border2.render(f5);
        this.Border3.render(f5);
        this.Border4.render(f5);
        this.Shape1.render(f5);
    }

    public void renderModel(float f) {
        this.Base.render(f);
        this.Border1.render(f);
        this.Border2.render(f);
        this.Border3.render(f);
        this.Border4.render(f);
        this.Shape1.render(f);
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

