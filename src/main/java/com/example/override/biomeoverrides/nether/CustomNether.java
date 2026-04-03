package com.example.override.biomeoverrides.nether;

import net.minecraft.src.*;

import java.util.Random;

public class CustomNether extends BiomeGenHell
{
	public BiomeDecorator theBiomeDecorator;
	private static CustomNether instance;
	public static CustomNether getInstance(){
		if(instance == null) instance = new CustomNether();
		return instance;
	}
	public CustomNether()
	{
		super(8);
		this.theBiomeDecorator = createBiomeDecorator();
		this.biomeName = "Hell";
		this.rainfall = 0.0f;
		this.temperature = 2.0f;
		this.color = 16711680;
	}
	protected BiomeDecorator createBiomeDecorator() {
		return new HellBiomeDecorator(this);
	}
	public void decorate(World world, Random random, int chunkX, int chunkZ) {
		this.theBiomeDecorator.decorate(world, random, chunkX, chunkZ);
	}
}
