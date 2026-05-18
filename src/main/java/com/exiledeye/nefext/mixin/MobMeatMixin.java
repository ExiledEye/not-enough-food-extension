package com.exiledeye.nefext.mixin;

import java.lang.reflect.Field;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.exiledeye.nefext.config.ModConfig;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mixin(value = LivingEntity.class, priority = 500)
public abstract class MobMeatMixin {

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
        LivingEntity self = (LivingEntity) (Object) this;
        int min = ModConfig.DROP_RATES_CONFIG.MobMeat.minMeat;
        int max = Math.max(min, ModConfig.DROP_RATES_CONFIG.MobMeat.maxMeat);
        int count = min + self.world.random.nextInt(max - min + 1);
        boolean onFire = self.isOnFire();

        if (self instanceof CowEntity) {
            self.dropItem(Item.LEATHER.id, self.world.random.nextInt(3));
            int id = onFire ? getModId("cookedBeef") : getModId("rawBeef");
            if (id != -1 && count > 0)
                self.dropItem(new ItemStack(id, count, 0), 0.0F);
            ci.cancel();

        } else if (self instanceof ChickenEntity) {
            self.dropItem(Item.FEATHER.id, self.world.random.nextInt(3));
            int id = onFire ? getModId("cookedChicken") : getModId("rawChicken");
            if (id != -1 && count > 0)
                self.dropItem(new ItemStack(id, count, 0), 0.0F);
            ci.cancel();

        } else if (self instanceof PigEntity) {
            int pigMin = min;
            int pigMax = max;
            if (ModConfig.DROP_RATES_CONFIG.MobMeat.pigBuff) {
                if (pigMin == pigMax) {
                    pigMax = pigMax + 1;
                } else {
                    pigMin = pigMin + 1;
                }
            }
            int pigCount = pigMin + self.world.random.nextInt(pigMax - pigMin + 1);
            int id = onFire ? Item.COOKED_PORKCHOP.id : Item.RAW_PORKCHOP.id;

            if (pigCount > 0)
                self.dropItem(new ItemStack(id, pigCount, 0), 0.0F);
            ci.cancel();

        } else if (self instanceof ZombieEntity) {
            if (ModConfig.DROP_RATES_CONFIG.MobMeat.rottenFleshDrop) {
                int fleshId = com.exiledeye.nefext.listener.ItemListener.rottenFlesh.id;
                if (fleshId != -1 && count > 0)
                    self.dropItem(new ItemStack(fleshId, count, 0), 0.0F);
            }
            if (!ModConfig.DROP_RATES_CONFIG.MobMeat.zombieFeatherDrop) {
                int featherCount = self.world.random.nextInt(3);
                if (featherCount > 0)
                    self.dropItem(Item.FEATHER.id, featherCount);
            }
            if (self.world.random.nextFloat() <= ModConfig.DROP_RATES_CONFIG.zombieDropChance) {
                float roll = self.world.random.nextFloat();
                int foodId;
                if (roll < 0.333F) {
                    foodId = getModId("carrot");
                } else if (roll < 0.666F) {
                    foodId = getModId("potato");
                } else {
                    foodId = Item.IRON_INGOT.id;
                }

                if (foodId == getModId("potato") && ModConfig.DROP_RATES_CONFIG.zombiePoisonousPotato) {
                    if (self.world.random.nextFloat() <= 0.20F) {
                        int poisonId = getModId("poisonousPotato");
                        if (poisonId != -1)
                            foodId = poisonId;
                    }
                }

                if (foodId != -1)
                    self.dropItem(foodId, 1);
            }
            ci.cancel();
        }
    }
}