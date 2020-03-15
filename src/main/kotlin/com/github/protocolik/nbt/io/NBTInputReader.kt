package com.github.protocolik.nbt.io

import com.github.protocolik.nbt.NBT
import com.github.protocolik.nbt.NBTReadLimiter
import kotlinx.io.core.Input

class NBTInputReader(
    val input: Input,
    override val nbtReadLimiter: NBTReadLimiter
) : NBTReader {
    override fun readByte(): Byte {
        nbtReadLimiter.read(1)
        return input.readByte()
    }

    override fun readShort(): Short {
        nbtReadLimiter.read(2)
        return input.readShort()
    }

    override fun readInt(): Int {
        nbtReadLimiter.read(4)
        return input.readInt()
    }

    override fun readLong(): Long {
        nbtReadLimiter.read(8)
        return input.readLong()
    }

    override fun readFloat(): Float {
        nbtReadLimiter.read(4)
        return input.readFloat()
    }

    override fun readDouble(): Double {
        nbtReadLimiter.read(8)
        return input.readDouble()
    }
}

fun Input.readNBT(nbtReadLimiter: NBTReadLimiter = NBTReadLimiter.UNLIMITED): NBT =
    NBTInputReader(this, nbtReadLimiter).readNBT()