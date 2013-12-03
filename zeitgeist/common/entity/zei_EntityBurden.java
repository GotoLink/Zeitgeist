package zeitgeist.common.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumGameType;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import zeitgeist.common.zei_IBot;
import zeitgeist.common.zei_Ids;
import zeitgeist.common.world.zei_PseudoSaveHandler;
import zeitgeist.common.world.zei_PseudoWorld;
import zeitgeist.common.world.zei_PseudoWorldProvider;

public class zei_EntityBurden extends zei_EntityOwnedBot implements zei_IBot {
	public zei_EntityBurden(World world) {
		super(world);
		texture = "/zeitgeist/bobby.png";
		setSize(0.9F, 0.9F);
		// storage=new zei_PseudoWorld[100];
		// for(int i=0;i<100;i++){
		storage = new zei_PseudoWorld(new zei_PseudoSaveHandler(), "fake",
				new zei_PseudoWorldProvider(), new WorldSettings(0,
						EnumGameType.NOT_SET, false, false, WorldType.FLAT));
		storage.setBlock(0, 0, 0, 4);
		// }
	}

	public zei_PseudoWorld storage;

	public zei_EntityBurden(World world, double d, double d1, double d2) {
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

	/*
	 * public void onLivingUpdate() { if (isWet()) { setDead(); }
	 * 
	 * super.onLivingUpdate(); }
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

	public boolean getCanSpawnHere() {
		return true;
	}

	protected int getDropItemId() {
		return 0;
	}

	public void onDeath(DamageSource damagesource) {
		super.onDeath(damagesource);
		Dropper();
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
			if (rand.nextInt(3) == 0) {
				entityDropItem(new ItemStack(zei_Ids.craftSet1, 1, 11), 0.0F);
			}
			deathTime = 999; // setEntityDead();
		}
	}

	@Override
	public float getMaxHealth() {
		return 5;
	}
}
