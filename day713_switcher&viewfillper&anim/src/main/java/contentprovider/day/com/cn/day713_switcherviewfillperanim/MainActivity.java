package contentprovider.day.com.cn.day713_switcherviewfillperanim;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {
    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextSwitcher textSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher);
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {

                TextView textView = new TextView(MainActivity.this);
                textView.setTextColor(Color.BLUE);
                textView.setTextSize(30);
                return textView;
            }
        });


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textSwitcher.setInAnimation(MainActivity.this,R.anim.downtoup_in);
                textSwitcher.setOutAnimation(MainActivity.this,R.anim.downtoup_out);
                textSwitcher.setText(""+count++);
                Intent intent = new Intent(MainActivity.this, ImageSwitcherActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.righttoleft_in,R.anim.downtoup_out);//设置开启动画

            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textSwitcher.setInAnimation(MainActivity.this,R.anim.uptodown_in);
                textSwitcher.setOutAnimation(MainActivity.this,R.anim.uptodown_out);
                textSwitcher.setText(""+(count--<0?0:count));
            }
        });
    }
}
