package storage;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import models.Article;


@Database(entities = {Article.class}, version = 1, exportSchema = false)
public abstract class ArticleDataBase extends RoomDatabase {

    private static final String DATABASE_NAME = "word_database";

    public static ArticleDataBase INSTANCE;

    public abstract ArticleDao wordDao();

    public static ArticleDataBase getInstance(final Context context){

        if(INSTANCE==null){
            INSTANCE = createInstance(context);
        }

        return INSTANCE;
    }

    public static ArticleDataBase createInstance(Context context){
        return Room.databaseBuilder(context.getApplicationContext(),
                ArticleDataBase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

}
