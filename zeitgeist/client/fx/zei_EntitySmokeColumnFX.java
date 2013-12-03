package zeitgeist.client.fx;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class zei_EntitySmokeColumnFX extends EntityFX {
	public zei_EntitySmokeColumnFX(World world, double d, double d1, double d2,
			double d3, double d4, double d5) {
		super(world, d, d1, d2, d3, d4, d5);
		motionX = d3 + (double) ((float) (Math.random() * 2D - 1.0D) * 0.05F);
		motionY = d4 + (double) ((float) (Math.random() * 2D - 1.0D) * 0.05F);
		motionZ = d5 + (double) ((float) (Math.random() * 2D - 1.0D) * 0.05F);
		particleRed = particleGreen = particleBlue = rand.nextFloat() * 0.3F + 0.4F;
		particleScale = rand.nextFloat() * 10F + 6.0F;
		particleMaxAge = (int) (128 + (rand.nextFloat() * 16));
	}

	public void renderParticle(Tessellator tessellator, float f, float f1,
			float f2, float f3, float f4, float f5) {
		super.renderParticle(tessellator, f, f1, f2, f3, f4, f5);
	}

	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		if (particleAge++ >= particleMaxAge) {
			setDead();
		}
		this.setParticleTextureIndex(7 - (particleAge * 8) / particleMaxAge);
		motionY += 0.0040000000000000001D;
		moveEntity(motionX, motionY, motionZ);
		motionX *= 0.98D;
		motionY *= 0.98D;
		motionZ *= 0.98D;
		if (onGround) {
			motionX *= 0.69999998807907104D;
			motionZ *= 0.69999998807907104D;
		}
	}
}
