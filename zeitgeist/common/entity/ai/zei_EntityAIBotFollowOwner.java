package zeitgeist.common.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import zeitgeist.common.entity.zei_EntityOwnedBot;

public class zei_EntityAIBotFollowOwner extends EntityAIBase {
	private zei_EntityOwnedBot thePet;
	private EntityLivingBase theOwner;
	World theWorld;
	private float field_48303_f;
	private PathNavigate petPathfinder;
	private int field_48310_h;
	float maxDist;
	float minDist;
	private boolean field_48311_i;

	public zei_EntityAIBotFollowOwner(zei_EntityOwnedBot par1EntityTameable,
			float par2, float par3, float par4) {
		thePet = par1EntityTameable;
		theWorld = par1EntityTameable.worldObj;
		field_48303_f = par2;
		petPathfinder = par1EntityTameable.getNavigator();
		minDist = par3;
		maxDist = par4;
		// setMutexBits(3);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		EntityLivingBase entityliving = thePet.reallyGetBotOwner();
		if (entityliving == null) {
			return false;
		}
		if (thePet.getDistanceSqToEntity(entityliving) < (double) (minDist
				* minDist * (thePet.getAITarget() != null ? 1.5 : 1))) {
			return false;
		} else {
			theOwner = entityliving;
			return true;
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean continueExecuting() {
		return !petPathfinder.noPath()
				&& thePet.getDistanceSqToEntity(theOwner) > (double) (maxDist * maxDist);// &&
																							// !thePet.isSitting();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		field_48310_h = 0;
		field_48311_i = thePet.getNavigator().getAvoidsWater();
		thePet.getNavigator().setAvoidsWater(false);
	}

	/**
	 * Resets the task
	 */
	public void resetTask() {
		theOwner = null;
		petPathfinder.clearPathEntity();
		thePet.getNavigator().setAvoidsWater(field_48311_i);
	}

	/**
	 * Updates the task
	 */
	public void updateTask() {
		thePet.getLookHelper().setLookPositionWithEntity(theOwner, 10F,
				thePet.getVerticalFaceSpeed());
		/*
		 * if (thePet.isSitting()) { return; }
		 */
		if (--field_48310_h > 0) {
			return;
		}
		field_48310_h = 10;
		if (petPathfinder.tryMoveToEntityLiving(theOwner, field_48303_f)) {
			return;
		}
		if (thePet.getDistanceSqToEntity(theOwner) < 144D) {
			return;
		}
		int i = MathHelper.floor_double(theOwner.posX) - 2;
		int j = MathHelper.floor_double(theOwner.posZ) - 2;
		int k = MathHelper.floor_double(theOwner.boundingBox.minY);
		for (int l = 0; l <= 4; l++) {
			for (int i1 = 0; i1 <= 4; i1++) {
				if ((l < 1 || i1 < 1 || l > 3 || i1 > 3)
						&& theWorld.isBlockNormalCube(i + l, k - 1, j + i1)
						&& !theWorld.isBlockNormalCube(i + l, k, j + i1)
						&& !theWorld.isBlockNormalCube(i + l, k + 1, j + i1)) {
					thePet.setLocationAndAngles((float) (i + l) + 0.5F, k,
							(float) (j + i1) + 0.5F, thePet.rotationYaw,
							thePet.rotationPitch);
					petPathfinder.clearPathEntity();
					return;
				}
			}
		}
	}
}
