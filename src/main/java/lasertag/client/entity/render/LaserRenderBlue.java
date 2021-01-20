package lasertag.client.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import lasertag.Utils;
import lasertag.entity.LaserstrahlEntityBlue;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LaserRenderBlue extends EntityRenderer<LaserstrahlEntityBlue> {
	private static final ResourceLocation texture = new ResourceLocation(Utils.MOD_ID, "textures/entity/laserstrahl_entity_blue.png");
	public LaserRenderBlue(EntityRendererManager renderManager) {
		super(renderManager);
	}

	@Override
	public void render(LaserstrahlEntityBlue entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn,
			int packedLightIn) {
		LaserRenderHelper.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn, this.getEntityTexture(entityIn));
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getEntityTexture(LaserstrahlEntityBlue entity) {
		return texture;
	}
}
