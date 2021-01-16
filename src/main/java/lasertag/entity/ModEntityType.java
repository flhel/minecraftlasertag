package lasertag.entity;

import lasertag.Utils;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityType {
  public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Utils.MOD_ID);
  public static final RegistryObject<EntityType<LaserstrahlEntityRed>> LASERSTRAHL_ENTITY_RED
  = ENTITY_TYPES.register("laserstrahl_entity_red", () -> EntityType.Builder.<LaserstrahlEntityRed>create(LaserstrahlEntityRed::new, EntityClassification.MISC)
		  		.size(0.5F, 0.5F)
		  		.setShouldReceiveVelocityUpdates(false)
		  		.setTrackingRange(64)
		  		.setUpdateInterval(1)
		  		.setCustomClientFactory(LaserstrahlEntityRed::new)		
		  		.build(new ResourceLocation(Utils.MOD_ID,"laserstrahl_entity_red").toString())); 
  public static final RegistryObject<EntityType<LaserstrahlEntityBlue>> LASERSTRAHL_ENTITY_BLUE
  = ENTITY_TYPES.register("laserstrahl_entity_blue", () -> EntityType.Builder.<LaserstrahlEntityBlue>create(LaserstrahlEntityBlue::new, EntityClassification.MISC)
		  		.size(0.5F, 0.5F)
		  		.setShouldReceiveVelocityUpdates(false)
		  		.setTrackingRange(64)
		  		.setUpdateInterval(1)
		  		.setCustomClientFactory(LaserstrahlEntityBlue::new)		
		  		.build(new ResourceLocation(Utils.MOD_ID,"laserstrahl_entity_blue").toString())); 
}
