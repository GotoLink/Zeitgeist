package zeitgeist.common.entity;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

// Referenced classes of package net.minecraft.src:
//            EntityLiving, MathHelper, AxisAlignedBB, World,
//            Block
/*&#$
 field_703_S:field_386_ba
 field_704_R:field_9141_bd
 field_705_Q:field_9142_bc
 &#$*/
public class zei_EntityFlying2 extends zei_EntityBot {
	public zei_EntityFlying2(World world) {
		super(world);
	}

	protected void fall(float f) {
	}

	public void moveEntityWithHeading(float f, float f1) {
		float f2 = 0.91F;
		if (onGround) {
			f2 = 0.5460001F;
			int i = worldObj.getBlockId(MathHelper.floor_double(posX),
					MathHelper.floor_double(boundingBox.minY) - 1,
					MathHelper.floor_double(posZ));
			if (i > 0) {
				f2 = Block.blocksList[i].slipperiness * 0.91F;
			}
		}
		float f3 = 0.1627714F / (f2 * f2 * f2);
		moveFlying(f, f1, onGround ? 0.1F * f3 : 0.02F);
		f2 = 0.91F;
		if (onGround) {
			f2 = 0.5460001F;
			int j = worldObj.getBlockId(MathHelper.floor_double(posX),
					MathHelper.floor_double(boundingBox.minY) - 1,
					MathHelper.floor_double(posZ));
			if (j > 0) {
				f2 = Block.blocksList[j].slipperiness * 0.91F;
			}
		}
		moveEntity(motionX, motionY, motionZ);
		motionX *= f2;
		motionY *= f2;
		motionZ *= f2;
		this.field_9142_bc = field_9141_bd;
		double d = posX - prevPosX;
		double d1 = posZ - prevPosZ;
		float f4 = MathHelper.sqrt_double(d * d + d1 * d1) * 4F;
		if (f4 > 1.0F) {
			f4 = 1.0F;
		}
		field_9141_bd += (f4 - field_9141_bd) * 0.4F;
		field_386_ba += field_9141_bd;
	}

	public boolean isOnLadder() {
		return false;
	}

	@Override
	public float getMaxHealth() {
		return 10;
	}
}
