package com.github.protocolik.nbt

class NBT(
    val name: String = ""
) : MutableMap<String, Any> by mutableMapOf() {
    override fun toString(): String {
        return entries.joinToString("\n", "{\n", "\n}") { (name, value) ->
            val valueText = if (value is NBT) value.toString() else "$value"
            "[${value::class.simpleName}] $name = $valueText".prependIndent("  ")
        }
    }
}