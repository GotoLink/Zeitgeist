package zeitgeist.common.block;

import java.util.Random;

import zeitgeist.common.tile.zei_TileEntityTexBlock2;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class zei_BlockTex2 extends BlockContainer {
	private Random random;

	protected zei_BlockTex2(int i) {
		super(i, Material.wood);
		blockIndexInTexture = 43;
		random = new Random();
		float f = 0.001f;
		setBlockBounds(f, f, f, 1 - f, 1 - f, 1 - f);
	}

	public void onBlockPlaced(World world, int i, int j, int k, int l) {
		world.setBlockMetadataWithNotify(i, j, k, l, 3);
	}

	public int idDropped(int i, Random random, int j) {
		return 5;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getBlockTextureFromSideAndMetadata(int i, int j) {
		return 4;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new zei_TileEntityTexBlock2();
	}
}
