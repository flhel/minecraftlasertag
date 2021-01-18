package lasertag.item;

import java.util.function.Predicate;

import lasertag.Utils;
import lasertag.entity.LaserstrahlEntityBlue;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemModelsProperties;
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
	private boolean startcharge = false;
	private short ticks = 0;
	
	//RegistryObject.of(new ResourceLocation(Utils.MOD_ID, "phaser_blue"), ForgeRegistries.ITEMS).get()
	
	public PhaserBlue() {
		super(new Properties().group(ItemGroup.COMBAT).maxStackSize(1));	
		ItemModelsProperties.registerProperty(this, new ResourceLocation("lasertag:power"), new IItemPropertyGetter() {
			@Override
			public float call(ItemStack stack, ClientWorld world, LivingEntity entity) {
				if (startcharge && ticks < Short.MAX_VALUE) {
					ticks++;
				} else {
					ticks = 0;
				}
				return (float) ticks / 500;
			}
		});
	}
	

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, LivingEntity entityLiving, int timeLeft) {
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerentity = (PlayerEntity)entityLiving;
			ItemStack itemstack = playerentity.findAmmo(stack);
			
			if (entityLiving instanceof ServerPlayerEntity) {
				ServerPlayerEntity entity = (ServerPlayerEntity) entityLiving;

				if (!itemstack.isEmpty() || playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0) {
					if (!world.isRemote) {
						LaserstrahlEntityBlue entityarrow = new LaserstrahlEntityBlue(arrow, entity, world);
						PhaserHelper.shoot(world, entity, getArrowVelocity(), PhaserHelper.getPhaserDmg(timeLeft), 0, entityarrow);
					}
					PhaserHelper.playSound(world, entityLiving);
					PhaserHelper.damageItem(entity, itemstack);
					PhaserHelper.ammoLogic(playerentity, itemstack, stack);
					startcharge = false;
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

	/**
	 * Gets the velocity of the arrow entity 
	 */
	public static float getArrowVelocity() {
		return PhaserHelper.getArrowVelocity();
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ticks = 0;
		startcharge = true;
		return PhaserHelper.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	/**
	 * Get the predicate to match ammunition when searching the player's inventory, not their main/offhand
	 * Find them in src\main\resources\data\lasertag\tags\items\laserstrahl_items:json
	 */
	public Predicate<ItemStack> getInventoryAmmoPredicate() {
		return PhaserHelper.getInventoryAmmoPredicate();
	}
}
