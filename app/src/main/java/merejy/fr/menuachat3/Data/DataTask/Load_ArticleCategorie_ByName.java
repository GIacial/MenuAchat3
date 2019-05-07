package merejy.fr.menuachat3.Data.DataTask;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import merejy.fr.menuachat3.Data.AppDatabase;
import merejy.fr.menuachat3.Data.Dao.ArticleCategorieDao;
import merejy.fr.menuachat3.Data.DataTask.LoaderAskerInterface.LoaderAsker_CategorieArticle;
import merejy.fr.menuachat3.Data.Entity.ArticleCategorie;
import merejy.fr.menuachat3.Kernel.MyConstante;

public class Load_ArticleCategorie_ByName extends AsyncTask<String,Void,List<ArticleCategorie>> {

    private Activity activity;
    private LoaderAsker_CategorieArticle loaderAsker_categorieArticle;

    public Load_ArticleCategorie_ByName(Activity activity , LoaderAsker_CategorieArticle loaderAsker_categorieArticle){
        this.activity = activity;
        this.loaderAsker_categorieArticle = loaderAsker_categorieArticle;
    }

    @Override
    protected List<ArticleCategorie> doInBackground(String... names) {
        List<ArticleCategorie> result = new ArrayList<>();
        ArticleCategorieDao dao = AppDatabase.getInstance(activity).getArticleCategorieDao();
        for(String name : names){
            List<ArticleCategorie> categorie = dao.findByName(name);
            Log.d(MyConstante.APP_TAG,"Cat load for name "+name+"  : "+categorie);
            if (categorie.size() == 1 && !result.contains(categorie.get(0))){
                result.addAll(categorie);
                Log.d(MyConstante.APP_TAG,"Add result for name "+name+"  : "+categorie);
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(List<ArticleCategorie> ok) {
        super.onPostExecute(ok);
        if(!ok.isEmpty()){
            loaderAsker_categorieArticle.setCategorieArticle(ok);
        }
    }
}
