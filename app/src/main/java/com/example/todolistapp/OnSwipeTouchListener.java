package com.example.todolistapp;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

    public abstract class OnSwipeTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector;

        public OnSwipeTouchListener(Context context) {
            gestureDetector = new GestureDetector(context, new GestureListener());
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                onClick();
                return super.onSingleTapUp(e);
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight(getPosition());
                            } else {
                                onSwipeLeft(getPosition());
                            }
                            result = true;
                        }
                    } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom(getPosition());
                        } else {
                            onSwipeTop(getPosition());
                        }
                        result = true;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        }

        public abstract void onSwipeRight(int position);

        public abstract void onSwipeLeft(int position);

        public abstract void onSwipeTop(int position);

        public abstract void onSwipeBottom(int position);

        public abstract void onClick();

        public abstract int getPosition();
    }


