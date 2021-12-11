package net.fabricmc.random_respawns.mixin;

import net.fabricmc.random_respawns.RRCore;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
	@Shadow private RegistryKey<World> spawnPointDimension;

	@Shadow private BlockPos spawnPointPosition;

	@Shadow private float spawnAngle;

	@Shadow public void setSpawnPoint(RegistryKey<World> dimension, BlockPos pos, float angle, boolean spawnPointSet, boolean bl){}

	public ServerPlayerEntityMixin(EntityType<?> type, World world) {
		super();
	}

	@Inject(at=@At("RETURN"), method="onDeath", cancellable = true)
	public void goToHell(DamageSource source, CallbackInfo ci){
		final Random random = new Random();
		BlockPos pos;
		BlockPos spawnPoint = spawnPointPosition;


		if(spawnPoint==null) {
			pos = new BlockPos(random.nextInt(16000) - 8000, random.nextInt(100)+20, random.nextInt(16000) - 8000);
		}else{
			pos = spawnPoint.add(random.nextInt(16000)-8000,0,random.nextInt(16000)-8000);
		}

		this.setSpawnPoint(World.OVERWORLD, pos, spawnAngle, true, false);
		System.out.println(spawnPointPosition.getY());


	}




}
