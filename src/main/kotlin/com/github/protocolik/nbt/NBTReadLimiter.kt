package com.github.protocolik.nbt

open class NBTReadLimiter(
    val limit: Long
) {
    private var read: Long = 0

    open fun read(length: Int) {
        read += length
        if (read > limit) {
            throw IllegalStateException("Read more than $limit bytes from NBT tag")
        }
    }

    companion object {
        @JvmField
        val UNLIMITED = object : NBTReadLimiter(0) {
            override fun read(length: Int) {
                /*nothing*/
            }
        }
    }
}