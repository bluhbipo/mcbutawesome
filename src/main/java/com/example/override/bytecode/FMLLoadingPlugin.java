package com.example.override.bytecode;

import com.example.mod_ExampleMod;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

public class FMLLoadingPlugin implements IFMLLoadingPlugin
{

	@Override
	public String[] getLibraryRequestClass() {

		return null;
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[]{ClassTransformer.class.getName()};
	}

	@Override
	public String getModContainerClass() {
		return mod_ExampleMod.class.getName();
	}

	@Override
	public String getSetupClass() {

		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {

	}

}