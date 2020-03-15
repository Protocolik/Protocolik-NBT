package com.github.protocolik.nbt.io

import com.github.protocolik.nbt.NBT
import io.netty.buffer.ByteBuf

class NBTByteBufWriter(
    val byteBuf: ByteBuf
) : NBTWriter {
    override fun writeByte(byte: Byte) {
        byteBuf.writeByte(byte.toInt())
    }

    override fun writeShort(short: Short) {
        byteBuf.writeShort(short.toInt())
    }

    override fun writeInt(int: Int) {
        byteBuf.writeInt(int)
    }

    override fun writeLong(long: Long) {
        byteBuf.writeLong(long)
    }

    override fun writeFloat(float: Float) {
        byteBuf.writeFloat(float)
    }

    override fun writeDouble(double: Double) {
        byteBuf.writeDouble(double)
    }

    companion object {
        @JvmStatic
        fun writeNBT(byteBuf: ByteBuf, nbt: NBT) = byteBuf.apply {
            NBTByteBufWriter(byteBuf).writeNBT(nbt)
        }
    }
}

fun ByteBuf.writeNBT(nbt: NBT) = NBTByteBufWriter.writeNBT(this, nbt)