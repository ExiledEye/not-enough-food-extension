package com.exiledeye.nefext.config;

import net.glasslauncher.mods.gcapi3.api.ConfigCategory;
import net.glasslauncher.mods.gcapi3.api.ConfigEntry;

public class ConfigFields {
    public static class FooodItemsConfig {

        @ConfigEntry(name = "Food Max Stack Size (1 to 64)", description = "Sets the maximum stack size for every food item.", minValue = 1, maxValue = 64)
        public Integer foodMaxStack = 1;

        @ConfigCategory(name = "NEF Soup Settings")
        public SoupConfig SoupConfig = new SoupConfig();
        public static class SoupConfig {

            @ConfigEntry(name = "NEF Soup Buff", description = "Buffs NEF Soups by simplyfing recipe and increasing healing.", longDescription = "If enabled, NEF soups will now no longer require a mushroom to craft and will heal 5 hearts (same as vanilla mushroom stew) instead of 4.", requiresRestart = true)
            public Boolean soupBuff = true;

            @ConfigEntry(name = "Do not eat the bowl!", description = "Returns the bowl after eating a NEF Soup, just like vanilla mushroom stew.")
            public Boolean bowlReturn = true;

        }

        @ConfigCategory(name = "Golden Food Settings")
        public GoldenFood GoldenFood = new GoldenFood();
        public static class GoldenFood {

            @ConfigEntry(name = "Golden Carrot - 8 Blocks Recipe", description = "Enables the 8 blocks recipe for Golden Carrots.", requiresRestart = true)
            public Boolean goldenCarrotBlocks = true;

            @ConfigEntry(name = "Golden Carrot - 8 Ingots Recipe", description = "Enables the 8 ingots recipe for Golden Carrots.", requiresRestart = true)
            public Boolean goldenCarrotIngots = false;

            @ConfigEntry(name = "Nerf Golden Apple Recipe", description = "Nerfs the Golden Apple recipe by removing the NEF-added 8 ingots option.", requiresRestart = true)
            public Boolean goldenAppleNerf = true;

        }
    }

    public static class DropRatesConfig {

        @ConfigEntry(name = "Apple Drop Chance (0.0 to 1.0)", description = "Sets the chance for apples to drop from tree leaves. 0 -> disabled.", longDescription =  "This will override NEF's default apple drop rate and in theory also every other mod that adds drop to leaves such as UniTweaks", minValue = 0.0F, maxValue = 1.0F)
        public Float appleChance = 0.05F;

        @ConfigEntry(name = "Zombie Drop Chance (0.0 to 1.0;)", description = "Sets the chance for Zombies to drop Carrot, Potato and Iron Ingot. 0 -> disabled.", minValue = 0.0F, maxValue = 1.0F)
        public Float zombieDropChance = 0.025F;

        @ConfigEntry(name = "Zombie Poisonous Potato", description = "Adds a chance for the Potatoes dropped by Zombies to be poisonous.")
        public Boolean zombiePoisonousPotato = false;

        @ConfigEntry(name = "Zombie Iron Ingot Drop", description = "Zombies can drop Iron Ingots alongside Carrots and Potatoes.")
        public Boolean zombieIronIngotDrop = true;

        @ConfigEntry(name = "Extra Grass Drops (0.0 to 1.0;)", description = "Adds a chance for Carrot and Potato to drop from Grass. 0 -> disabled.", minValue = 0.0F, maxValue = 1.0F)
        public Float grassCropChance = 0.0F;

        @ConfigCategory(name = "Mob Meat Drop Settings")
        public MobMeat MobMeat = new MobMeat();
        public static class MobMeat {

            @ConfigEntry(name = "Min Meat Drop", description = "Sets the minimum amount of meat dropped by animals.")
            public Integer minMeat = 0;

            @ConfigEntry(name = "Max Meat Drop", description = "Sets the maximum amount of meat dropped by animals.")
            public Integer maxMeat = 1;

            @ConfigEntry(name = "Pig Buff", description = "Makes pigs always drop meat when killed. if Min Meat Drop is greater than 0, it adds 1 to the Max Meat Drop.", longDescription = "So with NEF addittions Pigs lose their value since their only purpose in vanilla is to provide meat and with NEF every mob provides meat + other items, so this is my idea to make Pigs still relevant with these additions: if true and minMeat is 0 -> minMeat+1, if minMeat is greater than 0 -> maxMeat+1.")
            public Boolean pigBuff = true;

            @ConfigEntry(name = "Enable Rotten Flesh Drop", description = "Zombies will drop Rotten Flesh.", longDescription = "Yes, Zombies will drop Rotten Flesh, It follows minMeat and maxMeat rules. If consumed it may deal 1 heart of damage or may heal 2 hearts.")
            public Boolean rottenFleshDrop = true;
            
            @ConfigEntry(name = "Disable Zombie Feather Drop", description = "Disables vanilla Zombie feather drop (0-2)")
            public Boolean zombieFeatherDrop = true;
        }
    }

    public static class ModCompatibilityConfig {
 
        @ConfigEntry(name = "WAILA Crop Texture Fix", description = "Prevent a crash if using WAILA when looking at a carrot/potato crop", requiresRestart = true)
        public Boolean wailaFix = false;
    }
}