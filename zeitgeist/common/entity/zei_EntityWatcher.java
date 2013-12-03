package zeitgeist.common.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import zeitgeist.common.zei_IBot;
import zeitgeist.common.entity.ai.zei_EntityAIArrowAttack;

public class zei_EntityWatcher extends EntityMob implements zei_IBot {
	public zei_EntityWatcher(World world) {
		super(world);
		health = 60;
		moveSpeed = 0.2F;
		setSize(1.0F, 3.8F);
		texture = "/zeitgeist/watcher.png";
		this.tasks.addTask(4, new zei_EntityAIArrowAttack(this, this.moveSpeed, 1, 6));
		tasks.addTask(4, new EntityAIAttackOnCollide(this, moveSpeed, true));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
		tasks.addTask(9, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 24F, 1, true));
	}

	public zei_EntityWatcher(World world, double d, double d1, double d2) {
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
		return 60;
	}

	// private static final ItemStack defaultHeldItem;
	@Override
	public int getMaxSpawnedInChunk() {
		return 3;
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public void onDeath(DamageSource damagesource) {
		super.onDeath(damagesource);
		if (!worldObj.isRemote) {
			Dropper();// a(field_34905_c > 0);
		}
		this.setDead();
	}

	@Override
	public void onLivingUpdate() {
		/*
		 * if(isWet() && rand.nextInt(20)==0) { super.attackEntityFrom(null,1);
		 * }
		 */
		super.onLivingUpdate();
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
				zei_EntityLaser entityfireball = new zei_EntityLaser(worldObj, this, d5, d6, d7, 0.4D);
				// double d8 = 4D;
				// Vec3D vec3d = getLook(1.0F);
				entityfireball.posX = posX;// + vec3d.xCoord * d8;
				entityfireball.posY = posY + (height);
				entityfireball.posZ = posZ;// + vec3d.zCoord * d8;
				worldObj.spawnEntityInWorld(entityfireball);
				worldObj.playSoundAtEntity(this, "automatons.spark", 1.0F, 1.0F);
				attackTime = 10;
			}
			rotationYaw = (float) ((Math.atan2(d1, d) * 180D) / Math.PI) - 90F;
			hasAttacked = true;
		}
	}

	protected void Dropper() {
		int i = rand.nextInt(3);
		for (int j = 0; j < i; j++) {
			// dropItem(zei_Ids.crystal, 1);
		}
	}

	@Override
	protected Entity findPlayerToAttack() {
		List list = worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(1D, 3D, 1D));
		if (!list.isEmpty()) {
			return (EntityPlayer) list.get(0);
		}
		return null;
	}

	@Override
	protected String getDeathSound() {
		return "automatons.thunk";
	}

	@Override
	protected int getDropItemId() {
		return 0;
	}

	@Override
	protected String getHurtSound() {
		return "automatons.thunk";
	}

	@Override
	protected String getLivingSound() {
		return "";
	}
}
