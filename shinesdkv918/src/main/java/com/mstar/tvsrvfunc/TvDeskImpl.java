/**
 * MStar Software
 * Copyright (c) 2011 - 2012 MStar Semiconductor, Inc. All rights reserved.
 *
 * All software, firmware and related documentation herein (“MStar Software”) are
 * intellectual property of MStar Semiconductor, Inc. (“MStar”) and protected by
 * law, including, but not limited to, copyright law and international treaties.
 * Any use, modification, reproduction, retransmission, or republication of all
 * or part of MStar Software is expressly prohibited, unless prior written
 * permission has been granted by MStar.
 *
 * By accessing, browsing and/or using MStar Software, you acknowledge that you
 * have read, understood, and agree, to be bound by below terms (“Terms”) and to
 * comply with all applicable laws and regulations:
 *
 * 1. MStar shall retain any and all right, ownership and interest to MStar
 * Software and any modification/derivatives thereof.  No right, ownership,
 * or interest to MStar Software and any modification/derivatives thereof is
 * transferred to you under Terms.
 *
 * 2. You understand that MStar Software might include, incorporate or be supplied
 * together with third party’s software and the use of MStar Software may require
 * additional licenses from third parties.  Therefore, you hereby agree it is your
 * sole responsibility to separately obtain any and all third party right and
 * license necessary for your use of such third party’s software.
 *
 * 3. MStar Software and any modification/derivatives thereof shall be deemed as
 * MStar’s confidential information and you agree to keep MStar’s confidential
 * information in strictest confidence and not disclose to any third party.
 *
 * 4. MStar Software is provided on an “AS IS” basis without warranties of any kind.
 * Any warranties are hereby expressly disclaimed by MStar, including without
 * limitation, any warranties of merchantability, non-infringement of intellectual
 * property rights, fitness for a particular purpose, error free and in conformity
 * with any international standard.  You agree to waive any claim against MStar for
 * any loss, damage, cost or expense that you may incur related to your use of MStar
 * Software.  In no event shall MStar be liable for any direct, indirect, incidental
 * or consequential damages, including without limitation, lost of profit or revenues,
 * lost or damage of data, and unauthorized system use.  You agree that this Section 4
 * shall still apply without being affected even if MStar Software has been modified
 * by MStar in accordance with your request or instruction for your use, except
 * otherwise agreed by both parties in writing.
 *
 * 5. If requested, MStar may from time to time provide technical supports or
 * services in relation with MStar Software to you for your use of MStar Software
 * in conjunction with your or your customer’s product (“Services”).  You understand
 * and agree that, except otherwise agreed by both parties in writing, Services are
 * provided on an “AS IS” basis and the warranty disclaimer set forth in Section 4
 * above shall apply.
 *
 * 6. Nothing contained herein shall be construed as by implication, estoppels or
 * otherwise: (a) conferring any license or right to use MStar name, trademark,
 * service mark, symbol or any other identification; (b) obligating MStar or any
 * of its affiliates to furnish any person, including without limitation, you and
 * your customers, any assistance of any kind whatsoever, or any information; or
 * (c) conferring any license or right under any intellectual property right.
 *
 * 7. These terms shall be governed by and construed in accordance with the laws
 * of Taiwan, R.O.C., excluding its conflict of law rules.  Any and all dispute
 * arising out hereof or related hereto shall be finally settled by arbitration
 * referred to the Chinese Arbitration Association, Taipei in accordance with
 * the ROC Arbitration Law and the Arbitration Rules of the Association by three (3)
 * arbitrators appointed in accordance with the said Rules.  The place of
 * arbitration shall be in Taipei, Taiwan and the language shall be English.
 * The arbitration award shall be final and binding to both parties.
 */

package com.mstar.tvsrvfunc;

import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.mstar.tvsettingservice.CaDesk;
import com.mstar.tvsettingservice.ChannelDesk;
import com.mstar.tvsettingservice.CiDesk;
import com.mstar.tvsettingservice.CommonDesk;
import com.mstar.tvsettingservice.DataBaseDesk;
import com.mstar.tvsettingservice.DemoDesk;
import com.mstar.tvsettingservice.EpgDesk;
import com.mstar.tvsettingservice.FactoryDesk;
import com.mstar.tvsettingservice.PictureDesk;
import com.mstar.tvsettingservice.PvrDesk;
import com.mstar.tvsettingservice.S3DDesk;
import com.mstar.tvsettingservice.SettingDesk;
import com.mstar.tvsettingservice.SoundDesk;
import com.mstar.tvsettingservice.TvDesk;
import com.mstar.tvsettingservice.TvDeskProvider;

import java.util.Timer;
import java.util.TimerTask;

