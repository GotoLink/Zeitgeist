package zeitgeist.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import zeitgeist.common.tile.zei_TileEntityDuoFurnace;

public class zei_BlockDuoFurnace extends BlockContainer {
	/** True if this is an active furnace, false if idle */
	// private final boolean isActive;
	/**
	 * This flag is used to prevent the furnace inventory to be dropped upon
	 * block removal, is used internally when the furnace block changes from
	 * idle to active and vice-versa.
	 */
	private static boolean keepFurnaceInventory = false;
	/**
	 * Is the random generator used by furnace to drop the inventory contents in
	 * random directions.
	 */
	private Random furnaceRand;

	protected zei_BlockDuoFurnace(int par1) {
		super(par1, Material.rock);
		furnaceRand = new Random();
		// isActive = par2;
		blockIndexInTexture = 45;
	}

	/**
	 * Called upon block activation (left or right click on the block.). The
	 * three integers represent x,y,z of the block.
	 */
	public boolean blockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {
		if (par1World.isRemote) {
			return true;
		}
		int m = par1World.getBlockMetadata(par2, par3, par4);
		if (m > 9) {
			return false;
		}
		zei_TileEntityDuoFurnace tileentityfurnace = (zei_TileEntityDuoFurnace) par1World.getBlockTileEntity(par2, par3, par4);
		if (tileentityfurnace != null) {
			par5EntityPlayer.displayGUIFurnace(tileentityfurnace);
		}
		return true;
	}

	@Override
	public boolean canPlaceBlockAt(World w, int i, int j, int k) {
		int ii[] = neighborDuos(w, i, j, k);
		if (ii[1] == 1) {
			return true;
		} else if (ii[0] == 0) {
			return true;
		}
		return false;
	}

