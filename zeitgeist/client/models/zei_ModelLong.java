package zeitgeist.client.models;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class zei_ModelLong extends ModelQuadruped {
	// fields
	ModelRenderer ear2;
	ModelRenderer eye2;
	ModelRenderer ear1;
	ModelRenderer eye1;

	public zei_ModelLong() {
		super(0, 0);
		textureWidth = 64;
		textureHeight = 32;
		body = new ModelRenderer(this, 0, 12);
		body.addBox(-3F, 0F, -2F, 6, 8, 4);
		body.setRotationPoint(0F, 12F, 0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.4089647F, 0F, 0F);
		ear2 = new ModelRenderer(this, 48, 0);
		ear2.addBox(-1F, -10F, -0.5F, 2, 10, 1);
		ear2.setRotationPoint(-2F, -3, 0F);
		ear2.setTextureSize(64, 32);
		ear2.mirror = true;
		setRotation(ear2, -0.2230717F, 0F, 0F);
		eye2 = new ModelRenderer(this, 8, 8);
		eye2.addBox(-1F, -0.5F, -0.5F, 2, 1, 1);
		eye2.setRotationPoint(-2F, -2, -4F);
		eye2.setTextureSize(64, 32);
		eye2.mirror = true;
		setRotation(eye2, 0F, 0F, 0F);
		leg2 = new ModelRenderer(this, 8, 0);
		leg2.addBox(-1F, 0F, -1F, 2, 4, 2);
		leg2.setRotationPoint(-2F, 20F, 3F);
		leg2.setTextureSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0F, 0F, 0F);
		leg1 = new ModelRenderer(this, 0, 0);
		leg1.addBox(-1F, 0F, -1F, 2, 10, 2);
		leg1.setRotationPoint(-2F, 15F, -1F);
		leg1.setTextureSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, -0.296706F, 0F, 0F);
		leg3 = new ModelRenderer(this, 0, 0);
		leg3.addBox(-1F, 0F, -1F, 2, 10, 2);
		leg3.setRotationPoint(2F, 15F, -1F);
		leg3.setTextureSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, -0.296706F, 0F, 0F);
		leg4 = new ModelRenderer(this, 8, 0);
		leg4.addBox(-1F, 0F, -1F, 2, 4, 2);
		leg4.setRotationPoint(2F, 20F, 3F);
		leg4.setTextureSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0F, 0F, 0F);
		ear1 = new ModelRenderer(this, 48, 0);
		ear1.addBox(-1F, -10F, -0.5F, 2, 10, 1);
		ear1.setRotationPoint(2F, -3, 0F);
		ear1.setTextureSize(64, 32);
		ear1.mirror = true;
		setRotation(ear1, -0.2230717F, 0F, 0F);
		head = new ModelRenderer(this, 16, 0);
		head.addBox(-4F, -4F, -4F, 8, 4, 8);
		head.setRotationPoint(0F, 12F, 0F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		eye1 = new ModelRenderer(this, 8, 8);
		eye1.addBox(-1F, -0.5F, -0.5F, 2, 1, 1);
		eye1.setRotationPoint(2F, -2F, -4F);
		eye1.setTextureSize(64, 32);
		eye1.mirror = true;
		setRotation(eye1, 0F, 0F, 0F);
		head.addChild(eye1);
		head.addChild(eye2);
		head.addChild(ear1);
		head.addChild(ear2);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		// body.render(f5);
		// ear2.render(f5);
		// eye2.render(f5);
		// ear1.render(f5);
		// eye1.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity ent) {
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.body.rotateAngleX = 0.4089647F;
		this.leg2.rotateAngleX = MathHelper.cos(f * 3F + (float) Math.PI)
				* 1.4F * f1;
		this.leg4.rotateAngleX = MathHelper.cos(f * 3F) * 1.4F * f1;
		this.leg1.rotateAngleX = -0.3f + MathHelper.cos(f * 1.2f) * 1.4F * f1;
		this.leg3.rotateAngleX = -0.3f
				+ MathHelper.cos(f * 1.2f + (float) Math.PI) * 1.4F * f1;
		this.leg1.rotateAngleY = 0.6f;
		this.leg3.rotateAngleY = -0.6f;
	}
}
