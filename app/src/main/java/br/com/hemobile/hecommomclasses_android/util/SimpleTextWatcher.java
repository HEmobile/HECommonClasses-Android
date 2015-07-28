package br.com.hemobile.hecommomclasses_android.util;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * {@link TextWatcher} implementation that do nothing by default
 */
public class SimpleTextWatcher implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
