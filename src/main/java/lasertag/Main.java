package lasertag;

import lasertag.client.entity.render.CustomRender;
import lasertag.entity.ModEntityType;
import lasertag.item.ModItems;
import lasertag.item.Phaser;
import lasertag.sounds.ModSounds;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@Mod(Utils.MOD_ID)
public class Main {
	
	public Main() {
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModSounds.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModEntityType.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);      
    }
	
	@OnlyIn(Dist.CLIENT)
	private void commonSetup(FMLCommonSetupEvent evt) {
		Phaser.arrow = ModEntityType.LASERSTRAHL_ENTITY.get(); 
		RenderingRegistry.registerEntityRenderingHandler(Phaser.arrow, renderManager -> new CustomRender(renderManager));
	}
	
}


