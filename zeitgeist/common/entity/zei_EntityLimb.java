package zeitgeist.common.entity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class zei_EntityLimb extends Entity {
	public int age;
	private int health;
	public int type = 0;

	public zei_EntityLimb(World world, double d, double d1, double d2,
			float rot, int type) {
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
	}

	public String getEntityTexture() {
		return "/mob/zombie.png";
	}

	protected boolean canTriggerWalking() {
		return false;
	}

	public zei_EntityLimb(World world) {
		super(world);
		age = 0;
		health = 5;
		// field_804_d = (float)(Math.random() * Math.PI * 2D);
		setSize(0.25F, 0.25F);
		yOffset = height / 2.0F;
	}

	protected void entityInit() {
	}

	public void onUpdate() {
		super.onUpdate();
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		motionY -= 0.039999999105930328D;
		if (worldObj.getBlockMaterial(MathHelper.floor_double(posX),
				MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) == Material.lava) {
			motionY = 0.20000000298023221D;
			motionX = (rand.nextFloat() - rand.nextFloat()) * 0.2F;
			motionZ = (rand.nextFloat() - rand.nextFloat()) * 0.2F;
			worldObj.playSoundAtEntity(this, "random.fizz", 0.4F,
					2.0F + rand.nextFloat() * 0.4F);
		}
		pushOutOfBlocks(posX, (boundingBox.minY + boundingBox.maxY) / 2D, posZ);
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
			motionY *= -0.5D;
		}
		age++;
		if (age >= 200) {
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
