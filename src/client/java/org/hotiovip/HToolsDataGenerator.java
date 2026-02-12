package org.hotiovip;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.hotiovip.loot.HToolsChestLootTableProvider;
import org.hotiovip.recipe.HToolsRecipeProvider;
import org.hotiovip.tag.HToolsItemTagProvider;

public class HToolsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        // Recipe
        pack.addProvider(HToolsRecipeProvider::new);

        // Loot
        pack.addProvider(HToolsChestLootTableProvider::new);

        // Tags
        pack.addProvider(HToolsItemTagProvider::new);
	}
}
