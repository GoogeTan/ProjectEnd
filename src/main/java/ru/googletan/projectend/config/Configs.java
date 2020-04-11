package ru.googletan.projectend.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.googletan.projectend.Projectend;

@Config(modid = Projectend.MOD_ID, category = "")
@Mod.EventBusSubscriber(modid = Projectend.MOD_ID)
public class Configs 
{
	public static ConfigWorldGen worldgen = new ConfigWorldGen();
	
	public static class ConfigWorldGen 
	{
		@Config.Comment({"Controls size of end biomes. Larger number = larger biomes", "Default: 4"})
		@Config.RequiresWorldRestart
		public int endBiomeSize = 4;
		
		@Config.Comment({"Controls how often large end trees generate. Larger number = less trees", "Default: 7"})
		@Config.RequiresWorldRestart
		public int treeFrequency = 7;
		
		@Config.Comment({"Reduce number of end biomes by percent (range 0-99). e.g. 40 would generate 40% fewer end biomes", "Default: 0"})
		@Config.RequiresWorldRestart
		public int biomeReducer = 0;
	}
	
	@SubscribeEvent
	public static void onConfigReload(ConfigChangedEvent.OnConfigChangedEvent event) 
	{
		if (Projectend.MOD_ID.equals(event.getModID()))
			ConfigManager.sync(Projectend.MOD_ID, Config.Type.INSTANCE);
	}

}
