import 'package:flutter/material.dart';
import 'package:file_picker/file_picker.dart';
import 'package:flutter/services.dart'; // Import for SystemChrome
import '../widgets/video_player_widget.dart';
import '../utils/video_player_manager.dart';

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
  bool _isDarkMode = false;




  // Pick a video or audio file
  Future<void> _pickFile() async {
    FilePickerResult? result = await FilePicker.platform.pickFiles(
      type: FileType.media, // This allows both video and audio file selection
    );

    if (result != null && result.files.single.path != null) {
      setState(() {
        _filePath = result.files.single.path!;
        _videoUrl = ''; // Clear video URL if file is picked
      });
    }
  }

  // Show the dialog to enter video URL
  Future<void> _showVideoURLDialog() async {
    final TextEditingController videoController = TextEditingController();

    return showDialog<void>(
      context: context,
      barrierDismissible: false,
      builder: (BuildContext context) {
        return Center( // Center the dialog
          child: SingleChildScrollView(
            child: AlertDialog(
              title: const Text('Enter Video URL'),
              content: TextField(
                controller: videoController,
                decoration: const InputDecoration(hintText: 'Enter a valid video URL'),
                keyboardType: TextInputType.url,
              ),
              actions: <Widget>[
                TextButton(
                  child: const Text('Cancel'),
                  onPressed: () => Navigator.of(context).pop(),
                ),
                TextButton(
                  child: const Text('OK'),
                  onPressed: () {
                    setState(() {
                      _videoUrl = videoController.text;
                      _filePath = '';
                    });
                    Navigator.of(context).pop();
                  },
                ),
              ],
            ),
          ),
        );
      },
    );
  }

  // Show the subtitle dialog
  Future<void> _showSubtitleDialog() async {
    final TextEditingController subtitleController = TextEditingController();

    return showDialog<void>(
      context: context,
      barrierDismissible: false,
      builder: (BuildContext context) {
        return Center( // Center the dialog
          child: SingleChildScrollView(
            child: AlertDialog(
              title: const Text('Enter Subtitle URL'),
              content: TextField(
                controller: subtitleController,
                decoration: const InputDecoration(hintText: 'Enter a valid subtitle URL'),
                keyboardType: TextInputType.url,
              ),
              actions: <Widget>[
                TextButton(
                  child: const Text('Cancel'),
                  onPressed: () => Navigator.of(context).pop(),
                ),
                TextButton(
                  child: const Text('OK'),
                  onPressed: () {
                    setState(() {
                      _subtitleUrl = subtitleController.text;
                      _subtitleFilePath = ''; // Clear file path when URL is used
                    });
                    Navigator.of(context).pop();
                  },
                ),
              ],
            ),
          ),
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      onWillPop: _onWillPop, // Use this for back button intercept
      child: MaterialApp(
        debugShowCheckedModeBanner: false,
        themeMode: _isDarkMode ? ThemeMode.dark : ThemeMode.light, // Toggle theme mode
        theme: _lightTheme(),
        darkTheme: _darkTheme(), // Apply dark theme
        home: Scaffold(
          appBar: _videoUrl.isNotEmpty || _filePath.isNotEmpty
              ? null // Hide AppBar when video is playing
              : AppBar(
            title: const Text('ExoPlayer Creator'),
            actions: [
              IconButton(
                icon: Icon(_isDarkMode ? Icons.light_mode : Icons.dark_mode),
                onPressed: () {
                  setState(() {
                    _isDarkMode = !_isDarkMode;
                  });
                },
              ),
            ],
          ),
          body: Center(
            child: _videoUrl.isEmpty && _filePath.isEmpty
                ? SingleChildScrollView(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  const SizedBox(height: 20),
                  // Enter Video URL Button
                  Column(
                    children: [
                      IconButton(
                        icon: const Icon(Icons.link, size: 50),
                        onPressed: _showVideoURLDialog,
                      ),
                      const Text('Enter Video URL'),
                    ],
                  ),
                  const SizedBox(height: 20),
                  // Subtitle URL Button
                  Column(
                    children: [
                      IconButton(
                        icon: const Icon(Icons.subtitles, size: 50),
                        onPressed: _showSubtitleDialog,
                      ),
                      const Text('Add Subtitle URL'),
                    ],
                  ),
                  const SizedBox(height: 20),
                  // Choose File Button
                  Column(
                    children: [
                      IconButton(
                        icon: const Icon(Icons.video_library, size: 50),
                        onPressed: _pickFile,
                      ),
                      const Text('Choose File'),
                    ],
                  ),
                ],
              ),
            )
                : VideoPlayerWidget(
              videoUrl: _videoUrl,
              filePath: _filePath,
              subtitleUrl: _subtitleUrl,
              subtitleFilePath: _subtitleFilePath,
            ),
          ),
        ),
      ),
    );
  }

  Future<bool> _onWillPop() async {
    // If the video is not playing (i.e., file or URL is empty), exit the app
    if (_videoUrl.isEmpty && _filePath.isEmpty) {
      return true; // Exit the app
    }

    // If the video is playing, show the confirmation dialog
    return (await _showExitConfirmation()) ?? false;
  }

  Future<bool?> _showExitConfirmation() async {
    return showDialog<bool>(
      context: context,
      barrierDismissible: false, // Prevent dismissing by tapping outside the dialog
      builder: (BuildContext context) {
        return AlertDialog(
          title: const Text('Are you sure you want to exit?'),
          actions: <Widget>[
            TextButton(
              child: const Text('No'),
              onPressed: () {
                Navigator.of(context).pop(false); // Go back to VideoScreen
              },
            ),
            TextButton(
              child: const Text('Yes'),
              onPressed: () {
                Navigator.of(context).pop(true); // Exit the app
              },
            ),
          ],
        );
      },
    );
  }

  // Light theme
  ThemeData _lightTheme() {
    return ThemeData(
      primaryColor: Colors.lightBlue,
      scaffoldBackgroundColor: Colors.white,
      colorScheme: ColorScheme.fromSwatch(primarySwatch: Colors.lightBlue)
          .copyWith(secondary: Colors.lightBlue),
      appBarTheme: AppBarTheme(
        color: Colors.lightBlue,
        titleTextStyle: TextStyle(
          color: Colors.white,
          fontSize: 20,
          fontWeight: FontWeight.bold,
        ),
      ),
      iconTheme: IconThemeData(
        color: Colors.black, // Black icons for light mode
      ),
      textTheme: TextTheme(
        bodyLarge: TextStyle(color: Colors.black),
        bodyMedium: TextStyle(color: Colors.black),
        bodySmall: TextStyle(color: Colors.black),
      ),
    );
  }

  ThemeData _darkTheme() {
    return ThemeData(
      primaryColor: Colors.blue, // Primary color for the theme
      scaffoldBackgroundColor: Colors.black, // Black background for the scaffold
      colorScheme: ColorScheme.fromSwatch(primarySwatch: Colors.blue)
          .copyWith(secondary: Colors.blue), // Customize color scheme
      appBarTheme: AppBarTheme(
        color: Colors.blue, // Blue AppBar background
        titleTextStyle: TextStyle(
          color: Colors.white, // White text on AppBar
          fontSize: 20,
          fontWeight: FontWeight.bold,
        ),
      ),
      iconTheme: IconThemeData(
        color: Colors.white, // White icons for dark mode
      ),
      textTheme: TextTheme(
        bodyLarge: TextStyle(color: Colors.white),
        bodyMedium: TextStyle(color: Colors.white),
        bodySmall: TextStyle(color: Colors.white),
      ),
    );
  }
}
