package zeitgeist.common.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;
import zeitgeist.common.entity.zei_EntityWorker;

public class zei_EntityAIWorkerWander extends EntityAIBase {
	private zei_EntityWorker entity;
	private double xPosition;
	private double yPosition;
	private double zPosition;
	private float speed;

	public zei_EntityAIWorkerWander(zei_EntityWorker ent, float par2) {
		entity = ent;
		speed = par2;
		setMutexBits(1);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		if (entity.getMode() != 3) {
			return false;
		}
		if (entity.getRNG().nextInt(120) != 0) {
			return false;
		}
		Vec3 vec3d = RandomPositionGenerator.findRandomTarget(entity, 10, 7);
		if (vec3d == null) {
			return false;
		} else {
			xPosition = vec3d.xCoord;
			yPosition = vec3d.yCoord;
			zPosition = vec3d.zCoord;
			return true;
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean continueExecuting() {
		return !entity.getNavigator().noPath();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		entity.getNavigator().tryMoveToXYZ(xPosition, yPosition, zPosition,
				speed);
	}
}
