package com.iridevescence.everfrost.mixin.entity;

import com.iridevescence.everfrost.util.HeatUtil;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void applyFrosbite(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        int heat = HeatUtil.getHeatLevel(player);

        if (heat >= 0) {
            if (!Objects.requireNonNullElse(player.getStatusEffect(StatusEffects.SLOWNESS), new StatusEffectInstance(StatusEffects.SLOWNESS)).isPermanent()) {
                if (player.hasStatusEffect(StatusEffects.SLOWNESS)) {
                    StatusEffectInstance instance = player.getStatusEffect(StatusEffects.SLOWNESS);
                    if (instance != null) {
                        instance.setPermanent(false);
                    }
                    player.setStatusEffect(instance, player);
                }
            }
        }

        if (heat < 0) {
            if (!Objects.requireNonNullElse(player.getStatusEffect(StatusEffects.SLOWNESS), new StatusEffectInstance(StatusEffects.SLOWNESS)).isPermanent()) {
                StatusEffectInstance instance = new StatusEffectInstance(StatusEffects.SLOWNESS, 2400 * -heat, -heat - 1);
                instance.setPermanent(true);
                player.addStatusEffect(instance);
            }
        }

        if (heat < -1) {
            if (!Objects.requireNonNullElse(player.getStatusEffect(StatusEffects.WEAKNESS), new StatusEffectInstance(StatusEffects.WEAKNESS)).isPermanent()) {
                StatusEffectInstance instance = new StatusEffectInstance(StatusEffects.WEAKNESS, (-heat - 1), -heat - 2);
                instance.setPermanent(true);
                player.addStatusEffect(instance);
            }
        }

        if (player.hurtTime == 0 && heat < -2) {
            player.damage(DamageSource.FREEZE, 2f * (-heat-2));
            player.animateDamage();
        }
    }
}
