package merejy.fr.menuachat3.Data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import merejy.fr.menuachat3.Data.Dao.ArticleCategorieDao;
import merejy.fr.menuachat3.Data.Dao.ArticleDao;
import merejy.fr.menuachat3.Data.Entity.Article;
import merejy.fr.menuachat3.Data.Entity.ArticleCategorie;

@Database(entities = {ArticleCategorie.class, Article.class}, version = 3 , exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase ourInstance = null;
    private static final String Database_name = "MenuAchat3";

    public static AppDatabase getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = Room.databaseBuilder(context, AppDatabase.class, Database_name).fallbackToDestructiveMigration().build();
        }
        return ourInstance;
    }

    public abstract ArticleCategorieDao getArticleCategorieDao();
    public abstract ArticleDao getArticleDao();
}