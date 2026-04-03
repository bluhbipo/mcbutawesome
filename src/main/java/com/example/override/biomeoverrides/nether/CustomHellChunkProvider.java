package com.example.override.biomeoverrides.nether;

import net.minecraft.src.*;

import java.util.Random;

public class CustomHellChunkProvider extends ChunkProviderHell
{
	World world;
	Random oreRand;
	public CustomHellChunkProvider(World world, long par2)
	{
		super(world, par2);
		this.world = world;
		this.oreRand = new Random(par2);
	}

	public void populate(IChunkProvider par1IChunkProvider, int chunkX, int chunkZ) {
		BlockSand.fallInstantly = true;
		super.populate(par1IChunkProvider,chunkX,chunkZ);
		CustomNether.getInstance().decorate(world, oreRand, chunkX, chunkZ);
		BlockSand.fallInstantly = false;
	}
}
