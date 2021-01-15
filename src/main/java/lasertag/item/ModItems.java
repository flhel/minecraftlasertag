package lasertag.item;

import lasertag.Utils;
import lasertag.armor.ModArmorMaterial;
import lasertag.armor.ModArmorMaterial2;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Utils.MOD_ID);
    public static final RegistryObject<Phaser> PHASER = ITEMS.register("phaser", Phaser::new);
    public static final RegistryObject<LaserstrahlItem> LASERSTRAHL_ITEM = ITEMS.register("laserstrahl_item", LaserstrahlItem::new );
    public static final RegistryObject<ArmorItem> LASERTAGVESTONE = ITEMS.register("lasertagvestone",
			() -> new ArmorItem(ModArmorMaterial.VESTONE, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
	public static final RegistryObject<ArmorItem> LASERTAGVESTTWO = ITEMS.register("lasertagvesttwo",
			() -> new ArmorItem(ModArmorMaterial2.VESTTWO, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
	public static final RegistryObject<SwordItem> KNIVE = ITEMS.register("knive", () -> new SwordItem(Knive.KNIVE , 4, 1.0f, new Item.Properties().group(ItemGroup.COMBAT)));
}