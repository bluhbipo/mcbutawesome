package com.example.item.handler;

import com.example.ItemOrBlock;
import com.example.item.ModItemDefaults;
import com.example.item.ModifiedItem;
import com.example.item.creation.ModItemBuilder;
import net.minecraft.src.*;
import com.example.OwnerCode;

import java.util.HashMap;
import java.util.Map;

public class ModItem extends Item implements ItemOrBlock, ModifiedItem
{
	public static Map<Integer, ModifiedItem> getItemByID = new HashMap<>();
	public final ModItemBuilder props;
	public ModItem(ModItemBuilder struct)
	{
		super(ModItemDefaults.id-256);
		props = struct;
		ModItemDefaults.init(this, struct);
		this.setCreativeTab(CreativeTabs.tabMisc);
		getItemByID.put(ModItemDefaults.id, this);
		ModItemDefaults.id++;
	}

	@Override
	public String getTextureFile(){
		return "/mods/themod/textures/items.png";
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {

		if (player.canEat(false)) {
			player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
		}

		return itemStack;
	}


	@Override
	public String getItemDisplayName(ItemStack itemStack)
	{
		return props.name;
	}

	public static ModifiedItem getModItemFromItemStack(ItemStack i)
	{
		return ModItem.getItemByID.get(i.itemID);
	}

}
