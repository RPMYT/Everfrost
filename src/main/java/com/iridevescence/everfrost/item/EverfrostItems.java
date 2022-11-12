package com.iridevescence.everfrost.item;

import com.iridevescence.everfrost.item.tool.FlintAndWintersteelItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

public class EverfrostItems {
    private static final HashMap<String, Item> ITEMS = new HashMap<>();

    private static Item item(String name, Item item) {
        ITEMS.put(name, item);
        return item;
    }

    public static final Item FROST_CHARGE = item("frost_charge", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));

    public static void init() {
        ITEMS.forEach((name, item) -> Registry.register(Registry.ITEM, new Identifier("everfrost", name), item));
    }
}
