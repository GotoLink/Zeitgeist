package zeitgeist.client.models;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class zei_ModelSentry extends ModelQuadruped {
	public zei_ModelSentry() {
		super(0, 0);
		/*
		 * hip = new ModelRenderer(this,40, 11); hip.addBox(-4F, -3F, 0F, 8, 6,
		 * 4, 0F); hip.setRotationPoint(0F, 12F, 5F); hip.rotateAngleX =
		 * -0.1858931F; hip.rotateAngleY = 0F; hip.rotateAngleZ = 0F; hip.mirror
		 * = false;
		 * 
		 * head = new ModelRenderer(this,0, 0); head.addBox(-3F, -3F, -9F, 6, 3,
		 * 10, 0F); head.setRotationPoint(0F, 11F, -12F); head.rotateAngleX =
		 * 0F; head.rotateAngleY = 0F; head.rotateAngleZ = 0F; head.mirror =
		 * false;
		 * 
		 * leg1 = new ModelRenderer(this,0, 16); leg1.addBox(-2F, 0F, -2F, 4,
		 * 10, 4, 0F); leg1.setRotationPoint(-4F, 14F, 7F); leg1.rotateAngleX =
		 * 0F; leg1.rotateAngleY = 0F; leg1.rotateAngleZ = 0F; leg1.mirror =
		 * false;
		 * 
		 * waist = new ModelRenderer(this,40, 1); waist.addBox(-2F, -2F, 0F, 4,
		 * 3, 8, 0F); waist.setRotationPoint(0F, 12F, -3F); waist.rotateAngleX =
		 * -0.2974289F; waist.rotateAngleY = 0F; waist.rotateAngleZ = 0F;
		 * waist.mirror = false;
		 * 
		 * body=new ModelRenderer(this,15, 14); body.addBox(-5F, -10F, -4F, 10,
		 * 10, 8, 0F); body.setRotationPoint(0F, 8F, 0F); body.rotateAngleX =
		 * 1.831047F; body.rotateAngleY = 0F; body.rotateAngleZ = 0F;
		 * body.mirror = false;
		 * 
		 * leg2 = new ModelRenderer(this,0, 16); leg2.addBox(-2F, 0F, -2F, 4,
		 * 10, 4, 0F); leg2.setRotationPoint(4F, 14F, 7F); leg2.rotateAngleX =
		 * 0F; leg2.rotateAngleY = 0F; leg2.rotateAngleZ = 0F; leg2.mirror =
		 * false;
		 * 
		 * leg3 = new ModelRenderer(this,0, 16); leg3.addBox(-2F, 0F, -2F, 4,
		 * 12, 4, 0F); leg3.setRotationPoint(-6F, 12F, -5F); leg3.rotateAngleX =
		 * 0F; leg3.rotateAngleY = 0F; leg3.rotateAngleZ = 0F; leg3.mirror =
		 * false;
		 * 
		 * leg4 = new ModelRenderer(this,0, 16); leg4.addBox(-2F, 0F, -2F, 4,
		 * 12, 4, 0F); leg4.setRotationPoint(6F, 12F, -5F); leg4.rotateAngleX =
		 * 0F; leg4.rotateAngleY = 0F; leg4.rotateAngleZ = 0F; leg4.mirror =
		 * false;
		 * 
		 * bar = new ModelRenderer(this,40, 0); bar.addBox(-1F, -1F, 0F, 2, 2,
		 * 10, 0F); bar.setRotationPoint(0F, 5F, -1F); bar.rotateAngleX =
		 * -0.7807508F; bar.rotateAngleY = 0F; bar.rotateAngleZ = 0F; bar.mirror
		 * = false;
		 * 
		 * neck = new ModelRenderer(this,40, 1); neck.addBox(-2F, -1F, 0F, 4, 3,
		 * 8, 0F); neck.setRotationPoint(0F, 10F, -12F); neck.rotateAngleX =
		 * 0.3346075F; neck.rotateAngleY = 0F; neck.rotateAngleZ = 0F;
		 * neck.mirror = false;
		 * 
		 * head1 = new ModelRenderer(this,0, 3); head1.addBox(-3F, -3F, -9F, 6,
		 * 3, 10, 0F); head1.setRotationPoint(0F, 11F, -12F); head1.rotateAngleX
		 * = -0.5576792F; head1.rotateAngleY = 0F; head1.rotateAngleZ =
		 * (float)Math.PI; head1.mirror = false;
		 */
		textureWidth = 64;
		textureHeight = 32;
		hip = new ModelRenderer(this, 44, 11);
		hip.addBox(-3F, -3F, 0F, 6, 5, 4);
		hip.setRotationPoint(0F, 12F, 4F);
		hip.setTextureSize(64, 32);
		hip.mirror = true;
		setRotation(hip, -0.5130648F, 0F, 0F);
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-3F, -3F, -9F, 6, 3, 10);
		head.setRotationPoint(0F, 10F, -12F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		body = new ModelRenderer(this, 40, 1);
		body.addBox(-2F, -2F, 0F, 4, 2, 10);
		body.setRotationPoint(0F, 13F, -7F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, -0.0223072F, 0F, 0F);
		seg1 = new ModelRenderer(this, 15, 14);
		seg1.addBox(-4F, -9F, -4F, 8, 10, 5);
		seg1.setRotationPoint(0F, 11F, -7F);
		seg1.setTextureSize(64, 32);
		seg1.mirror = true;
		setRotation(seg1, -0.1549109F, 0F, 0F);
		leg1 = new ModelRenderer(this, 0, 16);
		leg1.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
		leg1.setRotationPoint(5F, 12F, -9F);
		leg1.setTextureSize(64, 32);
		leg1.mirror = true;
		leg2 = new ModelRenderer(this, 0, 16);
		leg2.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
		leg2.setRotationPoint(-5F, 12F, -9F);
		leg2.setTextureSize(64, 32);
		leg2.mirror = true;
		leg3 = new ModelRenderer(this, 0, 16);
		leg3.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
		leg3.setRotationPoint(5F, 12F, 6F);
		leg3.setTextureSize(64, 32);
		leg3.mirror = true;
		leg4 = new ModelRenderer(this, 0, 16);
		leg4.addBox(-1.5F, 0F, -1.5F, 3, 6, 3);
		leg4.setRotationPoint(-5F, 12F, 6F);
		leg4.setTextureSize(64, 32);
		leg4.mirror = true;
		leg12 = new ModelRenderer(this, 0, 24);
		leg12.addBox(-1F, 0F, -1F, 2, 6, 2);
		leg12.setRotationPoint(1F, 6F, 2F);// leg12.setRotationPoint(6F, 18F,
											// -7F);
		leg12.setTextureSize(64, 32);
		leg12.mirror = true;
		setRotation(leg12, -0.4461433F, 0F, 0F);
		leg1.addChild(leg12);
		leg2.addChild(leg12);
		leg3.addChild(leg12);
		leg4.addChild(leg12);
		setRotation(leg1, 0.4758862F, 0F, 0F);
		bar = new ModelRenderer(this, 40, 0);
		bar.addBox(-1F, -1F, 0F, 2, 2, 10);
		bar.setRotationPoint(1F, 8F, -3F);
		bar.setTextureSize(64, 32);
		bar.mirror = true;
		setRotation(bar, -0.3346075F, 0F, 0F);
		neck = new ModelRenderer(this, 40, 1);
		neck.addBox(-2F, -1F, 0F, 4, 2, 9);
		neck.setRotationPoint(0F, 11F, -12F);
		neck.setTextureSize(64, 32);
		neck.mirror = true;
		setRotation(neck, 0.3271718F, 0F, 0F);
		seg2 = new ModelRenderer(this, 14, 14);
		seg2.addBox(-5F, -9F, -4F, 10, 10, 5);
		seg2.setRotationPoint(0F, 10F, -1F);
		seg2.setTextureSize(64, 32);
		seg2.mirror = true;
		setRotation(seg2, -0.4523398F, 0F, 0F);
		seg3 = new ModelRenderer(this, 17, 18);
		seg3.addBox(-4F, -7F, -3F, 8, 8, 3);
		seg3.setRotationPoint(0F, 11F, 3F);
		seg3.setTextureSize(64, 32);
		seg3.mirror = true;
		setRotation(seg3, -0.928226F, 0F, 0F);
		jaw = new ModelRenderer(this, 38, 22);
		jaw.addBox(-3F, 0F, -7F, 6, 3, 7);
		jaw.setRotationPoint(0F, 0F, -2F);// jaw.setRotationPoint(0F, 10F,
											// -14F);
		jaw.setTextureSize(64, 32);
		jaw.mirror = true;
		setRotation(jaw, 0F, 0F, 0F);
		head.addChild(jaw);
		tail = new ModelRenderer(this, 40, 0);
		tail.addBox(-1F, -1F, -3F, 2, 2, 10);
		tail.setRotationPoint(0F, 11F, 8F);
		tail.setTextureSize(64, 32);
		tail.mirror = true;
		setRotation(tail, -0.6915222F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		hip.render(f5);
		// head.render(f5);
		// body.render(f5);
		seg1.render(f5);
		// leg1.render(f5);
		// leg12.render(f5);
		bar.render(f5);
		neck.render(f5);
		seg2.render(f5);
		seg3.render(f5);
		// jaw.render(f5);
		tail.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity ent) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
		jaw.rotateAngleX = -0.26f
				* MathHelper.cos(f2 * (0.09F + 0.01f * f * f1)); // (-head.rotateAngleX-0.2f)
		// head1.rotateAngleX=(-head.rotateAngleX-0.2f)-0.26f*MathHelper.cos(f2
		// * (0.09F+0.01f*f*f1));
		// head1.rotateAngleY=-head.rotateAngleY;
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	ModelRenderer hip;
	ModelRenderer seg1;
	ModelRenderer bar;
	ModelRenderer neck;
	ModelRenderer seg2;
	ModelRenderer seg3;
	ModelRenderer jaw;
	ModelRenderer leg12;
	ModelRenderer tail;
}
