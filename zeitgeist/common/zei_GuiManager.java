package zeitgeist.common;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import zeitgeist.client.gui.zei_GuiCannon;
import zeitgeist.client.gui.zei_GuiTote;
import zeitgeist.common.entity.zei_EntityTote;
import zeitgeist.common.tile.zei_TileEntityCannon;
import zeitgeist.common.tile.zei_TileEntityLatch;
import cpw.mods.fml.common.network.IGuiHandler;

public class zei_GuiManager implements IGuiHandler {
	public static final int GUI_CANNON = 51;
	public static final int GUI_TOTER = 49;
	public static final int GUI_TOTER2 = 50;

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case GUI_TOTER:
			List<Entity> list = world.getEntitiesWithinAABB(zei_EntityTote.class,
					AxisAlignedBB.getBoundingBox(player.posX - 2, player.posY - 2, player.posZ - 2, player.posX + 2, player.posY + 2, player.posZ + 2));
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).entityId == y) {
					zei_EntityTote tote = (zei_EntityTote) list.get(i);
					return new zei_GuiTote(player.inventory, tote.inv);
				}
			}
			return null;
		case GUI_TOTER2:
			zei_TileEntityLatch tote = (zei_TileEntityLatch) world.getBlockTileEntity(x, y, z);
			if (tote != null) {
				return new zei_GuiTote(player.inventory, tote);
			}
		case GUI_CANNON:
			zei_TileEntityCannon cannon = (zei_TileEntityCannon) world.getBlockTileEntity(x, y, z);
			if (cannon != null) {
				return new zei_GuiCannon(player.inventory, cannon);
			}
		default:
			return null;
		}
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case GUI_TOTER:
			List<Entity> list = world.getEntitiesWithinAABB(zei_EntityTote.class,
					AxisAlignedBB.getBoundingBox(player.posX - 2, player.posY - 2, player.posZ - 2, player.posX + 2, player.posY + 2, player.posZ + 2));
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).entityId == y) {
					zei_EntityTote tote = (zei_EntityTote) list.get(i);
					return new zei_ContainerTote(player.inventory, tote.inv);
				}
			}
			return null;
		case GUI_TOTER2:
			zei_TileEntityLatch tote = (zei_TileEntityLatch) world.getBlockTileEntity(x, y, z);
			if (tote != null) {
				return new zei_ContainerTote(player.inventory, tote);
			}
		case GUI_CANNON:
			zei_TileEntityCannon cannon = (zei_TileEntityCannon) world.getBlockTileEntity(x, y, z);
			if (cannon != null) {
				return new zei_ContainerCannon(player.inventory, cannon);
			}
		default:
			return null;
		}
	}
}
