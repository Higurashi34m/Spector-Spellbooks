package dev.higurashi.spector_spellbooks.datagen.server;

import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.damagesource.DamageScaling;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class SSDamageTypeProvider implements DataProvider {
    private final PackOutput output;

    public SSDamageTypeProvider(PackOutput output) {
        this.output = output;
    }

    @Override @NotNull
    public CompletableFuture<?> run(@NotNull CachedOutput output) {
        Path path = this.output.getOutputFolder(PackOutput.Target.DATA_PACK).resolve("spector_spellbooks/damage_type/spector_magic.json");

        JsonObject root = new JsonObject();
        root.addProperty("exhaustion", 0);
        root.addProperty("message_id", "spector_magic");
        root.addProperty("scaling", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER.getSerializedName());

        return DataProvider.saveStable(output, root, path);
    }

    @Override @NotNull
    public String getName() {
        return "";
    }
}
