package cifer.com.java_use.Activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.ArrayList;
import cifer.com.java_use.R;
import cifer.com.java_use.fragment.CourseFragment;
import cifer.com.java_use.fragment.HomeFragment;
import cifer.com.java_use.fragment.MineFragment;
import cifer.com.java_use.fragment.NoteFragment;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener,View.OnClickListener{
    private HomeFragment homeFragment;
    private CourseFragment courseFragment;
    private NoteFragment noteFragment;
    private MineFragment mineFragment;

    private RadioGroup radioGroup;
    private TextView titletext;
    private ViewGroup toolbar;
    private FrameLayout frameLayout;

    private int mCurrentTab;
    private final static int TAB_HOME = 0;
    private final static int TAB_COURSE = 1;
    private final static int TAB_NOTE = 2;
    private final static int TAB_MINE = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        titletext = (TextView)findViewById(R.id.title);
        toolbar = (ViewGroup)findViewById(R.id.toolbar);

        handlerDrawableTop(0);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        setTabCheckedById(checkedId);
    }


    public void setTabCheckedById(int checkedId) {
        switch(checkedId){
            case R.id.home_frag :
                toolbar.setVisibility(View.VISIBLE);
                titletext.setText(R.string.hometitle) ;
                mCurrentTab = TAB_HOME;
                FragmentClick(TAB_HOME);
                handlerDrawableTop(TAB_HOME) ;
                break ;
            case R.id.course_frag :
                toolbar.setVisibility(View.VISIBLE);
                titletext.setText(R.string.coursetitle) ;
                FragmentClick(TAB_COURSE);
                mCurrentTab = TAB_COURSE;
                handlerDrawableTop(TAB_COURSE) ;
                break ;
            case R.id.note_frag :
                toolbar.setVisibility(View.VISIBLE);
                titletext.setText(R.string.notetitle) ;
                FragmentClick(TAB_NOTE);
                mCurrentTab = TAB_NOTE;
                handlerDrawableTop(TAB_NOTE) ;
                break ;
            case R.id.mine_frag :
                toolbar.setVisibility(View.INVISIBLE);
                mCurrentTab =TAB_MINE;
                handlerDrawableTop(TAB_MINE) ;
                FragmentClick(TAB_MINE);
                break ;
        }
    }

    private void FragmentClick(int position){
        android.support.v4.app.FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (position){
            case 0:
                if(homeFragment == null){
                    homeFragment = new HomeFragment();
                }
                transaction.replace(R.id.main_frame,homeFragment);
                break;
            case 1:
                if(courseFragment == null){
                    courseFragment = new CourseFragment();
                }
                transaction.replace(R.id.main_frame,courseFragment);
                break;
            case 2:
                if(noteFragment == null){
                    noteFragment = new NoteFragment();
                }
                transaction.replace(R.id.main_frame,noteFragment);
                break;
            case 3:
                if(mineFragment == null){
                    mineFragment = new MineFragment();
                }
                transaction.replace(R.id.main_frame,mineFragment);
                break;
        }

        transaction.commit();
    }

    private static final int[] selectedDrawableIds = {R.drawable.main_page_fill,R.drawable.course_fill,
            R.drawable.note_fill  ,R.drawable.person_center_fill} ;

    private static final int[] normalDrawableIds = {R.drawable.main_page ,R.drawable.course,
            R.drawable.note ,R.drawable.person_center } ;

    private void handlerDrawableTop(int position){
        mCurrentTab = position ;
        //mViewPager.setCurrentItem(position);
        Drawable drawable = getResources().getDrawable(selectedDrawableIds[position]);
        drawable.setBounds(0,0,10,5);
        resetDrawableTop() ;
        ((RadioButton) radioGroup.getChildAt(position)).setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null) ;
    }

    private void resetDrawableTop(){
        for (int i = 0; i < 4; i++) {
            Drawable drawable = getResources().getDrawable(normalDrawableIds[i]);
            ((RadioButton) radioGroup.getChildAt(i)).setCompoundDrawablesWithIntrinsicBounds(null,
                    getResources().getDrawable(normalDrawableIds[i]), null, null) ;
        }
    }
}
