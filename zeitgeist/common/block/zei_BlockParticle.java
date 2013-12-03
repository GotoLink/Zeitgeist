package zeitgeist.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityCloudFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import zeitgeist.client.ClientProxy;
import zeitgeist.client.fx.zei_EntitySmokeColumnFX;

public class zei_BlockParticle extends Block {
	public zei_BlockParticle(int i) {
		super(i, 4, Material.ground);
		setHardness(0f);
		setResistance(0.5f);
		setStepSound(Block.soundStoneFootstep);
	}

	public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer) {
		if (world.isRemote) {
			return true;
		}
		return true;
	}

	public int getBlockTextureFromSideAndMetadata(int i, int j) {
		return blockIndexInTexture;
	}

	/*
	 * public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int
	 * k) { return 0x9999AA; }
	 */
	@Override
	public void randomDisplayTick(World world, int i, int j, int k, Random random) {
		double d1 = j + 1F;
		float f2 = 0.1f;
		double der1 = (random.nextFloat() - 0.5D);
		double der2 = (random.nextFloat() - 0.5D);
		double d = i + 0.5D + der1;
		double d2 = k + 0.5D + der2;
		ClientProxy.makeParticle(new EntityCloudFX(world, d, d1, d2, 0f, f2, 0f)); // EntitySmokeFX
																					// EntityCloudFX
		for (int H = 0; H < 5; H++) {
			der1 = (random.nextFloat() - 0.5D);
			der2 = (random.nextFloat() - 0.5D);
			d = i + 0.5D + der1;
			d2 = k + 0.5D + der2;
			f2 += 0.01f;
			ClientProxy.makeParticle(new zei_EntitySmokeColumnFX(world, d, d1, d2, 0f, f2, 0f));
		}
	}
}
