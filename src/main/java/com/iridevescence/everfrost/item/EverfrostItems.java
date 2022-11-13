package com.iridevescence.everfrost.item;

import com.iridevescence.everfrost.Reference;
import com.iridevescence.everfrost.item.tool.FlintAndWintersteelItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import java.util.Map;
import java.util.HashMap;
import java.lang.IllegalStateException;

public class EverfrostItems {
    private static Map<String, Item> ITEMS = new HashMap<>();

    private static Item item(String name, Item item) {
        ITEMS.put(name, item);
        return item;
    }

    public static final Item FROST_CHARGE = item(Reference.Items.FROST_CHARGE_ID, new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));

    public static void init() {
        if (ITEMS == null)
            throw new IllegalStateException("Called `init` more than once!");
        
        ITEMS.forEach((name, item) -> Registry.register(Registry.ITEM, new Identifier(Reference.MOD_ID, name), item));
        ITEMS = null;
        
    }
}
