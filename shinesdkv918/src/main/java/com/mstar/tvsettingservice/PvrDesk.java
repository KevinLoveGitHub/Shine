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

import com.mstar.android.tvapi.common.PvrManager.OnPvrEventListener;
import com.mstar.android.tvapi.common.vo.CaptureThumbnailResult;
import com.mstar.android.tvapi.common.vo.EnumPvrStatus;
import com.mstar.android.tvapi.common.vo.PvrFileInfo;
import com.mstar.android.tvapi.common.vo.PvrPlaybackSpeed.EnumPvrPlaybackSpeed;
import com.mstar.android.tvapi.common.vo.PvrUsbDeviceLabel.EnumPvrUsbDeviceLabel;
import com.mstar.android.tvapi.common.vo.VideoWindowType;

/**
 * Base Interface of Pvr Service
 *
 * @author steven.li
 */
public interface PvrDesk extends BaseDesk {
    /**
     * Start PVR AlwaysTimeShift record .
     *
     * @return short return PVR status
     * @throws Exception
     */
    public short startAlwaysTimeShiftRecord() throws Exception;

    /**
     * Stop PVR AlwaysTimeShift record.
     *
     * @return short return PVR status
     * @throws Exception
     */
    public short stopAlwaysTimeShiftRecord() throws Exception;

    /**
     * Pause PVR AlwaysTimeShift record.
     *
     * @return short return PVR status
     * @throws Exception
     */
    public short pauseAlwaysTimeShiftRecord() throws Exception;

    /**
     * Start PVR AlwaysTimeShift Playback.
     *
     * @return short
     * @throws Exception
     */
    public short startAlwaysTimeShiftPlayback() throws Exception;

    /**
     * Stop PVR AlwaysTimesShift Playback.
     *
     * @return void
     * @throws Exception
     */
    public void stopAlwaysTimeShiftPlayback() throws Exception;

    /**
     * Check if AlwaysTimeShift playback status is ready.
     *
     * @return boolean
     * @throws Exception
     */
    public boolean isAlwaysTimeShiftPlaybackPaused() throws Exception;

    /**
     * Check if AlwaysTimeShift is recording.
     *
     * @return boolean
     * @throws Exception
     */
    public boolean isAlwaysTimeShiftRecording() throws Exception;

    /**
     * Start PVR record
     *
     * @return EnumPvrStatus
     * @throws Exception
     */
    public EnumPvrStatus startRecord() throws Exception;

    /**
     * Pause PVR record
     *
     * @throws Exception
     */
    public void pauseRecord() throws Exception;

    /**
     * Resume PVR record
     *
     * @throws Exception
     */
    public void resumeRecord() throws Exception;

    /**
     * Stop PVR record
     *
     * @throws Exception
     */
    public void stopRecord() throws Exception;

    /**
     * Get the remaining time of current recording, which is estimated by
     * average bit-rate and free space.
     *
     * @return int
     * @throws Exception
     */
    public int getEstimateRecordRemainingTime() throws Exception;

    /**
     * Start play back
     *
     * @param fileName String
     * @return EnumPvrStatus return status of start playerback
     * @throws Exception
     */
    public EnumPvrStatus startPlayback(String fileName) throws Exception;

    /**
     * Start play back
     *
     * @param fileName String
     * @param playbackTimeInSecond int
     * @return EnumPvrStatus
     * @throws Exception
     */
    public EnumPvrStatus startPlayback(String fileName, int playbackTimeInSecond)
            throws Exception;

    /**
     * Start play back
     *
     * @param fileName String
     * @param playbackTimeInSecond int
     * @param thumbnailPts int
     * @return EnumPvrStatus
     * @throws Exception
     */
    public EnumPvrStatus startPlayback(String fileName, int playbackTimeInSecond, int thumbnailPts)
            throws Exception;

    /**
     * Pause PVR playback
     *
     * @throws Exception
     */
    public void pausePlayback() throws Exception;

    /**
     * Resume PVR playback
     *
     * @throws Exception
     */
    public void resumePlayback() throws Exception;

