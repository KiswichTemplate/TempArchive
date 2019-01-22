package top.kiswich.temparchive.util

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.io.*

object CoroutineUtils {
    val EOF = -1;

    suspend fun slowCopy(inputStream: InputStream, outputStream: OutputStream, copyDelay: Long = 1) {

        val buffer = ByteArray(1024)

        var read: Int
        var totalRead: Long = 0

        read = inputStream.read(buffer, 0, 1024)

        while (EOF != read) {
            outputStream.write(buffer, 0, read)
            totalRead += read.toLong()
            if (copyDelay > 0) delay(copyDelay)
            read = inputStream.read(buffer, 0, 1024)
        }
        return

    }

    suspend fun slowCopy(source: String, target: String) {
        runBlocking {
            println("start slow copy $source")
            File(target).parentFile.mkdirs()
//            File(source).is
            FileInputStream(source).use { inputStream ->
                FileOutputStream(target).use { outputStream ->
                    slowCopy(inputStream, outputStream)
                }
            }
        }
    }

    suspend fun waitFileSmooth(file: File) {
        var length = file.length()
        var count = 0

        while (count <= 3) {
            val newLength = file.length()
            if (newLength == length){
                count++
            }else{
                count = 0
                length = newLength
            }
            delay(4000)
        }
    }

    /**
     * 等待文件写锁释放
     */
    suspend fun waitRelease(file: File) {
        if (!file.exists()) return
        while (true) {
            try {
                waitReleaseRecursive(file)
                return
            } catch (e: FileNotFoundException) {
                println("file is writing... 4s after retry...")
                delay(4000)
            }
        }
    }

    private suspend fun waitReleaseRecursive(file: File) {
        RandomAccessFile(file, "rw").use {
            it.channel.use { channel ->
                var tryLock = channel.tryLock()
                while (tryLock == null) {
                    println("waiting ${file.name} lock release...")
                    delay(4000)
                    tryLock = channel.tryLock()
                }
                tryLock.release()
            }
        }
    }

}