package merejy.fr.menuachat3.Data.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ColumnInfo;
import android.support.annotation.NonNull;

import merejy.fr.menuachat3.Kernel.Enum.NeedingState;


@Entity(indices = {@Index(name = "NeedingArticleIdIndex", value = "id", unique = true)})
public class NeedingArticle {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    public NeedingState state;

    @ColumnInfo
    //en fonction de si le poids est fixe ou pas soit nombre unitaire ou poids en gramme
    public int nb_ou_poids;

    @ColumnInfo
    public int article_id;
}
