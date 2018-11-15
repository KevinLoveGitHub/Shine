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

import com.mstar.android.tvapi.common.vo.TvOsType.EnumInputSource;
import com.mstar.tvsettingservice.DataBaseDesk.E_ADC_SET_INDEX;
import com.mstar.tvsettingservice.DataBaseDesk.MS_NLA_SET_INDEX;

/**
 * Base Interface of TV Service
 *
 * @author stan
 */
public interface FactoryDesk extends BaseDesk {
    final static int AUTOTUNE_START = 20111;

    final static int AUTOTUNE_END_SUCESSED = 20112;

    final static int AUTOTUNE_END_FAILED = 20113;

    /*************************************************************************
     * ADC adjust setting
     ************************************************************************/
    public boolean setADCRedGain(int redGain);

    /**
     * get ADC red gain
     *
     * @param displayMode
     * @return
     */
    public int getADCRedGain();

    /**
     * set ADC green gain
     *
     * @param displayMode
     * @return
     */
    public boolean setADCGreenGain(int greenGain);

    /**
     * get ADC green gain
     *
     * @return
     */
    public int getADCGreenGain();

    /**
     * set ADC blue gain
     *
     * @param displayMode
     * @return
     */
    public boolean setADCBlueGain(int blueGain);

    /**
     * get ADC blue gain
     *
     * @return
     */
    public int getADCBlueGain();

    /**
     * set ADC red offset
     *
     * @param displayMode
     * @return
     */
    public boolean setADCRedOffset(int redOffset);

    /**
     * get ADC red offset
     *
     * @param displayMode
     * @return
     */
    public int getADCRedOffset();

    /**
     * set ADC green offset
     *
     * @param displayMode
     * @return
     */
    public boolean setADCGreenOffset(int greenOffset);

    /**
     * get ADC green offset
     *
     * @param displayMode
     * @return
     */
    public int getADCGreenOffset();

    /**
     * set ADC blue offset
     *
     * @param displayMode
     * @return
     */
    public boolean setADCBlueOffset(int blueOffset);

    /**
     * get ADC blue offset
     *
     * @param displayMode
     * @return
     */
    public int getADCBlueOffset();

    /**
     * set auto ADC mode, off or on
     *
     * @return Result Status
     */
    public boolean ExecAutoADC();

    /**
     * Set ADC index
     *
     * @param eIdx Adc index
     * @return Result status
     */
    public boolean setAdcIdx(E_ADC_SET_INDEX eIdx);

    /**
     * Get ADC Index
     *
     * @return Adc index
     */
    public E_ADC_SET_INDEX getAdcIdx();

    /*************************************************************************
     * White balance adjust
     ************************************************************************/
    public boolean changeWBParaWhenSourceChange();

    public boolean setWbRedGain(short redGain);

    /**
     * get white balance red gain
     *
     * @param displayMode
     * @return
     */
    public short getWbRedGain();

    /**
     * set white balance green gain
     *
     * @param displayMode
     * @return
     */
    public boolean setWbGreenGain(short greenGain);

    /**
     * get white balance green gain
     *
     * @return
     */
    public short getWbGreenGain();

    /**
     * set white balance blue gain
     *
     * @param displayMode
     * @return
     */
    public boolean setWbBlueGain(short blueGain);

    /**
     * get white balance blue gain
     *
     * @return
     */
    public short getWbBlueGain();

    /**
     * set white balance red offset
     *
     * @param displayMode
     * @return
     */
    public boolean setWbRedOffset(short redOffset);

    /**
     * get white balance red offset
     *
     * @param displayMode
     * @return
     */
    public short getWbRedOffset();

    /**
     * set white balance green offset
     *
     * @param displayMode
     * @return
     */
    public boolean setWbGreenOffset(short greenOffset);

    /**
     * get white balance green offset
     *
     * @param displayMode
     * @return
     */
    public short getWbGreenOffset();

    /**
     * set white balance blue offset
     *
     * @param displayMode
     * @return
     */
    public boolean setWbBlueOffset(short blueOffset);

    /**
     * get white balance blue offset
     *
     * @param displayMode
     * @return
     */
    public short getWbBlueOffset();

