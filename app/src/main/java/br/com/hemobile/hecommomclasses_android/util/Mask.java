package br.com.hemobile.hecommomclasses_android.util;

import android.support.annotation.NonNull;
import android.text.TextWatcher;
import android.widget.EditText;

import java.lang.ref.WeakReference;

/**
 * Source: https://andremrezende.wordpress.com/tag/android-mask-mascara-edittext-java-layout-cpf-cnpj/
 */
public abstract class Mask {

    public static final String PHONE_9_MASK = "(##) #####-####";
    public static final String PHONE_8_MASK = "(##) ####-####";
    public static final String CPF_MASK     = "###.###.###-##";
    public static final String CNPJ_MASK    = "##.###.###/####-##";

    @NonNull
    public static String unmask(String s) {
        return s.replaceAll("[./() -]", "");
    }

    @NonNull
    public static TextWatcher insert(final String mask, final EditText editText) {
        return new MaskTextWatcher(editText, mask);
    }

    @NonNull
    public static TextWatcher insertPhoneMask(final EditText editText) {
        return new MaskTextWatcher(editText, null) {
            @Override
            protected String getMask(String unmaskedValue) {
                if(unmaskedValue.length() < 11) {
                    return PHONE_8_MASK;
                }

                return PHONE_9_MASK;
            }
        };
    }

    private static class MaskTextWatcher extends SimpleTextWatcher {

        WeakReference<EditText> weakEditText;
        String mask, oldValue = "";

        boolean isUpdating;

        public MaskTextWatcher(EditText editText) {
            weakEditText = new WeakReference<EditText>(editText);
        }

        public MaskTextWatcher(EditText editText, String mask) {
            this(editText);
            this.mask = mask;
        }

        protected String getMask(String unmaskedValue) {
            return mask;
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String unmaskedString = Mask.unmask(s.toString());
            StringBuilder maskedString = new StringBuilder("");
            String mask = getMask(unmaskedString);

            EditText editText = weakEditText.get();

            // EditText was GC'ed
            if(editText == null) {
                return;
            }

            if (isUpdating) {
                oldValue = unmaskedString;
                isUpdating = false;

                return;
            }

            int i = 0;

            for (char m : mask.toCharArray()) {
                if (m != '#' && i < unmaskedString.length()) {
                    maskedString.append(m);
                    continue;
                }

                try {
                    maskedString.append(unmaskedString.charAt(i));
                } catch (Exception e) {
                    break;
                }

                i++;
            }

            isUpdating = true;

            editText.setText(maskedString);
            editText.setSelection(maskedString.length());
        }
    }
}
