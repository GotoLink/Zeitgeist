package zeitgeist.client.render;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class zei_RenderLimb extends Render {
	protected ModelBase mainModel;
	private Random random;
	public boolean field_27004_a;

	public zei_RenderLimb(ModelBase modelbase, float f) {
		mainModel = modelbase;
		shadowSize = f;
		random = new Random();
		field_27004_a = true;
		shadowSize = 0.75F;
	}

	public void doRenderItem(Entity entity, double d, double d1, double d2,
			float f, float f1) {
		random.setSeed(187L);
		GL11.glPushMatrix();
		// GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glTranslatef((float) d, (float) d1, (float) d2);
		// GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		// System.out.println(f);
		// GL11.glRotatef(180- f, 0, 1, 0);
		float f2 = 3.14f; // spin
		float f6 = 0.0625F;
		// loadTexture(entity.getTexture());
		// GL11.glScalef(20, 20,20);
		// GL11.glTranslatef(0.0F, -24F * f6 - 0.0078125F, 0.0F);
		// GL11.glEnable(GL11.GL_ALPHA_TEST);
		mainModel.render(entity, 0, 0, 0, 0, 0, f6);
		// GL11.glTranslatef((float)d, (float)d1 + f2, (float)d2);
		// GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		// GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();
	}

	public void doRender(Entity entity, double d, double d1, double d2,
			float f, float f1) {
		doRenderItem(entity, d, d1, d2, f, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
