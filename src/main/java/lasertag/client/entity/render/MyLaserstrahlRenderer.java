package lasertag.client.entity.render;

import lasertag.entity.LaserstrahlEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class MyLaserstrahlRenderer extends ArrowRenderer<LaserstrahlEntity> {
	public static final ResourceLocation RES_LASERSTRAHL_ENTITY = new ResourceLocation("textures/entity/laserstrahl_entity.png");
	
	public MyLaserstrahlRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
		System.out.println("hey1");
	}
	
	/**
	 * Returns the location of an entity's texture.
	 */
	public ResourceLocation getEntityTexture(LaserstrahlEntity entity) {
		System.out.println("hey " + RES_LASERSTRAHL_ENTITY);
		return RES_LASERSTRAHL_ENTITY;
	}
}