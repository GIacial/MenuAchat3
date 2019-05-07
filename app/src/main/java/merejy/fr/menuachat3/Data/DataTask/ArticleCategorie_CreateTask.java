package merejy.fr.menuachat3.Data.DataTask;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import merejy.fr.menuachat3.Data.AppDatabase;
import merejy.fr.menuachat3.Data.Dao.ArticleCategorieDao;
import merejy.fr.menuachat3.Data.Entity.ArticleCategorie;
import merejy.fr.menuachat3.Kernel.MyConstante;
import merejy.fr.menuachat3.R;
import merejy.fr.menuachat3.UI.activity.CreateArticleCategorieActivity;

public class ArticleCategorie_CreateTask extends AsyncTask<ArticleCategorie,String,Boolean> {

    private Activity activity;
    private List<ArticleCategorie> allAddingCat = new ArrayList<>();

    public ArticleCategorie_CreateTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Boolean doInBackground(ArticleCategorie... articleCategories) {
        final ArticleCategorieDao articleCategorieDao = AppDatabase.getInstance(activity).getArticleCategorieDao();
        boolean ok = true;
        for ( ArticleCategorie categorie : articleCategories){
            // Escape early if cancel() is called
            if (isCancelled()){
                ok = false;
                break;
            }
            this.allAddingCat.add(categorie);
            if(articleCategorieDao.findByName(categorie.nom).isEmpty()){
                System.err.println("ok free");
                articleCategorieDao.insertAll(categorie);

            }
            else {
                //nom pas dispo
                this.publishProgress(categorie.nom +" "+activity.getString(R.string.erreur_nom_deja_pris) );
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
            Toast.makeText(activity,R.string.text_categorie_rajout_comfirmation,Toast.LENGTH_SHORT).show();

            Intent resultIntent = new Intent();
            for (ArticleCategorie c : allAddingCat){
                resultIntent.putExtra(CreateArticleCategorieActivity.NEW_CAT,c.nom);
                Log.d(MyConstante.APP_TAG,"cat id enregistrement :"+ c.id);
            }
            activity.setResult(MyConstante.RESULT_OK, resultIntent);
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
