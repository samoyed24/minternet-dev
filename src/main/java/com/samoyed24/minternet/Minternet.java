package com.samoyed24.minternet;

import com.samoyed24.minternet.block.ModBlocks;
import com.samoyed24.minternet.block.entity.ModBlockEntityTypes;
import com.samoyed24.minternet.item.ModItemGroups;
import com.samoyed24.minternet.item.ModItems;
import com.samoyed24.minternet.recipe.ModRecipeTypes;
import com.samoyed24.minternet.screen.ModScreenHandlers;
import com.samoyed24.minternet.tags.ModBlockTags;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Minternet implements ModInitializer {
	public static final String MOD_ID = "minternet";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModItems.registerModItems();
		ModItemGroups.registerModItemGroups();
		ModBlocks.registerModBlocks();
		ModBlockEntityTypes.registerBlockEntityTypes();
		ModScreenHandlers.registerScreenHandlers();
		ModBlockTags.registerModBlockTags();
		ModRecipeTypes.registerRecipeTypes();
		LOGGER.info("Hello Fabric world!");
	}
}