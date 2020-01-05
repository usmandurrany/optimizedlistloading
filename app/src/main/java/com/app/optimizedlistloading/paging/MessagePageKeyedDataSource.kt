package com.app.optimizedlistloading.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.app.optimizedlistloading.MyApp
import com.app.optimizedlistloading.dao.Message
import com.app.optimizedlistloading.dao.MessageDao
import com.app.optimizedlistloading.item.MessageItem

class MessagePageKeyedDataSource(val myApp: MyApp, val conversationID: Long, val startAfter: Long) : PageKeyedDataSource<Long, MessageItem>() {

    companion object {
        private val TAG = MessagePageKeyedDataSource::class.java.simpleName
    }


    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, MessageItem>) {
        // not used
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, MessageItem>) {
        Log.e(TAG, "Load Messages After: ${params.key}")

        val messages = getMessagesAfter(conversationID, params.key)
        val nextLoadKey = messages.lastOrNull()?.id

        callback.onResult(messages.map { MessageItem(it) }, nextLoadKey)

    }

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, MessageItem>) {
        Log.e(TAG, "Load Initial Messages After: $startAfter")

        val messages = getMessagesAfter(conversationID, startAfter)
        val nextLoadKey = messages.lastOrNull()?.id


        callback.onResult(messages.map { MessageItem(it) }, null, nextLoadKey)

    }


    private fun getMessagesAfter(conversationID: Long, startAfter: Long): ArrayList<Message> {
        return myApp.getDaoSession()?.queryBuilder(Message::class.java)
            ?.where(MessageDao.Properties.ConversationID.eq(conversationID), MessageDao.Properties.Id.gt(startAfter))
            ?.orderAsc(MessageDao.Properties.Id)
            ?.limit(50)
            ?.list() as ArrayList<Message>? ?: arrayListOf()
    }
}