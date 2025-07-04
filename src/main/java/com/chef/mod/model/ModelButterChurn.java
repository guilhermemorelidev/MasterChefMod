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

public class ModelButterChurn
extends ModelBase {
    ModelRenderer Side1;
    ModelRenderer Side2;
    ModelRenderer Side3;
    ModelRenderer Side4;
    ModelRenderer Top1;
    ModelRenderer Top2;
    ModelRenderer Top3;
    ModelRenderer Top4;
    ModelRenderer Bottom;
    ModelRenderer Milk;

    public ModelButterChurn() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Side1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Side1.addBox(0.0f, 0.0f, 0.0f, 1, 15, 8);
        this.Side1.setRotationPoint(4.0f, 9.0f, -4.0f);
        this.Side1.setTextureSize(64, 32);
        this.Side1.mirror = true;
        this.setRotation(this.Side1, 0.0f, 0.0f, 0.0f);
        this.Side2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Side2.addBox(0.0f, 0.0f, 0.0f, 1, 15, 8);
        this.Side2.setRotationPoint(-5.0f, 9.0f, -4.0f);
        this.Side2.setTextureSize(64, 32);
        this.Side2.mirror = true;
        this.setRotation(this.Side2, 0.0f, 0.0f, 0.0f);
        this.Side3 = new ModelRenderer((ModelBase)this, 18, 0);
        this.Side3.addBox(0.0f, 0.0f, 0.0f, 8, 15, 1);
        this.Side3.setRotationPoint(-4.0f, 9.0f, 4.0f);
        this.Side3.setTextureSize(64, 32);
        this.Side3.mirror = true;
        this.setRotation(this.Side3, 0.0f, 0.0f, 0.0f);
        this.Side4 = new ModelRenderer((ModelBase)this, 18, 0);
        this.Side4.addBox(0.0f, 0.0f, 0.0f, 8, 15, 1);
        this.Side4.setRotationPoint(-4.0f, 9.0f, -5.0f);
        this.Side4.setTextureSize(64, 32);
        this.Side4.mirror = true;
        this.setRotation(this.Side4, 0.0f, 0.0f, 0.0f);
        this.Top1 = new ModelRenderer((ModelBase)this, 36, 9);
        this.Top1.addBox(0.0f, 0.0f, 2.0f, 4, 1, 2);
        this.Top1.setRotationPoint(-2.0f, 8.0f, 0.0f);
        this.Top1.setTextureSize(64, 32);
        this.Top1.mirror = true;
        this.setRotation(this.Top1, 0.0f, 0.0f, 0.0f);
        this.Top2 = new ModelRenderer((ModelBase)this, 36, 9);
        this.Top2.addBox(-2.0f, 8.0f, -4.0f, 4, 1, 2);
        this.Top2.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.Top2.setTextureSize(64, 32);
        this.Top2.mirror = true;
        this.setRotation(this.Top2, 0.0f, 0.0f, 0.0f);
        this.Top3 = new ModelRenderer((ModelBase)this, 36, 0);
        this.Top3.addBox(0.0f, 0.0f, 0.0f, 2, 1, 8);
        this.Top3.setRotationPoint(2.0f, 8.0f, -4.0f);
        this.Top3.setTextureSize(64, 32);
        this.Top3.mirror = true;
        this.setRotation(this.Top3, 0.0f, 0.0f, 0.0f);
        this.Top4 = new ModelRenderer((ModelBase)this, 36, 0);
        this.Top4.addBox(-4.0f, 8.0f, -4.0f, 2, 1, 8);
        this.Top4.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.Top4.setTextureSize(64, 32);
        this.Top4.mirror = true;
        this.setRotation(this.Top4, 0.0f, 0.0f, 0.0f);
        this.Bottom = new ModelRenderer((ModelBase)this, 32, 23);
        this.Bottom.addBox(0.0f, 0.0f, 0.0f, 8, 1, 8);
        this.Bottom.setRotationPoint(-4.0f, 23.0f, -4.0f);
        this.Bottom.setTextureSize(64, 32);
        this.Bottom.mirror = true;
        this.setRotation(this.Bottom, 0.0f, 0.0f, 0.0f);
        this.Milk = new ModelRenderer((ModelBase)this, 0, 23);
        this.Milk.addBox(0.0f, 0.0f, 0.0f, 8, 1, 8);
        this.Milk.setRotationPoint(-4.0f, 12.0f, -4.0f);
        this.Milk.setTextureSize(64, 32);
        this.Milk.mirror = true;
        this.setRotation(this.Milk, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Side1.render(f5);
        this.Side2.render(f5);
        this.Side3.render(f5);
        this.Side4.render(f5);
        this.Top1.render(f5);
        this.Top2.render(f5);
        this.Top3.render(f5);
        this.Top4.render(f5);
        this.Bottom.render(f5);
        this.Milk.render(f5);
    }

    public void renderModel(float f, boolean isFilled) {
        this.Side1.render(f);
        this.Side2.render(f);
        this.Side3.render(f);
        this.Side4.render(f);
        this.Top1.render(f);
        this.Top2.render(f);
        this.Top3.render(f);
        this.Top4.render(f);
        this.Bottom.render(f);
        if (isFilled) {
            this.Milk.render(f);
        }
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

