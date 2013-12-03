package zeitgeist.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.Icon;

public class zei_BlockLumo extends Block {
	static Icon[] D;

	protected zei_BlockLumo(int i) {
		super(i, Material.rock);
	}

	@Override
	public int damageDropped(int i) {
		return i;
	}

	@Override
	public Icon getIcon(int i, int j) {
		return D[j]; // D[0]
	}

	static void loadSprites() {
		D = new Icon[16];
		D[1] = "/zeitgeist/sky.png");
		D[0] = "/zeitgeist/walk.png");
	}
}
