package com.acomputerengineer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        rv.setLayoutManager(llm);

        ArrayList<String> alPost = new ArrayList<>();
        alPost.add("PICK IMAGE FROM GALLERY BEFORE AND AFTER KITKAT VERSION IN ANDROID(UPDATED)");
        alPost.add("HOW TO DISPLAY CANVAS ON IMAGEVIEW AND SAVE CANVAS AS BITMAP AND STORE IN SDCARD IN ANDROID");
        alPost.add("DRAW CIRCLE SHAPE IN IMAGEVIEW IN ANDROID");
        alPost.add("LIMIT NUMBER RANGE IN EDITTEXT USING INPUTFILTER IN ANDROID");

        PostAdapter adapter = new PostAdapter(alPost);
        rv.setAdapter(adapter);
    }

    public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

        ArrayList<String> alPost;

        PostAdapter(ArrayList<String> alPost) {
            this.alPost = alPost;
        }

        public class PostViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView tvName;

            public PostViewHolder(View itemView) {
                super(itemView);
                cv = (CardView) itemView.findViewById(R.id.cv);
                tvName = (TextView) itemView.findViewById(R.id.tv_name);

                cv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();

                        switch (position)   {
                            case 0:
                                Intent intentPickImage = new Intent(MainActivity.this, PickImageActivity.class);
                                startActivity(intentPickImage);
                                break;
                            case 1:
                                Intent intentCanvasDemo = new Intent(MainActivity.this, CanvasDemoActivity.class);
                                startActivity(intentCanvasDemo);
                                break;
                            case 2:
                                Intent intentCircleImageView = new Intent(MainActivity.this, CircleImageViewActivity.class);
                                startActivity(intentCircleImageView);
                                break;
                            case 3:
                                Intent intentLimitNumberRange = new Intent(MainActivity.this, LimitNumberRangeActivity.class);
                                startActivity(intentLimitNumberRange);
                                break;
                        }

                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return alPost.size();
        }

        @Override
        public PostViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_post, viewGroup, false);
            PostViewHolder pvh = new PostViewHolder(v);
            return pvh;
        }

        @Override
        public void onBindViewHolder(final PostViewHolder personViewHolder, int i) {
            personViewHolder.tvName.setText(String.valueOf(alPost.get(i)));
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }
    }
}
