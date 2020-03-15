package com.github.protocolik.nbt.io

import com.github.protocolik.nbt.NBT
import kotlinx.io.core.Output

class NBTOutputWriter(
    val output: Output
) : NBTWriter {
    override fun writeByte(byte: Byte) = output.writeByte(byte)
    override fun writeShort(short: Short) = output.writeShort(short)
    override fun writeInt(int: Int) = output.writeInt(int)
    override fun writeLong(long: Long) = output.writeLong(long)
    override fun writeFloat(float: Float) = output.writeFloat(float)
    override fun writeDouble(double: Double) = output.writeDouble(double)
}

fun Output.writeNBT(nbt: NBT) = NBTOutputWriter(this).writeNBT(nbt)