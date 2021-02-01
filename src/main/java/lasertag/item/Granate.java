package lasertag.item;

import lasertag.entity.GranateEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class Granate extends Item {
	
	 public Granate(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
	      ItemStack itemstack = playerIn.getHeldItem(handIn);
	      if (!worldIn.isRemote) {
	    	 GranateEntity granateentity = new GranateEntity(worldIn, playerIn);
	    	 granateentity.setItem(itemstack);
	    	 granateentity.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, -20.0F, 0.5F, 1.0F);
	         worldIn.addEntity(granateentity);
	      }

	      playerIn.addStat(Stats.ITEM_USED.get(this));
	      if (!playerIn.abilities.isCreativeMode) {
	         itemstack.shrink(1);
	      }

	      return ActionResult.func_233538_a_(itemstack, worldIn.isRemote());
	   }

}
