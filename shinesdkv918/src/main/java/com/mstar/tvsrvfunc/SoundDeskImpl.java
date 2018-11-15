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

import com.mstar.android.tvapi.common.TvManager;
import com.mstar.android.tvapi.common.vo.AudioOutParameter;
import com.mstar.android.tvapi.common.vo.DtvSoundEffect;
import com.mstar.android.tvapi.common.vo.EnumAdvancedSoundSubProcessType;
import com.mstar.android.tvapi.common.vo.EnumAdvancedSoundType;
import com.mstar.android.tvapi.common.vo.EnumAtvAudioModeType;
import com.mstar.android.tvapi.common.vo.EnumAudioReturn;
import com.mstar.android.tvapi.common.vo.EnumAudioVolumeSourceType;
import com.mstar.android.tvapi.common.vo.EnumMuteStatusType;
import com.mstar.android.tvapi.common.vo.EnumSoundEffectType;
import com.mstar.android.tvapi.common.vo.EnumSpdifType;
import com.mstar.android.tvapi.common.vo.MuteType.EnumMuteType;
import com.mstar.tvsettingservice.CommonDesk;
import com.mstar.tvsettingservice.DataBaseDesk.EN_SOUND_MODE;
import com.mstar.tvsettingservice.DataBaseDesk.EN_SPDIF_MODE;
import com.mstar.tvsettingservice.DataBaseDesk.EN_SURROUND_MODE;
import com.mstar.tvsettingservice.DataBaseDesk.EN_SURROUND_SYSTEM_TYPE;
import com.mstar.tvsettingservice.DataBaseDesk.MS_USER_SOUND_SETTING;
import com.mstar.tvsettingservice.DataBaseDesk.MS_USER_SUBTITLE_SETTING;
import com.mstar.tvsettingservice.DataBaseDesk.SPDIF_TYPE;
import com.mstar.tvsettingservice.SoundDesk;

public class SoundDeskImpl extends BaseDeskImpl implements SoundDesk {
    private DataBaseDeskImpl databaseMgr = null;

    private MS_USER_SOUND_SETTING userSoundSetting = null;

    private static SoundDeskImpl soundMgrImpl = null;

    private CommonDesk com = null;

    private SoundDeskImpl() {
        com = CommonDeskImpl.getInstance();
        com.printfI("TvService", "SoundManagerImpl constructor!!");
        databaseMgr = DataBaseDeskImpl.getDataBaseMgrInstance();
        userSoundSetting = databaseMgr.getSound();
    }

    public static SoundDeskImpl getSoundMgrInstance() {
        if (soundMgrImpl == null)
            soundMgrImpl = new SoundDeskImpl();
        return soundMgrImpl;
    }

