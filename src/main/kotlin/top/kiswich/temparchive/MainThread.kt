package top.kiswich.temparchive

import kotlinx.coroutines.*
import org.apache.commons.io.monitor.FileAlterationObserver
import top.kiswich.temparchive.config.GroovyConfig
import top.kiswich.temparchive.listener.CopyFileListener
import java.io.FileFilter

object MainThread {

    val fileListeners: MutableList<FileAlterationObserver> = mutableListOf()
    val jobs: MutableList<Job> = mutableListOf()

    @Volatile
    var runState: Boolean = false

    fun start() = runBlocking {
        synchronized(runState) {
            if (runState) return@runBlocking
            val config = GroovyConfig()
            config.load()

            config.configBean?.tempMapArchive?.forEach { (tempPath, archivePath) ->
                val observer = FileAlterationObserver(tempPath, FileFilter {
                    it.extension != "lnk" &&
                            it.parent == tempPath
                })
                fileListeners.add(observer)

                val fileListener = CopyFileListener(tempPath, archivePath)
                observer.addListener(fileListener)
                observer.initialize()
                val job = launch {
                    while (runState) {
                        observer.checkAndNotify()
                        delay(5000)
                    }
                }
                jobs.add(job)
            }
            runState = true
        }
    }

    fun stop() = runBlocking {
        synchronized(runState) {
            if (!runState) return@runBlocking
            runBlocking {
                jobs.forEach { it.cancelAndJoin() }
            }
            fileListeners.forEach { it.destroy() }
            jobs.clear()
            fileListeners.clear()

            runState = false
        }
    }
}