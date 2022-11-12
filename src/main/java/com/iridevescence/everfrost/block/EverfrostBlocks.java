package com.iridevescence.everfrost.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

public class EverfrostBlocks {
    private static final HashMap<String, Block> BLOCKS = new HashMap<>();

    private static Block block(String name, Block block) {
        BLOCKS.put(name, block);
        return block;
    }

    public static final Block FROZEN_OBSIDIAN = block("frozen_obsidian", new Block(FabricBlockSettings.copy(Blocks.OBSIDIAN)));

    public static void init() {
        BLOCKS.forEach((name, block) -> {
            Registry.register(Registry.BLOCK, new Identifier("everfrost", name), block);
            Registry.register(Registry.ITEM, new Identifier("everfrost", name), new BlockItem(block, new Item.Settings()));
        });
    }
}
