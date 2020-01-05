package com.app.optimizedlistloading.dao;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;

import java.util.Date;

/**
 * Entity mapped to table "CONVERSATION".
 */
@Entity
public class Conversation implements Parcelable {

    @Id(autoincrement = true)
    private Long id;

    @Unique
    @NotNull
    private String entityID;

    private String title;

    private java.util.Date createdAt;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.entityID);
        dest.writeString(this.title);
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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public java.util.Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }

    protected Conversation(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.entityID = in.readString();
        this.title = in.readString();
        long tmpCreatedAt = in.readLong();
        this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
    }

    @Generated(hash = 57672035)
    public Conversation(Long id, @NotNull String entityID, String title, java.util.Date createdAt) {
        this.id = id;
        this.entityID = entityID;
        this.title = title;
        this.createdAt = createdAt;
    }

    @Generated(hash = 1893991898)
    public Conversation() {
    }

    public static final Parcelable.Creator<Conversation> CREATOR = new Parcelable.Creator<Conversation>() {
        @Override
        public Conversation createFromParcel(Parcel source) {
            return new Conversation(source);
        }

        @Override
        public Conversation[] newArray(int size) {
            return new Conversation[size];
        }
    };
}