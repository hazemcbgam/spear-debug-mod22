package com.example.spearfix;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SpearItem extends SwordItem {
    public SpearItem(Settings settings) {
        // Materials, Damage addition (7), Attack speed (1.6), settings
        super(ToolMaterials.DIAMOND, 3, -2.4f, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        
        // Dynamic stats injected from web panel
        float speedMultiplier = 1.4f;
        int cooldownTicks = 100;

        if (!world.isClient()) {
            // Apply speed boost to trigger the Spear's sprinting charge
            user.setSprinting(true);
            
            // Speed amplifier based on multiplier
            int amplifier = speedMultiplier > 1.8f ? 2 : (speedMultiplier > 1.3f ? 1 : 0);
            user.addStatusEffect(new StatusEffectInstance(
                StatusEffects.SPEED, 
                60, // 3 seconds charge window
                amplifier, 
                false, 
                false, 
                true
            ));
            
            // Play a swoosh sound for mechanical feedback
            world.playSound(null, user.getX(), user.getY(), user.getZ(), 
                SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1.0F, 0.8F);
            
            // Apply cooldown to custom item so it cannot be abused
            user.getItemCooldownManager().set(this, cooldownTicks);
        }
        
        return TypedActionResult.success(itemStack, world.isClient());
    }
}