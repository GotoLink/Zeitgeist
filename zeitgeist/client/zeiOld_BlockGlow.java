package zeitgeist.client;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class zeiOld_BlockGlow extends Block {
	/*
	 * public int idDropped(int i, Random random) { return
	 * Item.energy.shiftedIndex; }
	 */
	public Icon[] D;

	protected zeiOld_BlockGlow(int i, int j) {
		super(i, Material.glass);
		float f = 0.1875F;
		float f2 = 1F - f;
		this.setBlockBounds(f, 0, f, f2, 1F - 0.5F, f2);
		// if (j == zei_Ids.glowy)
		// {
		loadSprites1();
		// }
		// else
		// {
		// loadSprites2();
		// }
	}

	@Override
	public boolean canBlockStay(World world, int i, int j, int k) {
		if (world.getBlockMaterial(i, j - 1, k).isSolid()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean canPlaceBlockAt(World world, int i, int j, int k) {
		if (!super.canPlaceBlockAt(world, i, j, k)) {
			return false;
		} else {
			return canBlockStay(world, i, j, k);
		}
	}

	@Override
	public Icon getBlockTextureFromSide(int i) {
		if (i <= 1) {
			return D[1];
		} else {
			return D[0];
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		float f = 0.25F;
		return AxisAlignedBB.getBoundingBox(i + f, j, k + f, i + 1 - f, j + 0.5F, k + 1 - f);
	}

	@Override
	public int getRenderType() {
		return 13;
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int i, int j, int k) {
		float f = 0.25F;
		return AxisAlignedBB.getBoundingBox(i + f, j, k + f, i + 1 - f, j + 0.5F, k + 1 - f);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	// public static int E[]={1,1};
	public void loadSprites1() {
		D = new int[2];
		D[0] = 0; // zei_Universal.modOverride("/terrain.png",
					// "/zeitgeist/crystal1.png");
		D[1] = 0; // zei_Universal.modOverride("/terrain.png",
					// "/zeitgeist/crystal2.png");
		blockIndexInTexture = D[0];
		/*
		 * E=new int[2]; E[0]=D[0]; E[1]=D[1];
		 */
	}

	public void loadSprites2() {
		D = new int[2];
"/zeitgeist/dragoneggfix.png");
		D[0] = 
				"/zeitgeist/glowy1.png");
		D[1] = 
				"/zeitgeist/glowy2.png");
		blockIndexInTexture = D[0];
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
}
