package zeitgeist.common.block;

import zeitgeist.common.zei_Ids;
import zeitgeist.common.tile.zei_TileEntityTurn;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class zei_BlockTurn extends BlockContainer {
	protected zei_BlockTurn(int i) {
		super(i, 4, Material.wood);
		setBlockBounds(f, f, f, 1 - f, 1 - f, 1 - f);
	}

	float f = 0.375F;

	public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i,
			int j, int k) {
		int m = iblockaccess.getBlockMetadata(i, j, k);
		if (m < 3) {
			setBlockBounds(f, f, 0, 1 - f, 1 - f, 1);
		} else if (m < 6) {
			setBlockBounds(0, f, f, 1, 1 - f, 1 - f);
		} else {
			setBlockBounds(f, 0, f, 1 - f, 1, 1 - f);
		}
	}

	// 0 x, 1xu,2xd, 3z, 4zu,5zd, 6y,7yu,8yd
	public boolean isOpaqueCube() {
		return false;
	}

	public int getRenderType() {
		return -1;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public TileEntity createNewTileEntity(World world) {
		return new zei_TileEntityTurn();
	}

	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
			EntityLivingBase par5EntityLiving) {
		int i = determineOrientation(par1World, par2, par3, par4,
				(EntityPlayer) par5EntityLiving);
		par1World.setBlockMetadataWithNotify(par2, par3, par4, i, 3);
		computeState(par1World, par2, par3, par4);
	}

	public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
		if (world.isRemote) {
			return;
		}
		computeState(world, i, j, k);
	}

	private boolean allow(World world, int i, int j, int k, int g, int g2) {
		return (world.getBlockId(i, j, k) == zei_Ids.windmill && world
				.getBlockMetadata(i, j, k) % 2 == 1)
				|| (world.getBlockId(i, j, k) == zei_Ids.gearbox
						&& world.getBlockMetadata(i, j, k) != 0 && world
						.getBlockMetadata(i, j, k) != g2)
				|| (world.getBlockId(i, j, k) == blockID && world
						.getBlockMetadata(i, j, k) == g);
	}

	private void computeState(World world, int i, int j, int k) {
		int m = world.getBlockMetadata(i, j, k);
		int u = m;
		int d = 0;
		if (m < 3) {
			d = 0;
			if (allow(world, i, j, k - 1, 1, 2)) {
				if (m != 1 && m != 2) {
					world.setBlockMetadataWithNotify(i, j, k, 1, 3);
				}
			} else if (allow(world, i, j, k + 1, 2, 1)) {
				if (m != 1 && m != 2) {
					world.setBlockMetadataWithNotify(i, j, k, 2, 3);
				}
			} else {
				// System.out.println("go to zero:" + i + "," + j + "," + k +
				// "  " + m);
				if (m != 0) {
					world.setBlockMetadataWithNotify(i, j, k, 0, 3);
				}
			}
		} else if (m < 6) {
			d = 1;
			u -= 3;
			if (allow(world, i - 1, j, k, 4, 5)) {
				if (m != 4 && m != 5) {
					world.setBlockMetadataWithNotify(i, j, k, 4, 3);
				}
			} else if (allow(world, i + 1, j, k, 5, 4)) {
				if (m != 4 && m != 5) {
					world.setBlockMetadataWithNotify(i, j, k, 5, 3);
				}
			} else {
				if (m != 3) {
					world.setBlockMetadataWithNotify(i, j, k, 3, 3);
				}
			}
		} else {
			d = 2;
			u -= 6;
			if (allow(world, i, j - 1, k, 7, 8)) {
				if (m != 7 && m != 8) {
					world.setBlockMetadataWithNotify(i, j, k, 7, 3);
				}
			} else if (allow(world, i, j + 1, k, 8, 7)) {
				if (m != 7 && m != 8) {
					world.setBlockMetadataWithNotify(i, j, k, 8, 3);
				}
			} else {
				if (m != 6) {
					world.setBlockMetadataWithNotify(i, j, k, 6, 3);
				}
			}
		}
	}

	private static int determineOrientation(World par0World, int par1,
			int par2, int par3, EntityPlayer par4EntityPlayer) {
		if (MathHelper.abs((float) par4EntityPlayer.posX - (float) par1) < 2.0F
				&& MathHelper.abs((float) par4EntityPlayer.posZ - (float) par3) < 2.0F) {
			double d = (par4EntityPlayer.posY + 1.8200000000000001D)
					- (double) par4EntityPlayer.yOffset;
			if (d - (double) par2 > 2D) {
				return 6;// 1;
			}
			if ((double) par2 - d > 0.0D) {
				return 6;// 0;
			}
		}
		int i = MathHelper
				.floor_double((double) ((par4EntityPlayer.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		if (i == 0 || i == 2) {
			return 3;
		}
		return 0;
	}
}
