package com.exiledeye.nefext.listener;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.util.Identifier;
import paulevs.bhcreative.api.SimpleTab;
import paulevs.bhcreative.registry.TabRegistryEvent;

import java.lang.reflect.Field;

public class CreativeListener {
    private static SimpleTab nefFoodTab;

    @EventListener
    public void onTabRegistry(TabRegistryEvent event) {
        try {
            nefFoodTab = new SimpleTab(
                Identifier.of("nefext:nef_foods"),
                new ItemStack(Item.GOLDEN_APPLE)
            );
            event.register(nefFoodTab);

            // Add NEF Extension items
            nefFoodTab.addItem(new ItemStack(ItemListener.rottenFlesh));
            nefFoodTab.addItem(new ItemStack(ItemListener.pumpkinPie));

            // Add NEF items
            addNefEntry("ItemListener", "rawBeef");
            addNefEntry("ItemListener", "cookedBeef");
            addNefEntry("ItemListener", "rawChicken");
            addNefEntry("ItemListener", "cookedChicken");
            addNefEntry("ItemListener", "rawMutton");
            addNefEntry("ItemListener", "cookedMutton");
            addNefEntry("ItemListener", "potatoSoup");
            addNefEntry("ItemListener", "carrotSoup");
            addNefEntry("ItemListener", "carrot");
            addNefEntry("ItemListener", "potato");
            addNefEntry("ItemListener", "poisonousPotato");
            addNefEntry("ItemListener", "goldenCarrot");

            // Add NEF blocks
            addNefEntry("BlockListener", "carrotCrop");
            addNefEntry("BlockListener", "potatoCrop");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addNefEntry(String className, String fieldName) {
        try {
            Class<?> clazz = Class.forName("com.louiszn.NotEnoughFood.listener." + className);
            Field field = clazz.getField(fieldName);
            Object obj = field.get(null);

            if (obj instanceof Item) {
                nefFoodTab.addItem(new ItemStack((Item) obj));
            } else if (obj instanceof Block) {
                nefFoodTab.addItem(new ItemStack((Block) obj));
            }
        } catch (Exception ignored) {
        }
    }
}