////IShineSystemManage.aidl
////version 0.1
////author: byh
////time 2013-04-15 14:00

package com.shine.systemmanage.aidl;
interface IShineSystemManage
{
////tts///////////////////////////////////////////////////
////actionMode 0 startNewTTS  1 stop  2 Volume
	int TTSChannel1(int nActionMode, String strValue);
	int TTSChannel2(int nActionMode, String strValue);
////screenshot//////////////////////////
	int ScreenShot(String strStorePath, int w, int h);

////power, rtc///////////////////////////////////////////////////
	int Shutdown();
	String GetPowerWakeTime();
	int SetPowerWakeTime(String strTime);//"2013-04-15 13:58:00"
	String GetRtcTime();
	int SetRtcTime(String strTime);//"2013-04-15 13:58:00"
////display///////////////////////////////////////////////////
	int EnableDisplay();
	int DisableDisplay();
	int GetDisplayState();

////goio pin5, 6; setMode getValue setValue	nMode: 0 Out 1 In////////
	int GPIOGetMode();
	int GPIOSetMode(int nPin, int nMode);
	int GPIOGetValue(int nPin);
	int GPIOSetValue(int nPin, int nVal);

////panel///////////////////////////////////////////////////
////back light 0-255
	int GetPanelBackLight();
	int SetPanelBackLight(int nVal);
////mirror default 0, 1 : ratate 180 degre
	int GetPanelMirrorState();
	int SetPanelMirror(int nMirror);
////solution
    int SetPanelSolution(int w, int h);
    String GetPanelSolution();//1024x768
////oddEven  default 0;
    int SwitchPanelOddEven(int nSwitch);
    int GetPanelOddEventState();


	int QueryInterfaceByName(String strName);//return 0 ok,can use; -1 fail,can't implemented
	int DetectInputSource(int nSourceIndex);//nSourceIndex: 0 vga 2 cvbs 25 hdmi; return : 0 no plug in; 1 pluged in
	int SetBackLightOffWhenPoweron(int nSwitch);//nSwitch 1: offWhenPowerOn 0: onWhenPowerOn
	int SetPanelNameToModelFile(String strPanelName);//strPanelName: such as  AU20_T200XW02.ini, FullHD_CMO216_H1L01.ini
	int SetPanelIniStringPairs(String strPairs);//such as m_bPanelSwapPort:1:m_wPanelWidth:1366
}