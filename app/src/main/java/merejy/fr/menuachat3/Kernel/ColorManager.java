package merejy.fr.menuachat3.Kernel;

import android.graphics.Color;


public class ColorManager {

    public static int getTextColorForCategorieColorInst(int background){
        int gray = (Color.red(background) + Color.green(background) + Color.green(background))/3;
        int res = 255 - gray;
        if (res >= 97 && res <= 157){//gris du milieu on mets du noir
            res = 0;
        }
        return Color.rgb(res,res,res) ;
    }

}
