package zeitgeist.common;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import zeitgeist.common.block.zei_Blocks;
import zeitgeist.common.entity.zei_Entities;
import zeitgeist.common.item.zei_Items;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = "zeitgeist", name = "Zeitgeist", version = "alpha")
@NetworkMod(clientSideRequired = true)
public class mod_Zeitgeist implements IWorldGenerator {
	@Instance(value = "zeitgeist")
	public static mod_Zeitgeist INSTANCE;
	@SidedProxy(clientSide = "zeitgeist.client.ClientProxy", serverSide = "zeitgest.common.CommonProxy")
	public static CommonProxy proxy;
	public static zei_Ids self;

	public mod_Zeitgeist() {
		INSTANCE = this;
		proxy.init();
		zei_Recipes.init();
		zei_Blocks.init();
		zei_Items.init();
		zei_ChalkLogic.init();
		zei_Entities.init(this);
		NetworkRegistry.instance().registerGuiHandler(this, new zei_GuiManager());
	}

	static {
		// zei_Ids.OFF();
		self = new zei_Ids();
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (!world.provider.isHellWorld)
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
	}

	public void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
		for (int i = 0; i < 8; i++) {
			int randPosX = chunkX + rand.nextInt(16);
			int randPosY = rand.nextInt(16) + 48;
			int randPosZ = chunkZ + rand.nextInt(16);
			(new WorldGenMinable(zei_Ids.saltOre, 9)).generate(world, rand, randPosX, randPosY, randPosZ);
		}
		for (int i = 0; i < 4; i++) {
			int randPosX = chunkX + rand.nextInt(16);
			int randPosY = rand.nextInt(36);
			int randPosZ = chunkZ + rand.nextInt(16);
			(new WorldGenMinable(zei_Ids.sulfOre, 5)).generate(world, rand, randPosX, randPosY, randPosZ);
		}
	}
}
