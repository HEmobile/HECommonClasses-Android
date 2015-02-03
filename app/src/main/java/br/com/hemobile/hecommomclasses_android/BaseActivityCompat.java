package br.com.hemobile.hecommomclasses_android;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.UiThread;

@EActivity
public abstract class BaseActivityCompat extends ActionBarActivity {

	boolean portraitMode = false;

	@InstanceState
	protected boolean fromScreenOrientationChange;

    private ProgressDialog progressDialog;

	protected boolean firstTime = true;

	@AfterViews
	protected final void baseInit() {
		if (!fromScreenOrientationChange) {
			fromScreenOrientationChange = true;
		} else {
			setFirstTime(false);
		}
		hideKeyboard();
		setupActionBar(getSupportActionBar());
	}

    private TextView getActionBarTitleTextView() {
		final int actionBarTitle = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
	    return (TextView)getWindow().findViewById(actionBarTitle);
	}

	public void setupActionBar(ActionBar actionBar) {
		try {
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
		} catch (Exception e) {
		}
	}

	public boolean isFirstTime() {
		return firstTime;
	}

	public void setFirstTime(boolean firstTime) {
		this.firstTime = firstTime;
	}

	public void hideKeyboard() {
		try {
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),  InputMethodManager.HIDE_NOT_ALWAYS);
		} catch (Exception e) {
		}
	}

	@OptionsItem(android.R.id.home)
	protected void onBackActionBarClick() {
		onBackPressed();
	}

	public void update() {

 	}

    @UiThread
    public void showProgress(String text) {
        progressDialog = ProgressDialog.show(this, "Aguarde...", text, true, false);
    }

    @UiThread
    public void dismissProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @UiThread
    public void changeProgressMessage(String message) {
        if (progressDialog != null) {
            progressDialog.setMessage(message);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (HEApplication.isAppBlocked()) {
            Toast.makeText(this, "Sua conta foi bloqueada, contate o administrador.", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public final BaseActivityCompat getActivity() {
        return this;
    }
}