package zeitgeist.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import zeitgeist.common.zei_Ids;
import zeitgeist.common.world.gen.zei_WorldGenBigFakeTree;

public class zei_BlockDapling extends Block {
	protected zei_BlockDapling(int i) {
		super(i, Material.plants);
		setTickRandomly(true);
		float f = 0.4F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int i, int j, int k) {
		int bbb = world.getBlockId(i, j - 1, k);
		return world.getBlockId(i, j, k) == 0 && (bbb == zei_Ids.arch || bbb == zei_Ids.arch2);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		return null;
	}

	@Override
	public int getRenderType() {
		return 1;
	}

	public void growTree(World world, int i, int j, int k, Random random) {
		// int l = world.getBlockMetadata(i, j, k) & 3;
		world.setBlock(i, j, k, 0);
		zei_WorldGenBigFakeTree obj = new zei_WorldGenBigFakeTree();
		obj.boo = false;
		if (!obj.generate(world, random, i, j, k)) {
			world.setBlock(i, j, k, blockID, 0, 3);
		}
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k) {
		world.scheduleBlockUpdate(i, j, k, blockID, 3);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void updateTick(World world, int i, int j, int k, Random random) {
		if (world.isRemote) {
			return;
		}
		// checkFlowerChange(world, i, j, k);
		if (random.nextInt(1) == 0) // world.getBlockLightValue(i, j + 1, k) >=
									// 9 &&
		{
			/*
			 * int l = world.getBlockMetadata(i, j, k); if((l & 8) == 0) {
			 * world.setBlockMetadataWithNotify(i, j, k, l | 8); } else {
			 */
			growTree(world, i, j, k, random);
			// }
		}
	}
}
