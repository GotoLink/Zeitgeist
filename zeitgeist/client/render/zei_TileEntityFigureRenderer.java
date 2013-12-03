package zeitgeist.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import zeitgeist.common.tile.zei_TileEntityStatue;

public class zei_TileEntityFigureRenderer extends TileEntitySpecialRenderer {
	public void renderMeAt(zei_TileEntityStatue enti, double x, double y,
			double z, float par8) {
	}

	public void renderTileEntityAt(TileEntity par1TileEntity, double par2,
			double par4, double par6, float par8) {
		renderMeAt((zei_TileEntityStatue) par1TileEntity, par2, par4, par6,
				par8);
	}
}
