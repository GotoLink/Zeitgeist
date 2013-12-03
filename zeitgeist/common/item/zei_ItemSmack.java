package zeitgeist.common.item;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class zei_ItemSmack extends Item {
	public zei_ItemSmack(int i) {
		super(i);
		setMaxDamage(25);
		maxStackSize = 1;
	}

	public boolean hitEntity(ItemStack itemstack, EntityLiving entity, EntityLiving me) {
		// entityliving
		itemstack.damageItem(1, me);
		POW(entity, me);
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		itemstack.damageItem(10, entityplayer);
		List L = world.getEntitiesWithinAABBExcludingEntity(entityplayer, entityplayer.boundingBox.expand(6D, 3D, 6D));
		if (!L.isEmpty()) {
			for (int i = 0; i < L.size(); i++) {
				Object o = L.get(i);
				if (o instanceof EntityLiving) {
					POW((EntityLiving) o, entityplayer);
				}
			}
		}
		return itemstack;
	}

	public void POW(EntityLiving entity, EntityLivingBase me) {
		World worldObj = me.worldObj;
		worldObj.playSoundAtEntity(me, "mob.clank", 1.0F, 1.0F);
		if (worldObj.isRemote) {
			return;
		}
		if (entity.riddenByEntity == me || entity.ridingEntity == me) {
			return;
		}
		double d = entity.posX - me.posX;
		double d1 = entity.posZ - me.posZ;
		double f1 = d1;
		double f = d;
		double d2 = MathHelper.abs_max(d, d1);
		if (d2 >= 0.0099999997764825821D) {
			d2 = MathHelper.sqrt_double(d2);
			d /= d2;
			d1 /= d2;
			double d3 = 1.0D / d2;
			if (d3 > 1.0D) {
				d3 = 1.0D;
			}
			d *= d3;
			d1 *= d3;
			d *= 0.05000000074505806D;
			d1 *= 0.05000000074505806D;
			d *= 1.0F; // - entityCollisionReduction;
			d1 *= 1.0F; // - entityCollisionReduction;
			me.addVelocity(-d, 0.0D, -d1);
			entity.addVelocity(f, 0.75D, f1);
			// entity.posY+=5;
		}
	}
}
