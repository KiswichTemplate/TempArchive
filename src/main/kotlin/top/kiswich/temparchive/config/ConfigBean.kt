package top.kiswich.temparchive.config

import com.google.common.collect.HashMultimap
import com.google.common.collect.ImmutableMap

data class ConfigBean(
    val tempMapArchive: MutableMap<String, String> = LinkedHashMap()
)