package zeitgeist.common.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.passive.EntityPig;

public class zei_EntityAITargetNonTamed extends EntityAINearestAttackableTarget {
	private EntityLiving targeted;

	public zei_EntityAITargetNonTamed(EntityLiving ento, Class par2Class,
			float par3, int par4, boolean par5) {
		super(ento, par2Class, par3, par4, par5);
		targeted = ento;
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		if (targeted instanceof EntityPig) {
			return super.shouldExecute();
		} else {
			return false;
		}
	}
}
