package zeitgeist.common.entity;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import zeitgeist.common.mod_Zeitgeist;

public class zei_EntityGoreMan extends EntityMob {
	public zei_EntityGoreMan(World world) {
		super(world);
		texture = "/mob/zombie.png";
		moveSpeed = 0.5F;
		attackStrength = 0;
	}

	public boolean attackEntityFrom(DamageSource damagesource, int i) {
		int h = getSever();
		if (h > 0) {
			int j = h - 1;
			if (j == 4) {
				float r = (float) Math.toRadians(270 + rotationYaw);
				double x = MathHelper.sin(r) * width;
				double y = -MathHelper.cos(r) * width;
				double xo = posX + x;
				double yo = posY + height - 0.2;
				double zo = posZ + y;
				mod_Zeitgeist.proxy.gore(worldObj, xo, yo, zo, x * 2f, y * 2f, 0, 10, 4f);
				// zei_EntityLimb ep= new
				// zei_EntityLimb(worldObj,xo,yo,zo,rotationYaw,1);
				zei_EntityRagdoll ep = new zei_EntityRagdoll(worldObj, posX + x * 0.5, yo, posZ + y * 0.5, rotationYaw, 1, this);
				worldObj.spawnEntityInWorld(ep);
			}
			if (j == 3) {
				float r = (float) Math.toRadians(90 + rotationYaw);
				double x = MathHelper.sin(r) * width;
				double y = -MathHelper.cos(r) * width;
				double xo = posX + x;
				double yo = posY + height - 0.2;
				double zo = posZ + y;
				mod_Zeitgeist.proxy.gore(worldObj, xo, yo, zo, x * 2f, y * 2f, 0, 10, 4f);
				zei_EntityLimb ep = new zei_EntityLimb(worldObj, xo, yo, zo, rotationYaw, 1);
				worldObj.spawnEntityInWorld(ep);
			}
			if (j == 2) {
				mod_Zeitgeist.proxy.gore(worldObj, posX, posY + height, posZ, 0, 0, 1f, 10, 4f);
				zei_EntityLimb ep = new zei_EntityLimb(worldObj, posX, posY + height, posZ, rotationYaw, 0);
				worldObj.spawnEntityInWorld(ep);
			}
			setSever(j);
		}
		return super.attackEntityFrom(damagesource, i);
	}

	public EnumCreatureAttribute func_40124_t() {
		return EnumCreatureAttribute.UNDEAD;
	}

	@Override
	public float getMaxHealth() {
		return 10;
	}

	public int getSever() {
		return mod_Zeitgeist.proxy.getInt(dataWatcher, 16);
	}

	@Override
	public void onLivingUpdate() {
		/*
		 * if(worldObj.isDaytime() && !worldObj.multiplayerWorld) { float f =
		 * getEntityBrightness(1.0F); if(f > 0.5F &&
		 * worldObj.canBlockSeeTheSky(MathHelper.floor_double(posX),
		 * MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) &&
		 * rand.nextFloat() * 30F < (f - 0.4F) * 2.0F) { func_40046_d(8); } }
		 */
		int s = getSever();
		if (s < 5) {
			float r = (float) Math.toRadians(270 + rotationYaw);
			double x = MathHelper.sin(r) * width * 0.5;
			double y = -MathHelper.cos(r) * width * 0.5;
			if (s < 5) {
				mod_Zeitgeist.proxy.gore(worldObj, posX + x, posY + height - 0.4, posZ + y, x, y, 0, 1);
			}
			if (s < 4) {
				mod_Zeitgeist.proxy.gore(worldObj, posX - x, posY + height - 0.4, posZ - y, -x, -y, 0, 1);
			}
			if (s < 3) {
				mod_Zeitgeist.proxy.gore(worldObj, posX, posY + height - 0.2, posZ, 0, 0, 1, 2);
			}
		}
		super.onLivingUpdate();
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		setSever(nbttagcompound.getInteger("sever"));
	}

	public void setSever(int i) {
		dataWatcher.updateObject(16, Integer.valueOf(i));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setInteger("sever", getSever());
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(16, Integer.valueOf((byte) 5));
	}

	protected int func_40119_ar() {
		return 2;
	}

	@Override
	protected String getDeathSound() {
		return "mob.zombiedeath";
	}

	@Override
	protected int getDropItemId() {
		return Item.rottenFlesh.itemID;
	}

	@Override
	protected String getHurtSound() {
		return "mob.zombiehurt";
	}

	@Override
	protected String getLivingSound() {
		return "mob.zombie";
	}
}
