package top.kiswich.temparchive.listener

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import mslinks.ShellLink
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor
import sun.reflect.misc.ConstructorUtil
import top.kiswich.temparchive.EventBusController
import top.kiswich.temparchive.extend.getTodayTime
import top.kiswich.temparchive.util.CoroutineUtils
import top.kiswich.temparchive.util.ShellLinkUtils
import java.io.File
import java.util.*

/**
 * 监听文件创建，
 */
class CopyFileListener(val basePath: String, val archivePath: String) : FileAlterationListenerAdaptor() {

    override fun onFileCreate(file: File) {
        val todayTime = Date().getTodayTime()
        super.onFileCreate(file)
        println("start copy ${file.name}")
        val targetPath =
            "$archivePath${File.separator}${todayTime.year}${File.separator}${todayTime.month}${File.separator}${todayTime.day}${File.separator}${file.name}"
        val filePath =
            "$archivePath${File.separator}${todayTime.year}${File.separator}${todayTime.month}${File.separator}${todayTime.day}"
        val launch = GlobalScope.launch {
            //TODO 大文件写入时
//            delay(10000)
            //等待文件写锁释放
            CoroutineUtils.waitRelease(file)

            CoroutineUtils.slowCopy(file.absolutePath, targetPath)

            //eventbus防止协程冲突
//            ShellLinkUtils.createLink(targetPath, File(basePath, "${file.name}.lnk").absolutePath)
//            EventBusController.post(LinkDto(targetPath, File(basePath, "${file.name}.lnk").absolutePath))
//            println("create link ${File(basePath, "${file.name}.lnk").absolutePath}")

//            ShellLinkUtils.createLink(filePath, File(archivePath, "today.lnk").absolutePath)
            EventBusController.post(LinkDto(filePath, File(archivePath, "today.lnk").absolutePath))
            println("create link ${File(archivePath, "today.lnk").absolutePath}")
        }

    }

    override fun onDirectoryCreate(directory: File?) {
        super.onDirectoryCreate(directory)
    }


}