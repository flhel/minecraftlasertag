package lasertagItem.item;

import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemGroup;

public class Laserstrahl extends ArrowItem {
	public Laserstrahl () {
		super(new Properties().group(ItemGroup.COMBAT).defaultMaxDamage(3));
	}
}
