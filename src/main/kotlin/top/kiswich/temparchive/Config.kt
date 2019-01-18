package top.kiswich.temparchive

import com.google.gson.Gson
import org.apache.commons.io.IOUtils
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import kotlin.reflect.KClass


class Config {
    val configPath: String = "${System.getProperty("user.home")}${File.separator}tempArchive"
    val configFileName: String = "config.json"
    var tempDirs: Array<String> = arrayOf()
    val gson: Gson = Gson()
    var configBean: ConfigBean? = null;

    public fun load() {

        val dir = File(configPath)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        val file = File(configPath, configFileName)
        if (!file.exists()) {
            file.createNewFile()
            FileOutputStream(file).use {
                IOUtils.copy(ClassLoader.getSystemResourceAsStream("config.json"), it)
            }
        }

        this.configBean = gson.fromJson<ConfigBean>(IOUtils.toString(FileInputStream(file)), ConfigBean::class.java)

    }

    public fun reload() {

    }


    data class ConfigBean(
        val tempPaths: Array<String>
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as ConfigBean

            if (!tempPaths.contentEquals(other.tempPaths)) return false

            return true
        }

        override fun hashCode(): Int {
            return tempPaths.contentHashCode()
        }
    }
}
