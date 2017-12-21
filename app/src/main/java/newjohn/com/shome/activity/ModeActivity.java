package newjohn.com.shome.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import newjohn.com.shome.R;

public class ModeActivity extends AppCompatActivity {
    @BindView(R.id.inhome)
    ImageView imageViewIn;
    @BindView(R.id.outside)
    ImageView imageViewOut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);
        ButterKnife.bind(this);

        imageViewIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ModeActivity.this,MainActivity.class);
                intent.putExtra("mode","in");
                startActivity(intent);
                finish();


            }
        });

        imageViewOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ModeActivity.this,MainActivity.class);
                intent.putExtra("mode","out");
                startActivity(intent);
                finish();
            }
        });

    }
}
