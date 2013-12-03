package zeitgeist.common.world.biome;

import net.minecraft.world.biome.BiomeGenBase;

public class zei_Biomes {
	public static final BiomeGenBase giants = (new zei_BiomeGenGiants(42))
			.setColor(0xff0000).setBiomeName("Giants");
	public static final BiomeGenBase autumn = (new zei_BiomeGenAutumn(43))
			.setColor(0xff0000).setBiomeName("Autumn");
	public static final BiomeGenBase windFarm = (new zei_BiomeGenWindFarm(44))
			.setColor(0xff0000).setBiomeName("Autumn");
	public static final BiomeGenBase city = (new zei_BiomeGenCity(45))
			.setColor(0xff0000).setBiomeName("Autumn");
}
