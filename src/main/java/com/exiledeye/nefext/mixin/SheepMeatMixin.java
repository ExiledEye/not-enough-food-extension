package com.exiledeye.nefext.mixin;

import com.exiledeye.nefext.config.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.lang.reflect.Field;

@Mixin(value = SheepEntity.class, priority = 500)
public abstract class SheepMeatMixin {

    private int getModId(String name) {
        try {
            Class<?> clazz = Class.forName("com.louiszn.NotEnoughFood.listener.ItemListener");
            Field field = clazz.getField(name);
            Item item = (Item) field.get(null);
            return item.id;
        } catch (Exception e) {
            return -1;
        }
    }

    @Inject(method = "dropItems", at = @At("HEAD"), cancellable = true)
    private void dropItems(CallbackInfo ci) {
        SheepEntity self = (SheepEntity)(Object)this;
        int min = ModConfig.DROP_RATES_CONFIG.MobMeat.minMeat;
        int max = Math.max(min, ModConfig.DROP_RATES_CONFIG.MobMeat.maxMeat);
        int count = min + self.world.random.nextInt(max - min + 1);
        boolean onFire = self.isOnFire();

        self.dropItem(new ItemStack(Block.WOOL.id, 1, self.getColor()), 0.0F);
        int id = onFire ? getModId("cookedMutton") : getModId("rawMutton");
        if (id != -1 && count > 0) self.dropItem(new ItemStack(id, count, 0), 0.0F);
        ci.cancel();
    }
}