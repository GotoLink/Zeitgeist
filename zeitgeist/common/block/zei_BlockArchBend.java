package zeitgeist.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import zeitgeist.common.zei_Ids;
import zeitgeist.common.entity.zei_EntityGolem;
import zeitgeist.common.entity.zei_EntityGolemPure;

public class zei_BlockArchBend extends Block {
	// 0 regular
	// 1 exposed to sun
	// 2 brancher
	// 3 plant eater
	// 4 nada
	public zei_BlockArchBend(int par1) {
		super(par1, Material.glass);
		setBlockBounds(0.25F, 0.0F, 0.25F, .75F, 1, .75F);
		this.setTickRandomly(true);
	}

	/*
	 * public void grow(World world, int i, int j, int k, Random random) { if
	 * (!world.isRemote) { int id = world.getBlockId(i, j + 1, k); if (id !=
	 * blockID && id != zei_Ids.crink) { if (world.canBlockSeeTheSky(i, j, k)) {
	 * if (random.nextInt(7) == 0) { payload(world, i, j, k, random); } else {
	 * world.setBlock(i, j + 1, k, blockID, 1); archy(world, i, j, k); } } else
	 * if (!planterCheck(world, i, j, k)) { if (random.nextInt(4) == 0) { switch
	 * (random.nextInt(4)) { case 1: if (world.getBlockId(i - 1, j, k) !=
	 * blockID) world.setBlock(i - 1, j, k, blockID, 2); break; case 2: if
	 * (world.getBlockId(i + 1, j, k) != blockID) world.setBlock(i + 1, j, k,
	 * blockID, 2); break; case 3: if (world.getBlockId(i, j, k - 1) != blockID)
	 * world.setBlock(i, j, k - 1, blockID, 2); break; default: if
	 * (world.getBlockId(i, j, k + 1) != blockID) world.setBlock(i, j, k + 1,
	 * blockID, 2); break; } } else { archy(world, i, j, k); world.setBlock(i, j
	 * + 1, k, blockID); } } } } } public void payload(World world, int i, int
	 * j, int k, Random random) { int ii = random.nextInt(5); if (ii == 0) { ii
	 * = 10; int it = 0; int count = 0; while (world.getBlockId(i, j - it, k) ==
	 * blockID) { it++; if (world.getBlockMetadata(i, j - it, k) == 3) count++;
	 * } if (count > 0) { for (int n = 0; n < it; n++) {
	 * world.spawnEntityInWorld(new zei_EntityGolemPure(world, (float) i + 0.5F,
	 * (float) j + 1, (float) k + 0.5F, zei_Ids.arch, 8, 0)); } } else {
	 * world.spawnEntityInWorld(new zei_EntityGolem(world, (float) i + 0.5F,
	 * (float) j + 1, (float) k + 0.5F, zei_Ids.arch, 2, 0)); } } if (ii == 4 ||
	 * ii == 3) { boop(world, i, j + 1, k, ii); boop(world, i + 1, j + 1, k,
	 * ii); boop(world, i + 2, j + 1, k, ii); boop(world, i - 1, j + 1, k, ii);
	 * boop(world, i - 2, j + 1, k, ii); boop(world, i, j + 1, k + 1, ii);
	 * boop(world, i, j + 1, k + 2, ii); boop(world, i, j + 1, k - 1, ii);
	 * boop(world, i, j + 1, k - 2, ii); boop(world, i, j + 2, k, ii);
	 * boop(world, i - 1, j + 1, k - 1, ii); boop(world, i - 1, j + 1, k + 1,
	 * ii); boop(world, i + 1, j + 1, k + 1, ii); boop(world, i + 1, j + 1, k -
	 * 1, ii); } else { world.setBlock(i, j + 1, k, zei_Ids.crink, ii); } }
	 * public void boop(World world, int i, int j, int k, int ii) { if
	 * (world.getBlockId(i, j, k) == 0) { world.setBlock(i, j, k, zei_Ids.crink,
	 * ii); } } public void archy(World world, int i, int j, int k) {
	 * zei_BlockArch.place(world, i - 1, j, k); zei_BlockArch.place(world, i +
	 * 1, j, k); zei_BlockArch.place(world, i, j, k - 1);
	 * zei_BlockArch.place(world, i, j, k + 1); }
	 */
	@Override
	public boolean canBlockStay(World world, int i, int j, int k) {
		return canPlaceBlockAt(world, i, j, k);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int i, int j, int k) {
		int bbb = world.getBlockId(i, j - 1, k);
		int meta = world.getBlockMetadata(i, j, k);
		if (meta >= 3) {
			return true;
		}
		if (bbb == 0) {
			if (meta == 2 && (world.getBlockId(i - 1, j, k) != 0 || world.getBlockId(i + 1, j, k) != 0 || world.getBlockId(i, j, k - 1) != 0 || world.getBlockId(i, j, k + 1) != 0)) {
				return true;
			} else {
				return false;
			}
		}
		return bbb != 0;
	}

