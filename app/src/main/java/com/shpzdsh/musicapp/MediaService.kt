package com.shpzdsh.musicapp

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.shpzdsh.musicapp.domain.MusicRepository
import com.shpzdsh.musicapp.domain.models.CurrentPlayingTrackInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MediaService : MediaSessionService() {


    // Внедрение репозитория через Koin
    private val musicRepository: MusicRepository by inject()
    private val serviceScope = CoroutineScope(Dispatchers.Main + Job())

    private var mediaSession: MediaSession? = null
    private var exoPlayer: ExoPlayer? = null

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        initializePlayer()
    }

    private fun initializePlayer() {
        exoPlayer = ExoPlayer.Builder(this).build()
        mediaSession = MediaSession.Builder(this, exoPlayer!!).build()
        exoPlayer?.prepare()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? {
        return mediaSession
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
        exoPlayer?.release()
        mediaSession?.release()
        mediaSession = null
        exoPlayer = null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "music_channel",
                "Music Player",
                NotificationManager.IMPORTANCE_LOW
            )
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("ForegroundServiceType")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        when (intent?.action) {
            "PLAY" -> {
                serviceScope.launch {
                    musicRepository.currentTrackFlow.last()?.copy(isPlaying = false)
                        ?.let { musicRepository.setupCurrentTrack(it) }
                }
            }
            "PAUSE" -> {
                serviceScope.launch {
                    musicRepository.currentTrackFlow.last()?.copy(isPlaying = true)
                        ?.let { musicRepository.setupCurrentTrack(it) }
                }
            }
            "NEXT" -> {
                serviceScope.launch {
                    musicRepository.nextTrack()
                }
            }
            "PREV" -> {
                serviceScope.launch {
                    musicRepository.prevTrack()
                }
            }
        }

        serviceScope.launch {
            musicRepository.currentTrackFlow.collect {
                if (it == null) return@collect
                setupTrack(it)
            }
        }

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        super.onBind(intent)
        return null
    }

    private fun setupTrack(track: CurrentPlayingTrackInfo) {
        val mediaItem = MediaItem.fromUri(track.data.trackPreview)
        exoPlayer?.setMediaItem(mediaItem)
        if (track.isPlaying) exoPlayer?.pause()
        else exoPlayer?.play()
        updateNotification(track)
    }

    private fun updateNotification(data: CurrentPlayingTrackInfo) {
        val notification = createNotification(data)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)
    }

    private fun createNotification(data: CurrentPlayingTrackInfo): Notification {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        // Создаем PendingIntent для кнопок
        val playPauseIntent = PendingIntent.getService(
            this,
            0,
            Intent(this, MediaService::class.java).setAction(if (data.isPlaying) "PAUSE" else "PLAY"),
            PendingIntent.FLAG_IMMUTABLE
        )

        val nextIntent = PendingIntent.getService(
            this,
            1,
            Intent(this, MediaService::class.java).setAction("NEXT"),
            PendingIntent.FLAG_IMMUTABLE
        )

        val prevIntent = PendingIntent.getService(
            this,
            2,
            Intent(this, MediaService::class.java).setAction("PREV"),
            PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, "music_channel")
            .setContentTitle(data.data.author)
            .setContentText(data.data.title)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.ic_previous, "Previous", prevIntent) // Кнопка "Назад"
            .addAction(
                if (data.isPlaying) R.drawable.ic_pause else R.drawable.ic_play, // Иконка Play/Pause
                if (data.isPlaying) "Pause" else "Play", // Текст кнопки
                playPauseIntent // Действие
            )
            .addAction(R.drawable.ic_next, "Next", nextIntent) // Кнопка "Вперед"
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setShowActionsInCompactView(0, 1, 2) // Отображать кнопки в компактном виде
            )
            .build()
    }
}