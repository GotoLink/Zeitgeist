package zeitgeist.common.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class zei_EntityMusicMaker extends EntityAgeable {
	public float cameraYaw;
	public double field_20061_w;
	public double field_20062_v;
	public double field_20063_u;
	public double field_20064_t;
	public double field_20065_s;
	public double field_20066_r;
	public InventoryPlayer inventory;
	public float prevCameraYaw;
	public String username;
	private ItemStack itemInUse;
	private int itemInUseCount;

	public zei_EntityMusicMaker(World par1World) {
		super(par1World);
		texture = "/zeitgeist/zei_ModelMusic.png";
		moveSpeed = 0.23F;
		// attackStrength = 0;
		getNavigator().setBreakDoors(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIBreakDoor(this));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, moveSpeed, false));
		tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, moveSpeed, true));
		tasks.addTask(4, new EntityAIMoveTwardsRestriction(this, moveSpeed));
		tasks.addTask(5, new EntityAIMoveThroughVillage(this, moveSpeed, false));
		tasks.addTask(6, new EntityAIWander(this, moveSpeed));
		tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
		tasks.addTask(7, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		// targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
		// net.minecraft.src.EntityPlayer.class, 16F, 0, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 16F, 0, false));
		username = "King Derp";
		setSize(0.8F, 4.0F);
		// inventory = new InventoryPlayer(this);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	@Override
	public float getEyeHeight() {
		return height * 0.83F;
	}

	public ItemStack getItemInUse() {
		return itemInUse;
	}

	public int getItemInUseCount() {
		return itemInUseCount;
	}

	@Override
	public float getMaxHealth() {
		return 20;
	}

	@Override
	public int getTotalArmorValue() {
		return 2;
	}

	@Override
	public void onLivingUpdate() {
		if (worldObj.isDaytime() && !worldObj.isRemote) {
			float f = getBrightness(1.0F);
			if (f > 0.5F && worldObj.canBlockSeeTheSky(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) && rand.nextFloat() * 30F < (f - 0.4F) * 2.0F) {
				// setFire(8);
			}
		}
		prevCameraYaw = cameraYaw;
		float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
		if (f > 0.1F) {
			f = 0.1F;
		}
		cameraYaw += (f - cameraYaw) * 0.4F;
		super.onLivingUpdate();
	}

	@Override
	public void onUpdate() {
		field_20066_r = field_20063_u;
		field_20065_s = field_20062_v;
		field_20064_t = field_20061_w;
		double d = posX - field_20063_u;
		double d1 = posY - field_20062_v;
		double d2 = posZ - field_20061_w;
		double d3 = 10D;
		if (d > d3) {
			field_20066_r = field_20063_u = posX;
		}
		if (d2 > d3) {
			field_20064_t = field_20061_w = posZ;
		}
		if (d1 > d3) {
			field_20065_s = field_20062_v = posY;
		}
		if (d < -d3) {
			field_20066_r = field_20063_u = posX;
		}
		if (d2 < -d3) {
			field_20064_t = field_20061_w = posZ;
		}
		if (d1 < -d3) {
			field_20065_s = field_20062_v = posY;
		}
		field_20063_u += d * 0.25D;
		field_20061_w += d2 * 0.25D;
		field_20062_v += d1 * 0.25D;
		super.onUpdate();
	}

	@Override
	protected void dropRareDrop(int par1) {
		switch (rand.nextInt(4)) {
		case 0:
			dropItem(Item.swordIron.itemID, 1);
			break;
		case 1:
			dropItem(Item.helmetIron.itemID, 1);
			break;
		case 2:
			dropItem(Item.ingotIron.itemID, 1);
			break;
		case 3:
			dropItem(Item.shovelIron.itemID, 1);
			break;
		}
	}

	@Override
	protected String getDeathSound() {
		return "mob.zombiedeath";
	}

	@Override
	protected int getDropItemId() {
		return Item.rottenFlesh.itemID;
	}

	@Override
	protected String getHurtSound() {
		return "mob.zombiehurt";
	}

	@Override
	protected String getLivingSound() {
		return "mob.zombie";
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}
}
