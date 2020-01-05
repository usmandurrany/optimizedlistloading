package com.app.optimizedlistloading

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.optimizedlistloading.dao.Conversation
import com.app.optimizedlistloading.dao.Message
import com.app.optimizedlistloading.item.MessageItem
import com.app.optimizedlistloading.paging.MessageDataSourceFactory
import com.app.optimizedlistloading.paging.MessagePagedListGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_message.*

class MessageActivity : BaseActivity() {
    companion object {
        val TAG: String = MessageActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        val conversation = intent?.getParcelableExtra<Conversation>("conversation")
        intent?.getParcelableArrayListExtra<Message>("recentMessages")?.let { recentMessages ->
            val lastMessage = recentMessages.lastOrNull()

            Log.e(TAG, "Recent Messages Size: ${recentMessages.size}")
            Log.e(TAG, "Last Messages Text: ${lastMessage?.text}")
            Log.e(TAG, "Last Messages Time: ${lastMessage?.createdAt?.time}")

            val messagePagedListGroup = MessagePagedListGroup()

            val messageAdapter = GroupAdapter<GroupieViewHolder>()
            messageAdapter.addAll(recentMessages.map { MessageItem(it) })
            messageAdapter.add(messagePagedListGroup)

            messages.apply {
                layoutManager = LinearLayoutManager(this@MessageActivity)
                adapter = messageAdapter
            }

            val factory = MessageDataSourceFactory(myApp, conversation!!.id, lastMessage?.id ?: 0)
            val config = PagedList.Config.Builder()
                .setInitialLoadSizeHint(50)
                .setPageSize(50)
                .build()

            LivePagedListBuilder(factory, config).build().observe(this, Observer {
                messagePagedListGroup.submitList(it)
            })


        } ?: finish()


    }

}
