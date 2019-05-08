package merejy.fr.menuachat3.UI.Popup;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;

import merejy.fr.menuachat3.Kernel.ColorManager;
import merejy.fr.menuachat3.R;
import merejy.fr.menuachat3.UI.Popup.Module.color.ColorPopupModule;
import merejy.fr.menuachat3.UI.Popup.Module.entier.EntierNumberPopupModule;
import merejy.fr.menuachat3.databinding.PopupColorBinding;
import merejy.fr.menuachat3.databinding.PopupQuantiteBinding;

public class EntierPopup extends DialogFragment {

    private static String KEY_MODULE = "module";
    private static String FRAGMENT_NAME = "entierPopup";

    private static EntierPopup newInstance(EntierNumberPopupModule module) {
        EntierPopup f = new EntierPopup();
        Bundle args = new Bundle();
        args.putSerializable(KEY_MODULE,module);
        f.setArguments(args);
        return f;
    }

    static public void showInstance(EntierNumberPopupModule module , Activity activity){
        EntierPopup popup = newInstance(module);
        popup.show(activity.getFragmentManager(),FRAGMENT_NAME);
    }




    private int number ;
    private EntierNumberPopupModule module = null;
    private PopupQuantiteBinding ui;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        Bundle argumentBundle = getArguments();
        if(argumentBundle != null){
            module = (EntierNumberPopupModule) argumentBundle.getSerializable(KEY_MODULE);
            number = module.getMinValue();
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        ui = DataBindingUtil.inflate(inflater, R.layout.popup_quantite,null,false);
        builder.setView(ui.getRoot());

       //le remplissage du design
        ui.buttonMoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number-= module.getPas();
                if(number < module.getMinValue()){
                    number = module.getMinValue();
                }
                ui.textQuantite.setText(number + " " + module.getUnit());
            }
        });
        ui.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number+=module.getPas();
                ui.textQuantite.setText(number + " " + module.getUnit());
            }
        });
        ui.textQuestion.setText(module.getQuestion());
        ui.textQuantite.setText(number + " " + module.getUnit());

        //les boutons comfirm et annuler
        builder.setPositiveButton(R.string.button_comfirmer, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //code de comfirmation
                Runnable method = module.getMethodOnComfirm(number);
                if(method != null){
                    method.run();
                }
            }
        })
                .setNegativeButton(R.string.button_annuler, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Runnable method = module.getMethodOnAnuller();
                        if(method != null){
                            method.run();
                        }
                    }
                });

        // Create the AlertDialog object and return it*/
        return builder.create();
    }


}
