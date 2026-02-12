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
import org.jetbrains.annotations.NotNull;

import static net.minecraft.data.recipes.SmithingTransformRecipeBuilder.smithing;

public class HToolsRecipeProvider extends FabricRecipeProvider {
    public HToolsRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.@NotNull Provider registryLookup, @NotNull RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                HolderLookup.RegistryLookup<@NotNull Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

                // Diamond gold ingot
                shaped(RecipeCategory.MISC, HToolsItems.DIAMOND_GOLD_INGOT, 1)
                        .pattern("dg")
                        .define('d', Items.DIAMOND)
                        .define('g', Items.GOLD_INGOT)
                        .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                        .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                        .save(output);
                // Netherite diamond gold ingot
                shaped(RecipeCategory.MISC, HToolsItems.NETHERITE_DIAMOND_GOLD_INGOT, 1)
                        .pattern("dgn")
                        .define('d', Items.DIAMOND)
                        .define('g', Items.GOLD_INGOT)
                        .define('n', Items.NETHERITE_INGOT)
                        .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                        .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                        .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                        .save(output);
                // Vein miner upgrade smithing template
                shaped(RecipeCategory.MISC, HToolsItems.VEIN_MINER_UPGRADE_SMITHING_TEMPLATE, 2)
                        .pattern("iti")
                        .pattern("ini")
                        .pattern("iii")
                        .define('i', HToolsItems.NETHERITE_DIAMOND_GOLD_INGOT)
                        .define('t', HToolsItems.VEIN_MINER_UPGRADE_SMITHING_TEMPLATE)
                        .define('n', Items.NETHERRACK)
                        .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                        .unlockedBy(getHasName(Items.NETHERRACK), has(Items.NETHERRACK))
                        .unlockedBy(getHasName(HToolsItems.VEIN_MINER_UPGRADE_SMITHING_TEMPLATE), has(HToolsItems.VEIN_MINER_UPGRADE_SMITHING_TEMPLATE))
                        .save(output);

                // Diamond golden pickaxe
                shaped(RecipeCategory.TOOLS, HToolsItems.DIAMOND_GOLDEN_PICKAXE, 1)
                        .pattern("iii")
                        .pattern(" s ")
                        .pattern(" s ")
                        .define('i', HToolsItems.DIAMOND_GOLD_INGOT)
                        .define('s', Items.STICK)
                        .unlockedBy(getHasName(HToolsItems.DIAMOND_GOLD_INGOT), has(HToolsItems.DIAMOND_GOLD_INGOT))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .save(output);
                // Netherite diamond golden pickaxe
//                smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
//                        Ingredient.of(HToolsItems.DIAMOND_GOLDEN_PICKAXE),
//                        Ingredient.of(Items.NETHERITE_INGOT),
//                        RecipeCategory.TOOLS,
//                        HToolsItems.NETHERITE_DIAMOND_GOLDEN_PICKAXE)
//                        .unlocks(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
//                        .unlocks(getHasName(HToolsItems.DIAMOND_GOLDEN_PICKAXE), has(HToolsItems.DIAMOND_GOLDEN_PICKAXE))
//                        .save(output, "netherite_diamond_golden_pickaxe");
                shaped(RecipeCategory.TOOLS, HToolsItems.NETHERITE_DIAMOND_GOLDEN_PICKAXE, 1)
                        .pattern("iii")
                        .pattern(" s ")
                        .pattern(" s ")
                        .define('i', HToolsItems.NETHERITE_DIAMOND_GOLD_INGOT)
                        .define('s', Items.STICK)
                        .unlockedBy(getHasName(HToolsItems.NETHERITE_DIAMOND_GOLD_INGOT), has(HToolsItems.NETHERITE_DIAMOND_GOLD_INGOT))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .save(output);
                // Vein miner pickaxe
                smithing(Ingredient.of(HToolsItems.VEIN_MINER_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(HToolsItems.NETHERITE_DIAMOND_GOLDEN_PICKAXE),
                        Ingredient.of(HToolsItems.NETHERITE_DIAMOND_GOLD_INGOT),
                        RecipeCategory.TOOLS,
                        HToolsItems.VEIN_MINER_PICKAXE)
                        .unlocks(getHasName(HToolsItems.NETHERITE_DIAMOND_GOLD_INGOT), has(HToolsItems.NETHERITE_DIAMOND_GOLD_INGOT))
                        .unlocks(getHasName(HToolsItems.NETHERITE_DIAMOND_GOLDEN_PICKAXE), has(HToolsItems.NETHERITE_DIAMOND_GOLDEN_PICKAXE))
                        .save(output, "vein_miner_pickaxe");
            }
        };
    }

    @Override
    public String getName() {
        return "HToolsRecipeProvider";
    }
}
