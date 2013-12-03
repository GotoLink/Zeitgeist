package zeitgeist.client.models;

import zeitgeist.client.zei_FlatRenderer;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class zei_ModelNoop extends ModelQuadruped {
	public zei_ModelNoop() {
		super(0, 0f);
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-3F, -2F, -5F, 6, 4, 5);
		head.setRotationPoint(0F, 21F, -3F);
		head.rotateAngleX = -0.20944F;
		head.rotateAngleY = 0F;
		head.rotateAngleZ = 0F;
		head.mirror = false;
		body = new ModelRenderer(this, 28, 8);
		body.addBox(-4F, -6F, -4F, 8, 10, 4);
		body.setRotationPoint(0F, 19F, 2F);
		body.rotateAngleX = 1.74533F;
		body.rotateAngleY = 0F;
		body.rotateAngleZ = 0F;
		body.mirror = false;
		leg1 = new ModelRenderer(this, 0, 16);
		leg1.addBox(-2F, 0F, -2F, 3, 3, 3);
		leg1.setRotationPoint(-3F, 21F, 7F);
		leg1.rotateAngleX = 0F;
		leg1.rotateAngleY = 0.5236F;
		leg1.rotateAngleZ = 0.5236F;
		leg1.mirror = false;
		leg2 = new ModelRenderer(this, 0, 16);
		leg2.addBox(-1F, 0F, -2F, 3, 3, 3);
		leg2.setRotationPoint(3F, 21F, 7F);
		leg2.rotateAngleX = 0F;
		leg2.rotateAngleY = -0.5236F;
		leg2.rotateAngleZ = -0.5236F;
		leg2.mirror = false;
		leg3 = new ModelRenderer(this, 0, 16);
		leg3.addBox(-2F, 0F, -2F, 3, 3, 3);
		leg3.setRotationPoint(-3F, 21F, -4F);
		leg3.rotateAngleX = 0F;
		leg3.rotateAngleY = -0.5236F;
		leg3.rotateAngleZ = 0.5236F;
		leg3.mirror = false;
		leg4 = new ModelRenderer(this, 0, 16);
		leg4.addBox(-1F, 0F, -2F, 3, 3, 3);
		leg4.setRotationPoint(3F, 21F, -4F);
		leg4.rotateAngleX = 0F;
		leg4.rotateAngleY = 0.5236F;
		leg4.rotateAngleZ = -0.5236F;
		leg4.mirror = false;
		eye1 = new zei_FlatRenderer(this, -6, -3);
		eye1.addBox(-3F, -1F, -5.5F, 3, 3, 3);
		eye1.setRotationPoint(0F, 21F, -3F);
		eye1.rotateAngleX = -0.20944F;
		eye1.rotateAngleY = 0F;
		eye1.rotateAngleZ = 0F;
		eye1.mirror = false;
		eye2 = new zei_FlatRenderer(this, -6, -3);
		eye2.addBox(0F, -1F, -5.5F, 3, 3, 3);
		eye2.setRotationPoint(0F, 21F, -3F);
		eye2.rotateAngleX = -0.20944F;
		eye2.rotateAngleY = 0F;
		eye2.rotateAngleZ = 0F;
		eye2.mirror = false;
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		head.render(f5);
		eye2.rotateAngleX = head.rotateAngleX;
		eye2.rotateAngleY = head.rotateAngleY - 0.17f;
		eye1.rotateAngleX = head.rotateAngleX;
		eye1.rotateAngleY = head.rotateAngleY + 0.17f;
		body.render(f5);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
		eye1.render(f5);
		eye2.render(f5);
	}

	// fields
	// ModelRenderer head;
	// ModelRenderer body;
	// ModelRenderer leg1;
	// ModelRenderer leg2;
	// ModelRenderer leg3;
	// ModelRenderer leg4;
	zei_FlatRenderer eye1;
	zei_FlatRenderer eye2;
}
