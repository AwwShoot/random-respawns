package net.fabricmc.random_respawns.mixin;


import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    /* If the spawnpointposition is ever null, then PlayerManager will skip calling PlayerEntity.findRespawnPosition
     * which means the spawnpoint will not be randomized.
     */
    @Inject(at = @At("TAIL"), method = "getSpawnPointPosition", cancellable = true)
    private void positionNotNull(CallbackInfoReturnable<BlockPos> info) {
        info.setReturnValue(new BlockPos(0,0,0));
    }

}
