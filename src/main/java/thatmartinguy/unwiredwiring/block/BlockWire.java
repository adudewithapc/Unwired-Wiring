package thatmartinguy.unwiredwiring.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thatmartinguy.unwiredwiring.tileentity.TileEntityWire;

public class BlockWire extends BlockHideable
{
	private boolean isCreativeWire;
	
	public BlockWire(String unlocalizedName, String registryName, Material material)
	{
		this(unlocalizedName, registryName, material, false);
	}
	
	public BlockWire(String unlocalizedName, String registryName, Material material, boolean isCreativeWire)
	{
		super(unlocalizedName, registryName, material);
		this.isCreativeWire = isCreativeWire;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		return new TileEntityWire();
	}
	
	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random)
	{
		return isCreativeWire ? 0 : 1;
	}
	
	@Override
	public boolean canProvidePower(IBlockState state)
	{
		return true;
	}
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	{	
		if(worldIn.getTileEntity(pos) instanceof TileEntityWire)
		{
			TileEntityWire tileEntityWire = (TileEntityWire) worldIn.getTileEntity(pos);
			if(worldIn.isBlockIndirectlyGettingPowered(pos) != tileEntityWire.getPowerLevel())
			{
				tileEntityWire.setPowerLevel(worldIn.isBlockIndirectlyGettingPowered(pos));
			}
			for(EnumFacing facing : EnumFacing.values())
			{
				if(worldIn.getTileEntity(pos.offset(facing)) instanceof TileEntityWire && worldIn.getBlockState(pos).getBlock() == this)
				{
					TileEntityWire wireOffset = (TileEntityWire) worldIn.getTileEntity(pos);
					if(wireOffset.getPowerLevel() != tileEntityWire.getPowerLevel())
						worldIn.notifyBlockUpdate(pos, state, state, 1);
				}
				else if(worldIn.getBlockState(pos.offset(facing)).getBlock() == Blocks.REDSTONE_WIRE)
				{
					IBlockState redstoneWire = worldIn.getBlockState(pos.offset(facing));
					if(redstoneWire.getValue(BlockRedstoneWire.POWER) != tileEntityWire.getPowerLevel())
					{
						IBlockState powerLevel = redstoneWire.withProperty(BlockRedstoneWire.POWER, tileEntityWire.getPowerLevel());
						worldIn.notifyBlockUpdate(pos.offset(facing), state, state, 1);
					}
				}
				else
				{
					worldIn.notifyBlockUpdate(pos.offset(facing), state, state, 1);
				}
			}
		}
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		worldIn.notifyNeighborsOfStateChange(pos, this, false);
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		super.breakBlock(worldIn, pos, state);
		worldIn.notifyNeighborsOfStateChange(pos, this, false);
	}
	
	@Override
	public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	{
		if(blockAccess.getTileEntity(pos) instanceof TileEntityWire)
		{
			TileEntityWire tileEntityWire = (TileEntityWire) blockAccess.getTileEntity(pos);
			return tileEntityWire.getPowerLevel();
		}
		return 0;
	}
	
	@Override
	public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	{
		return blockState.getWeakPower(blockAccess, pos, side);
	}
	
	//Currently only used for debugging. Prints the wires power level
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(worldIn.getTileEntity(pos) instanceof TileEntityWire)
		{
			System.out.println(((TileEntityWire) worldIn.getTileEntity(pos)).getPowerLevel());
		}
		return true;
	}
}
