package com.github.protocolik.nbt

enum class NBTTagType(
    val id: Int,
    val tagName: String,
    val clazz: Class<*>
) {
    END(0, "End", Unit::class.java),
    BYTE(1, "Byte", Byte::class.java),
    SHORT(2, "Short", Short::class.java),
    INT(3, "Int", Int::class.java),
    LONG(4, "Long", Long::class.java),
    FLOAT(5, "Float", Float::class.java),
    DOUBLE(6, "Double", Double::class.java),
    BYTE_ARRAY(7, "Byte_Array", ByteArray::class.java),
    STRING(8, "String", String::class.java),
    LIST(9, "List", List::class.java),
    COMPOUND(10, "Compound", NBT::class.java),
    INT_ARRAY(11, "Int_Array", IntArray::class.java),
    LONG_ARRAY(12, "Long_Array", LongArray::class.java);

    companion object {
        val values = values()
        private val byClass = values.map { it.clazz to it }.toMap()

        operator fun get(id: Number): NBTTagType =
            id.toInt().let { if (it in values.indices) values[it] else error("Unknown NBTTagType with id: $id") }

        operator fun get(clazz: Class<*>): NBTTagType = byClass[clazz]
            ?: error("Unknown NBTTagType with class: $clazz")

        inline fun <reified T> get(): NBTTagType = get(T::class.java)
    }
}