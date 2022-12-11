package me.pr3.uranite.impl.base.mixin.mixins;


import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetworkManager.class)
public class BaseMixinNetworkManager {
    @Inject(method = "sendPacket*", at = @At("RETURN"))
    public void sendPacket(Packet<?> packetIn, CallbackInfo ci){
       // System.out.println(packetIn.toString());
    }
}
