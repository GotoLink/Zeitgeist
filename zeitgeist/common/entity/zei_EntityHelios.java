package zeitgeist.common.entity;

import zeitgeist.common.zei_IBot;
import zeitgeist.common.zei_Ids;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class zei_EntityHelios extends zei_EntityFlying2 implements zei_IBot {
	public zei_EntityHelios(World world) {
		super(world);
		texture = "/zeitgeist/zei_ModelHelios.png";
		moveSpeed = 1.0F;
		health = 3;
		setSize(0.5F, 0.5F);
		courseChangeCooldown = 10;
	}

	public zei_EntityHelios(World world, double d, double d1, double d2) {
		this(world);
		setPosition(d, d1 + (double) yOffset, d2);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
	}

	public void onLivingUpdate() {
		if (isWet()) {
			dropper();
		}
		super.onLivingUpdate();
	}

	public void onDeath(DamageSource par1DamageSource) {
		super.onDeath(par1DamageSource);
		dropper();
	}

	void dropper() {
		if (rand.nextInt(3) == 0) {
			entityDropItem(new ItemStack(zei_Ids.soulCore, 1, 0), 0.0F);
		}
		setDead();
	}

	protected void updateEntityActionState() {
		despawnEntity();
		double d = waypointX - posX;
		double d1 = waypointY - posY;
		double d2 = waypointZ - posZ;
		double d3 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
		double d9 = MathHelper.sqrt_double(motionX * motionX + motionZ
				* motionZ + motionY * motionY);
		if (d9 < 0.5D) // || d3 > 60D || d9<1D)
		{
			waypointX = posX
					+ (double) ((rand.nextFloat() * 2.0F - 1.0F) * 16F);
			waypointZ = posZ
					+ (double) ((rand.nextFloat() * 2.0F - 1.0F) * 16F);
			if (posY < 50) {
				waypointY = posY
						+ (double) ((rand.nextFloat() * 2.0F - 1.0F) * 16F);
			} else {
				waypointY = rand.nextInt(5)
						+ worldObj.getTopSolidOrLiquidBlock((int) waypointX,
								(int) waypointZ) + 1;
			}
		}
		if (courseChangeCooldown-- <= 0) {
			courseChangeCooldown += rand.nextInt(20) + 10;
			if (isCourseTraversable(waypointX, waypointY, waypointZ, d3)) {
				motionX += (d / d3) * 0.1D;
				motionY += (d1 / d3) * 0.1D;
				motionZ += (d2 / d3) * 0.1D;
			} else {
				waypointX = posX
						+ (double) ((rand.nextFloat() * 2.0F - 1.0F) * 16F);
				waypointY = posY
						+ (double) ((rand.nextFloat() * 2.0F - 1.0F) * 16F);
				waypointZ = posZ
						+ (double) ((rand.nextFloat() * 2.0F - 1.0F) * 16F);
			}
		}
		renderYawOffset = rotationYaw = (-(float) Math.atan2(motionX, motionZ) * 180F)
				/ (float) Math.PI;
	}

	private boolean isCourseTraversable(double d, double d1, double d2,
			double d3) {
		double d4 = (waypointX - posX) / d3;
		double d5 = (waypointY - posY) / d3;
		double d6 = (waypointZ - posZ) / d3;
		AxisAlignedBB axisalignedbb = boundingBox.copy();
		for (int i = 1; (double) i < d3; i++) {
			axisalignedbb.offset(d4, d5, d6);
			if (worldObj.getCollidingBoundingBoxes(this, axisalignedbb).size() > 0) {
				return false;
			}
		}
		return true;
	}

	public boolean getCanSpawnHere() {
		return true;
	}

	protected void attackEntity(Entity entity, float f) {
	}

	protected float getSoundVolume() {
		return 0.2F;
	}

	protected String getLivingSound() {
		return "automatons.beep";
	}

	protected String getHurtSound() {
		return "automatons.clank";
	}

	protected String getDeathSound() {
		return "automatons.botdie";
	}

	protected int getDropItemId() {
		return 0;
	}

	public float getMaxHealth() {
		return 3;
	}

	public int courseChangeCooldown;
	public double waypointX;
	public double waypointY;
	public double waypointZ;
}
