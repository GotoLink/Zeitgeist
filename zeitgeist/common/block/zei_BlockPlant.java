package zeitgeist.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class zei_BlockPlant extends Block {
	protected zei_BlockPlant(int i) {
		super(i, 18, Material.ground);
		setBlockBounds(0.25F, 0.0F, 0.25F, .75F, 1, .75F);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		double sx = 0;
		double sx2 = 1;
		return AxisAlignedBB.getBoundingBox(par2 + sx, par3, par4 + sx, par2 + sx2, par3 + 0.5, par4 + sx2);
	}

	public String getTextureFile() {
		return "/zeitgeist/terrain.png";
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k) {
		boolean bound[] = new boolean[6];
		// int id=iblockaccess.getBlockId(i, j-1, k);
		bound[0] = iblockaccess.getBlockId(i - 1, j, k) == blockID;
		bound[1] = iblockaccess.getBlockId(i, j, k - 1) == blockID;
		bound[2] = iblockaccess.getBlockId(i + 1, j, k) == blockID;
		bound[3] = iblockaccess.getBlockId(i, j, k + 1) == blockID;
		bound[4] = iblockaccess.getBlockId(i, j + 1, k) == blockID;
		// boolean any=bound[0]||bound[1]||bound[2]||bound[3];
		setBlockBounds(bound[0] ? 0 : 0.125F, 0.0F, bound[1] ? 0 : 0.125F, bound[2] ? 1 : 0.875F, bound[4] ? 1 : 0.75f, bound[3] ? 1 : 0.875F);
		/*
		 * else{ bound[0]=iblockaccess.getBlockId(i-1, j, k)==blockID &&
		 * iblockaccess.getBlockId(i-1, j-1, k)==0;
		 * bound[1]=iblockaccess.getBlockId(i, j, k-1)==blockID &&
		 * iblockaccess.getBlockId(i, j-1, k-1)==0;
		 * bound[2]=iblockaccess.getBlockId(i+1, j, k)==blockID &&
		 * iblockaccess.getBlockId(i+1, j-1, k)==0;
		 * bound[3]=iblockaccess.getBlockId(i, j, k+1)==blockID &&
		 * iblockaccess.getBlockId(i, j-1, k+1)==0;
		 * setBlockBounds(bound[0]?0:0.25F, 0.0F, bound[1]?0:0.25F,
		 * bound[2]?1:0.75F, 1f, bound[3]?1:0.75F); //setBlockBounds(0.25F,
		 * 0.0F, 0.25F, .75F, 1, .75F); }
		 */
	}

	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0.125F, 0.125F, 0.125F, .875F, 0.875f, .875F);
	}
}
