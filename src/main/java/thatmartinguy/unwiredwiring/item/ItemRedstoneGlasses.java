package thatmartinguy.unwiredwiring.item;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import thatmartinguy.unwiredwiring.UnwiredWiring;
import thatmartinguy.unwiredwiring.util.Reference;

public class ItemRedstoneGlasses extends ItemArmor
{
	public ItemRedstoneGlasses(String unlocalizedName, String registryName)
	{
		super(ModItems.clothing, 0, EntityEquipmentSlot.HEAD);
		this.setUnlocalizedName(Reference.MOD_ID + ":" + unlocalizedName);
		this.setRegistryName(registryName);
		this.setCreativeTab(UnwiredWiring.tabUnwiredWiring);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		if(UnwiredWiring.isBaublesLoaded)
		{
			tooltip.add(ChatFormatting.BOLD + "Baubles is loaded");
			tooltip.add(ChatFormatting.BOLD + "Put in crafting table to make bauble slot compatible");
		}
		tooltip.add("Reveals wires while held or worn");
	}
}
