import 'package:flutter/material.dart';
import 'package:video_player/video_player.dart';
import 'package:chewie/chewie.dart';
import 'package:keep_screen_on/keep_screen_on.dart'; // Import the keep_screen_on package

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primarySwatch: Colors.blue,  // Set primary color to blue
        colorScheme: ColorScheme.light(
          primary: Colors.blue,         // Primary color
          secondary: Colors.blueAccent, // Accent color (replaces accentColor)
        ),
        appBarTheme: AppBarTheme(
          backgroundColor: Colors.blue, // Set AppBar background to blue
        ),
        buttonTheme: ButtonThemeData(
          buttonColor: Colors.blue,  // Set button color to blue
        ),
        // Customize other theme properties as needed
      ),
      home: VideoURLScreen(),
    );
  }
}

class VideoURLScreen extends StatefulWidget {
  const VideoURLScreen({super.key});

  @override
  _VideoURLScreenState createState() => _VideoURLScreenState();
}

class _VideoURLScreenState extends State<VideoURLScreen> {
  String _videoUrl = '';

  // Show the dialog to ask for the video URL
  Future<void> _showVideoURLDialog() async {
    final TextEditingController videoController = TextEditingController();

    return showDialog<void>(
      context: context,
      barrierDismissible: false, // Prevent dismissing by tapping outside
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text('Enter Video URL'),
          content: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              TextField(
                controller: videoController,
                decoration: InputDecoration(hintText: 'Enter a valid video URL'),
                keyboardType: TextInputType.url,
              ),
            ],
          ),
          actions: <Widget>[
            TextButton(
              child: Text('Cancel'),
              onPressed: () {
                Navigator.of(context).pop();
              },
            ),
            TextButton(
              child: Text('OK'),
              onPressed: () {
                setState(() {
                  _videoUrl = videoController.text;
                });
                Navigator.of(context).pop();
              },
            ),
          ],
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('ExoPlayer Creator (New Edition)'),
      ),
      body: Center(
        child: _videoUrl.isEmpty
            ? ElevatedButton(
          onPressed: _showVideoURLDialog,
          child: Text('Enter Video URL'),
        )
            : VideoPlayerWidget(videoUrl: _videoUrl),
      ),
    );
  }
}

class VideoPlayerWidget extends StatefulWidget {
  final String videoUrl;

  const VideoPlayerWidget({super.key, required this.videoUrl});

  @override
  _VideoPlayerWidgetState createState() => _VideoPlayerWidgetState();
}

class _VideoPlayerWidgetState extends State<VideoPlayerWidget> {
  late VideoPlayerController _videoPlayerController;
  late ChewieController _chewieController;

  @override
  void initState() {
    super.initState();
    _videoPlayerController = VideoPlayerController.networkUrl(Uri.parse(widget.videoUrl)); // Convert String to Uri

    _chewieController = ChewieController(
      videoPlayerController: _videoPlayerController,
      aspectRatio: 16 / 9,
      autoPlay: true,
      looping: true,
    );

    // Enable keep_screen_on to prevent screen sleep
    _videoPlayerController.addListener(() {
      if (_videoPlayerController.value.isPlaying) {
        KeepScreenOn.turnOn(); // Keep the screen on while video is playing
      } else {
        KeepScreenOn.turnOff(); // Allow the screen to turn off when the video is paused or stopped
      }
    });
  }

  @override
  void dispose() {
    super.dispose();
    _chewieController.dispose();
    _videoPlayerController.dispose();
    KeepScreenOn.turnOff(); // Ensure screen turns off when the widget is disposed
  }

  @override
  Widget build(BuildContext context) {
    return Chewie(
      controller: _chewieController,
    );
  }
}

// Confirm exit when back is pressed
Future<bool> _onWillPop(BuildContext context) async {
  return await showDialog(
    context: context,
    builder: (context) => AlertDialog(
      title: Text('Are you sure?'),
      content: Text('If you exit now, ExoPlayer will stop playing.'),
      actions: <Widget>[
        TextButton(
          onPressed: () => Navigator.of(context).pop(false),
          child: Text('No'),
        ),
        TextButton(
          onPressed: () => Navigator.of(context).pop(true),
          child: Text('Yes'),
        ),
      ],
    ),
  ) ?? false;
}
