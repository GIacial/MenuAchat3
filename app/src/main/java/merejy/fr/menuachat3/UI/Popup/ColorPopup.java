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
import android.widget.SeekBar;

import merejy.fr.menuachat3.Kernel.ColorManager;
import merejy.fr.menuachat3.R;
import merejy.fr.menuachat3.UI.Popup.Module.color.ColorPopupModule;
import merejy.fr.menuachat3.databinding.PopupColorBinding;

public class ColorPopup extends DialogFragment {

    private static String KEY_MODULE = "module";
    private static String FRAGMENT_NAME = "colorPopup";

    private static ColorPopup newInstance(ColorPopupModule module) {
        ColorPopup f = new ColorPopup();
        Bundle args = new Bundle();
        args.putSerializable(KEY_MODULE,module);
        f.setArguments(args);
        return f;
    }

    static public void showInstance(ColorPopupModule module , Activity activity){
        ColorPopup popup = newInstance(module);
        popup.show(activity.getFragmentManager(),FRAGMENT_NAME);
    }



    private int rouge = 0;
    private int vert = 0;
    private  int bleu = 0;
    private ColorPopupModule module = null;
    private PopupColorBinding ui;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        Bundle argumentBundle = getArguments();
        if(argumentBundle != null){
            module = (ColorPopupModule) argumentBundle.getSerializable(KEY_MODULE);
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
          ui = DataBindingUtil.inflate(inflater,R.layout.popup_color,null,false);
         builder.setView(ui.getRoot());

        ui.textColor.setBackgroundColor(Color.rgb(rouge,vert,bleu));
        ui.textColor.setTextColor(ColorManager.getTextColorForCategorieColorInst(Color.rgb(rouge,vert,bleu)));

        SeekBar.OnSeekBarChangeListener colorListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(ui.redBar.equals(seekBar)){
                    rouge = progress;
                }
                else if(ui.blueBar.equals(seekBar)){
                    bleu = progress;
                }
                else if(ui.greenBar.equals(seekBar)){
                    vert = progress;
                }
                ui.textColor.setBackgroundColor(Color.rgb(rouge,vert,bleu));
                ui.textColor.setTextColor(ColorManager.getTextColorForCategorieColorInst(Color.rgb(rouge,vert,bleu)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        ui.redBar.setOnSeekBarChangeListener(colorListener);
        ui.greenBar.setOnSeekBarChangeListener(colorListener);
        ui.blueBar.setOnSeekBarChangeListener(colorListener);




        builder.setPositiveButton(R.string.button_comfirmer, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //code de comfirmation
                Runnable method = module.getMethodOnComfirm(rouge,vert,bleu);
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
