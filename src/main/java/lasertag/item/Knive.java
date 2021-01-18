package lasertag.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import java.util.function.Supplier;

public enum Knive implements IItemTier {
	KNIVEENUM(1, 131, 4.0F, 3.0F, 5, () -> {return Ingredient.fromItems(Items.IRON_INGOT);});

	private final Supplier<Ingredient> repairmaterial;

	private final int enchantability;

	private final float attackDamage;

	private final float efficiency;

	private final int maxUses;

	private final int harvestLevel;

	Knive(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairmaterial) {
		this.harvestLevel = harvestLevel;
		this.maxUses = maxUses;
		this.efficiency = efficiency;
		this.attackDamage = attackDamage;
		this.enchantability = enchantability;
		this.repairmaterial = repairmaterial;
	}

	public int func_200926_a() {
		return this.maxUses;
	}

	public float func_200928_b() {
		return this.efficiency;
	}


	public float func_200929_c() {
		return this.attackDamage;
	}


	public int func_200925_d() {
		return this.harvestLevel;
	}


	public int func_200927_e() {
		return this.enchantability;
	}


	public Ingredient func_200924_f() {
		return this.repairmaterial.get();
	}

	@Override
	public int getMaxUses() {
		return this.maxUses;
	}

	@Override
	public float getEfficiency() {
		return this.efficiency;
	}

	@Override
	public float getAttackDamage() {
		return this.attackDamage;
	}

	@Override
	public int getHarvestLevel() {
		return this.harvestLevel;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return (Ingredient) this.repairmaterial;
	}
}