package zeitgeist.common.block;

import zeitgeist.common.zei_Ids;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class zei_BlockMiller extends Block {
	protected zei_BlockMiller(int i) {
		super(i, Material.rock);
		blockIndexInTexture = 16;
		float F = 0.25f;
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, F, 1.0F);
	}

	public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
		if (world.isRemote) {
			return;
		}
		if (l == zei_Ids.turnBlock)
			computeState(world, i, j, k);
	}

	public void computeState(World world, int i, int j, int k) {
		int m = world.getBlockMetadata(i, j, k);
		if (m == 0
				&& (derr(world, i + 1, j, k, 5) || derr(world, i + 1, j, k, 4)
						|| derr(world, i, j, k + 1, 2)
						|| derr(world, i, j, k - 1, 1)
						|| derr(world, i, j + 1, k, 8) || derr(world, i, j - 1,
							k, 7))) {
			world.setBlockMetadataWithNotify(i, j, k, 1, 3);
		} else if (m == 1) {
			world.setBlockMetadataWithNotify(i, j, k, 0, 3);
		}
	}

	public boolean derr(World world, int i, int j, int k, int m) {
		return world.getBlockId(i, j, k) == zei_Ids.turnBlock
				&& world.getBlockMetadata(i, j, k) == m;
	}

	/*
	 * public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i,
	 * int j, int k) { float f = 0.125F; float f2 = 1f-f; int l =
	 * world.getBlockMetadata(i, j, k); /*if(l == 0){ return
	 * AxisAlignedBB.getBoundingBoxFromPool(i, j, k, (float)i + f2, j+1, k + 1);
	 * }else if(l==1){ return AxisAlignedBB.getBoundingBoxFromPool(i, j, k, i +
	 * 1, j+1, (float)k + f2); } return AxisAlignedBB.getBoundingBoxFromPool(i,
	 * j, k, i + 1, (float)j+f2, k + 1); }
	 */
	/*
	 * public void onBlockPlaced(World world, int i, int j, int k, int l) {
	 * world.setBlockMetadataWithNotify(i, j, k, l); }
	 */
	public void onEntityCollidedWithBlock(World world, int i, int j, int k,
			Entity entity) {
		if (world.getBlockMetadata(i, j, k) != 1) {
			return;
		}
		if (entity instanceof EntityItem) {
			float x = i + 0.5f;
			float y = j + 0.5f;
			float z = k + 0.5f;
			entity.motionX = (x - entity.posX) / 100;
			entity.motionY = 0;
			entity.motionZ = (z - entity.posZ) / 100;
			EntityItem ei = (EntityItem) entity;
			if (ei.getEntityItem().itemID == Item.wheat.itemID) {
				ei.delayBeforeCanPickup += 10;
				if (ei.delayBeforeCanPickup > 200) {
					ei.getEntityItem().itemID = Item.bread.itemID;
					ei.delayBeforeCanPickup = 10;
				}
			}
		}
	}

	/*
	 * public void getCollidingBoundingBoxes(World world, int i, int j, int k,
	 * AxisAlignedBB axisalignedbb, ArrayList arraylist) { int l =
	 * world.getBlockMetadata(i, j, k); if(l == 0) { setBlockBounds(0.0F, 0.0F,
	 * 0.0F, 0.0625F, 1.0F, 1.0F); System.out.println("HERE");
	 * //setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 0.5F, 1.0F);
	 * //super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb,
	 * arraylist); //setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	 * 
	 * //super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb,
	 * arraylist); } else if(l == 1) { setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F,
	 * 1.0F, 1.0F); super.getCollidingBoundingBoxes(world, i, j, k,
	 * axisalignedbb, arraylist); setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 0.5F,
	 * 1.0F); super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb,
	 * arraylist); } else if(l == 2) { setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F,
	 * 0.5F, 0.5F); super.getCollidingBoundingBoxes(world, i, j, k,
	 * axisalignedbb, arraylist); setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 1.0F,
	 * 1.0F); super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb,
	 * arraylist); } else if(l == 3) { setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F,
	 * 1.0F, 0.5F); super.getCollidingBoundingBoxes(world, i, j, k,
	 * axisalignedbb, arraylist); setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 0.5F,
	 * 1.0F); super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb,
	 * arraylist); } setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F); }
	 */
	/*
	 * public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i,
	 * int j, int k) { return null; }
	 */
	/*
	 * public int getRenderType() { return 10; }
	 */
	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
}
