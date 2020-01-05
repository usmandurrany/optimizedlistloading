package com.app.optimizedlistloading.paging

import androidx.paging.DataSource
import com.app.optimizedlistloading.item.MessageItem
import com.app.optimizedlistloading.MyApp

class MessageDataSourceFactory(myApp: MyApp, conversationID: Long, startAfter: Long) : DataSource.Factory<Long, MessageItem>() {

    val source = MessagePageKeyedDataSource(myApp, conversationID, startAfter)

    override fun create(): DataSource<Long, MessageItem> {
        return source
    }
}