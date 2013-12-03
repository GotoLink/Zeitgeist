package zeitgeist.common.entity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class zei_EntityRagdoll extends Entity {
	public int age;
	private int health;
	public int type = 0;
	Entity bound;
	double bx;
	double by;
	double bz;

	public zei_EntityRagdoll(World world, double d, double d1, double d2,
			float rot, int type, Entity bound) {
		super(world);
		age = 0;
		health = 5;
		// field_804_d = (float)(Math.random() * Math.PI * 2D);
		setSize(0.25F, 0.25F);
		yOffset = height / 2.0F;
		setPosition(d, d1, d2);
		rotationYaw = rot;
		// motionX = (float)(Math.random() * 0.20000000298023221D -
		// 0.10000000149011611D);
		// motionY = 0.20000000298023221D;
		// motionZ = (float)(Math.random() * 0.20000000298023221D -
		// 0.10000000149011611D);
		this.type = type;
		this.bound = bound;
		setPo(d, d1 - 0.2, d2);
		setBo(d, d1, d2);
		setInit();
	}

	public String getEntityTexture() {
		return "/mob/zombie.png";
	}

	protected boolean canTriggerWalking() {
		return false;
	}

	public zei_EntityRagdoll(World world) {
		super(world);
		age = 0;
		health = 5;
		// field_804_d = (float)(Math.random() * Math.PI * 2D);
		setSize(0.25F, 0.25F);
		yOffset = height / 2.0F;
	}

	protected void entityInit() {
	}

	double px;
	double py;
	double pz;
	double hypo;

	public void setInit() {
		double vx = bx;
		double vy = by;
		double vz = bz;
		double ex = posX;
		double ey = posY;
		double ez = posZ;
		double dx = vx - ex;
		double dy = vy - ey;
		double dz = vz - ez;
		hypo = Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	public void setPo(double x, double y, double z) {
		px = posX = x;
		py = posY = y;
		pz = posZ = z;
	}

	public void setBo(double x, double y, double z) {
		bx = x;
		by = y;
		bz = z;
	}

	public void refresh() {
		double tx = posX;
		double ty = posY;
		double tz = posZ;
		motionX -= posX - px;
		motionY -= (posY - py);
		motionZ -= posZ - pz;
		px = tx;
		py = ty;
		pz = tz;
		float r = (float) Math.toRadians(270 + bound.rotationYaw);
		double x = MathHelper.sin(r) * bound.width * 0.5;
		double y = -MathHelper.cos(r) * bound.width * 0.5;
		double xo = bound.posX + x;
		double yo = bound.posY + bound.height - 0.2;
		double zo = bound.posZ + y;
		bx = xo;
		by = yo;
		bz = zo;
	}

	public void process() {
		double vx = bx;
		double vy = by;
		double vz = bz;
		double ex = posX;
		double ey = posY;
		double ez = posZ;
		double dx = vx - ex;
		double dy = vy - ey;
		double dz = vz - ez;
		double h = Math.sqrt(dx * dx + dy * dy + dz * dz);
		double diff = hypo - h;
		double df = diff / h;
		double offx = dx * df / 2;
		double offy = dy * df / 2;
		double offz = dz * df / 2;
		motionX -= offx;
		motionY -= offy;
		motionZ -= offz;
		// bound.motionX+=offx;
		// bound.motionY+=offy;
		// bound.motionZ+=offz;
		double h2 = Math.sqrt(dx * dx + dz * dz);
		rotationYaw = (float) (Math.atan2(-dx, dz)); // Math.toDegrees(angrad)
		rotationPitch = (float) (Math.atan2(h2, dy));
	}

	public void onUpdate() {
		super.onUpdate();
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		motionY -= 0.039999999105930328D;
		if (worldObj.getBlockMaterial(MathHelper.floor_double(posX),
				MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) == Material.lava) {
			// motionY = 0.20000000298023221D;
			motionX = (rand.nextFloat() - rand.nextFloat()) * 0.2F;
			motionZ = (rand.nextFloat() - rand.nextFloat()) * 0.2F;
			worldObj.playSoundAtEntity(this, "random.fizz", 0.4F,
					2.0F + rand.nextFloat() * 0.4F);
		}
		pushOutOfBlocks(posX, (boundingBox.minY + boundingBox.maxY) / 2D, posZ);
		/*
		 * motionX-=(this.posX - bound.posX)/20; motionY-=(this.posY -
		 * bound.posY)/20; motionZ-=(this.posZ - bound.posZ)/20;
		 */
		// setPo(bound.posX,bound.posY+bound.height,bound.posZ);
		refresh();
		process();
		moveEntity(motionX, motionY, motionZ);
		float f = 0.98F;
		if (onGround) {
			f = 0.5880001F;
			int i = worldObj.getBlockId(MathHelper.floor_double(posX),
					MathHelper.floor_double(boundingBox.minY) - 1,
					MathHelper.floor_double(posZ));
			if (i > 0) {
				f = Block.blocksList[i].slipperiness * 0.98F;
			}
		}
		motionX *= f;
		motionY *= 0.98000001907348633D;
		motionZ *= f;
		if (onGround) {
			// motionY *= -0.5D;
		}
		age++;
		if (age >= 9000) {
			setDead();
		}
	}

	public boolean handleWaterMovement() {
		return worldObj.handleMaterialAcceleration(boundingBox, Material.water,
				this);
	}

	protected void dealFireDamage(int i) {
		attackEntityFrom(DamageSource.inFire, i);
	}

	public boolean attackEntityFrom(DamageSource damagesource, int i) {
		setBeenAttacked();
		health -= i;
		if (health <= 0) {
			setDead();
		}
		return false;
	}

	/*
	 * public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
	 * nbttagcompound.setShort("Health", (byte)health);
	 * nbttagcompound.setShort("Age", (short)age);
	 * nbttagcompound.setCompoundTag("Item", item.writeToNBT(new
	 * NBTTagCompound())); }
	 */
	/*
	 * public void readEntityFromNBT(NBTTagCompound nbttagcompound) { health =
	 * nbttagcompound.getShort("Health") & 0xff; age =
	 * nbttagcompound.getShort("Age"); NBTTagCompound nbttagcompound1 =
	 * nbttagcompound.getCompoundTag("Item"); item =
	 * ItemStack.loadItemStackFromNBT(nbttagcompound1); if(item == null) {
	 * setEntityDead(); } }
	 */
	public void onCollideWithPlayer(EntityPlayer entityplayer) {
		if (worldObj.isRemote) {
			return;
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
	}
}
