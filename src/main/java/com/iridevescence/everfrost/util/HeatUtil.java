package com.iridevescence.everfrost.util;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import java.util.concurrent.atomic.AtomicInteger;

public class HeatUtil {
    public static int getHeatLevel(PlayerEntity player) {
        if (player.isCreative()) {
            return 0;
        }

        AtomicInteger heat = new AtomicInteger();

        World world = player.getWorld();
        BlockPos pos = new BlockPos(player.getPos());

        if (world.getDimensionKey().getValue().toString().contains("everfrost")) {
            heat.getAndDecrement();
        }

        if (world.isNight() && pos.getY() >= 60) {
            heat.getAndDecrement();
        }

        int light = world.getLightLevel(LightType.BLOCK, pos);

        if (light > 7) {
            heat.getAndIncrement();
        }

        if (light > 11) {
            heat.getAndIncrement();
        }

        if (light >= 15) {
            heat.addAndGet(2);
        }

        if (player.inPowderSnow) {
            heat.addAndGet(-2);
        }

        if (world.isRaining()) {
            heat.getAndDecrement();
        }

        player.getArmorItems().forEach(stack -> {
            if (stack.getItem() instanceof ArmorItem armour) {
                if (armour.getMaterial() == ArmorMaterials.LEATHER && armour.getSlotType() != EquipmentSlot.HEAD) {
                    heat.getAndIncrement();
                }
            }
        });

        return heat.get();
    }
}
