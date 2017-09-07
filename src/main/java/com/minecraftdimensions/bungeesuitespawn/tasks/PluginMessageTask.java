package com.minecraftdimensions.bungeesuitespawn.tasks;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.minecraftdimensions.bungeesuitespawn.utils.OptionalUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import com.minecraftdimensions.bungeesuitespawn.BungeeSuiteSpawn;


public class PluginMessageTask extends BukkitRunnable {
    
	private final ByteArrayOutputStream bytes;

	public PluginMessageTask(ByteArrayOutputStream bytes) {
		this.bytes = bytes;
	}
	
	public void run() {
			getOnlinePlayers().get(0).sendPluginMessage(
					BungeeSuiteSpawn.INSTANCE,
					BungeeSuiteSpawn.OUTGOING_PLUGIN_CHANNEL,
					bytes.toByteArray());
	
	}
	public static List<Player> getOnlinePlayers() {
		return new OptionalUtils<>(() -> {
			List<Player> onlinePlayers = new ArrayList<>();
			Bukkit.getWorlds().forEach(world -> onlinePlayers.addAll(world.getPlayers()));
			return onlinePlayers;
		}).getOptional().orElseGet(ArrayList::new);
	}
}