    /*************************************************************************
     * abnormal items setting
     ************************************************************************/
    /**
     * set vif tip value
     *
     * @param short
     * @return
     */
    public boolean setVifTop(short vifTop);

    /**
     * get vif tip value
     *
     * @return short
     */
    public short getVifTop();

    /**
     * set vif vga maximum value
     *
     * @param short
     * @return
     */
    public boolean setVifVgaMaximum(int vifVgaMaximum);

    /**
     * get vif vga maximum value
     *
     * @return short
     */
    public int getVifVgaMaximum();

    /**
     * set vif crkp value
     *
     * @param short
     * @return
     */
    public boolean setVifCrKp(short vifCrKp);

    /**
     * get vif cr kp value
     *
     * @return short
     */
    public short getVifCrKp();

    /**
     * set vif crki value
     *
     * @param short
     * @return
     */
    public boolean setVifCrKi(short vifCrKi);

    /**
     * get vif cr ki value
     *
     * @return short
     */
    public short getVifCrKi();

    /**
     * set vif asia signal option
     *
     * @param short
     * @return
     */
    public boolean setVifAsiaSignalOption(boolean vifAsiaSignalOption);

    /**
     * get vif asia signal option
     *
     * @return short
     */
    public boolean getVifAsiaSignalOption();

    /**
     * set vif cr kp ki adjust
     *
     * @param short
     * @return
     */
    public boolean setVifCrKpKiAdjust(boolean vifCrKpKiAdjust);

    /**
     * get vif cr kp ki adjust
     *
     * @return short
     */
    public boolean getVifCrKpKiAdjust();

    /**
     * set vif over modulation
     *
     * @param short
     * @return
     */
    public boolean setVifOverModulation(boolean vifOverModulation);

    /**
     * get vif over modulation
     *
     * @return short
     */
    public boolean getVifOverModulation();

    /**
     * set vif clampgain ov negative
     *
     * @param short
     * @return
     */
    public boolean setVifClampGainOvNegative(int vifClampGainOvNegative);

    /**
     * get vif clampgain ov negative
     *
     * @return short
     */
    public int getVifClampGainOvNegative();

    /**
     * set china descrable box
     *
     * @param short
     * @return
     */
    public boolean setChinaDescramblerBox(short chinaDescramblerBox);

    /**
     * get china descrable box
     *
     * @return short
     */
    public short getChinaDescramblerBox();

    /**
     * set delay reduce vlaue
     *
     * @param short
     * @return
     */
    public boolean setDelayReduce(short delayReduce);

    /**
     * get delay reduce value
     *
     * @return short
     */
    public short getDelayReduce();

    /**
     * set vif cr thr
     *
     * @param short
     * @return
     */
    public boolean setVifCrThr(int vifCrThr);

    /**
     * get vif cr thr
     *
     * @return short
     */
    public int getVifCrThr();

    /**
     * set vif version
     *
     * @param short
     * @return
     */
    public boolean setVifVersion(short vifVersion);

    /**
     * get vif version
     *
     * @return short
     */
    public short getVifVersion();

    /**
     * set vif agc ref
     *
     * @param short
     * @return
     */
    public boolean setVifAgcRef(short vifAgcRef);

    /**
     * get vif agc ref
     *
     * @return short
     */
    public short getVifAgcRef();

    /**
     * set gain distribution thr
     *
     * @param short
     * @return
     */
    public boolean setGainDistributionThr(int gainDistributionThr);

    /**
     * get gain distribution thr
     *
     * @return short
     */
    public int getGainDistributionThr();

    /**
     * set AEFC D4 register
     *
     * @param short
     * @return
     */
    public boolean setAEFC_D4(short AEFC_D4);

    /**
     * get AEFC D4 register
     *
     * @return short
     */
    public short getAEFC_D4();

    /**
     * set AEFC D5 register Bit2
     *
     * @param short
     * @return
     */
    public boolean setAEFC_D5Bit2(short AEFC_D5Bit2);

    /**
     * get AEFC D5 register Bit2
     *
     * @return short
     */
    public short getAEFC_D5Bit2();

