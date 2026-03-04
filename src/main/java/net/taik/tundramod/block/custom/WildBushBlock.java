package net.taik.tundramod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class WildBushBlock extends BushBlock implements BonemealableBlock {

    public WildBushBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
    }


    // bonemeal target check
    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                if (dx == 0 && dz == 0) continue;

                BlockPos target = pPos.offset(dx, 0, dz);
                if (pLevel.isEmptyBlock(target) && pState.canSurvive(pLevel, target)) {
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }
    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        for (int i = 0; i < 8; i++) { // tries like vanilla vegetation
            BlockPos target = pPos.offset(
                    pRandom.nextInt(3) - 1,
                    0,
                    pRandom.nextInt(3) - 1
            );

            if (pLevel.isEmptyBlock(target)
                    && pState.canSurvive(pLevel, target)) {

                pLevel.setBlock(target, pState, 3);
                return;
            }
        }

    }
}