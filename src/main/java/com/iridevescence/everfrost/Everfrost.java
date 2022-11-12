package com.iridevescence.everfrost;

import com.iridevescence.everfrost.block.EverfrostBlocks;
import com.iridevescence.everfrost.item.EverfrostItems;
import net.fabricmc.api.ModInitializer;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class Everfrost implements ModInitializer {
    @Override
    public void onInitialize() {
        EverfrostItems.init();
        EverfrostBlocks.init();

        CustomPortalBuilder.beginPortal()
                .tintColor(10, 10, 200)
                .frameBlock(EverfrostBlocks.FROZEN_OBSIDIAN)
                .lightWithItem(Items.AMETHYST_SHARD)
                .destDimID(new Identifier("everfrost", "everfrost"))
                .registerPortal();
    }
}
