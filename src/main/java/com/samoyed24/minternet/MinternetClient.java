package com.samoyed24.minternet;

import com.samoyed24.minternet.screen.ModScreenHandlers;
import com.samoyed24.minternet.screen.WeldingTableScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class MinternetClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.WELDING_TABLE_SCREEN_HANDLER, WeldingTableScreen::new);
    }
}
