package lasertag.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShootableItem;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Predicate;

import lasertag.entity.AbstractLaserstrahlEntity;
import lasertag.entity.ModEntityType;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;

public class Phaser extends ShootableItem {
	public static final RegistryObject<Item> LASERSTRAHL_ITEM = RegistryObject.of(new ResourceLocation("lasertag:laserstrahl_item"), ForgeRegistries.ITEMS);

	public Phaser() {
		super(new Properties().group(ItemGroup.COMBAT));
	}

	public void shotCustom(ItemStack stack, World worldIn, PlayerEntity playerentity) {
		
		ModEntityType.LASERSTRAHL_ENTITY.get().create(worldIn);
		
		boolean flag = playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
		ItemStack itemstack = playerentity.findAmmo(stack); //findAmmo weil item stack empty

		net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, 10, !itemstack.isEmpty() || flag);

		if (!itemstack.isEmpty() || flag) {
			if (itemstack.isEmpty()) {
				itemstack = new ItemStack(LASERSTRAHL_ITEM.get());
			}

			float f = getArrowVelocity();
			boolean flag1 = playerentity.abilities.isCreativeMode || (itemstack.getItem() instanceof LaserstrahlItem && ((LaserstrahlItem)itemstack.getItem()).isInfinite(itemstack, stack, playerentity));
			if (!worldIn.isRemote || worldIn.isRemote) { //TODO
				LaserstrahlItem arrowitem = (LaserstrahlItem)(itemstack.getItem() instanceof LaserstrahlItem ? itemstack.getItem() : LASERSTRAHL_ITEM.get());
				AbstractLaserstrahlEntity abstractarrowentity = arrowitem.createArrow(worldIn, itemstack, playerentity);
				abstractarrowentity.func_234612_a_(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F, 1.0F);

				abstractarrowentity.setIsCritical(true);

				abstractarrowentity.setDamage(abstractarrowentity.getDamage());
				abstractarrowentity.setKnockbackStrength(1);
				abstractarrowentity.pickupStatus = AbstractLaserstrahlEntity.PickupStatus.DISALLOWED;

				stack.damageItem(1, playerentity, (p_220009_1_) -> {
					p_220009_1_.sendBreakAnimation(playerentity.getActiveHand());
				});
				worldIn.addEntity(abstractarrowentity);
			}
			//.ENTITY_ARROW_SHOOT
			worldIn.playSound((PlayerEntity)null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
			if (!flag1 && !playerentity.abilities.isCreativeMode) {
				itemstack.shrink(1);
				if (itemstack.isEmpty()) {
					playerentity.inventory.deleteStack(itemstack);
				}
			}

			playerentity.addStat(Stats.ITEM_USED.get(this));
		}
	}	         

	/**
	 * Gets the velocity of the arrow entity 
	 */
	public static float getArrowVelocity() {
		return 30.0f;
	}

	/**
	 * returns the action that specifies what animation to play when the items is being used
	 */

	public UseAction getUseAction(ItemStack stack) {
		return UseAction.NONE;
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
			shotCustom(itemstack, worldIn, playerIn);
			playerIn.setActiveHand(handIn);
			return ActionResult.resultConsume(itemstack);
		}
	}

	/**
	 * Get the predicate to match ammunition when searching the player's inventory, not their main/offhand
	 */

	public Predicate<ItemStack> getInventoryAmmoPredicate() {
		System.out.println("" + ItemTags.getCollection().get(new ResourceLocation("lasertag", "laserstrahl_entity")));
		Predicate<ItemStack> LASER = (stack) -> {
		      return stack.getItem().isIn(ItemTags.getCollection().get(new ResourceLocation("minecraft", "arrows")));
		   };   
		return LASER;
	}
	
	public int func_230305_d_() {
	      return 15;
	}
}

