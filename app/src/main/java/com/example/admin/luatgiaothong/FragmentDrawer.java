package com.example.admin.luatgiaothong;

        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.content.pm.PackageManager;
        import android.graphics.Color;
        import android.net.Uri;
        import android.os.AsyncTask;
        import android.os.Build;
        import android.os.Bundle;
        import android.os.PowerManager;
        import android.preference.PreferenceManager;
        import android.provider.Settings;
        import android.provider.SyncStateContract;
        import android.support.v4.app.Fragment;
        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.ActionBarDrawerToggle;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.widget.SwitchCompat;
        import android.support.v7.widget.Toolbar;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.RelativeLayout;
        import android.widget.TextView;
        import android.widget.Toast;


        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.OutputStream;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.util.zip.ZipEntry;
        import java.util.zip.ZipInputStream;

public class FragmentDrawer extends Fragment {
    private static String TAG = FragmentDrawer.class.getSimpleName();
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View containerView;
    private static String[] titles = null;
    private FragmentDrawerListener drawerListener;
    MainActivity mainActivity;

    public FragmentDrawer() {

    }

    public void setDrawerListener(FragmentDrawerListener listener) {
        this.drawerListener = listener;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating view layout
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        // set menu Phat xe may
        final RelativeLayout menuXeMay =(RelativeLayout) layout.findViewById(R.id.menu_xemay);
        menuXeMay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuXeMay.setBackgroundColor(Color.parseColor("#FF6F00"));
                int id=2;
                mDrawerLayout.closeDrawers();
                Intent intent = new Intent(getActivity(),MainActivity.class);
                intent.putExtra("id",id);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        // set menu Phat o to
        final RelativeLayout menuOTo =(RelativeLayout) layout.findViewById(R.id.menu_car);
        menuOTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuOTo.setBackgroundColor(Color.parseColor("#FF6F00"));

                int id=1;
                mDrawerLayout.closeDrawers();
                Intent intent = new Intent(getActivity(),MainActivity.class);
                intent.putExtra("id",id);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

        });
        // set menu Phat xe tai
        final RelativeLayout menuXeTai =(RelativeLayout) layout.findViewById(R.id.menu_truck);
        menuXeTai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuXeTai.setBackgroundColor(Color.parseColor("#FF6F00"));

                int id=33;
                mDrawerLayout.closeDrawers();
                Intent intent = new Intent(getActivity(),MainActivity.class);
                intent.putExtra("id",id);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        // se menu phat xe khach
        final RelativeLayout menuXeKhach =(RelativeLayout) layout.findViewById(R.id.menu_bus);
        menuXeKhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuXeKhach.setBackgroundColor(Color.parseColor("#FF6F00"));
                int id=22;
                mDrawerLayout.closeDrawers();
                Intent intent = new Intent(getActivity(),MainActivity.class);
                intent.putExtra("id",id);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        // set menu bien bao
        final RelativeLayout menuBienBao =(RelativeLayout) layout.findViewById(R.id.menu_bien);
        menuBienBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuBienBao.setBackgroundColor(Color.parseColor("#FF6F00"));
                int id=4;
                mDrawerLayout.closeDrawers();
                Intent intent = new Intent(getActivity(),ActivityBienBao.class);
                intent.putExtra("id",id);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        // set menu duong day nóng
        final RelativeLayout menuDuongDay =(RelativeLayout) layout.findViewById(R.id.menu_duong_day);
        menuDuongDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuDuongDay.setBackgroundColor(Color.parseColor("#FF6F00"));
                int id=5;
                mDrawerLayout.closeDrawers();
                Intent intent = new Intent(getActivity(),ActivityDuongDayNong.class);
                intent.putExtra("id",id);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        //setUpClipboar(layout);

        LinearLayout menuRate = (LinearLayout)layout.findViewById(R.id.menu_rate);
        menuRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String appPackageName = getActivity().getPackageName();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id="
                                    + appPackageName)));
                }
            }
        });

        LinearLayout menuFB = (LinearLayout)layout.findViewById(R.id.menu_share_book);
        menuFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/sharer/sharer.php?u=https://play.google.com/store/apps/details?id="+ getActivity().getPackageName()));
                startActivity(browserIntent);
            }
        });

        LinearLayout menuStore = (LinearLayout)layout.findViewById(R.id.menu_store);
        menuStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String developerName =  "BK+Translate";
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:" + developerName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/developer?id=" + developerName)));
                }
            }
        });

        return layout;
    }

   /* SwitchCompat switchCompat;
    private void setUpClipboar(View v){
        LinearLayout llClipboard = (LinearLayout)v.findViewById(R.id.menu_clipboard);
        switchCompat = (SwitchCompat)v.findViewById(R.id.tv_clipboard);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        switchCompat.setChecked(sharedPreferences.getBoolean(Constants.IS_CLIPBOARD_ON, false));

        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchCompat.isChecked()){
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(getActivity())){
                        new AlertDialog.Builder(getActivity())
                                .setTitle("Thông báo")
                                .setMessage("Để sử dụng tính năng này bạn cấp cấp quyền cho ứng dụng ở phần cài đặt!")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent myIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                                        startActivity(myIntent);
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();



                    }
                    editor.putBoolean(Constants.IS_CLIPBOARD_ON, true).commit();
                    Intent intent = new Intent(getActivity(), ClipboadListenerService.class);
                    getActivity().startService(intent);

                } else {
                    editor.putBoolean(Constants.IS_CLIPBOARD_ON, false).commit();
                    Intent intent = new Intent(getActivity(), ClipboadListenerService.class);
                    getActivity().stopService(intent);
                }
            }
        });
    }*/

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(Build.VERSION.SDK_INT > 11) {
                    getActivity().invalidateOptionsMenu();
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if(Build.VERSION.SDK_INT > 11) {
                    getActivity().invalidateOptionsMenu();
                }
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);


            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    /*@Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        switchCompat.setChecked(sharedPreferences.getBoolean(SyncStateContract.Constants.IS_CLIPBOARD_ON, false));
    }*/

    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }


    public interface FragmentDrawerListener {
        public void onDrawerItemSelected(View view, int position);
    }
}
