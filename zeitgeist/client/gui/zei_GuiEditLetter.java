package zeitgeist.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatAllowedCharacters;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import zeitgeist.common.item.zei_LetterData;

public class zei_GuiEditLetter extends GuiScreen {
	/**
	 * This String is just a local copy of the characters allowed in text
	 * rendering of minecraft.
	 */
	private static final String allowedCharacters;
	static {
		allowedCharacters = ChatAllowedCharacters.allowedCharacters;
	}
	/** The number of the line that is being edited. */
	private int editLine;
	/** The title string that is displayed in the top-center of the screen. */
	protected String screenTitle;
	/** Reference to the sign object. */
	private zei_LetterData theLetter;
	/** Counts the number of screen updates. */
	private int updateCounter;

	public zei_GuiEditLetter(zei_LetterData letter) {
		screenTitle = "Edit Letter message:";
		editLine = 0;
		theLetter = letter;
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		if (!par1GuiButton.enabled) {
			return;
		}
		if (par1GuiButton.id == 0) {
			// entitySign.onInventoryChanged();
			mc.displayGuiScreen(null);
		}
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		drawCenteredString(fontRenderer, screenTitle, width / 2, 40, 0xffffff);
		GL11.glPushMatrix();
		GL11.glTranslatef(width / 2, 0.0F, 50F);
		float f = 93.75F;
		GL11.glScalef(-f, -f, -f);
		GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
		super.drawScreen(par1, par2, par3);
	}

	@Override
	public void initGui() {
		buttonList.clear();
		Keyboard.enableRepeatEvents(true);
		buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 120, "Done"));
	}

	@Override
	protected void keyTyped(char par1, int par2) {
		if (par2 == 200) {
			editLine = editLine - 1 & 3;
		}
		if (par2 == 208 || par2 == 28) {
			editLine = editLine + 1 & 3;
		}
	}

	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
		if (mc.theWorld.isRemote) {
			// mc.getSendQueue().addToSendQueue(new
			// Packet130UpdateSign(entitySign.xCoord, entitySign.yCoord,
			// entitySign.zCoord, entitySign.signText));
		}
		// entitySign.func_50006_a(true);
	}

	@Override
	public void updateScreen() {
		updateCounter++;
	}
}
