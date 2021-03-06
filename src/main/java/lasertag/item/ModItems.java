package lasertag.item;

import lasertag.Utils;
import lasertag.armor.CybertagArmor;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModItems {  
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Utils.MOD_ID);
	
	//Armor
	public static final RegistryObject<ArmorItem> LASERTAGVESTONE = ITEMS.register("lasertagvestone",
			() -> new ArmorItem(new CybertagArmor("lasertag:lasertagvestone", 33, new int[] {0, 0, 0, 0},  0,
					SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F, () -> {return Ingredient.fromItems(Items.IRON_INGOT);}),
					EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
	
	public static final RegistryObject<ArmorItem> LASERTAGVESTONE_HELMET = ITEMS.register("lasertagvestonehelmet",
			() -> new ArmorItem(new CybertagArmor("lasertag:lasertagvestone", 33, new int[] {3, 3, 3, 3},  0,
					SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F, () -> {return Ingredient.fromItems(Items.IRON_INGOT);}),
					EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
	
	public static final RegistryObject<ArmorItem> LASERTAGVESTONE_LEGS = ITEMS.register("lasertagvestonelegs",
			() -> new ArmorItem(new CybertagArmor("lasertag:lasertagvestone", 33, new int[] {2, 2, 2, 2},  0,
					SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F, () -> {return Ingredient.fromItems(Items.IRON_INGOT);}),
					EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
	
	public static final RegistryObject<ArmorItem> LASERTAGVESTONE_SHOES= ITEMS.register("lasertagvestoneshoes",
			() -> new ArmorItem(new CybertagArmor("lasertag:lasertagvestone", 33, new int[] {1, 1, 1, 1},  0,
					SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F, () -> {return Ingredient.fromItems(Items.IRON_INGOT);}),
					EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
	
	public static final RegistryObject<ArmorItem> LASERTAGVESTTWO = ITEMS.register("lasertagvesttwo",
			() -> new ArmorItem(new CybertagArmor("lasertag:lasertagvesttwo", 33, new int[] {0, 0, 0, 0},  0,
					SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F, () -> {return Ingredient.fromItems(Items.IRON_INGOT);}),
					EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
	
	//Guns 
	public static final RegistryObject<PhaserRed> PHASERRED = ITEMS.register("phaser_red", PhaserRed::new);
	public static final RegistryObject<PhaserBlue> PHASERBLUE = ITEMS.register("phaser_blue", PhaserBlue::new);
	public static final RegistryObject<MgRed> MGRED = ITEMS.register("mg_red", MgRed::new);
	public static final RegistryObject<MgBlue> MGBLUE = ITEMS.register("mg_blue", MgBlue::new);
    //Knives
	public static final RegistryObject<SwordItem> KNIVERED = ITEMS.register("knive_red", () -> new SwordItem(Knive.KNIVE, 4, 1.0f, new Item.Properties().group(ItemGroup.COMBAT)));
	public static final RegistryObject<SwordItem> KNIVEBLUE = ITEMS.register("knive_blue", () -> new SwordItem(Knive.KNIVE , 4, 1.0f, new Item.Properties().group(ItemGroup.COMBAT)));
	//Ammo
	public static final RegistryObject<LaserstrahlItem> LASERSTRAHL_ITEM = ITEMS.register("laserstrahl_item", LaserstrahlItem::new);
	//Potions
	public static final RegistryObject<Bluepill> BLUEPILL = ITEMS.register("bluepill", () -> new Bluepill(new Item.Properties().group(ItemGroup.BREWING)));
	//Grenades
	//public static final RegistryObject<Granate> GRANATE = ITEMS.register("granate", () -> new Granate(new Item.Properties().group(ItemGroup.COMBAT)));
}