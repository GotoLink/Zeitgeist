package zeitgeist.common.block;

import zeitgeist.common.entity.zei_EntityDispBot;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class zei_BlockDisplayBot extends Block {
	protected zei_BlockDisplayBot(int i) {
		super(i, Material.rock);
		blockIndexInTexture = 1;
	}

	public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i,
			int j, int k) {
		int l = iblockaccess.getBlockMetadata(i, j, k);
		if (l == 0) {
			setBlockBounds(0.0F, 0, 0.0F, 1.0F, 1.0f, 1.0F);
		} else {
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25f, 1.0F);
		}
	}

	public void onBlockPlaced(World world, int i, int j, int k, int l) {
		world.setBlockMetadataWithNotify(i, j, k, 0, 3);
	}

	public void setBlockBoundsForItemRender() {
		float f = 0.5F;
		float f1 = 0.125F;
		float f2 = 0.5F;
		setBlockBounds(0, 0, 0, 1, 1, 1);
	}

	public boolean isAIEnabled() {
		return true;
	}

	public boolean blockActivated(World world, int x, int y, int z,
			EntityPlayer par5EntityPlayer) {
		if (world.isRemote) {
			return true;
		}
		int m = world.getBlockMetadata(x, y, z);
		if (m == 0) {
			world.setBlock(x, y, z, blockID, 1, 3);
			zei_EntityDispBot dp = new zei_EntityDispBot(world, x + 0.5,
					y + 0.5, z + 0.5, x, y, z);
			world.spawnEntityInWorld(dp);
		} else {
			world.setBlockMetadataWithNotify(x, y, z, 3, 3);
		}
		return false;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
}
