package zeitgeist.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import zeitgeist.common.mod_Zeitgeist;
import zeitgeist.common.zei_IBot;
import zeitgeist.common.zei_Ids;
import zeitgeist.common.entity.ai.zei_EntityAIBotFollowOwner;
import zeitgeist.common.entity.ai.zei_EntityAIBotOwnerHurtByTarget;
import zeitgeist.common.entity.ai.zei_EntityAIBotOwnerHurtTarget;

public class zei_EntitySentry extends zei_EntityOwnedBot implements zei_IBot {
	private EntityPlayer entityplayer;

	public zei_EntitySentry(World world) {
		super(world);
		texture = "/zeitgeist/bit.png";// bit.png"; //bit
		setSize(0.8F, 1.2F);
		moveSpeed = 0.4F;
		getNavigator().setAvoidsWater(true);
		tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F)); // 0.4f
		tasks.addTask(4, new EntityAIAttackOnCollide(this, moveSpeed, true));
		tasks.addTask(5, new zei_EntityAIBotFollowOwner(this, moveSpeed, 8F, 4.0F));
		tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
		tasks.addTask(9, new EntityAILookIdle(this));
		targetTasks.addTask(1, new zei_EntityAIBotOwnerHurtByTarget(this));
		targetTasks.addTask(2, new zei_EntityAIBotOwnerHurtTarget(this));
		targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityChicken.class, 16F, 50, false));
		targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityMob.class, 24F, 1, true));
	}

	public zei_EntitySentry(World world, double d, double d1, double d2, int turn, String s) {
		this(world);
		setPosition(d, d1 + yOffset, d2);
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
		mod_Zeitgeist.proxy.rotateEntity(this, turn * 90);
		setBotOwner(s);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 5);
	}

	@Override
	public float getEyeHeight() {
		return height * 0.8F;
	}

	@Override
	public float getMaxHealth() {
		return 20;
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
			setDead();
		}
	}

	@Override
	public void onUpdate() {
		// System.out.println(this.rotationYawHead);
		super.onUpdate();
		if (isWet()) {
			if (isEntityAlive()) {
				Dropper();
				// setDead();
			}
		}
	}

	@Override
	protected void attackEntity(Entity entity, float f) {
		if (attackTime <= 0 && f < 5F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY) {
			attackTime = 2;
			attackEntityAsMob(entity);
		}
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	protected String getDeathSound() {
		return "random.glass";
	}

	@Override
	protected String getHurtSound() {
		return "step.stone";
	}

	@Override
	protected String getLivingSound() {
		return "automatons.techy";
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	protected boolean isMovementCeased() {
		return entityplayer == null;
	}

	void Dropper() {
		for (int j = 0; j < 20; j++) {
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			worldObj.spawnParticle("explode", (posX + rand.nextFloat() * width * 2.0F) - width, posY + rand.nextFloat() * height, (posZ + rand.nextFloat() * width * 2.0F) - width, d, d1, d2);
		}
		if (!worldObj.isRemote) {
			EntityItem entityitem = new EntityItem(worldObj, posX, posY + 1, posZ, new ItemStack(zei_Ids.soulCore, 1, 0));
			entityitem.delayBeforeCanPickup = 10;
			entityitem.addVelocity(Math.random() * 0.25 - 0.125, 0.25, Math.random() * 0.25 - 0.125);
			worldObj.spawnEntityInWorld(entityitem);
			//
			int meta = MathHelper.floor_double(rotationYawHead * 4.0F / 360.0F + 0.5D) & 3;
			worldObj.setBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ), zei_Ids.sentryBlock, meta, 3);
			setDead();
		}
	}
}
