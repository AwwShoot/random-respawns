package net.fabricmc.random_respawns.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.Random;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    private static final Logger LOGGER = LogManager.getLogger("random-respawns");
    @Inject(at=@At("RETURN"), method="findRespawnPosition", cancellable = true)
    private static void randomRespawn(ServerWorld world, BlockPos pos, float angle, boolean forced, boolean alive, CallbackInfoReturnable<Optional<Vec3d>> info) {
        /* Gives the player a random respawn position within 8k X and Y of their previous one.
         * Sets the player's height to maximum height to account for all elevations
         * The player will be given feather falling upon respawn.
         */
        final Random random = new Random();
        pos = pos.add(random.nextInt(16000)-8000,320 - pos.getY(),random.nextInt(16000)-8000);
        LOGGER.info("Randomizing spawnpoint");
        info.setReturnValue(Optional.of(new Vec3d((double)pos.getX() + 0.5, (double)pos.getY() + 0.1, (double)pos.getZ() + 0.5)));
    }

}
