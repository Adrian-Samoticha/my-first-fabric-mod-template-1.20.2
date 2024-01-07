package net.adrian.my_first_fabric_mod;

import net.adrian.my_first_fabric_mod.blocks.ModBlocks;
import net.adrian.my_first_fabric_mod.items.ModItemGroups;
import net.adrian.my_first_fabric_mod.items.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyFirstFabricMod implements ModInitializer {
    public static final String MOD_ID = "my-first-fabric-mod";

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("Hello Fabric world!");

        ModItemGroups.registerItemGroups();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();

        /*// run code very tick
        ClientTickEvents.END_CLIENT_TICK.register((client) -> {
            var player = MinecraftClient.getInstance().player;

            if (player != null) {
                var playerX = player.getX();
                var playerY = player.getY();
                var playerZ = player.getZ();

                LOGGER.info("Player position: " + playerX + ", " + playerY + ", " + playerZ);

                var world = MinecraftClient.getInstance().world;
                if (world != null) {
                    var biome = world.getBiome(new BlockPos((int) playerX, (int) playerY, (int) playerZ));
                }
            }
        });*/
    }
}