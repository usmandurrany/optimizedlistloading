package com.app.optimizedlistloading.dao;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;

import java.util.Date;

/**
 * Entity mapped to table "MESSAGE".
 */
@Entity
public class Message implements Parcelable {

    @Id(autoincrement = true)
    private Long id;

    @Unique
    @NotNull
    private String entityID;

    @NotNull
    private Long conversationID;

    private String text;

    private java.util.Date createdAt;

    @ToOne(joinProperty = "conversationID")
    private Conversation conversation;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.entityID);
        dest.writeValue(this.conversationID);
        dest.writeString(this.text);
        dest.writeLong(this.createdAt != null ? this.createdAt.getTime() : -1);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntityID() {
        return this.entityID;
    }

    public void setEntityID(String entityID) {
        this.entityID = entityID;
    }

    public Long getConversationID() {
        return this.conversationID;
    }

    public void setConversationID(Long conversationID) {
        this.conversationID = conversationID;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public java.util.Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1303060178)
    public Conversation getConversation() {
        Long __key = this.conversationID;
        if (conversation__resolvedKey == null || !conversation__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ConversationDao targetDao = daoSession.getConversationDao();
            Conversation conversationNew = targetDao.load(__key);
            synchronized (this) {
                conversation = conversationNew;
                conversation__resolvedKey = __key;
            }
        }
        return conversation;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1483054339)
    public void setConversation(@NotNull Conversation conversation) {
        if (conversation == null) {
            throw new DaoException(
                    "To-one property 'conversationID' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.conversation = conversation;
            conversationID = conversation.getId();
            conversation__resolvedKey = conversationID;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 747015224)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMessageDao() : null;
    }

    protected Message(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.entityID = in.readString();
        this.conversationID = (Long) in.readValue(Long.class.getClassLoader());
        this.text = in.readString();
        long tmpCreatedAt = in.readLong();
        this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
    }

    @Generated(hash = 1714238357)
    public Message(Long id, @NotNull String entityID, @NotNull Long conversationID, String text,
            java.util.Date createdAt) {
        this.id = id;
        this.entityID = entityID;
        this.conversationID = conversationID;
        this.text = text;
        this.createdAt = createdAt;
    }

    @Generated(hash = 637306882)
    public Message() {
    }

    public static final Parcelable.Creator<Message> CREATOR = new Parcelable.Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel source) {
            return new Message(source);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 859287859)
    private transient MessageDao myDao;

    @Generated(hash = 2043471887)
    private transient Long conversation__resolvedKey;
}