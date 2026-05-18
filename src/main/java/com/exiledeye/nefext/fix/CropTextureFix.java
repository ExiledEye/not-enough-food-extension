package com.exiledeye.nefext.fix;

import com.exiledeye.nefext.config.ModConfig;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.util.Identifier;

public class CropTextureFix {

    @EventListener
    public void fixCropBlockItemTextures(TextureRegisterEvent event) {
        if (!ModConfig.MOD_COMPATIBILITY_CONFIG.wailaFix) return;

        try {
            Class<?> blockListenerClass = Class.forName("com.louiszn.NotEnoughFood.listener.BlockListener");

            Block potatoCrop = (Block) blockListenerClass.getField("potatoCrop").get(null);
            Block carrotCrop = (Block) blockListenerClass.getField("carrotCrop").get(null);

            potatoCrop.asItem().setTexture(Identifier.of("not-enough-food:block/potatoes_stage0"));
            carrotCrop.asItem().setTexture(Identifier.of("not-enough-food:block/carrots_stage0"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}