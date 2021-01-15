package lasertag;

import lasertag.entity.ModEntityType;
import lasertag.item.ModItems;
import lasertag.item.Phaser;
import lasertag.sounds.ModSounds;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Utils.MOD_ID)
public class Main {
	
	public Main() {
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModSounds.SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModEntityType.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverSetup);
    }	
	 
	private void commonSetup(FMLCommonSetupEvent evt) {
		Phaser.arrow = ModEntityType.LASERSTRAHL_ENTITY.get(); 
	}
	
	private void clientSetup(FMLClientSetupEvent evt) {
		new ClientProxy();
		/*
		ItemModelsProperties.registerProperty(new Phaser(), new ResourceLocation("lasertag:pull"), new IItemPropertyGetter() {
		    @Override
		    public float call(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity entity) {
		      return (float)getPowerLevel(stack) / (float)getMaxPower(stack); // Some external methods
		    }
		  }
		 */
	}
	
	private void serverSetup(FMLDedicatedServerSetupEvent evt) {
		// empty until needed
	}
}


