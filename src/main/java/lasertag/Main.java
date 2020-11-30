package lasertag;

import lasertag.entity.ModEntityType;
import lasertag.item.ModItems;
import lasertag.item.Phaser;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Utils.MOD_ID)
public class Main {
	public Main() {
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModEntityType.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
    }

	private void commonSetup(FMLCommonSetupEvent evt) {
		Phaser.init();
	}
}


