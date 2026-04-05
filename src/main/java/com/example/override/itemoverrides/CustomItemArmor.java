package com.example.override.itemoverrides;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.*;

import java.util.List;

public class CustomItemArmor extends ItemArmor
{
	ItemArmor source;
	public CustomItemArmor(ItemArmor source, EnumArmorMaterial material)
	{
		super(source.shiftedIndex-256, material, source.renderIndex, source.armorType);
		this.source = source;
		this.iconIndex = source.getIconIndex(new ItemStack(this));
	}
	@Override
	public void addInformation(ItemStack itemStack, List list)
	{
		ToolTipRules.getTooltip(source, itemStack, list);
	}
	@SideOnly(Side.CLIENT)
	public String getItemDisplayName(ItemStack itemStack) {
		return (StringTranslate.getInstance().translateNamedKey(this.getLocalItemName(itemStack))).trim();
	}
	@SideOnly(Side.CLIENT)
	public String getLocalItemName(ItemStack itemStack) {
		String itemName = this.getItemNameIS(itemStack);
		return itemName == null ? "" : StatCollector.translateToLocal(itemName);
	}
	public String getItemNameIS(ItemStack itemStack) {
		return source.getItemName();
	}
}
