package org.hotiovip.loot;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import org.hotiovip.HTools;
import org.jetbrains.annotations.NotNull;

public class HToolsLootTables {
    public static ResourceKey<@NotNull LootTable> VEIN_MINER_UPGRADE_SMITHING_TEMPLATE_LOOT = ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(HTools.MOD_ID, "chests/vein_miner_upgrade_smithing_template_loot"));
}
