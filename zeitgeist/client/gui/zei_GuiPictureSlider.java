package zeitgeist.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import org.lwjgl.opengl.GL11;

public class zei_GuiPictureSlider extends GuiButton {
	/** Is this slider control being dragged. */
	public boolean dragging;
	/** The value of this slider control. */
	public float sliderValue;

	public zei_GuiPictureSlider(int par1, int par2, int par3, String par5Str, float par6) {
		super(par1, par2, par3, 50, 20, par5Str);
		sliderValue = 1.0F;
		dragging = false;
		sliderValue = par6;
	}

	@Override
	protected int getHoverState(boolean par1) {
		return 0;
	}

	public int getV() {
		int i = (int) Math.floor(sliderValue * 4);
		if (i > 3) {
			i = 3;
		}
		return i;
	}

	@Override
	protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3) {
		if (!drawButton) {
			return;
		}
		if (dragging) {
			sliderValue = (float) (par2 - (xPosition + 4)) / (float) (width - 8);
			if (sliderValue < 0.0F) {
				sliderValue = 0.0F;
			}
			if (sliderValue > 1.0F) {
				sliderValue = 1.0F;
			}
			// par1Minecraft.gameSettings.setOptionFloatValue(idFloat,
			// sliderValue);
			// displayString =
			// par1Minecraft.gameSettings.getKeyBinding(idFloat);
		}
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(xPosition + (int) (getV() * 0.33f * (width - 8)), yPosition, 0, 66, 4, 20);
		drawTexturedModalRect(xPosition + (int) (getV() * 0.33f * (width - 8)) + 4, yPosition, 196, 66, 4, 20);
	}

	@Override
	public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3) {
		if (super.mousePressed(par1Minecraft, par2, par3)) {
			sliderValue = (float) (par2 - (xPosition + 4)) / (float) (width - 8);
			if (sliderValue < 0.0F) {
				sliderValue = 0.0F;
			}
			if (sliderValue > 1.0F) {
				sliderValue = 1.0F;
			}
			// par1Minecraft.gameSettings.setOptionFloatValue(idFloat,
			// sliderValue);
			// displayString =
			// par1Minecraft.gameSettings.getKeyBinding(idFloat);
			dragging = true;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void mouseReleased(int par1, int par2) {
		dragging = false;
	}
}
