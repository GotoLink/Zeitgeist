package zeitgeist.common.block;

import java.util.Random;

import zeitgeist.common.tile.zei_TileEntityWindmill;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class zei_BlockWindmill extends BlockContainer {
	protected zei_BlockWindmill(int i, int j, Material mat) {
		super(i, mat);
		setTickRandomly(true);
	}

	public int tickRate() {
		return 0;
	}

	public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
		if (world.isRemote) {
			return;
		}
		computeState(world, i, j, k);
	}

	public void computeState(World world, int i, int j, int k) {
		boolean b1 = world.isAirBlock(i, j, k - 1)
				&& world.isAirBlock(i - 1, j, k - 1)
				&& world.isAirBlock(i + 1, j, k - 1)
				&& world.isAirBlock(i, j + 1, k - 1)
				&& world.isAirBlock(i, j - 1, k - 1);
		boolean b2 = world.isAirBlock(i + 1, j, k)
				&& world.isAirBlock(i + 1, j, k + 1)
				&& world.isAirBlock(i + 1, j, k - 1)
				&& world.isAirBlock(i + 1, j + 1, k)
				&& world.isAirBlock(i + 1, j - 1, k);
		boolean b3 = world.isAirBlock(i, j, k + 1)
				&& world.isAirBlock(i - 1, j, k + 1)
				&& world.isAirBlock(i + 1, j, k + 1)
				&& world.isAirBlock(i, j + 1, k + 1)
				&& world.isAirBlock(i, j - 1, k + 1);
		boolean b4 = world.isAirBlock(i - 1, j, k)
				&& world.isAirBlock(i - 1, j, k + 1)
				&& world.isAirBlock(i - 1, j, k - 1)
				&& world.isAirBlock(i - 1, j + 1, k)
				&& world.isAirBlock(i - 1, j - 1, k);
		int hh = world.getBlockMetadata(i, j, k);
		if (hh % 2 == 0) {
			if (hh == 0) {
				if (b1) {
					world.setBlockMetadataWithNotify(i, j, k, 1, 3);
				}
			} else if (hh == 2) {
				if (b2) {
					world.setBlockMetadataWithNotify(i, j, k, 3, 3);
				}
			} else if (hh == 4) {
				if (b3) {
					world.setBlockMetadataWithNotify(i, j, k, 5, 3);
				}
			} else if (hh == 6) {
				if (b4) {
					world.setBlockMetadataWithNotify(i, j, k, 7, 3);
				}
			}
			// world.setBlockMetadataWithNotify(i, j, k,hh+1);
		} else {
			if (hh == 1) {
				if (!b1) {
					world.setBlockMetadataWithNotify(i, j, k, 0, 3);
				}
			} else if (hh == 3) {
				if (!b2) {
					world.setBlockMetadataWithNotify(i, j, k, 2, 3);
				}
			} else if (hh == 5) {
				if (!b3) {
					world.setBlockMetadataWithNotify(i, j, k, 4, 3);
				}
			} else if (hh == 7) {
				if (!b4) {
					world.setBlockMetadataWithNotify(i, j, k, 6, 3);
				}
			}
		}
	}

	public void updateTick(World world, int i, int j, int k, Random random) {
		computeState(world, i, j, k);
	}

	float f = 0.75F; // 375

	public void setBlockBoundsForItemRender() {
		setBlockBounds(0, 0, 0, 1, 1, f);
	}

	public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i,
			int j, int k) {
		// float f = 0.125F;
		int m = iblockaccess.getBlockMetadata(i, j, k);
		switch (m) {
		case 0:
			setBlockBounds(0, 0, 0, 1, 1, f);
			break;
		case 1:
			setBlockBounds(0, 0, 0, 1, 1, f);
			break;
		case 2:
			setBlockBounds(1 - f, 0, 0, 1, 1, 1);
			break;
		case 3:
			setBlockBounds(1 - f, 0, 0, 1, 1, 1);
			break;
		case 4:
			setBlockBounds(0, 0, 1 - f, 1, 1, 1);
			break;
		case 5:
			setBlockBounds(0, 0, 1 - f, 1, 1, 1);
			break;
		case 6:
			setBlockBounds(0, 0, 0, f, 1, 1);
			break;
		case 7:
			setBlockBounds(0, 0, 0, f, 1, 1);
			break;
		}
	}

	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
			EntityLiving par5EntityLiving) {
		int i = ((MathHelper
				.floor_double((double) ((par5EntityLiving.rotationYaw * 4F) / 360F) + 0.5D) & 3) + 2) % 4;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, i * 2, 3);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO Auto-generated method stub
		return new zei_TileEntityWindmill();
	}
}
