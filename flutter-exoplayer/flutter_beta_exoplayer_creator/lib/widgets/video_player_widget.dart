import 'package:flutter/material.dart';
import 'package:video_player/video_player.dart';
import 'package:chewie/chewie.dart';
import 'dart:io';  // Import to use File class
import 'package:media3_exoplayer_creator/utils/permission_utils.dart'; // Import permission_utils.dart for permission handling
import 'package:media3_exoplayer_creator/utils/web_vtt.dart';



class VideoPlayerWidget extends StatefulWidget {
  final String videoUrl;
  final String filePath;
  final String subtitleUrl;
  final String subtitleFilePath;

  const VideoPlayerWidget({
    Key? key,
    required this.videoUrl,
    required this.filePath,
    required this.subtitleUrl,
    required this.subtitleFilePath,
  }) : super(key: key);

  @override
  _VideoPlayerWidgetState createState() => _VideoPlayerWidgetState();
}

class _VideoPlayerWidgetState extends State<VideoPlayerWidget> {
  late VideoPlayerController _videoPlayerController;
  late ChewieController _chewieController;
  late List<WebVttCue> _subtitles;
  String? _currentSubtitle;

  @override
  void initState() {
    super.initState();

    // Request permission for subtitle files if a subtitle file path is provided
    if (widget.subtitleFilePath.isNotEmpty) {
      requestPermissionIfNeeded(widget.subtitleFilePath, context);  // Correctly call the method here
    }

    // Initialize the video player controller based on video URL or file path
    if (widget.filePath.isNotEmpty) {
      _videoPlayerController = VideoPlayerController.file(File(widget.filePath));
    } else {
      _videoPlayerController = VideoPlayerController.networkUrl(Uri.parse(widget.videoUrl));
    }

    // Initialize the Chewie controller for video playback
    _chewieController = ChewieController(
      videoPlayerController: _videoPlayerController,
      aspectRatio: 16 / 9,
      autoPlay: true,
      looping: true,
    );

    // Load subtitles if needed
    _loadSubtitles();
  }

  // Method to load subtitles (you can adapt it to your subtitle parsing logic)
  Future<void> _loadSubtitles() async {
    // Add your subtitle loading logic here
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          Chewie(controller: _chewieController),  // Video player widget
          if (_currentSubtitle != null && _currentSubtitle!.isNotEmpty)
            Positioned(
              bottom: 50,
              left: 0,
              right: 0,
              child: Text(
                _currentSubtitle!,
                style: TextStyle(
                  fontSize: 19,
                  color: Colors.white,
                ),
                textAlign: TextAlign.center,
              ),
            ),
        ],
      ),
    );
  }
}
