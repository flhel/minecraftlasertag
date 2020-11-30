package lasertag.entity;

import lasertag.item.Phaser;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class LaserstrahlEntity extends AbstractArrowEntity implements IRendersAsItem {	
	
	public static final RegistryObject<Item> LASERSTRAHL_ITEM = RegistryObject.of(new ResourceLocation("lasertag:laserstrahl"), ForgeRegistries.ITEMS);
	
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

	@Override
	@OnlyIn(Dist.CLIENT)
	public ItemStack getItem() {
		return new ItemStack(Items.END_CRYSTAL, (int) (1));
	}

	
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	
	@Override
	public void tick() {
		System.out.println("lflag1");
		super.tick();
		if (this.inGround) {
			this.remove();
		}
	}	
}

