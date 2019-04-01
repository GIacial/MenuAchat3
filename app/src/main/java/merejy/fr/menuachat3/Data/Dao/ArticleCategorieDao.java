package merejy.fr.menuachat3.Data.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import merejy.fr.menuachat3.Data.Entity.ArticleCategorie;


@Dao
public interface ArticleCategorieDao {

    @Query("SELECT * FROM ArticleCategorie")
    List<ArticleCategorie> getAll();

    @Query("SELECT * FROM ArticleCategorie WHERE id IN (:articlecategorieIds)")
    List<ArticleCategorie> loadAllByIds(int[] articlecategorieIds);

    @Query("SELECT * FROM ArticleCategorie WHERE id = (:articlecategorieId)")
    ArticleCategorie loadById(int articlecategorieId);


    @Insert
    void insertAll(ArticleCategorie... articlecategories);

    @Insert
    void insertAll(List<ArticleCategorie> articlecategories);

    @Update
    void updateAll(ArticleCategorie... articlecategories);

    @Update
    void updateAll(List<ArticleCategorie> articlecategories);

    @Delete
    void delete(ArticleCategorie... articlecategories);

    @Delete
    void delete(List<ArticleCategorie> articlecategories);

}