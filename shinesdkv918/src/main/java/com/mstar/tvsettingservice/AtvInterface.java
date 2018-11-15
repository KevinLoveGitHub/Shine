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

package com.mstar.tvsettingservice;

import android.app.Activity;

import com.mstar.android.tvapi.atv.vo.EnumAtvManualTuneMode;
import com.mstar.android.tvapi.atv.vo.EnumGetProgramCtrl;
import com.mstar.android.tvapi.atv.vo.EnumGetProgramInfo;
import com.mstar.android.tvapi.atv.vo.EnumSetProgramCtrl;
import com.mstar.android.tvapi.atv.vo.EnumSetProgramInfo;
import com.mstar.android.tvapi.common.vo.AtvSystemStandard.EnumAtvSystemStandard;
import com.mstar.android.tvapi.common.vo.EnumAvdVideoStandardType;

public interface AtvInterface {
    public class ATV_SCAN_EVENT {
        // / the percentage
        public int u8Percent;

        // / the frequency in kHz
        public int u32FrequencyKHz;

        // / the counts of scanned channels
        public int u16ScannedChannelNum;

        // / current scanning channel number
        public int u16CurScannedChannel;

        // / Is Scaning
        public boolean bIsScaningEnable;
    }

    public enum ATV_MANUALTUNE_MODE {
        // / search one channel, direction = up
        E_MANUAL_TUNE_MODE_SEARCH_ONE_TO_UP,
        // / search one channel, direction = down
        E_MANUAL_TUNE_MODE_SEARCH_ONE_TO_DOWN,
        // / fine tune to a given frequency
        E_MANUAL_TUNE_MODE_FINE_TUNE_ONE_FREQUENCY,
        // / fine tune, direction = up
        E_MANUAL_TUNE_MODE_FINE_TUNE_UP,
        // / fine tune, direction = down
        E_MANUAL_TUNE_MODE_FINE_TUNE_DOWN,
        // / undefine
        E_MANUAL_TUNE_MODE_UNDEFINE;
    }

    public static String[] atvcolorsystem = {
            "PAL", "NTSC_M", "SECAM", "NTSC_44", "PAL_M", "PAL_N", "PAL_60", "NO_STAND", "AUTO"
    };

    public static String[] atvsoundsystem = {
            "BG", "DK", "I", "L", "M"
    };

    public class ATV_DATABASE {
        public int plldata;

        public EnumAtvSystemStandard asys;

        public EnumAvdVideoStandardType vsys;

        public boolean block;

        public boolean bskip;

        public boolean bneedaft;

        public String sname;
    }

    /**
     * To start the ATV manual tuning
     *
     * @param EventIntervalMs \b IN: interval to send the event to update the
     *            scan status OSD
     * @param Frequency \b IN: the frequency to do the manual searxh
     * @param eMode \b IN: manual tuning mode
     * @return
     */
    public boolean atvSetManualTuningStart(int EventIntervalMs, int Frequency,
                                           EnumAtvManualTuneMode eMode);

    /**
     * To stop the ATV manual tuning
     */
    public void atvSetManualTuningEnd();

    public boolean atvSetAutoTuningStart(int EventInterval, int FrequencyStart, int FrequencyEnd);

    /**
     * To get the frequency of current channel
     *
     * @return
     */
    public int atvGetCurrentFrequency();

    /**
     * Set to the specific frequency
     *
     * @param Frequency
     * @return
     */
    public boolean atvSetFrequency(int Frequency);

    /**
     * pause the ATV auto
     *
     * @return
     */
    public boolean atvSetAutoTuningPause();

    /**
     * To resume the ATV auto scan
     *
     * @return
     */
    public boolean atvSetAutoTuningResume();

    /**
     * To stop the ATV auto scan
     *
     * @return
     */
    public boolean atvSetAutoTuningEnd();

    /**
     * Get Sound System
     *
     * @return \b OUT: ATV_SOUND_SYSTEM
     */
    public EnumAtvSystemStandard atvGetSoundSystem();

    /**
     * Force Set to Sound System
     *
     * @param eSoundSystem \b IN: Input the Audio Sound System
     * @return \b OUT: True or False
     */
    boolean atvSetForceSoundSystem(EnumAtvSystemStandard eSoundSystem);

    /**
     * Get Video System
     *
     * @return
     */
    public EnumAvdVideoStandardType atvGetVideoSystem();

    /**
     * Force Set to Video System
     *
     * @param eVideoSystem
     */
    public void atvSetForceVedioSystem(EnumAvdVideoStandardType eVideoSystem);

    /**
     * @param Cmd To set program information
     * @param Program \b IN: SET_PROGRAM_PLL_DATA, AFT_NEED, SET_AUDIO_STANDARD,
     *            SET_VIDEO_STANDARD,...see EN_SET_PROGRAM_INFO
     * @param Param3
     * @param str
     * @return
     */
    public int atvSetProgramInfo(EnumSetProgramInfo Cmd, int Program, int Param3, final String str);

    /**
     * To get control program information
     *
     * @param Cmd
     * @param u16Program
     * @param u16Param3
     * @param str
     * @return cmd result
     */
    int atvGetProgramInfo(EnumGetProgramInfo Cmd, final int u16Program, final int u16Param3,
                          StringBuffer str);

    /**
     * Set channel to specific channel number
     *
     * @param ChannelNum
     * @param bCheckBlock
     * @return
     */
    int atvSetChannel(short ChannelNum, boolean bCheckBlock, Activity activity);

    /**
     * Send event to activity for ui upgrade
     */
    public void sendAtvScaninfo();

    /**
     * get atv cur channelnumber
     *
     * @return
     */
    public int getCurrentChannelNumber();

    /**
     * / To set control program information
     *
     * @param Cmd \b IN: RESET_CHANNEL_DATA,
     *            SET_CURRENT_PROGRAM_NUMBER,INC_CURRENT_PROGRAM_NUMBER
     *            ,DEC_CURRENT_PROGRAM_NUMBER
     *            ,DELETE_PROGRAM,MOVE_PROGRAM,SWAP_PROGRAM,COPY_PROGRA
     * @param u16Param2
     * @param u16Param3
     * @param pVoid
     * @return
     */
    public int setProgramCtrl(EnumSetProgramCtrl Cmd, int u16Param2, int u16Param3, String pVoid);

    /**
     * To get control program information
     *
     * @param Cmd
     * @param u16Param2
     * @param u16Param3
     * @param pVoid
     * @return
     */
    public int getProgramCtrl(EnumGetProgramCtrl Cmd, int u16Param2, int u16Param3, String pVoid);

    /**
     * instead of get station name
     *
     * @param programNo
     * @return
     */
    public String getAtvStationName(int programNo);
}
