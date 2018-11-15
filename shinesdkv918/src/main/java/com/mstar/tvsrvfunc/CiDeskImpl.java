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

import com.mstar.android.tvapi.dtv.common.CiManager.CredentialValidDateRange;
import com.mstar.android.tvapi.dtv.common.CiManager.OnCiEventListener;
import com.mstar.android.tvapi.dtv.common.DtvManager;
import com.mstar.android.tvapi.dtv.vo.EnumCardState;
import com.mstar.android.tvapi.dtv.vo.EnumMmiType;
import com.mstar.tvsettingservice.CiDesk;

public class CiDeskImpl extends BaseDeskImpl implements CiDesk {
    private static CiDeskImpl ciMgrImpl = null;

    private CiDeskImpl() {
    }

    public static CiDeskImpl getCiMgrInstance() {
        if (ciMgrImpl == null)
            ciMgrImpl = new CiDeskImpl();
        return ciMgrImpl;
    }

    /**
     * Credential Mode Set (Set CI+ Credential Mode)
     *
     * @param credentialMode short CI Credential Mode
     * @throws Exception
     */
    public void setCiCredentialMode(short credentialMode) throws Exception {
        DtvManager.getCiManager().setCiCredentialMode(credentialMode);
    }

    /**
     * Check CI+ Credential Mode is valid or not
     *
     * @param credentialMode short one of CI Credential Mode
     * @return boolean
     * @throws Exception
     */
    public boolean isCiCredentialModeValid(short credentialMode) throws Exception {
        return DtvManager.getCiManager().isCiCredentialModeValid(credentialMode);
    }

    /**
     * To enter the CIMMI menu
     *
     * @throws Exception
     */
    public void enterMenu() throws Exception {
        DtvManager.getCiManager().enterMenu();
    }

    /**
     * To return CIMMI current menu
     *
     * @throws Exception
     */
    public void backMenu() throws Exception {
        DtvManager.getCiManager().backMenu();
    }

    /**
     * Check if the content received in CIMMI existed, UI will draw graph
     * according to the result.
     *
     * @return TRUE: data existed; FALSE: data not existed.
     * @throws Exception
     */
    public boolean isDataExisted() throws Exception {
        return DtvManager.getCiManager().isDataExisted();
    }

    /**
     * To get CIMMI type
     *
     * @return EnumMmiType one of MMI Type
     * @throws Exception
     */
    public EnumMmiType getMmiType() throws Exception {
        return DtvManager.getCiManager().getMmiType();
    }

    /**
     * To get menu string of CI menu
     *
     * @return String Menu string
     * @throws Exception
     */
    public String getMenuString() throws Exception {
        return DtvManager.getCiManager().getMenuString();
    }

    /**
     * To get menu title string
     *
     * @return String Menu title string
     * @throws Exception
     */
    public String getMenuTitleString() throws Exception {
        return DtvManager.getCiManager().getMenuTitleString();
    }

    /**
     * Get Menu Bottom String
     *
     * @return String Menu bottom string
     * @throws Exception
     */
    public String getMenuBottomString() throws Exception {
        return DtvManager.getCiManager().getMenuBottomString();
    }

    /**
     * To get list title string
     *
     * @return String List title string
     * @throws Exception
     */
    public String getListTitleString() throws Exception {
        return DtvManager.getCiManager().getListTitleString();
    }

    /**
     * To get List Title Length
     *
     * @return String List title string
     * @throws Exception
     */
    public int getListTitleLength() throws Exception {
        return DtvManager.getCiManager().getListTitleLength();
    }

    /**
     * To get Menu Choice number
     *
     * @return short Menu Choice number
     * @throws Exception
     */
    public short getMenuChoiceNumber() throws Exception {
        return DtvManager.getCiManager().getMenuChoiceNumber();
    }

    /**
     * To get List Choice number
     *
     * @return short List Choice number
     * @throws Exception
     */
    public short getListChoiceNumber() throws Exception {
        return DtvManager.getCiManager().getListChoiceNumber();
    }

    /**
     * To get blind Answer
     *
     * @return short show password or not
     * @throws Exception
     */
    public short getEnqBlindAnswer() throws Exception {
        return DtvManager.getCiManager().getEnqBlindAnswer();
    }

    /**
     * To get List Subtitle Length
     *
     * @return String List subtitle length
     * @throws Exception
     */
    public int getListSubtitleLength() throws Exception {
        // TODO
        // return DtvManager.getCiManager().getListSubtitleString();
        return 1;
    }

    /**
     * To get List Subtitle String
     *
     * @return String List subtitle string
     * @throws Exception
     */
    public String getListSubtitleString() throws Exception {
        return DtvManager.getCiManager().getListSubtitleString();
    }

