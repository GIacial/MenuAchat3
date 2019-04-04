package merejy.fr.menuachat3.Data.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ColumnInfo;
import android.graphics.Color;
import android.support.annotation.NonNull;


@Entity
public class ArticleCategorie {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nom")
    @NonNull
    public String nom;

    @ColumnInfo (name = "couleur")
    @NonNull
    public int couleur;

    public ArticleCategorie(@NonNull String nom , @NonNull int couleur){
        this.nom = nom;
        this.couleur = couleur;
    }
}