    /**
     * Stop PVR playback
     *
     * @throws Exception
     */
    public void stopPlayback() throws Exception;

    /**
     * Fast foward playback
     *
     * @throws Exception
     */
    public void doPlaybackFastForward() throws Exception;

    /**
     * Back back playback
     *
     * @throws Exception
     */
    public void doPlaybackFastBackward() throws Exception;

    /**
     * Jump foward playback
     *
     * @throws Exception
     */
    public void doPlaybackJumpForward() throws Exception;

    /**
     * Jump back playback
     *
     * @throws Exception
     */
    public void doPlaybackJumpBackward() throws Exception;

    /**
     * Play back step in
     *
     * @throws Exception
     */
    public void stepInPlayback() throws Exception;

    /**
     * Start PVR playback loop between AB period
     *
     * @param abLoopBeginTime int Loop begin time
     * @param abLoopEndTime int Loop end time
     * @throws Exception
     */
    public void startPlaybackLoop(int abLoopBeginTime, int abLoopEndTime) throws Exception;

    /**
     * Stop playback loop
     *
     * @throws Exception
     */
    public void stopPlaybackLoop() throws Exception;

    /**
     * Set playback speed
     *
     * @param playbackSpeed EnumPvrPlaybackSpeed one of EnumPvrPlaybackSpeed
     * @throws Exception
     */
    public void setPlaybackSpeed(EnumPvrPlaybackSpeed playbackSpeed) throws Exception;

    /**
     * Get playback speed
     *
     * @return EnumPvrPlaybackSpeed one of EnumPvrPlaybackSpeed
     * @throws Exception
     */
    public EnumPvrPlaybackSpeed getPlaybackSpeed() throws Exception;

    /**
     * Jump to a specific time stamp in playback
     *
     * @param jumpToTimeInSeconds int
     * @return boolean
     * @throws Exception
     */
    public boolean jumpPlaybackTime(int jumpToTimeInSeconds) throws Exception;

    /**
     * PVR time shift record start.
     *
     * @return EnumPvrStatus pvr status
     * @throws Exception
     */
    public EnumPvrStatus startTimeShiftRecord() throws Exception;

    /**
     * PVR time shift record stop.
     *
     * @throws Exception
     */
    public void stopTimeShiftRecord() throws Exception;

    /**
     * PVR time shift playback stop.
     *
     * @throws Exception
     */
    public void stopTimeShiftPlayback() throws Exception;

    /**
     * PVR time shift stop.
     *
     * @throws Exception
     */
    public void stopTimeShift() throws Exception;

    /**
     * PVR stop both playback, record and time-shift.
     *
     * @return boolean
     * @throws Exception
     */
    public boolean stopPvr() throws Exception;

    /**
     * Query PVR is recroding or not.
     *
     * @return boolean TRUE : PVR is recording, FALSE: PVR is not recording.
     * @throws Exception
     */
    public boolean isRecording() throws Exception;

    /**
     * Query PVR is playbacking.
     *
     * @return boolean TRUE : PVR is playbacking, FALSE: PVR is not playbacking.
     * @throws Exception
     */
    public boolean isPlaybacking() throws Exception;

    /**
     * Query PVR is timeShift-recording.
     *
     * @return boolean TRUE : PVR is timeShift-recording, FALSE: PVR is not
     *         timeShift-recording.
     * @throws Exception
     */
    public boolean isTimeShiftRecording() throws Exception;

    /**
     * Query PVR is playback Parental Lock.
     *
     * @return boolean TRUE : PVR is Parental Lock, FALSE: PVR is not Parental
     *         Lock.
     * @throws Exception
     */
    public boolean isPlaybackParentalLock() throws Exception;

    /**
     * Query PVR is playback paused.
     *
     * @return boolean TRUE : PVR is playback Pause, FALSE: PVR is not
     *         playbacking.
     * @throws Exception
     */
    public boolean isPlaybackPaused() throws Exception;

    /**
     * Query PVR is record pause Mode.
     *
     * @return boolean TRUE : PVR is record Pause, FALSE: PVR is not record
     *         Pause.
     * @throws Exception
     */
    public boolean isRecordPaused() throws Exception;

