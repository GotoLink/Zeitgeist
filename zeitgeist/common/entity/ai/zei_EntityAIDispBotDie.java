package zeitgeist.common.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;
import zeitgeist.common.zei_Ids;
import zeitgeist.common.entity.zei_EntityDispBot;

public class zei_EntityAIDispBotDie extends EntityAIBase {
	zei_EntityDispBot bot;
	PathNavigate botPathfinder;
	int x, y, z;

	public zei_EntityAIDispBotDie(zei_EntityDispBot ento, int x, int y, int z) {
		bot = ento;
		setMutexBits(3);
		botPathfinder = ento.getNavigator();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		return bot.worldObj.getBlockMetadata(x, y, z) == 3;
	}

	public void startExecuting() {
		super.startExecuting();
		bot.worldObj.setBlock(x, y, z, zei_Ids.dispBlock, 0, 3);
		bot.Dropper();
	}
}
