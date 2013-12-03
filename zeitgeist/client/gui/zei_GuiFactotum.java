package zeitgeist.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import zeitgeist.common.zei_ContainerFactotum;
import zeitgeist.common.entity.zei_EntityFactotum;

public class zei_GuiFactotum extends GuiContainer {
	public static final ResourceLocation FACTOTUMGUI = new ResourceLocation("zeitgeist", "factotumg.png");
	private zei_EntityFactotum furnaceInventory;

	public zei_GuiFactotum(InventoryPlayer inventoryplayer, zei_EntityFactotum tileentityfurnace) {
		super(new zei_ContainerFactotum(inventoryplayer, tileentityfurnace));
		furnaceInventory = tileentityfurnace;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int ii, int jj) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(FACTOTUMGUI);
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
		if (furnaceInventory.isBurningStuff()) {
			int l = furnaceInventory.getBurnTimeRemainingSc(12);
			// System.out.println(l);
			drawTexturedModalRect(j + 26, (k + 36 + 12) - l, 176, 12 - l, 14, l + 2);// 26
																						// 56
		}
		int i1 = furnaceInventory.getCookProgressSc(24);
		drawTexturedModalRect(j + 55, k + 34, 176, 14, i1 + 1, 16);// 55 79
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {
		// fontRenderer.drawString("Factotum", 60, 6, 0x000000);
		fontRenderer.drawString("Furnace", 12, 6, 0xD14600);
		fontRenderer.drawString("Chest", 110, 6, 0x8C7632);
		fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
	}
}