    @Override
    public boolean setSoundMode(EN_SOUND_MODE SoundMode) {
        DtvSoundEffect dtvSoundEff = null;
        dtvSoundEff = new DtvSoundEffect();
        userSoundSetting.SoundMode = SoundMode;
        dtvSoundEff.eqBandNumber = (short) EN_SOUND_MODE.SOUND_MODE_NUM.ordinal();
        com.printfI("TvSoundServiceImpl", "SoundMode is" + userSoundSetting.SoundMode);
        com.printfI(
                "TvSoundServiceImpl",
                "Value:" + databaseMgr.getSoundMode(SoundMode).EqBand1 + ":"
                        + databaseMgr.getSoundMode(SoundMode).EqBand2 + ":"
                        + databaseMgr.getSoundMode(SoundMode).EqBand3 + ":"
                        + databaseMgr.getSoundMode(SoundMode).EqBand4 + ":"
                        + databaseMgr.getSoundMode(SoundMode).EqBand5);
        try {
            dtvSoundEff.soundParameterEqs[0].eqLevel = (int) (databaseMgr.getSoundMode(SoundMode).EqBand1);
            dtvSoundEff.soundParameterEqs[1].eqLevel = (int) (databaseMgr.getSoundMode(SoundMode).EqBand2);
            dtvSoundEff.soundParameterEqs[2].eqLevel = (int) (databaseMgr.getSoundMode(SoundMode).EqBand3);
            dtvSoundEff.soundParameterEqs[3].eqLevel = (int) (databaseMgr.getSoundMode(SoundMode).EqBand4);
            dtvSoundEff.soundParameterEqs[4].eqLevel = (int) (databaseMgr.getSoundMode(SoundMode).EqBand5);
            dtvSoundEff.eqBandNumber = 5;
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getAudioManager()
                        .setBasicSoundEffect(EnumSoundEffectType.E_EQ, dtvSoundEff);
            }
        } catch (Exception e) {
            com.printfE("TvSoundServiceImpl", "Set Sound Mode Exception");
            e.printStackTrace();
        }
        databaseMgr.updateSoundSetting(userSoundSetting);
        return true;
    }

    @Override
    public EN_SOUND_MODE getSoundMode() {
        return userSoundSetting.SoundMode;
    }

    @Override
    public boolean setBass(short BassValue) {
        DtvSoundEffect dtvSoundEff = null;
        dtvSoundEff = new DtvSoundEffect();
        databaseMgr.getSoundMode(userSoundSetting.SoundMode).Bass = BassValue;
        com.printfI("TvSoundServiceImpl",
                "Bass is" + databaseMgr.getSoundMode(userSoundSetting.SoundMode).Bass);
        try {
            com.printfI("TvSoundServiceImpl", "Bass is$$$$$$$$" + BassValue);
            databaseMgr.getSoundMode(userSoundSetting.SoundMode).EqBand1 = BassValue;
            dtvSoundEff.soundParameterEqs[0].eqLevel = (int) BassValue;
            dtvSoundEff.soundParameterEqs[1].eqLevel = (int) (databaseMgr
                    .getSoundMode(userSoundSetting.SoundMode).EqBand2);
            dtvSoundEff.soundParameterEqs[2].eqLevel = (int) (databaseMgr
                    .getSoundMode(userSoundSetting.SoundMode).EqBand3);
            dtvSoundEff.soundParameterEqs[3].eqLevel = (int) (databaseMgr
                    .getSoundMode(userSoundSetting.SoundMode).EqBand4);
            dtvSoundEff.soundParameterEqs[4].eqLevel = (int) (databaseMgr
                    .getSoundMode(userSoundSetting.SoundMode).EqBand5);
            dtvSoundEff.eqBandNumber = 5;
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getAudioManager()
                        .setBasicSoundEffect(EnumSoundEffectType.E_EQ, dtvSoundEff);
            }
        } catch (Exception e) {
            com.printfE("TvSoundServiceImpl", "Set Sound Mode Exception");
            e.printStackTrace();
        }
        databaseMgr.updateSoundModeSetting(databaseMgr.getSoundMode(userSoundSetting.SoundMode),
                userSoundSetting.SoundMode.ordinal());
        return true;
    }

    @Override
    public short getBass() {
        return databaseMgr.getSoundMode(userSoundSetting.SoundMode).Bass;
    }

    @Override
    public boolean setTreble(short TrebleValue) {
        DtvSoundEffect dtvSoundEff = null;
        dtvSoundEff = new DtvSoundEffect();
        databaseMgr.getSoundMode(userSoundSetting.SoundMode).Treble = TrebleValue;
        com.printfI("TvSoundServiceImpl",
                "Treble is" + databaseMgr.getSoundMode(userSoundSetting.SoundMode).Treble);
        try {
            dtvSoundEff.soundParameterEqs[0].eqLevel = (int) (databaseMgr
                    .getSoundMode(userSoundSetting.SoundMode).EqBand1);
            dtvSoundEff.soundParameterEqs[1].eqLevel = (int) (databaseMgr
                    .getSoundMode(userSoundSetting.SoundMode).EqBand2);
            dtvSoundEff.soundParameterEqs[2].eqLevel = (int) (databaseMgr
                    .getSoundMode(userSoundSetting.SoundMode).EqBand3);
            dtvSoundEff.soundParameterEqs[3].eqLevel = (int) (databaseMgr
                    .getSoundMode(userSoundSetting.SoundMode).EqBand4);
            databaseMgr.getSoundMode(userSoundSetting.SoundMode).EqBand5 = TrebleValue;
            dtvSoundEff.soundParameterEqs[4].eqLevel = (int) TrebleValue;
            dtvSoundEff.eqBandNumber = 5;
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getAudioManager()
                        .setBasicSoundEffect(EnumSoundEffectType.E_EQ, dtvSoundEff);
            }
        } catch (Exception e) {
            com.printfE("TvSoundServiceImpl", "Set Sound Mode Exception");
            e.printStackTrace();
        }
        databaseMgr.updateSoundModeSetting(databaseMgr.getSoundMode(userSoundSetting.SoundMode),
                userSoundSetting.SoundMode.ordinal());
        return true;
    }

    @Override
    public short getTreble() {
        return databaseMgr.getSoundMode(userSoundSetting.SoundMode).Treble;
    }

    @Override
    public boolean setBalance(short BalanceValue) {
        DtvSoundEffect dtvSoundEff = null;
        dtvSoundEff = new DtvSoundEffect();
        userSoundSetting.Balance = BalanceValue;
        com.printfI("TvSoundServiceImpl", "Set Balance is" + userSoundSetting.Balance);
        try {
            dtvSoundEff.balance = BalanceValue;
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getAudioManager()
                        .setBasicSoundEffect(EnumSoundEffectType.E_BALANCE, dtvSoundEff);
            }
        } catch (Exception e) {
            com.printfE("TvSoundServiceImpl", "Set Balance Exception");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        databaseMgr.updateSoundSetting(userSoundSetting);
        return true;
    }

    @Override
    public short getBalance() {
        com.printfI("TvSoundServiceImpl", "Get Balance is" + userSoundSetting.Balance);
        return userSoundSetting.Balance;
    }

    @Override
    public boolean setAVCMode(boolean AvcEnable) {
        userSoundSetting.bEnableAVC = AvcEnable;
        com.printfI("TvSoundServiceImpl", "bEnableAVC is" + userSoundSetting.bEnableAVC);
        databaseMgr.updateSoundSetting(userSoundSetting);
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getAudioManager()
                        .enableBasicSoundEffect(EnumSoundEffectType.E_AVC, AvcEnable);
            }
        } catch (Exception e) {
            com.printfE("TvSoundServiceImpl", "Set Balance Exception");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean getAVCMode() {
        return userSoundSetting.bEnableAVC;
    }

    @Override
    public boolean setSurroundMode(EN_SURROUND_MODE SurroundMode) {
        boolean bEnable = false;
        userSoundSetting.SurroundMode = SurroundMode;
        com.printfI("TvSoundServiceImpl", "SurroundMode is" + userSoundSetting.SurroundMode);
        if (SurroundMode == EN_SURROUND_MODE.E_SURROUND_MODE_OFF) {
            bEnable = false;
        } else {
            bEnable = true;
        }
        try {
            if (TvManager.getInstance() != null) {
                TvManager.getInstance().getAudioManager()
                        .enableBasicSoundEffect(EnumSoundEffectType.E_Surround, bEnable);
            }
        } catch (Exception e) {
            com.printfE("TvSoundServiceImpl", "Set Balance Exception");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        databaseMgr.updateSoundSetting(userSoundSetting);
        return true;
    }

    @Override
    public EN_SURROUND_MODE getSurroundMode() {
        return userSoundSetting.SurroundMode;
    }

    @Override
    public void enableSRS(boolean isEnable) {
        userSoundSetting.SurroundSoundMode = isEnable ? EN_SURROUND_SYSTEM_TYPE.SURROUND_SYSTEM_SRS
                : EN_SURROUND_SYSTEM_TYPE.SURROUND_SYSTEM_OFF;
        Log.d("SoundDeskImpl", "userSoundSetting.SurroundSoundMode "
                + userSoundSetting.SurroundSoundMode);
        try {
            TvManager
                    .getInstance()
                    .getAudioManager()
                    .enableAdvancedSoundEffect(
                            EnumAdvancedSoundType.E_SRS_TSHD,
                            isEnable ? EnumAdvancedSoundSubProcessType.E_SRS_TSHD_DEFINITION_ON
                                    : EnumAdvancedSoundSubProcessType.E_SRS_TSHD_DEFINITION_OFF);
            TvManager
                    .getInstance()
                    .getAudioManager()
                    .enableAdvancedSoundEffect(
                            EnumAdvancedSoundType.E_SRS_TSHD,
                            isEnable ? EnumAdvancedSoundSubProcessType.E_SRS_TSHD_SRS3D_ON
                                    : EnumAdvancedSoundSubProcessType.E_SRS_TSHD_SRS3D_OFF);
            TvManager
                    .getInstance()
                    .getAudioManager()
                    .enableAdvancedSoundEffect(
                            EnumAdvancedSoundType.E_SRS_TSHD,
                            isEnable ? EnumAdvancedSoundSubProcessType.E_SRS_TSHD_TRUEBASS_ON
                                    : EnumAdvancedSoundSubProcessType.E_SRS_TSHD_TRUEBASS_OFF);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        databaseMgr.updateSoundSetting(userSoundSetting);
    }

    @Override
    public boolean isSRSEnable() {
        return ((userSoundSetting.SurroundSoundMode.ordinal() == 0) ? false : true);
    }

    @Override
    public boolean setSpdifOutMode(EN_SPDIF_MODE SpdifMode) {
        Log.d("Charles : SoundDeskImpl ", "-------------------setSpdifOutMode-------------------");
        // userSoundSetting.spdifMode = SpdifMode;
        EnumSpdifType mode;
        SPDIF_TYPE type;
        // com.printfI("TvSoundServiceImpl", "spdifMode is"
        // + userSoundSetting.spdifMode);

        AudioOutParameter aoutp = new AudioOutParameter();
        // TvManager.getInstance().getAudioManager().new AudioOutParameter();
        aoutp.setspdifOutModeInUi(EnumSpdifType.values()[SpdifMode.ordinal()]);

        // databaseMgr.updateSoundSetting(userSoundSetting);
        if (SpdifMode == EN_SPDIF_MODE.PDIF_MODE_PCM) {
            mode = EnumSpdifType.E_PCM;
            type = SPDIF_TYPE.MSAPI_AUD_SPDIF_PCM_;
        } else if (SpdifMode == EN_SPDIF_MODE.PDIF_MODE_RAW) {
            mode = EnumSpdifType.E_NONPCM;
            type = SPDIF_TYPE.MSAPI_AUD_SPDIF_NONPCM_;
        } else // if(SpdifMode == EN_SPDIF_MODE.PDIF_MODE_OFF)
        {
            mode = EnumSpdifType.E_OFF;
            type = SPDIF_TYPE.MSAPI_AUD_SPDIF_OFF_;
        }
        databaseMgr.setSpdifMode(type);
        Log.d("Charles : SoundDeskImpl ", "db setSpdifMode type : " + type);
        try {
            if (TvManager.getInstance() != null) {
                Log.d("Charles : SoundDeskImpl ", "setDigitalOut mode : " + mode);
                TvManager.getInstance().getAudioManager().setDigitalOut(mode);
            }
        } catch (Exception e) {
            com.printfE("TvSoundServiceImpl", "Set Volume Exception");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public EN_SPDIF_MODE getSpdifOutMode() {
        EN_SPDIF_MODE mode = EN_SPDIF_MODE.PDIF_MODE_OFF;
        SPDIF_TYPE type;
        type = databaseMgr.getSpdifMode();
        switch (type) {
            case MSAPI_AUD_SPDIF_PCM_:
                mode = EN_SPDIF_MODE.PDIF_MODE_PCM;
                break;
            case MSAPI_AUD_SPDIF_NONPCM_:
                mode = EN_SPDIF_MODE.PDIF_MODE_RAW;
                break;
            case MSAPI_AUD_SPDIF_OFF_:
                mode = EN_SPDIF_MODE.PDIF_MODE_OFF;
                break;

        }
        return mode;
    }

    // public boolean SetMusicOnlyMode();
    // public boolean GetMusicOnlyMode();
    @Override
    public boolean setEqBand120(short eqValue) {
        databaseMgr.getSoundMode(userSoundSetting.SoundMode).EqBand1 = eqValue;
        com.printfI("TvSoundServiceImpl",
                "EqBand1 is" + databaseMgr.getSoundMode(userSoundSetting.SoundMode).EqBand1);
        setSoundMode(userSoundSetting.SoundMode);
        databaseMgr.updateSoundModeSetting(databaseMgr.getSoundMode(userSoundSetting.SoundMode),
                userSoundSetting.SoundMode.ordinal());
        return true;
    }

    @Override
    public short getEqBand120() {
        return databaseMgr.getSoundMode(userSoundSetting.SoundMode).EqBand1;
    }

    @Override
    public boolean setEqBand500(short eqValue) {
        databaseMgr.getSoundMode(userSoundSetting.SoundMode).EqBand2 = eqValue;
        com.printfI("TvSoundServiceImpl",
                "EqBand2 is" + databaseMgr.getSoundMode(userSoundSetting.SoundMode).EqBand2);
        setSoundMode(userSoundSetting.SoundMode);
        databaseMgr.updateSoundModeSetting(databaseMgr.getSoundMode(userSoundSetting.SoundMode),
                userSoundSetting.SoundMode.ordinal());
        return true;
    }

    @Override
    public short getEqBand500() {
        return databaseMgr.getSoundMode(userSoundSetting.SoundMode).EqBand2;
    }

    @Override
    public boolean setEqBand1500(short eqValue) {
        databaseMgr.getSoundMode(userSoundSetting.SoundMode).EqBand3 = eqValue;
        com.printfI("TvSoundServiceImpl",
                "EqBand3 is" + databaseMgr.getSoundMode(userSoundSetting.SoundMode).EqBand3);
        setSoundMode(userSoundSetting.SoundMode);
        databaseMgr.updateSoundModeSetting(databaseMgr.getSoundMode(userSoundSetting.SoundMode),
                userSoundSetting.SoundMode.ordinal());
        return true;
    }

    @Override
    public short getEqBand1500() {
        return databaseMgr.getSoundMode(userSoundSetting.SoundMode).EqBand3;
    }

    @Override
    public boolean setEqBand5k(short eqValue) {
        databaseMgr.getSoundMode(userSoundSetting.SoundMode).EqBand4 = eqValue;
        com.printfI("TvSoundServiceImpl",
                "EqBand4 is" + databaseMgr.getSoundMode(userSoundSetting.SoundMode).EqBand4);
        setSoundMode(userSoundSetting.SoundMode);
        databaseMgr.updateSoundModeSetting(databaseMgr.getSoundMode(userSoundSetting.SoundMode),
                userSoundSetting.SoundMode.ordinal());
        return true;
    }

    @Override
    public short getEqBand5k() {
        return databaseMgr.getSoundMode(userSoundSetting.SoundMode).EqBand4;
    }

    @Override
    public boolean setEqBand10k(short eqValue) {
        databaseMgr.getSoundMode(userSoundSetting.SoundMode).EqBand5 = eqValue;
        com.printfI("TvSoundServiceImpl",
                "EqBand5 is" + databaseMgr.getSoundMode(userSoundSetting.SoundMode).EqBand5);
        setSoundMode(userSoundSetting.SoundMode);
        databaseMgr.updateSoundModeSetting(databaseMgr.getSoundMode(userSoundSetting.SoundMode),
                userSoundSetting.SoundMode.ordinal());
        return true;
    }

    @Override
    public short getEqBand10k() {
        return databaseMgr.getSoundMode(userSoundSetting.SoundMode).EqBand5;
    }

    @Override
    public boolean setVolume(short volume) {
        userSoundSetting.Volume = volume;
        com.printfI("TvSoundServiceImpl", "setVolume is" + userSoundSetting.Volume);
        try {
            if (TvManager.getInstance() != null) {
                TvManager
                        .getInstance()
                        .getAudioManager()
                        .setAudioVolume(EnumAudioVolumeSourceType.E_VOL_SOURCE_SPEAKER_OUT,
                                (byte) volume);
            }
        } catch (Exception e) {
            com.printfE("TvSoundServiceImpl", "Set Volume Exception");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        databaseMgr.updateSoundSetting(userSoundSetting);
        return true;
    }

    @Override
    public short getVolume() {
        // Log.d("TvSoundServiceImpl", "getVolume is" +
        // userSoundSetting.Volume);
        userSoundSetting = databaseMgr.querySoundSetting();
        Log.d("TvSoundServiceImpl", "getVolume is" + userSoundSetting.Volume);
        return userSoundSetting.Volume;
    }

    @Override
    public boolean getMuteFlag() {
        // TODO Auto-generated method stub
        try {
            if (TvManager.getInstance() != null) {
                return TvManager.getInstance().getAudioManager()
                        .isMuteEnabled(EnumMuteStatusType.MUTE_STATUS_BBYUSERAUDIOMUTE);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean setMuteFlag(boolean muteFlag) {
        // TODO Auto-generated method stub
        if (muteFlag) {
            try {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().getAudioManager().enableMute(EnumMuteType.E_BYUSER);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            try {
                if (TvManager.getInstance() != null) {
                    TvManager.getInstance().getAudioManager().disableMute(EnumMuteType.E_BYUSER);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Log.d("SoundDeskImpl", "set muteFlag is" + muteFlag);
        return true;
    }

    @Override
    public EnumAudioReturn setAtvMtsMode(EnumAtvAudioModeType enAtvMtsMode) {
        EnumAudioReturn result = EnumAudioReturn.E_RETURN_NOTOK;
        try {
            if (TvManager.getInstance() != null) {
                result = TvManager.getInstance().getAudioManager().setAtvMtsMode(enAtvMtsMode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public EnumAtvAudioModeType getAtvMtsMode() {
        EnumAtvAudioModeType result = EnumAtvAudioModeType.E_ATV_AUDIOMODE_INVALID;
        try {
            if (TvManager.getInstance() != null) {
                result = TvManager.getInstance().getAudioManager().getAtvMtsMode();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public EnumAudioReturn setToNextAtvMtsMode() {
        EnumAudioReturn result = EnumAudioReturn.E_RETURN_NOTOK;
        try {
            if (TvManager.getInstance() != null) {
                result = TvManager.getInstance().getAudioManager().setToNextAtvMtsMode();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void setADAbsoluteVolume(int volume) {

        if (TvManager.getInstance() != null) {

            userSoundSetting = databaseMgr.querySoundSetting();
            userSoundSetting.ADVolume = (short) volume;
            databaseMgr.updateSoundSetting(userSoundSetting);
            TvManager.getInstance().getAudioManager().setADAbsoluteVolume(volume);
        }
    }

    @Override
    public void setADEnable(boolean enable) {
        if (TvManager.getInstance() != null) {
            userSoundSetting = databaseMgr.querySoundSetting();
            userSoundSetting.bEnableAD = enable;
            databaseMgr.updateSoundSetting(userSoundSetting);
            TvManager.getInstance().getAudioManager().setADEnable(enable);

        }
    }

    @Override
    public boolean getADEnable() {
        if (databaseMgr != null) {
            userSoundSetting = databaseMgr.querySoundSetting();
        }

        if (userSoundSetting != null) {
            return userSoundSetting.bEnableAD;
        }

        return false;
    }

    @Override
    public int getADAbsoluteVolume() {
        if (userSoundSetting != null) {
            return userSoundSetting.ADVolume;
        }
        return -1;
    }

    @Override
    public boolean getHOHStatus() {
        if (databaseMgr != null) {
            MS_USER_SUBTITLE_SETTING uss = databaseMgr.queryUserSubtitleSetting();
            return uss.fHardOfHearing;
        }
        return false;
    }

    @Override
    public void setHOHStatus(boolean state) {
        TvManager.getInstance().getAudioManager().setAutoHOHEnable(state);

    }
}
