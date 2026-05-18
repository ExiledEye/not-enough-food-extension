package com.exiledeye.nefext.mixin;

import com.exiledeye.nefext.config.ModConfig;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Field;
import java.util.Random;

@Mixin(value = TallPlantBlock.class, priority = 1100)
public abstract class TallPlantMixin {

    private static int getModId(String name) {
        try {
            Class<?> clazz = Class.forName("com.louiszn.NotEnoughFood.listener.ItemListener");
            Field field = clazz.getField(name);
            Item item = (Item) field.get(null);
            return item.id;
        } catch (Exception e) {
            return -1;
        }
    }

    @Inject(method = "getDroppedItemId", at = @At("RETURN"), cancellable = true)
    private void onGetDroppedItemId(int meta, Random random, CallbackInfoReturnable<Integer> cir) {
        
        if (ModConfig.DROP_RATES_CONFIG.grassCropChance <= 0.0F) return;

        if (random.nextFloat() <= ModConfig.DROP_RATES_CONFIG.grassCropChance) {
            
            int id = random.nextFloat() <= 0.5F ? getModId("carrot") : getModId("potato");
            
            if (id != -1) {
                cir.setReturnValue(id);
            }
        }
    }
}