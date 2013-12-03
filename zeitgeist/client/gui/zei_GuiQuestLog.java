package zeitgeist.client.gui;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.src.ModLoader;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import zeitgeist.common.zei_Quest;

public class zei_GuiQuestLog extends GuiScreen {
	protected String screenTitle;
	private boolean abandoning;
	private GuiButton buttonPurchase;
	private boolean field_28217_m;
	private Map inventoryMap = new HashMap();
	private Map<String, zei_Quest> questMap = new HashMap<String, zei_Quest>();
	private zei_GuiSlotQuest questSlot;
	private boolean selected;
	private int selectedQuest;
	private World world = Minecraft.getMinecraft().theWorld;
	private float xSize_lo;
	private float ySize_lo;

	public zei_GuiQuestLog() {
		selected = false;
	}

	public void abandonQuest(int i) {
		if (selected) {
			return;
		}
		selected = true;
		mc.thePlayer.addChatMessage("You have abandoned the quest '" + "!'");
		World world = ModLoader.getMinecraftInstance().theWorld;
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void drawScreen(int i, int j, float f) {
		questSlot.drawScreen(i, j, f);
		drawCenteredString(getFontRenderer(), screenTitle, width / 2, 20, 0xffffff);
		super.drawScreen(i, j, f);
	}

	@Override
	public void drawWorldBackground(int i) {
		if (mc.theWorld != null) {
			drawTexturedModalRect(0, 0, width, height, 0x000, 0x000);
		} else {
			drawBackground(i);
		}
	}

	public FontRenderer getFontRenderer() {
		return fontRenderer;
	}

	@Override
	public void initGui() {
		screenTitle = "Quest Log";
		questSlot = new zei_GuiSlotQuest(this);
		questSlot.registerScrollButtons(buttonList, 4, 5);
		Keyboard.enableRepeatEvents(true);
		buttonList.clear();
		loadQuests();
		byte byte0 = -16;
		buttonList.add(buttonPurchase = new GuiButton(0, width / 2 - 104, height / 4 + 164 + byte0, 98, 20, "Abandon"));
		buttonList.add(new GuiButton(1, width / 2 - 4, height / 4 + 164 + byte0, 98, 20, StatCollector.translateToLocal("Close")));
		buttonPurchase.enabled = false;
	}

	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	@Override
	protected void actionPerformed(GuiButton guibutton) {
		if (!guibutton.enabled) {
			return;
		}
		if (guibutton.id == 0) {
			int i = getSelectedQuest(this);
			abandonQuest(i);
			return;
		}
		if (guibutton.id == 1) {
			mc.displayGuiScreen(null);
			return;
		}
		if (guibutton.id == 2) {
			int i = getSelectedQuest(this);
			if (i >= 0) {
				abandoning = true;
				String s1 = StatCollector.translateToLocal("selectWorld.deleteQuestion");
				String s2 = (new StringBuilder()).append("'").append(getQuestNameBySlot(i)).append("' ").append(stringtranslate.translateKey("selectWorld.deleteWarning")).toString();
				String s3 = StatCollector.translateToLocal("selectWorld.deleteButton");
				String s4 = StatCollector.translateToLocal("gui.cancel");
				GuiYesNo guiyesno = new GuiYesNo(this, s1, s2, s3, s4, selectedQuest);
				mc.displayGuiScreen(guiyesno);
			}
		} else {
			return;
		}
	}

	protected void drawGuiContainerBackgroundLayer(float f, int par2, int par3) {
		char c = '\226';
		byte byte0 = 100;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(GuiContainer.field_110408_a);
		int j = (width - c) / 2;
		int k = (height - byte0) / 2;
		drawTexturedModalRect(j, k, 0, 0, (int) xSize_lo, (int) ySize_lo);
		GL11.glEnable(32826 /* GL_RESCALE_NORMAL_EXT */);
		GL11.glEnable(2903 /* GL_COLOR_MATERIAL */);
		GL11.glPushMatrix();
		GL11.glTranslatef(j + 51, k + 75, 50F);
		float f1 = 30F;
		GL11.glScalef(-f1, f1, f1);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		float f2 = mc.thePlayer.renderYawOffset;
		float f3 = mc.thePlayer.rotationYaw;
		float f4 = mc.thePlayer.rotationPitch;
		float f5 = j + 51 - xSize_lo;
		float f6 = (k + 75) - 50 - ySize_lo;
		GL11.glRotatef(135F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glRotatef(-135F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-(float) Math.atan(f6 / 40F) * 20F, 1.0F, 0.0F, 0.0F);
		mc.thePlayer.renderYawOffset = (float) Math.atan(f5 / 40F) * 20F;
		mc.thePlayer.rotationYaw = (float) Math.atan(f5 / 40F) * 40F;
		mc.thePlayer.rotationPitch = -(float) Math.atan(f6 / 40F) * 20F;
		GL11.glTranslatef(0.0F, mc.thePlayer.yOffset, 0.0F);
		RenderManager.instance.renderEntityWithPosYaw(mc.thePlayer, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
		mc.thePlayer.renderYawOffset = f2;
		mc.thePlayer.rotationYaw = f3;
		mc.thePlayer.rotationPitch = f4;
		GL11.glPopMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(32826 /* GL_RESCALE_NORMAL_EXT */);
	}

	private String getQuestNameBySlot(int i) {
		return questMap.get(Integer.toString(i)).name;
	}

	private void loadQuests() {
		selectedQuest = -1;
	}

	static int getSelectedQuest(zei_GuiQuestLog gurguiquestlog) {
		return gurguiquestlog.selectedQuest;
	}

	static int onElementSelected(zei_GuiQuestLog gurguiquestlog, int i) {
		return gurguiquestlog.selectedQuest = i;
	}
}
