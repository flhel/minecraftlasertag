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
  public static final RegistryObject<EntityType<LaserstrahlEntity>> LASERSTRAHL_ENTITY
  = ENTITY_TYPES.register("laserstrahl_entity", () -> EntityType.Builder.<LaserstrahlEntity>create(LaserstrahlEntity::new, EntityClassification.MISC)
		  		.size(0.5F, 0.5F)
		  		.setShouldReceiveVelocityUpdates(true)
		  		.setTrackingRange(64)
		  		.setUpdateInterval(1)
		  		.setCustomClientFactory(LaserstrahlEntity::new)		
		  		.build(new ResourceLocation(Utils.MOD_ID,"laserstrahl_entity").toString())); 
}
