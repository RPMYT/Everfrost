package com.iridevescence.everfrost.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

public class EverfrostBlocks {
    private static final HashMap<String, Block> BLOCKS = new HashMap<>();

    private static Block block(String name, Block block) {
        BLOCKS.put(name, block);
        return block;
    }

    private static Block log(String name, MapColor top, MapColor side) {
        return block(name, new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, (state) -> {
            return state.get(PillarBlock.AXIS) == Direction.Axis.Y ? top : side;
        }).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    }

    public static final Block FROSTSOIL = block("frostsoil", new Block(FabricBlockSettings.copy(Blocks.DIRT)));
    public static final Block PERMAFROST = block("permafrost", new Block(FabricBlockSettings.copy(Blocks.STONE)));
    public static final Block FROZEN_OBSIDIAN = block("frozen_obsidian", new Block(FabricBlockSettings.copy(Blocks.OBSIDIAN)));

    public static final Block NEVERGREEN_LOG = log("nevergreen_log", MapColor.CYAN, MapColor.CYAN);

    public static void init() {
        BLOCKS.forEach((name, block) -> {
            Registry.register(Registry.BLOCK, new Identifier("everfrost", name), block);
            Registry.register(Registry.ITEM, new Identifier("everfrost", name), new BlockItem(block, new Item.Settings()));
        });
    }
}
