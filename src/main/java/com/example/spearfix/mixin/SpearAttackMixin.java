package com.example.spearfix.mixin;

import com.example.spearfix.SpearItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class SpearAttackMixin {
    
    @Inject(method = "attack", at = @At("HEAD"))
    private void onAttack(Entity target, CallbackInfo ci) {
        PlayerEntity attacker = (PlayerEntity) (Object) this;
        
        // Intercept if attacker is holding the custom Spear
        if (attacker.getMainHandStack().getItem() instanceof SpearItem) {
            // FIX THE SELF-SLOWNESS BUG: Transfer the slowness effect to the target!
            // 1. Immediately remove any slowness active on the attacker from the sprint/charge drawback
            if (attacker.hasStatusEffect(StatusEffects.SLOWNESS)) {
                attacker.removeStatusEffect(StatusEffects.SLOWNESS);
            }
            
            // 2. Transfer the Slowness to the hit target (duration: 4s, level: II)
            if (target instanceof LivingEntity targetEntity) {
                targetEntity.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.SLOWNESS, 
                    80, // duration in ticks (20 ticks = 1 sec)
                    1, // amplifier (II)
                    false, 
                    true, 
                    true
                ));
                
                // Play damage indicators
                targetEntity.playSound(net.minecraft.sound.SoundEvents.ITEM_TRIDENT_HIT, 0.8F, 1.2F);
            }
        }
    }
}