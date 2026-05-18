package com.exiledeye.nefext.config;

import net.glasslauncher.mods.gcapi3.api.ConfigRoot;

public class ModConfig {

    @ConfigRoot(value = "FoodItemsConfig", visibleName = "Food Items Settings")
    public static final ConfigFields.FooodItemsConfig FOOD_ITEMS_CONFIG = new ConfigFields.FooodItemsConfig();

    @ConfigRoot(value = "DropRatesConfig", visibleName = "Drop Rates Settings")
    public static final ConfigFields.DropRatesConfig DROP_RATES_CONFIG = new ConfigFields.DropRatesConfig();

    @ConfigRoot(value = "ModCompatibilityConfig", visibleName = "Mod Compatibility Settings")
    public static final ConfigFields.ModCompatibilityConfig MOD_COMPATIBILITY_CONFIG = new ConfigFields.ModCompatibilityConfig();

}