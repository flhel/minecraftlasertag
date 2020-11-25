package lasertag.item;
import lasertag.entity.LaserstrahlEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LaserstrahlItem extends ArrowItem {
	public LaserstrahlItem () {
		super(new Properties().group(ItemGroup.COMBAT).maxStackSize(30));
	}
	
	@Override
	public LaserstrahlEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
		return new LaserstrahlEntity(worldIn, shooter);
	}

	@Override
	public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.PlayerEntity player) {
		return false;
	}
}
