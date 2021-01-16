package lasertag;

import lasertag.client.entity.render.LaserRenderBlue;
import lasertag.client.entity.render.LaserRenderRed;
import lasertag.item.PhaserBlue;
import lasertag.item.PhaserRed;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class ClientProxy {
	public ClientProxy() {
		RenderingRegistry.registerEntityRenderingHandler(PhaserRed.arrow, renderManager -> new LaserRenderRed(renderManager));
		RenderingRegistry.registerEntityRenderingHandler(PhaserBlue.arrow, renderManager -> new LaserRenderBlue(renderManager));
	}
}
