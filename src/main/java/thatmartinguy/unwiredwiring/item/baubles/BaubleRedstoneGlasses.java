package thatmartinguy.unwiredwiring.item.baubles;

import java.util.List;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import thatmartinguy.unwiredwiring.UnwiredWiring;
import thatmartinguy.unwiredwiring.item.ModItems;
import thatmartinguy.unwiredwiring.util.Reference;

public class BaubleRedstoneGlasses extends ItemArmor implements IBauble
{
	public BaubleRedstoneGlasses(String unlocalizedName, String registryName)
	{
		super(ModItems.clothing, 0, EntityEquipmentSlot.HEAD);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + unlocalizedName);
		this.setRegistryName(registryName);
		this.setCreativeTab(UnwiredWiring.tabUnwiredWiring);
	}
	
	@Override
	public BaubleType getBaubleType(ItemStack itemstack)
	{
		return BaubleType.HEAD;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		tooltip.add("Reveals wire when worn or held");
		tooltip.add("Can also be worn in the vanilla head slot");
	}
}
