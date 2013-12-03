package zeitgeist.common.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.world.World;

public class zei_EntityNoop extends EntityAnimal {
	public zei_EntityNoop(World world) {
		super(world);
		texture = "/zeitgeist/noop.png";
		setSize(0.9F, 0.9F);
	}

	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, Byte.valueOf((byte) 0));
	}

	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setBoolean("Saddle", getSaddled());
	}

	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		setSaddled(nbttagcompound.getBoolean("Saddle"));
	}

	protected String getLivingSound() {
		return "mob.pig";
	}

	protected String getHurtSound() {
		return "mob.pig";
	}

	protected String getDeathSound() {
		return "mob.pigdeath";
	}

	public boolean interact(EntityPlayer entityplayer) {
		if (getSaddled() && !worldObj.isRemote
				&& (riddenByEntity == null || riddenByEntity == entityplayer)) {
			entityplayer.mountEntity(this);
			return true;
		} else {
			return false;
		}
	}

	protected int getDropItemId() {
		return Item.porkRaw.itemID;
	}

	public boolean getSaddled() {
		return (dataWatcher.getWatchableObjectByte(16) & 1) != 0;
	}

	public void setSaddled(boolean flag) {
		if (flag) {
			dataWatcher.updateObject(16, Byte.valueOf((byte) 1));
		} else {
			dataWatcher.updateObject(16, Byte.valueOf((byte) 0));
		}
	}

	public void onStruckByLightning(EntityLightningBolt entitylightningbolt) {
		if (worldObj.isRemote) {
			return;
		} else {
			EntityPigZombie entitypigzombie = new EntityPigZombie(worldObj);
			entitypigzombie.setLocationAndAngles(posX, posY, posZ, rotationYaw,
					rotationPitch);
			worldObj.spawnEntityInWorld(entitypigzombie);
			setDead();
			return;
		}
	}

	protected void fall(float f) {
		super.fall(f);
		if (f > 5F && (riddenByEntity instanceof EntityPlayer)) {
			((EntityPlayer) riddenByEntity)
					.triggerAchievement(AchievementList.flyPig);
		}
	}

	@Override
	public float getMaxHealth() {
		return 3;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable) {
		return new zei_EntityNoop(worldObj);
	}
}
