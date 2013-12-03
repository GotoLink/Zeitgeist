package zeitgeist.client.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class zei_ModelAncient extends ModelBiped {
	ModelRenderer neck;
	ModelRenderer rightarm3;
	ModelRenderer leftleg2;
	ModelRenderer body2;
	ModelRenderer rightarm2;
	ModelRenderer rightleg2;
	ModelRenderer leftarm2;
	ModelRenderer leftarm3;

	public zei_ModelAncient() {
		textureWidth = 128;
		textureHeight = 64;
		neck = new ModelRenderer(this, 0, 56);
		neck.addBox(-2F, -4F, -2F, 4, 4, 4);
		neck.setRotationPoint(0F, -19F, 0F);
		neck.setTextureSize(184, 182);
		neck.mirror = true;
		setRotation(neck, 0F, 0F, 0F);
		bipedCloak = new ModelRenderer(this, 81, 0);
		bipedCloak.addBox(-6F, 0F, 0F, 12, 32, 1);
		bipedCloak.setRotationPoint(0F, -21F, 3.5F);
		bipedCloak.setTextureSize(184, 182);
		bipedCloak.mirror = true;
		setRotation(bipedCloak, 0F, 0F, 0F);
		rightarm3 = new ModelRenderer(this, 51, 45);
		rightarm3.addBox(-1.5F, 0F, -1.5F, 3, 10, 3);
		rightarm3.setRotationPoint(0F, 8F, 0F);
		rightarm3.setTextureSize(184, 182);
		rightarm3.mirror = true;
		setRotation(rightarm3, -0.12F, 0F, 0F);
		bipedLeftArm = new ModelRenderer(this, 55, 20);
		bipedLeftArm.addBox(-1F, -2F, -2.5F, 5, 6, 5);
		bipedLeftArm.setRotationPoint(6F, -17F, 0F);
		bipedLeftArm.setTextureSize(184, 182);
		bipedLeftArm.mirror = true;
		setRotation(bipedLeftArm, 0F, 0F, 0F);
		leftleg2 = new ModelRenderer(this, 0, 39);
		leftleg2.addBox(-2F, 0F, -2F, 4, 13, 4);
		leftleg2.setRotationPoint(0, 11F, 0F);
		leftleg2.setTextureSize(184, 182);
		leftleg2.mirror = true;
		setRotation(leftleg2, 0.12F, 0F, 0F);
		bipedLeftLeg = new ModelRenderer(this, 0, 21);
		bipedLeftLeg.addBox(-2F, 0F, -2F, 4, 12, 4);
		bipedLeftLeg.setRotationPoint(2F, -1F, 0F);
		bipedLeftLeg.setTextureSize(184, 182);
		bipedLeftLeg.mirror = true;
		setRotation(bipedLeftLeg, 0F, 0F, 0F);
		bipedRightArm = new ModelRenderer(this, 55, 20);
		bipedRightArm.addBox(-4F, -2F, -2.5F, 5, 6, 5);
		bipedRightArm.setRotationPoint(-6F, -17F, 0F);
		bipedRightArm.setTextureSize(184, 182);
		bipedRightArm.mirror = true;
		setRotation(bipedRightArm, 0F, 0F, 0F);
		body2 = new ModelRenderer(this, 20, 39);
		body2.addBox(-4F, 0F, -2.5F, 8, 10, 5);
		body2.setRotationPoint(0F, -11F, 0F);
		body2.setTextureSize(184, 182);
		body2.mirror = true;
		setRotation(body2, 0F, 0F, 0F);
		bipedHead = new ModelRenderer(this, 0, 0);
		bipedHead.addBox(-3.5F, -10F, -4F, 7, 8, 7);
		bipedHead.setRotationPoint(0F, -21F, 0F);
		bipedHead.setTextureSize(184, 182);
		bipedHead.mirror = true;
		setRotation(bipedHead, 0F, 0F, 0F);
		bipedBody = new ModelRenderer(this, 20, 20);
		bipedBody.addBox(-5F, 0F, -3.5F, 10, 10, 7);
		bipedBody.setRotationPoint(0F, -21F, 0F);
		bipedBody.setTextureSize(184, 182);
		bipedBody.mirror = true;
		setRotation(bipedBody, 0F, 0F, 0F);
		bipedRightLeg = new ModelRenderer(this, 0, 21);
		bipedRightLeg.addBox(-2F, 0F, -2F, 4, 12, 4);
		bipedRightLeg.setRotationPoint(-2F, -1F, 0F);
		bipedRightLeg.setTextureSize(184, 182);
		bipedRightLeg.mirror = true;
		setRotation(bipedRightLeg, 0F, 0F, 0F);
		rightarm2 = new ModelRenderer(this, 55, 31);
		rightarm2.addBox(-2F, 0F, -2F, 4, 8, 4);
		rightarm2.setRotationPoint(-1F, 4F, 0F); // -7F
		rightarm2.setTextureSize(184, 182);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0F, 0F, 0F);
		rightleg2 = new ModelRenderer(this, 0, 39);
		rightleg2.addBox(-2F, 0F, -2F, 4, 13, 4);
		rightleg2.setRotationPoint(0, 11F, 0F);
		rightleg2.setTextureSize(184, 182);
		rightleg2.mirror = true;
		setRotation(rightleg2, 0.12F, 0F, 0F);
		leftarm2 = new ModelRenderer(this, 55, 31);
		leftarm2.addBox(-2F, 0F, -2F, 4, 8, 4);
		leftarm2.setRotationPoint(1F, 4F, 0F);
		leftarm2.setTextureSize(184, 182);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0F, 0F, 0F);
		leftarm3 = new ModelRenderer(this, 51, 45);
		leftarm3.addBox(-1.5F, 0F, -1.5F, 3, 10, 3);
		leftarm3.setRotationPoint(0F, 8, 0F);
		leftarm3.setTextureSize(184, 182);
		leftarm3.mirror = true;
		setRotation(leftarm3, -0.12F, 0F, 0F);
		leftarm2.addChild(leftarm3);
		bipedLeftArm.addChild(leftarm2);
		rightarm2.addChild(rightarm3);
		bipedRightArm.addChild(rightarm2);
		bipedRightLeg.addChild(rightleg2);
		bipedLeftLeg.addChild(leftleg2);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		// super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		bipedHead.render(f5);
		// bipedCloak.render(f5);
		// leftarm2.render(f5);
		bipedLeftArm.render(f5);
		bipedRightLeg.render(f5);
		bipedLeftLeg.render(f5);
		// bipedHeadwear.render(f5);
		bipedRightArm.render(f5);
		// rightarm2.render(f5);
		bipedBody.render(f5);
		neck.render(f5);
		body2.render(f5);
		// head.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3,
			float par4, float par5, float par6) {
		bipedHead.rotateAngleY = par4 / (180F / (float) Math.PI);
		bipedHead.rotateAngleX = par5 / (180F / (float) Math.PI);
		// bipedHeadwear.rotateAngleY = bipedHead.rotateAngleY;
		// bipedHeadwear.rotateAngleX = bipedHead.rotateAngleX;
		bipedRightArm.rotateAngleX = 0.06f
				+ MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 2.0F
				* par2 * 0.5F;
		bipedLeftArm.rotateAngleX = 0.06f + MathHelper.cos(par1 * 0.6662F)
				* 2.0F * par2 * 0.5F;
		bipedRightArm.rotateAngleZ = 0.0F;
		bipedLeftArm.rotateAngleZ = 0.0F;
		bipedRightLeg.rotateAngleX = -0.06f + MathHelper.cos(par1 * 0.6662F)
				* 1.4F * par2;
		bipedLeftLeg.rotateAngleX = -0.06f
				+ MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F
				* par2;
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
					.sin(bipedBody.rotateAngleY) * 6F;
			bipedRightArm.rotationPointX = -MathHelper
					.cos(bipedBody.rotateAngleY) * 6F;
			bipedLeftArm.rotationPointZ = -MathHelper
					.sin(bipedBody.rotateAngleY) * 6F;
			bipedLeftArm.rotationPointX = MathHelper
					.cos(bipedBody.rotateAngleY) * 6F;
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
			bipedRightLeg.rotationPointY = -4F;
			bipedLeftLeg.rotationPointY = -4F;
			bipedHead.rotationPointY = -19.0F;
		} else {
			bipedBody.rotateAngleX = 0.0F;
			bipedRightLeg.rotationPointZ = 0.0F;
			bipedLeftLeg.rotationPointZ = 0.0F;
			bipedRightLeg.rotationPointY = -1F;
			bipedLeftLeg.rotationPointY = -1F;
			bipedHead.rotationPointY = -20.0F;
		}
		bipedRightArm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
		bipedLeftArm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
		bipedRightArm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
		bipedLeftArm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
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
	}
}
