package thatmartinguy.unwiredwiring.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import thatmartinguy.unwiredwiring.block.BlockWire;
import thatmartinguy.unwiredwiring.util.Reference;

public class ItemWrench extends Item
{
	protected BlockWire wireType;
	
	public ItemWrench(String unlocalizedName, String registryName, BlockWire wireType)
	{
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
		this.wireType = wireType;
		this.setNoRepair();
		//(Max amount of redstone possible in the inventory) + 1
		this.setMaxDamage(2242);
	}
	
	@Override
	public Item setUnlocalizedName(String unlocalizedName)
	{
		return super.setUnlocalizedName(Reference.MOD_ID + ":" + unlocalizedName);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		tooltip.add("Right click to fill with redstone");
		tooltip.add("Shift+right click to get back redstone");
		tooltip.add("Uses left: " + stack.getItemDamage());
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack usedWrench = player.getHeldItem(hand);
		pos = pos.offset(facing);
		
		if(!player.canPlayerEdit(pos, facing, usedWrench) || (usedWrench.getItemDamage() <= 0 && !player.isCreative()))
		{
			return EnumActionResult.FAIL;
		}
		else
		{
			if(worldIn.isAirBlock(pos))
			{
				worldIn.setBlockState(pos, wireType.getDefaultState());
				usedWrench.damageItem(-1, player);
			}
		}
		
		return EnumActionResult.SUCCESS;
	}
	
	public static void processLoading(EntityPlayer player, ItemStack wrench)
	{
		if(player.isSneaking())
		{
			unload(player, wrench);
		}
		else
		{
			reload(player, wrench);
		}
	}
	
	private static void reload(EntityPlayer player, ItemStack wrench)
	{
		for(int i = 0; i < player.inventory.getSizeInventory(); i++)
		{
			ItemStack currentItem = player.inventory.getStackInSlot(i);
			if(currentItem.getItem() == Items.REDSTONE)
			{
				wrench.setItemDamage(wrench.getItemDamage() + currentItem.getCount());
				currentItem.setCount(0);
			}
		}
	}
	
	private static void unload(EntityPlayer player, ItemStack wrench)
	{
		ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Items.REDSTONE, wrench.getItemDamage()));
		wrench.setItemDamage(0);
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn)
	{
		stack.setItemDamage(0);
	}
}
