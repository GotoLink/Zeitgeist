package zeitgeist.common.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class zei_EntityAniBot extends EntityAnimal {
	public zei_EntityAniBot(World world) {
		super(world);
	}

	@Override
	public float getMaxHealth() {
		return 0;
	}

	@Override
	public boolean attackEntityFrom(DamageSource damagesource, int i) {
		if (damagesource == DamageSource.inWall) {
			this.pushOutOfBlocks(posX, posY, posZ);
			return false;
		}
		return super.attackEntityFrom(damagesource, i);
	}
}
