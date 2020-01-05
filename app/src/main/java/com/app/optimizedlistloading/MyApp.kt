package com.app.optimizedlistloading

import android.app.Application
import com.app.optimizedlistloading.dao.Conversation
import com.app.optimizedlistloading.dao.DaoMaster
import com.app.optimizedlistloading.dao.DaoSession
import com.app.optimizedlistloading.dao.Message
import com.facebook.stetho.Stetho
import io.kimo.lib.faker.Faker
import org.greenrobot.greendao.database.Database
import java.util.*


class MyApp : Application() {
    private var daoSession: DaoSession? = null

    override fun onCreate() {
        super.onCreate()

        // regular SQLite database
        val helper: DaoMaster.DevOpenHelper = DaoMaster.DevOpenHelper(this, "oll-db")
        val db: Database = helper.writableDb
        Faker.with(this)
        Stetho.initializeWithDefaults(this)

        // encrypted SQLCipher database
        // note: you need to add SQLCipher to your dependencies, check the build.gradle file
        // DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db-encrypted");
        // Database db = helper.getEncryptedWritableDb("encryption-key");
        daoSession = DaoMaster(db).newSession()

        if (daoSession?.queryBuilder(Conversation::class.java)?.list().isNullOrEmpty()) {

            val conversations = arrayListOf<Conversation>()
            repeat(100) {
                conversations.add(Conversation().apply {
                    entityID = UUID.randomUUID().toString()
                    title = Faker.Name.fullName()
                    createdAt = Date()
                })
            }
            daoSession?.conversationDao?.insertInTx(conversations)

            daoSession?.queryBuilder(Conversation::class.java)?.list()?.forEach { conversation ->
                val messages = arrayListOf<Message>()
                repeat(500) {
                    messages.add(Message().apply {
                        entityID = UUID.randomUUID().toString()
                        conversationID = conversation.id
                        text = "$it ${conversation.title}"
                        createdAt = Date()
                    })
                }
                daoSession?.messageDao?.insertInTx(messages)
            }
        }

    }

    fun getDaoSession(): DaoSession? {
        return daoSession
    }
}