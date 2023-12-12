package com.natamus.moveboats;

import com.natamus.collective.check.RegisterMod;
import com.natamus.collective.fabric.callbacks.CollectivePlayerEvents;
import com.natamus.moveboats.events.BoatEvent;
import com.natamus.moveboats.util.Reference;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class ModFabric implements ModInitializer {
	
	@Override
	public void onInitialize() {
		setGlobalConstants();
		ModCommon.init();

		loadEvents();

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadEvents() {
		CollectivePlayerEvents.PLAYER_TICK.register((ServerLevel world, ServerPlayer player) -> {
			BoatEvent.onPlayerTick(world, player);
		});

		UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			return BoatEvent.onBoatClick(player, world, hand, entity, hitResult);
		});
	}

	private static void setGlobalConstants() {

	}
}
