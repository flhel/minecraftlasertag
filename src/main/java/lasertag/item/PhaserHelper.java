package lasertag.item;

import java.util.function.Predicate;

import lasertag.Utils;
import lasertag.sounds.ModSounds;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

/*
 * Zentralisiert alle Methoden für Phaser/Mg 
 */
public class PhaserHelper {	
	public static ItemStack onPlayerStoppedUsingInit(ItemStack stack, World world, LivingEntity entityLiving, int timeLeft) {
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerentity = (PlayerEntity)entityLiving;
			ItemStack itemstack = playerentity.findAmmo(stack);
			
			if (entityLiving instanceof ServerPlayerEntity) {
				if (!itemstack.isEmpty() || playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0) {
					if (!world.isRemote) {
						return itemstack;
					}
				}
			}
		}
		return null;
	}
	
	public static void shoot(World world, LivingEntity entity, float power, double damage, int knockback, AbstractArrowEntity entityarrow) {
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
	
	/* Paser Schadenskalukulation
	 * RealSchaden getPhaserDmgx 1,5 , getMgDmg x 1,5
	 * Mgs haben fixe Werte
	 */
	public static double getPhaserDmg(int timeLeft) {
		double dmg = (65 - timeLeft) * 0.1; // timeLeft abhängig von getUseDuration
		if (dmg < 2) { 
			dmg = 2;
		}
		if (dmg > 4.5) { 
			dmg = 4.5;
		}
		return dmg;
	}
	
	public static double getMgDmg() {
		return 1.5f;
	}

	public static int func_230305_d_() {
		return 15;
	}

	/**
	 * returns the action that specifies what animation to play when the items is being used
	 */
	public static UseAction getUseAction(ItemStack stack) {
		return UseAction.BOW;
	}

	/*
	 * UseDuration
	 */
	public static int getUseDurationPhaser(ItemStack itemstack) {
		return 50;
	}
	
	public static int getUseDurationMg(ItemStack itemstack) {
		return 1000000;
	}

	/**
	 * Gets the velocity of the arrow entity 
	 */
	public static float getPhaserVelocity() {
		return 5.0f;
	}
	
	public static float getMgVelocity() {
		return 4.2f;
	}

	public static ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);

		if(handIn != Hand.MAIN_HAND) {
			return ActionResult.resultFail(itemstack);
		}

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

	public static Predicate<ItemStack> getInventoryAmmoPredicate() {
		Predicate<ItemStack> LASER = (stack) -> {
			return stack.getItem().isIn(ItemTags.getCollection().get(new ResourceLocation(Utils.MOD_ID, "laserstrahl_items")));
		};   
		return LASER;
	}

	public static void playSound(World world, LivingEntity entityLiving) {
		world.playSound((PlayerEntity) null, entityLiving.getPosX(), entityLiving.getPosY(), entityLiving.getPosZ(), 
				ModSounds.PHASER_SOUND.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);	
	}

	public static void damageItem(ServerPlayerEntity entity, ItemStack itemstack) {
		itemstack.damageItem(1, entity, e -> e.sendBreakAnimation(entity.getActiveHand()));
	}

	public static void ammoLogic(PlayerEntity playerentity, ItemStack itemstack, ItemStack stack) {
		if (!(playerentity.abilities.isCreativeMode || (itemstack.getItem() instanceof LaserstrahlItem && ((LaserstrahlItem)itemstack.getItem()).isInfinite(itemstack, stack, playerentity)))) {
			itemstack.shrink(1);
			if (itemstack.isEmpty()) {
				playerentity.inventory.deleteStack(itemstack);
			}
		}	
	}
}
