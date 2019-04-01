package merejy.fr.menuachat3.Data.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ColumnInfo;
import android.graphics.Color;


@Entity
public class ArticleCategorie {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "nom")
    public String nom;

    @ColumnInfo (name = "couleur")
    public int couleur;
}
