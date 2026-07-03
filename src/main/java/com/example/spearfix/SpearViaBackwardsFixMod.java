package com.example.spearfix;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpearViaBackwardsFixMod implements ModInitializer {
    public static final String MOD_ID = "spearfix";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // Register custom spear item with attributes configured on the website dashboard
    public static final Item SPEAR = new SpearItem(new Item.Settings().maxCount(1));

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Spear & ViaBackwards Fix (Fabric 1.20.1)!");
        
        // Register custom spear
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "spear"), SPEAR);
        LOGGER.info("Registered spear weapon successfully under ID: " + MOD_ID + ":spear");
    }
}