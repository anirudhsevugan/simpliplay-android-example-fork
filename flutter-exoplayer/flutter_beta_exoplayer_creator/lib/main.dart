import 'package:flutter/material.dart';
import 'package:video_player/video_player.dart';
import 'package:chewie/chewie.dart';
import 'package:keep_screen_on/keep_screen_on.dart';
import 'package:file_picker/file_picker.dart';
import 'dart:io'; // Import for File class
import 'dart:convert'; // For decoding the WebVTT file

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
  String _subtitleUrl = '';
  String _subtitleFilePath = '';

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

  Future<void> _pickSubtitleFile() async {
    FilePickerResult? result = await FilePicker.platform.pickFiles(type: FileType.custom, allowedExtensions: ['vtt']);

    if (result != null && result.files.single.path != null) {
      setState(() {
        _subtitleFilePath = result.files.single.path!;
        _subtitleUrl = ''; // Clear subtitle URL if file is chosen
      });
    }
  }

  Future<void> _showSubtitleURLDialog() async {
    final TextEditingController subtitleController = TextEditingController();

    return showDialog<void>(
      context: context,
      barrierDismissible: false,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text('Enter Subtitle URL'),
          content: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              TextField(
                controller: subtitleController,
                decoration: InputDecoration(hintText: 'Enter a valid subtitle URL'),
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
                  _subtitleUrl = subtitleController.text;
                  _subtitleFilePath = ''; // Clear subtitle file path if URL is chosen
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
            SizedBox(height: 16),
            ElevatedButton(
              onPressed: _showSubtitleURLDialog,
              child: Text('Enter Subtitle URL'),
            ),
            SizedBox(height: 16),
            ElevatedButton(
              onPressed: _pickSubtitleFile,
              child: Text('Choose Subtitle File'),
            ),
          ],
        )
            : VideoPlayerWidget(
          videoUrl: _videoUrl,
          filePath: _filePath,
          subtitleUrl: _subtitleUrl,
          subtitleFilePath: _subtitleFilePath,
        ),
      ),
    );
  }
}

class VideoPlayerWidget extends StatefulWidget {
  final String videoUrl;
  final String filePath;
  final String subtitleUrl;
  final String subtitleFilePath;

  const VideoPlayerWidget({
    super.key,
    required this.videoUrl,
    required this.filePath,
    required this.subtitleUrl,
    required this.subtitleFilePath,
  });

  @override
  _VideoPlayerWidgetState createState() => _VideoPlayerWidgetState();
}

class _VideoPlayerWidgetState extends State<VideoPlayerWidget> {
  late VideoPlayerController _videoPlayerController;
  late ChewieController _chewieController;
  late List<WebVttCue> _subtitles;  // List to store parsed subtitles
  String? _currentSubtitle = '';  // Current subtitle to display

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

      _checkForSubtitles();  // Check if there are any subtitles to display
    });

    _loadSubtitles();  // Load subtitles if available
  }

  // Load and parse the WebVTT subtitle file
  Future<void> _loadSubtitles() async {
    if (widget.subtitleUrl.isNotEmpty) {
      final subtitleData = await _loadSubtitleFile(widget.subtitleUrl);
      setState(() {
        _subtitles = _parseWebVtt(subtitleData);
      });
    } else if (widget.subtitleFilePath.isNotEmpty) {
      final subtitleData = await _loadSubtitleFileFromFile(widget.subtitleFilePath);
      setState(() {
        _subtitles = _parseWebVtt(subtitleData);
      });
    }
  }

  // Fetch subtitle file data from URL (HTTP or HTTPS)
  Future<String> _loadSubtitleFile(String url) async {
    final HttpClient client = HttpClient();
    final HttpClientRequest request = await client.getUrl(Uri.parse(url));
    final HttpClientResponse response = await request.close();
    final String subtitleData = await response.transform(utf8.decoder).join();
    return subtitleData;
  }

  // Fetch subtitle file data from local file
  Future<String> _loadSubtitleFileFromFile(String filePath) async {
    final file = File(filePath);
    return await file.readAsString();
  }

  // Simple WebVTT parsing method
  List<WebVttCue> _parseWebVtt(String subtitleData) {
    final RegExp cuePattern = RegExp(r'(\d{2}:\d{2}:\d{2}.\d{3}) --> (\d{2}:\d{2}:\d{2}.\d{3})\n(.*?)\n\n', dotAll: true);
    final List<WebVttCue> cues = [];

    for (final match in cuePattern.allMatches(subtitleData)) {
      final start = _parseTime(match.group(1)!);
      final end = _parseTime(match.group(2)!);
      final text = match.group(3)!;

      cues.add(WebVttCue(start: start, end: end, text: text));
    }

    return cues;
  }

  // Parse the WebVTT time format (hh:mm:ss.ms) into a Duration
  Duration _parseTime(String time) {
    final parts = time.split(':');
    final secondsParts = parts[2].split('.');
    return Duration(
      hours: int.parse(parts[0]),
      minutes: int.parse(parts[1]),
      seconds: int.parse(secondsParts[0]),
      milliseconds: int.parse(secondsParts[1]),
    );
  }

  // Check if the video time matches the subtitle time
  void _checkForSubtitles() {
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

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        Chewie(
          controller: _chewieController,
        ),
        if (_currentSubtitle != null)
          Positioned(
            bottom: 50,
            left: 0,
            right: 0,
            child: Text(
              _currentSubtitle!,
              style: TextStyle(
                fontSize: 24,
                color: Colors.white,
                fontWeight: FontWeight.bold,
                shadows: [
                  Shadow(color: Colors.black, offset: Offset(2, 2), blurRadius: 5),
                ],
              ),
              textAlign: TextAlign.center,
            ),
          ),
      ],
    );
  }
}

class WebVttCue {
  final Duration start;
  final Duration end;
  final String text;

  WebVttCue({
    required this.start,
    required this.end,
    required this.text,
  });
}
