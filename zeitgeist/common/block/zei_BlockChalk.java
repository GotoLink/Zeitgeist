package zeitgeist.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import zeitgeist.common.mod_Zeitgeist;
import zeitgeist.common.zei_ChalkLogic;
import zeitgeist.common.zei_Ids;

public class zei_BlockChalk extends Block {
	public zei_BlockChalk(int i, int j) {
		super(i, j, Material.circuits);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
	}

	public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer) {
		if (world.isRemote) {
			return true;
		}
		if (entityplayer.getCurrentEquippedItem() != null) {
			if (entityplayer.getCurrentEquippedItem().itemID == zei_Ids.chalk2) {
				return false;
			}
		}
		zei_ChalkLogic.translate(world, i, j, k, entityplayer);
		return true;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int i, int j, int k) {
		return world.isBlockNormalCube(i, j - 1, k);
	}

	public int getBlockTextureFromSideAndMetadata(int i, int j) {
		return blockIndexInTexture;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		return null;
	}

	@Override
	public int getRenderType() {
		return zei_Ids.chalkRendererId;
	}

	@Override
	public int idDropped(int i, Random random, int j) {
		return zei_Ids.chalk2;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k) {
		super.onBlockAdded(world, i, j, k);
		if (world.isRemote) {
			return;
		}
		notifyWireNeighborsOfNeighborChange(world, i - 1, j, k);
		notifyWireNeighborsOfNeighborChange(world, i + 1, j, k);
		notifyWireNeighborsOfNeighborChange(world, i, j, k - 1);
		notifyWireNeighborsOfNeighborChange(world, i, j, k + 1);
	}

	public void onBlockRemoval(World world, int i, int j, int k) {
		super.onBlockRemoval(world, i, j, k);
		if (world.isRemote) {
			return;
		}
		int meta = world.getBlockMetadata(i, j, k);
		if (meta > 0) {
			if (meta > 1) {
				EntityItem entityitem = new EntityItem(world, i + 0.5f, j + 0.5f, k + 0.5f, new ItemStack(zei_Ids.chalk2, meta - 1, 0));
				entityitem.delayBeforeCanPickup = 10;
				world.spawnEntityInWorld(entityitem);
			}
			notifyWireNeighborsOfNeighborChange(world, i - 1, j, k);
			notifyWireNeighborsOfNeighborChange(world, i + 1, j, k);
			notifyWireNeighborsOfNeighborChange(world, i, j, k - 1);
			notifyWireNeighborsOfNeighborChange(world, i, j, k + 1);
		} else {
			mod_Zeitgeist.proxy.fwoo(world, i, j, k);
			// zei_Universal.makeParticle(new
			// EntityLargeExplodeFX(zei_Universal.mc.renderEngine, world, i +
			// 0.5, j + 1, k + 0.5, 0, 0, 0));
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
		if (world.isRemote) {
			return;
		}
		int i1 = world.getBlockMetadata(i, j, k);
		boolean flag = canPlaceBlockAt(world, i, j, k);
		if (!flag) {
			world.setBlock(i, j, k, 0);
		} else {
			boolean flag1 = world.isBlockIndirectlyGettingPowered(i, j, k);
			if ((flag1) && l != blockID) // || l > 0 &&
											// Block.blocksList[l].canProvidePower()
											// || l == 0
			{
				// onPoweredBlockChange(world, i, j, k, flag1);
				zei_ChalkLogic.translate(world, i, j, k, world.getClosestPlayer(i, j, k, -1));
			}
		}
		super.onNeighborBlockChange(world, i, j, k, l);
	}

	@Override
	public void randomDisplayTick(World world, int i, int j, int k, Random random) {
		double der1 = (random.nextFloat() - 0.5D);
		double der2 = (random.nextFloat() - 0.5D);
		double d = i + 0.5D + der1;
		double d1 = j + 0.0625F;
		double d2 = k + 0.5D + der2;
		float f2 = -0.01f;
		// world.spawnParticle("smoke", d, d1, d2, 0f, f2, 0f);
		// zei_Universal.makeParticle(new EntitySmokeFX(world, d, d1, d2, 0f,
		// f2, 0f)); //EntitySmokeFX EntityCloudFX
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	private void notifyWireNeighborsOfNeighborChange(World world, int i, int j, int k) {
		if (world.getBlockId(i, j, k) != blockID) {
			return;
		} else {
			world.notifyBlocksOfNeighborChange(i, j, k, blockID);
			return;
		}
	}
}
