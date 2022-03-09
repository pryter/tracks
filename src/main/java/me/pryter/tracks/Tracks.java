package me.pryter.tracks;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.pryter.tracks.registry.Events;
import me.pryter.tracks.registry.Items;
import me.pryter.tracks.utils.UnZipper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

public class Tracks implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("tracks");
	private final String PATCH_VERSION = "1.0.1";

	public static void downloadFile(URL url, String fileName) throws Exception {
        try (InputStream in = url.openStream()) {
            Files.copy(in, Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
        }
    }

	public static String URLReader(URL url, Charset encoding) throws IOException {
		try (InputStream in = url.openStream()) {
			return IOUtils.toString(in, encoding);
		}
	}
	
	@Override
	public void onInitialize() {

		LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, identifier, fabricLootSupplierBuilder, lootTableSetter) -> {
			if (new Identifier("minecraft", "entities/creeper").equals(identifier)) {

				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
					.rolls(ConstantLootNumberProvider.create(1f))
					.conditionally(RandomChanceWithLootingLootCondition.builder(0.15f, 2))
					.withEntry(ItemEntry.builder(Items.Disc_Package).build());
		
				fabricLootSupplierBuilder.withPool(poolBuilder.build());
			}
		});

		Items.init();
		Events.init();
		
		String latestPatch;

		try {
			latestPatch = URLReader(new URL("https://davikassk.com/patches/current.txt"), Charset.forName("ascii"));
			LOGGER.info(latestPatch);
		} catch (MalformedURLException e1) {
			LOGGER.info("Unable to fetch latest patches details. You are using "+ PATCH_VERSION + " patches.");
			e1.printStackTrace();
			return;
		} catch (IOException e1) {
			LOGGER.info("Unable to fetch latest patches details. You are using "+ PATCH_VERSION + " patches.");
			e1.printStackTrace();
			return;
		}
		

		if (latestPatch.equals(PATCH_VERSION)) {
			LOGGER.info("Your already have up-to-date patches! Skipping patches upgrade.");
			return;
		} 

		final String patchFileName = "patch-"+latestPatch+".zip";

		final Path folderPath = FabricLoader.getInstance().getGameDir();
		final Path modPatchesPath = folderPath.resolve("mods/"+patchFileName);

		try {
			File f = new File(modPatchesPath.toString());
			if(!f.exists() || f.isDirectory()) { 
				downloadFile(new URL("https://storage.googleapis.com/mecdn/minecraft/patch/"+patchFileName), modPatchesPath.toString());
			}else{
				LOGGER.info("Patch already exist. Skipping patches download.");
			}

			LOGGER.info("Extracting patches data..");

			try{
				UnZipper.unzip(modPatchesPath.toString(), folderPath.resolve("mods/").toString());

				modPatchesPath.toFile().delete();
				LOGGER.info("Folder cleanup..");
			} catch (IOException e) {
				LOGGER.error("Unable to extract patches file!");
			}

		} catch (MalformedURLException e) {
		
			LOGGER.error("Unable to download patches file!");
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Unable to download patches file!");
			e.printStackTrace();
		}

		
	}
}
