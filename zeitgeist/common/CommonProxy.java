package zeitgeist.common;

import java.util.Random;

import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import zeitgeist.common.entity.zei_EntityFactotum;
import zeitgeist.common.tile.zei_TileEntityCannon;
import zeitgeist.common.tile.zei_TileEntityDuoFurnace;

public class CommonProxy {
	public Icon blockArch(int i) {
		return null;
	}

	public Icon blockSky(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public Icon blockTech(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public void bomf(World world, int i, int j, int k) {
	}

	public void changeDimension(EntityPlayer ep, int dim) {
		// usePortal(dim);
	}

	public void dig(World world, int x, int y, int z) {
	}

	public void displayGUICannon(EntityPlayer ep, zei_TileEntityCannon t) {
		ep.openGui(mod_Zeitgeist.INSTANCE, zei_GuiManager.GUI_CANNON, ep.worldObj, t.xCoord, t.yCoord, t.zCoord);
	}

	public void duoGui(EntityPlayer ep, zei_TileEntityDuoFurnace furnace) {
		// ModLoader.openGUI(ep, new zei_GuiDuoFurnace(ep.inventory,furnace));
	}

	public void factotumGui(EntityPlayer entityplayer, zei_EntityFactotum F) {
		// ModLoader.openGUI(entityplayer, new
		// zei_GuiFactotum(entityplayer.inventory, F));
	}

	public void fwoo(World world, int i, int j, int k) {
	}

	public World getAWorld() {
		return null;
	}

	public WorldProvider getDimension(int dim) {
		// switch(dim){
		// case -1: return new WorldProviderHell();
		// case 0:return new WorldProviderSurface();
		// case 1:return new WorldProviderEnd();
		// case 99:return new zei_WorldProviderZeitgeist();
		// default: return null;
		// }
		return null;
		// ((WorldProvider)(par0 != -1 ? par0 != 0 ? par0 != 1 ? null : new
		// WorldProviderEnd() : new WorldProviderSurface() : new
		// WorldProviderHell()));
	}

	public int getInt(DataWatcher datawatcher, int i) {
		return datawatcher.getWatchableObjectInt(i);
	}

	public void gore(World world, double posX, double posY, double posZ, double vx, double vy, double vz, int v) {
		gore(world, posX, posY, posZ, vx, vy, vz, v, 2f);
	}

	public void gore(World world, double posX, double posY, double posZ, double vx, double vy, double vz, int v, float size) {
	}

	public void gorey(World world, double posX, double posY, double posZ) {
	}

	public void init() {
	}

	public void pictureGui(EntityPlayer ep, zei_PictureData data, int dam, int imgID) {
		// if(!ep.worldObj.isRemote)
		// ModLoader.openGUI(ep, new
		// zei_GuiEditPicture(ep.username,data,dam,imgID));
	}/*
	 * public static void pictureGui(EntityPlayer ep,zei_PictureData data,int
	 * dam,int imgID){ //EntityPlayerMP mp = (EntityPlayerMP) ep
	 * ep.getNextWidowId(); playerNetServerHandler.sendPacket(new
	 * Packet100OpenWindow(currentWindowId, 0, par1IInventory.getInvName(),
	 * par1IInventory.getSizeInventory())); craftingInventory = new
	 * ContainerChest(inventory, par1IInventory); craftingInventory.windowId =
	 * currentWindowId; craftingInventory.onCraftGuiOpened(this); }
	 */

	public void poof(World world, double posX, double posY, double posZ) {
		String s = "explode";
		Random rand = world.rand;
		for (int i = 0; i < 7; i++) {
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;
			world.spawnParticle(s, (posX + rand.nextFloat() * 1.6F - 0.8f), posY + 0.5f + (rand.nextFloat() * 0.2f), (posZ + rand.nextFloat() * 1.6F) - 0.8f, d, d1, d2);
		}
	}

	public void rotateEntity(Entity entity, float r) {
		entity.rotationYaw = r;
		entity.setRotation(r, 0);
		double xHeading = -MathHelper.sin((entity.rotationYaw * 3.141593F) / 180F);
		double zHeading = MathHelper.cos((entity.rotationYaw * 3.141593F) / 180F);
		entity.motionX = 0.01 * xHeading;// *MathHelper.cos((entity.rotationPitch
											// / 180F) * 3.141593F);
		// entity.motionY = -0.8*MathHelper.sin((entity.rotationPitch / 180F) *
		// 3.141593F);
		entity.motionZ = 0.01 * zHeading;// *MathHelper.cos((entity.rotationPitch
											// / 180F) * 3.141593F);
		entity.setPosition(entity.posX + xHeading * 0.01, entity.posY, entity.posZ + zHeading * 0.01);
	}

	public void toteGui(EntityPlayer entityplayer, Object ent) {
		if (ent instanceof Entity) {
			entityplayer.openGui(mod_Zeitgeist.INSTANCE, zei_GuiManager.GUI_TOTER, entityplayer.worldObj, 0, ((Entity) ent).entityId, 0);
		} else if (ent instanceof TileEntity) {
			TileEntity t = (TileEntity) ent;
			entityplayer.openGui(mod_Zeitgeist.INSTANCE, zei_GuiManager.GUI_TOTER2, entityplayer.worldObj, t.xCoord, t.yCoord, t.zCoord);
		}
		// ModLoader.openGUI(entityplayer, new
		// zei_GuiTote(entityplayer.inventory, ent));
	}

	public static double distance(double xi, double yi, double zi, double xe, double ye, double ze) {
		double dx = xi - xe;
		double dy = yi - ye;
		double dz = zi - ze;
		return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}
}
