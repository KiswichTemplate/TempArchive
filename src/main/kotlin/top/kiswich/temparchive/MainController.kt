package top.kiswich.temparchive

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.apache.commons.io.monitor.FileAlterationObserver
import top.kiswich.temparchive.config.GroovyConfig
import top.kiswich.temparchive.config.JsonConfig
import top.kiswich.temparchive.listener.CopyFileListener
import java.io.FileFilter
import kotlin.concurrent.thread
import java.io.InputStreamReader
import java.io.BufferedReader


class MainController {
    companion object {
        @JvmStatic
        fun main(args: Array<String>): Unit = runBlocking {
            var command = "start"
            if (args.isNotEmpty()) {
                command = args[0]
            }
//        val config = GroovyConfig()
//        config.load()
            if ("start" == command) {
                // process service start function


//            config.configBean?.tempMapArchive?.forEach { (tempPath, archivePath) ->
//                val observer = FileAlterationObserver(tempPath, FileFilter {
//                    it.extension != "lnk" &&
//                            it.parent == tempPath
//                })
//                val fileListener = CopyFileListener(tempPath, archivePath)
//                observer.addListener(fileListener)
//                observer.initialize()
//                launch {
//                    while (true) {
//                        observer.checkAndNotify()
//                        delay(5000)
//                    }
//                }
//            }
//            thread(isDaemon = true) {
                MainThread.start()
//            }.run()

            } else {
                // process service stop function
//            thread(isDaemon = true) {
                MainThread.stop()
//            }.run()
            }
            BufferedReader(InputStreamReader(System.`in`)).readLine()
            return@runBlocking
//        return
        }
    }


}