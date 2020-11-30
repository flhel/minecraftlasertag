package lasertag.item;
import lasertag.entity.LaserstrahlEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LaserstrahlItem extends ArrowItem {
	public LaserstrahlItem () {
		super(new Properties().group(ItemGroup.COMBAT).maxStackSize(30));
	}
	
	@Override
	public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
		//LaserstrahlEntity e = new LaserstrahlEntity(worldIn, shooter);
		//return e;
		
		return null;
	}
}