    /**
     * Query PVR set playback window.
     *
     * @param videoWindowType VideoWindowType
     * @param containerWidth int
     * @param containerHeight int
     * @return none
     * @throws Exception
     */
    public void setPlaybackWindow(VideoWindowType videoWindowType, int containerWidth,
                                  int containerHeight) throws Exception;

    /**
     * Get Current recording File Name
     *
     * @return String The name of current recording file
     * @throws Exception
     */
    public String getCurRecordingFileName() throws Exception;

    /**
     * Get current playbacking time in secends
     *
     * @return int current playbacking time in secends.
     * @throws Exception
     */
    public int getCurPlaybackTimeInSecond() throws Exception;

    /**
     * Get Current recording File Name
     *
     * @return int The name of current recording file.
     * @throws Exception
     */
    public int getCurRecordTimeInSecond() throws Exception;

    /**
     * Jump to specific PVR file target thumbnail
     *
     * @param thumbnailIndex int
     * @return boolean TRUE : successful, FALSE: fail.
     * @throws Exception
     */
    public boolean jumpToThumbnail(int thumbnailIndex) throws Exception;

    /**
     * PVR timeShift SetTimeShiftFileSize.
     *
     * @param timeShiftFileSizeInKB int
     * @return none
     * @throws Exception
     */
    public void setTimeShiftFileSize(long timeShiftFileSizeInKb) throws Exception;

    /**
     * Capture specific PVR file target thumbnail
     *
     * @return Specific file name
     * @throws Exception
     */
    public CaptureThumbnailResult captureThumbnail() throws Exception;

    /**
     * Get Current playbacking File Name
     *
     * @return String
     * @throws Exception
     */
    public String getCurPlaybackingFileName() throws Exception;

    /**
     * set PVR AlwaysTimeShift playback status as ready. Which means Live DTV
     * image is freezed.
     *
     * @param ready boolean ready or not
     * @return EnumPvrStatus
     * @throws Exception
     */
    public EnumPvrStatus pauseAlwaysTimeShiftPlayback(boolean ready) throws Exception;

    /**
     * PVR time shift playback start.
     *
     * @return EnumPvrStatus
     * @throws Exception
     */
    public EnumPvrStatus startTimeShiftPlayback() throws Exception;

    /**
     * check the read/write speed of curent USB storage device
     *
     * @return int Read/Write speed of current USB storage device
     * @throws Exception
     */
    public int checkUsbSpeed() throws Exception;

    /**
     * To get partition number in storage device
     *
     * @return int partition number in storage device.
     * @throws Exception
     */
    public int getUsbPartitionNumber() throws Exception;

    /**
     * To get Device number
     *
     * @return int Device number
     * @throws Exception
     */
    public int getUsbDeviceNumber() throws Exception;

    /**
     * To get Device index
     *
     * @return short Device index.
     * @throws Exception
     */
    public short getUsbDeviceIndex() throws Exception;

    /**
     * Get usb device label in E_LABEL for identification.
     *
     * @param deviceIndex int index of the device, which changes if other device
     *            is removed.
     * @return EnumPvrUsbDeviceLabel
     * @throws Exception
     */
    public EnumPvrUsbDeviceLabel getUsbDeviceLabel(int deviceIndex) throws Exception;

    /**
     * To get PVR file number in storage device
     *
     * @return int PVR file number in storage device.
     * @throws Exception
     */
    public int getPvrFileNumber() throws Exception;

    /**
     * To get PVR file info sorted by the given key, ascending or descending
     *
     * @param index int index, int nSortKey
     * @return PvrFileInfo PVR file info
     * @throws Exception
     */
    public PvrFileInfo getPvrFileInfo(int index, int nSortKey) throws Exception;

    /**
     * Get LCN of the specific file
     *
     * @param index int Target index file
     * @return int LCN (logical channel number)
     * @throws Exception
     */
    public int getFileLcn(int index) throws Exception;

