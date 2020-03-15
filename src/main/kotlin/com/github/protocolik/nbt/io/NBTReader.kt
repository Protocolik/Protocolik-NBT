package com.github.protocolik.nbt.io

import com.github.protocolik.nbt.NBT
import com.github.protocolik.nbt.NBTReadLimiter
import com.github.protocolik.nbt.NBTTagType

interface NBTReader {
    val nbtReadLimiter: NBTReadLimiter

    fun readNBT(readId: Boolean = true): NBT {
        if (readId) {
            check(readByte() == 10.toByte()) {
                "Expected a compound, didn't get one :C"
            }
        }
        val nbt = NBT(readString())
        while (true) {
            val nbtTagType = NBTTagType[readByte().toInt()]
            val tagName = if (nbtTagType != NBTTagType.END) readString() else ""
            if (nbtTagType == NBTTagType.END) {
                break
            }
            nbt[tagName] = readTag(nbtTagType)
        }
        return nbt
    }

    fun readTag(nbtTagType: NBTTagType): Any =
        when (nbtTagType) {
            NBTTagType.BYTE -> readByte()
            NBTTagType.SHORT -> readShort()
            NBTTagType.INT -> readInt()
            NBTTagType.LONG -> readLong()
            NBTTagType.FLOAT -> readFloat()
            NBTTagType.DOUBLE -> readDouble()
            NBTTagType.BYTE_ARRAY -> readByteArray()
            NBTTagType.STRING -> readString()
            NBTTagType.LIST -> {
                readByte().toInt()
                val size = readInt()
                List(size) {
                    readTag(nbtTagType)
                }
            }
            NBTTagType.COMPOUND -> readNBT(false)
            NBTTagType.INT_ARRAY -> IntArray(readInt()) { readInt() }
            NBTTagType.LONG_ARRAY -> LongArray(readInt()) { readLong() }
            NBTTagType.END -> {
                /*nothing*/
            }
        }

    fun readByte(): Byte
    fun readShort(): Short
    fun readInt(): Int
    fun readLong(): Long
    fun readFloat(): Float
    fun readDouble(): Double
    fun readByteArray(): ByteArray {
        val size = readInt()
        return ByteArray(size) {
            readByte()
        }
    }

    fun readString(): String {
        val bytes = readByteArray()
        return bytes.toString(Charsets.UTF_8)
    }
}