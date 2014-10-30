package br.com.hemobile.hecommomclasses_android.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import br.com.hemobile.hecommomclasses_android.BaseActivity;
import br.com.hemobile.hecommomclasses_android.R;

/**
 * Activity base para uma tela que contenha um NavigationDrawer
 * See the <a href="https://developer.android.com/design/patterns/navigation-drawer.html#Interaction">
 * Created by hemobile on 24/10/14.
 */
@EActivity
public abstract class DrawerActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @ViewById
    DrawerLayout drawerLayout;

    @ViewById
    ListView drawerList;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.actv_drawer);
        ViewGroup content = (ViewGroup) findViewById(R.id.content);
        content.addView(View.inflate(this, layoutResID, null));
    }

    @AfterViews
    public void initBase() {
        drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, R.drawable.ic_drawer, 0,0);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerList.setOnItemClickListener(this);
        setupDrawerList(drawerList);
    }

    protected abstract void setupDrawerList(ListView list);

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public ListView getListMenu() {
        return drawerList;
    }

    public void setListAdapter(ListAdapter adapter) {
        drawerList.setAdapter(adapter);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        selectItem(position);
    }

    public abstract void selectItem(int position);
}