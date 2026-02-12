package org.hotiovip.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.hotiovip.HToolsItems;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class HToolsItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public static final TagKey<@NotNull Item> PICKAXES = ItemTags.PICKAXES;

    public HToolsItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider wrapperLookup) {
        valueLookupBuilder(PICKAXES)
                .add(HToolsItems.DIAMOND_GOLDEN_PICKAXE)
                .add(HToolsItems.NETHERITE_DIAMOND_GOLDEN_PICKAXE)
                .add(HToolsItems.VEIN_MINER_PICKAXE)
                .setReplace(false);
    }
}