package zeitgeist.common.block;

import java.util.Random;

import zeitgeist.common.zei_Ids;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class zei_BlockPlague extends Block {
	Random randy;

	public zei_BlockPlague(int i) {
		super(i, 73, Material.plants);
		setHardness(.1f);
		setResistance(0f);
		setStepSound(Block.soundGrassFootstep);
		setTickRandomly(true);
		setBlockBounds(0.2F, 0.2F, 0.2F, 0.8F, 0.8F, 0.8F);
		randy = new Random();
	}

	public int idDropped(int par1, Random par2Random, int par3) {
		return 0;
	}

	public int tickRate() {
		return 0;
	}

	public int getRenderType() {
		return zei_Ids.plagueRenderId;
	}

	public void updateTick(World world, int i, int j, int k, Random random) {
		for (int nn = 0; nn < 4; nn++) {
			spread(world, i, j, k, random);
		}
		if (world.getBlockId(i, j - 1, k) == 2) {
			world.setBlock(i, j - 1, k, 3);
		}
	}

	public void spread(World world, int i, int j, int k, Random random) {
		int io = i;
		int jo = j; // +randy.nextInt(3)-1;
		int ko = k;
		switch (randy.nextInt(4)) {
		case 1:
			io++;
			break;
		case 2:
			io--;
			break;
		case 3:
			ko++;
			break;
		default:
			ko--;
			break;
		}
		randy.setSeed(randy.nextInt());
		Material mat = world.getBlockMaterial(io, jo, ko);
		if ((mat.isGroundCover() || mat == Material.air)
				&& checkHere(world, io, jo, ko) > 0 && mat != Material.water) {
			world.setBlock(io, jo, ko, blockID);
		} else if (world.isAirBlock(i, j - 1, k)) {
			world.setBlock(i, j - 1, k, blockID);
		} else if (world.getBlockId(io, jo, ko) == 18
				|| world.getBlockId(io, jo, ko) == 17) {
			world.setBlock(io, jo, ko, 12);
		} else {
			jo += randy.nextInt(2) == 1 ? 1 : -1;
			if ((mat.isGroundCover() || mat == Material.air)
					&& checkHere(world, io, jo, ko) > 0) {
				world.setBlock(io, jo, ko, blockID);
			}
		}
		// else if((world.isBlockOpaqueCube(i, j+1, k)|| (world.getBlockId(i,
		// j+1, k)==blockID &&checkHere(world,io,jo,ko)==0))&&
		// world.isAirBlock(i, j-1, k) ){
		// world.setBlockWithNotify(io, jo, ko, blockID);
		// }
	}

	public int getBlockTextureFromSideAndMetadata(int i, int j) {
		return blockIndexInTexture;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i,
			int j, int k) {
		return null;
	}

	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i,
			int j, int k, int l) {
		return true;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
		if (checkHere(world, i, j, k) == 0
				&& world.getBlockId(i, j + 1, k) != blockID) {
			world.setBlock(i, j, k, 0);
		} else {
			// spread(world,i,j,k,world.rand);
		}
	}

	/*
	 * public boolean isCollidable() { return true; }
	 */
	public void onEntityCollidedWithBlock(World world, int i, int j, int k,
			Entity en) {
		// spread(world,i,j,k,world.rand);
	}

	public int checkHere(World world, int i, int j, int k) {
		if (world.isBlockOpaqueCube(i - 1, j, k)) {
			return 2;
		}
		if (world.isBlockOpaqueCube(i + 1, j, k)) {
			return 2;
		}
		if (world.isBlockOpaqueCube(i, j, k - 1)) {
			return 2;
		}
		if (world.isBlockOpaqueCube(i, j, k + 1)) {
			return 2;
		}
		if (world.isBlockOpaqueCube(i, j - 1, k)) {
			return 1;
		}
		if (world.isBlockOpaqueCube(i, j + 1, k)) {
			return 1;
		}
		return 0;
	}

	public static boolean isGrowth(World world, int i, int j, int k) {
		int d = world.getBlockId(i, j, k);
		return true;// Block.blocksList[d].blockMaterial.isSolid();//d==0;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
	/*
	 * public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int
	 * k) { return 0x9999AA; }
	 */
}
