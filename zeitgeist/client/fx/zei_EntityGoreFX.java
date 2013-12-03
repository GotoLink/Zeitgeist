package zeitgeist.client.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import zeitgeist.client.ClientProxy;

public class zei_EntityGoreFX extends EntityFX {
	float factor;

	public zei_EntityGoreFX(World world, double d, double d1, double d2,
			double d3, double d4, double d5) {
		this(world, d, d1, d2, d3, d4, d5, 2.0F);
	}

	public zei_EntityGoreFX(World world, double d, double d1, double d2,
			double d3, double d4, double d5, float f) {
		super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
		motionX = d3;
		motionY = d4;
		motionZ = d5;//
		// particleGravity=20f;
		// motionY += 0.20000000000000001D;
		particleRed = 1f;
		particleGreen = 1f;
		particleBlue = 1f;
		factor = f;
		particleScale *= f;
		particleMaxAge = 60;
		noClip = false;
		// func_40099_c(64);
	}

	/*
	 * public void renderParticle(Tessellator tessellator, float f, float f1,
	 * float f2, float f3, float f4, float f5) { float f6 = (((float)particleAge
	 * + f) / (float)particleMaxAge) * 32F; if(f6 < 0.0F) { f6 = 0.0F; } if(f6 >
	 * 1.0F) { f6 = 1.0F; } particleScale = noteParticleScale * f6;
	 * super.renderParticle(tessellator, f, f1, f2, f3, f4, f5); }
	 */
	public void renderParticle(Tessellator tessellator, float f, float f1,
			float f2, float f3, float f4, float f5) {
		Minecraft.getMinecraft().renderEngine.bindTexture(ClientProxy.GORE);
		float f6 = 0f;// (float)(func_40100_q() % 16) / 16F;
		float f7 = 1f; // f6 + 0.0624375F;
		float f8 = 0f;// (float)(func_40100_q() / 16) / 16F;
		float f9 = 1f; // f8 + 0.0624375F;
		float f10 = 0.1F * particleScale;
		float f11 = (float) ((prevPosX + (posX - prevPosX) * (double) f) - interpPosX);
		float f12 = (float) ((prevPosY + (posY - prevPosY) * (double) f) - interpPosY);
		float f13 = (float) ((prevPosZ + (posZ - prevPosZ) * (double) f) - interpPosZ);
		float f14 = 1.0F;
		tessellator.setColorOpaque_F(particleRed * f14, particleGreen * f14,
				particleBlue * f14);
		tessellator.addVertexWithUV(f11 - f1 * f10 - f4 * f10, f12 - f2 * f10,
				f13 - f3 * f10 - f5 * f10, f7, f9);
		tessellator.addVertexWithUV((f11 - f1 * f10) + f4 * f10,
				f12 + f2 * f10, (f13 - f3 * f10) + f5 * f10, f7, f8);
		tessellator.addVertexWithUV(f11 + f1 * f10 + f4 * f10, f12 + f2 * f10,
				f13 + f3 * f10 + f5 * f10, f6, f8);
		tessellator.addVertexWithUV((f11 + f1 * f10) - f4 * f10,
				f12 - f2 * f10, (f13 + f3 * f10) - f5 * f10, f6, f9);
	}

	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		if (particleAge++ >= particleMaxAge) {
			setDead();
		}
		moveEntity(motionX, motionY, motionZ);
		motionY -= 0.1f; //
		particleScale = (1f - ((float) particleAge / (float) particleMaxAge))
				* factor;
		if (posY == prevPosY) {
			motionX *= 1.1000000000000001D;
			motionZ *= 1.1000000000000001D;
		}
		motionX *= 0.6600000262260437D;
		motionY *= 0.6600000262260437D;
		motionZ *= 0.6600000262260437D;
		if (onGround) {
			motionX *= 0.69999998807907104D;
			motionZ *= 0.69999998807907104D;
		}
	}
}
