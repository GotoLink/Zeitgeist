package zeitgeist.client;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import zeitgeist.common.entity.zei_EntityWatcher;

public class zeiOld_BlockBad extends Block {
	public Icon[] D = { 1, 1 };

	protected zeiOld_BlockBad(int i, int j) {
		super(i, Material.glass);
		float f = 0.1875F;
		float f2 = 1F - f;
		this.setBlockBounds(f, 0, f, f2, 1F - 0.5F, f2);
	}

	@Override
	public boolean canBlockStay(World world, int i, int j, int k) {
		if (world.getBlockMaterial(i, j - 1, k).isSolid()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean canPlaceBlockAt(World world, int i, int j, int k) {
		if (!super.canPlaceBlockAt(world, i, j, k)) {
			return false;
		} else {
			return canBlockStay(world, i, j, k);
		}
	}

	@Override
	public Icon getBlockTextureFromSide(int i) {
		if (i <= 1) {
			return D[1];
		} else {
			return D[0];
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		float f = 0.25F;
		return AxisAlignedBB.getBoundingBox(i + f, j, k + f, i + 1 - f, j + 0.5F, k + 1 - f);
	}

	@Override
	public int getRenderType() {
		return 13;
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int i, int j, int k) {
		float f = 0.25F;
		return AxisAlignedBB.getBoundingBox(i + f, j, k + f, i + 1 - f, j + 0.5F, k + 1 - f);
	}

	@Override
	public void harvestBlock(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
		zei_EntityWatcher et = new zei_EntityWatcher(world, i + 0.5F, j + 0.5F, k + 0.5F);
		world.spawnEntityInWorld(et);
		et.setTarget(entityplayer);
	}

	public int idDropped(int i, Random random) {
		return 0;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	public void loadSprites(int i, int j) {
		D[0] = i;
		D[1] = j;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
}
