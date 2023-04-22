package com.example.a5month_youtube.ui.player

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.example.a5month_youtube.R
import com.example.a5month_youtube.core.ext.ConnectionLiveData
import com.example.a5month_youtube.core.ui.BaseActivity
import com.example.a5month_youtube.databinding.ActivityPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.Util
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayerActivity : BaseActivity<ActivityPlayerBinding, PlayerViewModel>() {

    private var player: ExoPlayer? = null
    private var playWhenReady = true
    private var currentItem = 0
    private var playbackPosition = 0L

    override val viewModel: PlayerViewModel by viewModel()

    override fun checkConnection() {
        super.checkConnection()
        ConnectionLiveData(application).observe(this) { isConnected ->
            if (isConnected) {
                binding.noInternetConnection.visibility = View.GONE
                binding.internetConnection.visibility = View.VISIBLE
            } else {
                binding.noInternetConnection.visibility = View.VISIBLE
                binding.internetConnection.visibility = View.GONE
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private fun initializePlayer() {
        player = ExoPlayer.Builder(this@PlayerActivity)
            .build()
            .also { exoPlayer ->
                binding.videoView.player = exoPlayer
            }

        val youtubeLink = "https://www.youtube.com/watch?v=${intent.getStringExtra("id1")}"

        object : YouTubeExtractor(this) {
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>?,
                videoMeta: VideoMeta?
            ) {
                if (ytFiles != null) {

                    val videoTag = 133
                    val audioTag = 140

                    val videoUri = ytFiles.get(videoTag).url
                    val audioUri = ytFiles.get(audioTag).url

                    val audioSource =
                        ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
                            .createMediaSource(MediaItem.fromUri(audioUri))

                    val videoSource =
                        ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
                            .createMediaSource(MediaItem.fromUri(videoUri))


                    player?.setMediaSource(MergingMediaSource(true, videoSource, audioSource), true)
                    player?.playWhenReady = playWhenReady
                    player?.seekTo(currentItem, playbackPosition)
                    player?.prepare()
                }
            }

        }.extract(youtubeLink)
    }

    override fun inflateViewBinding(): ActivityPlayerBinding {
        return ActivityPlayerBinding.inflate(layoutInflater)
    }

    override fun initViewModel() {
        super.initViewModel()
        val getId = intent.getStringExtra("id1")
        val getTitle = intent.getStringExtra("title1")
        val getDesc = intent.getStringExtra("desc1")
        viewModel.getVideo(getId!!).observe(this) {
            binding.tvTitle.text = getTitle
            binding.tvDesc.text = getDesc
        }
    }

    public override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initializePlayer()
        }
    }

    public override fun onResume() {
        super.onResume()
        hideSystemUi()
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer()
        }
    }

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.videoView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    public override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    public override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    override fun initListener() {
        super.initListener()
        binding.backTv.setOnClickListener { finish() }
    }

    private fun releasePlayer() {
        player?.let { exoPlayer ->
            playbackPosition = exoPlayer.currentPosition
            currentItem = exoPlayer.currentMediaItemIndex
            playWhenReady = exoPlayer.playWhenReady
            exoPlayer.release()
        }
        player = null
    }

    override fun initView() {
        super.initView()
        val bt_fullscreen = findViewById<ImageButton>(R.id.fullscreen)

        var isFullscreen = false
        var isLandscape = false

        bt_fullscreen.setOnClickListener {
            if (!isFullscreen) {
                bt_fullscreen.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.baseline_fullscreen_24
                    )
                )
                if (requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    isLandscape = true
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                } else {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
                }
            } else {
                bt_fullscreen.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.baseline_fullscreen_exit_24
                    )
                )
                if (isLandscape) {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
                } else {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                }
            }
            isFullscreen = !isFullscreen
        }
    }
}
