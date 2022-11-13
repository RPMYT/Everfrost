package com.iridevescence.everfrost.item;

import com.iridevescence.everfrost.Reference;
import com.iridevescence.everfrost.util.Pair;
import com.iridevescence.everfrost.item.tool.FlintAndWintersteelItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import java.util.List;
import java.util.ArrayList;
import java.lang.IllegalStateException;

public class EverfrostItems {
    private static List<Pair<String, Item>> ITEMS = new ArrayList<>();

    private static Item item(String name, Item item) {
        ITEMS.add(new Pair<>(name, item));
        return item;
    }

    public static final Item FROST_CHARGE = item(Reference.Items.FROST_CHARGE_ID, new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));

    public static void init() {
        if (ITEMS == null)
            throw new IllegalStateException("Called `init` more than once!");
        
        ITEMS.forEach((pair) -> Registry.register(Registry.ITEM, new Identifier(Reference.MOD_ID, pair.getLeft()), pair.getRight()));
        ITEMS = null;
        
    }
}
