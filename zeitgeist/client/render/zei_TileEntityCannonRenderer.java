package zeitgeist.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import zeitgeist.client.models.zei_ModelCannon;
import zeitgeist.common.tile.zei_TileEntityCannon;

public class zei_TileEntityCannonRenderer extends TileEntitySpecialRenderer {
	public static final ResourceLocation CANNON = new ResourceLocation("zeitgeist", "Cannon.png");
	private zei_ModelCannon cannonModel = new zei_ModelCannon();

	public void renderCannonAt(zei_TileEntityCannon enti, double x, double y, double z, float par8) {
		bindTexture(CANNON);
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);
		GL11.glTranslatef(0.5f, -1.5f, -0.5f);
		GL11.glColor4f(1, 1, 1, 1);
		cannonModel.barrel.rotateAngleX = (float) (Math.PI * enti.Pitch / 180.0);
		cannonModel.side1.rotateAngleY = 1.5708f + (float) (Math.PI * enti.Rotation / 180.0);
		cannonModel.head.isHidden = enti.Mode < 1;
		cannonModel.renderAll();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		renderCannonAt((zei_TileEntityCannon) par1TileEntity, par2, par4, par6, par8);
	}
}
