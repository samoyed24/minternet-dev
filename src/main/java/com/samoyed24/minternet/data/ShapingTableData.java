package com.samoyed24.minternet.data;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.math.BlockPos;

public record ShapingTableData(BlockPos pos) implements BlockPosPayload {
    public static final PacketCodec<RegistryByteBuf, ShapingTableData> CODEC=
            PacketCodec.tuple(BlockPos.PACKET_CODEC, ShapingTableData::pos, ShapingTableData::new);
}