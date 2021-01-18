package lasertag;

import lasertag.entity.ModEntityType;
import lasertag.item.MgBlue;
import lasertag.item.MgRed;
import lasertag.item.ModItems;
import lasertag.item.PhaserBlue;
import lasertag.item.PhaserRed;
import lasertag.sounds.ModSounds;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Utils.MOD_ID)
public class Main {

	public Main() {
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModSounds.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModEntityType.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    }	
	 
	private void commonSetup(FMLCommonSetupEvent evt) {
		PhaserRed.arrow = ModEntityType.LASERSTRAHL_ENTITY_RED.get();
		PhaserBlue.arrow = ModEntityType.LASERSTRAHL_ENTITY_BLUE.get(); 
		MgRed.arrow = ModEntityType.LASERSTRAHL_ENTITY_RED.get();
		MgBlue.arrow = ModEntityType.LASERSTRAHL_ENTITY_BLUE.get(); 
	}
	
	private void clientSetup(FMLClientSetupEvent evt) {
		new ClientProxy();
	}
}


