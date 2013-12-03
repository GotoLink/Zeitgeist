package zeitgeist.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import zeitgeist.client.zei_PictureData;
import zeitgeist.common.tile.zei_TileEntityTexBlock;

public class zei_TileEntityTexBlockRenderer extends TileEntitySpecialRenderer {
	// int imgID;
	public zei_TileEntityTexBlockRenderer() {
		// imgID = zei_Universal.mc.renderEngine.allocateAndSetupTexture(new
		// BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB));
		// blockRenderer = new RenderBlocks(zei_Universal.mc.theWorld);
	}

	public void renderMeAt(zei_TileEntityTexBlock enti, double x, double y,
			double z, float par8) {
		// enti.blockType.
		// System.out.println("testin");
		if (enti == null || enti.indo == -1) {
			return;
		}
		// System.out.println("made it");
		zei_PictureData data = (zei_PictureData) enti.worldObj.loadItemData(
				zei_PictureData.class, "texture_" + enti.indo);
		if (data == null) {
			return;
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
		double x1 = enti.xCoord;
		double x2 = x1 + 1;
		double y1 = enti.yCoord;
		double y2 = y1 + 1;
		double z1 = enti.zCoord;
		double z2 = z1 + 1;
		double ff = 0.063;
		tessellator.setTranslation((float) x - (float) enti.xCoord, (float) y
				- (float) enti.yCoord, (float) z - (float) enti.zCoord);
		tessellator.setColorOpaque(1, 1, 1);
		// tessellator.setColorOpaque_F(1,1,1);
		int m = enti.meto;
		if (m == -1) {
			m = enti.findMeta();
		}
		tessellator.startDrawingQuads();
		data.bindPictures(enti.indo, true);
		if (m == 0) {
			tessellator.addVertexWithUV(x1, y2 - ff, z1, 0.0625f, 0.0625f);
			tessellator.addVertexWithUV(x1, y2 - ff, z2, 0.125f, 0.0625f);
			tessellator.addVertexWithUV(x2, y2 - ff, z2, 0.125f, 0);
			tessellator.addVertexWithUV(x2, y2 - ff, z1, 0.0625f, 0);
		} else if (m == 1) {
			tessellator.addVertexWithUV(x1, y1 + ff, z1, 0.0625f, 0.0625f);
			tessellator.addVertexWithUV(x1, y1 + ff, z2, 0.125f, 0.0625f);
			tessellator.addVertexWithUV(x2, y1 + ff, z2, 0.125f, 0);
			tessellator.addVertexWithUV(x2, y1 + ff, z1, 0.0625f, 0);
		} else if (m == 2) {
			tessellator.addVertexWithUV(x1, y1, z2 - ff, 0.125f, 0.0625f);
			tessellator.addVertexWithUV(x2, y1, z2 - ff, 0.0625f, 0.0625f);
			tessellator.addVertexWithUV(x2, y2, z2 - ff, 0.0625f, 0);
			tessellator.addVertexWithUV(x1, y2, z2 - ff, 0.125f, 0);
		} else if (m == 3) {
			tessellator.addVertexWithUV(x1, y1, z1 + ff, 0.0625f, 0.0625f);
			tessellator.addVertexWithUV(x2, y1, z1 + ff, 0.125f, 0.0625f);
			tessellator.addVertexWithUV(x2, y2, z1 + ff, 0.125f, 0);
			tessellator.addVertexWithUV(x1, y2, z1 + ff, 0.0625f, 0);
		} else if (m == 4) {
			tessellator.addVertexWithUV(x2 - ff, y1, z1, 0.0625f, 0.0625f);
			tessellator.addVertexWithUV(x2 - ff, y1, z2, 0.125f, 0.0625f);
			tessellator.addVertexWithUV(x2 - ff, y2, z2, 0.125f, 0);
			tessellator.addVertexWithUV(x2 - ff, y2, z1, 0.0625f, 0);
		} else if (m == 5) {
			tessellator.addVertexWithUV(x1 + ff, y1, z1, 0.125f, 0.0625f);
			tessellator.addVertexWithUV(x1 + ff, y1, z2, 0.0625f, 0.0625f);
			tessellator.addVertexWithUV(x1 + ff, y2, z2, 0.0625f, 0);
			tessellator.addVertexWithUV(x1 + ff, y2, z1, 0.125f, 0);
		}
		tessellator.draw();
		tessellator.setTranslation(0.0D, 0.0D, 0.0D);
		RenderHelper.enableStandardItemLighting();
	}

	public void renderTileEntityAt(TileEntity par1TileEntity, double par2,
			double par4, double par6, float par8) {
		renderMeAt((zei_TileEntityTexBlock) par1TileEntity, par2, par4, par6,
				par8);
	}
}
