package top.kiswich.temparchive.listener

import com.google.common.eventbus.Subscribe
import top.kiswich.temparchive.util.ShellLinkUtils

class EventBusChangeRecorder {

    @Subscribe
    fun CreateLink(linkDto: LinkDto) {
        ShellLinkUtils.createLink(linkDto.filePath, linkDto.targetPath)
    }
}