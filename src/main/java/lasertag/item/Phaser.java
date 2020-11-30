package lasertag.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShootableItem;
import net.minecraft.item.UseAction;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Random;
import java.util.function.Predicate;

import lasertag.client.entity.render.CustomRender;
import lasertag.entity.LaserstrahlEntity;
import lasertag.entity.ModEntityType;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;

public class Phaser extends ShootableItem {
	public static final RegistryObject<Item> LASERSTRAHL_ITEM = RegistryObject.of(new ResourceLocation("lasertag:laserstrahl_item"), ForgeRegistries.ITEMS);
	
	public static EntityType<LaserstrahlEntity> arrow = null;
	
	public Phaser() {
		super(new Properties().group(ItemGroup.COMBAT));
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
	}
	
	private void commonSetup(FMLCommonSetupEvent evt) {
		arrow = ModEntityType.LASERSTRAHL_ENTITY.get(); 
		System.out.println("hey222" + arrow);
		RenderingRegistry.registerEntityRenderingHandler(arrow, renderManager -> new CustomRender(renderManager));
		System.out.println("hey333");
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, LivingEntity entityLiving, int timeLeft) {
		System.out.println("shot1");
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerentity = (PlayerEntity)entityLiving;
			boolean flag = playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
			ItemStack itemstack = playerentity.findAmmo(stack);

			if (!world.isRemote && entityLiving instanceof ServerPlayerEntity) {
				ServerPlayerEntity entity = (ServerPlayerEntity) entityLiving;

				if (!itemstack.isEmpty() || flag) {
					boolean flag1 = playerentity.abilities.isCreativeMode || (itemstack.getItem() instanceof LaserstrahlItem && ((LaserstrahlItem)itemstack.getItem()).isInfinite(itemstack, stack, playerentity));
					
					LaserstrahlEntity entityarrow = shoot(world, entity, random, 2f, 0, 5);
					itemstack.damageItem(1, entity, e -> e.sendBreakAnimation(entity.getActiveHand()));
					entityarrow.pickupStatus = AbstractArrowEntity.PickupStatus.DISALLOWED;
					
					if (!flag1 && !playerentity.abilities.isCreativeMode) {
						itemstack.shrink(1);
						if (itemstack.isEmpty()) {
							playerentity.inventory.deleteStack(itemstack);
						}
					}
					
				}
			}
		}
	}
	
	public static LaserstrahlEntity shoot(World world, LivingEntity entity, Random random, float power, double damage, int knockback) {
		System.out.println("shot2");
		LaserstrahlEntity entityarrow = new LaserstrahlEntity(arrow, entity, world);
		entityarrow.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, power * 2, 0);
		entityarrow.setSilent(true);
		entityarrow.setIsCritical(false);
		entityarrow.setDamage(damage);
		entityarrow.setKnockbackStrength(knockback);
		world.addEntity(entityarrow);
		double x = entity.getPosX();
		double y = entity.getPosY();
		double z = entity.getPosZ();
		world.playSound((PlayerEntity) null, (double) x, (double) y, (double) z,
				(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.end_portal.spawn")),
				SoundCategory.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));

		return entityarrow;
	}

/*
	public void shoot(ItemStack stack, World worldIn, PlayerEntity playerentity) {
		
		// flag -> Munitionsverbrauch an/aus
		

		net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, 10, !itemstack.isEmpty() || flag);

		if (!itemstack.isEmpty() || flag) {
			if (itemstack.isEmpty()) {
				itemstack = new ItemStack(LASERSTRAHL_ITEM.get());
			}

			float f = getArrowVelocity();
			boolean flag1 = playerentity.abilities.isCreativeMode || (itemstack.getItem() instanceof LaserstrahlItem && ((LaserstrahlItem)itemstack.getItem()).isInfinite(itemstack, stack, playerentity));
			if (!worldIn.isRemote) {
				LaserstrahlItem arrowitem = (LaserstrahlItem)(itemstack.getItem() instanceof LaserstrahlItem ? itemstack.getItem() : LASERSTRAHL_ITEM.get());
				//ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
				AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(worldIn, itemstack, playerentity);
				abstractarrowentity.func_234612_a_(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F, 1.0F);

				abstractarrowentity.setIsCritical(false);

				abstractarrowentity.setDamage(abstractarrowentity.getDamage());
				abstractarrowentity.setKnockbackStrength(1);
				abstractarrowentity.pickupStatus = AbstractArrowEntity.PickupStatus.DISALLOWED;
				abstractarrowentity.shoot(playerentity.getLookVec().x, playerentity.getLookVec().y, playerentity.getLookVec().z, 5, 0);
				worldIn.addEntity(abstractarrowentity); //hier nach bug    EntityRendererManager
			}
			//Sound hier
			worldIn.playSound((PlayerEntity)null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
			if (!flag1 && !playerentity.abilities.isCreativeMode) {
				itemstack.shrink(1);
				if (itemstack.isEmpty()) {
					playerentity.inventory.deleteStack(itemstack);
				}
			}

			playerentity.addStat(Stats.ITEM_USED.get(this));
		}
	}	*/         

	/**
	 * Gets the velocity of the arrow entity 
	 */
	public static float getArrowVelocity() {
		return 20.0f;
	}

	/**
	 * returns the action that specifies what animation to play when the items is being used
	 */

	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BOW;
	}
	
	/*
	 * UseDuration
	 */
	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 5000;
	}

	/**
	 * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see
	 * {@link #onItemUse}.
	 */

	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

		ItemStack itemstack = playerIn.getHeldItem(handIn);
		boolean flag = !playerIn.findAmmo(itemstack).isEmpty();

		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
		if (ret != null) return ret;

		if (!playerIn.abilities.isCreativeMode && !flag) {
			return ActionResult.resultFail(itemstack);
		} else {
			playerIn.setActiveHand(handIn);

			//shoot(itemstack, worldIn, playerIn);
			return ActionResult.resultConsume(itemstack);
		}
	}
	/**
	 * Get the predicate to match ammunition when searching the player's inventory, not their main/offhand
	 * Find them in src\main\resources\data\lasertag\tags\items\laserstrahl_items:json
	 */

	public Predicate<ItemStack> getInventoryAmmoPredicate() {
		Predicate<ItemStack> LASER = (stack) -> {
		      return stack.getItem().isIn(ItemTags.getCollection().get(new ResourceLocation("lasertag", "laserstrahl_items")));
		   };   
		return LASER;
	}
	
	public int func_230305_d_() {
	      return 15;
	}
}