/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.EntitySmokeFX
 *  net.minecraft.world.World
 */
package com.chef.mod.particle;

import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.world.World;

public class EntityParticleFXVapor
extends EntitySmokeFX {
    public EntityParticleFXVapor(World world, double parX, double parY, double parZ, double parMotionX, double parMotionY, double parMotionZ) {
        super(world, parX, parY, parZ, parMotionX, parMotionY, parMotionZ);
    }

    public EntityParticleFXVapor(World world, double parX, double parY, double parZ, double parMotionX, double parMotionY, double parMotionZ, float f) {
        super(world, parX, parY, parZ, parMotionX, parMotionY, parMotionZ, f);
        this.particleBlue = 1.0f;
        this.particleGreen = 1.0f;
        this.particleRed = 1.0f;
        this.particleMaxAge = (int)(15.0 / (Math.random() * 0.8 + 0.2));
    }
}