    /**
     * Get service name (channel name) of the specific file
     *
     * @param fileName String Specific file name
     * @return String Service name
     * @throws Exception
     */
    public String getFileServiceName(String fileName) throws Exception;

    /**
     * Get event name (program name) of the specific file
     *
     * @param fileName String Specific file name
     * @return String Event name
     * @throws Exception
     */
    public String getFileEventName(String fileName) throws Exception;

    /**
     * Assign specific PVR file info for thumbnail using
     *
     * @param fileName String Specific file name
     * @return boolean TRUE : successful;FALSE: fail.
     * @throws Exception
     */
    public boolean assignThumbnailFileInfoHandler(String fileName) throws Exception;

    /**
     * Get specific PVR file thumbnail number
     *
     * @return int thumbnail number
     * @throws Exception
     */
    public int getThumbnailNumber() throws Exception;

    /**
     * Get specific PVR file target thumbnail complete path
     *
     * @param index int Target index thumbnail
     * @return String thumbnail complete path
     * @throws Exception
     */
    public String getThumbnailPath(int index) throws Exception;

    /**
     * Get specific PVR file target thumbnail display UI info
     *
     * @param index int Target index thumbnail
     * @return String display UI info
     * @throws Exception
     */
    public String getThumbnailDisplay(int index) throws Exception;

    /**
     * Get specific PVR file target thumbnail TimeStamp
     *
     * @param index int Target index thumbnail
     * @return int[] int[0]: JumpTimeStamp(sec), int[1]:JumpCapturePTS(VDEC use)
     * @throws Exception
     */
    public int[] getThumbnailTimeStamp(int index) throws Exception;

    /**
     * Establish database by another USB Device
     *
     * @param deviceIndex short Device index
     * @return boolean TRUE : establish Success;FALSE: establish Failure.
     * @throws Exception
     */
    public boolean changeDevice(short deviceIndex) throws Exception;

    /**
     * set PVR parameters before PVR start
     *
     * @param path String usb disk path
     * @param fileType short usb disk file type
     * @return boolean TRUE : set Success;FALSE: set Failure.
     * @throws Exception
     */
    public boolean setPVRParas(String path, short fileType) throws Exception;

    public void setOnPvrEventListener(OnPvrEventListener listener);

    /**
     * get PVR Recorded file duration time
     *
     * @param filename String recorded file name
     * @return int duration time.
     * @throws Exception
     */
    public int getRecordedFileDurationTime(String filename) throws Exception;

    /**
     * delete the recorded file
     *
     * @param index don't care
     * @param filename String recorded file name
     * @return void
     * @throws Exception
     */
    public void deletefile(int index, String filename) throws Exception;

    /**
     * turn on/off PVR Record all function
     *
     * @param bRecordAll boolean on/off
     * @return void
     * @throws Exception
     */
    public void setRecordAll(boolean bRecordAll) throws Exception;

    /**
     * getMetadataSortKey
     *
     * @param
     * @return int: Pvr sort key
     * @throws Exception
     */
    public int getMetadataSortKey() throws Exception;

    /**
     * setMetadataSortKey
     *
     * @param int: Pvr sort key
     * @return
     * @throws Exception
     */
    public void setMetadataSortKey(int nSortKey) throws Exception;

    /**
     * setMetadataSortAscending
     *
     * @param boolean: sort by ascending or not
     * @return
     * @throws Exception
     */
    public void setMetadataSortAscending(boolean bIsAscend) throws Exception;

    /**
     * isMetadataSortAscending
     *
     * @param
     * @return boolean: sort by ascending or not
     * @throws Exception
     */
    public boolean isMetadataSortAscending() throws Exception;

    /**
     * isMetadataSortAscending
     *
     * @param
     * @return boolean: sort by ascending or not
     * @throws Exception
     */
    public boolean createMetaData(String strMountPath) throws Exception;

    /**
     * clearMetaData
     *
     * @param
     * @return
     * @throws Exception
     */
    public void clearMetaData() throws Exception;

    /**
     * get pvr mount path
     *
     * @param
     * @return String mount path
     * @throws Exception
     */
    public String getPvrMountPath() throws Exception;
}
