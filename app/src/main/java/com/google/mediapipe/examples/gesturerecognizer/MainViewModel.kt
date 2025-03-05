package com.google.mediapipe.examples.gesturerecognizer

import android.content.Context
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var _delegate: Int = GestureRecognizerHelper.DELEGATE_CPU
    private var _minHandDetectionConfidence: Float =
        GestureRecognizerHelper.DEFAULT_HAND_DETECTION_CONFIDENCE
    private var _minHandTrackingConfidence: Float = GestureRecognizerHelper
        .DEFAULT_HAND_TRACKING_CONFIDENCE
    private var _minHandPresenceConfidence: Float = GestureRecognizerHelper
        .DEFAULT_HAND_PRESENCE_CONFIDENCE
    private var _gestureRecognizerHelper: GestureRecognizerHelper? = null

    val currentDelegate: Int get() = _delegate
    val currentMinHandDetectionConfidence: Float
        get() = _minHandDetectionConfidence
    val currentMinHandTrackingConfidence: Float
        get() = _minHandTrackingConfidence
    val currentMinHandPresenceConfidence: Float
        get() = _minHandPresenceConfidence

    fun setDelegate(delegate: Int) {
        _delegate = delegate
        updateGestureRecognizerHelper()
    }

    fun setMinHandDetectionConfidence(confidence: Float) {
        _minHandDetectionConfidence = confidence
        updateGestureRecognizerHelper()
    }

    fun setMinHandTrackingConfidence(confidence: Float) {
        _minHandTrackingConfidence = confidence
        updateGestureRecognizerHelper()
    }

    fun setMinHandPresenceConfidence(confidence: Float) {
        _minHandPresenceConfidence = confidence
        updateGestureRecognizerHelper()
    }
    fun setLanguage(language: String) {
        _gestureRecognizerHelper?.updateRecognizerTask(language)
    }

    fun updateGestureRecognizerHelper() {
        _gestureRecognizerHelper?.let {
            it.clearGestureRecognizer() // Clear the old instance
            it.setupGestureRecognizer() // Set up a new instance with updated parameters
        }
    }
    fun getGestureRecognizerHelper(context: Context, listener: GestureRecognizerHelper.GestureRecognizerListener): GestureRecognizerHelper {
        if (_gestureRecognizerHelper == null) {
            _gestureRecognizerHelper = GestureRecognizerHelper(
                minHandDetectionConfidence = _minHandDetectionConfidence,
                minHandTrackingConfidence = _minHandTrackingConfidence,
                minHandPresenceConfidence = _minHandPresenceConfidence,
                currentDelegate = _delegate,
                context = context,
                gestureRecognizerListener = listener
            )
        }
        return _gestureRecognizerHelper!!
    }
}
