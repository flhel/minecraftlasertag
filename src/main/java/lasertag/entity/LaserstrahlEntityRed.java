package lasertag.entity;

import lasertag.Utils;
import lasertag.item.PhaserRed;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

//@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class LaserstrahlEntityRed extends AbstractArrowEntity {	
	
	public static final RegistryObject<Item> LASERSTRAHL_ITEM = RegistryObject.of(new ResourceLocation(Utils.MOD_ID,"laserstrahl"), ForgeRegistries.ITEMS);
	
	public LaserstrahlEntityRed(EntityType<? extends LaserstrahlEntityRed> type, World worldIn) {
		super(type, worldIn);
	}
	
	public LaserstrahlEntityRed(FMLPlayMessages.SpawnEntity packet, World world) {
		super(PhaserRed.arrow, world);
	}

	public LaserstrahlEntityRed(EntityType<? extends LaserstrahlEntityRed> type, double x, double y, double z, World world) {
		super(type, x, y, z, world);
	}

	public LaserstrahlEntityRed(EntityType<? extends LaserstrahlEntityRed> type, LivingEntity entity, World world) {
		super(type, entity, world);
	}
	
	protected ItemStack getArrowStack() {
		return new ItemStack(LASERSTRAHL_ITEM.get());
	}
	
	@Override //.ENTITY_ARROW_HIT
	protected SoundEvent getHitEntitySound() {
		return SoundEvents.BLOCK_ANVIL_HIT;
	}
	
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
	
	@Override
	protected void onEntityHit(EntityRayTraceResult p_213868_1_) {
		super.onEntityHit(p_213868_1_);
		this.remove();
		Entity entity = p_213868_1_.getEntity();
		if (entity instanceof LivingEntity) {
			LivingEntity livingentity = (LivingEntity)entity;
			if (!this.world.isRemote && this.getPierceLevel() <= 0) {
				/* 
				 * ArrowCountInEntity will get increased by +1 as a default because Lasers are only modded Arrows
				 * Setting the count -1 will fix the bug where Arrows appear when hitting Enemys
				 */
				livingentity.setArrowCountInEntity(livingentity.getArrowCountInEntity() - 1);
			}
		}
	}
	
	@Override
	protected void func_230299_a_(BlockRayTraceResult p_230299_1_) {
		//Soll momentan nichts tun außer die Methode leer zu überschreiben
	}

}