	@Override
	public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k) {
		return 0xccbbbb;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new zei_TileEntityDuoFurnace();
	}

	@Override
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		int i = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
		if (par5 == 1 || i == 10) {
			return blockIndexInTexture + 17;
		}
		if (par5 == 0) {
			return blockIndexInTexture + 17;
		}
		boolean bb = false;
		if (i >= 6) {
			bb = true;
			i -= 4;
		}
		if (par5 != i) {
			return blockIndexInTexture;
		}
		if (bb) {
			return blockIndexInTexture + 16;
		} else {
			return blockIndexInTexture - 1;
		}
	}

	/**
	 * Returns the block texture based on the side being looked at. Args: side
	 */
	@Override
	public Icon getBlockTextureFromSide(int par1) {
		if (par1 == 1) {
			return blockIndexInTexture + 17;
		}
		if (par1 == 0) {
			return blockIndexInTexture + 17;
		}
		if (par1 == 3) {
			return blockIndexInTexture - 1;
		} else {
			return blockIndexInTexture;
		}
	}

	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 */
	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		super.onBlockAdded(par1World, par2, par3, par4);
		setDefaultDirection(par1World, par2, par3, par4);
	}

	/**
	 * Called when the block is placed in the world.
	 */
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving) {
		int i = MathHelper.floor_double((par5EntityLiving.rotationYaw * 4F) / 360F + 0.5D) & 3;
		int ii[] = neighborDuos(par1World, par2, par3, par4);
		if (ii[1] >= 1) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 10, 3);
			return;
		}
		if (i == 0) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 3);
		}
		if (i == 1) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 3);
		}
		if (i == 2) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 3);
		}
		if (i == 3) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 3);
		}
	}

	/**
	 * Called whenever the block is removed.
	 */
	public void onBlockRemoval(World par1World, int par2, int par3, int par4) {
		if (!keepFurnaceInventory) {
			TileEntityFurnace tileentityfurnace = (TileEntityFurnace) par1World.getBlockTileEntity(par2, par3, par4);
			if (tileentityfurnace != null) {
				label0: for (int i = 0; i < tileentityfurnace.getSizeInventory(); i++) {
					ItemStack itemstack = tileentityfurnace.getStackInSlot(i);
					if (itemstack == null) {
						continue;
					}
					float f = furnaceRand.nextFloat() * 0.8F + 0.1F;
					float f1 = furnaceRand.nextFloat() * 0.8F + 0.1F;
					float f2 = furnaceRand.nextFloat() * 0.8F + 0.1F;
					do {
						if (itemstack.stackSize <= 0) {
							continue label0;
						}
						int j = furnaceRand.nextInt(21) + 10;
						if (j > itemstack.stackSize) {
							j = itemstack.stackSize;
						}
						itemstack.stackSize -= j;
						EntityItem entityitem = new EntityItem(par1World, par2 + f, par3 + f1, par4 + f2, new ItemStack(itemstack.itemID, j, itemstack.getItemDamage()));
						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
						}
						float f3 = 0.05F;
						entityitem.motionX = (float) furnaceRand.nextGaussian() * f3;
						entityitem.motionY = (float) furnaceRand.nextGaussian() * f3 + 0.2F;
						entityitem.motionZ = (float) furnaceRand.nextGaussian() * f3;
						par1World.spawnEntityInWorld(entityitem);
					} while (true);
				}
			}
		}
		super.onBlockRemoval(par1World, par2, par3, par4);
	}

	/**
	 * A randomly called display update to be able to add particles or other
	 * items for display
	 */
	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		int i = par1World.getBlockMetadata(par2, par3, par4);
		if (i < 6 || i > 9) {
			return;
		}
		// if (!isActive)
		// {
		// return;
		// }
		float f = par2 + 0.5F;
		float f1 = par3 + 0.0F + (par5Random.nextFloat() * 6F) / 16F;
		float f2 = par4 + 0.5F;
		float f3 = 0.52F;
		float f4 = par5Random.nextFloat() * 0.6F - 0.3F;
		if (i == 8) {
			par1World.spawnParticle("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			par1World.spawnParticle("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
		} else if (i == 9) {
			par1World.spawnParticle("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			par1World.spawnParticle("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
		} else if (i == 6) {
			par1World.spawnParticle("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
			par1World.spawnParticle("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
		} else if (i == 7) {
			par1World.spawnParticle("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
			par1World.spawnParticle("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
		}
	}

	/**
	 * set a blocks direction
	 */
	private void setDefaultDirection(World par1World, int par2, int par3, int par4) {
		if (par1World.isRemote) {
			return;
		}
		int i = par1World.getBlockId(par2, par3, par4 - 1);
		int j = par1World.getBlockId(par2, par3, par4 + 1);
		int k = par1World.getBlockId(par2 - 1, par3, par4);
		int l = par1World.getBlockId(par2 + 1, par3, par4);
		byte byte0 = 3;
		if (Block.opaqueCubeLookup[i] && !Block.opaqueCubeLookup[j]) {
			byte0 = 3;
		}
		if (Block.opaqueCubeLookup[j] && !Block.opaqueCubeLookup[i]) {
			byte0 = 2;
		}
		if (Block.opaqueCubeLookup[k] && !Block.opaqueCubeLookup[l]) {
			byte0 = 5;
		}
		if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[k]) {
			byte0 = 4;
		}
		par1World.setBlockMetadataWithNotify(par2, par3, par4, byte0, 3);
	}

	public static boolean g1(World w, int i, int j, int k) {
		return w.getBlockId(i, j, k) == 42;// zei_Ids.duoFurnace;
	}

	public static boolean g2(World w, int i, int j, int k) {
		return w.getBlockMetadata(i, j, k) < 10;
	}

	public static int[] neighborDuos(World w, int i, int j, int k) {
		int c = 0;
		// boolean b[] = new boolean[26];
		int mx = 0;
		int bx = 0;
		// System.out.println("no "+max);
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				for (int z = -1; z <= 1; z++) {
					System.out.println("uh " + x + "," + y + "," + z);
					if (!(x == 0 && y == 0 && z == 0)) {
						// b[c]
						if (g1(w, i + x, j + y, k + z)) {
							if (g2(w, i + x, j + y, k + z)) {
								mx++;
							} else {
								bx++;
							}
						}
						c++;
					}
				}
			}
		}
		return new int[] { bx, mx };
	}

	/**
	 * Update which block ID the furnace is using depending on whether or not it
	 * is burning
	 */
	public static void updateFurnaceBlockState(boolean par0, World par1World, int par2, int par3, int par4) {
		int i = par1World.getBlockMetadata(par2, par3, par4);
		// TileEntity tileentity = par1World.getBlockTileEntity(par2, par3,
		// par4);
		// keepFurnaceInventory = true;
		if (par0) {
			if (i < 6) {
				i += 4;
			}
			par1World.setBlockMetadataWithNotify(par2, par3, par4, i, 3);
		} else {
			if (i >= 6) {
				i -= 4;
			}
			par1World.setBlockMetadataWithNotify(par2, par3, par4, i, 3);
		}
	}
}
