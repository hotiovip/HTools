package org.hotiovip.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.hotiovip.HTools;
import org.jetbrains.annotations.NotNull;

public class VeinMinerPickaxe extends Item {
    public VeinMinerPickaxe(Properties properties) {
        super(properties);
    }

    @Override
    public boolean mineBlock(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull BlockState blockState, @NotNull BlockPos blockPos, @NotNull LivingEntity livingEntity) {
        Identifier cOres = Identifier.tryBuild("c", "ores");
        if (cOres != null) {
            TagKey<@NotNull Block> cOresTag = TagKey.create(Registries.BLOCK, cOres);
            HTools.LOGGER.info(String.valueOf(blockState.is(cOresTag)));
        }
        return true;
    }
}
