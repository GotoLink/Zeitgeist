package zeitgeist.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import zeitgeist.common.zei_IBot;
import zeitgeist.common.zei_Ids;

public class zei_EntityRemnant extends EntityMob implements zei_IBot {
	public zei_EntityRemnant(World world) {
		super(world);
		texture = "/zeitgeist/um.png";
		moveSpeed = 0.4F;
		isImmuneToFire = true;
		attackStrength = 2;
		attackTime = 80;
	}

	public zei_EntityRemnant(World world, double d, double d1, double d2) {
		this(world);
		setPosition(d, d1 + yOffset, d2);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
	}

	@Override
	public float getMaxHealth() {
		return 6;
	}

	public void knockBack(Entity entity, int i, double d, double d1) {
	}

	@Override
	public void onDeath(DamageSource damagesource) {
		/*
		 * Entity entity = damagesource.getEntity(); if(scoreValue >= 0 &&
		 * entity != null) { entity.addToPlayerScore(this, scoreValue); }
		 * if(entity != null) { entity.onKillEntity(this); } unused_flag = true;
		 */
		super.onDeath(damagesource);
		if (!worldObj.isRemote) {
			Dropper();// a(field_34905_c > 0);
			setDead();
		}
		// worldObj.setEntityState(this, (byte)3);
	}

	@Override
	public void onLivingUpdate() {
		if (isWet()) {
			Dropper();
		}
		super.onLivingUpdate();
	}

	@Override
	protected Entity findPlayerToAttack() {
		EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, 100d);
		return entityplayer;
	}

	@Override
	protected String getDeathSound() {
		return "random.glass";
	}

	@Override
	protected String getHurtSound() {
		return "automatons.hzz";
	}

	@Override
	protected String getLivingSound() {
		return "automatons.hzz";
	}

	@Override
	protected float getSoundVolume() {
		return 0.2F;
	}

	void Dropper() {
		for (int j = 0; j < 20; j++) {
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			worldObj.spawnParticle("explode", (posX + rand.nextFloat() * width * 2.0F) - width, posY + rand.nextFloat() * height, (posZ + rand.nextFloat() * width * 2.0F) - width, d, d1, d2);
		}
		if (!worldObj.isRemote) {
			entityDropItem(new ItemStack(zei_Ids.soulCore, 1, 0), 0.0F);
			deathTime = 999;
			// setEntityDead();
		}
	}
}
