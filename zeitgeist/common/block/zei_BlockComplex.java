package zeitgeist.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.Icon;

public class zei_BlockComplex extends Block {
	protected zei_BlockComplex(int i) {
		super(i, Material.rock);
	}

	static Icon D[];

	static void loadSprites() {
		D = new Icon[16];
		D[0] = "/zeitgeist/tech.png");
		D[1] = "/zeitgeist/tree.png");
		D[2] = "/zeitgeist/wallo.png");
		D[3] = "/zeitgeist/bwop.png");
		D[4] = "/zeitgeist/tech2.png");
	}

	/*
	 * public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i,
	 * int j, int k) { float f = 0.125F; return
	 * AxisAlignedBB.getBoundingBoxFromPool(i, j, k, i + 1, (float)(j + 1) - f,
	 * k + 1); }
	 * 
	 * public void onEntityCollidedWithBlock(World world, int i, int j, int k,
	 * Entity entity) { int h=world.getBlockMetadata(i,j,k); if(h<25){
	 * world.setBlockAndMetadata(i, j, k,Block.frass.blockID,h+1 ); }
	 */
	/*
	 * if(entity.motionX!=0 &&entity.motionZ!=0){ float
	 * r=(float)Math.sqrt(entity
	 * .motionX*entity.motionX+entity.motionZ*entity.motionZ); entity.motionX =
	 * 0.2F*entity.motionX/r; entity.motionZ = 0.2F*entity.motionZ/r; }
	 */
	// entity.motionX=-MathHelper.sin(entity.rotationYaw*0.0175F)*0.2F;
	// entity.motionZ=MathHelper.cos(entity.rotationYaw*0.0175F)*0.2F;
	// entity.fallDistance=-10;
	// entity.motionY=2F;
	// entity.motionZ*=1.2F;
	// entity.motionX*=1.2F;
	// }
	/*
	 * public int getBlockTextureFromSideAndMetadata(int i, int j) { if(i == 1)
	 * { return 21; } if(i == 0) { return 21; } if(j == 1) { return 116; }
	 * return j != 2 ? 20 : 117; }
	 */
	public Icon getIcon(int i, int j) {
		return D[j]; // D[0]
	}

	public int damageDropped(int i) {
		return i;
	}
}
