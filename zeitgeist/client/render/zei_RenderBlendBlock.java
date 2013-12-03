package zeitgeist.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

public class zei_RenderBlendBlock {
	public static boolean render(RenderBlocks renderblocks,
			IBlockAccess blockAccess, Block block, int i, int j, int k) {
		Block block1 = Block.blocksList[blockAccess.getBlockId(i - 1, j, k)];
		Block block2 = Block.blocksList[blockAccess.getBlockId(i + 1, j, k)];
		Block block3 = Block.blocksList[blockAccess.getBlockId(i, j, k - 1)];
		Block block4 = Block.blocksList[blockAccess.getBlockId(i, j, k + 1)];
		Block block5 = Block.blocksList[blockAccess.getBlockId(i, j - 1, k)];
		Block block6 = Block.blocksList[blockAccess.getBlockId(i, j + 1, k)];
		boolean first = true;
		Tessellator.instance.draw();
		Tessellator.instance.startDrawingQuads();
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(block.getMixedBrightnessForBlock(blockAccess,
				i, j, k));
		tessellator.setColorOpaque_F(1, 1, 1);
		tessellator.setColorRGBA_F(1, 1, 1, 1f);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_DST_ALPHA, GL11.GL_ONE_MINUS_DST_ALPHA);
		float divisors = 0f;
		if (block1 != null) {
			divisors++;
		}
		if (block2 != null) {
			divisors++;
		}
		if (block3 != null) {
			divisors++;
		}
		if (block4 != null) {
			divisors++;
		}
		if (block5 != null) {
			divisors++;
		}
		if (block6 != null) {
			divisors++;
		}
		float shade = 1f / divisors;
		if (block1 != null) {
			applyTexture(block1.getBlockTextureFromSide(0), i, j, k, 0f,
					tessellator);
			if (first) {
				first = false;
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				tessellator.setColorRGBA_F(1, 1, 1, shade);
			}
		}
		if (block2 != null) {
			applyTexture(block2.getBlockTextureFromSide(0), i, j, k, 0f,
					tessellator);
			if (first) {
				first = false;
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				tessellator.setColorRGBA_F(1, 1, 1, shade);
			}
		}
		if (block3 != null) {
			applyTexture(block3.getBlockTextureFromSide(0), i, j, k, 0f,
					tessellator);
			if (first) {
				first = false;
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				tessellator.setColorRGBA_F(1, 1, 1, shade);
			}
		}
		if (block4 != null) {
			applyTexture(block4.getBlockTextureFromSide(0), i, j, k, 0f,
					tessellator);
			if (first) {
				first = false;
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				tessellator.setColorRGBA_F(1, 1, 1, shade);
			}
		}
		if (block5 != null) {
			applyTexture(block5.getBlockTextureFromSide(0), i, j, k, 0f,
					tessellator);
			if (first) {
				first = false;
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				tessellator.setColorRGBA_F(1, 1, 1, shade);
			}
		}
		if (block6 != null) {
			applyTexture(block6.getBlockTextureFromSide(0), i, j, k, 0f,
					tessellator);
			if (first) {
				first = false;
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				tessellator.setColorRGBA_F(1, 1, 1, shade);
			}
		}
		Tessellator.instance.draw();
		Tessellator.instance.startDrawingQuads();
		GL11.glDisable(GL11.GL_BLEND);
		return true;
	}

	static void applyTexture(Icon icon, int i, int j, int k, float offset,
			Tessellator tessellator) {
		// tessellator.setBrightness(block2.getMixedBrightnessForBlock(blockAccess,
		// i, j, k));
		// tessellator.setColorRGBA_F(.6f, 1, 1, 0.2f);
		double d = icon.getMinU();
		double d2 = icon.getMaxU();
		double d3 = icon.getMinV();
		double d4 = icon.getMaxV();
		tessellator.addVertexWithUV(i, j, k + 1, d, d4);
		tessellator.addVertexWithUV(i + 1, j, k + 1, d2, d4);
		tessellator.addVertexWithUV(i + 1, j + 1, k + 1, d2, d3);
		tessellator.addVertexWithUV(i, j + 1, k + 1, d, d3);
		tessellator.addVertexWithUV(i + 1, j, k, d, d4);
		tessellator.addVertexWithUV(i, j, k, d2, d4);
		tessellator.addVertexWithUV(i, j + 1, k, d2, d3);
		tessellator.addVertexWithUV(i + 1, j + 1, k, d, d3);
	}
}
