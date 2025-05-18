package com.samoyed24.minternet.block.custom;

import com.mojang.serialization.MapCodec;
import com.samoyed24.minternet.block.entity.WeldingTableBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public abstract class AbstractMachineBlock<MachineBlockEntity extends BlockEntity> extends BlockWithEntity {

    public static final DirectionProperty FACING;
//    public static final MapCodec<AbstractMachineBlock> CODEC = Block.createCodec(AbstractMachineBlock::new);

    public AbstractMachineBlock(AbstractBlock.Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

//    @Override
//    protected MapCodec<? extends BlockWithEntity> getCodec() {
//        return CODEC;
//    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            // 这里会调用 BlockWithEntity 的 createScreenHandlerFactory 方法，会将返回的方块实体强转为
            // 一个 namedScreenHandlerFactory。如果你的方块没有继承 BlockWithEntity，那就需要单独实现 createScreenHandlerFactory。
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

            if (screenHandlerFactory != null) {
                // 这个调用会让服务器请求客户端开启合适的 Screenhandler
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    // 这个方法能让方块破坏时物品全部掉落
//    @Override
//    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
//        if (state.getBlock() != newState.getBlock()) {
//            BlockEntity blockEntity = world.getBlockEntity(pos);
//            if (blockEntity instanceof WeldingTableBlockEntity) {
//                ItemScatterer.spawn(world, pos, (WeldingTableBlockEntity)blockEntity);
//                // 更新比较器
//                world.updateComparators(pos,this);
//            }
//            super.onStateReplaced(state, world, pos, newState, moved);
//        }
//    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }

    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
    }

    static {
        FACING = Properties.HORIZONTAL_FACING;
    }
}
