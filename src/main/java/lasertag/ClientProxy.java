package lasertag;

import lasertag.client.entity.render.CustomRender;
import lasertag.entity.ModEntityType;
import lasertag.item.Phaser;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class ClientProxy {
	public ClientProxy() {
		Phaser.arrow = ModEntityType.LASERSTRAHL_ENTITY.get(); 
		RenderingRegistry.registerEntityRenderingHandler(Phaser.arrow, renderManager -> new CustomRender(renderManager));
	}
}
