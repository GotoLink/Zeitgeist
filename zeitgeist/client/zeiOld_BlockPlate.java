package zeitgeist.client;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.Facing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class zeiOld_BlockPlate extends Block {
	protected zeiOld_BlockPlate(int i) {
		super(i, Material.rock);
		blockIndexInTexture = 1;
	}

	/*
	 * public int getRenderType() { return 10; }
	 */
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	public void onBlockPlaced(World world, int i, int j, int k, int l) {
		world.setBlockMetadataWithNotify(i, j, k, l, 3);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	/*
	 * public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i,
	 * int j, int k) { float f = 0.125F; float f2 = 1f-f; int l =
	 * world.getBlockMetadata(i, j, k); /*if(l == 0){ return
	 * AxisAlignedBB.getBoundingBoxFromPool(i, j, k, (float)i + f2, j+1, k + 1);
	 * }else if(l==1){ return AxisAlignedBB.getBoundingBoxFromPool(i, j, k, i +
	 * 1, j+1, (float)k + f2); } return AxisAlignedBB.getBoundingBoxFromPool(i,
	 * j, k, i + 1, (float)j+f2, k + 1); }
	 */
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k) {
		// float f = 0.125F;
		int l = iblockaccess.getBlockMetadata(i, j, k);
		float F = 0.0625F;
		float F2 = 1f - F;
		if (l == 0) {
			setBlockBounds(0.0F, F2, 0.0F, 1.0F, 1.0f, 1.0F);
		} else if (l == 1) {
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, F, 1.0F);
		} else if (l == 2) {
			setBlockBounds(0.0F, 0.0F, F2, 1.0F, 1.0f, 1.0f);
		} else if (l == 3) {
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0f, F);
		} else if (l == 4) {
			setBlockBounds(F2, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
		} else {
			setBlockBounds(0.0F, 0.0F, 0.0F, F, 1.0f, 1.0F);
		}
	}

	/*
	 * public void getCollidingBoundingBoxes(World world, int i, int j, int k,
	 * AxisAlignedBB axisalignedbb, ArrayList arraylist) { int l =
	 * world.getBlockMetadata(i, j, k); if(l == 0) { setBlockBounds(0.0F, 0.0F,
	 * 0.0F, 0.0625F, 1.0F, 1.0F); System.out.println("HERE");
	 * //setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 0.5F, 1.0F);
	 * //super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb,
	 * arraylist); //setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	 * //super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb,
	 * arraylist); } else if(l == 1) { setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F,
	 * 1.0F, 1.0F); super.getCollidingBoundingBoxes(world, i, j, k,
	 * axisalignedbb, arraylist); setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 0.5F,
	 * 1.0F); super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb,
	 * arraylist); } else if(l == 2) { setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F,
	 * 0.5F, 0.5F); super.getCollidingBoundingBoxes(world, i, j, k,
	 * axisalignedbb, arraylist); setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 1.0F,
	 * 1.0F); super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb,
	 * arraylist); } else if(l == 3) { setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F,
	 * 1.0F, 0.5F); super.getCollidingBoundingBoxes(world, i, j, k,
	 * axisalignedbb, arraylist); setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 0.5F,
	 * 1.0F); super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb,
	 * arraylist); } setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F); }
	 */
	/*
	 * public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i,
	 * int j, int k) { return null; }
	 */
	@Override
	public void setBlockBoundsForItemRender() {
		float f = 0.5F;
		float f1 = 0.125F;
		float f2 = 0.5F;
		setBlockBounds(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1, 0.5F + f2);
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		/*
		 * if (par5 != 1 && par5 != 0 &&
		 * !super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4,
		 * par5)) { return false; }
		 */
		// int mm=par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		int i = par2;
		int j = par3;
		int k = par4;
		i += Facing.offsetsXForSide[Facing.faceToSide[par5]];
		j += Facing.offsetsYForSide[Facing.faceToSide[par5]];
		k += Facing.offsetsZForSide[Facing.faceToSide[par5]];
		boolean flag = (par1IBlockAccess.getBlockMetadata(i, j, k) & 8) != 0;
		int mm = par1IBlockAccess.getBlockMetadata(i, j, k);
		if (par5 == mm) {
			return true;
		} else {
			return !par1IBlockAccess.isBlockOpaqueCube(par2, par3, par4);
		}
		/*
		 * if (!flag) { if (par5 == 1) { return true; } if (par5 == 0 &&
		 * super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5))
		 * { return true; } else { return !(par1IBlockAccess.getBlockId(par2,
		 * par3, par4) == blockID && par1IBlockAccess.getBlockMetadata(par2,
		 * par3, par4) == mm) || (par1IBlockAccess.getBlockMetadata(par2, par3,
		 * par4) & 8) != 0; } } if (par5 == 0) { return true; } if (par5 == 1 &&
		 * super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5))
		 * { return true; } else { return !(par1IBlockAccess.getBlockId(par2,
		 * par3, par4) == blockID && par1IBlockAccess.getBlockMetadata(par2,
		 * par3, par4) == mm)|| (par1IBlockAccess.getBlockMetadata(par2, par3,
		 * par4) & 8) == 0; }
		 */
	}
}
