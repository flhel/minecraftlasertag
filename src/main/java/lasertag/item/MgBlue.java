package lasertag.item;

import java.util.function.Predicate;

import lasertag.Utils;
import lasertag.entity.LaserstrahlEntityBlue;
import lasertag.sounds.ModSounds;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
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
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class MgBlue extends ShootableItem{
	public static final RegistryObject<Item> LASERSTRAHL_ITEM = RegistryObject.of(new ResourceLocation(Utils.MOD_ID, "laserstrahl_item"), ForgeRegistries.ITEMS);
	
	public static EntityType<LaserstrahlEntityBlue> arrow = null;
	
	public MgBlue() {
		super(new Properties().group(ItemGroup.COMBAT).maxStackSize(1));	
	}
	
	@Override
	public void onUse(World world, LivingEntity entityLiving, ItemStack stack, int count) {
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerentity = (PlayerEntity)entityLiving;
			ItemStack itemstack = playerentity.findAmmo(stack);
			
			if (entityLiving instanceof ServerPlayerEntity) {
				ServerPlayerEntity entity = (ServerPlayerEntity) entityLiving;

				if (!itemstack.isEmpty() || playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0) {
					if (!world.isRemote) {
						shoot(world, entity, getArrowVelocity(), 1, 0);
					}
					
					world.playSound((PlayerEntity) null, entityLiving.getPosX(), entityLiving.getPosY(), entityLiving.getPosZ(), ModSounds.PHASER_SOUND.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
					itemstack.damageItem(1, entity, e -> e.sendBreakAnimation(entity.getActiveHand()));
					
					if (!(playerentity.abilities.isCreativeMode || (itemstack.getItem() instanceof LaserstrahlItem && ((LaserstrahlItem)itemstack.getItem()).isInfinite(itemstack, stack, playerentity)))) {
						itemstack.shrink(1);
						if (itemstack.isEmpty()) {
							playerentity.inventory.deleteStack(itemstack);
						}
					}					
				}
			}
		}
	}

	public static void shoot(World world, LivingEntity entity, float power, double damage, int knockback) {
		LaserstrahlEntityBlue entityarrow = new LaserstrahlEntityBlue(arrow, entity, world);
		entityarrow.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, power , 1.0f);
		entityarrow.setSilent(true);
		entityarrow.setIsCritical(false);
		entityarrow.setDamage(damage);
		entityarrow.setKnockbackStrength(knockback);
		entityarrow.func_234612_a_(entity, entity.rotationPitch, entity.rotationYaw, 0.0F, power , 1.0f); 
		entityarrow.setNoGravity(true);
		entityarrow.arrowShake = 0;
		entityarrow.pickupStatus = AbstractArrowEntity.PickupStatus.DISALLOWED;
		world.addEntity(entityarrow);
	}

	@Override
	public int func_230305_d_() {
		// TODO Auto-generated method stub
		return 15;
	}
	
	/**
	 * returns the action that specifies what animation to play when the items is being used
	 */
	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BOW;
	}
	
	/*
	 * UseDuration
	 */
	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 50;
	}
	
	/**
	 * Gets the velocity of the arrow entity 
	 */
	public static float getArrowVelocity() {
		return 4.0f;
	}
	
	@Override
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

	@Override
	public Predicate<ItemStack> getInventoryAmmoPredicate() {
		Predicate<ItemStack> LASER = (stack) -> {
			return stack.getItem().isIn(ItemTags.getCollection().get(new ResourceLocation(Utils.MOD_ID, "laserstrahl_items")));
		};   
		return LASER;
	}
}
