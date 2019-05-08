package merejy.fr.menuachat3.UI.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

import merejy.fr.menuachat3.Data.DataTask.LoadAll_Article;
import merejy.fr.menuachat3.Data.DataTask.Load_Article_ByName;
import merejy.fr.menuachat3.Data.Entity.Article;
import merejy.fr.menuachat3.Kernel.MyConstante;
import merejy.fr.menuachat3.R;
import merejy.fr.menuachat3.UI.Interface.Cancellable;
import merejy.fr.menuachat3.UI.Interface.Comfirmable;
import merejy.fr.menuachat3.UI.Interface.Groupe;
import merejy.fr.menuachat3.UI.Listener.Factory.EntierModuleFactory.AddArticleToGroupNumberPopupModuleFactory;
import merejy.fr.menuachat3.UI.Listener.Factory.OnClickFactory.AddToGroupClickFactory;
import merejy.fr.menuachat3.UI.Listener.OnClickOnCancel;
import merejy.fr.menuachat3.UI.Listener.OnClickOnComfirm;
import merejy.fr.menuachat3.UI.adapter.Recycler.RecyclerAdapter_All_Article;
import merejy.fr.menuachat3.databinding.ActivitySelectArticleBinding;

public class SelectArticleActivity extends AppCompatActivity implements Cancellable , Comfirmable , Groupe<Article>{



    private ActivitySelectArticleBinding ui;
    private RecyclerAdapter_All_Article recyclerAdapter_all_article;
    private HashMap<Article,Integer> articlesSelected = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = DataBindingUtil.setContentView(this,R.layout.activity_select_article);

        recyclerAdapter_all_article = new RecyclerAdapter_All_Article(this,new AddToGroupClickFactory<>(this,new AddArticleToGroupNumberPopupModuleFactory()));
        ui.ListArticle.setLayoutManager(new LinearLayoutManager(this));
        ui.ListArticle.setAdapter(recyclerAdapter_all_article);
        LoadAll_Article loader = new LoadAll_Article(this,recyclerAdapter_all_article);
        loader.execute();

        ui.cancelButton.setOnClickListener(new OnClickOnCancel(this));
        ui.ComfirmButton.setOnClickListener(new OnClickOnComfirm(this));

        ui.AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(SelectArticleActivity.this,CreateArticleActivity.class);
                if(nextActivity != null){
                    startActivityForResult(nextActivity,MyConstante.REQUEST_ADD_ARTICLE);
                }
                else{
                    Log.e(MyConstante.APP_TAG,getString(R.string.debug_no_activity_add_article));
                }
            }
        });
    }

    @Override
    public void cancel() {
        this.finish();
    }

    @Override
    public void comfirm() {
        Toast.makeText(this,getString(R.string.debug_todo),Toast.LENGTH_SHORT).show();
        for(Article article : this.articlesSelected.keySet()){
            Log.d(MyConstante.APP_TAG,"Selection de "+article.nom+" x"+this.articlesSelected.get(article));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (MyConstante.REQUEST_ADD_ARTICLE) :
                if (resultCode == MyConstante.RESULT_OK) {
                    // Extract the data returned from the child Activity.
                    String articleName = data.getStringExtra(CreateArticleActivity.ARTICLE_NAME);


                    //ajout des info dans le recycler
                    Load_Article_ByName loader = new Load_Article_ByName(this,recyclerAdapter_all_article);
                    loader.execute(articleName);
                }
                break;
            default: Log.d(MyConstante.APP_TAG,getString(R.string.debug_unknow_activity));

        }
    }

    @Override
    public void addToGroup(Article object) {
        this.addToGroup(object,1);
    }

    @Override
    public void addToGroup(Article object, int nombre) {
        if(this.articlesSelected.containsKey(object)){
            int nb = this.articlesSelected.get(object);
            if(-nombre >= nb){
                //supression
                this.articlesSelected.remove(object);
            }
            else{
                this.articlesSelected.put(object,nb+nombre);
            }
        }
        else{
            if( nombre > 0){
                this.articlesSelected.put(object,nombre);
            }
        }
    }
}
