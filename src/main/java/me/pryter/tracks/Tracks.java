package me.pryter.tracks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.pryter.tracks.registry.Events;
import me.pryter.tracks.registry.Items;

public class Tracks implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

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
	}
}
