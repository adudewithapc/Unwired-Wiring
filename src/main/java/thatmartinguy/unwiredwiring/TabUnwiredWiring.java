package thatmartinguy.unwiredwiring;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import thatmartinguy.unwiredwiring.item.ModItems;

public class TabUnwiredWiring extends CreativeTabs
{
	public TabUnwiredWiring(String label)
	{
		super(label);
	}

	@Override
	public ItemStack getTabIconItem()
	{
		return new ItemStack(ModItems.wrenchRed);
	}
}
