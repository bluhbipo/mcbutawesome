package com.example.override.biomeoverrides.nether;

import com.example.block.ModBlockList;
import net.minecraft.src.*;

import java.util.Random;

public class HellBiomeDecorator extends BiomeDecorator
{

	public final WorldGenMinableNether netheriteGen;


	public HellBiomeDecorator(BiomeGenBase biome)
	{
		super(biome);
		netheriteGen = new WorldGenMinableNether(((Block) ModBlockList.netheriteOre).blockID, 8);
	}

	public void decorate(World world, Random random, int chunkX, int chunkZ) {
		if (this.currentWorld != null) {
			throw new RuntimeException("Already decorating!!");
		} else {
			this.currentWorld = world;
			this.randomGenerator = random;
			this.chunk_X = chunkX;
			this.chunk_Z = chunkZ;
			this.decorate();
			this.currentWorld = null;
			this.randomGenerator = null;
		}
	}

	protected void decorate() {
		this.generateOres();
	}

	protected void generateOres()
	{
		this.genStandardOre1(16, this.netheriteGen, 0, 120);
	}

	protected void genStandardOre1(int veinsPerChunk, WorldGenerator generator, int miny, int maxy) {
		for(int i = 0; i < veinsPerChunk; ++i) {
			int x = 16*this.chunk_X + this.randomGenerator.nextInt(16);
			int y = this.randomGenerator.nextInt(maxy - miny) + miny;
			int z = 16*this.chunk_Z + this.randomGenerator.nextInt(16);
			System.out.println("posting a netherite vein:"+x+", "+y+", "+z);
			generator.generate(this.currentWorld, this.randomGenerator, x, y, z);
		}

	}
}
