package com.example.fbstorageandgesture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GestureDetectorCompat mGestureDetectorCompat;
    Toolbar toolbar;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGestureDetectorCompat=new GestureDetectorCompat(this,new GestureListener());
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView=findViewById(R.id.textView);

    }

    private class GestureListener implements GestureDetector.OnGestureListener {

        int threshold=100;
        int velocity_threshold=100;
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float xdiff=e2.getX()-e1.getX();
            float ydiff=e2.getY()-e1.getY();

            if (Math.abs(xdiff)>Math.abs(ydiff)){
             if (Math.abs(xdiff)>threshold && Math.abs(velocityX)>velocity_threshold){
                 if (xdiff>0){
                     toolbar.setTitle("Swipe Right");
                 }else{
                     toolbar.setTitle("Swipe Left");
                 }
             }
            }else{
                if (Math.abs(ydiff)>Math.abs(xdiff)){
                    if (Math.abs(ydiff)>threshold && Math.abs(velocityY)>velocity_threshold){
                        if (ydiff>0){
                            toolbar.setTitle("Swipe Down");
                        }else {
                            toolbar.setTitle("Swipe Up");
                        }
                    }
                }
            }

            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}