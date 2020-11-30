package lasertag.item;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemGroup;

public class LaserstrahlItem extends ArrowItem {
	public LaserstrahlItem () {
		super(new Properties().group(ItemGroup.COMBAT).maxStackSize(30));
	}
}
