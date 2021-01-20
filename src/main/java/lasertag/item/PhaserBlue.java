package lasertag.item;

import java.util.function.Predicate;

import lasertag.Utils;
import lasertag.entity.LaserstrahlEntityBlue;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShootableItem;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class PhaserBlue extends ShootableItem{
	public static final RegistryObject<Item> LASERSTRAHL_ITEM = RegistryObject.of(new ResourceLocation(Utils.MOD_ID, "laserstrahl_item"), ForgeRegistries.ITEMS);
	public static EntityType<LaserstrahlEntityBlue> arrow = null;
	public boolean animationRegistered = false;
	public boolean startCharge = false;
	public short ticks = 0;
	
	public PhaserBlue() {
		super(new Properties().group(ItemGroup.COMBAT).maxStackSize(1));
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, LivingEntity entityLiving, int timeLeft) {
		startCharge = false;
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerentity = (PlayerEntity)entityLiving;
			ItemStack itemstack = playerentity.findAmmo(stack);
			
			if (entityLiving instanceof ServerPlayerEntity) {
				ServerPlayerEntity entity = (ServerPlayerEntity) entityLiving;

				if (!itemstack.isEmpty() || playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0) {
					if (!world.isRemote) {
						LaserstrahlEntityBlue entityarrow = new LaserstrahlEntityBlue(arrow, entity, world);
						PhaserHelper.shoot(world, entity, PhaserHelper.getPhaserVelocity(), PhaserHelper.getPhaserDmg(timeLeft), 0, entityarrow);	
					}
					PhaserHelper.playSound(world, entityLiving);
					PhaserHelper.damageItem(entity, itemstack);
					PhaserHelper.ammoLogic(playerentity, itemstack, stack);
				}
			}
		}
	}
	
	@Override
	public int func_230305_d_() {
		return PhaserHelper.func_230305_d_();
	}
	
	/**
	 * returns the action that specifies what animation to play when the items is being used
	 */
	@Override
	public UseAction getUseAction(ItemStack stack) {
		return PhaserHelper.getUseAction(stack);
	}
	
	/*
	 * UseDuration
	 */
	@Override
	public int getUseDuration(ItemStack itemstack) {
		return PhaserHelper.getUseDurationPhaser(itemstack);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity playerIn, Hand handIn) {
		if(world.isRemote) {
			if(!animationRegistered) {
				ModRegisterAnimation.RegisterAnimationPhaserBlue(this);
				animationRegistered = true;
			}
			ticks = 0;
			startCharge = true;
		}
		
		return PhaserHelper.onItemRightClick(world, playerIn, handIn);
	}
	
	/**
	 * Get the predicate to match ammunition when searching the player's inventory, not their main/offhand
	 * Find them in src\main\resources\data\lasertag\tags\items\laserstrahl_items:json
	 */
	public Predicate<ItemStack> getInventoryAmmoPredicate() {
		return PhaserHelper.getInventoryAmmoPredicate();
	}
}
