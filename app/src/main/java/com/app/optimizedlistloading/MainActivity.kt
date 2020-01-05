package com.app.optimizedlistloading

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.optimizedlistloading.dao.Conversation
import com.app.optimizedlistloading.dao.Message
import com.app.optimizedlistloading.dao.MessageDao
import com.app.optimizedlistloading.item.ConversationItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import com.xwray.groupie.OnItemClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val conversationAdapter = GroupAdapter<GroupieViewHolder>()
        conversationAdapter.addAll(getAllConversations().map { ConversationItem(it) })

        conversations.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = conversationAdapter
        }

        conversationAdapter.setOnItemClickListener(this)

    }

    private fun getAllConversations(): List<Conversation> {
        return myApp.getDaoSession()?.queryBuilder(Conversation::class.java)?.list() ?: listOf()
    }

    override fun onItemClick(item: Item<*>, view: View) {
        if (item is ConversationItem) {
            startActivity(
                Intent(this, MessageActivity::class.java)
                    .putExtra("conversation", item.conversation)
                    .putParcelableArrayListExtra("recentMessages",getRecentMessages(item.conversation))
            )
        }
    }


    private fun getRecentMessages(conversation: Conversation): ArrayList<Message> {
        return myApp.getDaoSession()?.queryBuilder(Message::class.java)
            ?.where(MessageDao.Properties.ConversationID.eq(conversation.id))
            ?.limit(50)
            ?.list() as ArrayList<Message>? ?: arrayListOf()
    }
}
