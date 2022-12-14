package com.iridevescence.everfrost.item;

import com.iridevescence.everfrost.Reference;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class EverfrostItems {
    private static List<Pair<String, Item>> ITEMS = new ArrayList<>();

    private static Item item(String name, Item item) {
        ITEMS.add(new Pair<>(name, item));
        return item;
    }



    public static void init() {
        if (ITEMS == null) {
            throw new IllegalStateException("Called `init` more than once!");
        }
        
        ITEMS.forEach((pair) -> Registry.register(Registry.ITEM, new Identifier(Reference.MOD_ID, pair.getLeft()), pair.getRight()));
        ITEMS = null;
        
    }
}
