package com.iridevescence.everfrost;

import com.iridevescence.everfrost.block.EverfrostBlocks;
import com.iridevescence.everfrost.item.EverfrostItems;
import net.fabricmc.api.ModInitializer;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;

public class Everfrost implements ModInitializer {
    @Override
    public void onInitialize() {
        EverfrostItems.init();
        EverfrostBlocks.init();

        CustomPortalBuilder.beginPortal()
                .lightWithWater()
                .onlyLightInOverworld()
                .frameBlock(Blocks.BLUE_ICE)
                .tintColor(10, 10, 200)
                .destDimID(new Identifier(Reference.MOD_ID, Reference.MOD_ID))
                .registerPortal();
    }
}
