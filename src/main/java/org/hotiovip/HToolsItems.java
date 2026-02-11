package org.hotiovip;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import org.hotiovip.item.VeinMinerPickaxe;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

//TODO: ADD REPAIR TAGS TO CUSTOM INGOTS TO MAKE PICKAXES REPAIRABLE
public class HToolsItems {
    // DIAMOND GOLDEN PICKAXE
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

    // NETHERITE DIAMOND GOLDEN PICKAXE
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

    // VEIN MINER PICKAXE
    public static final TagKey<@NotNull Item> REPAIRS_VEIN_MINER_TOOLS = TagKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(HTools.MOD_ID, "repairs_vein_miner_tools"));
    public static final ToolMaterial VEIN_MINER_TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            ToolMaterial.NETHERITE.durability(),
            ToolMaterial.GOLD.speed(),
            ToolMaterial.GOLD.attackDamageBonus(),
            ToolMaterial.GOLD.enchantmentValue(),
            REPAIRS_VEIN_MINER_TOOLS
    );
    public static final Item VEIN_MINER_PICKAXE = register(
            "vein_miner_pickaxe",
            VeinMinerPickaxe::new,
            new Item.Properties().pickaxe(VEIN_MINER_TOOL_MATERIAL, 1.0F, -2.8F)
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
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register((itemGroup) -> itemGroup.accept(DIAMOND_GOLDEN_PICKAXE));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register((itemGroup) -> itemGroup.accept(NETHERITE_DIAMOND_GOLDEN_PICKAXE));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register((itemGroup) -> itemGroup.accept(VEIN_MINER_PICKAXE));
    }
}
