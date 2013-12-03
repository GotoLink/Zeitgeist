package zeitgeist.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import zeitgeist.client.models.zei_ModelAncient;
import zeitgeist.common.entity.zei_EntityAncient;

public class zei_RenderAncient extends RenderLiving {
	private zei_ModelAncient modelBipedMain;
	private static final String armorFilenamePrefix[] = { "cloth", "chain",
			"iron", "diamond", "gold" };

	public zei_RenderAncient() {
		super(new zei_ModelAncient(), 0.5F);
		modelBipedMain = (zei_ModelAncient) mainModel;
	}

	public void renderPlayer(zei_EntityAncient par1EntityPlayer, double par2,
			double par4, double par6, float par8, float par9) {
		modelBipedMain.isSneak = par1EntityPlayer.isSneaking();
		double d = par4 - (double) par1EntityPlayer.yOffset;
		if (par1EntityPlayer.isSneaking()) {
			d -= 0.125D;
		}
		super.doRenderLiving(par1EntityPlayer, par2, d, par6, par8, par9);
		modelBipedMain.aimedBow = false;
		modelBipedMain.isSneak = false;
		modelBipedMain.heldItemRight = 0;
	}

	/**
	 * Used to render a player's name above their head
	 */
	protected void renderName(zei_EntityAncient par1EntityPlayer, double par2,
			double par4, double par6) {
		if (Minecraft.isGuiEnabled()
				&& par1EntityPlayer != renderManager.livingPlayer) {
			float f = 1.6F;
			float f1 = 0.01666667F * f;
			float f2 = par1EntityPlayer
					.getDistanceToEntity(renderManager.livingPlayer);
			float f3 = par1EntityPlayer.isSneaking() ? 32F : 64F;
			if (f2 < f3) {
				String s = par1EntityPlayer.username;
				if (!par1EntityPlayer.isSneaking()) {
					if (par1EntityPlayer.isPlayerSleeping()) {
						renderLivingLabel(par1EntityPlayer, s, par2,
								par4 - 1.5D, par6, 64);
					} else {
						renderLivingLabel(par1EntityPlayer, s, par2, par4 + 2,
								par6, 64);
					}
				} else {
					FontRenderer fontrenderer = getFontRendererFromRenderManager();
					GL11.glPushMatrix();
					GL11.glTranslatef((float) par2 + 0.0F, (float) par4 + 2.3F,
							(float) par6);
					GL11.glNormal3f(0.0F, 1.0F, 0.0F);
					GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
					GL11.glScalef(-f1, -f1, f1);
					GL11.glDisable(GL11.GL_LIGHTING);
					GL11.glTranslatef(0.0F, 0.25F / f1, 0.0F);
					GL11.glDepthMask(false);
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA,
							GL11.GL_ONE_MINUS_SRC_ALPHA);
					Tessellator tessellator = Tessellator.instance;
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					tessellator.startDrawingQuads();
					int i = fontrenderer.getStringWidth(s) / 2;
					tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
					tessellator.addVertex(-i - 1, -1D, 0.0D);
					tessellator.addVertex(-i - 1, 8D, 0.0D);
					tessellator.addVertex(i + 1, 8D, 0.0D);
					tessellator.addVertex(i + 1, -1D, 0.0D);
					tessellator.draw();
					GL11.glEnable(GL11.GL_TEXTURE_2D);
					GL11.glDepthMask(true);
					fontrenderer.drawString(s,
							-fontrenderer.getStringWidth(s) / 2, 0, 0x20ffffff);
					GL11.glEnable(GL11.GL_LIGHTING);
					GL11.glDisable(GL11.GL_BLEND);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					GL11.glPopMatrix();
				}
			}
		}
	}

	/**
	 * Method for adding special render rules
	 */
	protected void renderSpecials(zei_EntityAncient par1EntityPlayer, float par2) {
		super.renderEquippedItems(par1EntityPlayer, par2);
		if (1 == 1)// loadDownloadableImageTexture(par1EntityPlayer.playerCloakUrl,
					// null))
		{
			GL11.glPushMatrix();
			double d = (par1EntityPlayer.field_20066_r + (par1EntityPlayer.field_20063_u - par1EntityPlayer.field_20066_r)
					* (double) par2)
					- (par1EntityPlayer.prevPosX + (par1EntityPlayer.posX - par1EntityPlayer.prevPosX)
							* (double) par2);
			double d1 = (par1EntityPlayer.field_20065_s + (par1EntityPlayer.field_20062_v - par1EntityPlayer.field_20065_s)
					* (double) par2)
					- (par1EntityPlayer.prevPosY + (par1EntityPlayer.posY - par1EntityPlayer.prevPosY)
							* (double) par2);
			double d2 = (par1EntityPlayer.field_20064_t + (par1EntityPlayer.field_20061_w - par1EntityPlayer.field_20064_t)
					* (double) par2)
					- (par1EntityPlayer.prevPosZ + (par1EntityPlayer.posZ - par1EntityPlayer.prevPosZ)
							* (double) par2);
			float f10 = par1EntityPlayer.prevRenderYawOffset
					+ (par1EntityPlayer.renderYawOffset - par1EntityPlayer.prevRenderYawOffset)
					* par2;
			double d3 = MathHelper.sin((f10 * (float) Math.PI) / 180F);
			double d4 = -MathHelper.cos((f10 * (float) Math.PI) / 180F);
			float f12 = (float) d1 * 10F;
			if (f12 < -6F) {
				f12 = -6F;
			}
			if (f12 > 32F) {
				f12 = 32F;
			}
			float f13 = (float) (d * d3 + d2 * d4) * 100F;
			float f14 = (float) (d * d4 - d2 * d3) * 100F;
			if (f13 < 0.0F) {
				f13 = 0.0F;
			}
			float f15 = par1EntityPlayer.prevCameraYaw
					+ (par1EntityPlayer.cameraYaw - par1EntityPlayer.prevCameraYaw)
					* par2;
			f12 += MathHelper
					.sin((par1EntityPlayer.prevDistanceWalkedModified + (par1EntityPlayer.distanceWalkedModified - par1EntityPlayer.prevDistanceWalkedModified)
							* par2) * 6F)
					* 32F * f15;
			if (par1EntityPlayer.isSneaking()) {
				f12 += 25F;
			}
			GL11.glTranslatef(0.0F, -0.625F, 0.125F);
			GL11.glRotatef(6F + f13 / 2.0F + f12, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(f14 / 2.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(-f14 / 2.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(180F, 1.0F, 0.0F, 0.0F);
			modelBipedMain.renderCloak(0.0625F);
			GL11.glPopMatrix();
		}
	}

	protected void renderPlayerScale(zei_EntityAncient par1EntityPlayer,
			float par2) {
		float f = 1.2f;// 0.9375F;
		GL11.glScalef(f, f, f);
	}

	@Override
	protected void passSpecialRender(EntityLivingBase par1EntityLiving,
			double par2, double par4, double par6) {
		renderName((zei_EntityAncient) par1EntityLiving, par2, par4, par6);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase par1EntityLiving,
			float par2) {
		renderPlayerScale((zei_EntityAncient) par1EntityLiving, par2);
	}

	@Override
	protected void renderEquippedItems(EntityLivingBase par1EntityLiving,
			float par2) {
		renderSpecials((zei_EntityAncient) par1EntityLiving, par2);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4,
			double par6, float par8, float par9) {
		renderPlayer((zei_EntityAncient) par1Entity, par2, par4, par6, par8,
				par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
