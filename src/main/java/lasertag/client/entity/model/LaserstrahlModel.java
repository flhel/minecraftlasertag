package lasertag.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.projectile.AbstractArrowEntity;

//ka ob gebraucht
public class LaserstrahlModel<T extends AbstractArrowEntity>  extends EntityModel<T> {
	
	public LaserstrahlModel() {
		System.out.println("hey7");
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		// TODO Auto-generated method stub
		
	}


}