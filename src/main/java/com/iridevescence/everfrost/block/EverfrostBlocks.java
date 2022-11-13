package com.iridevescence.everfrost.block;

import com.iridevescence.everfrost.Reference;
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

import java.lang.IllegalStateException;

import java.util.HashMap;

public class EverfrostBlocks {
    private static HashMap<String, Block> BLOCKS = new HashMap<>();

    private static Block block(String name, Block block) {
        BLOCKS.put(name, block);
        return block;
    }

    private static Block log(String name, MapColor top, MapColor side) {
        return block(name, new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, (state) -> {
            return state.get(PillarBlock.AXIS) == Direction.Axis.Y ? top : side;
        }).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    }

    public static final Block FROSTSOIL = block(Reference.Blocks.FROSTSOIL_ID, new Block(FabricBlockSettings.copy(Blocks.DIRT)));
    public static final Block PERMAFROST = block(Reference.Blocks.PERMAFROST_ID, new Block(FabricBlockSettings.copy(Blocks.STONE)));
    public static final Block FROZEN_OBSIDIAN = block(Reference.Blocks.FROZEN_OBSIDIAN_ID, new Block(FabricBlockSettings.copy(Blocks.OBSIDIAN)));

    public static final Block NEVERGREEN_LOG = log(Reference.Blocks.NEVERGREEN_LOG_ID, MapColor.CYAN, MapColor.CYAN);

    public static void init() {
        if (BLOCKS == null)
            throw new IllegalStateException("Called `init` more than once!");

            
        BLOCKS.forEach((name, block) -> {
            Registry.register(Registry.BLOCK, new Identifier(Reference.MOD_ID, name), block);
            Registry.register(Registry.ITEM, new Identifier(Reference.MOD_ID, name), new BlockItem(block, new Item.Settings()));
        });
        BLOCKS = null;
    }
}
