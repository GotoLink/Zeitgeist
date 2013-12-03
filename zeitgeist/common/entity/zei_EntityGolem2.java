package zeitgeist.common.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import zeitgeist.common.zei_IBot;
import zeitgeist.common.zei_Ids;

public class zei_EntityGolem2 extends zei_EntityAniBot implements zei_IBot {
	protected Entity entityplayer;

	public zei_EntityGolem2(World world) {
		super(world);
		texture = "/zeitgeist/oooh.png";
		health = 20;
		moveSpeed = 1.1F;
		isImmuneToFire = true;
	}

	public zei_EntityGolem2(World world, double d, double d1, double d2) {
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
	public boolean attackEntityAsMob(Entity entity) {
		return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 3);
	}

	@Override
	public boolean attackEntityFrom(DamageSource damagesource, int i) {
		moveSpeed = 1.6F;
		Entity e = damagesource.getEntity();
		if (e != null || e != this) {
			setTarget(e);
		}
		super.attackEntityFrom(damagesource, i);
		return true;
	}

	@Override
	public EntityAnimal createChild(EntityAgeable entityanimal) {
		return new zei_EntityGolem2(worldObj);
	}

	@Override
	public float getMaxHealth() {
		return 20;
	}

	public void knockBack(Entity entity, int i, double d, double d1) {
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
		if (isWet()) {
			Dropper();
		}
		super.onLivingUpdate();
	}

	@Override
	protected void attackEntity(Entity entity, float f) {
		if (attackTime <= 0 && f < 2.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY) {
			attackTime = 40;
			attackEntityAsMob(entity);
		}
	}

	@Override
	protected String getDeathSound() {
		return "step.gravel";
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
	protected void updateEntityActionState() {
		super.updateEntityActionState();
		if (entityplayer == null && entityToAttack == null) {
			List L = worldObj.getEntitiesWithinAABB(EntityPlayer.class, boundingBox.expand(4D, 3D, 4D));
			if (!L.isEmpty()) {
				entityplayer = (Entity) L.get(0);
			}
		} else {
			float f = getDistanceToEntity(entityplayer);
			if (f > 7F) {
				getPathOrWalkableBlock(entityplayer, f);
			}
		}
	}

	private void getPathOrWalkableBlock(Entity entity, float f) {
		PathEntity pathentity = worldObj.getPathEntityToEntity(this, entity, 16F, true, false, false, true);
		/*
		 * isWoddenDoorAllowed,isMovementBlockAllowed,isPathingInWater,
		 * canEntityDrown
		 */
		if (pathentity == null && f > 20F) {
			int i = MathHelper.floor_double(entity.posX) - 2;
			int j = MathHelper.floor_double(entity.posZ) - 2;
			int k = MathHelper.floor_double(entity.boundingBox.minY);
			for (int l = 0; l <= 4; l++) {
				for (int i1 = 0; i1 <= 4; i1++) {
					if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && worldObj.isBlockNormalCube(i + l, k - 1, j + i1) && !worldObj.isBlockNormalCube(i + l, k, j + i1)
							&& !worldObj.isBlockNormalCube(i + l, k + 1, j + i1)) {
						setLocationAndAngles(i + l + 0.5F, k, j + i1 + 0.5F, rotationYaw, rotationPitch);
						return;
					}
				}
			}
		} else {
			setPathToEntity(pathentity);
		}
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
			entityDropItem(new ItemStack(88, 1, 0), 0.0F);
			setDead();
		}
	}
}
