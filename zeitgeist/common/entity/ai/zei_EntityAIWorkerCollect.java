package zeitgeist.common.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;
import zeitgeist.common.entity.zei_EntityWorker;

public class zei_EntityAIWorkerCollect extends EntityAIBase {
	zei_EntityWorker bot;
	EntityLivingBase owner;
	PathNavigate botPathfinder;
	int cur = 0;
	int timer = 0;

	public zei_EntityAIWorkerCollect(zei_EntityWorker ento) {
		bot = ento;
		setMutexBits(3);
		botPathfinder = ento.getNavigator();
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		if (bot.getMode() == 4) {
			owner = bot.reallyGetBotOwner();
			if (owner != null) {
				return true;
			}
		}
		return false;
	}

	public boolean continueExecuting() {
		if (botPathfinder.noPath() || bot.getMode() != 4) {
			return false;
		}
		int ind = botPathfinder.getPath().getCurrentPathIndex();
		if (cur != ind) {
			cur = ind;
			timer = 0;
		} else {
			timer++;
		}
		if (timer >= 200) {
			System.out.println("Worker: giving up path :(");
			botPathfinder.tryMoveToXYZ(bot.hx, bot.hy, bot.hz, bot.moveSpeed);
			return false;
		}
		return true;// && !thePet.isSitting();
	}

	public void updateTask() {
		if (bot.itemGet != null) {
			bot.getLookHelper().setLookPositionWithEntity(bot.itemGet, 10F,
					bot.getVerticalFaceSpeed());
		}
		// bot.getLookHelper().setLookPositionWithEntity(owner, 10F,
		// bot.getVerticalFaceSpeed());
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		super.startExecuting();
		bot.modeCollection(owner);
	}
}