public class TvDeskImpl extends Service implements TvDesk {
    // private Binder tvServiceBinder = new TvServiceBinder();
    private TvServiceBinder tvServiceBinder;

    final static int FIXVALUE = 10;

    static int siTestValue;

    int iTestValue;

    private CommonDesk com = null;

    public TvDeskImpl() {
        com = CommonDeskImpl.getInstance();
        com.printfE("TvService", "TvServiceImpl constructor!!");
        new Thread(new Runnable() {
            @Override
            public void run() {
                com.printfE("TvServiceImpl.....");
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // System.out.println("start activity.....");
                // Intent actIntent = new Intent("com.mstar.view.menu");
                // actIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                // startActivity(actIntent);
            }
        }).start();
        com.printfE("start timer...");
        Timer tMainTask = new Timer();
        tMainTask.schedule(new TimerTask() {
            // int i = 0;
            @Override
            public void run() {
                try {
                    // com.printfE("time task:" + i);
                    // i++;
                } catch (Exception e) {
                }
            }
        }, 1000, 1000);
    }

    @Override
    public int test(int a) {
        // TODO Auto-generated method stub
        com.printfE("TvService", "test!!");
        return 0;
    }

    @Override
    public void showinfo() {
        // TODO Auto-generated method stub
        com.printfE("TvService", "This is mock class!!!");
        selffunc1();
        selffunc2();
    }

    private void selffunc1() {
        com.printfE("TvService", "selffunc1:hello!!");
        // Nothings to do
    }

    protected void selffunc2() {
        com.printfE("TvService", "selffunc2:hello!!");
        // Nothings to do
    }

    @Override
    public void onCreate() {
        tvServiceBinder = new TvServiceBinder();
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return tvServiceBinder;
    }

    @Override
    public void onDestroy() {
        Log.d("service", "onDestroy");
        super.onDestroy();
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, CursorFactory factory) {
        // TODO Auto-generated method stub
        return super.openOrCreateDatabase("data/data/mstar.tvsetting.factory/databases", mode,
                factory);
    }

    public class TvServiceBinder extends Binder implements TvDeskProvider {
        CommonDesk comManager = null;

        PictureDesk pictureManager = null;

        DataBaseDesk dataBaseManager = null;

        SettingDesk settingManager = null;

        ChannelDesk channelManager = null;

        SoundDesk soundManager = null;

        S3DDesk s3dManager = null;

        DemoDesk demoManager = null;

        FactoryDesk factoryManager = null;

        EpgDesk epgManager = null;

        PvrDesk pvrManager = null;

        CiDesk ciManager = null;

        CaDesk caManager = null;

        public TvServiceBinder() {
            super();
            dataBaseManager = DataBaseDeskImpl.getDataBaseMgrInstance();
        }

        @Override
        public void initTvSrvProvider() {
            // TODO Auto-generated method stub
            // pictureService = new PictureServiceImpl();
        }

        @Override
        public CommonDesk getCommonManagerInstance() {
            comManager = CommonDeskImpl.getInstance();
            return comManager;
        }

        @Override
        public PictureDesk getPictureManagerInstance() {
            pictureManager = PictureDeskImpl.getPictureMgrInstance();
            return pictureManager;
        }

        @Override
        public DataBaseDesk getDataBaseManagerInstance() {
            return dataBaseManager;
        }

        @Override
        public SettingDesk getSettingManagerInstance() {
            settingManager = SettingDeskImpl.getSettingMgrInstance();
            return settingManager;
        }

        @Override
        public ChannelDesk getChannelManagerInstance() {
            channelManager = ChannelDeskImpl.getChannelMgrInstance();
            return channelManager;
        }

        @Override
        public SoundDesk getSoundManagerInstance() {
            soundManager = SoundDeskImpl.getSoundMgrInstance();
            return soundManager;
        }

        @Override
        public S3DDesk getS3DManagerInstance() {
            s3dManager = S3DDeskImpl.getS3DMgrInstance();
            return s3dManager;
        }

        @Override
        public DemoDesk getDemoManagerInstance() {
            demoManager = DemoDeskImpl.getDemoMgrInstance();
            return demoManager;
        }

        @Override
        public EpgDesk getEpgManagerInstance() {
            epgManager = EpgDeskImpl.getEpgMgrInstance();
            return epgManager;
        }

        @Override
        public PvrDesk getPvrManagerInstance() {
            pvrManager = PvrDeskImpl.getPvrMgrInstance();
            return pvrManager;
        }

        @Override
        public CiDesk getCiManagerInstance() {
            ciManager = CiDeskImpl.getCiMgrInstance();
            return ciManager;
        }

        @Override
        public CaDesk getCaManagerInstance() {
            caManager = CaDeskImpl.getCaMgrInstance();
            return caManager;
        }
    }
}
