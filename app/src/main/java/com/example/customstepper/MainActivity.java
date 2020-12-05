package com.example.customstepper;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    int progress = 0;

    int listSize = 10;

    @BindView(R.id.lyt_container)
    LinearLayout lytContainer;
    @BindView(R.id.btn_next)
    Button btnNext;
    LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
            1.0f
    );

    LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.MATCH_PARENT
    );
    @BindView(R.id.btn_back)
    Button btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        setUpprogressView(listSize);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (progress == listSize - 1)
                    return;
                progress = progress + 1;
                updateProgressView(true);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (progress < 1)
                    return;
//                progress = progress - 1;
                updateProgressView(false);
            }
        });
    }

    private void initProgressView() {

        View itemView = lytContainer.findViewById(progress);

        ImageView imageView = itemView.findViewById(R.id.image_placed);
        imageView.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
    }

    private void updateProgressView(boolean isNext) {
        Log.e("progress : ", progress + "");
        View itemView = lytContainer.findViewById(progress);
        ImageView imageView = itemView.findViewById(R.id.image_placed);

        View view = itemView.findViewById(R.id.line_preparing);

        if (isNext) {
            imageView.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else {
            imageView.setColorFilter(getResources().getColor(android.R.color.transparent), PorterDuff.Mode.SRC_ATOP);
            imageView.setImageResource(R.drawable.shape_round_outline_primary);
            view.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            progress = progress - 1;
        }

    }

    private void setUpprogressView(int size) {


        lytContainer.removeAllViews();
        for (int j = 0; j < size; j++) {
            if (j == 0) {
                lytContainer.addView(getprogressItemViewEnd(j));
            } else {
                lytContainer.addView(getprogressItemViewNormal(j));
            }
        }

        lytContainer.requestLayout();
        initProgressView();

    }


    private View getprogressItemViewNormal(int index) {
        View itemView = getLayoutInflater().inflate(R.layout.item_ptogress_normal, null, false);
        itemView.setId(index);
        itemView.setLayoutParams(param);
        return itemView;

    }

    private View getprogressItemViewEnd(int index) {
        View itemView = getLayoutInflater().inflate(R.layout.item_ptogress_end, null, false);
        itemView.setId(index);
        itemView.setLayoutParams(param2);
        return itemView;

    }

}