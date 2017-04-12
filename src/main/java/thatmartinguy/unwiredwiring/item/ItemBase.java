package thatmartinguy.unwiredwiring.item;

import net.minecraft.item.Item;
import thatmartinguy.unwiredwiring.UnwiredWiring;
import thatmartinguy.unwiredwiring.util.Reference;

public class ItemBase extends Item
{
	public ItemBase(String unlocalizedName, String registryName)
	{
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
		this.setCreativeTab(UnwiredWiring.tabUnwiredWiring);
	}
	
	@Override
	public Item setUnlocalizedName(String unlocalizedName)
	{
		return super.setUnlocalizedName(Reference.MOD_ID + ":" + unlocalizedName);
	}
}
