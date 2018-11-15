/**
 * MStar Software Copyright (c) 2011 - 2012 MStar Semiconductor, Inc. All rights
 * reserved.
 * <p/>
 * All software, firmware and related documentation herein (��MStar Software��)
 * are intellectual property of MStar Semiconductor, Inc. (��MStar��) and
 * protected by law, including, but not limited to, copyright law and
 * international treaties. Any use, modification, reproduction, retransmission,
 * or republication of all or part of MStar Software is expressly prohibited,
 * unless prior written permission has been granted by MStar.
 * <p/>
 * By accessing, browsing and/or using MStar Software, you acknowledge that you
 * have read, understood, and agree, to be bound by below terms (��Terms��) and
 * to comply with all applicable laws and regulations:
 * <p/>
 * 1. MStar shall retain any and all right, ownership and interest to MStar
 * Software and any modification/derivatives thereof. No right, ownership, or
 * interest to MStar Software and any modification/derivatives thereof is
 * transferred to you under Terms.
 * <p/>
 * 2. You understand that MStar Software might include, incorporate or be
 * supplied together with third party��s software and the use of MStar Software
 * may require additional licenses from third parties. Therefore, you hereby
 * agree it is your sole responsibility to separately obtain any and all third
 * party right and license necessary for your use of such third party��s
 * software.
 * <p/>
 * 3. MStar Software and any modification/derivatives thereof shall be deemed as
 * MStar��s confidential information and you agree to keep MStar��s confidential
 * information in strictest confidence and not disclose to any third party.
 * <p/>
 * 4. MStar Software is provided on an ��AS IS�� basis without warranties of any
 * kind. Any warranties are hereby expressly disclaimed by MStar, including
 * without limitation, any warranties of merchantability, non-infringement of
 * intellectual property rights, fitness for a particular purpose, error free
 * and in conformity with any international standard. You agree to waive any
 * claim against MStar for any loss, damage, cost or expense that you may incur
 * related to your use of MStar Software. In no event shall MStar be liable for
 * any direct, indirect, incidental or consequential damages, including without
 * limitation, lost of profit or revenues, lost or damage of data, and
 * unauthorized system use. You agree that this Section 4 shall still apply
 * without being affected even if MStar Software has been modified by MStar in
 * accordance with your request or instruction for your use, except otherwise
 * agreed by both parties in writing.
 * <p/>
 * 5. If requested, MStar may from time to time provide technical supports or
 * services in relation with MStar Software to you for your use of MStar
 * Software in conjunction with your or your customer��s product (��Services��).
 * You understand and agree that, except otherwise agreed by both parties in
 * writing, Services are provided on an ��AS IS�� basis and the warranty
 * disclaimer set forth in Section 4 above shall apply.
 * <p/>
 * 6. Nothing contained herein shall be construed as by implication, estoppels
 * or otherwise: (a) conferring any license or right to use MStar name,
 * trademark, service mark, symbol or any other identification; (b) obligating
 * MStar or any of its affiliates to furnish any person, including without
 * limitation, you and your customers, any assistance of any kind whatsoever, or
 * any information; or (c) conferring any license or right under any
 * intellectual property right.
 * <p/>
 * 7. These terms shall be governed by and construed in accordance with the laws
 * of Taiwan, R.O.C., excluding its conflict of law rules. Any and all dispute
 * arising out hereof or related hereto shall be finally settled by arbitration
 * referred to the Chinese Arbitration Association, Taipei in accordance with
 * the ROC Arbitration Law and the Arbitration Rules of the Association by three
 * (3) arbitrators appointed in accordance with the said Rules. The place of
 * arbitration shall be in Taipei, Taiwan and the language shall be English. The
 * arbitration award shall be final and binding to both parties.
 */

package cn.shine.sdk.tv.bean;

/**
 * <p>
 * Title: Program Info
 * </p>
 * <p/>
 * <p>
 * Description: Define the struct for get program information
 * </p>
 * <p/>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p/>
 * <p>
 * Company: Mstarsemi Inc.
 * </p>
 *
 * @author Jacky.Lin
 * @version 1.0
 */
public class ProgramInfo {
    /**
     * program information of program index
     */
    public int queryIndex;
    /**
     * program information of program number
     */
    public int number;
    /**
     * Major Channnel Number,Range 1 and 99
     */
    public short majorNum;
    /**
     * Minor Channel Number,Range 0 to 999
     */
    public short minorNum;
    /**
     * Program ID in db
     */
    public int progId;
    /**
     * Program favorite
     */
    public short favorite;
    /**
     * Program lock
     */
    public boolean isLock;
    /**
     * Program skip
     */
    public boolean isSkip;
    /**
     * Program scramble
     */
    public boolean isScramble;
    /**
     * Program delete
     */
    public boolean isDelete;
    /**
     * Program visible
     */
    public boolean isVisible;
    /**
     * Program Hide
     */
    public boolean isHide;
    /**
     * Program service type
     */
    public short serviceType;
    /**
     * Program screen mute status
     */
    public int screenMuteStatus;
    /**
     * Program name
     */
    public String serviceName;

    /**
     * Program's mux Frequency
     */
    public int frequency;
    /**
     * Program's transport ID
     */
    public int transportStreamId;
    /**
     * Program's service ID
     */
    public int serviceId;
    /**
     * Prgraom's antenna type
     */
    public int antennaType;

    public ProgramInfo() {
        queryIndex = 0;
        number = 0;
        majorNum = 0;
        minorNum = 0;
        progId = 0;
        favorite = 0;
        isLock = false;
        isSkip = false;
        isScramble = false;
        isDelete = false;
        isVisible = false;
        isHide = false;
        serviceType = 0;
        screenMuteStatus = 0;
        serviceName = "";
        frequency = 0;
        transportStreamId = 0;
        serviceId = 0;
        antennaType = 0;
    }

    public ProgramInfo(int queryIndex, int number, int majorNum, int minorNum, int progId, short favorite, boolean isLock, boolean isSkip, boolean isScramble, boolean isDelete, boolean isVisible, boolean isHide, short serviceType, int screenMuteStatus, String serviceName, int frequency, int transportStreamId, int serviceId, int antennaType) {
        this.queryIndex = queryIndex;
        this.number = number;
        this.majorNum = (short) majorNum;
        this.minorNum = (short) minorNum;
        this.progId = progId;
        this.favorite = favorite;
        this.isLock = isLock;
        this.isSkip = isSkip;
        this.isScramble = isScramble;
        this.isDelete = isDelete;
        this.isVisible = isVisible;
        this.isHide = isHide;
        this.serviceType = serviceType;
        this.screenMuteStatus = screenMuteStatus;
        this.serviceName = serviceName;
        this.frequency = frequency;
        this.transportStreamId = transportStreamId;
        this.serviceId = serviceId;
        this.antennaType = antennaType;
    }

}
