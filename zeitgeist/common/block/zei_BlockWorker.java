package zeitgeist.common.block;

import java.util.Random;

import zeitgeist.common.zei_BeaconManager;
import zeitgeist.common.zei_Ids;
import zeitgeist.common.tile.zei_TileEntityBeacon;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class zei_BlockWorker extends BlockContainer {
	protected zei_BlockWorker(int par1) {
		super(par1, Material.piston);
		float f = 0.0625F;
		setBlockBounds(f * 2f, 0.0F, f * 2f, 14f * f, 14f * f, 14f * f);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public int idDropped(int par1, Random par2Random, int par3) {
		return zei_Ids.itemWorker;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getRenderType() {
		return zei_Ids.workerBlockRenderId;
	}

	public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2,
			int par3, int i) {
		return true;
	}

	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		return par1World.isBlockOpaqueCube(par2, par3 - 1, par4);
	}

	public void onNeighborBlockChange(World par1World, int par2, int par3,
			int par4, int par5) {
		canSnowStay(par1World, par2, par3, par4);
	}

	private boolean canSnowStay(World par1World, int par2, int par3, int par4) {
		if (!canPlaceBlockAt(par1World, par2, par3, par4)) {
			dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(
					zei_Ids.itemWorker, 1, 0));
			par1World.setBlock(par2, par3, par4, 0);
			return false;
		} else {
			return true;
		}
	}

	/*
	 * public void onEntityCollidedWithBlock(World par1World, int i, int j, int
	 * k, Entity ent) { if (par1World.isRemote || ent.isInWater() ||
	 * par1World.canLightningStrikeAt(i, j + 1, k)) { return; }
	 * 
	 * if (ent instanceof EntityItem) { EntityItem ei = (EntityItem)ent;
	 * 
	 * if (ei.item.itemID == zei_Ids.soulCore && ei.item.getItemDamage()==0 &&
	 * ei.item.stackSize > 0) { if (ei.item.stackSize == 1) { ei.setDead(); }
	 * 
	 * ei.item.stackSize--; EntityPlayer ep = par1World.getClosestPlayer(i, j,
	 * k, -1); String out = "";
	 * 
	 * if (ep != null) { out = ep.username; }
	 * 
	 * par1World.setBlockWithNotify(i, j, k, 0);
	 * par1World.spawnEntityInWorld(new zei_EntityWorker(par1World, i + 0.5f, j,
	 * k + 0.5f, out)); } } }
	 */
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new zei_TileEntityBeacon();
	}

	public void onBlockAdded(World world, int i, int j, int k) {
		int ii = zei_BeaconManager.addBeacon(world, i, j, k);
		zei_TileEntityBeacon beacon = (zei_TileEntityBeacon) world
				.getBlockTileEntity(i, j, k);
		beacon.numeral = ii;
		beacon.mode = 9; // worker
	}

	public void onBlockRemoval(World par1World, int par2, int par3, int par4) {
		int i = par1World.getBlockMetadata(par2, par3, par4);
		if (i > 0) {
			par1World.notifyBlocksOfNeighborChange(par2, par3, par4, blockID);
			par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4,
					blockID);
		}
		super.onBlockRemoval(par1World, par2, par3, par4);
	}

	public void setBlockBoundsForItemRender() {
		float f = 0.5F;
		float f1 = 0.125F;
		float f2 = 0.5F;
		setBlockBounds(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1,
				0.5F + f2);
	}

	public int getMobilityFlag() {
		return 2;
	}
}
