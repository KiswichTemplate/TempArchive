package top.kiswich.temparchive

import com.google.common.eventbus.EventBus
import top.kiswich.temparchive.listener.EventBusChangeRecorder
import java.util.*

object EventBusController {
    val eventBus: EventBus = EventBus()

    init {
        eventBus.register(EventBusChangeRecorder())
    }

    public fun register(objects: Any){
        eventBus.register(objects)
    }

    public fun post(objects: Any){
        eventBus.post(objects)
    }
}