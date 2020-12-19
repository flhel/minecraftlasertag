package lasertag.entity;

import lasertag.Utils;
import lasertag.item.Phaser;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

//@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class LaserstrahlEntity extends AbstractArrowEntity {	
	
	public static final RegistryObject<Item> LASERSTRAHL_ITEM = RegistryObject.of(new ResourceLocation(Utils.MOD_ID,"laserstrahl"), ForgeRegistries.ITEMS);
	
	public LaserstrahlEntity(EntityType<? extends LaserstrahlEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	public LaserstrahlEntity(FMLPlayMessages.SpawnEntity packet, World world) {
		super(Phaser.arrow, world);
	}

	public LaserstrahlEntity(EntityType<? extends LaserstrahlEntity> type, double x, double y, double z, World world) {
		super(type, x, y, z, world);
	}

	public LaserstrahlEntity(EntityType<? extends LaserstrahlEntity> type, LivingEntity entity, World world) {
		super(type, entity, world);
	}
	
	protected ItemStack getArrowStack() {
		return new ItemStack(LASERSTRAHL_ITEM.get());
	}
	
	
	@Override //.ENTITY_ARROW_HIT
	protected SoundEvent getHitEntitySound() {
		return SoundEvents.BLOCK_ANVIL_HIT;
	}

	
	//KA was das soll kann eig null sein
	//@Override
	//@OnlyIn(Dist.CLIENT)
	//public ItemStack getItem() {
	//	return new ItemStack(Items.END_CRYSTAL, (int) (1));
	//}

	
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	
	@Override
	public void tick() {
		super.tick();
		if (this.inGround || this.inWater) {
			this.remove();
		}
	}
	
	protected void onEntityHit(EntityRayTraceResult p_213868_1_) {
	      super.onEntityHit(p_213868_1_);
	      this.remove();
	}
}

