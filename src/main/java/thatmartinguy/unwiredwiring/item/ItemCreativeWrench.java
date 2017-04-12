package thatmartinguy.unwiredwiring.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thatmartinguy.unwiredwiring.UnwiredWiring;
import thatmartinguy.unwiredwiring.block.BlockWire;

public class ItemCreativeWrench extends ItemWrench
{
	public ItemCreativeWrench(String unlocalizedName, String registryName, BlockWire wireType)
	{
		super(unlocalizedName, registryName, wireType);
		this.setCreativeTab(UnwiredWiring.tabUnwiredWiring);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		return EnumRarity.EPIC;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		tooltip.add("An uncraftable wrench with unlimited uses");
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack usedWrench = player.getHeldItem(hand);
		pos = pos.offset(facing);
		
		if(!player.canPlayerEdit(pos, facing, usedWrench))
		{
			return EnumActionResult.FAIL;
		}
		else
		{
			if(worldIn.isAirBlock(pos))
			{
				worldIn.setBlockState(pos, this.wireType.getDefaultState());
			}
		}
		
		return EnumActionResult.SUCCESS;
	}
}
