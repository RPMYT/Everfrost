package com.iridevescence.everfrost.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class HeatUtil {
    public static int getHeatLevel(PlayerEntity player) {
        int heat = 0;

        World world = player.getWorld();
        BlockPos pos = new BlockPos(player.getPos());

        if (world.getDimensionKey().getValue().toString().contains("everfrost")) {
            heat--;
        }

        if (world.isNight() && pos.getY() >= 60) {
            heat--;
        }

        int light = world.getLightLevel(LightType.BLOCK, pos);

        if (light > 7) {
            heat++;
        }

        if (light > 11) {
            heat++;
        }

        if (light >= 15) {
            heat += 2;
        }

        if (player.inPowderSnow) {
            heat -= 2;
        }

        if (world.isRaining()) {
            heat--;
        }

        return heat;
    }
}
