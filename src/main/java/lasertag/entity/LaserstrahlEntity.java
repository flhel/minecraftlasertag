package lasertag.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class LaserstrahlEntity extends AbstractArrowEntity implements IRendersAsItem {
	
	public static final RegistryObject<Item> LASERSTRAHL_ITEM = RegistryObject.of(new ResourceLocation("lasertag:laserstrahl"), ForgeRegistries.ITEMS);
	
	public LaserstrahlEntity(EntityType<? extends LaserstrahlEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public LaserstrahlEntity(World worldIn, double x, double y, double z) {
		super(ModEntityType.LASERSTRAHL_ENTITY.get(), x, y, z, worldIn);
	}

	public LaserstrahlEntity(World worldIn, LivingEntity shooter) {
		super(ModEntityType.LASERSTRAHL_ENTITY.get(), shooter, worldIn);
	}
	
	protected ItemStack getArrowStack() {
		return new ItemStack(LASERSTRAHL_ITEM.get());
	}
	
	@Override //.ENTITY_ARROW_HIT
	protected SoundEvent getHitEntitySound() {
		return SoundEvents.BLOCK_ANVIL_HIT;
	}

	@Override
	public ItemStack getItem() {
		// return new ItemStack(Items.END_CRYSTAL, (int) (1));
		return null;
	}
	
	/*
	@Override
	public void tick() {
		super.tick();
		double x = this.getPosX();
		double y = this.getPosY();
		double z = this.getPosZ();
		World world = this.world;
		Entity entity = this.shooter;
		if (this.inGround) {
			this.remove();
		}
	}
	
	public static LaserstrahlEntity shoot(World world, LivingEntity entity, Random random, float power, double damage, int knockback) {
		LaserstrahlEntity entityarrow = new LaserstrahlEntity(entity, world);
		entityarrow.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, power * 2, 0);
		entityarrow.setSilent(true);
		entityarrow.setIsCritical(true);
		entityarrow.setDamage(damage);
		entityarrow.setKnockbackStrength(knockback);
		world.addEntity(entityarrow);
		double x = entity.getPosX();
		double y = entity.getPosY();
		double z = entity.getPosZ();
		world.playSound((PlayerEntity) null, (double) x, (double) y, (double) z,
				(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.anvil.break")),
				SoundCategory.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
		return entityarrow;
	}

	public static LaserstrahlEntity shoot(LivingEntity entity, LivingEntity target) {
		LaserstrahlEntity entityarrow = new LaserstrahlEntity(entity, entity.world);
		double d0 = target.getPosY() + (double) target.getEyeHeight() - 1.1;
		double d1 = target.getPosX() - entity.getPosX();
		double d3 = target.getPosZ() - entity.getPosZ();
		entityarrow.shoot(d1, d0 - entityarrow.getPosY() + (double) MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 50f * 2, 12.0F);
		entityarrow.setSilent(true);
		entityarrow.setDamage(5);
		entityarrow.setKnockbackStrength(5);
		entityarrow.setIsCritical(true);
		entity.world.addEntity(entityarrow);
		double x = entity.getPosX();
		double y = entity.getPosY();
		double z = entity.getPosZ();
		entity.world.playSound((PlayerEntity) null, (double) x, (double) y, (double) z,
				(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.anvil.break")),
				SoundCategory.PLAYERS, 1, 1f / (new Random().nextFloat() * 0.5f + 1));
		return entityarrow;
	}
	
	*/
}