    /**
     * set AEFC D8 register Bit3,2,1,0
     *
     * @param short
     * @return
     */
    public boolean setAEFC_D8Bit3210(short AEFC_D8Bit3210);

    /**
     * get AEFC D8 register Bit3,2,1,0
     *
     * @return short
     */
    public short getAEFC_D8Bit3210();

    /**
     * set AEFC D9 register Bit0
     *
     * @param short
     * @return
     */
    public boolean setAEFC_D9Bit0(short AEFC_D9Bit0);

    /**
     * get AEFC D9 register Bit0
     *
     * @return short
     */
    public short getAEFC_D9Bit0();

    /**
     * set AEFC D7 register low boun
     *
     * @param short
     * @return
     */
    public boolean setAEFC_D7LowBoun(short AEFC_D7LowBoun);

    /**
     * get AEFC D7 register low boun
     *
     * @return short
     */
    public short getAEFC_D7LowBoun();

    /**
     * set AEFC A0 register
     *
     * @param short
     * @return
     */
    public boolean setAEFC_A0(short AEFC_A0);

    /**
     * get AEFC A0 register
     *
     * @return short
     */
    public short getAEFC_A0();

    /**
     * set AEFC A1 register
     *
     * @param short
     * @return
     */
    public boolean setAEFC_A1(short AEFC_A1);

    /**
     * get AEFC A1 register
     *
     * @return short
     */
    public short getAEFC_A1();

    /**
     * set AEFC 66 register Bit7,6
     *
     * @param short
     * @return
     */
    public boolean setAEFC_66Bit76(short AEFC_66Bit76);

    /**
     * get AEFC 66 register Bit7,6
     *
     * @return short
     */
    public short getAEFC_66Bit76();

    /**
     * set AEFC 6e register Bit7,6,5,4
     *
     * @param short
     * @return
     */
    public boolean setAEFC_6EBit7654(short AEFC_6EBit7654);

    /**
     * get AEFC 6e register Bit7,6,5,4
     *
     * @return short
     */
    public short getAEFC_6EBit7654();

    /**
     * set AEFC 6e register Bit3,2,1,0
     *
     * @param short
     * @return
     */
    public boolean setAEFC_6EBit3210(short AEFC_6EBit3210);

    /**
     * get AEFC 6e register Bit3,2,1,0
     *
     * @return short
     */
    public short getAEFC_6EBit3210();

    /**
     * set AEFC 43 register
     *
     * @param short
     * @return
     */
    public boolean setAEFC_43(short AEFC_43);

    /**
     * get AEFC 43 register
     *
     * @return short
     */
    public short getAEFC_43();

    /**
     * set AEFC 44 register
     *
     * @param short
     * @return
     */
    public boolean setAEFC_44(short AEFC_44);

    /**
     * get AEFC 44 register
     *
     * @return short
     */
    public short getAEFC_44();

    /**
     * set AEFC CB register
     *
     * @param short
     * @return
     */
    public boolean setAEFC_CB(short AEFC_CB);

    /**
     * get AEFC CB register
     *
     * @return short
     */
    public short getAEFC_CB();

    /**
     * set vd dsp version
     *
     * @param short
     * @return
     */
    public boolean setVdDspVersion(short vdDspVersion);

    /**
     * get vd dsp version
     *
     * @return short
     */
    public short getVdDspVersion();

    /**
     * set audio hidev mode
     *
     * @param short
     * @return
     */
    public boolean setAudioHiDevMode(int audioHiDevMode);

    /**
     * get audio hidev mode
     *
     * @return short
     */
    public int getAudioHiDevMode();

    /**
     * set audio nr thr
     *
     * @param short
     * @return
     */
    public boolean setAudioNrThr(short audioNrThr);

    /**
     * get audio nr thr
     *
     * @return short
     */
    public short getAudioNrThr();

    /**
     * set audio sif threshold
     *
     * @param short
     * @return
     */
    public boolean setAudioSifThreshold(short audioSifThreshold);

    /**
     * get audio sif threshold
     *
     * @return short
     */
    public short getAudioSifThreshold();

    /**
     * set audio dsp version
     *
     * @param short
     * @return
     */
    public boolean setAudioDspVersion(short aduioDspVersion);

