package com.exiledeye.nefext.listener;

import com.exiledeye.nefext.config.ModConfig;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

public class RecipeListener {

    @EventListener
    public void onRecipeRegister(RecipeRegisterEvent event) {
        if (event.recipeId.equals(RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type())) {
            nerfRecipes();
        }
    }

    @SuppressWarnings("unchecked")
    private void nerfRecipes() {
        try {
            Class<?> registryClass = Class.forName("net.minecraft.class_148");
            Object instance = null;
            for (Method m : registryClass.getDeclaredMethods()) {
                if (m.getParameterCount() == 0
                        && m.getReturnType().equals(registryClass)
                        && Modifier.isStatic(m.getModifiers())) {
                    m.setAccessible(true);
                    instance = m.invoke(null);
                    break;
                }
            }
            if (instance == null) return;

            List<Object> recipes = null;
            for (Field f : registryClass.getDeclaredFields()) {
                if (List.class.isAssignableFrom(f.getType())) {
                    f.setAccessible(true);
                    recipes = (List<Object>) f.get(instance);
                    break;
                }
            }
            if (recipes == null) return;

            // GOLDEN CARROT
            Item carrot = getModItem("carrot");
            Item goldenCarrot = getModItem("goldenCarrot");
            
            if (carrot != null && goldenCarrot != null) {
                recipes.removeIf(recipe -> {
                    Object outputStack = getRecipeOutput(recipe);
                    return outputStack != null && isItemStackId(outputStack, goldenCarrot.id);
                });

                if (ModConfig.FOOD_ITEMS_CONFIG.GoldenFood.goldenCarrotBlocks) {
                    CraftingRegistry.addShapedRecipe(new ItemStack(goldenCarrot, 1), 
                        "GGG", "GCG", "GGG", 
                        'G', Block.GOLD_BLOCK, 'C', carrot
                    );
                }

                if (ModConfig.FOOD_ITEMS_CONFIG.GoldenFood.goldenCarrotIngots) {
                    CraftingRegistry.addShapedRecipe(new ItemStack(goldenCarrot, 1), 
                        "GGG", "GCG", "GGG", 
                        'G', Item.GOLD_INGOT, 'C', carrot
                    );
                }
            }

            // GOLDEN APPLE
            if (ModConfig.FOOD_ITEMS_CONFIG.GoldenFood.goldenAppleNerf) {
                recipes.removeIf(recipe -> {
                    Object outputStack = getRecipeOutput(recipe);
                    return outputStack != null && isItemStackId(outputStack, Item.GOLDEN_APPLE.id);
                });
                CraftingRegistry.addShapedRecipe(new ItemStack(Item.GOLDEN_APPLE, 1), 
                    "GGG", "GAG", "GGG", 
                    'G', Block.GOLD_BLOCK, 'A', Item.APPLE
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helpers to manipulate recipes
    private static Object getRecipeOutput(Object recipe) {
        for (Method m : recipe.getClass().getMethods()) {
            if (m.getParameterCount() == 0 && m.getReturnType().getName().equals("net.minecraft.class_31")) {
                try {
                    m.setAccessible(true);
                    return m.invoke(recipe);
                } catch (Exception ignored) {}
            }
        }
        Class<?> cls = recipe.getClass();
        while (cls != null && cls != Object.class) {
            for (Field f : cls.getDeclaredFields()) {
                if (f.getType().getName().equals("net.minecraft.class_31")) {
                    try {
                        f.setAccessible(true);
                        return f.get(recipe);
                    } catch (Exception ignored) {}
                }
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    private static boolean isItemStackId(Object itemStack, int targetId) {
        for (Field f : itemStack.getClass().getDeclaredFields()) {
            if (f.getType() == int.class) {
                try {
                    f.setAccessible(true);
                    if (f.getInt(itemStack) == targetId) return true;
                } catch (Exception ignored) {}
            }
        }
        return false;
    }

    private static Item getModItem(String name) {
        try {
            Class<?> clazz = Class.forName("com.louiszn.NotEnoughFood.listener.ItemListener");
            Field field = clazz.getField(name);
            return (Item) field.get(null);
        } catch (Exception e) {
            return null;
        }
    }
}