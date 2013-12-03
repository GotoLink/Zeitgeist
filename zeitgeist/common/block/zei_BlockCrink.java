package zeitgeist.common.block;

import zeitgeist.server.zei_Universal;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class zei_BlockCrink extends Block {
	protected zei_BlockCrink(int i, int j) {
		super(i, j, Material.circuits);
		slipperiness = 1.0F;
	}

	public void harvestBlock(World world, EntityPlayer entityplayer, int i,
			int j, int k, int l) {
		super.harvestBlock(world, entityplayer, i, j, k, l);
		if (world.getBlockId(i + 1, j, k) == blockID) {
			world.setBlock(i + 1, j, k, 0);
		}
		if (world.getBlockId(i - 1, j, k) == blockID) {
			world.setBlock(i - 1, j, k, 0);
		}
		if (world.getBlockId(i, j, k - 1) == blockID) {
			world.setBlock(i, j, k - 1, 0);
		}
		if (world.getBlockId(i, j, k + 1) == blockID) {
			world.setBlock(i, j, k + 1, 0);
		}
		if (world.getBlockId(i, j - 1, k) == blockID) {
			world.setBlock(i, j - 1, k, 0);
		}
		if (world.getBlockId(i, j + 1, k) == blockID) {
			world.setBlock(i, j + 1, k, 0);
		}
	}

	/*
	 * public void onBlockRemoval(World world, int i, int j, int k) {
	 * if(world.getBlockId(i +1, j, k )== Block.crink.blockID){ world.setBlock(i
	 * +1, j, k,0 ); }
	 * 
	 * if(world.getBlockId(i -1, j, k )== Block.crink.blockID){ world.setBlock(i
	 * -1, j, k,0 ); }
	 * 
	 * if(world.getBlockId(i, j, k-1 )== Block.crink.blockID){ world.setBlock(i
	 * , j, k-1,0 ); }
	 * 
	 * if(world.getBlockId(i, j, k+1 )== Block.crink.blockID){ world.setBlock(i,
	 * j, k+1,0 ); }
	 * 
	 * if(world.getBlockId(i, j-1, k )== Block.crink.blockID){ world.setBlock(i,
	 * j-1, k,0 ); } if(world.getBlockId(i, j+1, k )== Block.crink.blockID){
	 * world.setBlock(i, j+1, k,0 ); } }
	 */
	/*
	 * public int getBlockTextureFromSide(int i) { if(i <=1 ) { return
	 * blockIndexInTexture + 1; } else { return blockIndexInTexture; } }
	 */
	/*
	 * public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int
	 * j, int k, int l) { return true; }
	 */
	static Icon D[];

	static void loadSprites() {
		D = new Icon[3];
		D[0] = "/zeitgeist/crink.png");
		D[1] = "/zeitgeist/crink2.png");
		D[2] = "/zeitgeist/crink3.png");
	}

	/*
	 * public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int
	 * j, int k, int l) { boolean graphicsLevel=true; int i1 =
	 * iblockaccess.getBlockId(i, j, k); if(!graphicsLevel && i1 == blockID) {
	 * return false; } else { return super.shouldSideBeRendered(iblockaccess, i,
	 * j, k, l); } }
	 */
	public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k) {
		int g = iblockaccess.getBlockMetadata(i, j, k);
		return derk[g];
	}

	int derk[] = {
			0xffffff, // 0x0093bd,
			0x496FD6, 0x003469, 0x96E6EB, 0x43cef1, 0x4b6e8a, 0x7D4B94,
			0x292f3f, 0x08143a, 0x737a7b };

	public int getRenderBlockPass() {
		return (!Block.leaves.graphicsLevel) ? 0 : 1; // false;;
	}

	/*
	 * public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int
	 * j, int k, int l) { return super.shouldSideBeRendered(iblockaccess, i, j,
	 * k, 1 - l); }
	 */
	/*
	 * public int getRenderBlockPass() { return 1; }
	 */
	/*
	 * public boolean renderAsNormalBlock() { return false; }
	 */
	public Icon getIcon(int i, int j) {
		if (!Block.leaves.graphicsLevel) {
			return D[2];
		}
		if (j > 0) {
			return D[0]; // top 235
		}
		return D[1]; // D[0]
	}

	public boolean isOpaqueCube() {
		return (!Block.leaves.graphicsLevel);// ?1:0;//false;
	}
}
