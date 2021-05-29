package storage;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import models.Article;

@Dao
public interface ArticleDao {

    @Insert
    void addWord(Article article);

    @Update
    int updateWord(Article article);

    @Query("DELETE from article_history_table")
    void deleteAllWords();

    @Query("SELECT * from article_history_table")
    LiveData<List<Article>> getAllWords();

    @Delete
    void deleteItem(Article word);
}
