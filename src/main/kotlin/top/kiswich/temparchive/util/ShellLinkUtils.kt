package top.kiswich.temparchive.util

import mslinks.ShellLink
import java.io.File
import java.io.FileNotFoundException
import javax.swing.ImageIcon
import javax.swing.filechooser.FileSystemView
import javax.swing.Icon


object ShellLinkUtils {
    fun createLink(file: File, target: File) {

    }

    fun createLink(filePath: String, targetPath: String) {
        val parentFile = File(targetPath).parentFile
        if(!parentFile.exists()){
            parentFile.mkdirs()
        }
        val link = ShellLink.createLink(filePath)
        link.workingDir = parentFile.absolutePath
        link.iconLocation = "%SystemRoot%\\system32\\SHELL32.dll"
        link.saveTo(targetPath)
//        val linkFile = File(linkPath)
//        val smallIcon = getSmallIcon(File(filePath))
//
//        File(filePath).canonicalPath
//        if(smallIcon is ImageIcon){
//            smallIcon.image.
//        }
    }


    fun getBigIcon(f: File?): ImageIcon? {
        if (f != null && f.exists()) {
            try {
                val sf = sun.awt.shell.ShellFolder.getShellFolder(f)
                return ImageIcon(sf.getIcon(true))
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
        return null
    }

    private fun getSmallIcon(f: File?): Icon? {
        if (f != null && f.exists()) {
            val fsv = FileSystemView.getFileSystemView()
            return fsv.getSystemIcon(f)
        }
        return null
    }


}