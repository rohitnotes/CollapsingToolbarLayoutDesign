package collapsing.toolbar.layout.design;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Callback;
import java.util.ArrayList;

public class CollapsingToolbarLayoutActivity extends AppCompatActivity {

    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private ImageView hamburgerImageView;
    public static ActionBar actionbar;
    private ImageView collapseImageView;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private RecyclerView recyclerView;
    private Menu menu;

    private LinearLayoutManager linearLayoutManager;
    private ArrayList<ItemModel> itemModelArrayList;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar_layout);
        setupToolbar();
        intiView();
        setDataOnRecylerView();
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        actionbar = getSupportActionBar();
        assert actionbar != null;
        hamburgerIcon();
    }

    private void intiView()
    {
        appBarLayout= findViewById(R.id.app_bar_layout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
              toolbar.setBackgroundColor(changeAlpha(getResources().getColor(R.color.colorPrimary),Math.abs(verticalOffset*1.0f)/appBarLayout.getTotalScrollRange()));
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    showOption(R.id.action_email);
                } else if (isShow) {
                    isShow = false;
                    hideOption(R.id.action_email);
                }
            }
        });

        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            boolean isShow = false;
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if(state.equals(State.COLLAPSED))
                {
                   // toolbar.setBackgroundResource(R.drawable.custom_toolbar);
                    Toast.makeText(getApplicationContext(), "COLLAPSED", Toast.LENGTH_SHORT).show();
                    //isShow = false;
                  // hideOption(R.id.action_email);
                }
                else if (state.equals(State.EXPANDED))
                {
                    //toolbar.setBackgroundResource(R.color.transparent);
                    Toast.makeText(getApplicationContext(), "EXPANDED", Toast.LENGTH_SHORT).show();
                    /*isShow = true;
                    showOption(R.id.action_email);*/
                }
                else if ((state.equals(State.IDLE)))
                {
                   // isShow = true;
                    Toast.makeText(getApplicationContext(), "IDLE", Toast.LENGTH_SHORT).show();
                    /*isShow = false;
                    hideOption(R.id.action_email);*/
                }
            }
        });


        collapseImageView = findViewById(R.id.collapse_image_view);
        Picasso.with(this).load("https://uniqueandrocode.000webhostapp.com/hiren/primg/2.jpg").error(R.drawable.collapse_image_placeholder).into(collapseImageView, new Callback() {
            @Override
            public void onSuccess() {
                Log.d("TAG", "onSuccess");
            }

            @Override
            public void onError() {
                Toast.makeText(getApplicationContext(), "Collapse Image Loading Error", Toast.LENGTH_SHORT).show();
            }
        });

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        collapsingToolbarLayout.setTitle("Application Name");

        recyclerView = findViewById(R.id.recycler_view);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Send Email", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDataOnRecylerView()
    {
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        itemModelArrayList =new ArrayList<>();
        recyclerViewAdapter =new RecyclerViewAdapter(CollapsingToolbarLayoutActivity.this);
        recyclerView.setAdapter(recyclerViewAdapter);

        itemModelArrayList.add(new ItemModel("John","9:30 AM"));
        itemModelArrayList.add(new ItemModel("Rob","9:40 AM"));
        itemModelArrayList.add(new ItemModel("Peter","9:45 AM"));
        itemModelArrayList.add(new ItemModel("Jack","9:50 AM"));
        itemModelArrayList.add(new ItemModel("Bob","9:55 AM"));
        itemModelArrayList.add(new ItemModel("Sandy","10:00 AM"));
        itemModelArrayList.add(new ItemModel("Kate","10:05 AM"));
        itemModelArrayList.add(new ItemModel("Roger","10:15 AM"));
        itemModelArrayList.add(new ItemModel("Sid","10:20 AM"));
        itemModelArrayList.add(new ItemModel("Kora","10:25 AM"));
        itemModelArrayList.add(new ItemModel("Nick","10:30 AM"));
        itemModelArrayList.add(new ItemModel("Mia","10:40 AM"));
        itemModelArrayList.add(new ItemModel("Scott","10:45 AM"));

        recyclerViewAdapter.feedData(itemModelArrayList);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    /** Change color transparency based on percentage */
    public int changeAlpha(int color, float fraction)
    {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }

    private void hamburgerIcon()
    {
        hamburgerImageView=findViewById(R.id.hamburger_image_view);
        Animation rotate_clockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        Animation rotate_anticlockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        hamburgerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hamburgerImageView.startAnimation(rotate_clockwise);
            }
        });
        rotate_clockwise.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (hamburgerImageView.getRotation()==360)
                {
                    hamburgerImageView.setRotation(180);
                    hamburgerImageView.setImageResource(R.drawable.ic_arrow_back_white_24dp);
                }
                else if (hamburgerImageView.getRotation()==180)
                {
                    hamburgerImageView.setRotation(360);
                    hamburgerImageView.setImageResource(R.drawable.ic_menu_white_24dp);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        hideOption(R.id.action_email);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_email) {
            Toast.makeText(getApplicationContext(), "Send Email", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }
}