    /**
     * get audio dsp version
     *
     * @return short
     */
    public short getAudioDspVersion();

    /*************************************************************************
     * Nonlinear setting
     ************************************************************************/
    /**
     * set curve type
     *
     * @param short
     * @return
     */
    public boolean setCurveType(MS_NLA_SET_INDEX curveTypeIndex);

    /**
     * get curve type
     *
     * @param short
     * @return
     */
    public MS_NLA_SET_INDEX getCurveType();

    /**
     * set osd v0 nonlinear
     *
     * @param short
     * @return
     */
    public boolean setOsdV0Nonlinear(short nonlinearVal);

    /**
     * get osd v0 nonlinear
     *
     * @param short
     * @return
     */
    public short getOsdV0Nonlinear();

    /**
     * set osd v25 nonlinear
     *
     * @param short
     * @return
     */
    public boolean setOsdV25Nonlinear(short nonlinearVal);

    /**
     * get osd v25 nonlinear
     *
     * @param short
     * @return
     */
    public short getOsdV25Nonlinear();

    /**
     * set osd v50 nonlinear
     *
     * @param short
     * @return
     */
    public boolean setOsdV50Nonlinear(short nonlinearVal);

    /**
     * get osd v50 nonlinear
     *
     * @param short
     * @return
     */
    public short getOsdV50Nonlinear();

    /**
     * set osd v75 nonlinear
     *
     * @param short
     * @return
     */
    public boolean setOsdV75Nonlinear(short nonlinearVal);

    /**
     * get osd v75 nonlinear
     *
     * @param short
     * @return
     */
    public short getOsdV75Nonlinear();

    /**
     * set osd v100 nonlinear
     *
     * @param short
     * @return
     */
    public boolean setOsdV100Nonlinear(short nonlinearVal);

    /**
     * get osd v100 nonlinear
     *
     * @param short
     * @return
     */
    public short getOsdV100Nonlinear();

    /**************************************************************************
     * Overscan setting
     *************************************************************************/
    /**
     * set over scan source type
     *
     * @param EN_INPUT_SOURCE_TYPE
     * @return
     */
    public boolean setOverScanSourceType(EnumInputSource SourceType);

    /**
     * get over scan source type
     *
     * @return EN_INPUT_SOURCE_TYPE
     */
    public EnumInputSource getOverScanSourceType();

    /**
     * set over scan horizontal size
     *
     * @param short
     * @return
     */
    public boolean setOverScanHsize(short hSize);

    /**
     * get over scan horizontal size
     *
     * @param short
     * @return
     */
    public short getOverScanHsize();

    /**
     * set over scan horizontal position
     *
     * @param short
     * @return
     */
    public boolean setOverScanHposition(short hPosition);

    /**
     * get over scan horizontal position
     *
     * @param short
     * @return
     */
    public short getOverScanHposition();

    /**
     * set over scan vertical size
     *
     * @param short
     * @return
     */
    public boolean setOverScanVsize(short vSize);

    /**
     * get over scan vertical size
     *
     * @param short
     * @return
     */
    public short getOverScanVsize();

    /**
     * set over scan vertical position
     *
     * @param short
     * @return
     */
    public boolean setOverScanVposition(short vPosition);

    /**
     * get over scan vertical position
     *
     * @param short
     * @return
     */
    public short getOverScanVposition();

    /**************************************************************************
     * SSC setting
     *************************************************************************/
    /**
     * set MIU enable or disable
     *
     * @param boolean
     * @return
     */
    public boolean setMIUenable(boolean Miu_SscEnable);

    /**
     * set MIU enable or disable
     *
     * @return boolean
     */
    public boolean getMIUenalbe();

    /**
     * set MIU modulation span
     *
     * @param int
     * @return
     */
    public boolean setMIUmodulation(int Miu_SscSpan);

    /**
     * get MIU modulation span
     *
     * @return int
     */
    public int getMIUmodulation();

    /**
     * set MIU percentage
     *
     * @param int
     * @return
     */
    public boolean setMIUpercentage(int Miu_SscPercentage);

    /**
     * get MIU percentage
     *
     * @return int
     */
    public int getMIUpercentage();

