package zeitgeist.common.block;

import java.util.Random;

import zeitgeist.server.zei_Ids;
import zeitgeist.server.zei_Universal;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class zei_BlockGrower extends Block {
	protected zei_BlockGrower(int i, int j) {
		super(i, Material.plants);
		// blockIndexInTexture = j;
		float f = 0.375F;
		// setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
		// float f = 0.1875F;
		float f2 = 1F - f;
		this.setBlockBounds(f, 0, f, f2, 1F, f2);
		setTickRandomly(true);
	}

	static Icon[] D = { 0, 0 };

	static void loadSprites() {
		D = new int[2];
		D[0] = "/zeitgeist/grower1.png");
		D[1] = "/zeitgeist/grower2.png");
		// blockIndexInTexture=D[0];
	}

	public Icon getBlockTextureFromSide(int i) {
		if (i <= 1) {
			return D[1];
		} else {
			return D[0];
		}
	}

	public int derk[] = {
	/*
	 * 13981788, 11619426, 9257064, 6894702, 4532340, 2169978
	 */
	15599658, 12913722, 10227786, 7541850, 4855914, 2169978 };

	public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k) {
		int g = iblockaccess.getBlockMetadata(i, j, k);
		// double d = iblockaccess.getWorldChunkManager().temperature[0];
		// double d1 = iblockaccess.getWorldChunkManager().humidity[0];
		// System.out.println((((255<<8)+255)<<8)+255);
		/*
		 * 21 1C 7A FC 56 56
		 * 
		 * 33 28 122 252 86 86
		 */
		/*
		 * int f1=33+55*g; int f2=28+14*g; int f3=122-9*g;
		 */
		return derk[g];// (((f1<<8)+f2)<<8)+f3;//0xaa00ff<<(8*(g+1));//ColorizerGrass.getGrassColor(d,
						// d1);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i,
			int j, int k) {
		float f = 0.375F;
		return AxisAlignedBB.getBoundingBox((float) i + f, j, (float) k
				+ f, (float) (i + 1) - f, (float) j + 1, (float) (k + 1) - f);
	}

	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int i,
			int j, int k) {
		float f = 0.375f;
		return AxisAlignedBB.getBoundingBox((float) i + f, j, (float) k
				+ f, (float) (i + 1) - f, (float) j + 1, (float) (k + 1) - f);
	}

	public void updateTick(World world, int i, int j, int k, Random random) {
		int i1 = world.getBlockMetadata(i, j, k);
		if (world.isAirBlock(i, j + 1, k)) {
			int l;
			for (l = 1; world.getBlockId(i, j - l, k) == blockID; l++) {
			}
			if (l < 15) {
				if (i1 >= 3) {
					// world.setBlockMetadataWithNotify(i, j, k, 5);
					world.setBlock(i, j + 1, k, blockID);
					world.setBlockMetadataWithNotify(i, j, k, 3, 3);
				} else {
					world.setBlock(i, j, k, blockID,
							i1 + 1, 3);
				}
			}
		} else if (i1 < 5) {
			world.setBlock(i, j, k, blockID, i1 + 1, 3);
		}
	}

	public boolean canPlaceBlockAt(World world, int i, int j, int k) {
		int bbb = world.getBlockId(i, j - 1, k);
		int hhh = world.getBlockId(i, j, k);
		// hhh=0 hhh=mine
		// bbb=frass bbb=mine
		return (hhh == 0 || hhh == blockID)
				&& (bbb == zei_Ids.arch || bbb == zei_Ids.arch2 || bbb == blockID);
	}

	public void onBlockRemoval(World world, int i, int j, int k) {
		// world.spawnParticle("reddust", i+0.5F, j+0.5F, k+0.5F, 0, 0.2F, 0);
		if (world.getBlockId(i, j, k) == 0
				&& world.getBlockId(i, j - 1, k) == blockID) {
			world.setBlockMetadataWithNotify(i, j - 1, k, 0, 3);
		}
	}

	public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
		checkBlockCoordValid(world, i, j, k);
		// System.out.println("neighbor: "+l);
	}

	protected final void checkBlockCoordValid(World world, int i, int j, int k) {
		if (!canBlockStay(world, i, j, k)) {
			dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k), 0);
			world.setBlock(i, j, k, 0);
		}
	}

	public boolean canBlockStay(World world, int i, int j, int k) {
		return canPlaceBlockAt(world, i, j, k);
	}

	/*
	 * public int idDropped(int i, Random random) { return
	 * Item.reed.shiftedIndex; }
	 */
	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
	/*
	 * public int getRenderType() { return 11; }
	 */
}
