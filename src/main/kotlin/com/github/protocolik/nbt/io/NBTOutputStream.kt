package com.github.protocolik.nbt.io

import com.github.protocolik.nbt.NBT
import java.io.DataOutputStream
import java.io.OutputStream
import java.util.zip.GZIPOutputStream

class NBTOutputStream(
    outputStream: OutputStream,
    compressed: Boolean = true
) : DataOutputStream(if (compressed) GZIPOutputStream(outputStream) else outputStream), NBTWriter {
    override fun writeByte(byte: Byte) = writeByte(byte.toInt())

    override fun writeShort(short: Short) = writeShort(short.toInt())

    companion object {
        @JvmStatic
        @JvmOverloads
        fun writeNBT(
            outputStream: OutputStream,
            nbt: NBT,
            compressed: Boolean = true
        ) = NBTOutputStream(outputStream, compressed).writeNBT(nbt)
    }
}

fun OutputStream.writeNBT(
    nbt: NBT,
    compressed: Boolean = true
) = NBTOutputStream.writeNBT(this, nbt, compressed)