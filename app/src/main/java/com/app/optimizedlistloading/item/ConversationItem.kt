package com.app.optimizedlistloading.item

import com.app.optimizedlistloading.R
import com.app.optimizedlistloading.dao.Conversation
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.view_conversation_item.view.*

class ConversationItem(val conversation: Conversation) : Item<GroupieViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.view_conversation_item
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.txtConversationTitle.text = conversation.title
    }

}