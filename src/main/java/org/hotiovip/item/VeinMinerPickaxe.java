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

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class VeinMinerPickaxe extends Item {
    private final Set<BlockPos> visited = ConcurrentHashMap.newKeySet();
    private static final int MAX_VEIN_SIZE = 64;

    public VeinMinerPickaxe(Properties properties) {
        super(properties);
    }

    @Override
    public boolean mineBlock(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull BlockState blockState, @NotNull BlockPos blockPos, @NotNull LivingEntity livingEntity) {
        Identifier cOres = Identifier.tryBuild("c", "ores");
        if (cOres != null) {
            TagKey<@NotNull Block> cOresTag = TagKey.create(Registries.BLOCK, cOres);

            if (blockState.is(cOresTag)) {
                // Reset HashMap
                visited.clear();

                // Search for the same ore blocks attached to them
                List<BlockPos> attachedBlocks = getAttachedBlocks(level, blockState, blockPos);
                attachedBlocks.forEach(attachedBlock -> {
                    HTools.LOGGER.info(level.getBlockState(attachedBlock).toString());
                });

                // Destroy each of them
                attachedBlocks.forEach(attachedBlock -> {
                    level.destroyBlock(attachedBlock, true, livingEntity);
                });
            }
        }
        return true;
    }

    private List<BlockPos> getAttachedBlocks(Level level, BlockState sourceState, BlockPos startPos) {
        List<BlockPos> vein = new ArrayList<>();
        Queue<BlockPos> queue = new LinkedList<>();

        if (!level.isClientSide()) {
            queue.add(startPos);
            visited.add(startPos);
        }

        while (!queue.isEmpty() && vein.size() < MAX_VEIN_SIZE) {
            BlockPos pos = queue.poll();
            vein.add(pos);

            HTools.LOGGER.info("While");

            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        if (x == 0 && y == 0 && z == 0) continue;

                        HTools.LOGGER.info("Checking");

                        BlockPos offsetPos = pos.offset(x, y, z);
                        if (checkBlockPos(level, sourceState, offsetPos) && visited.add(offsetPos)) {
                            queue.add(offsetPos);
                            HTools.LOGGER.info("Adding BlockPos");
                        }
                    }
                }
            }
        }

        return vein;
    }

    private boolean checkBlockPos(Level level, BlockState sourceState, BlockPos blockPos) {
        return level.getBlockState(blockPos).is(sourceState.getBlock());
    }
}
