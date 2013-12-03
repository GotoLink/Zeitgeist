package zeitgeist.common.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import zeitgeist.common.mod_Zeitgeist;
import zeitgeist.common.zei_BeaconManager;
import zeitgeist.common.zei_Ids;
import zeitgeist.common.tile.zei_TileEntityLatch;

public class zei_BlockTote extends BlockContainer {
	private Random random;

	protected zei_BlockTote(int i) {
		super(i, Material.piston);
		blockIndexInTexture = 1;
		float F = 0.25f;
		setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.5625f, 0.875F);
		random = new Random();
	}

	public boolean blockActivated(World world, int par2, int par3, int par4, EntityPlayer ep) {
		if (world.isRemote) {
			return true;
		}
		ItemStack it = ep.inventory.getCurrentItem();
		if (it != null && it.itemID == zei_Ids.soulCore) {
			return false;
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
	public int getRenderType() {
		return zei_Ids.toteBlockRenderId;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return zei_Ids.itemTote;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k) {
		int ii = zei_BeaconManager.addBeacon(world, i, j, k);
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
	public boolean renderAsNormalBlock() {
		return false;
	}
}
