package zeitgeist.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import zeitgeist.common.zei_Ids;

public class zei_BlockGearBox extends Block {
	float f = 0.375F;
	int it = 0;
	int jt = 0;
	int kt = 0;

	protected zei_BlockGearBox(int i, int j, Material mat) {
		super(i, mat);
		// setBlockBounds(f,f,f,1-f,1-f,1-f);
	}

	@Override
	public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k) {
		return iblockaccess.getBlockMetadata(i, j, k) == 0 ? 0xaa5555 : 0xffffff; // ColorizerGrass.getGrassColor(d,
																					// d1);
	}

	/*
	 * public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i,
	 * int j, int k) { //float f = 0.125F; int
	 * m=iblockaccess.getBlockMetadata(i,j,k); if(m<3){
	 * setBlockBounds(f,f,0,1-f,1-f,1); }else if(m<6){
	 * setBlockBounds(0,f,f,1,1-f,1-f); }else{ setBlockBounds(f,0,f,1-f,1,1-f);
	 * } }
	 */
	// 0 x, 1xu,2xd, 3z, 4zu,5zd, 6y,7yu,8yd
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving) {
		// int i = determineOrientation(par1World, par2, par3, par4,
		// (EntityPlayer)par5EntityLiving);
		// par1World.setBlockMetadataWithNotify(par2, par3, par4, i);
		computeState(par1World, par2, par3, par4);
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
		if (world.isRemote) {
			return;
		}
		computeState(world, i, j, k);
	}

	private boolean allow(World world, int i, int j, int k, int g) {
		boolean b = (world.getBlockId(i, j, k) == zei_Ids.turnBlock && world.getBlockMetadata(i, j, k) == g);
		if (b) {
			if (world.getBlockMetadata(it, jt, kt) != g) {
				world.setBlockMetadataWithNotify(it, jt, kt, g, 3);
			}
		}
		return b;
	}

	private void computeState(World world, int i, int j, int k) {
		int m = world.getBlockMetadata(i, j, k);
		it = i;
		jt = j;
		kt = k;
		if (allow(world, i, j, k + 1, 2) || allow(world, i, j, k - 1, 1) || allow(world, i + 1, j, k, 5) || allow(world, i - 1, j, k, 4) || allow(world, i, j + 1, k, 8)
				|| allow(world, i, j - 1, k, 7)) {
		} else {
			if (m != 0) {
				world.setBlockMetadataWithNotify(i, j, k, 0, 3);
			}
		}
	}
}