    /**
     * Get list bottom string
     *
     * @return String List bottom string
     * @throws Exception
     */
    public String getListBottomString() throws Exception {
        return DtvManager.getCiManager().getListBottomString();
    }

    /**
     * Get menu selection string
     *
     * @param index Menu index
     * @return String Menu selection string
     * @throws Exception
     */
    public String getMenuSelectionString(int index) throws Exception {
        return DtvManager.getCiManager().getMenuSelectionString(index);
    }

    /**
     * Get list selection string
     *
     * @param index List index
     * @return String List selection string
     * @throws Exception
     */
    public String getListSelectionString(int index) throws Exception {
        return DtvManager.getCiManager().getListSelectionString(index);
    }

    /**
     * Get enq String
     *
     * @return String Enq string
     * @throws Exception
     */
    public String getEnqString() throws Exception {
        return DtvManager.getCiManager().getEnqString();
    }

    /**
     * Get Menu Title Length
     *
     * @return int Menu title length
     * @throws Exception
     */
    public int getMenuTitleLength() throws Exception {
        return DtvManager.getCiManager().getMenuTitleLength();
    }

    /**
     * Get list subtitle length
     *
     * @return int List subtitle length
     * @throws Exception
     */
    public int getMenuSubtitleLength() throws Exception {
        return DtvManager.getCiManager().getMenuSubtitleLength();
    }

    /**
     * To get Menu Subtitle String
     *
     * @return String Menu Title String
     * @throws Exception
     */
    public String getMenuSubtitleString() throws Exception {
        return DtvManager.getCiManager().getMenuSubtitleString();
    }

    /**
     * To get Menu Bottom Length
     *
     * @return int Menu Bottom Length
     * @throws Exception
     */
    public int getMenuBottomLength() throws Exception {
        return DtvManager.getCiManager().getMenuBottomLength();
    }

    /**
     * To close CIMMI
     *
     * @throws Exception
     */
    public void close() throws Exception {
        DtvManager.getCiManager().close();
    }

    /**
     * Get list bottom length
     *
     * @return int List bottom length
     * @throws Exception
     */
    public int getListBottomLength() throws Exception {
        return DtvManager.getCiManager().getListBottomLength();
    }

    /**
     * Get CI card password length
     *
     * @return short Enq length
     * @throws Exception
     */
    public short getEnqLength() throws Exception {
        return DtvManager.getCiManager().getEnqLength();
    }

    /**
     * Get CI card password answer length
     *
     * @return short Enq answer length
     * @throws Exception
     */
    public short getEnqAnsLength() throws Exception {
        return DtvManager.getCiManager().getEnqAnsLength();
    }

    /**
     * Back to CI card password menu
     *
     * @return boolean
     * @throws Exception
     */
    public boolean backEnq() throws Exception {
        return DtvManager.getCiManager().backEnq();
    }

    /**
     * Enter CI card password
     *
     * @param password String password
     * @return boolean
     * @throws Exception
     */
    public boolean answerEnq(String password) throws Exception {
        return DtvManager.getCiManager().answerEnq(password);
    }

    /**
     * To enter CIMMI menu
     *
     * @param index short menu index
     * @throws Exception
     */
    public void answerMenu(short index) throws Exception {
        DtvManager.getCiManager().answerMenu(index);
    }

    /**
     * Get CI card state
     *
     * @return EnumCardState card state
     * @throws Exception
     */
    public EnumCardState getCardState() throws Exception {
        return DtvManager.getCiManager().getCardState();
    }

    /**
     * Is CI Menu On
     *
     * @return boolean return CI menu on or not
     * @throws Exception
     */
    public boolean isCiMenuOn() throws Exception {
        return DtvManager.getCiManager().isCiMenuOn();
    }

    /**
     * Get credential valid date range
     *
     * @return CredentialValidDateRange credential valid date range
     * @throws Exception
     */
    public CredentialValidDateRange getCiCredentialValidRange() throws Exception {
        return DtvManager.getCiManager().getCiCredentialValidRange();
    }

    /**
     * set Debug Mode
     *
     * @return none
     * @throws Exception
     */
    public void setDebugMode(boolean mode) throws Exception {
        DtvManager.getCiManager().setDebugMode(mode);
    }

    /**
     * Register setOnCiEventListener(OnCiEventListener listener), your listener
     * will be triggered when the events posted from native code.
     *
     * @param listener OnCiEventListener
     */
    public void setOnCiEventListener(OnCiEventListener listener) {
        try {
            DtvManager.getCiManager().setOnCiEventListener(listener);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
