package lasertag.sounds;

import lasertag.Utils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSounds {
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Utils.MOD_ID);
	public static final RegistryObject<SoundEvent> PHASER_SOUND = SOUNDS.register("item.phaser_sound", () -> new SoundEvent(new ResourceLocation(Utils.MOD_ID, "item.phaser_sound.ogg")));
}
