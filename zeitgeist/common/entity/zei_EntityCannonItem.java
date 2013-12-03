package zeitgeist.common.entity;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import zeitgeist.common.zei_Ids;
import zeitgeist.common.item.zei_ItemSoulCore;

public class zei_EntityCannonItem extends Entity// Item
{
	/**
	 * The age of this EntityItem (used to animate it up and down as well as
	 * expire it)
	 */
	public int age = 0;
	public float field_804_d = (float) (Math.random() * Math.PI * 2.0D);
	/** The item stack of this EntityItem. */
	public ItemStack item;
	private boolean fallz = true;
	// public int delayBeforeCanPickup;
	/** The health of this EntityItem. (For example, damage for tools) */
	private int health = 5;
	private boolean type;

	public zei_EntityCannonItem(boolean type, float speed, boolean falls, ItemStack is, World par1World, double par2, double par4, double par6, double mx, double my, double mz) {
		super(par1World);
		this.setSize(0.1F, 0.1F);
		this.setLocationAndAngles(par2, par4, par6, this.rotationYaw, this.rotationPitch);
		this.setPosition(par2, par4, par6);
		this.item = is;
		this.motionX = speed * mx;
		this.motionY = speed * my;
		this.motionZ = speed * mz;
		this.type = type;
		fallz = falls;
	}

	public zei_EntityCannonItem(World par1World) {
		super(par1World);
		this.setSize(0.1F, 0.1F);
		this.yOffset = this.height / 2.0F;
		this.item = new ItemStack(4, 1, 0);
	}

	public zei_EntityCannonItem(World par1World, double par2, double par4, double par6, ItemStack par8ItemStack) {
		super(par1World);
		this.setSize(0.1F, 0.1F);
		this.yOffset = this.height / 2.0F;
		this.setPosition(par2, par4, par6);
		this.item = par8ItemStack;
		this.rotationYaw = (float) (Math.random() * 360.0D);
		this.motionX = ((float) (Math.random() * 0.2000000029802322D - 0.1000000014901161D));
		this.motionY = 0.2000000029802322D;
		this.motionZ = ((float) (Math.random() * 0.2000000029802322D - 0.1000000014901161D));
	}

	public void BAM() {
		if (!this.isDead) {
			this.worldObj.playSoundEffect(posX, posY, posZ, "random.explode", 0.6F, 1f);
			this.worldObj.spawnParticle("explode", posX, posY, posZ, 0.0D, 0.0D, 0.0D);
			// this.worldObj.newExplosion((Entity)null, this.posX, this.posY,
			// this.posZ, 1.0F, false);
			if (type && item.itemID < 256) {
				int xx = (int) Math.floor(posX);
				int yy = (int) Math.floor(posY);
				int zz = (int) Math.floor(posZ);
				if (worldObj.getBlockId(xx, yy, zz) != zei_Ids.cannon) {
					worldObj.setBlock(xx, yy, zz, item.itemID, item.getItemDamage(), 3);
				}
			} else {
				if (!worldObj.isRemote)
					if (item.itemID == zei_Ids.soulCore) {
						int xx = (int) Math.floor(posX);
						int yy = (int) Math.floor(posY);
						int zz = (int) Math.floor(posZ);
						if (!zei_ItemSoulCore.touch(null, item.getItemDamage(), worldObj, xx, yy - 1, zz)) {
							this.entityDropItem(item, 0);
						}
					} else {
						this.entityDropItem(item, 0);
					}
			}
			this.setDead();
		}
	}

	@Override
	public boolean canAttackWithItem() {
		return true;
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public float getCollisionBorderSize() {
		return 1.0F;
	}

	@Override
	public boolean handleWaterMovement() {
		return this.worldObj.handleMaterialAcceleration(this.boundingBox, Material.water, this);
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
		// BAM();
	}

	@Override
	public void onUpdate() {
		if (!this.worldObj.isRemote && !this.worldObj.blockExists((int) this.posX, (int) this.posY, (int) this.posZ)) {
			this.setDead();
		} else {
			// super.onUpdate();
			// this.setFire(1);
			Vec3 var15 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			Vec3 var2 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			MovingObjectPosition var3 = this.worldObj.clip(var15, var2);
			if (var3 != null) {
				var2 = Vec3.createVectorHelper(var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord);
			}
			// Entity var4 = null;
			List var5 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(0.5D, 0.5D, 0.5D));
			// double var6 = 0.0D;
			for (int var8 = 0; var8 < var5.size(); ++var8) {
				Entity var9 = (Entity) var5.get(var8);
				if (var9.canBeCollidedWith()) {
					var9.attackEntityFrom(DamageSource.explosion, 4);
					BAM();
					break;
					/*
					 * float var10 = 0.3F; AxisAlignedBB var11 =
					 * var9.boundingBox.expand((double)var10, (double)var10,
					 * (double)var10); MovingObjectPosition var12 =
					 * var11.calculateIntercept(var15, var2); if (var12 != null)
					 * { double var13 = var15.distanceTo(var12.hitVec); if(var9
					 * instanceof EntityPlayer){
					 * ((EntityPlayer)var9).addChatMessage("hit"); } if (var13 <
					 * var6 || var6 == 0.0D) { var4 = var9; var6 = var13; } }
					 */
				}
			}
			float var17 = 0.95F;
			if (this.isCollided) {
				BAM();
			}
			if (this.isInWater()) {
				for (int var19 = 0; var19 < 4; ++var19) {
					float var18 = 0.25F;
					this.worldObj.spawnParticle("bubble", this.posX - this.motionX * var18, this.posY - this.motionY * var18, this.posZ - this.motionZ * var18, this.motionX, this.motionY,
							this.motionZ);
				}
				var17 = 0.8F;
			}
			this.prevPosX = this.posX;
			this.prevPosY = this.posY;
			this.prevPosZ = this.posZ;
			if (fallz) {
				this.motionY -= 0.03999999910593033D;
			}
			this.motionX *= var17;
			this.motionY *= var17;
			this.motionZ *= var17;
			this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
			this.moveEntity(this.motionX, this.motionY, this.motionZ);
			++this.age;
			if (this.age >= 200) {
				BAM();
			}
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		this.health = par1NBTTagCompound.getShort("Health") & 255;
		this.age = par1NBTTagCompound.getShort("Age");
		NBTTagCompound var2 = par1NBTTagCompound.getCompoundTag("Item");
		this.item = ItemStack.loadItemStackFromNBT(var2);
		if (this.item == null) {
			this.setDead();
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		par1NBTTagCompound.setShort("Health", ((byte) this.health));
		par1NBTTagCompound.setShort("Age", (short) this.age);
		par1NBTTagCompound.setCompoundTag("Item", this.item.writeToNBT(new NBTTagCompound()));
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	protected void entityInit() {
	}

	protected void func_40071_a(MovingObjectPosition par1MovingObjectPosition) {
		if (!this.worldObj.isRemote) {
			if (par1MovingObjectPosition.entityHit != null && par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.explosion, 4)) {
				;
			}
			BAM();
		}
	}
}
