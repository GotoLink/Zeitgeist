package zeitgeist.common.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import zeitgeist.common.zei_IBot;
import zeitgeist.common.zei_Ids;

public class zei_EntityGuard extends zei_EntityBot implements zei_IBot {
	public zei_EntityGuard(World world) {
		super(world);
		health = 20;
		moveSpeed = 0.0F;
		setSize(0.5F, 0.5F);
		texture = "/zeitgeist/guardSkin.png";
	}

	public zei_EntityGuard(World world, double d, double d1, double d2) {
		this(world);
		setPosition(d, d1 + yOffset, d2);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
	}

	public boolean attackEntityFrom(DamageSource damagesource, int i) {
		Entity entity = damagesource.getEntity();
		if (entity != null && entity != this && (entity instanceof EntityPlayer)) {
			i = 20;
		}
		return super.attackEntityFrom(damagesource, i);
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public float getMaxHealth() {
		return 20;
	}

	@Override
	public void onDeath(DamageSource damagesource) {
		super.onDeath(damagesource);
		if (!worldObj.isRemote) {
			Dropper();// a(field_34905_c > 0);
		}
		setDead();
	}

	@Override
	public void onLivingUpdate() {
		/*
		 * if(newPosRotationIncrements > 0) { double d = posX + (newPosX - posX)
		 * / (double)newPosRotationIncrements; double d1 = posY + (newPosY -
		 * posY) / (double)newPosRotationIncrements; double d2 = posZ + (newPosZ
		 * - posZ) / (double)newPosRotationIncrements; double d3; }
		 */
		if (!worldObj.isRemote) {
			if (!worldObj.getBlockMaterial(MathHelper.floor_double(posX), MathHelper.floor_double(posY) - 1, MathHelper.floor_double(posZ)).isSolid()) {
				Dropper();
			}
			updateEntityActionState();
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
	}

	@Override
	protected void attackEntity(Entity entity, float f) {
		if (f < 15F) {
			double d = entity.posX - posX;
			double d1 = entity.posZ - posZ;
			if (attackTime == 0) {
				Entity targetedEntity = entity;
				double d5 = targetedEntity.posX - posX;
				double d6 = (targetedEntity.boundingBox.minY + targetedEntity.height / 2.0F) - (posY + (height));
				double d7 = targetedEntity.posZ - posZ;
				zei_EntityLaser entityfireball = new zei_EntityLaser(worldObj, this, d5, d6, d7, 0.1D);
				entityfireball.posX = posX;// + vec3d.xCoord * d8;
				entityfireball.posY = posY + (height / 1.5F);
				entityfireball.posZ = posZ;// + vec3d.zCoord * d8;
				worldObj.spawnEntityInWorld(entityfireball);
				worldObj.playSoundAtEntity(this, "mob.fwoom", 1.0F, 1.0F);
				attackTime = 40;
			}
			rotationYaw = (float) ((Math.atan2(d1, d) * 180D) / Math.PI) - 90F;
		}
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	protected Entity findPlayerToAttack() {
		List list = worldObj.getEntitiesWithinAABB(EntityMob.class, boundingBox.expand(12D, 6D, 12D));
		List list2 = worldObj.getEntitiesWithinAABB(EntitySlime.class, boundingBox.expand(12D, 6D, 12D));
		list.addAll(list2);
		if (!list.isEmpty()) {
			return closest(list);
		}
		return null;
	}

	@Override
	protected String getDeathSound() {
		return "random.glass";
	}

	@Override
	protected int getDropItemId() {
		return Item.arrow.itemID;
	}

	@Override
	protected String getHurtSound() {
		return "automatons.clank";
	}

	@Override
	protected String getLivingSound() {
		return "automatons.swee";
	}

	@Override
	protected float getSoundVolume() {
		return 0.1F;
	}

	private Entity closest(List list) {
		double d4 = 9000D;
		Entity ent = null;
		for (int i = 0; i < list.size(); i++) {
			Entity ent1 = (Entity) list.get(i);
			double d5 = ent1.getDistanceSq(posX, posY, posZ);
			if (d5 < d4) {
				d4 = d5;
				ent = ent1;
			}
		}
		return ent;
	}

	void Dropper() {
		for (int j = 0; j < 20; j++) {
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			worldObj.spawnParticle("explode", (posX + rand.nextFloat() * width * 2.0F) - width, posY + rand.nextFloat() * height, (posZ + rand.nextFloat() * width * 2.0F) - width, d, d1, d2);
		}
		if (!worldObj.isRemote) {
			entityDropItem(new ItemStack(zei_Ids.guard, 1, 0), 0.0F);
			setDead();
		}
	}
}
