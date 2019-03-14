package adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import pagerfrag.Business;
import pagerfrag.Entertainment;
import pagerfrag.Health;
import pagerfrag.Science;
import pagerfrag.Sports;
import pagerfrag.Tech;
import pagerfrag.Top;

public class NewsPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public NewsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new Top();
            case 1:
                return new Business();
            case 2:
                return new Entertainment();
            case 3:
                return new Health();
            case 4:
                return new Sports();
            case 5:
                return new Science();
            case 6:
                return new Tech();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 7;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return new String("Top");
            case 1:
                return new String("Business");
            case 2:
                return new String("Entertainment");
            case 3:
                return new String("Health");
            case 4:
                return new String("Sports");
            case 5:
                return new String("Science");
            case 6:
                return new String("Tech");
            default:
                return null;
        }
    }
}
