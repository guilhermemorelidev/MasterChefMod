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

public class ModelWaffleMakerWaffles1Right
extends ModelBase {
    ModelRenderer Base;
    ModelRenderer Base2;
    ModelRenderer Pillar;
    ModelRenderer Plateholder;
    ModelRenderer Plate;
    ModelRenderer Waffle2;

    public ModelWaffleMakerWaffles1Right() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Base = new ModelRenderer((ModelBase)this, 23, 6);
        this.Base.addBox(0.0f, 0.0f, 0.0f, 6, 7, 6);
        this.Base.setRotationPoint(-3.0f, 17.0f, -3.0f);
        this.Base.setTextureSize(64, 32);
        this.Base.mirror = true;
        this.setRotation(this.Base, 0.0f, 0.0f, 0.0f);
        this.Base2 = new ModelRenderer((ModelBase)this, 11, 13);
        this.Base2.addBox(0.0f, 0.0f, 0.0f, 4, 2, 4);
        this.Base2.setRotationPoint(-2.0f, 15.0f, -2.0f);
        this.Base2.setTextureSize(64, 32);
        this.Base2.mirror = true;
        this.setRotation(this.Base2, 0.0f, 0.0f, 0.0f);
        this.Pillar = new ModelRenderer((ModelBase)this, 6, 13);
        this.Pillar.addBox(0.0f, 0.0f, 0.0f, 2, 4, 2);
        this.Pillar.setRotationPoint(-1.0f, 11.0f, -1.0f);
        this.Pillar.setTextureSize(64, 32);
        this.Pillar.mirror = true;
        this.setRotation(this.Pillar, 0.0f, 0.0f, 0.0f);
        this.Plateholder = new ModelRenderer((ModelBase)this, 0, 12);
        this.Plateholder.addBox(0.0f, 0.0f, 0.0f, 6, 1, 6);
        this.Plateholder.setRotationPoint(-3.0f, 10.0f, -3.0f);
        this.Plateholder.setTextureSize(64, 32);
        this.Plateholder.mirror = true;
        this.setRotation(this.Plateholder, 0.0f, 0.0f, 0.0f);
        this.Plate = new ModelRenderer((ModelBase)this, 0, 19);
        this.Plate.addBox(0.0f, 0.0f, 0.0f, 16, 1, 12);
        this.Plate.setRotationPoint(-8.0f, 9.0f, -6.0f);
        this.Plate.setTextureSize(64, 32);
        this.Plate.mirror = true;
        this.setRotation(this.Plate, 0.0f, 0.0f, 0.0f);
        this.Waffle2 = new ModelRenderer((ModelBase)this, 0, 2);
        this.Waffle2.addBox(0.0f, 0.0f, 0.0f, 5, 1, 9);
        this.Waffle2.setRotationPoint(1.0f, 8.0f, -5.0f);
        this.Waffle2.setTextureSize(64, 32);
        this.Waffle2.mirror = true;
        this.setRotation(this.Waffle2, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Base.render(f5);
        this.Base2.render(f5);
        this.Pillar.render(f5);
        this.Plateholder.render(f5);
        this.Plate.render(f5);
        this.Waffle2.render(f5);
    }

    public void renderModel(float f) {
        this.Base.render(f);
        this.Base2.render(f);
        this.Pillar.render(f);
        this.Plateholder.render(f);
        this.Plate.render(f);
        this.Waffle2.render(f);
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

