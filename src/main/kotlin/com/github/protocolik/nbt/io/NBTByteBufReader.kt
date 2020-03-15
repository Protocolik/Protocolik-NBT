package com.github.protocolik.nbt.io

import com.github.protocolik.nbt.NBT
import com.github.protocolik.nbt.NBTReadLimiter
import io.netty.buffer.ByteBuf

class NBTByteBufReader(
    val byteBuf: ByteBuf,
    override val nbtReadLimiter: NBTReadLimiter
) : NBTReader {
    override fun readByte(): Byte {
        nbtReadLimiter.read(1)
        return byteBuf.readByte()
    }

    override fun readShort(): Short {
        nbtReadLimiter.read(2)
        return byteBuf.readShort()
    }

    override fun readInt(): Int {
        nbtReadLimiter.read(4)
        return byteBuf.readInt()
    }

    override fun readLong(): Long {
        nbtReadLimiter.read(8)
        return byteBuf.readLong()
    }

    override fun readFloat(): Float {
        nbtReadLimiter.read(4)
        return byteBuf.readFloat()
    }

    override fun readDouble(): Double {
        nbtReadLimiter.read(8)
        return byteBuf.readDouble()
    }

    companion object {
        @JvmStatic
        @JvmOverloads
        fun readNBT(byteBuf: ByteBuf, nbtReadLimiter: NBTReadLimiter = NBTReadLimiter.UNLIMITED): NBT =
            NBTByteBufReader(byteBuf, nbtReadLimiter).readNBT()
    }
}

fun ByteBuf.readNBT(nbtReadLimiter: NBTReadLimiter = NBTReadLimiter.UNLIMITED): NBT =
    NBTByteBufReader.readNBT(this, nbtReadLimiter)