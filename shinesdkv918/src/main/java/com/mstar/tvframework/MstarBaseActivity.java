
package com.mstar.tvframework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;

import com.mstar.android.MKeyEvent;


public class MstarBaseActivity extends Activity implements MstarBaseInterface {

    private int delayMillis = 10000;

    private int delayMessage = 88888888;

    private Intent intent;

    protected boolean alwaysTimeout = false;

    private boolean isGoingToBeClosed = true;

    private Handler timerHander = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == delayMessage) {
                onTimeOut();
            }

        }
    };

    // 子类如果要决定这个activity被覆盖的话 就传入true 或者是不传入调用 因为默认就是true
    // 给子类一个选择的权利。否则有别的activity盖住之后就会被杀掉。
    protected void isGoingToBeClosed(boolean flag) {
        this.isGoingToBeClosed = flag;
    }

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        timerHander.sendEmptyMessageDelayed(delayMessage, delayMillis);
        // 在onResume之中恢复状态
        isGoingToBeClosed = true;
    }

    @Override
    public void onUserInteraction() {
        // TODO Auto-generated method stub
        super.onUserInteraction();
        if (timerHander.hasMessages(delayMessage)) {
            timerHander.removeMessages(delayMessage);
            timerHander.sendEmptyMessageDelayed(delayMessage, delayMillis);
        }
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        timerHander.removeMessages(delayMessage);
        if (isGoingToBeClosed) {
            finish();
        }
        super.onPause();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    public void onTimeOut() {
        // TODO Auto-generated method stub
        if (alwaysTimeout)
            timerHander.sendEmptyMessageDelayed(delayMessage, delayMillis);
    }

    @Override
    public boolean onKeyDown(int arg0, KeyEvent arg1) {
        // TODO Auto-generated method stub
        if (arg0 == MKeyEvent.KEYCODE_LIST) {
            finish();
            return true;
        }
        return super.onKeyDown(arg0, arg1);
    }

}
