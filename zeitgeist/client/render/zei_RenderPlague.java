package zeitgeist.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

public class zei_RenderPlague {
	static int yo = 6;
	static int xo = 0;
	static float ff = 8f;
	static double d = (float) xo / ff;
	static double d2 = ((float) xo + 0.99F) / ff;
	static double d4 = (float) yo / ff;
	static double d6 = ((float) yo + 0.99F) / ff;
	static double ds2 = (float) 1 / ff;
	static double de2 = ((float) 1 + 0.99F) / ff;
	static double ds3 = (float) 2 / ff;
	static double de3 = ((float) 2 + 0.99F) / ff;
	static double ds4 = (float) 3 / ff;
	static double de4 = ((float) 3 + 0.99F) / ff;
	static double ds5 = (float) 4 / ff;
	static double de5 = ((float) 4 + 0.99F) / ff;
	static double ds6 = (float) 5 / ff;
	static double de6 = ((float) 5 + 0.99F) / ff;

	public static boolean render(RenderBlocks renderblocks,
			IBlockAccess blockAccess, Block block, int i, int j, int k) {
		Tessellator tessellator = Tessellator.instance;
		// int l = blockAccess.getBlockMetadata(i, j, k);
		tessellator.setBrightness(block.getMixedBrightnessForBlock(blockAccess,
				i, j, k));
		tessellator.setColorOpaque_F(1, 1, 1);
		// GL11.glEnable (GL11.GL_BLEND);
		// GL11.glBlendFunc (GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_DST_ALPHA);
		// tessellator.setColorRGBA_F(1, 1, 1, 0.2f);
		float xs = i + 0;
		float xe = i + 1;
		float zs = k + 0;
		float ze = k + 1;
		float ys = j + 0;
		float ye = j + 1;
		float v = 0.015625f;
		boolean one = false;
		boolean below = blockAccess.getBlockId(i, j - 1, k) == block.blockID;
		double ds = 0;
		double de = 0;
		if (below) {
			ds = ds6;
			de = de6;
		} else {
			ds = ds5;
			de = de5;
		}
		if (blockAccess.isBlockOpaqueCube(i - 1, j, k)) {
			tessellator.addVertexWithUV(xs + v, ys, ze, d, d6);
			tessellator.addVertexWithUV(xs + v, ys, zs, d2, d6);
			tessellator.addVertexWithUV(xs + v, ye, zs, d2, d4);
			tessellator.addVertexWithUV(xs + v, ye, ze, d, d4);
			tessellator.addVertexWithUV(xs, ys, zs + .5f, ds, d6);
			tessellator.addVertexWithUV(xe, ys, zs + .5f, de, d6);
			tessellator.addVertexWithUV(xe, ye, zs + .5f, de, d4);
			tessellator.addVertexWithUV(xs, ye, zs + .5f, ds, d4);
			tessellator.addVertexWithUV(xe, ys, zs + .5f, de, d6);
			tessellator.addVertexWithUV(xs, ys, zs + .5f, ds, d6);
			tessellator.addVertexWithUV(xs, ye, zs + .5f, ds, d4);
			tessellator.addVertexWithUV(xe, ye, zs + .5f, de, d4);
			one = true;
		}
		if (blockAccess.isBlockOpaqueCube(i + 1, j, k)) {
			tessellator.addVertexWithUV(xe - v, ys, zs, d2, d6);
			tessellator.addVertexWithUV(xe - v, ys, ze, d, d6);
			tessellator.addVertexWithUV(xe - v, ye, ze, d, d4);
			tessellator.addVertexWithUV(xe - v, ye, zs, d2, d4);
			tessellator.addVertexWithUV(xs, ys, zs + .5f, de, d6);
			tessellator.addVertexWithUV(xe, ys, zs + .5f, ds, d6);
			tessellator.addVertexWithUV(xe, ye, zs + .5f, ds, d4);
			tessellator.addVertexWithUV(xs, ye, zs + .5f, de, d4);
			tessellator.addVertexWithUV(xe, ys, zs + .5f, ds, d6);
			tessellator.addVertexWithUV(xs, ys, zs + .5f, de, d6);
			tessellator.addVertexWithUV(xs, ye, zs + .5f, de, d4);
			tessellator.addVertexWithUV(xe, ye, zs + .5f, ds, d4);
			one = true;
		}
		if (blockAccess.isBlockOpaqueCube(i, j, k + 1)) {
			tessellator.addVertexWithUV(xe, ys, ze - v, d2, d6);
			tessellator.addVertexWithUV(xs, ys, ze - v, d, d6);
			tessellator.addVertexWithUV(xs, ye, ze - v, d, d4);
			tessellator.addVertexWithUV(xe, ye, ze - v, d2, d4);
			tessellator.addVertexWithUV(xe - .5f, ys, zs, de, d6);
			tessellator.addVertexWithUV(xe - .5f, ys, ze, ds, d6);
			tessellator.addVertexWithUV(xe - .5f, ye, ze, ds, d4);
			tessellator.addVertexWithUV(xe - .5f, ye, zs, de, d4);
			tessellator.addVertexWithUV(xe - .5f, ys, ze, ds, d6);
			tessellator.addVertexWithUV(xe - .5f, ys, zs, de, d6);
			tessellator.addVertexWithUV(xe - .5f, ye, zs, de, d4);
			tessellator.addVertexWithUV(xe - .5f, ye, ze, ds, d4);
			one = true;
		}
		if (blockAccess.isBlockOpaqueCube(i, j, k - 1)) {
			tessellator.addVertexWithUV(xs, ys, zs + v, d, d6);
			tessellator.addVertexWithUV(xe, ys, zs + v, d2, d6);
			tessellator.addVertexWithUV(xe, ye, zs + v, d2, d4);
			tessellator.addVertexWithUV(xs, ye, zs + v, d, d4);
			tessellator.addVertexWithUV(xe - .5f, ys, zs, ds, d6);
			tessellator.addVertexWithUV(xe - .5f, ys, ze, de, d6);
			tessellator.addVertexWithUV(xe - .5f, ye, ze, de, d4);
			tessellator.addVertexWithUV(xe - .5f, ye, zs, ds, d4);
			tessellator.addVertexWithUV(xe - .5f, ys, ze, de, d6);
			tessellator.addVertexWithUV(xe - .5f, ys, zs, ds, d6);
			tessellator.addVertexWithUV(xe - .5f, ye, zs, ds, d4);
			tessellator.addVertexWithUV(xe - .5f, ye, ze, de, d4);
			one = true;
		}
		if (blockAccess.isBlockOpaqueCube(i, j - 1, k)) {
			tessellator.addVertexWithUV(xe, ys + v, ze, d, d6);
			tessellator.addVertexWithUV(xe, ys + v, zs, d2, d6);
			tessellator.addVertexWithUV(xs, ys + v, zs, d2, d4);
			tessellator.addVertexWithUV(xs, ys + v, ze, d, d4);
			if (blockAccess.getBlockId(i, j + 1, k) == block.blockID) {
				tessellator.addVertexWithUV(xs, ys, zs, ds4, d6);
				tessellator.addVertexWithUV(xs, ye, zs, ds4, d4);
				tessellator.addVertexWithUV(xe, ye, ze, de4, d4);
				tessellator.addVertexWithUV(xe, ys, ze, de4, d6);
				tessellator.addVertexWithUV(xs, ye, zs, ds4, d4);
				tessellator.addVertexWithUV(xs, ys, zs, ds4, d6);
				tessellator.addVertexWithUV(xe, ys, ze, de4, d6);
				tessellator.addVertexWithUV(xe, ye, ze, de4, d4);
				tessellator.addVertexWithUV(xe, ye, zs, ds4, d4);
				tessellator.addVertexWithUV(xe, ys, zs, ds4, d6);
				tessellator.addVertexWithUV(xs, ys, ze, de4, d6);
				tessellator.addVertexWithUV(xs, ye, ze, de4, d4);
				tessellator.addVertexWithUV(xe, ys, zs, ds4, d6);
				tessellator.addVertexWithUV(xe, ye, zs, ds4, d4);
				tessellator.addVertexWithUV(xs, ye, ze, de4, d4);
				tessellator.addVertexWithUV(xs, ys, ze, de4, d6);
			} else if (!one) {
				tessellator.addVertexWithUV(xs, ys, zs, ds2, d6);
				tessellator.addVertexWithUV(xs, ye, zs, ds2, d4);
				tessellator.addVertexWithUV(xe, ye, ze, de2, d4);
				tessellator.addVertexWithUV(xe, ys, ze, de2, d6);
				tessellator.addVertexWithUV(xs, ye, zs, ds2, d4);
				tessellator.addVertexWithUV(xs, ys, zs, ds2, d6);
				tessellator.addVertexWithUV(xe, ys, ze, de2, d6);
				tessellator.addVertexWithUV(xe, ye, ze, de2, d4);
				tessellator.addVertexWithUV(xe, ye, zs, ds2, d4);
				tessellator.addVertexWithUV(xe, ys, zs, ds2, d6);
				tessellator.addVertexWithUV(xs, ys, ze, de2, d6);
				tessellator.addVertexWithUV(xs, ye, ze, de2, d4);
				tessellator.addVertexWithUV(xe, ys, zs, ds2, d6);
				tessellator.addVertexWithUV(xe, ye, zs, ds2, d4);
				tessellator.addVertexWithUV(xs, ye, ze, de2, d4);
				tessellator.addVertexWithUV(xs, ys, ze, de2, d6);
			}
			one = true;
		}
		if (blockAccess.isBlockOpaqueCube(i, j + 1, k)) {
			tessellator.addVertexWithUV(xe, ye - v, zs, d2, d6);
			tessellator.addVertexWithUV(xe, ye - v, ze, d, d6);
			tessellator.addVertexWithUV(xs, ye - v, ze, d, d4);
			tessellator.addVertexWithUV(xs, ye - v, zs, d2, d4);
			tessellator.addVertexWithUV(xs, ys, zs, ds3, d6);
			tessellator.addVertexWithUV(xs, ye, zs, ds3, d4);
			tessellator.addVertexWithUV(xe, ye, ze, de3, d4);
			tessellator.addVertexWithUV(xe, ys, ze, de3, d6);
			tessellator.addVertexWithUV(xs, ye, zs, ds3, d4);
			tessellator.addVertexWithUV(xs, ys, zs, ds3, d6);
			tessellator.addVertexWithUV(xe, ys, ze, de3, d6);
			tessellator.addVertexWithUV(xe, ye, ze, de3, d4);
			tessellator.addVertexWithUV(xe, ye, zs, ds3, d4);
			tessellator.addVertexWithUV(xe, ys, zs, ds3, d6);
			tessellator.addVertexWithUV(xs, ys, ze, de3, d6);
			tessellator.addVertexWithUV(xs, ye, ze, de3, d4);
			tessellator.addVertexWithUV(xe, ys, zs, ds3, d6);
			tessellator.addVertexWithUV(xe, ye, zs, ds3, d4);
			tessellator.addVertexWithUV(xs, ye, ze, de3, d4);
			tessellator.addVertexWithUV(xs, ys, ze, de3, d6);
			one = true;
		}
		if (!one) {
			if (blockAccess.getBlockId(i, j + 1, k) == block.blockID) {
				// renderblocks.renderBlockReed(block, i, j, k);
				tessellator.addVertexWithUV(xs, ys, zs, ds4, d6);
				tessellator.addVertexWithUV(xs, ye, zs, ds4, d4);
				tessellator.addVertexWithUV(xe, ye, ze, de4, d4);
				tessellator.addVertexWithUV(xe, ys, ze, de4, d6);
				tessellator.addVertexWithUV(xs, ye, zs, ds4, d4);
				tessellator.addVertexWithUV(xs, ys, zs, ds4, d6);
				tessellator.addVertexWithUV(xe, ys, ze, de4, d6);
				tessellator.addVertexWithUV(xe, ye, ze, de4, d4);
				tessellator.addVertexWithUV(xe, ye, zs, ds4, d4);
				tessellator.addVertexWithUV(xe, ys, zs, ds4, d6);
				tessellator.addVertexWithUV(xs, ys, ze, de4, d6);
				tessellator.addVertexWithUV(xs, ye, ze, de4, d4);
				tessellator.addVertexWithUV(xe, ys, zs, ds4, d6);
				tessellator.addVertexWithUV(xe, ye, zs, ds4, d4);
				tessellator.addVertexWithUV(xs, ye, ze, de4, d4);
				tessellator.addVertexWithUV(xs, ys, ze, de4, d6);
			} else {
				tessellator.addVertexWithUV(i + .6f, ys + .5f, k + .6f, d, d6);
				tessellator.addVertexWithUV(i + .6f, ys + .5f, k + .4f, d2, d6);
				tessellator.addVertexWithUV(i + .4f, ys + .5f, k + .4f, d2, d4);
				tessellator.addVertexWithUV(i + .4f, ys + .5f, k + .6f, d, d4);
			}
		}
		// + 0.015625D is the height off the ground
		/*
		 * 
		 * tessellator.setColorOpaque_F(f, f, f);
		 * tessellator.addVertexWithUV(f6, (double)j + 0.015625D, f8, d2, d6 +
		 * 0.0625D); tessellator.addVertexWithUV(f6, (double)j + 0.015625D, f7,
		 * d2, d4 + 0.0625D); tessellator.addVertexWithUV(f5, (double)j +
		 * 0.015625D, f7, d, d4 + 0.0625D); tessellator.addVertexWithUV(f5,
		 * (double)j + 0.015625D, f8, d, d6 + 0.0625D);
		 */
		/*
		 * } else if(byte0 == 1) { tessellator.addVertexWithUV(f6, (double)j +
		 * 0.015625D, f8, d2, d6); tessellator.addVertexWithUV(f6, (double)j +
		 * 0.015625D, f7, d2, d4); tessellator.addVertexWithUV(f5, (double)j +
		 * 0.015625D, f7, d, d4); tessellator.addVertexWithUV(f5, (double)j +
		 * 0.015625D, f8, d, d6); tessellator.setColorOpaque_F(f, f, f);
		 * tessellator.addVertexWithUV(f6, (double)j + 0.015625D, f8, d2, d6 +
		 * 0.0625D); tessellator.addVertexWithUV(f6, (double)j + 0.015625D, f7,
		 * d2, d4 + 0.0625D); tessellator.addVertexWithUV(f5, (double)j +
		 * 0.015625D, f7, d, d4 + 0.0625D); tessellator.addVertexWithUV(f5,
		 * (double)j + 0.015625D, f8, d, d6 + 0.0625D); } else if(byte0 == 2) {
		 * tessellator.addVertexWithUV(f6, (double)j + 0.015625D, f8, d2, d6);
		 * tessellator.addVertexWithUV(f6, (double)j + 0.015625D, f7, d, d6);
		 * tessellator.addVertexWithUV(f5, (double)j + 0.015625D, f7, d, d4);
		 * tessellator.addVertexWithUV(f5, (double)j + 0.015625D, f8, d2, d4);
		 * tessellator.setColorOpaque_F(f, f, f);
		 * tessellator.addVertexWithUV(f6, (double)j + 0.015625D, f8, d2, d6 +
		 * 0.0625D); tessellator.addVertexWithUV(f6, (double)j + 0.015625D, f7,
		 * d, d6 + 0.0625D); tessellator.addVertexWithUV(f5, (double)j +
		 * 0.015625D, f7, d, d4 + 0.0625D); tessellator.addVertexWithUV(f5,
		 * (double)j + 0.015625D, f8, d2, d4 + 0.0625D); }
		 * 
		 * /*
		 * 
		 * if(!blockAccess.isBlockNormalCube(i, j + 1, k)) { double d1 =
		 * (float)(xo + 16) / 64f; double d3 = ((float)(xo + 16) + 15.99F) /
		 * 64f; double d5 = (float)yo / 64f; double d7 = ((float)yo + 15.99F) /
		 * 64f; if(blockAccess.isBlockNormalCube(i - 1, j, k) &&
		 * blockAccess.getBlockId(i - 1, j + 1, k) == zei_Ids.chalk) {
		 * tessellator.setColorOpaque_F(f * f2, f * f2,f * f2);
		 * tessellator.addVertexWithUV((double)i + 0.015625D, (float)(j + 1) +
		 * 0.021875F, k + 1, d3, d5); tessellator.addVertexWithUV((double)i +
		 * 0.015625D, j + 0, k + 1, d1, d5);
		 * tessellator.addVertexWithUV((double)i + 0.015625D, j + 0, k + 0, d1,
		 * d7); tessellator.addVertexWithUV((double)i + 0.015625D, (float)(j +
		 * 1) + 0.021875F, k + 0, d3, d7); tessellator.setColorOpaque_F(f, f,
		 * f); tessellator.addVertexWithUV((double)i + 0.015625D, (float)(j + 1)
		 * + 0.021875F, k + 1, d3, d5 + 0.0625D);
		 * tessellator.addVertexWithUV((double)i + 0.015625D, j + 0, k + 1, d1,
		 * d5 + 0.0625D); tessellator.addVertexWithUV((double)i + 0.015625D, j +
		 * 0, k + 0, d1, d7 + 0.0625D); tessellator.addVertexWithUV((double)i +
		 * 0.015625D, (float)(j + 1) + 0.021875F, k + 0, d3, d7 + 0.0625D); }
		 * if(blockAccess.isBlockNormalCube(i + 1, j, k) &&
		 * blockAccess.getBlockId(i + 1, j + 1, k) == zei_Ids.chalk) {
		 * tessellator.setColorOpaque_F(f * f2, f * f2, f * f2);
		 * tessellator.addVertexWithUV((double)(i + 1) - 0.015625D, j + 0, k +
		 * 1, d1, d7); tessellator.addVertexWithUV((double)(i + 1) - 0.015625D,
		 * (float)(j + 1) + 0.021875F, k + 1, d3, d7);
		 * tessellator.addVertexWithUV((double)(i + 1) - 0.015625D, (float)(j +
		 * 1) + 0.021875F, k + 0, d3, d5);
		 * tessellator.addVertexWithUV((double)(i + 1) - 0.015625D, j + 0, k +
		 * 0, d1, d5); tessellator.setColorOpaque_F(f, f, f);
		 * tessellator.addVertexWithUV((double)(i + 1) - 0.015625D, j + 0, k +
		 * 1, d1, d7 + 0.0625D); tessellator.addVertexWithUV((double)(i + 1) -
		 * 0.015625D, (float)(j + 1) + 0.021875F, k + 1, d3, d7 + 0.0625D);
		 * tessellator.addVertexWithUV((double)(i + 1) - 0.015625D, (float)(j +
		 * 1) + 0.021875F, k + 0, d3, d5 + 0.0625D);
		 * tessellator.addVertexWithUV((double)(i + 1) - 0.015625D, j + 0, k +
		 * 0, d1, d5 + 0.0625D); } if(blockAccess.isBlockNormalCube(i, j, k - 1)
		 * && blockAccess.getBlockId(i, j + 1, k - 1) == zei_Ids.chalk) {
		 * tessellator.setColorOpaque_F(f * f2, f * f2, f * f2);
		 * tessellator.addVertexWithUV(i + 1, j + 0, (double)k + 0.015625D, d1,
		 * d7); tessellator.addVertexWithUV(i + 1, (float)(j + 1) + 0.021875F,
		 * (double)k + 0.015625D, d3, d7); tessellator.addVertexWithUV(i + 0,
		 * (float)(j + 1) + 0.021875F, (double)k + 0.015625D, d3, d5);
		 * tessellator.addVertexWithUV(i + 0, j + 0, (double)k + 0.015625D, d1,
		 * d5); tessellator.setColorOpaque_F(f, f, f);
		 * tessellator.addVertexWithUV(i + 1, j + 0, (double)k + 0.015625D, d1,
		 * d7 + 0.0625D); tessellator.addVertexWithUV(i + 1, (float)(j + 1) +
		 * 0.021875F, (double)k + 0.015625D, d3, d7 + 0.0625D);
		 * tessellator.addVertexWithUV(i + 0, (float)(j + 1) + 0.021875F,
		 * (double)k + 0.015625D, d3, d5 + 0.0625D);
		 * tessellator.addVertexWithUV(i + 0, j + 0, (double)k + 0.015625D, d1,
		 * d5 + 0.0625D); } if(blockAccess.isBlockNormalCube(i, j, k + 1) &&
		 * blockAccess.getBlockId(i, j + 1, k + 1) == zei_Ids.chalk) {
		 * tessellator.setColorOpaque_F(f * f2, f * f2, f * f2);
		 * tessellator.addVertexWithUV(i + 1, (float)(j + 1) + 0.021875F,
		 * (double)(k + 1) - 0.015625D, d3, d5); tessellator.addVertexWithUV(i +
		 * 1, j + 0, (double)(k + 1) - 0.015625D, d1, d5);
		 * tessellator.addVertexWithUV(i + 0, j + 0, (double)(k + 1) -
		 * 0.015625D, d1, d7); tessellator.addVertexWithUV(i + 0, (float)(j + 1)
		 * + 0.021875F, (double)(k + 1) - 0.015625D, d3, d7);
		 * tessellator.setColorOpaque_F(f, f, f); tessellator.addVertexWithUV(i
		 * + 1, (float)(j + 1) + 0.021875F, (double)(k + 1) - 0.015625D, d3, d5
		 * + 0.0625D); tessellator.addVertexWithUV(i + 1, j + 0, (double)(k + 1)
		 * - 0.015625D, d1, d5 + 0.0625D); tessellator.addVertexWithUV(i + 0, j
		 * + 0, (double)(k + 1) - 0.015625D, d1, d7 + 0.0625D);
		 * tessellator.addVertexWithUV(i + 0, (float)(j + 1) + 0.021875F,
		 * (double)(k + 1) - 0.015625D, d3, d7 + 0.0625D); } }
		 */
		// GL11.glDisable (GL11.GL_BLEND);
		return true;
	}
}
