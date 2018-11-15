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

import android.util.Log;

import com.mstar.android.tvapi.atv.AtvManager;
import com.mstar.android.tvapi.common.TvManager;
import com.mstar.android.tvapi.common.vo.CecSetting;
import com.mstar.android.tvapi.common.vo.EnumPowerOnLogoMode;
import com.mstar.android.tvapi.common.vo.EnumPowerOnMusicMode;
import com.mstar.android.tvapi.common.vo.Film.EnumFilm;
import com.mstar.android.tvapi.common.vo.TvOsType.EnumLanguage;
import com.mstar.tvsettingservice.CommonDesk;
import com.mstar.tvsettingservice.DataBaseDesk.EN_MS_CHANNEL_SWITCH_MODE;
import com.mstar.tvsettingservice.DataBaseDesk.EN_MS_FILM;
import com.mstar.tvsettingservice.DataBaseDesk.EN_MS_OFFLINE_DET_MODE;
import com.mstar.tvsettingservice.SettingDesk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Shuai.Wang
 */
public class SettingDeskImpl extends BaseDeskImpl implements SettingDesk {
    private DataBaseDeskImpl databaseMgr;

    private static SettingDeskImpl settingMgrImpl;

    private CommonDesk com = null;

    private static int menuTimeOut;

    public static SettingDeskImpl getSettingMgrInstance() {
        if (settingMgrImpl == null)
            settingMgrImpl = new SettingDeskImpl();
        return settingMgrImpl;
    }

    private SettingDeskImpl() {
        int value;
        databaseMgr = DataBaseDeskImpl.getDataBaseMgrInstance();
        com = CommonDeskImpl.getInstance();
        com.printfE("TvService", "SettingServiceImpl constructor!!");
        value = databaseMgr.getUsrData().u8OsdDuration;
        menuTimeOut = (5 + 5 * value);
        if (value == 4) // Idx 4: 30 Second
        {
            menuTimeOut += 10;
        }
        if (value >= 5)
            menuTimeOut = 0xFFFF;
    }

    @Override
    public EnumLanguage GetOsdLanguage() {
        // TODO Auto-generated method stub
        com.printfE("TvService", "GetOsdLanguage Parameter:" + databaseMgr.getUsrData().enLanguage
                + "!!");
        return databaseMgr.getUsrData().enLanguage;
    }