    /**
     * set LVDS enable or disable
     *
     * @param boolean
     * @return
     */
    public boolean setLVDSenable(boolean Lvds_SscEnable);

    /**
     * set LVDS enable or disable
     *
     * @return boolean
     */
    public boolean getLVDSenalbe();

    /**
     * set LVDS modulation span
     *
     * @param int
     * @return
     */
    public boolean setLVDSmodulation(int Lvds_SscSpan);

    /**
     * get LVDS modulation span
     *
     * @return int
     */
    public int getLVDSmodulation();

    /**
     * set LVDS percentage
     *
     * @param int
     * @return
     */
    public boolean setLVDSpercentage(int Lvds_SscSpan);

    /**
     * get LVDS percentage
     *
     * @return int
     */
    public int getLVDSpercentage();

    /**************************************************************************
     * SSC setting
     *************************************************************************/
    /**
     * set PEQ FO Coarse value
     *
     * @param
     * @return
     */
    public boolean setPeqFoCoarse(int index, int coarseVal);

    /**
     * get PEQ FO Coarse value
     *
     * @return
     */
    public int getPeqFoCoarse(int index);

    /**
     * set PEQ FO Fine value
     *
     * @return
     */
    public boolean setPeqFoFine(int index, int fineVal);

    /**
     * set PEQ FO Fine value
     *
     * @return
     */
    public int getPeqFoFine(int index);

    /**
     * set PEQ gain value
     *
     * @param boolean
     * @return
     */
    public boolean setPeqGain(int index, int gainVal);

    /**
     * get PEQ gain value
     *
     * @param boolean
     * @return
     */
    public int getPeqGain(int index);

    /**
     * set PEQ Q value
     *
     * @param boolean
     * @return
     */
    public boolean setPeqQ(int index, int Qvalue);

    /**
     * get PEQ Q value
     *
     * @param boolean
     * @return
     */
    public int getPeqQ(int index);

    /**************************************************************************
     * Other setting
     *************************************************************************/
    /**
     * get software version
     *
     * @return
     */
    public String getSoftWareVersion();

    /**
     * get board type
     *
     * @return
     */
    public String getBoardType();

    /**
     * get panel type
     *
     * @return
     */
    public String getPanelType();

    /**
     * get the time of code compiled
     *
     * @return
     */
    public String getCompileTime();

    /**
     * set watch dog mode
     */
    public boolean setWatchDogMode(short isEnable);

    /**
     * get watch dog mode
     */
    public short getWatchDogMode();

    /**
     * set test pattern
     */
    public boolean setTestPattern(int testPatternMode);

    /**
     * get test pattern
     */
    public int getTestPattern();

    /**
     * restore the current factory setting to default
     */
    public boolean restoreToDefault();

    /**
     * set power on mode
     */
    public boolean setPowerOnMode(int factoryPowerMode);

    /**
     * get power on mode
     */
    public int getPowerOnMode();

    /**
     * set uart on off
     */
    public boolean setUartOnOff(boolean isEnable);

    /**
     * get uart enable
     */
    public boolean getUartOnOff();

    /**
     * set Dtv Av abnormal delay
     */
    public boolean setDtvAvAbnormalDelay(boolean isEnable);

    /**
     * get Dtv Av abnormal delay
     */
    public boolean getDtvAvAbnormalDelay();

    /**
     * set factory pre set feature
     */
    public boolean setFactoryPreSetFeature(int factoryPreset);

    /**
     * get factory pre set feature
     */
    public int getFactoryPreSetFeature();

    /**
     * set panel swing
     */
    public boolean setPanelSwing(short panleSwingVal);

    /**
     * get panel swing
     */
    public short getPanelSwing();

    /**
     * set audio prescale
     */
    public boolean setAudioPrescale(short audioPrescaleVal);

    /**
     * get audio prescale
     */
    public short getAudioPrescale();

    /**
     * set 3D Self-Adaptive Detect Level
     */
    public boolean set3DSelfAdaptiveLevel(int ThreeDSelfAdaptiveLevel);

    /**
     * get 3D Self-Adaptive Detect Level
     */
    public int get3DSelfAdaptiveLevel();

    /**
     * boolean setPEQ()
     */
    public boolean setPEQ();
}
