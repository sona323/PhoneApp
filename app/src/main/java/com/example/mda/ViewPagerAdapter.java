package com.example.mda;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new KeypadFragment();
            case 1:
                return new RecentFragment();
            case 2:
                return new FavoritesFragment();
            case 3:
                return new ContactsFragment();
            default:
                return new KeypadFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;  // Total 4 tabs
    }
}
