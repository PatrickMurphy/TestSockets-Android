package com.patrickmurphywebdesign.testsockets.controller;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class MapIcons {
    private Activity act;
    private BitmapDescriptor icon_stop_half;
    private BitmapDescriptor icon_stop_threeFourths;

    public MapIcons(Activity act) {
        this.act = act;
        Bitmap imageBitmap = BitmapFactory.decodeResource(this.act.getResources(), this.act.getResources().getIdentifier("variable_stop", "drawable", this.act.getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, imageBitmap.getWidth() / 2, imageBitmap.getHeight() / 2, false);
        Bitmap resizedBitmapTimed = Bitmap.createScaledBitmap(imageBitmap, (imageBitmap.getWidth()/4)*3, (imageBitmap.getHeight()/4)*3, false);
        icon_stop_half = BitmapDescriptorFactory.fromBitmap(resizedBitmap);
        icon_stop_threeFourths = BitmapDescriptorFactory.fromBitmap(resizedBitmapTimed);
    }

    public BitmapDescriptor getIcon_bus(int direction, int color, int velocity){
        String iconName;
        switch(color){
            case 0:
                iconName = "redbus";
                break;
            case 1:
                iconName = "bluebus";
                break;
            default:
                iconName = "redbus";
        }
        int scale = 5;
        Matrix matrix = new Matrix();
        if(velocity <= 0){
            iconName += "_stopped";
            scale++;
        }else {
            matrix.postRotate(direction);
        }
        Bitmap imageBitmap = BitmapFactory.decodeResource(act.getResources(), act.getResources().getIdentifier(iconName, "drawable", act.getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, imageBitmap.getWidth() / scale, imageBitmap.getHeight() / scale, false);
        return BitmapDescriptorFactory.fromBitmap(Bitmap.createBitmap(resizedBitmap, 0, 0, resizedBitmap.getWidth(), resizedBitmap.getHeight(), matrix, true));
    };

    public BitmapDescriptor getIcon_stop_half() {
        return icon_stop_half;
    }

    public BitmapDescriptor getIcon_stop_threeFourths() {
        return icon_stop_threeFourths;
    }
}
