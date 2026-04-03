package com.example.override.biomeoverrides.nether;

import net.minecraft.src.ChunkProviderHell;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.WorldProviderHell;

public class CustomHellWorldProvider extends WorldProviderHell
{
	public IChunkProvider getChunkProvider() {

		return new CustomHellChunkProvider(this.worldObj, this.worldObj.getSeed());
	}
}
