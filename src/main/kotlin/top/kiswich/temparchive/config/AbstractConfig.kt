package top.kiswich.temparchive.config

import org.apache.commons.io.IOUtils
import java.io.File
import java.io.FileOutputStream

abstract class AbstractConfig : Config {
//    val configPath: String = "${System.getProperty("user.home")}${File.separator}tempArchive"
    val configPath: String = "C:\\ProgramData${File.separator}tempArchive"
    abstract val configFileName: String

    fun generateConfigFile() {
        val file = File(this.configPath, this.configFileName)
        file.parentFile.mkdirs()
        println(file.absolutePath)
        if (!file.exists()) {
            file.createNewFile()
            FileOutputStream(file).use {
                IOUtils.copy(ClassLoader.getSystemResourceAsStream(this.configFileName), it)
            }
        }
    }
}