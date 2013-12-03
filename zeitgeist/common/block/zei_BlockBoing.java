package zeitgeist.common.block;

import zeitgeist.common.zei_Universal;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class zei_BlockBoing extends Block {
	protected zei_BlockBoing(int i) {
		super(i, Material.grass);
		blockIndexInTexture = 250;
	}

	static int D;

	static void loadSprites() {
		D = "/zeitgeist/boing.png";
		// blockIndexInTexture=D;
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
	public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i,
			int j, int k) {
		// float f = 0.125F;
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

	public void onEntityCollidedWithBlock(World world, int i, int j, int k,
			Entity entity) {
		int l = world.getBlockMetadata(i, j, k);
		entity.fallDistance = -10;
		if (l == 0) {
			entity.motionY = -0.75F;
			// entity.motionZ=0;
			// entity.motionX=0;
			entity.motionZ *= 1.2F;
			entity.motionX *= 1.2F;
		} else if (l == 1) {
			entity.motionY = 0.75F;
			// entity.motionZ=0;
			// entity.motionX=0;
			entity.motionZ *= 1.2F;
			entity.motionX *= 1.2F;
		} else if (l == 2) {
			entity.motionZ = -1.25F;
			// entity.motionY=0;
			// entity.motionX=0;
			entity.motionY *= 1.2F;
			entity.motionX *= 1.2F;
		} else if (l == 3) {
			entity.motionZ = 1.25F;
			// entity.motionY=0;
			// entity.motionX=0;
			entity.motionY *= 1.2F;
			entity.motionX *= 1.2F;
		} else if (l == 4) {
			entity.motionX = -1.25F;
			// entity.motionZ=0;
			// entity.motionY=0;
			entity.motionY *= 1.2F;
			entity.motionZ *= 1.2F;
		} else {
			entity.motionX = 1.25F;
			// entity.motionZ=0;
			// entity.motionY=0;
			entity.motionY *= 1.2F;
			entity.motionZ *= 1.2F;
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
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i,
			int j, int k) {
		return null;
	}

	public Icon getBlockTexture(IBlockAccess iblockaccess, int i, int j, int k,
			int l) {
		int l2 = iblockaccess.getBlockMetadata(i, j, k);
		if (l == l2) // 1
		{
			return D; // top
		}
		return 0; // side
	}

	public void setBlockBoundsForItemRender() {
		float f = 0.5F;
		float f1 = 0.125F;
		float f2 = 0.5F;
		setBlockBounds(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1,
				0.5F + f2);
	}

	/*
	 * public int getRenderType() { return 10; }
	 */
	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getBlockTextureFromSideAndMetadata(int i, int j) {
		return D;
	}
}
