package zeitgeist.common.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;
import zeitgeist.common.entity.zei_EntityMaker;

public class zei_EntityAIMakerBuild extends EntityAIBase {
	zei_EntityMaker bot;
	PathNavigate botPathfinder;

	public zei_EntityAIMakerBuild(zei_EntityMaker ento) {
		bot = ento;
		setMutexBits(3);
		botPathfinder = ento.getNavigator();
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		return bot.making && botPathfinder.noPath();
	}

	public void updateTask() {
		// /bot.getLookHelper().setLookPosition(bot.dX, bot.dY, bot.dZ, 10F,
		// bot.getVerticalFaceSpeed());
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public boolean continueExecuting() {
		return !botPathfinder.noPath() && bot.making;
	}

	public void startExecuting() {
		super.startExecuting();
		// System.out.println("hmm");
		// botPathfinder.setPath(bot.worldObj.getEntityPathToXYZ(bot, x, y, z,
		// 16F, true, true, false, true), 0.25f);
		bot.build(bot.xx, bot.yy + 2, bot.zz);
		// bot.making=true;
		// botPathfinder.
	}
}
