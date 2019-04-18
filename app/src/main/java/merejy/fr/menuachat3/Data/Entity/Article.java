package merejy.fr.menuachat3.Data.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(foreignKeys = @ForeignKey(entity = ArticleCategorie.class,
        parentColumns = "id",
        childColumns = "cat_id")
        ,indices = {@Index(name = "ArticleIdIndex",value = "id", unique = true),
        @Index(name = "ArticleCatIndex",value = {"cat_id"})})
public class Article {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "nom")
    public String nom;

    @NonNull
    @ColumnInfo(name = "cat_id")
    public int cat_id;

    @NonNull
    @ColumnInfo(name = "poids_fixe")
    public boolean poids_fixe;

    public Article(@NonNull String nom, @NonNull int cat_id, @NonNull boolean poids_fixe) {
        this.nom = nom;
        this.cat_id = cat_id;
        this.poids_fixe = poids_fixe;
    }
}
