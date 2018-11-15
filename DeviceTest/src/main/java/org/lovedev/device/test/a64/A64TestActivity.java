package org.lovedev.device.test.a64;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.shine.timingboot.TimingBootUtils;

import org.lovedev.device.test.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class A64TestActivity extends Activity {

    private static final String TAG = "A64TestActivity";
    private TimePickerView pvTime;
    private Disposable mDisposable;

    @Override
    @SuppressLint("CheckResult")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a64_test);
        initTimePicker();

        final Button inputTime = findViewById(R.id.et_format);
        inputTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.show(v);
            }
        });
        findViewById(R.id.btn_format).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                Date date = null;
                try {
                    date = format.parse(inputTime.getText().toString());
                    ((AlarmManager) getSystemService(Context.ALARM_SERVICE)).setTime(date.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.btn_current_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date(System.currentTimeMillis());
                Toast.makeText(A64TestActivity.this, getTime(date), Toast.LENGTH_SHORT).show();
            }
        });

        final EditText rebootTime = findViewById(R.id.et_reboot);
        findViewById(R.id.btn_reboot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time = Integer.parseInt(rebootTime.getText().toString());
                long currentTimeMillis = System.currentTimeMillis();
                open(currentTimeMillis + time * 60 * 1000);
                Flowable.timer(300, TimeUnit.MILLISECONDS)
                        //                        .observeOn(Schedulers.io())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                Intent intent = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
                                intent.putExtra("android.intent.extra.KEY_CONFIRM", false);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                System.exit(0);
                            }
                        });

            }
        });

        final Button openTimBtn = findViewById(R.id.et_reboot3);
        final Button shutdownTimeBtn = findViewById(R.id.et_reboot2);
        openTimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.show(v);
            }
        });

        shutdownTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.show(v);
            }
        });

        findViewById(R.id.et_reboot4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String openTime = openTimBtn.getText().toString();
                final String shutdownTime = shutdownTimeBtn.getText().toString();
                mDisposable = Flowable.interval(0, 1, TimeUnit.SECONDS)
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                String currentTime = getTime(new Date(System.currentTimeMillis()));
                                if (currentTime.equals(shutdownTime)) {
                                    Intent intent = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
                                    intent.putExtra("android.intent.extra.KEY_CONFIRM", false);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    System.exit(0);
                                }
                            }
                        });
                new TimingBootUtils().setRtcTime(openTime);
            }
        });
    }


    void open(Long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        String start = sdf.format(date);
        Log.d(TAG, "开机时间为" + start);
        int result = new TimingBootUtils().setRtcTime(start);
    }

    private void initTimePicker() {//Dialog 模式下，在底部弹出
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                String time = getTime(date);
                Toast.makeText(A64TestActivity.this, time, Toast.LENGTH_SHORT).show();
                Log.i("pvTime", "onTimeSelect");
                Button btn = (Button) v;
                btn.setText(time);
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setType(new boolean[]{true, true, true, true, true, true})
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return format.format(date);
    }
}
