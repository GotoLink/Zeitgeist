package zeitgeist.common.block;

import java.util.Random;

import zeitgeist.common.tile.zei_TileEntityTexBlock;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class zei_BlockTex extends BlockContainer {
	protected zei_BlockTex(int i) {
		super(i, Material.wood);
		blockIndexInTexture = 64;
	}

	public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i,
			int j, int k) {
		int l = iblockaccess.getBlockMetadata(i, j, k);
		float F = 0.0625F;
		float F2 = 1f - F;
		if (l == 0) {
			setBlockBounds(0.0F, F2, 0.0F, 1.0F, 1.0f, 1.0F);
		} else if (l == 1) {
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, F, 1.0F);
		} else if (l == 2) {
			setBlockBounds(0.0F, 0.0F, F2, 1.0F, 1.0f, 1.0f);
		} else if (l == 3) {
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0f, F);
		} else if (l == 4) {
			setBlockBounds(F2, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
		} else {
			setBlockBounds(0.0F, 0.0F, 0.0F, F, 1.0f, 1.0F);
		}
	}

	public void onBlockPlaced(World world, int i, int j, int k, int l) {
		world.setBlockMetadataWithNotify(i, j, k, l, 3);
	}

	public void setBlockBoundsForItemRender() {
		float f = 0.5F;
		float f1 = 0.125F;
		float f2 = 0.5F;
		setBlockBounds(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1,
				0.5F + f2);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new zei_TileEntityTexBlock();
	}

	public int idDropped(int i, Random random, int j) {
		return 35;
	}
}
