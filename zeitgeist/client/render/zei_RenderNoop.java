package zeitgeist.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import zeitgeist.client.models.zei_ModelNoop;

public class zei_RenderNoop extends RenderLiving {
	public zei_RenderNoop(float f) {
		super(new zei_ModelNoop(), f);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
