package zeitgeist.client.gui;

import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.input.Keyboard;

import zeitgeist.client.zei_QuestHandler;

class zei_GuiSlotQuest extends zei_GuiQuestSlot {
	private zei_GuiQuestLog guiQuestLog;

	public zei_GuiSlotQuest(zei_GuiQuestLog gurGuiQuestLog) {
		super(gurGuiQuestLog, gurGuiQuestLog.width, gurGuiQuestLog.height, 32, gurGuiQuestLog.height - 64, 36);
		guiQuestLog = gurGuiQuestLog;
	}

	@Override
	public void drawScreen(int i, int j, float f) {
		guiQuestLog.drawWorldBackground(0);
		super.drawScreen(i, j, f);
	}

	@Override
	protected void drawBackground() {
	}

	@Override
	protected void drawSlot(int i, int j, int k, int l, Tessellator tessellator) {
		String s = getQuestNameBySlot(i);
		guiQuestLog.drawString(guiQuestLog.getFontRenderer(), s, j + 2, k + 1, 0xffffff);
	}

	@Override
	protected void elementClicked(int i, boolean flag) {
		zei_GuiQuestLog.onElementSelected(guiQuestLog, i);
		boolean flag1 = zei_GuiQuestLog.getSelectedQuest(guiQuestLog) >= 0 && zei_GuiQuestLog.getSelectedQuest(guiQuestLog) < getSize();
		if (flag && flag1 && Keyboard.isKeyDown(14)) {
			guiQuestLog.abandonQuest(i);
		}
	}

	@Override
	protected int getContentHeight() {
		return 16 * 36;
	}

	@Override
	protected int getSize() {
		return zei_QuestHandler.questMap.size() * 16;
	}

	@Override
	protected boolean isSelected(int i) {
		return i == zei_GuiQuestLog.getSelectedQuest(guiQuestLog);
	}

	private String getQuestNameBySlot(int i) {
		return "";
	}
}
