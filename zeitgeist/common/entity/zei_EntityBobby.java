package zeitgeist.common.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import zeitgeist.common.zei_IBot;
import zeitgeist.common.zei_Ids;

public class zei_EntityBobby extends zei_EntityBot implements zei_IBot {
	public zei_EntityBobby(World world) {
		super(world);
		texture = "/zeitgeist/bobby.png";
		setSize(0.9F, 0.9F);
		health = 5;
	}

	public zei_EntityBobby(World world, double d, double d1, double d2) {
		this(world);
		setPosition(d, d1 + yOffset, d2);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
		setPathToEntity(null);
	}

	@Override
	public boolean getCanSpawnHere() {
		return true;
	}

	@Override
	public float getMaxHealth() {
		return 5;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 100;
	}

	@Override
	public void onDeath(DamageSource damagesource) {
		super.onDeath(damagesource);
		if (!worldObj.isRemote) {
			Dropper();
		}
		setDead();
	}

	@Override
	public void onLivingUpdate() {
		if (isWet()) {
			setDead();
		}
		super.onLivingUpdate();
	}

	@Override
	protected String getDeathSound() {
		return "automatons.botdie";
	}

	@Override
	protected int getDropItemId() {
		return 0;
	}

	@Override
	protected String getHurtSound() {
		return "automatons.clank";
	}

	@Override
	protected String getLivingSound() {
		return "automatons.beep";
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	void Dropper() {
		for (int j = 0; j < 20; j++) {
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			worldObj.spawnParticle("explode", (posX + rand.nextFloat() * width * 2.0F) - width, posY + rand.nextFloat() * height, (posZ + rand.nextFloat() * width * 2.0F) - width, d, d1, d2);
		}
		if (!worldObj.isRemote) {
			int R = rand.nextInt(2);
			if (R == 0) {
				if (worldObj.countEntities(zei_EntityBobby.class) < 50) {
					worldObj.spawnEntityInWorld(new zei_EntityBobby(worldObj, posX, posY, posZ));
					worldObj.spawnEntityInWorld(new zei_EntityBobby(worldObj, posX, posY, posZ));
				}
			} else {
				if (rand.nextInt(3) == 0) {
					entityDropItem(new ItemStack(zei_Ids.craftSet1, 1, 11), 0.0F);
				}
			}
			deathTime = 999; // setEntityDead();
		}
	}
}
