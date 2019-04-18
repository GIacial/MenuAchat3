package merejy.fr.menuachat3.Data.DataTask;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import merejy.fr.menuachat3.Data.AppDatabase;
import merejy.fr.menuachat3.Data.Dao.ArticleCategorieDao;
import merejy.fr.menuachat3.Data.Dao.ArticleDao;
import merejy.fr.menuachat3.Data.Entity.Article;
import merejy.fr.menuachat3.Data.Entity.ArticleCategorie;
import merejy.fr.menuachat3.Kernel.MyConstante;
import merejy.fr.menuachat3.R;
import merejy.fr.menuachat3.UI.activity.CreateArticleCategorieActivity;

public class Article_createTask extends AsyncTask<Article,String,Boolean> {

    private Activity activity;
    private List<Article> allAddingArticle = new ArrayList<>();

    public Article_createTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Boolean doInBackground(Article... articles) {
        final ArticleDao articleDao = AppDatabase.getInstance(activity).getArticleDao();
        boolean ok = true;
        for ( Article article : articles){
            // Escape early if cancel() is called
            if (isCancelled()){
                ok = false;
                break;
            }
            this.allAddingArticle.add(article);
            if(articleDao.findByName(article.nom).isEmpty()){
                System.err.println("ok free");
                articleDao.insertAll(article);

            }
            else {
                //nom pas dispo
                this.publishProgress(article.nom +" "+activity.getString(R.string.erreur_nom_deja_pris) );
                ok = false;
            }
        }

        return ok;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        for (String val : values) {
            Toast.makeText(activity, val, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPostExecute(Boolean ok) {
        super.onPostExecute(ok);
        if(ok){
            Toast.makeText(activity,R.string.text_article_rajout_comfirmation,Toast.LENGTH_SHORT).show();

            /*Intent resultIntent = new Intent();
            for (Article c : allAddingArticle){
                resultIntent.putExtra(CreateArticleCategorieActivity.NEW_CAT,c.nom);
                resultIntent.putExtra(CreateArticleCategorieActivity.NEW_COLOR_CAT,c.couleur);
            }
            activity.setResult(MyConstante.RESULT_OK, resultIntent);*/
            this.activity.finish();
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Intent resultIntent = new Intent();
        activity.setResult(MyConstante.RESULT_CANCEL, resultIntent);
        activity.finish();
    }
}
