package ro.pub.cs.systems.eim.myapplicationf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

import ro.pub.cs.systems.eim.myapplicationf.fragments.ContactFragment;
import ro.pub.cs.systems.eim.myapplicationf.fragments.ContulMeuFragment;
import ro.pub.cs.systems.eim.myapplicationf.fragments.DespreFragment;
import ro.pub.cs.systems.eim.myapplicationf.fragments.FacturiFragment;
import ro.pub.cs.systems.eim.myapplicationf.fragments.IndexFragment;
import ro.pub.cs.systems.eim.myapplicationf.fragments.MainPageFragment;
import ro.pub.cs.systems.eim.myapplicationf.fragments.PuncteFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public FragmentManager fragmentManager;
    private DrawerLayout drawer;
    public NavigationView navigationView;
    public IndexFragment indexFragment;
    public FacturiFragment facturiFragment;
    public ContulMeuFragment contulMeuFragment;
    public PuncteFragment puncteFragment;
    public ContactFragment contactFragment;
    public DespreFragment despreFragment;

    private Button indexButton;
    private Button facturiButton;
    private Button contulMeuButton;
    private Button puncteButton;
    private Button contactButton;
    private Button logoutButton;

    public String jwtTokenCode = "";

    private MainPageFragment mainPageFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jwtTokenCode = getIntent().getExtras().getString("jwtTokenCode");

        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.Open,R.string.Close);
        drawer.addDrawerListener(toggle);

        mainPageFragment = new MainPageFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mainPageFragment,MainPageFragment.class.getSimpleName()).commit();


        indexFragment = new IndexFragment();
        facturiFragment = new FacturiFragment();
        contulMeuFragment = new ContulMeuFragment();
        puncteFragment = new PuncteFragment();
        contactFragment = new ContactFragment();
        despreFragment = new DespreFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        while(getSupportFragmentManager().getBackStackEntryCount() >= 1) {
            Log.i("entries",String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));
            getSupportFragmentManager().popBackStackImmediate();
            Log.i("entries",String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    public void onBackPressed() {
        super.onBackPressed();

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }
    }

    public String getJwtTokenCode() {
        return jwtTokenCode;
    }

    public void setJwtTokenCode(String jwtTokenCode) {
        this.jwtTokenCode = jwtTokenCode;
    }
}
