package com.lenovo.way.touchdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GestureDetector mGestureDetector;
    private TextView mTextView;
    private String TAG = "TouchDemo";
    private float startNum = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView)findViewById(R.id.textview);
    }

    @Override
    protected void onResume() {
        mGestureDetector = new GestureDetector(new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                final double FLING_MIN_DISTANCE = 0.5;
                final double FLING_MIN_VELOCITY = 0.5;
                if (e1.getY() - e2.getY() > FLING_MIN_DISTANCE && Math.abs(distanceY) > FLING_MIN_VELOCITY){
                    Log.e(TAG,"up ~");
                    if (startNum <= 8.9){
                        startNum += 0.1 ;
                        mTextView.setText(" " + startNum);
                    }else {
                        // Toast.makeText(MainActivity.this,"放到最大啦！",Toast.LENGTH_SHORT).show();
                    }
                }
                if (e1.getY() - e2.getY() < FLING_MIN_DISTANCE && Math.abs(distanceY) > FLING_MIN_VELOCITY){
                    Log.e(TAG,"down !");
                    if (startNum >= -8.9){
                        startNum -= 0.1 ;
                        mTextView.setText(" " + startNum);
                    }else {
                        // Toast.makeText(MainActivity.this,"缩到最小了……",Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return true;
            }
        });

        super.onResume();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = mGestureDetector.onTouchEvent(event);
        if (!result){
            if (event.getAction() == MotionEvent.ACTION_UP){
                // getVideoInfosfromPath(filePath);
            }
            result = super.onTouchEvent(event);
        }
        return result;
    }
}
