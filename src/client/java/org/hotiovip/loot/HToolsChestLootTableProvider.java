package org.hotiovip.loot;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.hotiovip.HToolsItems;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class HToolsChestLootTableProvider extends SimpleFabricLootTableProvider {
    private final HolderLookup.Provider registries;

    public HToolsChestLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup, LootContextParamSets.CHEST);
        registries = registryLookup.join();
    }

    @Override
    public void generate(@NotNull BiConsumer<ResourceKey<@NotNull LootTable>, LootTable.Builder> lootTableBiConsumer) {
        // Override bastion treasure chests (rare templates spawn here)
        lootTableBiConsumer.accept(BuiltInLootTables.BASTION_TREASURE, createBastionTreasureLootTable());

        // Optional: hoglin stable chests
        //lootTableBiConsumer.accept(BuiltInLootTables.BASTION_HOGLIN_STABLE, createTemplateLootTable());

        // Optional: other chests (less rare)
        //lootTableBiConsumer.accept(BuiltInLootTables.BASTION_OTHER, createTemplateLootTable());
    }

    private LootTable.Builder createBastionTreasureLootTable() {
        return LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                                .setBonusRolls(ConstantValue.exactly(0.0f))
                                .setRolls(ConstantValue.exactly(3.0f))
                                .add(LootItem.lootTableItem(Items.NETHERITE_INGOT)
                                        .setWeight(15)
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f), false)))
                                .add(LootItem.lootTableItem(Items.ANCIENT_DEBRIS)
                                        .setWeight(10)
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f), false)))
                                .add(LootItem.lootTableItem(Items.NETHERITE_SCRAP)
                                        .setWeight(8)
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f), false)))
                                .add(LootItem.lootTableItem(Items.ANCIENT_DEBRIS)
                                        .setWeight(4)
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0f), false)))
                                .add(LootItem.lootTableItem(Items.DIAMOND_SWORD)
                                        .setWeight(6)
                                        .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.8f, 1.0f), false))
                                        .apply(EnchantRandomlyFunction.randomApplicableEnchantment(registries)))
                                .add(LootItem.lootTableItem(Items.DIAMOND_SPEAR)
                                        .setWeight(6)
                                        .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.8f, 1.0f), false))
                                        .apply(EnchantRandomlyFunction.randomApplicableEnchantment(registries)))
                                .add(LootItem.lootTableItem(Items.DIAMOND_CHESTPLATE)
                                        .setWeight(6)
                                        .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.8f, 1.0f), false))
                                        .apply(EnchantRandomlyFunction.randomApplicableEnchantment(registries)))
                                .add(LootItem.lootTableItem(Items.DIAMOND_HELMET)
                                        .setWeight(6)
                                        .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.8f, 1.0f), false))
                                        .apply(EnchantRandomlyFunction.randomApplicableEnchantment(registries)))
                                .add(LootItem.lootTableItem(Items.DIAMOND_LEGGINGS)
                                        .setWeight(6)
                                        .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.8f, 1.0f), false))
                                        .apply(EnchantRandomlyFunction.randomApplicableEnchantment(registries)))
                                .add(LootItem.lootTableItem(Items.DIAMOND_BOOTS)
                                        .setWeight(6)
                                        .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.8f, 1.0f), false))
                                        .apply(EnchantRandomlyFunction.randomApplicableEnchantment(registries)))
                                .add(LootItem.lootTableItem(Items.DIAMOND_SWORD).setWeight(6))
                                .add(LootItem.lootTableItem(Items.DIAMOND_SPEAR).setWeight(6))
                                .add(LootItem.lootTableItem(Items.DIAMOND_CHESTPLATE).setWeight(5))
                                .add(LootItem.lootTableItem(Items.DIAMOND_HELMET).setWeight(5))
                                .add(LootItem.lootTableItem(Items.DIAMOND_BOOTS).setWeight(5))
                                .add(LootItem.lootTableItem(Items.DIAMOND_LEGGINGS).setWeight(5))
                                .add(LootItem.lootTableItem(Items.DIAMOND)
                                        .setWeight(5)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f, 6.0f), false)))
                                .add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE)
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f), false)))
                )
                .withPool(
                        LootPool.lootPool()
                                .setBonusRolls(ConstantValue.exactly(0.0f))
                                .setRolls(UniformGenerator.between(3.0f, 4.0f))
                                .add(LootItem.lootTableItem(Items.SPECTRAL_ARROW)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(12.0f, 25.0f), false)))
                                .add(LootItem.lootTableItem(Items.GOLD_BLOCK)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f, 5.0f), false)))
                                .add(LootItem.lootTableItem(Items.IRON_BLOCK)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f, 5.0f), false)))
                                .add(LootItem.lootTableItem(Items.GOLD_INGOT)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0f, 9.0f), false)))
                                .add(LootItem.lootTableItem(Items.IRON_INGOT)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0f, 9.0f), false)))
                                .add(LootItem.lootTableItem(Items.CRYING_OBSIDIAN)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0f, 5.0f), false)))
                                .add(LootItem.lootTableItem(Items.QUARTZ)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(8.0f, 23.0f), false)))
                                .add(LootItem.lootTableItem(Items.GILDED_BLACKSTONE)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(5.0f, 15.0f), false)))
                                .add(LootItem.lootTableItem(Items.MAGMA_CREAM)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0f, 8.0f), false)))
                )
                .withPool(
                        LootPool.lootPool()
                                .setBonusRolls(ConstantValue.exactly(0.0f))
                                .setRolls(ConstantValue.exactly(1.0f))
                                .add(EmptyLootItem.emptyItem().setWeight(11))
                                .add(LootItem.lootTableItem(Items.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE))
                )
                .withPool(
                        LootPool.lootPool()
                                .setBonusRolls(ConstantValue.exactly(0.0f))
                                .setRolls(ConstantValue.exactly(1.0f))
                                .add(LootItem.lootTableItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                )
                .withPool(LootPool.lootPool()
                        .setBonusRolls(ConstantValue.exactly(0.0f))
                        .setRolls(ConstantValue.exactly(1.0f))
                        .add(EmptyLootItem.emptyItem()
                                .setWeight(1))
                        .add(LootItem.lootTableItem(HToolsItems.VEIN_MINER_UPGRADE_SMITHING_TEMPLATE)
                                .setWeight(1)
                                .setQuality(2))     // Bonus rolls for Looting
                );
    }
}
