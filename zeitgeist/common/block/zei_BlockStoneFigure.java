package zeitgeist.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import zeitgeist.common.zei_Ids;
import zeitgeist.common.tile.zei_TileEntityStatue;

public class zei_BlockStoneFigure extends BlockContainer {
	protected zei_BlockStoneFigure(int i) {
		super(i, Material.ground);
		setHardness(0.2f);
		setResistance(0.5f);
		setStepSound(Block.soundStoneFootstep);
		// setLightOpacity(255);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int i, int j, int k) {
		return world.getBlockId(i, j - 1, k) != 0;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new zei_TileEntityStatue();
	}

	@Override
	public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		return true;
	}

	@Override
	public Icon getIcon(int i, int j) {
		return 22;
	}

	@Override
	public int getRenderType() {
		return zei_Ids.stoneFigureRenderId;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return zei_Ids.stoneFigureItem;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k) {
		/*
		 * zei_TileEntityStatue tile=
		 * (zei_TileEntityStatue)world.getBlockTileEntity(i, j, k); int
		 * idd=world.getBlockId(i, j - 1, k); if(idd==blockID){
		 * zei_TileEntityStatue tile2=
		 * (zei_TileEntityStatue)world.getBlockTileEntity(i, j-1, k);
		 * idd=tile2.blok; } tile.setBlokSafe(idd);
		 * world.setBlockMetadataWithNotify(i, j, k, world.getBlockMetadata(i,
		 * j-1, k));
		 */
	}

	/*
	 * public void onBlockPlacedBy(World world, int i, int j, int k,
	 * EntityLiving entityliving) { int l =
	 * MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F)
	 * + 0.5D) & 3; zei_TileEntityStatue tile = new
	 * zei_TileEntityStatue();//(zei_TileEntityStatue
	 * )world.getBlockTileEntity(i, j, k); if (l == 0) { tile.setTurn(14);
	 * //world.setBlockMetadataWithNotify(i, j, k, 14); } if (l == 1) {
	 * tile.setTurn(13); //world.setBlockMetadataWithNotify(i, j, k, 13); } if
	 * (l == 2) { tile.setTurn(15); //world.setBlockMetadataWithNotify(i, j, k,
	 * 15); } if (l == 3) { tile.setTurn(12);
	 * //world.setBlockMetadataWithNotify(i, j, k, 12); }
	 * world.setBlockTileEntity(i, j, k, tile); }
	 */
	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
		// System.out.println("changed");
		int idd = world.getBlockId(i, j - 1, k);
		if (idd == 0) {
		} else if (world.isBlockIndirectlyGettingPowered(i, j, k) || l == 989) {
			zei_TileEntityStatue tile = (zei_TileEntityStatue) world.getBlockTileEntity(i, j, k);
			if (idd == blockID) {
				zei_TileEntityStatue tile2 = (zei_TileEntityStatue) world.getBlockTileEntity(i, j - 1, k);
				tile.setBlokSafe(tile2.blok);
			} else {
				tile.setBlokSafe(idd);
			}
			world.setBlockMetadataWithNotify(i, j, k, world.getBlockMetadata(i, j - 1, k), 2);
			world.notifyBlockChange(i, j + 2, k, idd);
		}
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	/*
	 * public void define(World world,int i,int j,int k){ int it=1; int id=0;
	 * while((id=world.getBlockId(i, j-it, k))==blockID){ it++; } int
	 * me=world.getBlockMetadata(i, j-it, k);
	 * world.setBlockMetadataWithNotify(i, j, k, me); }
	 */
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
}
