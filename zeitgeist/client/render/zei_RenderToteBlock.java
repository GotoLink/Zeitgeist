package zeitgeist.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

public class zei_RenderToteBlock {
	static float f632 = 6f / 32f;
	static float f2064 = 20f / 64f;
	static float f2664 = 26f / 64f;
	static float f3664 = 36f / 64f;
	static float f4664 = 46f / 64f;
	static float f1032 = 10f / 32f;
	static float f5464 = 54f / 64f;

	// static float f1632=16f/32f;
	public static boolean render(RenderBlocks renderblocks,
			IBlockAccess blockAccess, Block block, int i, int j, int k) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(block.getMixedBrightnessForBlock(blockAccess,
				i, j, k));
		tessellator.setColorOpaque_F(1, 1, 1);
		tessellator.addVertexWithUV(i + 0.1875f, j + 0.5625f, k + 0.1875f,
				f3664, 0);
		tessellator.addVertexWithUV(i + 0.8125f, j + 0.5625f, k + 0.1875f,
				f4664, 0);
		tessellator.addVertexWithUV(i + 0.8125f, j + 0.0625f, k + 0.1875f,
				f4664, f632);
		tessellator.addVertexWithUV(i + 0.1875f, j + 0.0625f, k + 0.1875f,
				f3664, f632);
		tessellator.addVertexWithUV(i + 0.8125f, j + 0.5625f, k + 0.8125f,
				f3664, 0);
		tessellator.addVertexWithUV(i + 0.1875f, j + 0.5625f, k + 0.8125f,
				f2664, 0);
		tessellator.addVertexWithUV(i + 0.1875f, j + 0.0625f, k + 0.8125f,
				f2664, f632);
		tessellator.addVertexWithUV(i + 0.8125f, j + 0.0625f, k + 0.8125f,
				f3664, f632);
		tessellator.addVertexWithUV(i + 0.8125f, j + 0.5625f, k + 0.1875f, 1,
				f1032);
		tessellator.addVertexWithUV(i + 0.8125f, j + 0.5625f, k + 0.8125f,
				f5464, f1032);
		tessellator.addVertexWithUV(i + 0.8125f, j + 0.0625f, k + 0.8125f,
				f5464, 0.5f);
		tessellator.addVertexWithUV(i + 0.8125f, j + 0.0625f, k + 0.1875f, 1,
				0.5f);
		tessellator.addVertexWithUV(i + 0.1875f, j + 0.5625f, k + 0.8125f,
				f5464, f1032);
		tessellator.addVertexWithUV(i + 0.1875f, j + 0.5625f, k + 0.1875f, 1,
				f1032);
		tessellator.addVertexWithUV(i + 0.1875f, j + 0.0625f, k + 0.1875f, 1,
				0.5f);
		tessellator.addVertexWithUV(i + 0.1875f, j + 0.0625f, k + 0.8125f,
				f5464, 0.5f);
		tessellator.addVertexWithUV(i + 0.1875f, j + 0.5625f, k + 0.1875f,
				f2664, f632);
		tessellator.addVertexWithUV(i + 0.1875f, j + 0.5625f, k + 0.8125f,
				f2664, 0.5f);
		tessellator.addVertexWithUV(i + 0.8125f, j + 0.5625f, k + 0.8125f,
				f3664, 0.5f);
		tessellator.addVertexWithUV(i + 0.8125f, j + 0.5625f, k + 0.1875f,
				f3664, f632);
		return true;
	}
}
