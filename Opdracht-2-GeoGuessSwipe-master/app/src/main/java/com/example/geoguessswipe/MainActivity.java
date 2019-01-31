package com.example.geoguessswipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    /**
     Binding
     */
    @BindView(R.id.descriptionTextView)
    TextView descriptionTextView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    /**
     Creating Adapter and List
     */
    private GeoImageAdapter geoImageAdapter;
    private ArrayList<GeoImage> geoImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Binding all views making use o the ButterKnife library.
        ButterKnife.bind(this);

        geoImages = new ArrayList<>();
        getDrawables();
        geoImageAdapter = new GeoImageAdapter(geoImages);

        recyclerView.setAdapter(geoImageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        createSwipe();
    }

    /**
      This method  gets all the necessary images by looking for the word 'img'
     */


    public void getDrawables(){
        Field[] drawablesFields = com.example.geoguessswipe.R.drawable.class.getFields();
        for (Field drawablesField : drawablesFields) {
            try {
                if (drawablesField.getName().contains("img")) {
                    if(drawablesField.getName().contains("yes")) {
                        geoImages.add(new GeoImage(getResources().getDrawable(drawablesField.getInt(null)), true));
                    } else if(drawablesField.getName().contains("no")) {
                        geoImages.add(new GeoImage(getResources().getDrawable(drawablesField.getInt(null)), false));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void createSwipe() {
        /** Adds touch helper to recyclerview to detect swipes
          An ItemTouchHelper enables touch behavior on each ViewHolder,
          callbacks are performed when an user swipes
        */

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                            target) {
                        return false;
                    }
                    /**
                      Called when a user swipes on a ViewHolder
                     */
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        //Get the index corresponding to the selected position
                        int position = (viewHolder.getAdapterPosition());
                        //check if image is from Europe or not and if it's swiped to the correct side
                        if(swipeDir == ItemTouchHelper.LEFT && geoImages.get(position).isEU()) {
                            //show notification when its true
                            Toast.makeText(getApplicationContext(),"Correct!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            //show notification when its false
                            Toast.makeText(getApplicationContext(),"Too bad!",
                                    Toast.LENGTH_SHORT).show();
                        }
                        //remove image from list
                        geoImages.remove(position);
                        //notify the adapter of the removed image
                        geoImageAdapter.notifyItemRemoved(position);
                    }
                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
