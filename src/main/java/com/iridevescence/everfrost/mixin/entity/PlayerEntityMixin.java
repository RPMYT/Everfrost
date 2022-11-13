package com.iridevescence.everfrost.mixin.entity;

import com.iridevescence.everfrost.util.HeatUtil;
import net.minecraft.entity.LivingEntity;
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
        switch (heat) {
            case 0 -> {
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

            case -1 -> {
                if (!Objects.requireNonNullElse(player.getStatusEffect(StatusEffects.SLOWNESS), new StatusEffectInstance(StatusEffects.SLOWNESS)).isPermanent()) {
                    StatusEffectInstance instance = new StatusEffectInstance(StatusEffects.SLOWNESS, 2400, 0);
                    instance.setPermanent(true);
                    player.addStatusEffect(instance);
                }
            }

            case -2 -> {
                if (!Objects.requireNonNullElse(player.getStatusEffect(StatusEffects.SLOWNESS), new StatusEffectInstance(StatusEffects.SLOWNESS)).isPermanent()) {
                    StatusEffectInstance instance = new StatusEffectInstance(StatusEffects.SLOWNESS, 4800, 1);
                    instance.setPermanent(true);
                    player.addStatusEffect(instance);
                }

                if (!Objects.requireNonNullElse(player.getStatusEffect(StatusEffects.WEAKNESS), new StatusEffectInstance(StatusEffects.WEAKNESS)).isPermanent()) {
                    StatusEffectInstance instance = new StatusEffectInstance(StatusEffects.WEAKNESS, 2400, 0);
                    instance.setPermanent(true);
                    player.addStatusEffect(instance);
                }
            }

            case -3 -> {
                if (!Objects.requireNonNullElse(player.getStatusEffect(StatusEffects.SLOWNESS), new StatusEffectInstance(StatusEffects.SLOWNESS)).isPermanent()) {
                    StatusEffectInstance instance = new StatusEffectInstance(StatusEffects.SLOWNESS, 7200, 2);
                    instance.setPermanent(true);
                    player.addStatusEffect(instance);
                }

                if (!Objects.requireNonNullElse(player.getStatusEffect(StatusEffects.WEAKNESS), new StatusEffectInstance(StatusEffects.WEAKNESS)).isPermanent()) {
                    StatusEffectInstance instance = new StatusEffectInstance(StatusEffects.WEAKNESS, 4800, 1);
                    instance.setPermanent(true);
                    player.addStatusEffect(instance);
                }

                if (player.hurtTime == 0) {
                    player.damage(DamageSource.FREEZE, 2f);
                    player.animateDamage();
                }
            }

            case -4 -> {
                if (!Objects.requireNonNullElse(player.getStatusEffect(StatusEffects.SLOWNESS), new StatusEffectInstance(StatusEffects.SLOWNESS)).isPermanent()) {
                    StatusEffectInstance instance = new StatusEffectInstance(StatusEffects.SLOWNESS, 9600, 3);
                    instance.setPermanent(true);
                    player.addStatusEffect(instance);
                }

                if (!Objects.requireNonNullElse(player.getStatusEffect(StatusEffects.WEAKNESS), new StatusEffectInstance(StatusEffects.WEAKNESS)).isPermanent()) {
                    StatusEffectInstance instance = new StatusEffectInstance(StatusEffects.WEAKNESS, 7200, 2);
                    instance.setPermanent(true);
                    player.addStatusEffect(instance);
                }

                if (player.hurtTime == 0) {
                    player.damage(DamageSource.FREEZE, 3f);
                    player.animateDamage();
                }
            }

            case -5, -6, -7 -> {
                if (!Objects.requireNonNullElse(player.getStatusEffect(StatusEffects.SLOWNESS), new StatusEffectInstance(StatusEffects.SLOWNESS)).isPermanent()) {
                    StatusEffectInstance instance = new StatusEffectInstance(StatusEffects.SLOWNESS, 9600, 3);
                    instance.setPermanent(true);
                    player.addStatusEffect(instance);
                }

                if (!Objects.requireNonNullElse(player.getStatusEffect(StatusEffects.WEAKNESS), new StatusEffectInstance(StatusEffects.WEAKNESS)).isPermanent()) {
                    StatusEffectInstance instance = new StatusEffectInstance(StatusEffects.WEAKNESS, 9600, 3);
                    instance.setPermanent(true);
                    player.addStatusEffect(instance);
                }

                if (player.hurtTime == 0) {
                    player.damage(DamageSource.FREEZE, 4f);
                    player.animateDamage();
                }
            }

        }
    }
}
