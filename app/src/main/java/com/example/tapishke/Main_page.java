package com.example.tapishke;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.tapishke.ui.home.HomeFragment;
import com.example.tapishke.ui.info.InfoFragment;
import com.example.tapishke.ui.order.OrderFragment;
import com.example.tapishke.ui.profile.ProfileFragment;
import com.example.tapishke.ui.search.SearchFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Main_page extends AppCompatActivity {
    //Initialize variable
    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_page);

            //Assign variable
        bottomNavigation = findViewById(R.id.bottom_navigation);

        //Add menu item
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_search_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_person_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_baseline_local_dining_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(5,R.drawable.ic_baseline_info_24));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener(){

            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                //Initialize fragment
                Fragment fragment = null;

                switch(item.getId()){
                    case 1:
                        //when id is 1
                        //Initialize notification fragment
                      fragment = new HomeFragment();
                        break;
                    case 2:
                        fragment= new SearchFragment();
                        break;
                    case 3:
                        fragment = new ProfileFragment();
                        break;
                    case 4:
                        fragment = new OrderFragment();
                        break;
                    case 5:
                        fragment = new InfoFragment();
                        break;
                }
                //Load fragment
                loadFragment(fragment);
            }
        });

        //Set notification count
        bottomNavigation.setCount(1,"10");
        //Set home fragment initially selected
        bottomNavigation.show(2,true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //Display toast
                Toast.makeText(getApplicationContext(), "You clicked" + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener(){
            @Override
                    public void onReselectItem(MeowBottomNavigation.Model item){
                    //display toast
                Toast.makeText(getApplicationContext(), "You Reselected"+item.getId(),Toast.LENGTH_SHORT).show();
            }

        });

    }
    private void  loadFragment(Fragment fragment){
        //Replace fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_layout, fragment)
                .commit();
    }

}