package merejy.fr.menuachat3.UI.Listener;

import android.view.View;

import merejy.fr.menuachat3.UI.Interface.Comfirmable;

public class OnClickOnComfirm implements View.OnClickListener {

    private Comfirmable comfirmable;

    public OnClickOnComfirm (Comfirmable comfirmable){
        this.comfirmable = comfirmable;
    }

    @Override
    public void onClick(View v) {
        comfirmable.comfirm();
    }
}
