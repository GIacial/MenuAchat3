package merejy.fr.menuachat3.UI.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import merejy.fr.menuachat3.Data.Entity.ArticleCategorie;
import merejy.fr.menuachat3.Kernel.ColorManager;
import merejy.fr.menuachat3.Kernel.MyConstante;
import merejy.fr.menuachat3.R;

public class ArrayAdapter_CategorieArticle extends ArrayAdapter<ArticleCategorie> {


    public ArrayAdapter_CategorieArticle(@NonNull Context context, int resource, @NonNull List<ArticleCategorie> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = super.getDropDownView(position, convertView, parent);
        ArticleCategorie c = this.getItem(position);
        v.setBackgroundColor(c.couleur);
        if(v instanceof TextView){
            ((TextView)v).setTextColor(ColorManager.getTextColorForCategorieColorInst(c.couleur));
        }
        return v;
    }
}
