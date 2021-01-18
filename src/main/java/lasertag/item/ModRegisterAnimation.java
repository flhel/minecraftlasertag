package lasertag.item;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ModRegisterAnimation {

	public static void RegisterAnimationPhaserRed(PhaserRed phaser) {
		ItemModelsProperties.registerProperty(phaser, new ResourceLocation("lasertag:power"), new IItemPropertyGetter() {
			@Override
			public float call(ItemStack stack, ClientWorld world, LivingEntity entity) {
				if (PhaserRed.startCharge && PhaserRed.ticks < Short.MAX_VALUE) {
					PhaserRed.ticks++;
				} else {
					PhaserRed.ticks = 0;
				}
				return (float) PhaserRed.ticks / 500;
			}
		});
	}
	
	public static void RegisterAnimationPhaserBlue(PhaserBlue phaser) {
		ItemModelsProperties.registerProperty(phaser, new ResourceLocation("lasertag:power"), new IItemPropertyGetter() {
			@Override
			public float call(ItemStack stack, ClientWorld world, LivingEntity entity) {
				if (phaser.startCharge && phaser.ticks < Short.MAX_VALUE) {
					phaser.ticks++;
				} else {
					phaser.ticks = 0;
				}
				return (float) phaser.ticks / 500;
			}
		});
	}
}
