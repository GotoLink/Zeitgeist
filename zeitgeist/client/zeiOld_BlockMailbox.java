package zeitgeist.client;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import zeitgeist.common.tile.zei_TileEntityMailbox;

public class zeiOld_BlockMailbox extends BlockContainer {
	private Random random;

	protected zeiOld_BlockMailbox(int i) {
		super(i, Material.grass);
		blockIndexInTexture = 250;
		random = new Random();
		setBlockBounds(0.45F, 0.0F, 0.45F, .55F, 0.85f, .55F);
	}

	public boolean blockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {
		Object obj = par1World.getBlockTileEntity(par2, par3, par4);
		if (obj == null) {
			return true;
		}
		if (par1World.isRemote) {
			return true;
		} else {
			par5EntityPlayer.displayGUIChest(((IInventory) (obj)));
			return true;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new zei_TileEntityMailbox();
	}

	@Override
	public Icon getBlockTexture(IBlockAccess iblockaccess, int i, int j, int k, int l) {
		int l2 = iblockaccess.getBlockMetadata(i, j, k);
		if (l == l2) // 1
		{
			return 4; // top
		}
		return 0; // side
	}

	public int getBlockTextureFromSideAndMetadata(int i, int j) {
		return 4;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		return null;
	}

	/*
	 * public void setBlockBoundsForItemRender() { float f = 0.5F; float f1 =
	 * 0.125F; float f2 = 0.5F; setBlockBounds(0.5F - f, 0.5F - f1, 0.5F - f2,
	 * 0.5F + f, 0.5F + f1, 0.5F + f2); }
	 */
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	public void onBlockPlaced(World world, int i, int j, int k, int l) {
		world.setBlockMetadataWithNotify(i, j, k, l, 3);
	}

	public void onBlockRemoval(World par1World, int par2, int par3, int par4) {
		TileEntityChest tileentitychest = (TileEntityChest) par1World.getBlockTileEntity(par2, par3, par4);
		if (tileentitychest != null) {
			for (int i = 0; i < tileentitychest.getSizeInventory(); i++) {
				ItemStack itemstack = tileentitychest.getStackInSlot(i);
				if (itemstack == null) {
					continue;
				}
				float f = random.nextFloat() * 0.8F + 0.1F;
				float f1 = random.nextFloat() * 0.8F + 0.1F;
				float f2 = random.nextFloat() * 0.8F + 0.1F;
				while (itemstack.stackSize > 0) {
					int j = random.nextInt(21) + 10;
					if (j > itemstack.stackSize) {
						j = itemstack.stackSize;
					}
					itemstack.stackSize -= j;
					EntityItem entityitem = new EntityItem(par1World, par2 + f, par3 + f1, par4 + f2, new ItemStack(itemstack.itemID, j, itemstack.getItemDamage()));
					float f3 = 0.05F;
					entityitem.motionX = (float) random.nextGaussian() * f3;
					entityitem.motionY = (float) random.nextGaussian() * f3 + 0.2F;
					entityitem.motionZ = (float) random.nextGaussian() * f3;
					if (itemstack.hasTagCompound()) {
						entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
					}
					par1World.spawnEntityInWorld(entityitem);
				}
			}
		}
		super.onBlockRemoval(par1World, par2, par3, par4);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
}
