package zeitgeist.client.render;

import java.awt.image.BufferedImage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import zeitgeist.client.zei_PictureData;
import zeitgeist.client.zei_Universal;
import zeitgeist.common.tile.zei_TileEntityTexBlock2;

public class zei_TileEntityTexBlockRenderer2 extends TileEntitySpecialRenderer {
	int imgID;

	public zei_TileEntityTexBlockRenderer2() {
		imgID = zei_Universal.mc.renderEngine
				.allocateAndSetupTexture(new BufferedImage(256, 256,
						BufferedImage.TYPE_INT_RGB));
	}

	public void renderMeAt(zei_TileEntityTexBlock2 enti, double x, double y,
			double z, float par8) {
		if (enti == null || enti.isUnused()) {
			return;
		}
		zei_PictureData data1 = null;
		zei_PictureData data2 = null;
		zei_PictureData data3 = null;
		zei_PictureData data4 = null;
		zei_PictureData data5 = null;
		zei_PictureData data6 = null;
		if (enti.getId1() != -1) {
			data1 = (zei_PictureData) zei_Universal.mc.theWorld.loadItemData(
					zei_PictureData.class, "texture_" + enti.getId1());
		}
		if (enti.getId2() != -1) {
			data2 = (zei_PictureData) zei_Universal.mc.theWorld.loadItemData(
					zei_PictureData.class, "texture_" + enti.getId2());
		}
		if (enti.getId3() != -1) {
			data3 = (zei_PictureData) zei_Universal.mc.theWorld.loadItemData(
					zei_PictureData.class, "texture_" + enti.getId3());
		}
		if (enti.getId4() != -1) {
			data4 = (zei_PictureData) zei_Universal.mc.theWorld.loadItemData(
					zei_PictureData.class, "texture_" + enti.getId4());
		}
		if (enti.getId5() != -1) {
			data5 = (zei_PictureData) zei_Universal.mc.theWorld.loadItemData(
					zei_PictureData.class, "texture_" + enti.getId5());
		}
		if (enti.getId6() != -1) {
			data6 = (zei_PictureData) zei_Universal.mc.theWorld.loadItemData(
					zei_PictureData.class, "texture_" + enti.getId6());
		}
		Tessellator tessellator = Tessellator.instance;
		// bindTextureByName("/terrain.png");
		RenderHelper.disableStandardItemLighting();
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_CULL_FACE);
		if (Minecraft.isAmbientOcclusionEnabled()) {
			GL11.glShadeModel(GL11.GL_SMOOTH);
		} else {
			GL11.glShadeModel(GL11.GL_FLAT);
		}
		int x1 = enti.xCoord;
		int x2 = x1 + 1;
		int y1 = enti.yCoord;
		int y2 = y1 + 1;
		int z1 = enti.zCoord;
		int z2 = z1 + 1;
		tessellator.setTranslation((float) x - (float) enti.xCoord, (float) y
				- (float) enti.yCoord, (float) z - (float) enti.zCoord);
		// tessellator.setColorOpaque(255, 255, 255);
		tessellator.setColorOpaque_F(1, 1, 1);
		tessellator.setBrightness(16);
		if (data1 != null) {
			data1.bindPictures(enti.getId1(), true);
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(x1, y1, z2, 0.0625f, 0.0625f);
			tessellator.addVertexWithUV(x2, y1, z2, 0.125f, 0.0625f);
			tessellator.addVertexWithUV(x2, y2, z2, 0.125f, 0);
			tessellator.addVertexWithUV(x1, y2, z2, 0.0625f, 0);
			tessellator.draw();
		}
		if (data2 != null) {
			data2.bindPictures(enti.getId2(), true);
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(x2, y1, z1, 0.0625f, 0.0625f);
			tessellator.addVertexWithUV(x2, y1, z2, 0.125f, 0.0625f);
			tessellator.addVertexWithUV(x2, y2, z2, 0.125f, 0);
			tessellator.addVertexWithUV(x2, y2, z1, 0.0625f, 0);
			tessellator.draw();
		}
		if (data3 != null) {
			data3.bindPictures(enti.getId3(), true);
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(x1, y1, z1, 0.0625f, 0.0625f);
			tessellator.addVertexWithUV(x2, y1, z1, 0.125f, 0.0625f);
			tessellator.addVertexWithUV(x2, y2, z1, 0.125f, 0);
			tessellator.addVertexWithUV(x1, y2, z1, 0.0625f, 0);
			tessellator.draw();
		}
		if (data4 != null) {
			data4.bindPictures(enti.getId4(), true);
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(x1, y1, z1, 0.0625f, 0.0625f);
			tessellator.addVertexWithUV(x1, y1, z2, 0.125f, 0.0625f);
			tessellator.addVertexWithUV(x1, y2, z2, 0.125f, 0);
			tessellator.addVertexWithUV(x1, y2, z1, 0.0625f, 0);
			tessellator.draw();
		}
		if (data5 != null) {
			data5.bindPictures(enti.getId5(), true);
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(x1, y1, z1, 0.0625f, 0.0625f);
			tessellator.addVertexWithUV(x1, y1, z2, 0.125f, 0.0625f);
			tessellator.addVertexWithUV(x2, y1, z2, 0.125f, 0);
			tessellator.addVertexWithUV(x2, y1, z1, 0.0625f, 0);
			tessellator.draw();
		}
		if (data6 != null) {
			data6.bindPictures(enti.getId6(), true);
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(x1, y2, z1, 0.0625f, 0.0625f);
			tessellator.addVertexWithUV(x1, y2, z2, 0.125f, 0.0625f);
			tessellator.addVertexWithUV(x2, y2, z2, 0.125f, 0);
			tessellator.addVertexWithUV(x2, y2, z1, 0.0625f, 0);
			tessellator.draw();
		}
		tessellator.setTranslation(0.0D, 0.0D, 0.0D);
		RenderHelper.enableStandardItemLighting();
	}

	public void pre(Tessellator tessellator) {
	}

	public void renderTileEntityAt(TileEntity par1TileEntity, double par2,
			double par4, double par6, float par8) {
		renderMeAt((zei_TileEntityTexBlock2) par1TileEntity, par2, par4, par6,
				par8);
	}
}
