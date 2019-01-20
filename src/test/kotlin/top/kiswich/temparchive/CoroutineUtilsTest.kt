package top.kiswich.temparchive

import kotlinx.coroutines.runBlocking
import org.junit.Test

import top.kiswich.temparchive.util.CoroutineUtils
import java.io.FileInputStream
import java.io.FileOutputStream

class CoroutineUtilsTest {

    @Test
    fun slowCopy() {
        runBlocking {
            CoroutineUtils.slowCopy(
                FileInputStream("F:\\BaiduNetdiskDownload\\20140136_JOJO&UHA-WING_720P_CHS\\【AGE动漫】[JOJO&UHA-WING][JoJo.no.Kimyou.na.Bouken-Stardust.Crusaders_01][GB][1280x720][BDRIP].mp4"),
                FileOutputStream("C:\\a.mp4")
            )
        }
    }

    @Test
    fun slowCopySpeed_0() {
        runBlocking {
            CoroutineUtils.slowCopy(
                FileInputStream("F:\\BaiduNetdiskDownload\\【幻之字幕组】Fate HF剧场版 第2章 [1080P][BDRip]][Movie][简体].mp4"),
                FileOutputStream("C:\\a.mp4"),
                0
            )
        }
    }
}