package com.bibta.miti;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * The start-up activity.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // setTheme(R.style.AlternateAppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup calendar pages

        ViewPager viewPager = (ViewPager)findViewById(R.id.calendarPager);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(new CalendarPagerAdapter(getSupportFragmentManager()));

        Date today = new Date(Calendar.getInstance()).convertToNepali();
        viewPager.setCurrentItem(
                (today.year - DateUtils.startNepaliYear) * 12 + (today.month - 1)
        );


        // Set today's date at today panel

        LinearLayout todayLayout = (LinearLayout)findViewById(R.id.today);
        TextView todayNepaliDate = (TextView)todayLayout.findViewById(R.id.today_date_nepali);
        TextView todayNepaliMonthYear =
                (TextView)todayLayout.findViewById(R.id.today_month_year_nepali);
        TextView todayEnglishDate = (TextView)todayLayout.findViewById(R.id.today_date_english);

        if (todayNepaliDate != null)
            todayNepaliDate.setText(NepaliTranslator.getNumber(today.day+""));

        if (todayNepaliMonthYear != null) {
            String nepali =  NepaliTranslator.getMonth(today.month) + ". "
                    + NepaliTranslator.getNumber(today.year + "");
            todayNepaliMonthYear.setText(nepali);
        }

        if (todayEnglishDate != null) {
            String english = new SimpleDateFormat("d MMMM, yyyy", Locale.US)
                    .format(Calendar.getInstance().getTime());
            todayEnglishDate.setText(english);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
