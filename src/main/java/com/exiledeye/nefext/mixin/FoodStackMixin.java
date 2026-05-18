package com.exiledeye.nefext.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.FoodItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.exiledeye.nefext.config.ModConfig;

@Mixin(Item.class)
public class FoodStackMixin {
    @Inject(method = "getMaxCount()I", at = @At("HEAD"), cancellable = true)
    private void overrideFoodStack(CallbackInfoReturnable<Integer> cir) {
        if ((Object) this instanceof FoodItem) {
            int stack = ModConfig.FOOD_ITEMS_CONFIG.foodMaxStack;
            if (stack > 64)
                stack = 64;
            if (stack < 1)
                stack = 1;
            if (((Item) (Object) this).id == Item.COOKIE.id) {
                stack = Math.max(8, stack);
            }
            cir.setReturnValue(stack);
        }
    }
}