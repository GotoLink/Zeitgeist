package zeitgeist.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import zeitgeist.common.zei_ContainerTote;

public class zei_GuiTote extends GuiContainer {
	public static final ResourceLocation TOTEGUI = new ResourceLocation("zeitgeist", "toteg.png");
	private IInventory tote;

	public zei_GuiTote(InventoryPlayer inventoryplayer, IInventory e) {
		super(new zei_ContainerTote(inventoryplayer, e));
		tote = e;
		// ySize = ('\336' )+ 3 * 18; //+i
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int ii, int jj) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(TOTEGUI);
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {
		// fontRenderer.drawString("Factotum", 60, 6, 0x000000);
		// fontRenderer.drawString("Furnace", 12, 6, 0xD14600);
		fontRenderer.drawString("Toter", 70, 6, 0x31a8d4);
		fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
	}
}
