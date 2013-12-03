package zeitgeist.client;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class zeiOld_BlockHollow extends Block {
	static int D[];

	protected zeiOld_BlockHollow(int i) {
		super(i, Material.air);
		// setTickOnLoad(true);
		// slipperiness = 1.50F;
	}

	public int getBlockTextureFromSideAndMetadata(int i, int j) {
		return D[j];
	}

	/*
	 * public void updateTick(World world, int i, int j, int k, Random random) {
	 * //if(random.nextInt(10)==0){ //if(world.getClosestPlayer((double)i,
	 * (double)j, (double)k, 10D)==null){ //int bb=world.getBlockId(i,j+1,k);
	 * if(random.nextInt(20)==15){ onBlockRemoval(world,i,j,k); } /*int
	 * xo=random.nextInt(3)-1; int yo=random.nextInt(3)-1; int
	 * zo=random.nextInt(3)-1; if(world.getBlockId(i+xo,j+yo,k+zo)!=blockID){
	 * onBlockRemoval( world, i, j, k); } //} //} }
	 */
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		return null;
	}

	public boolean getIsBlockSolid(IBlockAccess iblockaccess, int i, int j, int k, int l) {
		return false;// iblockaccess.getBlockMaterial(i, j, k).isSolid();
	}

	@Override
	public int getRenderBlockPass() {
		return 1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	public void onBlockRemoval(World world, int i, int j, int k) {
		int meto = world.getBlockMetadata(i, j, k);
		switch (meto) {
		case 0:
			world.setBlock(i, j, k, 1);
			return;
		case 1:
			world.setBlock(i, j, k, 2);
			return;
		case 2:
			world.setBlock(i, j, k, 4);
			return;
		case 3:
			world.setBlock(i, j, k, 9);
			return;
		case 4:
			world.setBlock(i, j, k, 11);
			return;
		case 5:
			world.setBlock(i, j, k, 82);
			return;
		case 6:
			world.setBlock(i, j, k, 3);
			return;
		case 7:
			world.setBlock(i, j, k, 12);
			return;
		case 8:
			world.setBlock(i, j, k, 24);
			return;
		case 9:
			world.setBlock(i, j, k, 13);
			return;
		}
	}

	// handleWaterMovement
	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
		// entity.motionY*=0.99f;
		if (entity.motionY < 0) {
			entity.motionY /= 1.4f;
		} else {
			if (entity.onGround) {
				entity.motionY += 0.01f;
			}
		}
		entity.fallDistance = -2;
	}

	/*
	 * public int tickRate() { return 1; }
	 */
	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
		/*
		 * if(l==9 || l==8){ onBlockRemoval(world,i,j,k); return; }
		 */
		int bb = world.getBlockId(i, j + 1, k);
		if (bb != 0 && bb != blockID) {
			onBlockRemoval(world, i, j, k);
			return;
		}
		/*
		 * int bb=world.getBlockId(i-1,j,k); if(bb!=0 && bb!=blockID){
		 * onBlockRemoval(world,i,j,k); return; } bb=world.getBlockId(i+1,j,k);
		 * if(bb!=0 && bb!=blockID){ onBlockRemoval(world,i,j,k); return; }
		 * bb=world.getBlockId(i,j,k+1); if(bb!=0 && bb!=blockID){
		 * onBlockRemoval(world,i,j,k); return; } bb=world.getBlockId(i,j,k-1);
		 * if(bb!=0 && bb!=blockID){ onBlockRemoval(world,i,j,k); return; }
		 */
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l) {
		int i1 = iblockaccess.getBlockId(i, j, k);
		if (i1 == blockID || i1 == 9) {
			return false;
		} else {
			return super.shouldSideBeRendered(iblockaccess, i, j, k, l);
		}
	}

	/*
	 * public boolean blockActivated(World world, int i, int j, int k,
	 * EntityPlayer entityplayer) { onBlockRemoval(world,i,j,k); return false; }
	 */
	/*
	 * public void onEntityCollidedWithBlock(World world, int i, int j, int k,
	 * Entity entity) { }
	 */
	public int type(int i) {
		switch (i) {
		// case 0:return 1;
		case 1:
			return 2;
		case 2:
			return 4;
		case 3:
			return 9;
		case 4:
			return 11;
		case 5:
			return 82;
		case 6:
			return 3;
		case 7:
			return 12;
		case 8:
			return 24;
		case 9:
			return 13;
		}
		return 1;
	}

	static void loadSprites() {
		D = new int[16];
		D[0] = 0; // AutomatonUniversal.modOverride("/terrain.png",
					// "/automatons/hollow1.png");
		D[1] = 0; // AutomatonUniversal.modOverride("/terrain.png",
					// "/automatons/hollow3.png");
		D[2] = 0; // AutomatonUniversal.modOverride("/terrain.png",
					// "/automatons/hollow4.png");
		D[3] = 0; // AutomatonUniversal.modOverride("/terrain.png",
					// "/automatons/hollow9.png");
		D[4] = 0; // AutomatonUniversal.modOverride("/terrain.png",
					// "/automatons/hollow11.png");
		D[5] = 0; // AutomatonUniversal.modOverride("/terrain.png",
					// "/automatons/hollow82.png");
		D[6] = 0; // AutomatonUniversal.modOverride("/terrain.png",
					// "/automatons/hollow2.png");
		D[7] = 0; // AutomatonUniversal.modOverride("/terrain.png",
					// "/automatons/hollow12.png");
		D[8] = D[7];
		D[9] = 0; // AutomatonUniversal.modOverride("/terrain.png",
					// "/automatons/hollow13.png");
	}
}
