package de.mennomax.astikorcarts.util;

import de.mennomax.astikorcarts.entity.AbstractDrawnEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

public final class CartWheel {
    private float rotation;
    private float rotationIncrement;
    private final float offsetX;
    private final float offsetZ;
    private final float circumference;
    private double posX;
    private double posZ;
    private double prevPosX;
    private double prevPosZ;
    private final AbstractDrawnEntity cart;

    public CartWheel(final AbstractDrawnEntity cartIn, final float offsetXIn, final float offsetZIn, final float circumferenceIn) {
        this.cart = cartIn;
        this.offsetX = offsetXIn;
        this.offsetZ = offsetZIn;
        this.circumference = circumferenceIn;
        this.posX = this.prevPosX = cartIn.getX();
        this.posZ = this.prevPosZ = cartIn.getZ();
    }

    public CartWheel(final AbstractDrawnEntity cartIn, final float offsetX) {
        this(cartIn, offsetX, 0.0F, (float) (10 * Math.PI * 2 / 16));
    }

    public void tick() {
        this.rotation += this.rotationIncrement;
        this.prevPosX = this.posX;
        this.prevPosZ = this.posZ;
        final float yaw = (float) Math.toRadians(this.cart.getYRot());
        final float nx = -Mth.sin(yaw);
        final float nz = Mth.cos(yaw);
        this.posX = this.cart.getX() + nx * this.offsetZ - nz * this.offsetX;
        this.posZ = this.cart.getZ() + nz * this.offsetZ + nx * this.offsetX;
        final double dx = this.posX - this.prevPosX;
        final double dz = this.posZ - this.prevPosZ;
        final float distanceTravelled = (float) Math.sqrt(dx * dx + dz * dz);
        final double dxNormalized = dx / distanceTravelled;
        final double dzNormalized = dz / distanceTravelled;
        final float travelledForward = Mth.sign(dxNormalized * nx + dzNormalized * nz);
        if (distanceTravelled > 0.2) {
            final BlockPos blockpos = new BlockPos(Mth.floor(this.posX), Mth.floor(this.cart.getY() - 0.2F), Mth.floor(this.posZ));
            final BlockState blockstate = this.cart.level().getBlockState(blockpos);
            if (!blockstate.addRunningEffects(this.cart.level(), blockpos, this.cart)) {
                if (blockstate.getRenderShape() != RenderShape.INVISIBLE) {
                    this.cart.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockstate).setPos(blockpos), this.posX, this.cart.getY(), this.posZ, dx, distanceTravelled, dz);
                }
            }
        }
        this.rotationIncrement = travelledForward * distanceTravelled * this.circumference * 0.2F;
    }

    public void clearIncrement() {
        this.rotationIncrement = 0.0F;
    }

    public float getRotation() {
        return this.rotation;
    }

    public float getRotationIncrement() {
        return this.rotationIncrement;
    }
}
