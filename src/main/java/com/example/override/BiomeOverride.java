package com.example.override;

import com.example.override.biomeoverrides.nether.CustomHellChunkProvider;
import com.example.override.biomeoverrides.nether.CustomHellWorldProvider;
import com.example.override.biomeoverrides.nether.CustomNether;
import net.minecraft.src.*;
import net.minecraftforge.common.DimensionManager;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Hashtable;

public class BiomeOverride
{
	public static void inject()
	{

		BiomeGenBase.plains.color = 0x00FF00;
		BiomeGenBase hell = CustomNether.getInstance();
		BiomeGenBase.biomeList[8] = hell;

		replaceProvider(-1, CustomHellWorldProvider.class);
		overrideHellBiome(hell);
	}
	public static void overrideHellBiome(BiomeGenBase newBiome) {
		try {
			// Find the hell field dynamically
			Field hellField = null;
			for (Field f : BiomeGenBase.class.getDeclaredFields()) {
				if (java.lang.reflect.Modifier.isStatic(f.getModifiers())
					&& BiomeGenBase.class.isAssignableFrom(f.getType())
					&& f.get(null) instanceof BiomeGenHell) {
					hellField = f;
					break;
				}
			}

			if (hellField == null) {
				System.err.println("Could not find hell field!");
				return;
			}

			// Get Unsafe
			Field f = Unsafe.class.getDeclaredField("theUnsafe");
			f.setAccessible(true);
			Unsafe unsafe = (Unsafe) f.get(null);

			// Get the static field base and offset
			Object staticBase = unsafe.staticFieldBase(hellField);
			long offset = unsafe.staticFieldOffset(hellField);

			// Set the new biome
			unsafe.putObject(staticBase, offset, newBiome);

			System.out.println("Successfully replaced BiomeGenBase.hell via Unsafe!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void replaceProvider(int dimensionId, Class<? extends WorldProvider> newProvider) {
		try {
			// Get the 'providers' field
			Field providersField = DimensionManager.class.getDeclaredField("providers");
			providersField.setAccessible(true);

			// Get the actual Hashtable object
			@SuppressWarnings("unchecked")
			Hashtable<Integer, Class<? extends WorldProvider>> providers =
				(Hashtable<Integer, Class<? extends WorldProvider>>) providersField.get(null);

			// Update it with your new provider
			providers.put(dimensionId, newProvider);

			System.out.println("Dimension " + dimensionId + " now uses " + newProvider.getName());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
