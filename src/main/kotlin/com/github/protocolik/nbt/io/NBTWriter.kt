package com.github.protocolik.nbt.io

import com.github.protocolik.nbt.NBT
import com.github.protocolik.nbt.NBTTagType

interface NBTWriter {
    fun writeNBT(value: NBT?) {
        if (value != null) {
            writeByte(10)
            writeString(value.name)
            value.forEach { (name, value) ->
                val nbtTagType = NBTTagType[value::class.java]
                if (nbtTagType != NBTTagType.COMPOUND) {
                    writeByte(nbtTagType.id.toByte())
                    writeString(name)
                }
                writeValue(value)
            }
            writeByte(0)
        } else {
            writeByte(0)
        }
    }

    private fun writeValue(value: Any) {
        when (value) {
            is Byte -> writeByte(value)
            is Short -> writeShort(value)
            is Int -> writeInt(value)
            is Long -> writeLong(value)
            is Float -> writeFloat(value)
            is Double -> writeDouble(value)
            is ByteArray -> writeByteArray(value)
            is String -> writeString(value)
            is List<*> -> {
                val nbtTagType = value.firstOrNull()?.let { NBTTagType[it::class.java] } ?: NBTTagType.END
                writeByte(nbtTagType.id.toByte())
                writeInt(value.size)
                value.forEach {
                    writeValue(it!!)
                }
            }
            is NBT -> writeNBT(value)
            is IntArray -> {
                writeInt(value.size)
                value.forEach { writeInt(it) }
            }
            is LongArray -> {
                writeInt(value.size)
                value.forEach { writeLong(it) }
            }
            else -> error("Unknown tag: [${value::class.simpleName}] $value")
        }
    }

    fun writeByte(byte: Byte)
    fun writeShort(short: Short)
    fun writeInt(int: Int)
    fun writeLong(long: Long)
    fun writeFloat(float: Float)
    fun writeDouble(double: Double)
    fun writeByteArray(byteArray: ByteArray) {
        writeInt(byteArray.size)
        byteArray.forEach {
            writeByte(it)
        }
    }

    fun writeString(string: String) {
        val bytes = string.toByteArray(Charsets.UTF_8)
        writeByteArray(bytes)
    }
}