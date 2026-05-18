package com.exiledeye.nefext.listener;

import com.exiledeye.nefext.item.PumpkinPieItem;
import com.exiledeye.nefext.item.RottenFleshItem;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class ItemListener {

    @Entrypoint.Namespace
    public static Namespace NAMESPACE = Null.get();

    public static Item rottenFlesh;
    public static Item pumpkinPie;

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        rottenFlesh = new RottenFleshItem(Identifier.of(NAMESPACE, "rotten_flesh"))
            .setTranslationKey("rotten_flesh");

        pumpkinPie = new PumpkinPieItem(Identifier.of(NAMESPACE, "pumpkin_pie"))
            .setTranslationKey("pumpkin_pie");
    }
}