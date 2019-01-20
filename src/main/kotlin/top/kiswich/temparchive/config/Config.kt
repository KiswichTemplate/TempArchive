package top.kiswich.temparchive.config

import java.io.File

interface Config {
    fun load(): Config
    val configBean: ConfigBean
}