    @Override
    public boolean SetOsdLanguage(EnumLanguage eLang) {
        // TODO Auto-generated method stub
        int value;
        databaseMgr.getUsrData().enLanguage = eLang;
        databaseMgr.getSubtitleSet().SubtitleDefaultLanguage = eLang;
        databaseMgr.getSubtitleSet().SubtitleDefaultLanguage_2 = eLang;
        switch (eLang) {
            case E_CHINESE:
                value = EnumLanguage.E_CHINESE.ordinal();
                break;
            case E_ACHINESE:
                value = EnumLanguage.E_ACHINESE.ordinal();
                break;
            case E_ENGLISH:
            default:
                value = EnumLanguage.E_ENGLISH.ordinal();
                break;
        }
        // try
        {
            // dManager.setOsdLanguage(EnumLanguage.values()[value]);
        }
        // catch (Exception e)
        {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
        databaseMgr.updateUserSysSetting(databaseMgr.getUsrData());
        databaseMgr.updateUserSubtitleSetting(databaseMgr.getSubtitleSet());
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().setLanguage(EnumLanguage.values()[value]);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public short GetOsdDuration() {
        // TODO Auto-generated method stub
        com.printfE("TvService", "GetOsdDuration Parameter:"
                + databaseMgr.getUsrData().u8OsdDuration + "!!");
        // databaseMgr.getUsrData().u8OsdDuration =
        // (short)((databaseMgr.getUsrData().u32MenuTimeOut/1000 - 5)/5);
        int second = (int) (databaseMgr.getUsrData().u32MenuTimeOut / 1000);
        switch (second) {
            case 5:
                databaseMgr.getUsrData().u8OsdDuration = 0;
                break;
            case 10:
                databaseMgr.getUsrData().u8OsdDuration = 1;
                break;
            case 15:
                databaseMgr.getUsrData().u8OsdDuration = 2;
                break;
            case 20:
                databaseMgr.getUsrData().u8OsdDuration = 3;
                break;
            case 30:
                databaseMgr.getUsrData().u8OsdDuration = 4;
                break;
            default:
                databaseMgr.getUsrData().u8OsdDuration = 5;
                break;

        }
        return databaseMgr.getUsrData().u8OsdDuration;
    }

    @Override
    public boolean SetOsdDuration(short value) {
        // TODO Auto-generated method stub
        com.printfE("TvService", "SetOsdDuration Parameter: Nothing to do in mock!!");
        databaseMgr.getUsrData().u8OsdDuration = value;
        databaseMgr.getUsrData().u32MenuTimeOut = (5 + 5 * value) * 1000;
        menuTimeOut = (5 + 5 * value);
        if (value == 4) // Idx 4: 30 Second
        {
            databaseMgr.getUsrData().u32MenuTimeOut += 5 * 1000;
            menuTimeOut += 5;
        }
        if (value >= 5) {
            menuTimeOut = 0xFFFF;
            databaseMgr.getUsrData().u32MenuTimeOut = 0xFFFF;
        }
        com.printfE("TvService", "SetOsdDuration : TimeOut = "
                + databaseMgr.getUsrData().u32MenuTimeOut + "ms!!");
        databaseMgr.updateUserSysSetting(databaseMgr.getUsrData());
        return true;
    }

    @Override
    public int getOsdTimeoutSecond() {
        menuTimeOut = (int) (databaseMgr.getUsrData().u32MenuTimeOut / 1000);
        return menuTimeOut;
    }

    @Override
    public EN_MS_CHANNEL_SWITCH_MODE GetChannelSWMode() {
        // TODO Auto-generated method stub
        com.printfE("TvService", "GetChannelSWMode Parameter:" + databaseMgr.getUsrData().eChSwMode
                + "!!");
        return databaseMgr.getUsrData().eChSwMode;
    }

    @Override
    public boolean SetChannelSWMode(EN_MS_CHANNEL_SWITCH_MODE eMode) {
        // TODO Auto-generated method stub
        com.printfE("TvService", "SetOsdLanguage SetChannelSWMode: Nothing to do in mock!!");
        databaseMgr.getUsrData().eChSwMode = eMode;
        databaseMgr.updateUserSysSetting(databaseMgr.getUsrData());
        if (eMode == EN_MS_CHANNEL_SWITCH_MODE.MS_CHANNEL_SWM_BLACKSCREEN) {
            try {
                AtvManager.getAtvPlayerManager().setChannelChangeFreezeMode(false);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            try {
                AtvManager.getAtvPlayerManager().setChannelChangeFreezeMode(true);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public EN_MS_OFFLINE_DET_MODE GetOffDetMode() {
        // TODO Auto-generated method stub
        com.printfE("TvService", "GetOffDetMode Parameter:" + databaseMgr.getUsrData().eOffDetMode
                + "!!");
        return databaseMgr.getUsrData().eOffDetMode;
    }

    @Override
    public boolean SetOffDetMode(EN_MS_OFFLINE_DET_MODE eMode) {
        // TODO Auto-generated method stub
        com.printfE("TvService", "SetOsdLanguage SetOffDetMode: Nothing to do in mock!!");
        databaseMgr.getUsrData().eOffDetMode = eMode;
        databaseMgr.updateUserSysSetting(databaseMgr.getUsrData());
        return true;
    }

    @Override
    public short GetColorRange() {
        return databaseMgr.getUsrData().u8ColorRangeMode;
    }

    @Override
    public boolean SetColorRanger(short value) {
        boolean colorRange0_255 = false;
        databaseMgr.getUsrData().u8ColorRangeMode = value;
        databaseMgr.updateUserSysSetting(databaseMgr.getUsrData());
        try {
            if (value == 2) {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().getPictureManager().autoHDMIColorRange();
                }
            } else {
                if (value != 0) {
                    colorRange0_255 = false;
                } else {
                    colorRange0_255 = true;
                }

                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().getPictureManager().setColorRange(colorRange0_255);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public EN_MS_FILM GetFilmMode() {
        return databaseMgr.getVideo().eFilm;
    }

    @Override
    public boolean SetFilmMode(EN_MS_FILM eMode) {
        databaseMgr.getVideo().eFilm = eMode;
        databaseMgr.updateVideoBasePara(databaseMgr.getVideo(), com.GetCurrentInputSource()
                .ordinal());
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getPictureManager()
                        .setFilm(EnumFilm.values()[eMode.ordinal() + 1]);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean GetBlueScreenFlag() {
        return databaseMgr.getUsrData().bBlueScreen;
    }

    @Override
    public boolean SetBlueScreenFlag(boolean bFlag) {
        databaseMgr.getUsrData().bBlueScreen = bFlag;
        databaseMgr.updateUserSysSetting(databaseMgr.getUsrData());
        return true;
    }

    @Override
    public CecSetting GetCecStatus() {
        CecSetting cec_Setting = null;
        try {
            if (TvManager.getInstance() != null) {
                cec_Setting = TvManager.getInstance().getCecManager().getCecConfiguration();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return cec_Setting;
    }

    @Override
    public boolean SetCecStatus(CecSetting set_Cecsetting) {
        if (set_Cecsetting != null) {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getCecManager().setCecConfiguration(set_Cecsetting);
            }
        }
        // databaseMgr.getCECVar().mCECStatus = value;
        return true;
    }

    @Override
    public boolean ExecRestoreToDefault() {
        boolean ret = true;
        boolean result = false;
        File srcFile = new File("/tvdatabase/DatabaseBackup/", "user_setting.db");
        File destFile = new File("/tvdatabase/Database/", "user_setting.db");
        result = copyFile(srcFile, destFile);
        result = false;
        if (result == false) {
            ret = false;
        }
        // BugFix add begin: Mantis:0380715 Fix set to default Frequency in
        // Mantual Scan Setting
        /*
         * try { if(TvManager.getInstance() != null) { ret =
         * TvManager.getInstance().resetToFactoryDefault(); }
         * Log.d("ExecRestoreToDefault", "resetToFactoryDefault"); } catch
         * (Exception e) { // TODO Auto-generated catch block
         * e.printStackTrace(); }
         */
        // BugFix add end: Mantis:0380715
        // srcFile = new File("/tvdatabase/DatabaseBackup/", "factory.db");
        // destFile = new File("/tvdatabase/Database/", "factory.db");
        // result = copyFile(srcFile,destFile);
        // result = false;
        // if(result == false){
        // ret = false;
        // }
        return ret;
    }

    /**
     * Copy data from a source stream to destFile. Return true if succeed,
     * return false if failed.
     */
    private boolean copyToFile(InputStream inputStream, File destFile) {
        try {
            if (destFile.exists()) {
                destFile.delete();
            }
            FileOutputStream out = new FileOutputStream(destFile);
            try {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) >= 0) {
                    Log.d(" out.write(buffer, 0, bytesRead);", " out.write(buffer, 0, bytesRead);");
                    out.write(buffer, 0, bytesRead);
                }
            } finally {
                out.flush();
                try {
                    out.getFD().sync();
                } catch (IOException e) {
                }
                out.close();
            }
            return true;
        } catch (IOException e) {
            Log.d("copyToFile(InputStream inputStream, File destFile)", e.getMessage());
            return false;
        }
    }

    // copy a file from srcFile to destFile, return true if succeed, return
    // false if fail
    private boolean copyFile(File srcFile, File destFile) {
        boolean result = false;
        try {
            InputStream in = new FileInputStream(srcFile);
            try {
                result = copyToFile(in, destFile);
            } finally {
                in.close();
            }
        } catch (IOException e) {
            Log.d("copyFile(File srcFile, File destFile)", e.getMessage());
            result = false;
        }
        chmodFile(destFile);
        return result;
    }

    private void chmodFile(File destFile) {
        try {
            String command = "chmod 666 " + destFile.getAbsolutePath();
            Log.i("zyl", "command = " + command);
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            Log.i("zyl", "chmod fail!!!!");
            e.printStackTrace();
        }
    }

    @Override
    public EnumPowerOnMusicMode GetEnvironmentPowerOnMusicMode() {
        // TODO Auto-generated method stub
        EnumPowerOnMusicMode ret = EnumPowerOnMusicMode.E_POWERON_MUSIC_OFF;
        try {
            if (TvManager.getInstance() != null) {
                ret = TvManager.getInstance().getEnvironmentPowerOnMusicMode();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public boolean SetEnvironmentPowerOnMusicMode(EnumPowerOnMusicMode eMusicMode) {
        boolean ret = false;
        try {
            if (TvManager.getInstance() != null) {
                ret = TvManager.getInstance().setEnvironmentPowerOnMusicMode(eMusicMode);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.d("SetEnvironmentPowerOnMusicMode", "Set Music Mode:" + eMusicMode);
        return ret;
    }

    @Override
    public EnumPowerOnLogoMode GetEnvironmentPowerOnLogoMode() {
        // TODO Auto-generated method stub
        EnumPowerOnLogoMode ret = EnumPowerOnLogoMode.E_POWERON_LOGO_OFF;
        try {
            if (TvManager.getInstance() != null) {
                ret = TvManager.getInstance().getEnvironmentPowerOnLogoMode();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public boolean SetEnvironmentPowerOnLogoMode(EnumPowerOnLogoMode eLogoMode) {
        // TODO Auto-generated method stub
        boolean ret = false;
        try {
            if (TvManager.getInstance() != null) {
                ret = TvManager.getInstance().setEnvironmentPowerOnLogoMode(eLogoMode);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.d("SetEnvironmentPowerOnLogoMode", "Set Logo Mode:" + eLogoMode);
        return ret;
    }

    @Override
    public short GetEnvironmentPowerOnMusicVolume() {
        // TODO Auto-generated method stub
        short ret = 0;
        try {
            if (TvManager.getInstance() != null) {
                ret = TvManager.getInstance().getFactoryManager()
                        .getEnvironmentPowerOnMusicVolume();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.d("GetEnvironmentPowerOnMusicVolume", "Get Music Volume: 0x" + Integer.toHexString(ret));
        // ret = (short) (100 - ret * 100 / 127 - 1); // 0x7F -> 0; 0x00 -> 100
        return ret;
    }

    @Override
    public boolean SetEnvironmentPowerOnMusicVolume(short volume) {
        // TODO Auto-generated method stub
        boolean ret = false;
        // volume = (short) ((100 - volume) * 127 /100); // 0x7F -> 0; 0x00 ->
        // 100
        try {
            if (TvManager.getInstance() != null) {
                ret = TvManager.getInstance().getFactoryManager()
                        .setEnvironmentPowerOnMusicVolume(volume);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.d("SetEnvironmentPowerOnMusicVolume",
                "Set Music Volume: 0x" + Integer.toHexString(volume));
        return ret;
    }

}
