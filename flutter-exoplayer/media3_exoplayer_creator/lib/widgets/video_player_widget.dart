import 'package:flutter/material.dart';
import 'package:video_player/video_player.dart';
import 'package:chewie/chewie.dart';
import 'package:http/http.dart' as http;
import 'dart:io';  // Import to use File class
import 'package:media3_exoplayer_creator/utils/permission_utils.dart'; // Import permission_utils.dart for permission handling
import 'package:keep_screen_on/keep_screen_on.dart'; // Import keep_screen_on
import 'package:flutter/services.dart'; // Import SystemChrome
import '../utils/web_vtt.dart'; // Import web_vtt.dart for subtitle handling

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
  bool _isLoading = true; // Flag to track loading state of subtitles

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

    // Keep the screen on while the video is playing
    KeepScreenOn.turnOn();

    // Hide system UI (status bar and navigation bar) when the video starts
    SystemChrome.setEnabledSystemUIMode(SystemUiMode.immersiveSticky);

    // Listen to video position changes to update subtitles
    _videoPlayerController.addListener(_updateCurrentSubtitle);

    // Initialize the video player
    _initializeVideoPlayer();
  }

  @override
  void dispose() {
    // Turn off the screen stay-on feature and reset the system UI when the widget is disposed
    KeepScreenOn.turnOff();
    SystemChrome.setEnabledSystemUIMode(SystemUiMode.manual, overlays: SystemUiOverlay.values); // Restore UI to default

    _videoPlayerController.removeListener(_updateCurrentSubtitle);
    _videoPlayerController.dispose();
    super.dispose();
  }

  // Method to load subtitles (you can adapt it to your subtitle parsing logic)
  Future<void> _loadSubtitles() async {
    setState(() {
      _isLoading = true; // Start loading subtitles
    });

    // Check if subtitle path is provided
    if (widget.subtitleFilePath.isNotEmpty) {
      // Load subtitle from local file
      try {
        final file = File(widget.subtitleFilePath);
        final subtitleData = await file.readAsString();
        setState(() {
          _subtitles = parseWebVtt(subtitleData); // Use parseWebVtt from web_vtt.dart
          _isLoading = false; // Subtitles loaded successfully
        });
      } catch (e) {
        setState(() {
          _isLoading = false; // Failed to load subtitles
        });
        print('Error loading subtitle file: $e');
      }
    } else if (widget.subtitleUrl.isNotEmpty) {
      // Load subtitle from URL
      try {
        final response = await http.get(Uri.parse(widget.subtitleUrl));
        if (response.statusCode == 200) {
          setState(() {
            _subtitles = parseWebVtt(response.body); // Use parseWebVtt from web_vtt.dart
            _isLoading = false; // Subtitles loaded successfully
          });
        } else {
          setState(() {
            _isLoading = false; // Failed to load subtitles from URL
          });
          print('Failed to load subtitle from URL: ${response.statusCode}');
        }
      } catch (e) {
        setState(() {
          _isLoading = false; // Failed to load subtitles
        });
        print('Error loading subtitle from URL: $e');
      }
    }
  }

  // Method to update the current subtitle based on the video position
  void _updateCurrentSubtitle() {
    final currentTime = _videoPlayerController.value.position;

    for (var cue in _subtitles) {
      if (currentTime >= cue.start && currentTime <= cue.end) {
        setState(() {
          _currentSubtitle = cue.text;
        });
        return;
      }
    }

    setState(() {
      _currentSubtitle = '';
    });
  }

  void _initializeVideoPlayer() async {
    // Initialize the video player
    await _videoPlayerController.initialize();

    // Update loading state once the video is ready
    setState(() {
      _isLoading = false;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          // Background set to black
          Container(
            color: Colors.black,
            child: Chewie(controller: _chewieController), // Video player widget
          ),
          // Show a loading indicator while video or subtitles are loading
          if (_isLoading)
            Center(
              child: CircularProgressIndicator(),
            ),
          // Display the current subtitle if available
          if (_currentSubtitle != null && _currentSubtitle!.isNotEmpty && !_isLoading)
            Positioned(
              bottom: 70, // Adjusted the bottom padding to be higher
              left: 0,
              right: 0,
              child: Container(
                padding: EdgeInsets.symmetric(horizontal: 20, vertical: 10),
                color: Colors.black.withOpacity(0.7), // Black background with transparency
                child: Text(
                  _currentSubtitle!,
                  style: TextStyle(
                    fontSize: 19,
                    color: Colors.white,
                  ),
                  textAlign: TextAlign.center,
                ),
              ),
            ),
        ],
      ),
    );
  }
}
