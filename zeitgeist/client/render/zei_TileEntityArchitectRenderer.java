package zeitgeist.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import zeitgeist.client.models.zei_ModelEye;
import zeitgeist.common.tile.zei_TileEntityArchitect;

public class zei_TileEntityArchitectRenderer extends TileEntitySpecialRenderer {
	public static final ResourceLocation EYE = new ResourceLocation("zeitgeist", "Eye.png");
	private zei_ModelEye eyeModel = new zei_ModelEye();

	public void renderEyeAt(zei_TileEntityArchitect enti, double x, double y, double z, float par8) {
		bindTexture(EYE);
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);
		GL11.glTranslatef(0.5f, -1.5f, -0.5f);
		GL11.glColor4f(1, 1, 1, 1);
		eyeModel.ball.rotateAngleX = 1.571f - enti.Pitch;
		eyeModel.ball.rotateAngleY = 3.1416f - enti.Rotation;
		eyeModel.cornea.rotateAngleX = enti.rnx;
		eyeModel.cornea.rotateAngleY = enti.rny + (enti.Rotation - enti.prevRotation);
		eyeModel.lid1.rotateAngleY = -(enti.lid);
		eyeModel.lid2.rotateAngleY = -(enti.lid);
		eyeModel.renderAll();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glEnable(GL11.GL_CULL_FACE);
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		renderEyeAt((zei_TileEntityArchitect) par1TileEntity, par2, par4, par6, par8);
	}
}
