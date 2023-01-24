package me.pr3.uranite.impl.base.mixin.mixins;


import me.pr3.cdi.extensions.events.EventManager;
import me.pr3.uranite.impl.base.events.packet.PacketSentEvent;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetworkManager.class)
public class BaseMixinNetworkManager {
    @Inject(method = "sendPacket*", at = @At("RETURN"), cancellable = true)
    public void sendPacket(Packet<?> packetIn, CallbackInfo ci) {
        PacketSentEvent packetSentEvent = new PacketSentEvent(packetIn);
        EventManager.post(packetSentEvent);
        if (packetSentEvent.isCanceled()) {
            ci.cancel();
        }
    }
}
