package zeitgeist.client.gui;

import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public abstract class zei_GuiQuestSlot {
	protected final int bottom;
	protected final int posZ;
	protected final int top;
	private float amountScrolled;
	private boolean field_25123_p;
	private int field_27261_r;
	private boolean field_27262_q;
	private final int height;
	private float initialClickY;
	private long lastClicked;
	private final int left = 0;
	private final int right;
	private int scrollDownButtonID;
	private float scrollMultiplier;
	private int scrollUpButtonID;
	private int selectedElement;
	private final int width;
	final zei_GuiQuestLog guiQuestLog;

	public zei_GuiQuestSlot(zei_GuiQuestLog gurGuiQuestLog, int i, int j, int k, int l, int i1) {
		initialClickY = -2F;
		selectedElement = -1;
		lastClicked = 0L;
		field_25123_p = true;
		width = i;
		height = j;
		top = k;
		bottom = l;
		posZ = i1;
		right = i;
		guiQuestLog = gurGuiQuestLog;
	}

	public void actionPerformed(GuiButton guibutton) {
		if (!guibutton.enabled) {
			return;
		}
		if (guibutton.id == scrollUpButtonID) {
			amountScrolled -= (posZ * 2) / 3;
			initialClickY = -2F;
			bindAmountScrolled();
		} else if (guibutton.id == scrollDownButtonID) {
			amountScrolled += (posZ * 2) / 3;
			initialClickY = -2F;
			bindAmountScrolled();
		}
	}

	public void drawScreen(int i, int j, float f) {
		drawBackground();
		int k = getSize();
		int l = width / 2 + 124;
		int i1 = l + 6;
		if (Mouse.isButtonDown(0)) {
			if (initialClickY == -1F) {
				boolean flag = true;
				if (j >= top && j <= bottom) {
					int j1 = width / 2 - 110;
					int k1 = width / 2 + 110;
					int i2 = ((j - top - field_27261_r) + (int) amountScrolled) - 4;
					int k2 = i2 / posZ;
					if (i >= j1 && i <= k1 && k2 >= 0 && i2 >= 0 && k2 < k) {
						boolean flag1 = k2 == selectedElement && System.currentTimeMillis() - lastClicked < 250L;
						elementClicked(k2, flag1);
						selectedElement = k2;
						lastClicked = System.currentTimeMillis();
					} else if (i >= j1 && i <= k1 && i2 < 0) {
						func_27255_a(i - j1, ((j - top) + (int) amountScrolled) - 4);
						flag = false;
					}
					if (i >= l && i <= i1) {
						scrollMultiplier = -1F;
						int i3 = getContentHeight() - (bottom - top - 4);
						if (i3 < 1) {
							i3 = 1;
						}
						int l3 = (int) ((float) ((bottom - top) * (bottom - top)) / (float) getContentHeight());
						if (l3 < 32) {
							l3 = 32;
						}
						if (l3 > bottom - top - 8) {
							l3 = bottom - top - 8;
						}
						scrollMultiplier /= (float) (bottom - top - l3) / (float) i3;
					} else {
						scrollMultiplier = 1.0F;
					}
					if (flag) {
						initialClickY = j;
					} else {
						initialClickY = -2F;
					}
				} else {
					initialClickY = -2F;
				}
			} else if (initialClickY >= 0.0F) {
				amountScrolled -= (j - initialClickY) * scrollMultiplier;
				initialClickY = j;
			}
		} else {
			initialClickY = -1F;
		}
		bindAmountScrolled();
		Tessellator tessellator = Tessellator.instance;
		int l1 = width / 2 - 92 - 16;
		int j2 = (top + 4) - (int) amountScrolled;
		if (field_27262_q) {
			func_27260_a(l1, j2, tessellator);
		}
		for (int l2 = 0; l2 < k; l2++) {
			int j3 = j2 + l2 * posZ + field_27261_r;
			int i4 = posZ - 4;
			if (j3 > bottom || j3 + i4 < top) {
				continue;
			}
			if (field_25123_p && isSelected(l2)) {
				int k4 = width / 2 - 110;
				int i5 = width / 2 + 110;
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glDisable(3553 /* GL_TEXTURE_2D */);
				tessellator.startDrawingQuads();
				tessellator.setColorOpaque_I(0x808080);
				tessellator.addVertexWithUV(k4, j3 + i4 + 2, 0.0D, 0.0D, 1.0D);
				tessellator.addVertexWithUV(i5, j3 + i4 + 2, 0.0D, 1.0D, 1.0D);
				tessellator.addVertexWithUV(i5, j3 - 2, 0.0D, 1.0D, 0.0D);
				tessellator.addVertexWithUV(k4, j3 - 2, 0.0D, 0.0D, 0.0D);
				tessellator.setColorOpaque_I(0);
				tessellator.addVertexWithUV(k4 + 1, j3 + i4 + 1, 0.0D, 0.0D, 1.0D);
				tessellator.addVertexWithUV(i5 - 1, j3 + i4 + 1, 0.0D, 1.0D, 1.0D);
				tessellator.addVertexWithUV(i5 - 1, j3 - 1, 0.0D, 1.0D, 0.0D);
				tessellator.addVertexWithUV(k4 + 1, j3 - 1, 0.0D, 0.0D, 0.0D);
				tessellator.draw();
				GL11.glEnable(3553 /* GL_TEXTURE_2D */);
			}
			drawSlot(l2, l1, j3, i4, tessellator);
		}
		byte byte0 = 4;
		overlayBackground();
		GL11.glEnable(3042 /* GL_BLEND */);
		GL11.glBlendFunc(770, 771);
		GL11.glShadeModel(7425 /* GL_SMOOTH */);
		GL11.glDisable(3553 /* GL_TEXTURE_2D */);
		tessellator.startDrawingQuads();
		tessellator.setColorRGBA_I(0, 0);
		tessellator.addVertexWithUV(left, top + byte0, 0.0D, 0.0D, 1.0D);
		tessellator.addVertexWithUV(right, top + byte0, 0.0D, 1.0D, 1.0D);
		tessellator.setColorRGBA_I(0, 255);
		tessellator.addVertexWithUV(right, top, 0.0D, 1.0D, 0.0D);
		tessellator.addVertexWithUV(left, top, 0.0D, 0.0D, 0.0D);
		tessellator.draw();
		int k3 = getContentHeight() - (bottom - top - 4);
		if (k3 > 0) {
			int j4 = ((bottom - top) * (bottom - top)) / getContentHeight();
			if (j4 < 32) {
				j4 = 32;
			}
			if (j4 > bottom - top - 8) {
				j4 = bottom - top - 8;
			}
			int l4 = ((int) amountScrolled * (bottom - top - j4)) / k3 + top;
			if (l4 < top) {
				l4 = top;
			}
			tessellator.startDrawingQuads();
			tessellator.setColorRGBA_I(0, 255);
			tessellator.addVertexWithUV(l, bottom, 0.0D, 0.0D, 1.0D);
			tessellator.addVertexWithUV(i1, bottom, 0.0D, 1.0D, 1.0D);
			tessellator.addVertexWithUV(i1, top, 0.0D, 1.0D, 0.0D);
			tessellator.addVertexWithUV(l, top, 0.0D, 0.0D, 0.0D);
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setColorRGBA_I(0x808080, 255);
			tessellator.addVertexWithUV(l, l4 + j4, 0.0D, 0.0D, 1.0D);
			tessellator.addVertexWithUV(i1, l4 + j4, 0.0D, 1.0D, 1.0D);
			tessellator.addVertexWithUV(i1, l4, 0.0D, 1.0D, 0.0D);
			tessellator.addVertexWithUV(l, l4, 0.0D, 0.0D, 0.0D);
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setColorRGBA_I(0xc0c0c0, 255);
			tessellator.addVertexWithUV(l, (l4 + j4) - 1, 0.0D, 0.0D, 1.0D);
			tessellator.addVertexWithUV(i1 - 1, (l4 + j4) - 1, 0.0D, 1.0D, 1.0D);
			tessellator.addVertexWithUV(i1 - 1, l4, 0.0D, 1.0D, 0.0D);
			tessellator.addVertexWithUV(l, l4, 0.0D, 0.0D, 0.0D);
			tessellator.draw();
		}
		func_27257_b(i, j);
		GL11.glEnable(3553 /* GL_TEXTURE_2D */);
	}

	public int func_27256_c(int i, int j) {
		int k = width / 2 - 110;
		int l = width / 2 + 110;
		int i1 = ((j - top - field_27261_r) + (int) amountScrolled) - 4;
		int j1 = i1 / posZ;
		if (i >= k && i <= l && j1 >= 0 && i1 >= 0 && j1 < getSize()) {
			return j1;
		} else {
			return -1;
		}
	}

	public void func_27258_a(boolean flag) {
		field_25123_p = flag;
	}

	public void registerScrollButtons(List list, int i, int j) {
		scrollUpButtonID = i;
		scrollDownButtonID = j;
	}

	protected abstract void drawBackground();

	protected abstract void drawSlot(int i, int j, int k, int l, Tessellator tessellator);

	protected abstract void elementClicked(int i, boolean flag);

	protected void func_27255_a(int i, int j) {
	}

	protected void func_27257_b(int i, int j) {
	}

	protected void func_27259_a(boolean flag, int i) {
		field_27262_q = flag;
		field_27261_r = i;
		if (!flag) {
			field_27261_r = 0;
		}
	}

	protected void func_27260_a(int i, int j, Tessellator tessellator) {
	}

	protected int getContentHeight() {
		return getSize() * posZ + field_27261_r;
	}

	protected abstract int getSize();

	protected abstract boolean isSelected(int i);

	private void bindAmountScrolled() {
		int i = getContentHeight() - (bottom - top - 4);
		if (i < 0) {
			i /= 2;
		}
		if (amountScrolled < 0.0F) {
			amountScrolled = 0.0F;
		}
		if (amountScrolled > i) {
			amountScrolled = i;
		}
	}

	private void overlayBackground() {
	}
}
