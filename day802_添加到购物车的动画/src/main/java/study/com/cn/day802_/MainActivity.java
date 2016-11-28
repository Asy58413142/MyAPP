package study.com.cn.day802_;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView cart;
    private String[] images;
    private PathMeasure pathMeasure;
    private RelativeLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            images = getAssets().list("images");
        } catch (IOException e) {
            e.printStackTrace();
        }

        parent = (RelativeLayout) findViewById(R.id.parent);
        cart = (ImageView) findViewById(R.id.iv_cart);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(new recyclerViewAdapter());

    }

    public void startAddCartAnim(final ImageView imageView){

        final ImageView view = new ImageView(MainActivity.this);

        view.setImageDrawable(imageView.getDrawable());

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(60,60);

        parent.addView(view,layoutParams);


        int[] iamgeLocation = new int[2];
        imageView.getLocationInWindow(iamgeLocation);

        final int[] cartLocation = new int[2];
        final float [] currentLocation = new float[2];
        cart.getLocationInWindow(cartLocation);


        float startX = iamgeLocation[0];
        float startY =  iamgeLocation[1]-imageView.getHeight()/2;

        float toX = cartLocation[0] + cart.getWidth() / 2;
        float toY = cartLocation[1];

        Path path = new Path();
        path.moveTo(startX, startY);
        path.quadTo((startX + toX) / 2, startY, toX, toY);

        pathMeasure = new PathMeasure(path, false);

        final ValueAnimator valueAnimator =ValueAnimator.ofFloat(0,pathMeasure.getLength());
               valueAnimator .setDuration(2000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float animatedValue = (float) animation.getAnimatedValue();

                pathMeasure.getPosTan(animatedValue, currentLocation, null);

                view.setTranslationX(currentLocation[0]);
                view.setTranslationY(currentLocation[1]);
            }
        });
//        valueAnimator.start();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view,"hehhe",1, 0);
        objectAnimator .setDuration(2000);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                view.setScaleX(animatedValue);
                view.setScaleY(animatedValue);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(valueAnimator,objectAnimator);
        animatorSet.start();

    }

    class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewHolder> {



        @Override
        public recyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item, null, false);

            return new recyclerViewHolder(view, new onClickListener() {
                @Override
                public void onImageViewClickListener(int position,ImageView imageView) {

                    startAddCartAnim(imageView);
                }
            });
        }

        @Override
        public void onBindViewHolder(recyclerViewHolder holder, int position) {
            try {
                InputStream in = MainActivity.this.getAssets().open("images/" + images[position]);

                Bitmap bitmap = BitmapFactory.decodeStream(in);
                holder.image.setImageBitmap(bitmap);
                holder.text.setText(images[position]);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public int getItemCount() {
            return images.length;
        }
    }



    class recyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView text;
        private final ImageView image;
        private onClickListener onClickListener;
        public recyclerViewHolder(View itemView,onClickListener onClickListener) {
            super(itemView);
            this.onClickListener = onClickListener;
            text = (TextView) itemView.findViewById(R.id.text);

            image = (ImageView) itemView.findViewById(R.id.image);

            image.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (v == image) {
                onClickListener.onImageViewClickListener(getLayoutPosition(),image);
            }
        }
    }


    interface onClickListener{
        void onImageViewClickListener(int position,ImageView imageView);
    }
}
