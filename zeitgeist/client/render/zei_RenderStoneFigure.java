package zeitgeist.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import zeitgeist.common.tile.zei_TileEntityStatue;

public class zei_RenderStoneFigure {
	public static boolean render(RenderBlocks renderblocks,
			IBlockAccess blockAccess, Block block, int i, int j, int k) {
		zei_TileEntityStatue tile = ((zei_TileEntityStatue) blockAccess
				.getBlockTileEntity(i, j, k));
		int l = tile.turn;
		int M = 0;
		Block BB;
		// int it = 1;
		// while ((M = blockAccess.getBlockId(i, j - it, k)) == block.blockID)
		// {
		// it++;
		// }
		BB = block;
		block = Block.blocksList[tile.blok];
		if (block == null) {
			block = BB;
		}
		// renderblocks.overrideBlockTexture=Block.blocksList[M].getBlockTextureFromSide(0);
		boolean flag = false;
		// Tessellator tess =Tessellator.instance;
		// tess.draw();
		// tess.addTranslation(i+0.5f, j+0.5f, k+0.5f);
		// int meta=blockAccess.getBlockMetadata(i, j-1, k);
		if (l == 12) {
			if (blockAccess.getBlockId(i, j + 1, k) != BB.blockID) {
				block.setBlockBounds(0.125F, 0.25F, 0.125F, 0.875F, 0.75F,
						0.875F);
				renderblocks.renderStandardBlock(block, i, j, k);
				// renderblocks.renderBlockAsItem(block, meta, 1);
				block.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.125F,
						0.875F);
				// renderblocks.renderBlockAsItem(block, meta, 1);
				renderblocks.renderStandardBlock(block, i, j, k);
				block.setBlockBounds(0.25F, 0.125F, 0.125F, 0.875F, 0.25F,
						0.875F);
				// renderblocks.renderBlockAsItem(block, meta, 1);
				renderblocks.renderStandardBlock(block, i, j, k);
				block.setBlockBounds(0.0625F, 0.5F, 0.1875F, 0.15F, 0.6F,
						0.8125F);
				// renderblocks.renderBlockAsItem(block, meta, 1);
				renderblocks.renderStandardBlock(block, i, j, k);
				BB.setBlockBounds(0.1F, 0.4F, 0.7F, 0.3F, 0.5F, 0.8F);
				renderblocks.renderStandardBlock(BB, i, j, k);
				// renderblocks.renderBlockAsItem(BB, meta, 1);
				BB.setBlockBounds(0.1F, 0.4F, 0.2F, 0.3F, 0.5F, 0.3F);
				renderblocks.renderStandardBlock(BB, i, j, k);
				// renderblocks.renderBlockAsItem(BB, meta, 1);
				flag = true;
			} else if (blockAccess.getBlockId(i, j + 2, k) != BB.blockID) {
				block.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 1.0F, 0.8F);
				renderblocks.renderStandardBlock(block, i, j, k);
				block.setBlockBounds(0.4F, 0.0F, 0.8F, 0.6F, 0.95F, 1.0F);
				renderblocks.renderStandardBlock(block, i, j, k);
				block.setBlockBounds(0.4F, 0.0F, 0.0F, 0.6F, 0.95F, 0.2F);
				renderblocks.renderStandardBlock(block, i, j, k);
			} else {
				block.setBlockBounds(0.4F, 0.0F, 0.6F, 0.6F, 1F, 0.8F);
				renderblocks.renderStandardBlock(block, i, j, k);
				block.setBlockBounds(0.4F, 0.0F, 0.2F, 0.6F, 1F, 0.4F);
				renderblocks.renderStandardBlock(block, i, j, k);
			}
		} else if (l == 14) {
			if (blockAccess.getBlockId(i, j + 1, k) != BB.blockID) {
				block.setBlockBounds(0.125F, 0.25F, 0.125F, 0.875F, 0.75F,
						0.875F);
				renderblocks.renderStandardBlock(block, i, j, k);
				block.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.125F,
						0.875F);
				renderblocks.renderStandardBlock(block, i, j, k);
				block.setBlockBounds(0.125F, 0.125F, 0.25F, 0.875F, 0.25F,
						0.875F);
				renderblocks.renderStandardBlock(block, i, j, k);
				block.setBlockBounds(0.1875F, 0.5F, 0.0625F, 0.8125F, 0.6F,
						0.15F);
				renderblocks.renderStandardBlock(block, i, j, k);
				BB.setBlockBounds(0.7F, 0.4F, 0.1F, 0.8F, 0.5F, 0.3F);
				renderblocks.renderStandardBlock(BB, i, j, k);
				BB.setBlockBounds(0.2F, 0.4F, 0.1F, 0.3F, 0.5F, 0.3F);
				renderblocks.renderStandardBlock(BB, i, j, k);
				flag = true;
			} else if (blockAccess.getBlockId(i, j + 2, k) != BB.blockID) {
				block.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 1.0F, 0.8F);
				renderblocks.renderStandardBlock(block, i, j, k);
				block.setBlockBounds(0.8F, 0.0F, 0.4F, 1F, 0.95F, .6F);
				renderblocks.renderStandardBlock(block, i, j, k);
				block.setBlockBounds(0.0F, 0.0F, 0.4F, 0.2F, 0.95F, 0.6F);
				renderblocks.renderStandardBlock(block, i, j, k);
			} else {
				block.setBlockBounds(0.6F, 0.0F, 0.4F, 0.8F, 1F, 0.6F);
				renderblocks.renderStandardBlock(block, i, j, k);
				block.setBlockBounds(0.2F, 0.0F, 0.4F, 0.4F, 1F, 0.6F);
				renderblocks.renderStandardBlock(block, i, j, k);
			}
		} else if (l == 13) {
			if (blockAccess.getBlockId(i, j + 1, k) != BB.blockID) {
				block.setBlockBounds(0.125F, 0.25F, 0.125F, 0.875F, 0.75F,
						0.875F);
				renderblocks.renderStandardBlock(block, i, j, k);
				block.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.125F,
						0.875F);
				renderblocks.renderStandardBlock(block, i, j, k);
				block.setBlockBounds(0.125F, 0.125F, 0.125F, 0.75f, 0.25F,
						0.875F);
				renderblocks.renderStandardBlock(block, i, j, k);
				block.setBlockBounds(0.8125F, 0.5F, 0.1875F, 0.9375F, 0.6F,
						0.8125F);
				renderblocks.renderStandardBlock(block, i, j, k);
				BB.setBlockBounds(0.7F, 0.4F, 0.7F, 0.9F, 0.5F, 0.8F);
				renderblocks.renderStandardBlock(BB, i, j, k);
				BB.setBlockBounds(0.7F, 0.4F, 0.2F, 0.9F, 0.5F, 0.3F);
				renderblocks.renderStandardBlock(BB, i, j, k);
				flag = true;
			} else if (blockAccess.getBlockId(i, j + 2, k) != BB.blockID) {
				block.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 1.0F, 0.8F);
				renderblocks.renderStandardBlock(block, i, j, k);
				block.setBlockBounds(0.4F, 0.0F, 0.8F, 0.6F, 0.95F, 1.0F);
				renderblocks.renderStandardBlock(block, i, j, k);
				block.setBlockBounds(0.4F, 0.0F, 0.0F, 0.6F, 0.95F, 0.2F);
				renderblocks.renderStandardBlock(block, i, j, k);
			} else {
				block.setBlockBounds(0.4F, 0.0F, 0.6F, 0.6F, 1F, 0.8F);
				renderblocks.renderStandardBlock(block, i, j, k);
				block.setBlockBounds(0.4F, 0.0F, 0.2F, 0.6F, 1F, 0.4F);
				renderblocks.renderStandardBlock(block, i, j, k);
			}
		} else {
			if (blockAccess.getBlockId(i, j + 1, k) != BB.blockID) {
				block.setBlockBounds(0.125F, 0.25F, 0.125F, 0.875F, 0.75F,
						0.875F);
				renderblocks.renderStandardBlock(block, i, j, k);
				block.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.125F,
						0.875F);
				renderblocks.renderStandardBlock(block, i, j, k);
				block.setBlockBounds(0.125F, 0.125F, 0.125F, 0.875F, 0.25F,
						0.75F);
				renderblocks.renderStandardBlock(block, i, j, k);
				block.setBlockBounds(0.1875F, 0.5F, 0.8125F, 0.8125F, 0.6F,
						0.9375F);
				renderblocks.renderStandardBlock(block, i, j, k);
				BB.setBlockBounds(0.7F, 0.4F, 0.7F, 0.8F, 0.5F, 0.9F);
				renderblocks.renderStandardBlock(BB, i, j, k);
				BB.setBlockBounds(0.2F, 0.4F, 0.7F, 0.3F, 0.5F, 0.9F);
				renderblocks.renderStandardBlock(BB, i, j, k);
				flag = true;
			} else if (blockAccess.getBlockId(i, j + 2, k) != BB.blockID) {
				block.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 1.0F, 0.8F);
				renderblocks.renderStandardBlock(block, i, j, k);
				block.setBlockBounds(0.8F, 0.0F, 0.4F, 1F, 0.95F, .6F);
				renderblocks.renderStandardBlock(block, i, j, k);
				block.setBlockBounds(0.0F, 0.0F, 0.4F, 0.2F, 0.95F, 0.6F);
				renderblocks.renderStandardBlock(block, i, j, k);
			} else {
				block.setBlockBounds(0.6F, 0.0F, 0.4F, 0.8F, 1F, 0.6F);
				renderblocks.renderStandardBlock(block, i, j, k);
				block.setBlockBounds(0.2F, 0.0F, 0.4F, 0.4F, 1F, 0.6F);
				renderblocks.renderStandardBlock(block, i, j, k);
			}
		}
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		BB.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		// renderblocks.overrideBlockTexture=-1;
		// tess.addTranslation(-i-0.5f, -j-0.5f, -k-0.5f);
		// tess.startDrawingQuads();
		return flag;
	}
}
