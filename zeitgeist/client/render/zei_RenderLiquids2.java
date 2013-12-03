package zeitgeist.client.render;

import zeitgeist.common.block.zei_BlockFluid2;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class zei_RenderLiquids2 {
	
	  public static boolean render(RenderBlocks renderblocks, IBlockAccess blockAccess,Block par1Block,int par2, int par3, int par4)
	    {
	        Tessellator tessellator = Tessellator.instance;
	        int i = par1Block.colorMultiplier(blockAccess, par2, par3, par4);
	        float f = (float)(i >> 16 & 0xff) / 255F;
	        float f1 = (float)(i >> 8 & 0xff) / 255F;
	        float f2 = (float)(i & 0xff) / 255F;
	        boolean flag = par1Block.shouldSideBeRendered(blockAccess, par2, par3 + 1, par4, 1);
	        boolean flag1 = par1Block.shouldSideBeRendered(blockAccess, par2, par3 - 1, par4, 0);
	        boolean aflag[] =
	        {
	            par1Block.shouldSideBeRendered(blockAccess, par2, par3, par4 - 1, 2), par1Block.shouldSideBeRendered(blockAccess, par2, par3, par4 + 1, 3), par1Block.shouldSideBeRendered(blockAccess, par2 - 1, par3, par4, 4), par1Block.shouldSideBeRendered(blockAccess, par2 + 1, par3, par4, 5)
	        };

	        if (!flag && !flag1 && !aflag[0] && !aflag[1] && !aflag[2] && !aflag[3])
	        {
	            return false;
	        }

	        boolean flag2 = false;
	        float f3 = 0.5F;
	        float f4 = 1.0F;
	        float f5 = 0.8F;
	        float f6 = 0.6F;
	        double d = 0.0D;
	        double d1 = 1.0D;
	        Material material = par1Block.blockMaterial;
	        int j = blockAccess.getBlockMetadata(par2, par3, par4);
	        double d2 = getFluidHeight(blockAccess,par2, par3, par4, material);
	        double d3 = getFluidHeight(blockAccess,par2, par3, par4 + 1, material);
	        double d4 = getFluidHeight(blockAccess,par2 + 1, par3, par4 + 1, material);
	        double d5 = getFluidHeight(blockAccess,par2 + 1, par3, par4, material);
	        double d6 = 0.0010000000474974513D;

	        if (renderblocks.renderAllFaces || flag)
	        {
	            flag2 = true;
	            int k = par1Block.getBlockTextureFromSideAndMetadata(1, j);
	            float f7 = (float)zei_BlockFluid2.func_293_a(blockAccess, par2, par3, par4, material);

	            if (f7 > -999F)
	            {
	                k = par1Block.getBlockTextureFromSideAndMetadata(2, j);
	            }

	            d2 -= d6;
	            d3 -= d6;
	            d4 -= d6;
	            d5 -= d6;
	            int l1 = (k & 0xf) << 4;
	            int i1 = k & 0xf0;
	            double d7 = ((double)l1 + 8D) / 256D;
	            double d8 = ((double)i1 + 8D) / 256D;

	            if (f7 < -999F)
	            {
	                f7 = 0.0F;
	            }
	            else
	            {
	                d7 = (float)(l1 + 16) / 256F;
	                d8 = (float)(i1 + 16) / 256F;
	            }

	            double d10 = (double)(MathHelper.sin(f7) * 8F) / 256D;
	            double d12 = (double)(MathHelper.cos(f7) * 8F) / 256D;
	            tessellator.setBrightness(par1Block.getMixedBrightnessForBlock(blockAccess, par2, par3, par4));
	            float f9 = 1.0F;
	            tessellator.setColorOpaque_F(f4 * f9 * f, f4 * f9 * f1, f4 * f9 * f2);
	            tessellator.addVertexWithUV(par2 + 0, (double)par3 + d2, par4 + 0, d7 - d12 - d10, (d8 - d12) + d10);
	            tessellator.addVertexWithUV(par2 + 0, (double)par3 + d3, par4 + 1, (d7 - d12) + d10, d8 + d12 + d10);
	            tessellator.addVertexWithUV(par2 + 1, (double)par3 + d4, par4 + 1, d7 + d12 + d10, (d8 + d12) - d10);
	            tessellator.addVertexWithUV(par2 + 1, (double)par3 + d5, par4 + 0, (d7 + d12) - d10, d8 - d12 - d10);
	        }

	        if (renderblocks.renderAllFaces || flag1)
	        {
	            tessellator.setBrightness(par1Block.getMixedBrightnessForBlock(blockAccess, par2, par3 - 1, par4));
	            float f8 = 1.0F;
	            tessellator.setColorOpaque_F(f3 * f8, f3 * f8, f3 * f8);
	            renderblocks.renderBottomFace(par1Block, par2, (double)par3 + d6, par4, par1Block.getBlockTextureFromSide(0));
	            flag2 = true;
	        }

	        for (int l = 0; l < 4; l++)
	        {
	            int k1 = par2;
	            int j1 = par4;

	            if (l == 0)
	            {
	                j1 = par4 - 1;
	            }

	            if (l == 1)
	            {
	                j1++;
	            }

	            if (l == 2)
	            {
	                k1 = par2 - 1;
	            }

	            if (l == 3)
	            {
	                k1++;
	            }

	            int i2 = par1Block.getBlockTextureFromSideAndMetadata(l + 2, j);
	            int j2 = (i2 & 0xf) << 4;
	            int k2 = i2 & 0xf0;

	            if (renderblocks.renderAllFaces || aflag[l])
	            {
	                double d9;
	                double d11;
	                double d13;
	                double d14;
	                double d15;
	                double d16;

	                if (l == 0)
	                {
	                    d11 = d2;
	                    d9 = d5;
	                    d14 = par2;
	                    d16 = par2 + 1;
	                    d13 = (double)par4 + d6;
	                    d15 = (double)par4 + d6;
	                }
	                else if (l == 1)
	                {
	                    d11 = d4;
	                    d9 = d3;
	                    d14 = par2 + 1;
	                    d16 = par2;
	                    d13 = (double)(par4 + 1) - d6;
	                    d15 = (double)(par4 + 1) - d6;
	                }
	                else if (l == 2)
	                {
	                    d11 = d3;
	                    d9 = d2;
	                    d14 = (double)par2 + d6;
	                    d16 = (double)par2 + d6;
	                    d13 = par4 + 1;
	                    d15 = par4;
	                }
	                else
	                {
	                    d11 = d5;
	                    d9 = d4;
	                    d14 = (double)(par2 + 1) - d6;
	                    d16 = (double)(par2 + 1) - d6;
	                    d13 = par4;
	                    d15 = par4 + 1;
	                }

	                flag2 = true;
	                double d17 = (float)(j2 + 0) / 256F;
	                double d18 = ((double)(j2 + 16) - 0.01D) / 256D;
	                double d19 = ((double)k2 + (1.0D - d11) * 16D) / 256D;
	                double d20 = ((double)k2 + (1.0D - d9) * 16D) / 256D;
	                double d21 = ((double)(k2 + 16) - 0.01D) / 256D;
	                tessellator.setBrightness(par1Block.getMixedBrightnessForBlock(blockAccess, k1, par3, j1));
	                float f10 = 1.0F;

	                if (l < 2)
	                {
	                    f10 *= f5;
	                }
	                else
	                {
	                    f10 *= f6;
	                }

	                tessellator.setColorOpaque_F(f4 * f10 * f, f4 * f10 * f1, f4 * f10 * f2);
	                tessellator.addVertexWithUV(d14, (double)par3 + d11, d13, d17, d19);
	                tessellator.addVertexWithUV(d16, (double)par3 + d9, d15, d18, d20);
	                tessellator.addVertexWithUV(d16, par3 + 0, d15, d18, d21);
	                tessellator.addVertexWithUV(d14, par3 + 0, d13, d17, d21);
	            }
	        }

	        par1Block.minY = d;
	        par1Block.maxY = d1;
	        return flag2;
	    }
	  
	  
	  public static float getFluidHeight(IBlockAccess blockAccess,int par1, int par2, int par3, Material par4Material)
	    {
	        int i = 0;
	        float f = 0.0F;

	        for (int j = 0; j < 4; j++)
	        {
	            int k = par1 - (j & 1);
	            int l = par3 - (j >> 1 & 1);

	            if (blockAccess.getBlockMaterial(k, par2 + 1, l) == par4Material)
	            {
	                return 1.0F;
	            }

	            Material material = blockAccess.getBlockMaterial(k, par2, l);

	            if (material == par4Material)
	            {
	                int i1 = blockAccess.getBlockMetadata(k, par2, l);

	                if (i1 >= 8 || i1 == 0)
	                {
	                    f += zei_BlockFluid2.getFluidHeightPercent(i1) * 10F;
	                    i += 10;
	                }

	                f += zei_BlockFluid2.getFluidHeightPercent(i1);
	                i++;
	            }
	            else if (!material.isSolid())
	            {
	                f++;
	                i++;
	            }
	        }

	        return 1.0F - f / (float)i;
	    }
}
