package com.samoyed24.minternet.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent RESIN = new FoodComponent.Builder()
            .nutrition(0)
            .saturationModifier(0.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 200), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200), 1.0f)
            .build();
}
