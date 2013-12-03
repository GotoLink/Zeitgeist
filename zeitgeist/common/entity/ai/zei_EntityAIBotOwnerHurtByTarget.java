package zeitgeist.common.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import zeitgeist.common.entity.zei_EntityOwnedBot;

public class zei_EntityAIBotOwnerHurtByTarget extends EntityAITarget {
	zei_EntityOwnedBot bot;
	EntityLivingBase targ;

	public zei_EntityAIBotOwnerHurtByTarget(zei_EntityOwnedBot ento) {
		super(ento, false);
		bot = ento;
		setMutexBits(1);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		/*
		 * if (!bot.isTamed()) { return false; }
		 */
		EntityLivingBase entityliving = bot.reallyGetBotOwner();
		if (entityliving == null) {
			return false;
		} else {
			targ = entityliving.getAITarget();
			return func_48284_a(targ, false);
		}
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		taskOwner.setAttackTarget(targ);
		super.startExecuting();
	}
}
