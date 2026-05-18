package com.exiledeye.nefext.mixin;

import com.exiledeye.nefext.config.ModConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.FoodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Field;

@Mixin(FoodItem.class)
public abstract class SoupMixin {

    private int getModItemId(String name) {
        try {
            Class<?> clazz = Class.forName("com.louiszn.NotEnoughFood.listener.ItemListener");
            Field field = clazz.getField(name);
            Item item = (Item) field.get(null);
            return item.id;
        } catch (Exception e) {
            return -1;
        }
    }

    private boolean isSoup() {
        int currentId = ((Item) (Object) this).id;
        return currentId == getModItemId("potatoSoup") || currentId == getModItemId("carrotSoup");
    }

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void onSoupUse(ItemStack stack, World world, PlayerEntity player, CallbackInfoReturnable<ItemStack> cir) {
        if (!isSoup()) return;
        if (!ModConfig.FOOD_ITEMS_CONFIG.SoupConfig.soupBuff && !ModConfig.FOOD_ITEMS_CONFIG.SoupConfig.bowlReturn) {
            return;
        }
        --stack.count;

        int healAmount = ModConfig.FOOD_ITEMS_CONFIG.SoupConfig.soupBuff ? 10 : 8;
        player.heal(healAmount);

        if (ModConfig.FOOD_ITEMS_CONFIG.SoupConfig.bowlReturn) { // Trying to mimic vanilla behavior here
            if (stack.count <= 0) {
                cir.setReturnValue(new ItemStack(Item.BOWL));
            } else {
                if (!player.inventory.addStack(new ItemStack(Item.BOWL))) {
                    player.dropItem(Item.BOWL.id, 1);
                }
                cir.setReturnValue(stack);
            }
        } else {
            cir.setReturnValue(stack);
        }
    }
}