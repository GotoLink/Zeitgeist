package zeitgeist.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import zeitgeist.common.zei_ContainerCannon;
import zeitgeist.common.tile.zei_TileEntityCannon;

public class zei_GuiCannon extends GuiContainer {
	public static final ResourceLocation CANNONGUI = new ResourceLocation("zeitgeist", "guiCannon.png");
	private zei_TileEntityCannon cannon;

	public zei_GuiCannon(InventoryPlayer par1InventoryPlayer, zei_TileEntityCannon cannon) {
		super(new zei_ContainerCannon(par1InventoryPlayer, cannon));
		this.cannon = cannon;
	}

	@Override
	public void mouseClicked(int x, int y, int i) {
		super.mouseClicked(x, y, i);
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		x -= j;
		y -= k;
		if (y >= 47 && y <= 64) {
			if (x >= 25 && x <= 42) {
				cannon.toggleSafety();
				if (this.mc.theWorld.isRemote) {
					Packet132TileEntityData pkt = new Packet132TileEntityData();
					pkt.xPosition = cannon.xCoord;
					pkt.yPosition = cannon.yCoord;
					pkt.zPosition = cannon.zCoord;
					pkt.actionType = 7;
					pkt.data = cannon.SafetyOff ? 1 : 0;
					this.mc.thePlayer.sendQueue.addToSendQueue(pkt);
				}
			} else if (x >= 61 && x <= 78) {
				cannon.toggleFiretype();
				if (this.mc.theWorld.isRemote) {
					Packet132TileEntityData pkt = new Packet132TileEntityData();
					pkt.xPosition = cannon.xCoord;
					pkt.yPosition = cannon.yCoord;
					pkt.zPosition = cannon.zCoord;
					pkt.actionType = 6;
					pkt.data = cannon.Firetype ? 1 : 0;
					this.mc.thePlayer.sendQueue.addToSendQueue(pkt);
				}
			}
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(CANNONGUI);
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
		int l = cannon.gun;
		drawTexturedModalRect(j + 25, k + 16, 191, 16, l, 18);
		// int i1 = furnaceInventory.getCookProgressScaled(24);
		// 43?
		// 200
		int ll = Math.round((43f * cannon.cool / 1000f));
		drawTexturedModalRect(j + 9, (k + 79) - ll, 176, 43 - ll, 14, ll); // 36+43
		if (cannon.Firetype) {
			drawTexturedModalRect(j + 58, (k + 47), 176, 44, 23, 30);
		}
		if (cannon.SafetyOff) {
			drawTexturedModalRect(j + 25, (k + 47), 200, 44, 17, 29);
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRenderer.drawString("Cannon", 110, 6, 0x404040);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 110, (ySize - 96) + 2, 0x404040);
	}
}
