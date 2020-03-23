package project.novelreader.database

import androidx.room.*

@Entity
data class Novel(
    @PrimaryKey(autoGenerate = true) val novelId: Long = 0,
    @ColumnInfo() val title: String,
    @ColumnInfo() val url: String,
    @ColumnInfo() val imageUrl: String
)

@Entity
data class Chapter(
    @PrimaryKey(autoGenerate = true) val chapterId: Long = 0,
    val novelOwnerId: Long,
    val title: String,
    val url: String,
    val content: String = ""
)

@Entity
data class NovelWithChapter(
    @Embedded val novel: Novel,
    @Relation(
        parentColumn = "novelId",
        entityColumn = "novelOwnerId"
    )
    val chapters: List<Chapter>
)

@Database(
    entities = [Novel::class],
    version = 1
)

@Dao
interface NovelDao {
    @Insert
    fun insertNovel(novel: Novel)

    @Insert
    fun insertNovels(vararg novels: Novel)

    @Insert
    fun insertChapter(chapter: Chapter)

    @Insert
    fun insertChapter(vararg chapters: Chapter)

    @Query("SELECT * FROM novel")
    fun getAllNovels(): Array<Novel>

    @Query("SELECT * FROM novel WHERE novelId = :id")
    fun getNovel(id: Long): Novel

    @Query("SELECT * FROM chapter")
    fun getAllChapters(): Array<Chapter>

    @Query("SELECT * FROM novel WHERE novelId = :id")
    fun getChapter(id: Long): Chapter

    @Transaction
    @Query("SELECT * FROM novel")
    fun getNovelWithChapter(): List<NovelWithChapter>
}

abstract class AppDB : RoomDatabase() {
    abstract fun novelDao(): NovelDao
}


