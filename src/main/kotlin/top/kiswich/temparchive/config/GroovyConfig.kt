package top.kiswich.temparchive.config

import groovy.util.ConfigSlurper
import java.io.File

class GroovyConfig : AbstractConfig() {
    override val configFileName: String = "config.groovy"
    private val aConfigBean : ConfigBean  = ConfigBean()

    override fun load(): Config {
        generateConfigFile()
        val parse = ConfigSlurper().parse(File(this.configPath, this.configFileName).toURI().toURL())
        val map = parse["tempMapArchive"]
        if (map is Map<*, *>) {
            map.forEach { (key, value) ->
                if (key is String && value is String) {
                    configBean.tempMapArchive[key] = value
                }
            }
        }
        return this
    }

    override val configBean: ConfigBean
        get() = aConfigBean
}