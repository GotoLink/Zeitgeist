package zeitgeist.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class zei_ModelCannon extends ModelBase {
	public ModelRenderer barrel;
	public ModelRenderer head;
	public ModelRenderer side1;
	// fields
	ModelRenderer base;
	ModelRenderer side2;

	public zei_ModelCannon() {
		textureWidth = 128;
		textureHeight = 64;
		head = new ModelRenderer(this, 62, 20);
		head.addBox(-2.5F, -5F, -7F, 5, 5, 7);
		head.setRotationPoint(0F, -2F, -5F);
		head.setTextureSize(128, 64);
		head.mirror = true;
		setRotation(head, 0F, 3.141593F, 0F);
		base = new ModelRenderer(this, 0, 0);
		base.addBox(-8F, -4F, -8F, 16, 4, 16);
		base.setRotationPoint(0F, 24F, 0F);
		base.setTextureSize(128, 64);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		side2 = new ModelRenderer(this, 0, 20);
		side2.addBox(4F, -6F, -8F, 4, 6, 14);
		side2.setRotationPoint(0F, 0, 0F);
		side2.setTextureSize(128, 64);
		side2.mirror = true;
		setRotation(side2, 0F, 0F, 0F);
		barrel = new ModelRenderer(this, 18, 20);
		barrel.addBox(-4F, -4F, -8F, 8, 8, 28);
		barrel.setRotationPoint(0F, -6F, -3F);
		barrel.setTextureSize(128, 64);
		barrel.mirror = true;
		setRotation(barrel, 1.59868F, 0F, 0F);
		side1 = new ModelRenderer(this, 0, 20);
		side1.addBox(-8F, -6F, -8F, 4, 6, 14);
		side1.setRotationPoint(0F, 20F, 0F);
		side1.setTextureSize(128, 64);
		side1.mirror = true;
		setRotation(side1, 0F, 0F, 0F);
		barrel.addChild(head);
		side1.addChild(barrel);
		side1.addChild(side2);
	}

	public void renderAll() {
		float f5 = 0.0625F;
		base.render(f5);
		// side2.render(f5);
		// barrel.render(f5);
		side1.render(f5);
		side1.rotateAngleY = 0.25f;
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
