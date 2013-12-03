package zeitgeist.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import zeitgeist.common.mod_Zeitgeist;
import zeitgeist.common.tile.zei_TileEntityLatch;

public class zei_BlockLatch extends BlockContainer {
	private Random random;

	protected zei_BlockLatch(int i) {
		super(i, Material.iron);
		blockIndexInTexture = 22;
		float F = 0.25f;
		setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.875f, 0.875F);
		random = new Random();
	}

	/*
	 * public void onBlockPlaced(World world, int i, int j, int k, int l) {
	 * world.setBlockMetadataWithNotify(i, j, k, l); }
	 */
	public boolean blockActivated(World world, int par2, int par3, int par4, EntityPlayer ep) {
		if (world.isRemote) {
			return true;
		}
		zei_TileEntityLatch latch = (zei_TileEntityLatch) world.getBlockTileEntity(par2, par3, par4);
		if (latch != null) {
			mod_Zeitgeist.proxy.toteGui(ep, latch);
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new zei_TileEntityLatch();
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	public void onBlockRemoval(World world, int par2, int par3, int par4) {
		zei_TileEntityLatch latch = (zei_TileEntityLatch) world.getBlockTileEntity(par2, par3, par4);
		if (latch != null) {
			for (int i = 0; i < latch.getSizeInventory(); i++) {
				ItemStack itemstack = latch.getStackInSlot(i);
				if (itemstack != null) {
					float f = random.nextFloat() * 0.8F + 0.1F;
					float f1 = random.nextFloat() * 0.8F + 0.1F;
					float f2 = random.nextFloat() * 0.8F + 0.1F;
					while (itemstack.stackSize > 0) {
						int j = random.nextInt(21) + 10;
						if (j > itemstack.stackSize) {
							j = itemstack.stackSize;
						}
						itemstack.stackSize -= j;
						EntityItem entityitem = new EntityItem(world, par2 + f, par3 + f1, par4 + f2, new ItemStack(itemstack.itemID, j, itemstack.getItemDamage()));
						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
						}
						float f3 = 0.05F;
						entityitem.motionX = (float) random.nextGaussian() * f3;
						entityitem.motionY = (float) random.nextGaussian() * f3 + 0.2F;
						entityitem.motionZ = (float) random.nextGaussian() * f3;
						world.spawnEntityInWorld(entityitem);
					}
				}
			}
		}
		super.onBlockRemoval(world, par2, par3, par4);
	}

	@Override
	public void onNeighborBlockChange(World world, int par2, int par3, int par4, int par5) {
		if (par5 > 0 && Block.blocksList[par5].canProvidePower()) {
			boolean flag = world.isBlockIndirectlyGettingPowered(par2, par3, par4) || world.isBlockIndirectlyGettingPowered(par2, par3 + 1, par4);
			if (flag) {
				world.scheduleBlockUpdate(par2, par3, par4, blockID, tickRate());
			}
		}
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	public int tickRate() {
		return 4;
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		if (!par1World.isRemote && (par1World.isBlockIndirectlyGettingPowered(par2, par3, par4) || par1World.isBlockIndirectlyGettingPowered(par2, par3 + 1, par4))) {
			dispenseItem(par1World, par2, par3, par4, par5Random);
		}
	}

	private void dispenseItem(World world, int x, int y, int z, Random par5Random) {
		int i = world.getBlockMetadata(x, y, z);
		int j = 0;
		int k = 0;
		if (i == 3) {
			k = 1;
		} else if (i == 2) {
			k = -1;
		} else if (i == 5) {
			j = 1;
		} else {
			j = -1;
		}
		zei_TileEntityLatch latch = (zei_TileEntityLatch) world.getBlockTileEntity(x, y, z);
		if (latch != null) {
			int id = latch.getNextStackFromInventory();
			double d = x + j * 0.59999999999999998D + 0.5D;
			double d1 = y + 0.5D;
			double d2 = z + k * 0.59999999999999998D + 0.5D;
			ItemStack itemstack = null;
			if (id == -1) {
				world.playAuxSFX(1001, x, y, z, 0);
			} else {
				itemstack = latch.dispenserContents[id];
				if (itemstack.itemID < 256) {
					Block b = Block.blocksList[itemstack.itemID];
					if (world.getBlockId(x, y - 1, z) == 0 && b.canPlaceBlockAt(world, x, y - 1, z)) {
						latch.decrStackSize(id, 1);
						world.setBlock(x, y - 1, z, itemstack.itemID);
					} else if (world.getBlockId(x - 1, y, z) == 0 && b.canPlaceBlockAt(world, x - 1, y, z)) {
						latch.decrStackSize(id, 1);
						world.setBlock(x - 1, y, z, itemstack.itemID);
					} else if (world.getBlockId(x, y, z - 1) == 0 && b.canPlaceBlockAt(world, x, y, z - 1)) {
						latch.decrStackSize(id, 1);
						world.setBlock(x, y, z - 1, itemstack.itemID);
					} else if (world.getBlockId(x + 1, y, z) == 0 && b.canPlaceBlockAt(world, x + 1, y, z)) {
						latch.decrStackSize(id, 1);
						world.setBlock(x + 1, y, z, itemstack.itemID);
					} else if (world.getBlockId(x, y, z + 1) == 0 && b.canPlaceBlockAt(world, x, y, z + 1)) {
						latch.decrStackSize(id, 1);
						world.setBlock(x, y, z + 1, itemstack.itemID);
					} else if (world.getBlockId(x, y + 1, z) == 0 && b.canPlaceBlockAt(world, x, y + 1, z)) {
						latch.decrStackSize(id, 1);
						world.setBlock(x, y + 1, z, itemstack.itemID);
					}
				}
				/*
				 * if (itemstack.itemID == Item.arrow.shiftedIndex) {
				 * EntityArrow entityarrow = new EntityArrow(par1World, d, d1,
				 * d2); entityarrow.setArrowHeading(j, 0.10000000149011612D, k,
				 * 1.1F, 6F); entityarrow.doesArrowBelongToPlayer = true;
				 * par1World.spawnEntityInWorld(entityarrow);
				 * par1World.playAuxSFX(1002, par2, par3, par4, 0); } else if
				 * (itemstack.itemID == Item.egg.shiftedIndex) { EntityEgg
				 * entityegg = new EntityEgg(par1World, d, d1, d2);
				 * entityegg.setThrowableHeading(j, 0.10000000149011612D, k,
				 * 1.1F, 6F); par1World.spawnEntityInWorld(entityegg);
				 * par1World.playAuxSFX(1002, par2, par3, par4, 0); } else if
				 * (itemstack.itemID == Item.snowball.shiftedIndex) {
				 * EntitySnowball entitysnowball = new EntitySnowball(par1World,
				 * d, d1, d2); entitysnowball.setThrowableHeading(j,
				 * 0.10000000149011612D, k, 1.1F, 6F);
				 * par1World.spawnEntityInWorld(entitysnowball);
				 * par1World.playAuxSFX(1002, par2, par3, par4, 0); } else if
				 * (itemstack.itemID == Item.potion.shiftedIndex &&
				 * ItemPotion.isSplash(itemstack.getItemDamage())) {
				 * EntityPotion entitypotion = new EntityPotion(par1World, d,
				 * d1, d2, itemstack.getItemDamage());
				 * entitypotion.setThrowableHeading(j, 0.10000000149011612D, k,
				 * 1.375F, 3F); par1World.spawnEntityInWorld(entitypotion);
				 * par1World.playAuxSFX(1002, par2, par3, par4, 0); } else if
				 * (itemstack.itemID == Item.expBottle.shiftedIndex) {
				 * EntityExpBottle entityexpbottle = new
				 * EntityExpBottle(par1World, d, d1, d2);
				 * entityexpbottle.setThrowableHeading(j, 0.10000000149011612D,
				 * k, 1.375F, 3F);
				 * par1World.spawnEntityInWorld(entityexpbottle);
				 * par1World.playAuxSFX(1002, par2, par3, par4, 0); } else if
				 * (itemstack.itemID == Item.monsterPlacer.shiftedIndex) {
				 * ItemMonsterPlacer.func_48440_a(par1World,
				 * itemstack.getItemDamage(), d + (double)j *
				 * 0.29999999999999999D, d1 - 0.29999999999999999D, d2 +
				 * (double)k * 0.29999999999999999D); par1World.playAuxSFX(1002,
				 * par2, par3, par4, 0); } else if (itemstack.itemID ==
				 * Item.fireballCharge.shiftedIndex) { EntitySmallFireball
				 * entitysmallfireball = new EntitySmallFireball(par1World, d +
				 * (double)j * 0.29999999999999999D, d1, d2 + (double)k *
				 * 0.29999999999999999D, (double)j + par5Random.nextGaussian() *
				 * 0.050000000000000003D, par5Random.nextGaussian() *
				 * 0.050000000000000003D, (double)k + par5Random.nextGaussian()
				 * * 0.050000000000000003D);
				 * par1World.spawnEntityInWorld(entitysmallfireball);
				 * par1World.playAuxSFX(1009, par2, par3, par4, 0); } else {
				 * EntityItem entityitem = new EntityItem(par1World, d, d1 -
				 * 0.29999999999999999D, d2, itemstack); double d3 =
				 * par5Random.nextDouble() * 0.10000000000000001D +
				 * 0.20000000000000001D; entityitem.motionX = (double)j * d3;
				 * entityitem.motionY = 0.20000000298023224D; entityitem.motionZ
				 * = (double)k * d3; entityitem.motionX +=
				 * par5Random.nextGaussian() * 0.0074999998323619366D * 6D;
				 * entityitem.motionY += par5Random.nextGaussian() *
				 * 0.0074999998323619366D * 6D; entityitem.motionZ +=
				 * par5Random.nextGaussian() * 0.0074999998323619366D * 6D;
				 * par1World.spawnEntityInWorld(entityitem);
				 * par1World.playAuxSFX(1000, par2, par3, par4, 0); }
				 */
				world.playAuxSFX(2000, x, y, z, j + 1 + (k + 1) * 3);
			}
		}
	}
}
