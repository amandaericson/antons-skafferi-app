package se.grupp1.antonsskafferi.activites;import android.content.Intent;import android.os.Bundle;import android.view.Menu;import android.view.MenuInflater;import android.view.MenuItem;import android.view.View;import android.widget.Button;import androidx.appcompat.app.AppCompatActivity;import androidx.navigation.NavController;import androidx.navigation.Navigation;import androidx.navigation.ui.AppBarConfiguration;import androidx.navigation.ui.NavigationUI;import com.google.android.material.bottomnavigation.BottomNavigationView;import se.grupp1.antonsskafferi.R;public class MainActivity extends AppCompatActivity{    NavController navController;    @Override    protected void onCreate(Bundle savedInstanceState)    {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_main);        BottomNavigationView navView = findViewById(R.id.nav_view);        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(                R.id.navigation_schedule)                .build();        navController = Navigation.findNavController(this, R.id.nav_host_fragment);        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);        NavigationUI.setupWithNavController(navView, navController);    }    public boolean onOptionsItemSelected(MenuItem item)    {        navController.navigateUp();        return true;    }}