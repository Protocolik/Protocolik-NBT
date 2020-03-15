package com.github.protocolik.nbt.io

import com.github.protocolik.nbt.NBT
import com.github.protocolik.nbt.NBTReadLimiter
import java.io.DataInputStream
import java.io.InputStream
import java.util.zip.GZIPInputStream

class NBTInputStream(
    inputStream: InputStream,
    compressed: Boolean = true,
    override val nbtReadLimiter: NBTReadLimiter
) : DataInputStream(DataInputStream(if (compressed) GZIPInputStream(inputStream) else inputStream)), NBTReader {
    override fun read(): Int {
        nbtReadLimiter.read(1)
        return super.read()
    }

    companion object {
        @JvmStatic
        @JvmOverloads
        fun readNBT(
            inputStream: InputStream,
            compressed: Boolean = true,
            nbtReadLimiter: NBTReadLimiter = NBTReadLimiter.UNLIMITED
        ): NBT =
            NBTInputStream(inputStream, compressed, nbtReadLimiter).readNBT(compressed, nbtReadLimiter)
    }
}

fun InputStream.readNBT(compressed: Boolean = true, nbtReadLimiter: NBTReadLimiter = NBTReadLimiter.UNLIMITED): NBT =
    NBTInputStream.readNBT(this, compressed, nbtReadLimiter)