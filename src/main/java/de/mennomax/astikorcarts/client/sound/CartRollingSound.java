package de.mennomax.astikorcarts.client.sound;

import de.mennomax.astikorcarts.AstikorCarts;
import de.mennomax.astikorcarts.entity.AbstractDrawnEntity;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

public class CartRollingSound extends AbstractTickableSoundInstance {
    private final AbstractDrawnEntity cart;
    private float distance = 0.0F;
    private double lastX, lastZ;
    private int tickCount = 0;

    public CartRollingSound(AbstractDrawnEntity cart) {
        super(AstikorCarts.SoundEvents.CART_ROLLING.get(), SoundSource.NEUTRAL, RandomSource.create());
        this.cart = cart;
        this.looping = true;
        this.delay = 0;
        this.volume = 1.0F; // 设置初始音量，调大音量以便更好地听到
        this.pitch = 1.0F;
        this.x = (float) cart.getX();
        this.y = (float) cart.getY();
        this.z = (float) cart.getZ();
        this.lastX = cart.getX();
        this.lastZ = cart.getZ();

    }

    @Override
    public void tick() {
        if (!this.cart.isAlive() || this.cart.getPulling() == null) {
            this.stop();
            return;
        }

        // 更新声音位置
        this.x = (float) this.cart.getX();
        this.y = (float) this.cart.getY();
        this.z = (float) this.cart.getZ();

        this.tickCount++;

        // 每15tick计算一次实际移动距离，进一步减少频繁变化
        if (this.tickCount >= 15) {
            double currentX = this.cart.getX();
            double currentZ = this.cart.getZ();
            // 只计算水平移动，忽略Y轴变化
            double horizontalMovement = Math.sqrt((currentX - this.lastX) * (currentX - this.lastX) + (currentZ - this.lastZ) * (currentZ - this.lastZ));
            float newDistance = (float) (horizontalMovement / 15.0); // 平均每tick的移动距离

            // 更平滑的过渡，避免突然变化
            this.distance = Mth.lerp(0.2F, this.distance, newDistance);

            this.lastX = currentX;
            this.lastZ = currentZ;
            this.tickCount = 0;

            // 根据移动距离调整音量和音调，但变化更平滑
            if (this.distance > 0.0008F) { // 降低阈值
                float targetVolume = Mth.clamp(this.distance * 30.0F + 0.2F, 0.2F, 0.8F);
                float targetPitch = Mth.clamp(0.8F + this.distance * 4.0F, 0.8F, 1.2F);

                // 更平滑的调整
                this.volume = Mth.lerp(0.05F, this.volume, targetVolume);
                this.pitch = Mth.lerp(0.05F, this.pitch, targetPitch);
            } else {
                // 马车停止移动，逐渐降低音量
                this.volume *= 0.95F;
                if (this.volume < 0.15F) {
                    this.stop();
                }
            }
        }
    }

    @Override
    public boolean canStartSilent() {
        return true;
    }

    public void stopSound() {
        this.stop();
    }
}
