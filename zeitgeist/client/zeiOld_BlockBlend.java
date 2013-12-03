package zeitgeist.client;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class zeiOld_BlockBlend extends Block {
	public zeiOld_BlockBlend(int i, int j) {
		super(i, Material.clay);
		setHardness(0f);
		setResistance(0.5f);
		setStepSound(Block.soundStoneFootstep);
	}

	public int getBlockTextureFromSideAndMetadata(int i, int j) {
		return blockIndexInTexture;
	}

	@Override
	public int getRenderType() {
		return zei_Ids.blenderRenderId;
	}

	/*
	 * public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int
	 * k) { return 0x9999AA; }
	 */
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
}
