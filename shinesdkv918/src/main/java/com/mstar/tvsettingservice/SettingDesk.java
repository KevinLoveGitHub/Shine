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

import com.mstar.android.tvapi.common.vo.CecSetting;
import com.mstar.android.tvapi.common.vo.EnumPowerOnLogoMode;
import com.mstar.android.tvapi.common.vo.EnumPowerOnMusicMode;
import com.mstar.android.tvapi.common.vo.TvOsType.EnumLanguage;
import com.mstar.tvsettingservice.DataBaseDesk.EN_MS_CHANNEL_SWITCH_MODE;
import com.mstar.tvsettingservice.DataBaseDesk.EN_MS_FILM;
import com.mstar.tvsettingservice.DataBaseDesk.EN_MS_OFFLINE_DET_MODE;

/**
 * TV Setting Service
 *
 * @author Shuai.Wang
 */
public interface SettingDesk extends BaseDesk {
    /**
     * Get OSD Language Enumerate
     *
     * @return Current OSD Language Enumerate
     */
    public EnumLanguage GetOsdLanguage();

    /**
     * Set OSD Language Enumerate
     *
     * @param eLang
     * @return Result status.
     */
    public boolean SetOsdLanguage(EnumLanguage eLang);

    /**
     * Get OSD duration time Rang:0~4 0: 5s 1: 10 2: 15s 3: 20 4: 30
     *
     * @return duration time
     */
    public short GetOsdDuration();

    /**
     * Set OSD duration time Rang:0~4 0: 5s 1: 10 2: 15s 3: 20 4: 30
     *
     * @param value duration time
     * @return Result status.
     */
    public boolean SetOsdDuration(short value);

    /**
     * Get OSD duration time second
     *
     * @return timeout second
     */
    public int getOsdTimeoutSecond();

    /**
     * Get Channel change mode
     *
     * @return Change mode Enumerate
     */
    public EN_MS_CHANNEL_SWITCH_MODE GetChannelSWMode();

    /**
     * Set Channel change mode
     *
     * @param eMode Enumerate of Change mode
     * @return Result status.
     */
    public boolean SetChannelSWMode(EN_MS_CHANNEL_SWITCH_MODE eMode);

    /**
     * Get Off-line detection mode
     *
     * @return Enumerate of Off-line detection mode .
     */
    public EN_MS_OFFLINE_DET_MODE GetOffDetMode();

    /**
     * Set Off-line detection mode
     *
     * @param eMode
     * @return Result status.
     */
    public boolean SetOffDetMode(EN_MS_OFFLINE_DET_MODE eMode);

    /**
     * Get HDMI color range index Rang: 0~1, 0: 0-255; 1: 16-235
     *
     * @return color range index
     */
    public short GetColorRange();

    /**
     * Set HDMI color range index Rang: 0~1, 0: 0-255; 1: 16-235
     *
     * @param value
     * @return Result status.
     */
    public boolean SetColorRanger(short value);

    /**
     * Get video film mode
     *
     * @return Enumerate of film mode
     */
    public EN_MS_FILM GetFilmMode();

    /**
     * Set video film mode
     *
     * @param eMode Enumerate of film mode
     * @return Result status.
     */
    public boolean SetFilmMode(EN_MS_FILM eMode);

    /**
     * Get TV no signal mode: 0: Noise; 1: Blue Screen
     *
     * @return Blue Screen Flag
     */
    public boolean GetBlueScreenFlag();

    /**
     * Set TV no signal mode: 0: Noise; 1: Blue Screen
     *
     * @param bFlag Blue Screen Flag
     * @return Result status.
     */
    public boolean SetBlueScreenFlag(boolean bFlag);

    /**
     * Get HDMI CEC status Range: 0~1, 0: Off; 1: On
     *
     * @return CEC status
     */
    public CecSetting GetCecStatus();

    /**
     * Set HDMI CEC status Range: 0~1, 0: Off; 1: On
     *
     * @param value CEC status
     * @return Result status.
     */
    public boolean SetCecStatus(CecSetting value);

    /**
     * Restore user database to default
     *
     * @return Result status.
     */
    public boolean ExecRestoreToDefault();

    /**
     * Get the environment of power on music mode
     *
     * @return Music mode.
     */
    public EnumPowerOnMusicMode GetEnvironmentPowerOnMusicMode();

    /**
     * Set the environment of power on music mode
     *
     * @return Result status.
     */
    public boolean SetEnvironmentPowerOnMusicMode(EnumPowerOnMusicMode eMusicMode);

    /**
     * Get the environment of power on logo mode
     *
     * @return Music mode.
     */
    public EnumPowerOnLogoMode GetEnvironmentPowerOnLogoMode();

    /**
     * Set the environment of power on logo mode
     *
     * @return Result status.
     */
    public boolean SetEnvironmentPowerOnLogoMode(EnumPowerOnLogoMode eLogoMode);

    /**
     * Get the environment of power on music volume
     *
     * @return Volume.
     */
    public short GetEnvironmentPowerOnMusicVolume();

    /**
     * Set the environment of power on music volume
     *
     * @return Result status.
     */
    public boolean SetEnvironmentPowerOnMusicVolume(short volume);
}
