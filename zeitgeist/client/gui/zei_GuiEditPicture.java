package zeitgeist.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import zeitgeist.client.zei_PictureData;
import zeitgeist.common.zei_Packet133UpdatePicture;

public class zei_GuiEditPicture extends GuiScreen {
	/** The title string that is displayed in the top-center of the screen. */
	protected String screenTitle;
	private int imgID;
	private String name;
	/** Reference to the sign object. */
	private zei_PictureData picture;
	private int spare;
	/** Counts the number of screen updates. */
	private int updateCounter;
	zei_GuiPictureSlider alpha;
	zei_GuiPictureSlider blue;
	int color = 0;
	boolean down = false;
	zei_GuiPictureSlider green;
	int px = 0;
	int py = 0;
	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	zei_GuiPictureSlider red;
	zei_GuiPictureSlider size;
	String username;

	public zei_GuiEditPicture(String n, zei_PictureData pic, int ID, int spare) {
		name = n;
		screenTitle = "Edit Texture:";
		imgID = ID;
		this.spare = spare;
		picture = pic;
	}

	public void blot(int xx, int yy, int s) {
		for (int x1 = xx - s; x1 < xx + s; x1++) {
			for (int y1 = yy - s; y1 < yy + s; y1++) {
				int x = x1;
				int y = y1;
				if (x < 0)
					x = 0;
				if (x > 15)
					x = 15;
				if (y < 0)
					y = 0;
				if (y > 15)
					y = 15;
				picture.colors[y * 16 + x] = (byte) (color - 128);
			}
		}
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		drawCenteredString(fontRenderer, screenTitle, width / 2, 40, 0xffffff);
		drawStuff();
		super.drawScreen(par1, par2, par3);
	}

	public void drawStuff() {
		picture.bindPicture(spare, false);
		int xx = width / 2;
		int yy = height / 2;
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(xx - 100, yy + 80, zLevel, 0.0625f, 0.0625f);
		tessellator.addVertexWithUV(xx + 100, yy + 80, zLevel, 0.125f, 0.0625f);
		tessellator.addVertexWithUV(xx + 100, yy - 120, zLevel, 0.125f, 0);
		tessellator.addVertexWithUV(xx - 100, yy - 120, zLevel, 0.0625f, 0);
		tessellator.draw();
	}

	public int[] getI(int x, int y) {
		int x1 = -100 + width / 2;
		int x2 = 100 + width / 2;
		int y1 = -120 + height / 2;
		int y2 = 80 + height / 2;
		px = x;
		py = y;
		if (x >= x1 && x <= x2) {
			x -= x1;
			if (y >= y1 && y <= y2) {
				y -= y1;
				x = (x * 16) / 200;
				y = (y * 16) / 200;
				if (x > 15)
					x = 15;
				if (y > 15)
					y = 15;
				return new int[] { x, y };
			}
		}
		return new int[] { -1, -1 };
	}

	public int getV(int x, int y) {
		int[] ii = getI(x, y);
		if (ii[0] == -1)
			return 256;
		return picture.colors[ii[1] * 16 + ii[0]] + 128;
	}

	@Override
	public void initGui() {
		buttonList.clear();
		buttonList.add(new GuiButton(0, width / 2 - 100, height - 32, "Done"));
		int w = width / 2 + 100;
		buttonList.add(new GuiButton(1, w, (height / 2) - 100, 50, 20, "Blue"));
		buttonList.add(new GuiButton(2, w, (height / 2) - 80, 50, 20, "Red"));
		buttonList.add(new GuiButton(3, w, (height / 2) - 60, 50, 20, "Green"));
		buttonList.add(new GuiButton(4, w, (height / 2) - 40, 50, 20, "White"));
		buttonList.add(new GuiButton(5, w, (height / 2) - 20, 50, 20, "Black"));
		// controlList.add(new GuiButton(6, w, (height/2) ,50,20, "Clear"));
		red = new zei_GuiPictureSlider(6, w, (height / 2), "Red", 1); // 50,20
		green = new zei_GuiPictureSlider(7, w, (height / 2) + 20, "Green", 1); // 50,20
		blue = new zei_GuiPictureSlider(8, w, (height / 2) + 40, "Blue", 1); // 50,20
		alpha = new zei_GuiPictureSlider(9, w, (height / 2) + 60, "Alpha", 1); // 50,20
		size = new zei_GuiPictureSlider(10, w, (height / 2) + 80, "Size", 1); // 50,20
		buttonList.add(red);
		buttonList.add(green);
		buttonList.add(blue);
		buttonList.add(alpha);
		buttonList.add(size);
	}

	@Override
	public void onGuiClosed() {
		if (mc.theWorld.isRemote) {
			mc.thePlayer.sendQueue.addToSendQueue(new zei_Packet133UpdatePicture(name, picture.colors));
		}
		picture.markDirty();
	}

	public void setColor(int r, int g, int b, int a) {
		color = ((a * 4 + r) * 4 + g) * 4 + b;
	}

	@Override
	public void updateScreen() {
		updateCounter++;
	}

	public void window(int x, int y) {
		// System.out.println("called"+b);
		setColor(red.getV(), green.getV(), blue.getV(), alpha.getV());
		int[] ii = getI(x, y);
		if (ii[0] == -1)
			return;
		int sz = size.getV();
		if (sz == 0) {
			picture.colors[ii[1] * 16 + ii[0]] = (byte) (color - 128);
		} else {
			blot(ii[0], ii[1], sz);
		}
		zei_PictureData.picRenew.put(imgID, true);
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		if (!par1GuiButton.enabled) {
			return;
		}
		switch (par1GuiButton.id) {
		case 1:
			red.sliderValue = 0;
			green.sliderValue = 0;
			blue.sliderValue = 1;
			alpha.sliderValue = 1;
			break;
		case 2:
			red.sliderValue = 1;
			green.sliderValue = 0;
			blue.sliderValue = 0;
			alpha.sliderValue = 1;
			break;
		case 3:
			red.sliderValue = 0;
			green.sliderValue = 1;
			blue.sliderValue = 0;
			alpha.sliderValue = 1;
			break;
		case 4:
			red.sliderValue = 1;
			green.sliderValue = 1;
			blue.sliderValue = 1;
			alpha.sliderValue = 1;
			break;
		case 5:
			red.sliderValue = 0;
			green.sliderValue = 0;
			blue.sliderValue = 0;
			alpha.sliderValue = 1;
			break;
		// case 6:setColor(0,0,0,0);break;
		case 0:
			mc.displayGuiScreen(null);
			break;
		}
	}

	@Override
	protected void mouseClicked(int x, int y, int par3) {
		super.mouseClicked(x, y, par3);
		if (par3 == 0) {
			down = true;
			window(x, y);
		} else if (par3 == 1) {
			color = getV(x, y);
			alpha.sliderValue = ((color & 255) >> 6) / 4f;
			red.sliderValue = ((color & 63) >> 4) / 4f;
			green.sliderValue = ((color & 15) >> 2) / 4f;
			blue.sliderValue = ((color & 3)) / 4f;
		}
	}

	@Override
	protected void mouseMovedOrUp(int x, int y, int par3) {
		super.mouseMovedOrUp(x, y, par3);
		if (par3 > -1) {
			down = false;
		}
		if (down) {
			if (x != px || y != py) {
				window(x, y);
			}
		}
	}
}
