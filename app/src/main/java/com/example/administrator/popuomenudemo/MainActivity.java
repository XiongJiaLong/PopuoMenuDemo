package com.example.administrator.popuomenudemo;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    Button button;
    PopupWindow popupWindow;
    LayoutInflater inflater;
    View view;
    GirlsFragment girlsFragment;
    BoysFragment boysFragment;
    ChildrenFragment childrenFragment;
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.mToolbar);
        setSupportActionBar(toolbar);
        button = (Button) findViewById(R.id.btn_change);
        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        if (boysFragment == null){
            boysFragment = new BoysFragment();
        }
        fragmentTransaction.add(R.id.container,boysFragment).show(boysFragment).commit();
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String s = ((Button)view).getText()+"";
        switch (view.getId()){
            case R.id.btn_change:
                break;
            case R.id.boys:
                break;
            case R.id.girls:
                break;
            case R.id.children:
                break;
        }
        if (popupWindow != null && popupWindow.isShowing()){
            initFrameLayout(s);
        }
        button.setText(s);
        openClosePopupWindow();
        if (popupWindow != null){
            backgroundAlpha();
        }
    }

    private void initMenu(){
        Button btn_boys;
        Button btn_childrens;
        Button btn_girls;
        view = inflater.inflate(R.layout.my_popup_window,null);
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK){
                    popupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });

        btn_boys = (Button) view.findViewById(R.id.boys);
        btn_childrens = (Button) view.findViewById(R.id.children);
        btn_girls = (Button) view.findViewById(R.id.girls);
        btn_boys.setOnClickListener(this);
        btn_childrens.setOnClickListener(this);
        btn_girls.setOnClickListener(this);
    }

    private void openClosePopupWindow(){
        if (popupWindow == null) {
            initMenu();
            popupWindow.showAsDropDown(button,0,0);
        }else {
            if (popupWindow.isShowing()){
                popupWindow.dismiss();
            }else {
                popupWindow.showAsDropDown(button,0,0);
            }
        }
    }

    private void backgroundAlpha(){
        WindowManager.LayoutParams attributes = this.getWindow().getAttributes();
        if (popupWindow.isShowing()){
            attributes.alpha = 0.7f;
        }else {
            attributes.alpha = 1.0f;
        }
        this.getWindow().setAttributes(attributes);
    }

    private void initFrameLayout(String s){
        FragmentTransaction ft = fm.beginTransaction();
        switch (s){
            case "男生":
                if (boysFragment == null) {
                    boysFragment = new BoysFragment();
                    ft.add(R.id.container,boysFragment);
                }
                ft.replace(R.id.container,boysFragment).commit();
                break;
            case "女生":
                if (girlsFragment == null){
                    girlsFragment = new GirlsFragment();
                    ft.add(R.id.container,girlsFragment);
                }
                ft.replace(R.id.container,girlsFragment).commit();
                break;
            case "小孩":
                if (childrenFragment == null){
                    childrenFragment = new ChildrenFragment();
                    ft.add(R.id.container,childrenFragment);
                }
                ft.replace(R.id.container,childrenFragment).commit();
                break;
        }
    }
}
