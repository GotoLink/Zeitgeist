package zeitgeist.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class zei_EntityOwnedBot extends EntityCreature {
	public zei_EntityOwnedBot(World world) {
		super(world);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		// dataWatcher.addObject(16, Byte.valueOf((byte)0));
		dataWatcher.addObject(17, "");
	}

	@Override
	public boolean attackEntityFrom(DamageSource damagesource, int i) {
		if (damagesource == DamageSource.inWall) {
			this.pushOutOfBlocks(posX, posY, posZ);
			return false;
		}
		Entity entity = damagesource.getEntity();
		if (entity != null && entity != this
				&& (entity instanceof EntityPlayer)
				&& ((EntityPlayer) entity).username.equals(getBotOwner())) {
			i = 20;
		}
		return super.attackEntityFrom(damagesource, i);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	@Override
	protected int getDropItemId() {
		return 0;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		if (getBotOwner() == null) {
			nbttagcompound.setString("Owner", "");
		} else {
			nbttagcompound.setString("Owner", getBotOwner());
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		String s = nbttagcompound.getString("Owner");
		if (s.length() > 0) {
			setBotOwner(s);
		}
	}

	public String getBotOwner() {
		return dataWatcher.getWatchableObjectString(17);
	}

	public void setBotOwner(String s) {
		dataWatcher.updateObject(17, s);
	}

	public boolean canWander() {
		return true;
	}

	public EntityPlayer reallyGetBotOwner() {
		return worldObj.getPlayerEntityByName(getBotOwner());
	}

	@Override
	public float getMaxHealth() {
		return 10;
	}
}
