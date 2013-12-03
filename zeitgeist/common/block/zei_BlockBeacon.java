package zeitgeist.common.block;

import java.util.Random;

import zeitgeist.common.zei_BeaconManager;
import zeitgeist.common.zei_Ids;
import zeitgeist.common.tile.zei_TileEntityBeacon;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class zei_BlockBeacon extends BlockContainer {
	protected zei_BlockBeacon(int i) {
		super(i, 6, Material.circuits);
		setBlockBounds(6f / 16f, 0.0F, 6f / 16f, 10f / 16f, 0.875f, 10f / 16f);
	}

	public void onBlockAdded(World world, int i, int j, int k) {
		int ii = zei_BeaconManager.addBeacon(world, i, j, k);
		zei_TileEntityBeacon beacon = (zei_TileEntityBeacon) world
				.getBlockTileEntity(i, j, k);
		beacon.numeral = ii;
		// zei_BeaconManager.printAll(world);
	}

	public int getBlockTextureFromSideAndMetadata(int i, int j) {
		if (j == 0) {
			return 64;
		} else if (j == 1) {
			return 177;
		}
		return 129;
	}

	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase e) {
		if (e instanceof EntityPlayer) {
			select(world, i, j, k, (EntityPlayer) e);
		}
	}

	public void onBlockRemoval(World world, int i, int j, int k) {
		zei_TileEntityBeacon beacon = (zei_TileEntityBeacon) world
				.getBlockTileEntity(i, j, k);
		zei_BeaconManager.removeBeacon(world, beacon.numeral);
		// this.onBlockClicked(world, i, j, k, entityplayer)
	}

	public int idDropped(int par1, Random par2Random, int par3) {
		return zei_Ids.itemBeacon;
	}

	public boolean blockActivated(World world, int i, int j, int k,
			EntityPlayer ep) {
		select(world, i, j, k, ep);
		return false;
	}

	public void select(World world, int i, int j, int k, EntityPlayer ep) {
		zei_TileEntityBeacon beacon = (zei_TileEntityBeacon) world
				.getBlockTileEntity(i, j, k);
		zei_BeaconManager.select(ep, beacon.numeral);
		ItemStack is = ep.inventory.getCurrentItem();
		if (is != null && is.itemID == Item.stick.itemID) {
			beacon.mode = 2;
			world.setBlockMetadataWithNotify(i, j, k, 2, 3);
			world.markBlockForUpdate(i, j, k);
		} else if (is != null && is.itemID == Item.compass.itemID) {
			// beacon.mode=2;
			// world.setBlockMetadataWithNotify(i, j, k, 2);
			// world.markBlockAsNeedsUpdate(i, j, k);
			zei_BeaconManager.alertPlayerOnBots(world, ep);
		} else if (beacon.mode != 0) {
			beacon.mode = 0;
			world.setBlockMetadataWithNotify(i, j, k, 0, 3);
			world.markBlockForUpdate(i, j, k);
		} else {
			beacon.mode = 1;
			world.setBlockMetadataWithNotify(i, j, k, 1, 3);
			world.markBlockForUpdate(i, j, k);
		}
	}

	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new zei_TileEntityBeacon();
	}
}
