package merejy.fr.menuachat3.UI.adapter.Recycler;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import merejy.fr.menuachat3.Data.DataObject.ArticleWithCategorie;
import merejy.fr.menuachat3.Data.DataTask.LoaderAskerInterface.LoaderAsker_Article;
import merejy.fr.menuachat3.Data.DataTask.LoaderAskerInterface.LoaderAsker_CategorieArticle;
import merejy.fr.menuachat3.Data.Entity.Article;
import merejy.fr.menuachat3.Data.Entity.ArticleCategorie;
import merejy.fr.menuachat3.Kernel.ColorManager;
import merejy.fr.menuachat3.R;
import merejy.fr.menuachat3.UI.Listener.Factory.OnClickFactory.OnClickFactory;
import merejy.fr.menuachat3.databinding.RecyclerArticleAllListeBinding;

public class RecyclerAdapter_All_Article extends RecyclerView.Adapter<RecyclerAdapter_All_Article.ViewHolder> implements LoaderAsker_CategorieArticle , LoaderAsker_Article {
    private List<Article> articles;
    private Activity activity;
    private OnClickFactory<Article> onClickFactory;
    private HashMap<Integer,ArticleCategorie> categories;

    @Override
    public void setCategorieArticle(List<ArticleCategorie> categories) {
        for(ArticleCategorie categorie : categories){
            if(!this.categories.containsKey(categorie.id)){
                this.categories.put(categorie.id,categorie);
            }
        }
    }

    @Override
    public void setArticle(List<ArticleWithCategorie> articles) {
        for(ArticleWithCategorie article : articles){
            this.articles.add(article.article);
            if(!this.categories.containsKey(article.article.cat_id)){
                //charge la catégorie
                setCategorieArticle(article.categorie);
            }
        }
        this.notifyDataSetChanged();

    }

    //View pour un item
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerArticleAllListeBinding ui;

        ViewHolder(View v, RecyclerArticleAllListeBinding ui) {
            super(v);
            this.ui = ui;
        }
    }

    // constructeur
    public RecyclerAdapter_All_Article(Activity activity , OnClickFactory<Article> onClickFactory) {
        this.activity = activity;
        this.onClickFactory = onClickFactory;
        this.articles = new ArrayList<>();
        this.categories = new HashMap<>();
    }

    // Initialisation des views
    @Override
    public RecyclerAdapter_All_Article.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        // create a new view

        RecyclerArticleAllListeBinding ui = DataBindingUtil.inflate(activity.getLayoutInflater(), R.layout.recycler_article_all_liste,null,false);
        ViewHolder vh = new ViewHolder(ui.getRoot(),ui);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //ici on mets a jour les info des composant du Holder
        Article article = this.articles.get(position);
        ArticleCategorie categorie = categories.get(article.cat_id);

        holder.ui.name.setText(article.nom);
        holder.ui.catName.setText(categorie.toString());

        holder.itemView.setBackgroundColor(categorie.couleur);
        if(onClickFactory != null){
            holder.itemView.setOnClickListener(onClickFactory.createListener(article,activity));
        }
        holder.ui.name.setTextColor(ColorManager.getTextColorForCategorieColorInst(categorie.couleur));
        holder.ui.catName.setTextColor(ColorManager.getTextColorForCategorieColorInst(categorie.couleur));
        if(!article.poids_fixe){
            holder.ui.poidsFixe.setVisibility(View.INVISIBLE);
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return articles.size();
    }








}