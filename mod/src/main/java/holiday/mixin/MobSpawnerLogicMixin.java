package holiday.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import holiday.block.HolidayServerBlocks;
import net.minecraft.block.spawner.MobSpawnerLogic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MobSpawnerLogic.class)
public class MobSpawnerLogicMixin {
    @WrapMethod(method = "isPlayerInRange")
    boolean is(World world, BlockPos pos, Operation<Boolean> original) {
        for (Direction value : Direction.values()) {
            BlockPos add = pos.add(value.getOffsetX(), value.getOffsetY(), value.getOffsetZ());
            if (world.getBlockState(add).isOf(HolidayServerBlocks.ENDER_SPAWNER_AUGMENT)) return true;
        }
        return original.call(world, pos);
    }
}
