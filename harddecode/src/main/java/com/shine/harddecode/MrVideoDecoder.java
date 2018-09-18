package com.shine.harddecode;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Build;
import android.util.Log;
import android.view.Surface;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by Lemon on 2017/3/16.
 */

public class MrVideoDecoder {
    public static final String TAG = "MrVideoDecoder";

    private MediaCodec mDecoder;
    private MediaFormat mFormat;
    private MediaCodec.BufferInfo mBufferInfo;
    private ByteBuffer[] mInputBuffers;
    private ByteBuffer[] mOutputBuffers;
    private boolean bDecoderStart;

    public MrVideoDecoder() {
    }

    public void createDecoder(int width, int height, String mine, Surface surface) {
        try {
            mFormat = MediaFormat.createVideoFormat(mine, width, height);
            mDecoder = MediaCodec.createDecoderByType(mine);
            //mFormat = MediaFormat.createVideoFormat("video/avc", 1920, 1080);
            //mDecoder = MediaCodec.createDecoderByType("video/avc");
            mDecoder.configure(mFormat, surface, null, 0);
            mDecoder.start();
            Log.d(TAG, "decoder start");
            /* Synchronous API using buffer arrays */
            mBufferInfo = new MediaCodec.BufferInfo();
            mInputBuffers = mDecoder.getInputBuffers();
            mOutputBuffers = mDecoder.getOutputBuffers();
            mDecoder.getOutputBuffers();
            bDecoderStart = true;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            bDecoderStart = false;
            release();
        } finally {

        }
    }

    public void decodeFrame(byte[] buf, int frameSize) {
        synchronized (mDecoder) {
            int inputIndex = mDecoder.dequeueInputBuffer(0);
            if (inputIndex >= 0) {
                ByteBuffer inputBuffer;
                // fill inputBuffers[inputBufferId] with valid data
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    inputBuffer = mInputBuffers[inputIndex];
                } else {
                    inputBuffer = mDecoder.getInputBuffer(inputIndex);
                }

                //inputBuffer.put(buf, offset, length);
                if (inputBuffer != null) {
                    inputBuffer.clear();
                    inputBuffer.put(buf, 0, frameSize);
                    //Log.d(TAG, "presentationTimeUs = " + mBufferInfo.presentationTimeUs);
                    //mDecoder.queueInputBuffer(inputIndex, 0, frameSize, System.currentTimeMillis(), 0);
                    mDecoder.queueInputBuffer(inputIndex, 0, frameSize, mBufferInfo.presentationTimeUs, 0);
                }
            }
            int outIndex = 0;
            while (outIndex >= 0) {
                outIndex = mDecoder.dequeueOutputBuffer(mBufferInfo, 500);
                if (outIndex >= 0) {
                    // outputBuffers[outputBufferId] is ready to be processed or rendered.
                    mDecoder.releaseOutputBuffer(outIndex, true);
                } else if (outIndex == MediaCodec.INFO_OUTPUT_BUFFERS_CHANGED) {
                    mOutputBuffers = mDecoder.getOutputBuffers();
                } else if (outIndex == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED) {
                    // Subsequent data will conform to new format.
                    mFormat = mDecoder.getOutputFormat();
                }
            }
        }

    }

    public boolean getStart() {
        return bDecoderStart;
    }

    public void release() {
        if (mDecoder != null) {
            bDecoderStart = false;
            synchronized (mDecoder) {
                try {
                    mDecoder.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    mDecoder.release();
                    mDecoder = null;
                    Log.d(TAG, "decoder release!");
                }
            }
        }
    }
}

