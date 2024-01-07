package net.adrian.my_first_fabric_mod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.zip.CRC32;

@Mixin(InGameHud.class)
public class RenderGuiTickMixin {
    @Unique
    private String getCountryNameFromID(long id) {
        var syllables = new String[] {
                "ke", "fo", "bab", "kek", "kab", "so", "si", "sii", "gro", "grob", "grok", "grog", "ka", "lib", "su",
                "ffi", "kra", "kri", "kro", "kroa", "kroo"
        };

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 2; i++) {
            CRC32 crc32 = new CRC32();
            crc32.update((int)id);
            crc32.update(i);
            var chosenSyllableID = Math.abs((int)crc32.getValue());

            var chosenSyllable = syllables[chosenSyllableID % syllables.length];

            result.append(chosenSyllable);
        }

        return result.toString().toUpperCase() + "ISTAN";
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void render(DrawContext context, float tickDelta, CallbackInfo ci) {
        var player = MinecraftClient.getInstance().player;

        if (player != null) {
            int playerX = (int) player.getX();
            int playerY = (int) player.getY();
            int playerZ = (int) player.getZ();

            CRC32 crc32 = new CRC32();
            crc32.update(playerX / 32);
            crc32.update(playerZ / 32);
            var countryID = crc32.getValue();

            var text = getCountryNameFromID(countryID);

            var baseColor = 0xFFFFFF;
            var alpha = 128;
            var fontColor = baseColor | (alpha << 24);

            context.drawText(MinecraftClient.getInstance().textRenderer, text, 10, 10,
                    fontColor, true);
        }
    }
}
