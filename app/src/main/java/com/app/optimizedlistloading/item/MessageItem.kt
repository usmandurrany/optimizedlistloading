package com.app.optimizedlistloading.item

import com.app.optimizedlistloading.R
import com.app.optimizedlistloading.dao.Message
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.view_message_item.view.*

class MessageItem(val message: Message) : Item<GroupieViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.view_message_item
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.txtMessageText.text = message.text
    }

}