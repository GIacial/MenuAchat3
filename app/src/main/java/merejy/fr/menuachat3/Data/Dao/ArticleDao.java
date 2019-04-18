package merejy.fr.menuachat3.Data.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import merejy.fr.menuachat3.Data.Entity.Article;


@Dao
public interface ArticleDao {

    @Query("SELECT * FROM Article")
    List<Article> getAll();

    @Query("SELECT * FROM Article WHERE id IN (:articleIds)")
    List<Article> loadAllByIds(int[] articleIds);

    @Query("SELECT * FROM Article WHERE id = (:articleId)")
    Article loadById(int articleId);

    @Query("SELECT * FROM ARTICLE WHERE nom = (:articleName)")
    List<Article> findByName(String articleName);


    @Insert
    void insertAll(Article... articles);

    @Insert
    void insertAll(List<Article> articles);

    @Update
    void updateAll(Article... articles);

    @Update
    void updateAll(List<Article> articles);

    @Delete
    void delete(Article... articles);

    @Delete
    void delete(List<Article> articles);

}