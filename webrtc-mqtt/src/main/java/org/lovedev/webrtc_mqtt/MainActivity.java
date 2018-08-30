package org.lovedev.webrtc_mqtt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.webrtc.AudioSource;
import org.webrtc.AudioTrack;
import org.webrtc.Camera2Enumerator;
import org.webrtc.CameraEnumerator;
import org.webrtc.EglBase;
import org.webrtc.Logging;
import org.webrtc.MediaConstraints;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.SdpObserver;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.SurfaceViewRenderer;
import org.webrtc.VideoCapturer;
import org.webrtc.VideoSource;
import org.webrtc.VideoTrack;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {
    private static final String VIDEO_TRACK_ID = "ARDAMSv0";
    private static final String TAG = "MainActivity";
    private SurfaceViewRenderer localVideoView;
    private EglBase eglBase;
    private PeerConnectionFactory mPeerConnectionFactory;
    MediaConstraints audioConstraints;
    MediaConstraints videoConstraints;
    VideoSource videoSource;
    VideoTrack localVideoTrack;
    AudioSource audioSource;
    AudioTrack localAudioTrack;
    private MediaConstraints constraints;
    private VideoTrack videoTrack;
    private AudioTrack audioTrack;
    private ArrayList<PeerConnection.IceServer> iceServers;
    private PeerConnection localPeer;
    private PeerConnection remotePeer;
    private SdpObserver localObserver;
    private SdpObserver remoteObserver;
    private SurfaceTextureHelper videoCapturerSurfaceTextureHelper;
    private PeerConnectionFactory peerConnectionFactory;
    private VideoCapturer localVideoCapturer;
    private VideoSource localVideoSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initVideos();
        initLocalStream();
    }


    private void initViews() {
        localVideoView = findViewById(R.id.glview_call);
    }

    private void initVideos() {
        eglBase = EglBase.create();
        localVideoView.init(eglBase.getEglBaseContext(), null);
        localVideoView.setZOrderMediaOverlay(true);
    }


    private void initLocalStream() {
        /* Initialize SurfaceTextureHelper for video */
        videoCapturerSurfaceTextureHelper = SurfaceTextureHelper.create("CaptureThread", eglBase.getEglBaseContext());

        /* Initialize Peer Connection Factory */
        PeerConnectionFactory.initialize(PeerConnectionFactory.InitializationOptions.builder(getApplicationContext())
                .setEnableInternalTracer(true)
                .createInitializationOptions());
        peerConnectionFactory = PeerConnectionFactory.builder().createPeerConnectionFactory();


        /* Now create a VideoCapturer instance. Callback methods are there if you want to do something! Duh! */
        localVideoCapturer = createVideoCapturer();

        /* Create local video source instance */
        assert peerConnectionFactory != null;
        localVideoSource = peerConnectionFactory.createVideoSource(localVideoCapturer.isScreencast());

        /* Initialize local video capturer */
        assert localVideoSource != null;
        localVideoCapturer.initialize(videoCapturerSurfaceTextureHelper, getApplicationContext(), localVideoSource.getCapturerObserver());

        /* Start video capture */
        localVideoCapturer.startCapture(640, 480, 15);

        /* Create video track instance for local video */
        localVideoTrack = peerConnectionFactory.createVideoTrack(VIDEO_TRACK_ID, localVideoSource);
        localVideoTrack.setEnabled(true);

        /* Sink local video surface view with local video track */
        assert localVideoView != null;
        localVideoTrack.addSink(localVideoView);
    }





    private VideoCapturer createVideoCapturer() {
        VideoCapturer videoCapturer;
        Logging.d(TAG, "Creating capturer using camera1 API.");
        //        videoCapturer = createCameraCapturer(new Camera1Enumerator(false));

        videoCapturer = createCameraCapturer(new Camera2Enumerator(this));

        return videoCapturer;
    }


    private @Nullable
    VideoCapturer createCameraCapturer(CameraEnumerator enumerator) {
        final String[] deviceNames = enumerator.getDeviceNames();

        // First, try to find front facing camera
        Logging.d(TAG, "Looking for front facing cameras.");
        for (String deviceName : deviceNames) {
            if (enumerator.isFrontFacing(deviceName)) {
                Logging.d(TAG, "Creating front facing camera capturer.");
                VideoCapturer videoCapturer = enumerator.createCapturer(deviceName, null);

                if (videoCapturer != null) {
                    return videoCapturer;
                }
            }
        }

        // Front facing camera not found, try something else
        Logging.d(TAG, "Looking for other cameras.");
        for (String deviceName : deviceNames) {
            if (!enumerator.isFrontFacing(deviceName)) {
                Logging.d(TAG, "Creating other camera capturer.");
                VideoCapturer videoCapturer = enumerator.createCapturer(deviceName, null);

                if (videoCapturer != null) {
                    return videoCapturer;
                }
            }
        }

        return null;
    }


}
