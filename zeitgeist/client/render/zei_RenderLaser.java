package zeitgeist.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class zei_RenderLaser extends Render {
	public void doRenderFireball(Entity entityfireball, double d, double d1,
			double d2, float f, float f1) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d, (float) d1, (float) d2);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		float f2 = 1.0F;
		GL11.glScalef(f2 / 1.0F, f2 / 1.0F, f2 / 1.0F);
		int i = 32;// Item.snowball.getIconFromDamage(0);
		// renderManager.renderEngine.loadTexture("/zeitgeist/boot.png");
		Tessellator tessellator = Tessellator.instance;
		float f3 = 0F;// (float)((i % 8) * 8 + 0) / 256F;
		float f4 = 1F;// (float)((i % 8) * 8 + 8) / 256F;
		float f5 = 0F;// (float)((i / 8) * 8 + 0) / 256F;
		float f6 = 1F;// (float)((i / 8) * 8 + 8) / 256F;
		float f7 = 1.0F;
		float f8 = 0.5F;
		float f9 = 0.25F;
		GL11.glRotatef(180F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		tessellator.addVertexWithUV(0.0F - f8, 0.0F - f9, 0.0D, f3, f6);
		tessellator.addVertexWithUV(f7 - f8, 0.0F - f9, 0.0D, f4, f6);
		tessellator.addVertexWithUV(f7 - f8, 1.0F - f9, 0.0D, f4, f5);
		tessellator.addVertexWithUV(0.0F - f8, 1.0F - f9, 0.0D, f3, f5);
		tessellator.draw();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	public void doRender(Entity entity, double d, double d1, double d2,
			float f, float f1) {
		doRenderFireball(entity, d, d1, d2, f, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		// TODO Auto-generated method stub
		return null;// "/zeitgeist/boot.png"
	}
}
