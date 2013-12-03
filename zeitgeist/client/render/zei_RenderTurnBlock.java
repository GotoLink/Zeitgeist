package zeitgeist.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

public class zei_RenderTurnBlock {
	static float ff = 0.0625f;
	static float f2f = 2 * ff;
	static float f8f = 8 * ff;
	static float f14f = 14 * ff;
	static float f4f = 4 * ff;
	static float f6f = 6 * ff;
	static float f12f = 12 * ff;
	static float f10f = 10 * ff;
	static float f9f = 9 * ff;
	static float f3f = 3 * ff;
	static float f13f = 13 * ff;
	static float ff5s = 5.5f * ff;
	static float ff5e = 10.5f * ff;
	static float ff5su = ff5s * ff;
	static float ff5eu = ff5e * ff;
	static float f5f = 5 * ff;
	static float f332 = 3f / 32f;
	static float f1132 = 11f / 32f;
	static float f1932 = 19f / 32f;
	static float f4564 = 45f / 64f;
	static float f5564 = (55f / 64f);
	static float f5864 = (58f / 64f);
	static float f5964 = (59f / 64f);
	static float f4964 = (49f / 64f);
	static float f2332 = (23f / 32f);
	static float f2632 = (26f / 32f);
	static float f1464 = 14f / 64f;
	static float f1964 = 19f / 64f;
	static float f2732 = 27f / 32f;
	static float f564 = 5 / 64f;
	static float f764 = 7f / 64f;
	static float f2664 = 26f / 64f;
	static float f3164 = 31f / 64f;
	static float f2032 = 20f / 32f;
	static float ang = 0;

	public static boolean render(RenderBlocks renderblocks,
			IBlockAccess blockAccess, Block block, int i, int j, int k) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(block.getMixedBrightnessForBlock(blockAccess,
				i, j, k));
		tessellator.setColorOpaque_F(1, 1, 1);
		ang += 0.1f;
		// top bod
		tessellator.addVertexWithUV(i + f2f, j + f8f, k + f6f, f4964, f2332);
		tessellator.addVertexWithUV(i + f2f, j + f8f, k + f9f, f4964, f2632);
		tessellator.addVertexWithUV(i + f14f, j + f8f, k + f9f, f5964, f2632);
		tessellator.addVertexWithUV(i + f14f, j + f8f, k + f6f, f5964, f2332);
		tessellator.addVertexWithUV(i + ff5e, j + f13f, k + f13f, 0, f2732);
		tessellator.addVertexWithUV(i + ff5s, j + f13f, k + f13f, f564, f2732);
		tessellator.addVertexWithUV(i + ff5s, j + f8f, k + f13f, f564, 1);
		tessellator.addVertexWithUV(i + ff5e, j + f8f, k + f13f, 0, 1);
		tessellator.addVertexWithUV(i + ff5s, j + f13f, k + f6f, f3164, f2732);
		tessellator.addVertexWithUV(i + ff5e, j + f13f, k + f6f, f2664, f2732);
		tessellator.addVertexWithUV(i + ff5e, j + f8f, k + f6f, f2664, 1);
		tessellator.addVertexWithUV(i + ff5s, j + f8f, k + f6f, f3164, 1);
		tessellator.addVertexWithUV(i + ff5s, j + f13f, k + f13f, f1464, f2732);
		tessellator.addVertexWithUV(i + ff5s, j + f13f, k + f6f, f764, f2732);
		tessellator.addVertexWithUV(i + ff5s, j + f8f, k + f6f, f764, 1);
		tessellator.addVertexWithUV(i + ff5s, j + f8f, k + f13f, f1464, 1);
		tessellator.addVertexWithUV(i + ff5e, j + f13f, k + f6f, f764, f2732);
		tessellator.addVertexWithUV(i + ff5e, j + f13f, k + f13f, f1464, f2732);
		tessellator.addVertexWithUV(i + ff5e, j + f8f, k + f13f, f1464, 1);
		tessellator.addVertexWithUV(i + ff5e, j + f8f, k + f6f, f764, 1);
		// top head
		tessellator.addVertexWithUV(i + ff5s, j + f13f, k + f13f, f1464, f2032);
		tessellator.addVertexWithUV(i + ff5e, j + f13f, k + f13f, f1464, f2732);
		tessellator.addVertexWithUV(i + ff5e, j + f13f, k + f6f, f1964, f2732);
		tessellator.addVertexWithUV(i + ff5s, j + f13f, k + f6f, f1964, f2032);
		return true;
	}
}
