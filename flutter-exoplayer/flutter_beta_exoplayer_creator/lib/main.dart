import 'package:flutter/material.dart';
import 'package:video_player/video_player.dart';
import 'package:chewie/chewie.dart';
import 'package:keep_screen_on/keep_screen_on.dart';
import 'package:file_picker/file_picker.dart'; // Import the file picker package
import 'dart:io'; // Import for File class

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
        primarySwatch: Colors.blue,
        colorScheme: ColorScheme.light(
          primary: Colors.blue,
          secondary: Colors.blueAccent,
        ),
        appBarTheme: AppBarTheme(
          backgroundColor: Colors.blue,
        ),
        buttonTheme: ButtonThemeData(
          buttonColor: Colors.blue,
        ),
      ),
      home: VideoScreen(),
    );
  }
}

class VideoScreen extends StatefulWidget {
  const VideoScreen({super.key});

  @override
  _VideoScreenState createState() => _VideoScreenState();
}

class _VideoScreenState extends State<VideoScreen> {
  String _videoUrl = '';
  String _filePath = '';

  Future<void> _showVideoURLDialog() async {
    final TextEditingController videoController = TextEditingController();

    return showDialog<void>(
      context: context,
      barrierDismissible: false,
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
                  _filePath = ''; // Clear file path if URL is chosen
                });
                Navigator.of(context).pop();
              },
            ),
          ],
        );
      },
    );
  }

  Future<void> _pickFile() async {
    FilePickerResult? result = await FilePicker.platform.pickFiles(type: FileType.video);

    if (result != null && result.files.single.path != null) {
      setState(() {
        _filePath = result.files.single.path!;
        _videoUrl = ''; // Clear video URL if a file is chosen
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('ExoPlayer Creator (New Edition)'),
      ),
      body: Center(
        child: _videoUrl.isEmpty && _filePath.isEmpty
            ? Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: _showVideoURLDialog,
              child: Text('Enter Video URL'),
            ),
            SizedBox(height: 16),
            ElevatedButton(
              onPressed: _pickFile,
              child: Text('Choose Video File'),
            ),
          ],
        )
            : VideoPlayerWidget(
          videoUrl: _videoUrl,
          filePath: _filePath,
        ),
      ),
    );
  }
}

class VideoPlayerWidget extends StatefulWidget {
  final String videoUrl;
  final String filePath;

  const VideoPlayerWidget({super.key, required this.videoUrl, required this.filePath});

  @override
  _VideoPlayerWidgetState createState() => _VideoPlayerWidgetState();
}

class _VideoPlayerWidgetState extends State<VideoPlayerWidget> {
  late VideoPlayerController _videoPlayerController;
  late ChewieController _chewieController;

  @override
  void initState() {
    super.initState();

    if (widget.filePath.isNotEmpty) {
      _videoPlayerController = VideoPlayerController.file(File(widget.filePath));
    } else {
      _videoPlayerController = VideoPlayerController.networkUrl(Uri.parse(widget.videoUrl));
    }

    _chewieController = ChewieController(
      videoPlayerController: _videoPlayerController,
      aspectRatio: 16 / 9,
      autoPlay: true,
      looping: true,
    );

    _videoPlayerController.addListener(() {
      if (_videoPlayerController.value.isPlaying) {
        KeepScreenOn.turnOn();
      } else {
        KeepScreenOn.turnOff();
      }
    });
  }

  @override
  void dispose() {
    super.dispose();
    _chewieController.dispose();
    _videoPlayerController.dispose();
    KeepScreenOn.turnOff();
  }

  @override
  Widget build(BuildContext context) {
    return Chewie(
      controller: _chewieController,
    );
  }
}
