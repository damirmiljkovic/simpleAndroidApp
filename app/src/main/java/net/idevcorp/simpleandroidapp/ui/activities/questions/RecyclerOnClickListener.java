package net.idevcorp.simpleandroidapp.ui.activities.questions;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

public class RecyclerOnClickListener implements RecyclerView.OnItemTouchListener {
    private OnItemClickListener itemClickListener;
    private GestureDetector gestureDetector;

    public interface OnItemClickListener{
        void onClick(View view, int position);
        void onLongPress(View view, int position);
    }
    public RecyclerOnClickListener(Context context, final RecyclerView recyclerView, final OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
        gestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View childLong = recyclerView.findChildViewUnder(e.getX(),e.getY());
                if (childLong != null && itemClickListener != null){
                    itemClickListener.onLongPress(childLong,recyclerView.getChildAdapterPosition(childLong));
                }
            }
        });
    }
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childShort = rv.findChildViewUnder(e.getX(),e.getY());
        if (childShort != null && itemClickListener != null && gestureDetector.onTouchEvent(e)){
            itemClickListener.onClick(childShort,rv.getChildAdapterPosition(childShort));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
// bilo bi mi logicnije da je ovdje sadrzaj onInterceptTouchEvent metode...
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
