package se.grupp1.antonsskafferi;import android.content.Intent;import android.os.Bundle;import android.view.Menu;import android.view.MenuInflater;import android.view.MenuItem;import androidx.appcompat.app.AppCompatActivity;import androidx.navigation.NavController;import androidx.navigation.Navigation;import androidx.navigation.ui.AppBarConfiguration;import androidx.navigation.ui.NavigationUI;import com.google.android.material.bottomnavigation.BottomNavigationView;public class MainActivity extends AppCompatActivity{    NavController navController;    @Override    protected void onCreate(Bundle savedInstanceState)    {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_main);        BottomNavigationView navView = findViewById(R.id.nav_view);        // Passing each menu ID as a set of Ids because each        // menu should be considered as top level destinations.        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(                R.id.navigation_schedule)                .build();        navController = Navigation.findNavController(this, R.id.nav_host_fragment);        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);        NavigationUI.setupWithNavController(navView, navController);        /*        findViewById(R.id.bookingButton).setOnClickListener(new View.OnClickListener()        {            @Override            public void onClick(View v)            {                startActivity(new Intent(MainActivity.this, BookingActivity.class));            }        });        findViewById(R.id.scheduleButton).setOnClickListener(new View.OnClickListener()        {            @Override            public void onClick(View v)            {                startActivity(new Intent(MainActivity.this, ScheduleFragment.class));            }        });        if(LoginActivity.isAdmin())        {            findViewById(R.id.adminButton).setVisibility(View.VISIBLE);            findViewById(R.id.adminButton).setOnClickListener(new View.OnClickListener() {                @Override                public void onClick(View v) {                    startActivity(new Intent(MainActivity.this, AdminFragment.class));                }            });        }        else        {            findViewById(R.id.adminButton).setVisibility(View.GONE);        }*/    }    public boolean onOptionsItemSelected(MenuItem item)    {        navController.navigateUp();        return true;    }    public void logout()    {        startActivity(new Intent(MainActivity.this, LoginActivity.class));    }}