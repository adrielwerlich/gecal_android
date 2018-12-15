package com.smp.adrie.complaintsystem.activities;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public abstract class PhoneMask  {

    private static final String mask10 = "(##) ####-####";
    private static final String mask11 = "(##) #####-####";
    private static final String mask8 = "####-####";
    private static final String mask9 = "#####-####";

    private static String getDefaultMask (String s) {
        String defaultMask = mask8;
        if (s.length() > 11){
            defaultMask = mask11;
        }
        return defaultMask;
    }


    public static TextWatcher insert(final String mask, final EditText ediTxt) {


        return new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void afterTextChanged(Editable s) {

            }

            boolean isUpdating;
            String old = "";

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String str = PhoneMask.unmask(s.toString());
                String mask = "";
                String defaultMask = getDefaultMask(str);
                switch (str.length()) {
                    case 11:
                        mask = mask11;
                        break;
                    case 10:
                        mask = mask10;
                        break;
                    case 9:
                        mask = mask9;
                        break;
                    default:
                        mask = defaultMask;
                        break;
                }
                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if ((m != '#' && str.length() > old.length()) || (m != '#' && str.length() < old.length() && str.length() != i)) {
                        mascara += m;
                        continue;
                    }

                    try {
                        mascara += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());

            Log.i("[PHONE MASK]", String.valueOf(
                        "CharSequence-> " + s +
                        ". Start-> " + start +
                        ". Before-> " +  before +
                        ". Count-> " + count +
                        ". IsUpdating-> " + isUpdating +
                        ". Mask-> " + mask
                 ));

            }
        };
    }

    public static String unmask(String s) {
        return s.replaceAll("[^0-9]*", "");
    }

//    public static String unmask(String s) {
//        return s.replaceAll("[.]", "").replaceAll("[-]", "")
//                .replaceAll("[/]", "").replaceAll("[(]", "")
//                .replaceAll("[)]", "");
//    }
}
