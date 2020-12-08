package lasertag.armor;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.item.Items;

import java.util.function.Supplier;

public enum ModArmorMaterial implements IArmorMaterial {
	
	VESTONE ("lasertag:lasertagvestone", 33, new int[] {0, 0, 0, 0},  0,
			SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F, () -> {return Ingredient.fromItems(Items.IRON_INGOT);});
	
	//private static final int[] MAX_DAMAGE_ARRAY = new int[] {0, 0, 0, 0};
	private final String name;
	//private final int maxDamageFactor;
	//private final int[] damageReductionAmountArray;
	private final int enchantability;
	private final SoundEvent soundEvent;
	private final float toughness;
	private final Supplier<Ingredient> repairMaterial;
	
	ModArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, Supplier<Ingredient> repairMaterial) {
		this.name = name;
		//this.maxDamageFactor = maxDamageFactor;
		//this.damageReductionAmountArray = damageReductionAmountArray;
		this.enchantability = enchantability;
		this.soundEvent = soundEvent;
		this.toughness = toughness;
		this.repairMaterial = repairMaterial;
	}
	
	@Override
	public int getDurability(EquipmentSlotType slotIn) {
		// TODO Auto-generated method stub
		return 7200;
	}

	@Override
	public int getDamageReductionAmount(EquipmentSlotType slotIn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEnchantability() {
		// TODO Auto-generated method stub
		return this.enchantability;
	}

	@Override
	public SoundEvent getSoundEvent() {
		// TODO Auto-generated method stub
		return this.soundEvent;
	}

	@Override
	public Ingredient getRepairMaterial() {
		// TODO Auto-generated method stub
		return this.repairMaterial.get();
	}
	@OnlyIn(Dist.CLIENT)
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public float getToughness() {
		// TODO Auto-generated method stub
		return this.toughness;
	}
	

	@Override
	public float getKnockbackResistance() {
		// TODO Auto-generated method stub
		return 0;
	}

}
