package zeitgeist.client.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class zei_ModelMusic extends ModelBiped {
	// fields
	// ModelRenderer head;
	// ModelRenderer body;
	ModelRenderer spinner;
	// ModelRenderer bipedRightLeg;
	// ModelRenderer bipedLeftLeg;
	ModelRenderer bodyIn;
	ModelRenderer headIn;

	public zei_ModelMusic() {
		textureWidth = 64;
		textureHeight = 32;
		bipedHead = new ModelRenderer(this, 0, 0);
		bipedHead.addBox(-4F, -7F, -4F, 8, 7, 8);
		bipedHead.setRotationPoint(0F, 0F, 0F);
		bipedHead.setTextureSize(64, 32);
		bipedHead.mirror = true;
		setRotation(bipedHead, 0F, 0F, 0F);
		bipedBody = new ModelRenderer(this, 12, 16);
		bipedBody.addBox(-4F, 0F, -2F, 8, 12, 4);
		bipedBody.setRotationPoint(0F, 0F, 0F);
		bipedBody.setTextureSize(64, 32);
		bipedBody.mirror = true;
		setRotation(bipedBody, 0F, 0F, 0F);
		bipedRightArm = new ModelRenderer(this, 36, 15);
		bipedRightArm.addBox(-2F, -2F, -1.5F, 3, 14, 3);
		bipedRightArm.setRotationPoint(-5F, 2F, 0F);
		bipedRightArm.setTextureSize(64, 32);
		bipedRightArm.mirror = true;
		setRotation(bipedRightArm, 0F, 0F, 0F);
		spinner = new ModelRenderer(this, 56, 12);
		spinner.addBox(-1F, -3F, 0F, 2, 6, 2);
		spinner.setRotationPoint(0F, 4F, 2F);
		spinner.setTextureSize(64, 32);
		spinner.mirror = true;
		setRotation(spinner, 0F, 0F, 0F);
		bipedRightLeg = new ModelRenderer(this, 0, 16);
		bipedRightLeg.addBox(-1.5F, 0F, -1.5F, 3, 12, 3);
		bipedRightLeg.setRotationPoint(-2F, 12F, 0F);
		bipedRightLeg.setTextureSize(64, 32);
		bipedRightLeg.mirror = true;
		setRotation(bipedRightLeg, 0F, 0F, 0F);
		bipedLeftLeg = new ModelRenderer(this, 0, 16);
		bipedLeftLeg.addBox(-1.5F, 0F, -1.5F, 3, 12, 3);
		bipedLeftLeg.setRotationPoint(2F, 12F, 0F);
		bipedLeftLeg.setTextureSize(64, 32);
		bipedLeftLeg.mirror = true;
		setRotation(bipedLeftLeg, 0F, 0F, 0F);
		bodyIn = new ModelRenderer(this, 48, 20);
		bodyIn.addBox(-3F, 1F, -1F, 6, 10, 2);
		bodyIn.setRotationPoint(0F, 0F, 0F);
		bodyIn.setTextureSize(64, 32);
		bodyIn.mirror = true;
		setRotation(bodyIn, 0F, 0F, 0F);
		headIn = new ModelRenderer(this, 40, 0);
		headIn.addBox(-3F, -6F, -3F, 6, 6, 6);
		headIn.setRotationPoint(0F, 0F, 0F);
		headIn.setTextureSize(64, 32);
		headIn.mirror = true;
		bipedHead.addChild(headIn);
		setRotation(headIn, 0F, 0F, 0F);
		bipedLeftArm = new ModelRenderer(this, 36, 15);
		bipedLeftArm.addBox(-1F, -2F, -1.5F, 3, 14, 3);
		bipedLeftArm.setRotationPoint(5F, 2F, 0F);
		bipedLeftArm.setTextureSize(64, 32);
		bipedLeftArm.mirror = true;
		setRotation(bipedLeftArm, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		// super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bipedHead.render(f5);
		bipedBody.render(f5);
		bipedRightArm.render(f5);
		spinner.render(f5);
		bipedRightLeg.render(f5);
		bipedLeftLeg.render(f5);
		bodyIn.render(f5);
		// headIn.render(f5);
		bipedLeftArm.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3,
			float par4, float par5, float par6, Entity ent) {
		bipedHead.rotateAngleY = par4 / (180F / (float) Math.PI);
		bipedHead.rotateAngleX = par5 / (180F / (float) Math.PI);
		bipedHeadwear.rotateAngleY = bipedHead.rotateAngleY;
		bipedHeadwear.rotateAngleX = bipedHead.rotateAngleX;
		bipedRightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F
				+ (float) Math.PI)
				* 2.0F * par2 * 0.5F;
		bipedLeftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F
				* par2 * 0.5F;
		bipedRightArm.rotateAngleZ = 0.0F;
		bipedLeftArm.rotateAngleZ = 0.0F;
		bipedRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F
				* par2;
		bipedLeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F
				+ (float) Math.PI)
				* 1.4F * par2;
		bipedRightLeg.rotateAngleY = 0.0F;
		bipedLeftLeg.rotateAngleY = 0.0F;
		if (isRiding) {
			bipedRightArm.rotateAngleX += -((float) Math.PI / 5F);
			bipedLeftArm.rotateAngleX += -((float) Math.PI / 5F);
			bipedRightLeg.rotateAngleX = -((float) Math.PI * 2F / 5F);
			bipedLeftLeg.rotateAngleX = -((float) Math.PI * 2F / 5F);
			bipedRightLeg.rotateAngleY = ((float) Math.PI / 10F);
			bipedLeftLeg.rotateAngleY = -((float) Math.PI / 10F);
		}
		if (heldItemLeft != 0) {
			bipedLeftArm.rotateAngleX = bipedLeftArm.rotateAngleX * 0.5F
					- ((float) Math.PI / 10F) * (float) heldItemLeft;
		}
		if (heldItemRight != 0) {
			bipedRightArm.rotateAngleX = bipedRightArm.rotateAngleX * 0.5F
					- ((float) Math.PI / 10F) * (float) heldItemRight;
		}
		bipedRightArm.rotateAngleY = 0.0F;
		bipedLeftArm.rotateAngleY = 0.0F;
		if (onGround > -9990F) {
			float f = onGround;
			bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f)
					* (float) Math.PI * 2.0F) * 0.2F;
			bipedRightArm.rotationPointZ = MathHelper
					.sin(bipedBody.rotateAngleY) * 5F;
			bipedRightArm.rotationPointX = -MathHelper
					.cos(bipedBody.rotateAngleY) * 5F;
			bipedLeftArm.rotationPointZ = -MathHelper
					.sin(bipedBody.rotateAngleY) * 5F;
			bipedLeftArm.rotationPointX = MathHelper
					.cos(bipedBody.rotateAngleY) * 5F;
			bipedRightArm.rotateAngleY += bipedBody.rotateAngleY;
			bipedLeftArm.rotateAngleY += bipedBody.rotateAngleY;
			bipedLeftArm.rotateAngleX += bipedBody.rotateAngleY;
			f = 1.0F - onGround;
			f *= f;
			f *= f;
			f = 1.0F - f;
			float f2 = MathHelper.sin(f * (float) Math.PI);
			float f4 = MathHelper.sin(onGround * (float) Math.PI)
					* -(bipedHead.rotateAngleX - 0.7F) * 0.75F;
			bipedRightArm.rotateAngleX -= (double) f2 * 1.2D + (double) f4;
			bipedRightArm.rotateAngleY += bipedBody.rotateAngleY * 2.0F;
			bipedRightArm.rotateAngleZ = MathHelper.sin(onGround
					* (float) Math.PI)
					* -0.4F;
		}
		if (isSneak) {
			bipedBody.rotateAngleX = 0.5F;
			bipedRightArm.rotateAngleX += 0.4F;
			bipedLeftArm.rotateAngleX += 0.4F;
			bipedRightLeg.rotationPointZ = 4F;
			bipedLeftLeg.rotationPointZ = 4F;
			bipedRightLeg.rotationPointY = 9F;
			bipedLeftLeg.rotationPointY = 9F;
			bipedHead.rotationPointY = 1.0F;
		} else {
			bipedBody.rotateAngleX = 0.0F;
			bipedRightLeg.rotationPointZ = 0.0F;
			bipedLeftLeg.rotationPointZ = 0.0F;
			bipedRightLeg.rotationPointY = 12F;
			bipedLeftLeg.rotationPointY = 12F;
			bipedHead.rotationPointY = 0.0F;
		}
		bipedRightArm.rotateAngleZ += MathHelper.cos(par3 * 0.36F) * 0.2F + 0.2F;// 0.09F
		bipedLeftArm.rotateAngleZ -= MathHelper.cos(par3 * 0.36F) * 0.2F + 0.2F;// 0.09F
		bipedRightArm.rotateAngleX += MathHelper.sin(par3 * 0.268F) * 0.05F; // 0.067F
		bipedLeftArm.rotateAngleX -= MathHelper.sin(par3 * 0.268F) * 0.05F;// 0.067F
		if (aimedBow) {
			float f1 = 0.0F;
			float f3 = 0.0F;
			bipedRightArm.rotateAngleZ = 0.0F;
			bipedLeftArm.rotateAngleZ = 0.0F;
			bipedRightArm.rotateAngleY = -(0.1F - f1 * 0.6F)
					+ bipedHead.rotateAngleY;
			bipedLeftArm.rotateAngleY = (0.1F - f1 * 0.6F)
					+ bipedHead.rotateAngleY + 0.4F;
			bipedRightArm.rotateAngleX = -((float) Math.PI / 2F)
					+ bipedHead.rotateAngleX;
			bipedLeftArm.rotateAngleX = -((float) Math.PI / 2F)
					+ bipedHead.rotateAngleX;
			bipedRightArm.rotateAngleX -= f1 * 1.2F - f3 * 0.4F;
			bipedLeftArm.rotateAngleX -= f1 * 1.2F - f3 * 0.4F;
			bipedRightArm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
			bipedLeftArm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
			bipedRightArm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
			bipedLeftArm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
		}
		spinner.rotateAngleZ = par3 / 8f;
	}
}
