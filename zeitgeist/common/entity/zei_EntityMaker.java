package zeitgeist.common.entity;

import zeitgeist.common.zei_IBot;
import zeitgeist.common.zei_Universal;
import zeitgeist.common.entity.ai.zei_EntityAIMakerBuild;
import zeitgeist.common.entity.ai.zei_EntityAIMakerMake;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class zei_EntityMaker extends zei_EntityBot implements zei_IBot {
	public boolean making = false;

	public zei_EntityMaker(World world) {
		super(world);
		texture = "/zeitgeist/golem1.png";
		setSize(0.6F, 0.8F);
		getNavigator().setAvoidsWater(true);
		// tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new zei_EntityAIMakerBuild(this));
		tasks.addTask(1, new EntityAIPanic(this, 0.38F));
		// tasks.addTask(2, new EntityAIMate(this, f));
		tasks.addTask(3, new EntityAITempt(this, 0.25F, Item.redstone.itemID,
				false));
		tasks.addTask(6, new EntityAIWander(this, 0.23f));
		tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6F));
		tasks.addTask(8, new EntityAILookIdle(this));
		tasks.addTask(9, new zei_EntityAIMakerMake(this));
	}

	public zei_EntityMaker(World world, double d, double d1, double d2) {
		this(world);
		setPosition(d, d1 + (double) yOffset, d2);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
		setPathToEntity(null);
	}

	public boolean isAIEnabled() {
		return true;
	}

	public void onLivingUpdate() {
		if (isWet()) {
			setDead();
		}
		xx = MathHelper.floor_double(posX);
		yy = MathHelper.floor_double(posY);
		zz = MathHelper.floor_double(posZ);
		super.onLivingUpdate();
	}

	public int xx, yy, zz;

	public void build(int xi, int yi, int zi) {
		// int id=worldObj.getBlockId(xx, yy-1, zz);
		// if(id==2){
		worldObj.setBlock(xi, yi, zi, 4);
		// }
	}

	/*
	 * protected float getBlockPathWeight(int i, int j, int k) {
	 * if(worldObj.getBlockId(i, j - 1, k) == mod_Automatons.frass.blockID) {
	 * return 10F; } else { return worldObj.getLightBrightness(i, j, k) - 0.5F;
	 * } }
	 */
	public int getMaxSpawnedInChunk() {
		return 100;
	}

	/*
	 * protected void entityInit() { dataWatcher.addObject(16,
	 * Byte.valueOf((byte)0)); }
	 * 
	 * public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
	 * super.writeEntityToNBT(nbttagcompound);
	 * nbttagcompound.setBoolean("Saddle", getSaddled()); }
	 * 
	 * public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
	 * super.readEntityFromNBT(nbttagcompound);
	 * setSaddled(nbttagcompound.getBoolean("Saddle")); }
	 */
	protected float getSoundVolume() {
		return 0.4F;
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

	/*
	 * public boolean interact(EntityPlayer entityplayer) { if(getSaddled() &&
	 * !worldObj.multiplayerWorld && (riddenByEntity == null || riddenByEntity
	 * == entityplayer)) { entityplayer.mountEntity(this); return true; } else {
	 * return false; } }
	 */
	public boolean getCanSpawnHere() {
		return true;
	}

	protected int getDropItemId() {
		return Item.silk.itemID;
	}

	public void onDeath(DamageSource damagesource) {
		super.onDeath(damagesource);
		Dropper();// a(field_34905_c > 0);
		setDead();
	}

	void Dropper() {
		for (int j = 0; j < 20; j++) {
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			worldObj.spawnParticle("explode",
					(posX + (double) (rand.nextFloat() * width * 2.0F))
							- (double) width, posY
							+ (double) (rand.nextFloat() * height),
					(posZ + (double) (rand.nextFloat() * width * 2.0F))
							- (double) width, d, d1, d2);
		}
		if (!worldObj.isRemote) {
			int R = rand.nextInt(2);
			if (R == 0) {
				// List list =
				// worldObj.getEntitiesWithinAABB(net.minecraft.src.EntityBobby.class,
				// boundingBox.expand(24D, 3D, 24D));
				if (worldObj.countEntities(zei_EntityMaker.class) < 50) {
					worldObj.spawnEntityInWorld(new zei_EntityMaker(worldObj,
							posX, posY, posZ));
					worldObj.spawnEntityInWorld(new zei_EntityMaker(worldObj,
							posX, posY, posZ));
				}
			} else {
				if (rand.nextInt(3) == 0) {
					// entityDropItem(new ItemStack(zei_Ids.stuffs, 1, 4),
					// 0.0F);
				}
			}
			deathTime = 999; // setEntityDead();
		}
	}

	@Override
	public float getMaxHealth() {
		return 5;
	}
}
