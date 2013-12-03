package zeitgeist.common.block;

import java.util.Random;

import zeitgeist.common.zei_Ids;
import zeitgeist.common.tile.zei_TileEntityArchitect;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class zei_BlockArchitect extends BlockContainer {
	protected zei_BlockArchitect(int par1) {
		super(par1, Material.rock);
		blockIndexInTexture = 45;
	}

	public int idDropped(int par1, Random par2Random, int par3) {
		return blockID;
	}

	public void randomDisplayTick(World par1World, int par2, int par3,
			int par4, Random par5Random) {
		int i = par1World.getBlockMetadata(par2, par3, par4);
		float f = (float) par2 + 0.5F;
		float f1 = (float) par3 + 0.0F + (par5Random.nextFloat() * 6F) / 16F;
		float f2 = (float) par4 + 0.5F;
		float f3 = 0.52F;
		float f4 = par5Random.nextFloat() * 0.6F - 0.3F;
		if (i == 4) {
			par1World.spawnParticle("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D,
					0.0D);
			par1World.spawnParticle("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D,
					0.0D);
		} else if (i == 5) {
			par1World.spawnParticle("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D,
					0.0D);
			par1World.spawnParticle("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D,
					0.0D);
		} else if (i == 2) {
			par1World.spawnParticle("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D,
					0.0D);
			par1World.spawnParticle("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D,
					0.0D);
		} else if (i == 3) {
			par1World.spawnParticle("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D,
					0.0D);
			par1World.spawnParticle("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D,
					0.0D);
		}
	}

	public boolean blockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer) {
		if (par1World.isRemote) {
			return true;
		}
		return true;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public int getRenderType() {
		return -1;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
		if (l == zei_Ids.chalk)
			world.setBlock(i, j, k, zei_Ids.arch2);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new zei_TileEntityArchitect();
	}
}
