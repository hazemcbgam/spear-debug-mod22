package com.example.spearfix.mixin;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PacketByteBuf.class)
public class ViaBackwardsPacketMixin {
    
    // Intercepts and overrides unresolvable item/block identifiers sent by newer proxy servers
    @Inject(method = "readIdentifier", at = @At("RETURN"), cancellable = true)
    private void onReadIdentifier(CallbackInfoReturnable<Identifier> cir) {
        Identifier id = cir.getReturnValue();
        if (id != null && id.getNamespace().equals("minecraft")) {
            String path = id.getPath();
            
            // Custom ID remappings generated from the configuration dashboard
            if (path.equals("trial_spawner")) {
                cir.setReturnValue(new Identifier("minecraft", "spawner"));
                return;
            }
            if (path.equals("copper_bulb")) {
                cir.setReturnValue(new Identifier("minecraft", "redstone_lamp"));
                return;
            }
            if (path.equals("crafter")) {
                cir.setReturnValue(new Identifier("minecraft", "dispenser"));
                return;
            }

            // Catch-all safety guard to prevent client registry lookups on 1.21 items throwing crashes
            if (path.contains("copper_bulb") || path.contains("trial_spawner") || path.contains("crafter") || path.contains("mace")) {
                cir.setReturnValue(new Identifier("minecraft", "stone"));
            }
        }
    }
}