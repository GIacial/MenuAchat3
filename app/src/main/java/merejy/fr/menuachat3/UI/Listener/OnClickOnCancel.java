package merejy.fr.menuachat3.UI.Listener;

import android.view.View;

import merejy.fr.menuachat3.UI.Interface.Cancellable;

public class OnClickOnCancel implements View.OnClickListener {

    private Cancellable cancellable;

    public OnClickOnCancel(Cancellable cancellable){
        this.cancellable = cancellable;
    }

    @Override
    public void onClick(View v) {
        cancellable.cancel();
    }

}
