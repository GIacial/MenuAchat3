package merejy.fr.menuachat3.Data.DataTask;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.List;

import merejy.fr.menuachat3.Data.AppDatabase;
import merejy.fr.menuachat3.Data.DataTask.LoaderAskerInterface.LoaderAsker_CategorieArticle;
import merejy.fr.menuachat3.Data.Entity.ArticleCategorie;

public class LoadAll_ArticleCategorie extends AsyncTask<Void,Void,List<ArticleCategorie>> {

    private Activity activity;
    private LoaderAsker_CategorieArticle loaderAsker_categorieArticle;

    public LoadAll_ArticleCategorie(Activity activity , LoaderAsker_CategorieArticle loaderAsker_categorieArticle){
        this.activity = activity;
        this.loaderAsker_categorieArticle = loaderAsker_categorieArticle;
    }

    @Override
    protected List<ArticleCategorie> doInBackground(Void... voids) {
        return AppDatabase.getInstance(activity).getArticleCategorieDao().getAll();
    }

    @Override
    protected void onPostExecute(List<ArticleCategorie> ok) {
        super.onPostExecute(ok);
        if(!ok.isEmpty()){
            loaderAsker_categorieArticle.setCategorieArticle(ok);
        }
    }
}
