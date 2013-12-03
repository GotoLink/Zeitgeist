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
import zeitgeist.common.zei_Ids;
import zeitgeist.common.tile.zei_TileEntityCannon;

public class zei_BlockCannon extends BlockContainer {
	private Random rando = new Random();

	protected zei_BlockCannon(int i) {
		super(i, 4, Material.wood);
		// setBlockBounds(f, f, f, 1 - f, 1 - f, 1 - f);
	}

	public boolean blockActivated(World world, int i, int j, int k, EntityPlayer ep) {
		if (world.isRemote) {
			return true;
		}
		zei_TileEntityCannon cannon = (zei_TileEntityCannon) world.getBlockTileEntity(i, j, k);
		// cannon.Fire();
		if (cannon != null) {
			ItemStack is = ep.getCurrentEquippedItem();
			if (is != null && (is.itemID == 259 || is.itemID == 50 || is.itemID == 76)) {
				cannon.Fire();
			} else if (is != null && is.itemID == zei_Ids.craftSet1 && is.getItemDamage() == 23) {
				if (cannon.setMode((short) 1)) {
					ep.inventory.decrStackSize(ep.inventory.currentItem, 1);
				}
			} else if (is != null && is.itemID == zei_Ids.soulCore) {
				if (is.getItemDamage() == 0 && cannon.setMode((short) 2)) {
					ep.inventory.decrStackSize(ep.inventory.currentItem, 1);
				} else if (is.getItemDamage() == 5 && cannon.setMode((short) 3)) {
					ep.inventory.decrStackSize(ep.inventory.currentItem, 1);
				} else if (is.getItemDamage() == 6 && cannon.setMode((short) 4)) {
					ep.inventory.decrStackSize(ep.inventory.currentItem, 1);
				}
			} else {
				cannon.alert();
				mod_Zeitgeist.proxy.displayGUICannon(ep, cannon);
			}
		}
		return true;
	}

	@Override
	public boolean canCollideCheck(int par1, boolean par2) {
		return !par2;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new zei_TileEntityCannon();
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onBlockClicked(World world, int i, int j, int k, EntityPlayer ep) {
		if (world.isRemote) {
			return;
		}
		zei_TileEntityCannon cannon = (zei_TileEntityCannon) world.getBlockTileEntity(i, j, k);
		// //cannon.adjust();
		if (cannon.rotateYaw(ep)) {
			cannon.adjust(ep.isSneaking() ? -5 : 5);
		}
		// cannon.Fire();
	}

	public void onBlockRemoval(World par1World, int par2, int par3, int par4) {
		zei_TileEntityCannon var5 = (zei_TileEntityCannon) par1World.getBlockTileEntity(par2, par3, par4);
		if (var5 != null) {
			for (int var6 = 0; var6 < var5.getSizeInventory(); ++var6) {
				ItemStack var7 = var5.getStackInSlot(var6);
				if (var7 != null) {
					float var8 = this.rando.nextFloat() * 0.8F + 0.1F;
					float var9 = this.rando.nextFloat() * 0.8F + 0.1F;
					float var10 = this.rando.nextFloat() * 0.8F + 0.1F;
					while (var7.stackSize > 0) {
						int var11 = this.rando.nextInt(21) + 10;
						if (var11 > var7.stackSize) {
							var11 = var7.stackSize;
						}
						var7.stackSize -= var11;
						EntityItem var12 = new EntityItem(par1World, par2 + var8, par3 + var9, par4 + var10, new ItemStack(var7.itemID, var11, var7.getItemDamage()));
						if (var7.hasTagCompound()) {
							var12.getEntityItem().setTagCompound((NBTTagCompound) var7.getTagCompound().copy());
						}
						float var13 = 0.05F;
						var12.motionX = (float) this.rando.nextGaussian() * var13;
						var12.motionY = (float) this.rando.nextGaussian() * var13 + 0.2F;
						var12.motionZ = (float) this.rando.nextGaussian() * var13;
						par1World.spawnEntityInWorld(var12);
					}
				}
			}
			if (var5.Mode > 0) {
				EntityItem itm = new EntityItem(par1World, par2, par3, par4, new ItemStack(zei_Ids.craftSet1, 1, 23));
				par1World.spawnEntityInWorld(itm);
				if (var5.Mode > 1) {
					itm = new EntityItem(par1World, par2, par3, par4, new ItemStack(zei_Ids.soulCore, 1, var5.Mode == 3 ? 5 : var5.Mode == 4 ? 6 : 0));
					par1World.spawnEntityInWorld(itm);
				}
			}
		}
		super.onBlockRemoval(par1World, par2, par3, par4);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
}
