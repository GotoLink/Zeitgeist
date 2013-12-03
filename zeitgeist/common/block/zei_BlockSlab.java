package zeitgeist.common.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import zeitgeist.common.zei_Ids;

public class zei_BlockSlab extends Block {
	public zei_BlockSlab(int par1) {
		super(par1, 6, Material.rock);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	}

	@Override
	public int damageDropped(int par1) {
		return 9;
	}

	public void getCollidingBoundingBoxes(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, ArrayList par6ArrayList) {
		setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		super.getCollidingBoundingBoxes(par1World, par2, par3, par4, par5AxisAlignedBB, par6ArrayList);
	}

	@Override
	public Icon getIcon(int par1, int par2) {
		return 1;
	}

	/*
	 * public void onBlockPlaced(World par1World, int par2, int par3, int par4,
	 * int par5) { if (par5 == 0 && !blockType) { int i =
	 * par1World.getBlockMetadata(par2, par3, par4) & 7;
	 * par1World.setBlockMetadataWithNotify(par2, par3, par4, i | 8); } }
	 */
	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return zei_Ids.craftSet1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 1;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		int into = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, (16 - into) / 16f, 1.0F);
	}

	/**
	 * Sets the block's bounds for rendering it as an item
	 */
	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		// if (blockType)
		// {
		return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
		// }
		/*
		 * if (par5 != 1 && par5 != 0 &&
		 * !super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4,
		 * par5)) { return false; } int i = par2; int j = par3; int k = par4; i
		 * += Facing.offsetsXForSide[Facing.faceToSide[par5]]; j +=
		 * Facing.offsetsYForSide[Facing.faceToSide[par5]]; k +=
		 * Facing.offsetsZForSide[Facing.faceToSide[par5]]; boolean flag =
		 * (par1IBlockAccess.getBlockMetadata(i, j, k) & 8) != 0; if (!flag) {
		 * if (par5 == 1) { return true; } if (par5 == 0 &&
		 * super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5))
		 * { return true; } else { return par1IBlockAccess.getBlockId(par2,
		 * par3, par4) != blockID || (par1IBlockAccess.getBlockMetadata(par2,
		 * par3, par4) & 8) != 0; } } if (par5 == 0) { return true; } if (par5
		 * == 1 && super.shouldSideBeRendered(par1IBlockAccess, par2, par3,
		 * par4, par5)) { return true; } else { return
		 * par1IBlockAccess.getBlockId(par2, par3, par4) != blockID ||
		 * (par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 8) == 0; }
		 */
	}
	/**
	 * Returns an item stack containing a single instance of the current block
	 * type. 'i' is the block's subtype/damage and is ignored for blocks which
	 * do not support subtypes. Blocks which cannot be harvested should return
	 * null.
	 */
	/*
	 * protected ItemStack createStackedBlock(int par1) { return new
	 * ItemStack(Block.stairSingle.blockID, 1, par1 & 7); }
	 */
}
