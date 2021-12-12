package net.fabricmc.random_respawns.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.RespawnAnchorBlock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RespawnAnchorBlock.class)
public class RespawnAnchorBlockMixin {
        private static final Logger LOGGER = LogManager.getLogger("random-respawns");
        // Prevents respawn anchors from charging. When an anchor runs out of charges it causes issues
        @Inject(at=@At("RETURN"), method = "canCharge", cancellable = true)
        private static void noCharging(BlockState state, CallbackInfoReturnable<Boolean> info) {
                LOGGER.info("prevented charging of a respawn anchor");
                info.setReturnValue(false);
        }
}
