package top.kiswich.temparchive.util

import org.junit.Test

import org.junit.Assert.*
import java.io.File
import java.nio.file.FileSystem
import java.nio.file.FileSystems
import java.nio.file.Files

class ShellLinkUtilsTest {

    @Test
    fun createLink() {
        Files.createLink(FileSystems.getDefault().getPath("F:\\link.mp4.lnk"),
            FileSystems.getDefault().getPath("F:\\BaiduNetdiskDownload\\psycho pass\\[Kamigami] Psycho-Pass The Movie [BD 1080p x264 Hi10 AAC Sub(Chs,Cht,Jap)].mkv"))
    }

    @Test
    fun createLink1() {
        ShellLinkUtils.createLink( "F:\\temp\\【AGE动漫】[JOJO&UHA-WING][JoJo.no.Kimyou.na.Bouken-Stardust.Crusaders_03][GB][1280x720][BDRIP] - 副本 - 副本 (3) - 副本 - 副本 - 副本 - 副本.mp4",
            "F:\\link\\")
    }

    @Test
    fun pathTest() {
        println(File("F:\\BaiduNetdiskDownload\\psycho pass\\[Kamigami] Psycho-Pass The Movie [BD 1080p x264 Hi10 AAC Sub(Chs,Cht,Jap)].mkv").canonicalPath)
    }
}