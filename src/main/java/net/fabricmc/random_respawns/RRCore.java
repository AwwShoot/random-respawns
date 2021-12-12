package net.fabricmc.random_respawns;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RRCore implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("random-respawns");

	@Override
	public void onInitialize() {


		EntitySleepEvents.ALLOW_SETTING_SPAWN.register((player, sleepingPos) -> false);
		LOGGER.info("test");

		ServerPlayerEvents.AFTER_RESPAWN.register(((oldPlayer, newPlayer, alive) -> newPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 600))
			));
	}
}
