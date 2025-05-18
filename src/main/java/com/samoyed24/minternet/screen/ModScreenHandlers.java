package com.samoyed24.minternet.screen;

import com.samoyed24.minternet.Minternet;
import com.samoyed24.minternet.data.ShapingTableData;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<WeldingTableScreenHandler> WELDING_TABLE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Minternet.MOD_ID, "welding_table"),
                    new ScreenHandlerType<>(WeldingTableScreenHandler::new, FeatureSet.empty()));
    public static final ScreenHandlerType<ShapingTableScreenHandler> SHAPING_TABLE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Minternet.MOD_ID, "shaping_table"),
                    new ExtendedScreenHandlerType<>(ShapingTableScreenHandler::new, ShapingTableData.CODEC));
    public static void registerScreenHandlers() {

    }
}
