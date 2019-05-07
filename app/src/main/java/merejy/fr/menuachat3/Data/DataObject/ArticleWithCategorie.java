package merejy.fr.menuachat3.Data.DataObject;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

import merejy.fr.menuachat3.Data.Entity.Article;
import merejy.fr.menuachat3.Data.Entity.ArticleCategorie;

public class ArticleWithCategorie {
    @Embedded
    public Article article;

    @Relation(parentColumn = "cat_id",entityColumn = "id")
    public List<ArticleCategorie> categorie;


}
