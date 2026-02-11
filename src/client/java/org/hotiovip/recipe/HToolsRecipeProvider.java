package org.hotiovip.recipe;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.hotiovip.HToolsItems;

import static net.minecraft.data.recipes.SmithingTransformRecipeBuilder.smithing;

public class HToolsRecipeProvider extends FabricRecipeProvider {
    public HToolsRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

                shaped(RecipeCategory.TOOLS, HToolsItems.DIAMOND_GOLDEN_PICKAXE, 1)
                        .pattern(" d ")
                        .pattern("dgd")
                        .define('d', Items.DIAMOND)
                        .define('g', Items.GOLDEN_PICKAXE)
                        //.group("multi_bench")
                        .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                        .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                        .save(output);

                smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(HToolsItems.DIAMOND_GOLDEN_PICKAXE),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.TOOLS,
                        HToolsItems.NETHERITE_DIAMOND_GOLDEN_PICKAXE)
                        .unlocks(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                        .unlocks(getHasName(HToolsItems.DIAMOND_GOLDEN_PICKAXE), has(HToolsItems.DIAMOND_GOLDEN_PICKAXE))
                        .save(output, "netherite_diamond_golden_pickaxe");
            }
        };
    }

    @Override
    public String getName() {
        return "HToolsRecipeProvider";
    }
}
