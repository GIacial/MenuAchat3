package merejy.fr.menuachat3.UI.Popup.Module.entier;

import android.app.Activity;

import merejy.fr.menuachat3.R;
import merejy.fr.menuachat3.UI.Interface.Groupe;

public class EntierAddToGroupModule<T> implements EntierNumberPopupModule {

    private Groupe<T> groupe;
    private T object;
    private Activity activity;

    public EntierAddToGroupModule(Groupe<T> groupe, T object , Activity activity){
        this.groupe = groupe;
        this.object = object;
        this.activity = activity;
    }

    @Override
    public Runnable getMethodOnComfirm(final int val) {
        return new Runnable() {
            @Override
            public void run() {
                groupe.addToGroup(object,val);
            }
        };
    }

    @Override
    public Runnable getMethodOnAnuller() {
        return null;
    }

    @Override
    public String getQuestion() {
        return activity.getString(R.string.text_combien_de)+ " " +object.toString()+" "+activity.getString(R.string.text_voulez_vous);
    }

}
