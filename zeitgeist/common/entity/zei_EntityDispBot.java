package zeitgeist.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import zeitgeist.common.zei_IBot;
import zeitgeist.common.zei_Ids;
import zeitgeist.common.entity.ai.zei_EntityAIDispBotDie;

public class zei_EntityDispBot extends zei_EntityFlying2 implements zei_IBot {
	public int courseChangeCooldown;
	public double waypointX;
	public double waypointY;
	public double waypointZ;
	int originX = 0;
	int originY = 0;
	int originZ = 0;

	public zei_EntityDispBot(World world) {
		super(world);
		texture = "/zeitgeist/zei_ModelHelios.png";
		moveSpeed = 1.0F;
		health = 3;
		setSize(0.5F, 0.5F);
		courseChangeCooldown = 10;
	}

	public zei_EntityDispBot(World world, double d, double d1, double d2, int xx, int yy, int zz) {
		this(world);
		setPosition(d, d1 + yOffset, d2);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
		originX = xx;
		originY = yy;
		;
		originZ = zz;
		tasks.addTask(5, new zei_EntityAIDispBotDie(this, originX, originY, originZ));
	}

	public void Dropper() {
		for (int j = 0; j < 20; j++) {
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			worldObj.spawnParticle("explode", (posX + rand.nextFloat() * width * 2.0F) - width, posY + rand.nextFloat() * height, (posZ + rand.nextFloat() * width * 2.0F) - width, d, d1, d2);
		}
		if (!worldObj.isRemote) {
			// poof();
			setDead();
		}
	}

	@Override
	public boolean getCanSpawnHere() {
		return true;
	}

	@Override
	public float getMaxHealth() {
		return 3;
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public void onLivingUpdate() {
		if (isWet()) {
			health = 0;
		}
		super.onLivingUpdate();
	}

	@Override
	protected void attackEntity(Entity entity, float f) {
	}

	@Override
	protected String getDeathSound() {
		return "automatons.botdie";
	}

	@Override
	protected int getDropItemId() {
		return zei_Ids.soulCore;
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
		return 0.2F;
	}

	@Override
	protected void updateEntityActionState() {
		despawnEntity();
		double d = waypointX - posX;
		double d1 = waypointY - posY;
		double d2 = waypointZ - posZ;
		double d3 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
		double d9 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ + motionY * motionY);
		if (d9 < 0.5D) // || d3 > 60D || d9<1D)
		{
			waypointX = posX + (rand.nextFloat() * 2.0F - 1.0F) * 16F;
			waypointZ = posZ + (rand.nextFloat() * 2.0F - 1.0F) * 16F;
			if (posY < 50) {
				waypointY = posY + (rand.nextFloat() * 2.0F - 1.0F) * 16F;
			} else {
				waypointY = rand.nextInt(5) + worldObj.getTopSolidOrLiquidBlock((int) waypointX, (int) waypointZ) + 1;
			}
		}
		if (courseChangeCooldown-- <= 0) {
			courseChangeCooldown += rand.nextInt(20) + 10;
			if (isCourseTraversable(waypointX, waypointY, waypointZ, d3)) {
				motionX += (d / d3) * 0.1D;
				motionY += (d1 / d3) * 0.1D;
				motionZ += (d2 / d3) * 0.1D;
			} else {
				waypointX = posX + (rand.nextFloat() * 2.0F - 1.0F) * 16F;
				waypointY = posY + (rand.nextFloat() * 2.0F - 1.0F) * 16F;
				waypointZ = posZ + (rand.nextFloat() * 2.0F - 1.0F) * 16F;
			}
		}
		renderYawOffset = rotationYaw = (-(float) Math.atan2(motionX, motionZ) * 180F) / (float) Math.PI;
	}

	private boolean isCourseTraversable(double d, double d1, double d2, double d3) {
		double d4 = (waypointX - posX) / d3;
		double d5 = (waypointY - posY) / d3;
		double d6 = (waypointZ - posZ) / d3;
		AxisAlignedBB axisalignedbb = boundingBox.copy();
		for (int i = 1; i < d3; i++) {
			axisalignedbb.offset(d4, d5, d6);
			if (worldObj.getCollidingBoundingBoxes(this, axisalignedbb).size() > 0) {
				return false;
			}
		}
		return true;
	}
}
