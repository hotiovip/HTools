package org.hotiovip;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import org.hotiovip.item.VeinMinerPickaxe;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;

public class HToolsItems {
    // Diamond gold ingot
    public static final Item DIAMOND_GOLD_INGOT = register(
            "diamond_gold_ingot",
            Item::new,
            new Item.Properties());
    // Netherite diamond gold ingot
    public static final Item NETHERITE_DIAMOND_GOLD_INGOT = register(
            "netherite_diamond_gold_ingot",
            Item::new,
            new Item.Properties());
    // Vein miner smithing template
    private static List<Identifier> createVeinMinerUpgradeIconList() {
        return List.of(
                Identifier.withDefaultNamespace("container/slot/pickaxe")
        );
    }
    private static List<Identifier> createVeinMinerUpgradeMaterialList() {
        return List.of(
                Identifier.withDefaultNamespace("container/slot/ingot")
        );
    }
    private static SmithingTemplateItem createVeinMinerUpgradeTemplate(Item.Properties properties) {
        return new SmithingTemplateItem(
                Component.translatable("item.htools.vein_template.applies_to"),
                Component.translatable("item.htools.vein_template.ingredients"),
                Component.translatable("item.htools.vein_template.base_slot"),
                Component.translatable("item.htools.vein_template.additions_slot"),
                createVeinMinerUpgradeIconList(),
                createVeinMinerUpgradeMaterialList(),
                properties
        );
    }
    public static final Item VEIN_MINER_UPGRADE_SMITHING_TEMPLATE = register(
            "vein_miner_upgrade_smithing_template",
            HToolsItems::createVeinMinerUpgradeTemplate,
            new Item.Properties().rarity(Rarity.RARE));


    // Diamond golden pickaxe
    public static final TagKey<@NotNull Item> REPAIRS_DIAMOND_GOLDEN_TOOLS = TagKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(HTools.MOD_ID, "repairs_diamond_golden_tools"));
    public static final ToolMaterial DIAMOND_GOLDEN_TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            ToolMaterial.DIAMOND.durability(),
            ToolMaterial.GOLD.speed(),
            ToolMaterial.GOLD.attackDamageBonus(),
            ToolMaterial.GOLD.enchantmentValue(),
            REPAIRS_DIAMOND_GOLDEN_TOOLS
    );
    public static final Item DIAMOND_GOLDEN_PICKAXE = register(
            "diamond_golden_pickaxe",
            Item::new,
            new Item.Properties().pickaxe(DIAMOND_GOLDEN_TOOL_MATERIAL, 1.0F, -2.8F)
    );

    // Netherite diamond golden pickaxe
    public static final TagKey<@NotNull Item> REPAIRS_NETHERITE_DIAMOND_GOLDEN_TOOLS = TagKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(HTools.MOD_ID, "repairs_netherite_diamond_golden_tools"));
    public static final ToolMaterial NETHERITE_DIAMOND_GOLDEN_TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            ToolMaterial.NETHERITE.durability(),
            ToolMaterial.GOLD.speed(),
            ToolMaterial.GOLD.attackDamageBonus(),
            ToolMaterial.GOLD.enchantmentValue(),
            REPAIRS_NETHERITE_DIAMOND_GOLDEN_TOOLS
    );
    public static final Item NETHERITE_DIAMOND_GOLDEN_PICKAXE = register(
            "netherite_diamond_golden_pickaxe",
            Item::new,
            new Item.Properties().pickaxe(NETHERITE_DIAMOND_GOLDEN_TOOL_MATERIAL, 1.0F, -2.8F)
    );

    // vein miner pickaxe
    public static final TagKey<@NotNull Item> REPAIRS_VEIN_MINER_TOOLS = TagKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(HTools.MOD_ID, "repairs_vein_miner_tools"));
    public static final ToolMaterial VEIN_MINER_TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            4062,
            15.0F,
            5.0F,
            30,
            REPAIRS_VEIN_MINER_TOOLS
    );
    public static final Item VEIN_MINER_PICKAXE = register(
            "vein_miner_pickaxe",
            VeinMinerPickaxe::new,
            new Item.Properties().pickaxe(VEIN_MINER_TOOL_MATERIAL, 1.0F, -2.0F)
    );


    public static <GenericItem extends Item> GenericItem register(String name, Function<Item.Properties, GenericItem> itemFactory, Item.Properties settings) {
        // Create the item key.
        ResourceKey<@NotNull Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(HTools.MOD_ID, name));

        // Create the item instance.
        GenericItem item = itemFactory.apply(settings.setId(itemKey));

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static void initialize() {
        // Add items to creative inventory

        // DIAMOND GOLD INGOT
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS)
                .register((itemGroup) -> itemGroup.accept(DIAMOND_GOLD_INGOT));
        // NETHERITE DIAMOND GOLD INGOT
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS)
                .register((itemGroup) -> itemGroup.accept(NETHERITE_DIAMOND_GOLD_INGOT));
        // VEIN MINER SMITHING TEMPLATE
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS)
                .register((itemGroup) -> itemGroup.accept(VEIN_MINER_UPGRADE_SMITHING_TEMPLATE));

        // DIAMOND GOLDEN PICKAXE
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register((itemGroup) -> itemGroup.accept(DIAMOND_GOLDEN_PICKAXE));
        // NETHERITE DIAMOND GOLDEN PICKAXE
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register((itemGroup) -> itemGroup.accept(NETHERITE_DIAMOND_GOLDEN_PICKAXE));
        // VEIN MINER PICKAXE
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register((itemGroup) -> itemGroup.accept(VEIN_MINER_PICKAXE));
    }
}