	/*
	 * public int getBlockTextureFromSideAndMetadata(int i, int j) { return
	 * zei_BlockArch.D[0]; }
	 */
	@Override
	public int colorMultiplier(IBlockAccess par1IBlockAccess, int i, int j, int k) {
		switch (par1IBlockAccess.getBlockMetadata(i, j, k)) {
		case 1:
			return 0x5EA4FF;
		case 3:
			int var5 = 0;
			int var6 = 0;
			int var7 = 0;
			for (int var8 = -1; var8 <= 1; ++var8) {
				for (int var9 = -1; var9 <= 1; ++var9) {
					int var10 = par1IBlockAccess.getBiomeGenForCoords(i + var9, k + var8).getBiomeGrassColor();
					var5 += (var10 & 16711680) >> 16;
					var6 += (var10 & 65280) >> 8;
					var7 += var10 & 255;
				}
			}
			return (var5 / 9 & 255) << 16 | (var6 / 9 & 255) << 8 | var7 / 9 & 255;
		default:
			return 0xffffff;
		}
	}

	@Override
	public int damageDropped(int i) {
		return 4;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	public void grow(World world, int i, int j, int k, Random random) {
		if (!world.isRemote) {
			int id = world.getBlockId(i, j + 1, k);
			if (id != blockID && id != zei_Ids.crink) {
				planterCheck(world, i, j, k);
				if (world.canBlockSeeTheSky(i, j, k)) {
					if (random.nextInt(4) == 0) {
						payload(world, i, j, k, random);
					} else {
						world.setBlock(i, j + 1, k, blockID, 1, 3);
					}
				} else {
					if (random.nextInt(4) == 0) {
						switch (random.nextInt(4)) {
						case 1:
							if (world.getBlockId(i - 1, j, k) != blockID)
								world.setBlock(i - 1, j, k, blockID, 2, 3);
							break;
						case 2:
							if (world.getBlockId(i + 1, j, k) != blockID)
								world.setBlock(i + 1, j, k, blockID, 2, 3);
							break;
						case 3:
							if (world.getBlockId(i, j, k - 1) != blockID)
								world.setBlock(i, j, k - 1, blockID, 2, 3);
							break;
						default:
							if (world.getBlockId(i, j, k + 1) != blockID)
								world.setBlock(i, j, k + 1, blockID, 2, 3);
							break;
						}
					} else {
						world.setBlock(i, j + 1, k, blockID);
					}
				}
			}
		}
	}

	public boolean infect(World world, int i, int j, int k, boolean am) {
		Material th = world.getBlockMaterial(i, j, k);
		if (th == Material.wood || th == Material.vine || th == Material.plants || th == Material.leaves) {
			world.setBlock(i, j, k, blockID, 3, 3);
			return true;
		}
		return am ? zei_BlockArch.place(world, i, j, k) : false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	public void onBlockPlaced(World world, int i, int j, int k, int l) {
	}

	public void onBlockRemoval(World world, int i, int j, int k) {
		if (world.getBlockId(i, j + 1, k) == zei_Ids.crink) {
			world.setBlock(i, j + 1, k, 0);
			dropBlockAsItem_do(world, i, j + 1, k, new ItemStack(zei_Ids.crink, 1, 0));
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
		checkBlockCoordValid(world, i, j, k);
		// System.out.println("neighbor: "+l);
	}

	public void payload(World world, int i, int j, int k, Random random) {
		int ii = random.nextInt(5);
		if (ii == 0) {
			ii = 10;
			int it = 0;
			int count = 0;
			while (world.getBlockId(i, j - it, k) == blockID) {
				it++;
				if (world.getBlockMetadata(i, j - it, k) == 3)
					count++;
			}
			if (count > 0) {
				for (int n = 0; n < it; n++) {
					world.spawnEntityInWorld(new zei_EntityGolemPure(world, i + 0.5F, (float) j + 1, k + 0.5F, zei_Ids.arch, 8, 0));
				}
			} else {
				world.spawnEntityInWorld(new zei_EntityGolem(world, i + 0.5F, (float) j + 1, k + 0.5F, zei_Ids.arch, 2, 0));
			}
		}
		world.setBlock(i, j + 1, k, zei_Ids.crink, ii, 3);
	}

	public boolean planterCheck(World world, int i, int j, int k) {
		boolean b1 = infect(world, i - 1, j, k, true);
		boolean b2 = infect(world, i + 1, j, k, true);
		boolean b3 = infect(world, i, j, k - 1, true);
		boolean b4 = infect(world, i, j, k + 1, true);
		boolean b5 = infect(world, i, j + 1, k, false);
		return b1 || b2 || b3 || b4 || b5;
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 1;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k) {
		boolean bound[] = new boolean[6];
		int id = iblockaccess.getBlockId(i, j - 1, k);
		if (id != blockID) {
			bound[0] = iblockaccess.getBlockId(i - 1, j, k) != 0;
			bound[1] = iblockaccess.getBlockId(i, j, k - 1) != 0;
			bound[2] = iblockaccess.getBlockId(i + 1, j, k) != 0;
			bound[3] = iblockaccess.getBlockId(i, j, k + 1) != 0;
			bound[4] = iblockaccess.getBlockId(i, j + 1, k) != 0;
			// boolean any=bound[0]||bound[1]||bound[2]||bound[3];
			setBlockBounds(bound[0] ? 0 : 0.25F, 0.0F, bound[1] ? 0 : 0.25F, bound[2] ? 1 : 0.75F, bound[4] ? 1 : 0.5f, bound[3] ? 1 : 0.75F);
		} else {
			bound[0] = iblockaccess.getBlockId(i - 1, j, k) == blockID && iblockaccess.getBlockId(i - 1, j - 1, k) == 0;
			bound[1] = iblockaccess.getBlockId(i, j, k - 1) == blockID && iblockaccess.getBlockId(i, j - 1, k - 1) == 0;
			bound[2] = iblockaccess.getBlockId(i + 1, j, k) == blockID && iblockaccess.getBlockId(i + 1, j - 1, k) == 0;
			bound[3] = iblockaccess.getBlockId(i, j, k + 1) == blockID && iblockaccess.getBlockId(i, j - 1, k + 1) == 0;
			setBlockBounds(bound[0] ? 0 : 0.25F, 0.0F, bound[1] ? 0 : 0.25F, bound[2] ? 1 : 0.75F, 1f, bound[3] ? 1 : 0.75F);
			// setBlockBounds(0.25F, 0.0F, 0.25F, .75F, 1, .75F);
		}
	}

	@Override
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0.25F, 0.25F, 0.25F, .75F, 0.75f, .75F);
	}

	/*
	 * public void onNeighborBlockChange(World world, int i, int j, int k, int
	 * l){ grow(world,i,j,k); }
	 */
	@Override
	public void updateTick(World world, int i, int j, int k, Random random) {
		if (world.getBlockMetadata(i, j, k) != 4)
			grow(world, i, j, k, random);
	}

	protected final void checkBlockCoordValid(World world, int i, int j, int k) {
		if (!canBlockStay(world, i, j, k)) {
			dropBlockAsItem(world, i, j, k, 0, 0);
			world.setBlock(i, j, k, 0);
		}
	}

	public static void place(World world, int i, int j, int k) {
		int id = world.getBlockId(i, j, k);
		if (id == 3) {
			world.setBlock(i, j, k, zei_Ids.arch, 4, 3);
		} else if (id == 1 || id == 4) {
			world.setBlock(i, j, k, zei_Ids.arch, 1, 3);
		} else if (id == 8 || id == 9) {
			world.setBlock(i, j, k, zei_Ids.arch, 2, 3);
		} else if (id == 2) {
			world.setBlock(i, j, k, zei_Ids.arch, 3, 3);
		} else if (id != zei_Ids.arch) {
			world.setBlock(i, j, k, zei_Ids.arch, 0, 3);
		}
	}
}
