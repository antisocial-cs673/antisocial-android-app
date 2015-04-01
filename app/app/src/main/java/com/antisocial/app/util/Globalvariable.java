package com.antisocial.app.util;

import android.app.Activity;

/**
 * Created by Nirmit Shah on 4/1/2015.
 */
public class Globalvariable extends Activity {

    public int flag=0;

    public int getSomeVariable() {
        return flag;
    }

    public void setSomeVariable(int someVariable) {
        this.flag = someVariable;
    }
}
