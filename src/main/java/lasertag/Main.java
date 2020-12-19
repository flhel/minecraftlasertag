package lasertag;

import lasertag.entity.ModEntityType;
import lasertag.item.ModItems;
import lasertag.sounds.ModSounds;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Utils.MOD_ID)
public class Main {
	
	public Main() {
		System.out.println("hey");
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModSounds.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModEntityType.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverSetup);
    }	
	
	 
	private void commonSetup(FMLCommonSetupEvent evt) {
		// empty until needed
	}
	
	private void clientSetup(FMLClientSetupEvent evt) {
		new ClientProxy();
	}
	
	private void serverSetup(FMLDedicatedServerSetupEvent evt) {
		// empty until needed
	}
}


