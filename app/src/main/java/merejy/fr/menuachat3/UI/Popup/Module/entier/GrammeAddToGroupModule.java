package merejy.fr.menuachat3.UI.Popup.Module.entier;

import android.app.Activity;

import merejy.fr.menuachat3.R;
import merejy.fr.menuachat3.UI.Interface.Groupe;

public class GrammeAddToGroupModule<T> extends EntierAddToGroupModule<T> {

    public GrammeAddToGroupModule(Groupe<T> groupe, T object, Activity activity) {
        super(groupe, object, activity);
    }

    @Override
    public String getQuestion() {
        return getActivity().getString(R.string.text_combien_de_gramme)+ " " +getObject().toString()+" "+getActivity().getString(R.string.text_voulez_vous);
    }

    @Override
    public int getPas() {
        return 50;
    }

    @Override
    public String getUnit() {
        return "g";
    }

}
