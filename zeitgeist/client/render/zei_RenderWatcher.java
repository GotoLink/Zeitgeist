package zeitgeist.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class zei_RenderWatcher extends RenderLiving {
	public zei_RenderWatcher(ModelBase modelbase, float f) {
		super(modelbase, f);
	}

	protected void preRenderCallback(EntityLiving entityliving, float f) {
		GL11.glScalef(1.2f, 1.2f, 1.2f);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
