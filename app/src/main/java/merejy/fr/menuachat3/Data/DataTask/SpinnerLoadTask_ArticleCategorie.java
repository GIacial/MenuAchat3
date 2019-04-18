package merejy.fr.menuachat3.Data.DataTask;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import merejy.fr.menuachat3.Data.AppDatabase;
import merejy.fr.menuachat3.Data.Entity.ArticleCategorie;
import merejy.fr.menuachat3.Kernel.MyConstante;
import merejy.fr.menuachat3.R;
import merejy.fr.menuachat3.UI.activity.CreateArticleCategorieActivity;
import merejy.fr.menuachat3.UI.adapter.ArrayAdapter_CategorieArticle;

public class SpinnerLoadTask_ArticleCategorie extends AsyncTask<Void,Void,List<ArticleCategorie>> {

    private Activity activity;
    private ArrayAdapter_CategorieArticle adapter_categorieArticle;

    public SpinnerLoadTask_ArticleCategorie(Activity activity , ArrayAdapter_CategorieArticle adapter_categorieArticle){
        this.activity = activity;
        this.adapter_categorieArticle = adapter_categorieArticle;
    }

    @Override
    protected List<ArticleCategorie> doInBackground(Void... voids) {
        return AppDatabase.getInstance(activity).getArticleCategorieDao().getAll();
    }

    @Override
    protected void onPostExecute(List<ArticleCategorie> ok) {
        super.onPostExecute(ok);
        if(!ok.isEmpty()){
            adapter_categorieArticle.addAll(ok);
        }
    }
}
