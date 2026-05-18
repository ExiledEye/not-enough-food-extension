package com.exiledeye.nefext.item;

import com.exiledeye.nefext.config.ModConfig;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateFoodItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class RottenFleshItem extends TemplateFoodItem {

    public RottenFleshItem(Identifier identifier) {
        super(identifier, 4, true); 
        this.setMaxCount(ModConfig.FOOD_ITEMS_CONFIG.foodMaxStack);
    }

    @Override
    public ItemStack use(ItemStack stack, World world, PlayerEntity player) {

        --stack.count;

        if (world.random.nextFloat() <= 0.8F) {
            player.damage(null, 2);
        } else {
            player.heal(4);
        }

        return stack;
    }
}