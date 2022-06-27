package com.example.gbwhastapp.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.gbwhastapp.Fragments.callFragment;
import com.example.gbwhastapp.Fragments.chatFragment;
import com.example.gbwhastapp.Fragments.statusFragment;

public class FragmentsAdapter extends FragmentPagerAdapter {
    public FragmentsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: return new chatFragment();
            case 1: return new statusFragment();
            case 2: return new callFragment();
            default: return new chatFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position){
        String title =null;
        if(position==0)
        {
            title = "CHATS";

        }
        if(position==1)
        {
            title = "STATUS";

        }
        if(position==2)
        {
            title = "CALLS";

        }
        return title;
    }

}
