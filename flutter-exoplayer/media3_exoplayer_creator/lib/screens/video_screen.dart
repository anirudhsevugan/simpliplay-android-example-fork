import 'dart:io';
import 'package:flutter/material.dart';
import 'package:file_picker/file_picker.dart';
import '../widgets/video_player_widget.dart';
import 'package:media3_exoplayer_creator/main.dart';

// The VideoScreen widget class
class VideoScreen extends StatefulWidget {
  const VideoScreen({super.key});

  @override
  _VideoScreenState createState() => _VideoScreenState();
}

// The State class for VideoScreen
class _VideoScreenState extends State<VideoScreen> {
  String _videoUrl = '';
  String _filePath = '';
  String _subtitleUrl = ''; // Subtitle URL variable
  String _subtitleFilePath = ''; // Subtitle file path
  bool _isDarkMode = false; // Add theme state here

  // Method to show video URL dialog
  Future<void> _showVideoURLDialog() async {
    final TextEditingController videoController = TextEditingController();

    return showDialog<void>(
      context: context,
      barrierDismissible: false,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text('Enter Video URL'),
          content: TextField(
            controller: videoController,
            decoration: InputDecoration(hintText: 'Enter a valid video URL'),
            keyboardType: TextInputType.url,
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
                  _filePath = '';
                });
                Navigator.of(context).pop();
              },
            ),
          ],
        );
      },
    );
  }

  // Method to pick video file
  Future<void> _pickFile() async {
    FilePickerResult? result = await FilePicker.platform.pickFiles(type: FileType.video);

    if (result != null && result.files.single.path != null) {
      setState(() {
        _filePath = result.files.single.path!;
        _videoUrl = '';
      });
    }
  }

  // Method to pick subtitle file
  Future<void> _pickSubtitleFile() async {
    FilePickerResult? result = await FilePicker.platform.pickFiles(type: FileType.custom, allowedExtensions: ['vtt']);

    if (result != null && result.files.single.path != null) {
      setState(() {
        _subtitleFilePath = result.files.single.path!;
        _subtitleUrl = ''; // Clear subtitle URL when a subtitle file is picked
      });
    }
  }

  // Method to enter subtitle URL
  Future<void> _enterSubtitleURL() async {
    final TextEditingController subtitleController = TextEditingController();

    return showDialog<void>(
      context: context,
      barrierDismissible: false,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text('Enter Subtitle URL'),
          content: TextField(
            controller: subtitleController,
            decoration: InputDecoration(hintText: 'Enter a valid subtitle URL'),
            keyboardType: TextInputType.url,
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
                });
                Navigator.of(context).pop();
              },
            ),
          ],
        );
      },
    );
  }

  // Toggle the theme
  void _toggleTheme() {
    setState(() {
      _isDarkMode = !_isDarkMode;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: _isDarkMode
          ? ThemeData.dark().copyWith(
        primaryColor: Colors.blue,
        colorScheme: ColorScheme.fromSwatch(
            primarySwatch: Colors.lightBlue)
            .copyWith(secondary: Colors.blue),
        appBarTheme: const AppBarTheme(
          color: Colors.blue,
          titleTextStyle: TextStyle(
            color: Colors.white,
            fontSize: 20,
            fontWeight: FontWeight.bold,
          ),
        ),
        textButtonTheme: TextButtonThemeData(
          style: TextButton.styleFrom(
            foregroundColor: Colors.blue,
          ),
        ),
        inputDecorationTheme: InputDecorationTheme(
          filled: true,
          fillColor: Colors.white,
          border: OutlineInputBorder(
            borderRadius: BorderRadius.circular(8),
            borderSide: BorderSide(
              color: Colors.blue,
              width: 2,
            ),
          ),
          focusedBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(8),
            borderSide: BorderSide(
              color: Colors.blue,
              width: 2,
            ),
          ),
        ),
      )
          : ThemeData.light().copyWith(
        primaryColor: Colors.blue,
        scaffoldBackgroundColor: Colors.white,
        colorScheme: ColorScheme.fromSwatch(
            primarySwatch: Colors.lightBlue)
            .copyWith(secondary: Colors.blue),
        appBarTheme: const AppBarTheme(
          color: Colors.blue,
          titleTextStyle: TextStyle(
            color: Colors.white,
            fontSize: 20,
            fontWeight: FontWeight.bold,
          ),
        ),
        textButtonTheme: TextButtonThemeData(
          style: TextButton.styleFrom(
            foregroundColor: Colors.blue,
          ),
        ),
        inputDecorationTheme: InputDecorationTheme(
          filled: true,
          fillColor: Colors.white,
          border: OutlineInputBorder(
            borderRadius: BorderRadius.circular(8),
            borderSide: BorderSide(
              color: Colors.blue,
              width: 2,
            ),
          ),
          focusedBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(8),
            borderSide: BorderSide(
              color: Colors.blue,
              width: 2,
            ),
          ),
        ),
        dialogTheme: DialogTheme(
          backgroundColor: Colors.white, // Light background for light mode
          titleTextStyle: TextStyle(
            color: Colors.black, // Black text for titles in light mode
            fontWeight: FontWeight.bold,
          ),
          contentTextStyle: TextStyle(
            color: Colors.black, // Black text for content in light mode
          ),
        ),
      ),
      home: Scaffold(
        appBar: _videoUrl.isEmpty && _filePath.isEmpty
            ? AppBar(
          title: const Text('ExoPlayer Creator'),
          actions: [
            IconButton(
              icon: Icon(_isDarkMode ? Icons.light_mode : Icons.dark_mode),
              onPressed: _toggleTheme,
            ),
          ],
        )
            : null, // Hide AppBar when video is playing
        body: Center(
          child: _videoUrl.isEmpty && _filePath.isEmpty
              ? Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  ElevatedButton(
                    onPressed: _showVideoURLDialog,
                    child: Text('Enter Video URL'),
                  ),
                  SizedBox(width: 16),
                  ElevatedButton(
                    onPressed: _pickFile,
                    child: Text('Choose Video File'),
                  ),
                ],
              ),
              SizedBox(height: 16),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  ElevatedButton(
                    onPressed: _enterSubtitleURL,
                    child: Text('Enter Subtitle URL'),
                  ),
                  SizedBox(width: 16),
                  ElevatedButton(
                    onPressed: _pickSubtitleFile,
                    child: Text('Choose Subtitle File'),
                  ),
                ],
              ),
            ],
          )
              : VideoPlayerWidget(
            videoUrl: _videoUrl,
            filePath: _filePath,
            subtitleUrl: _subtitleUrl, // Pass subtitle URL to VideoPlayerWidget
            subtitleFilePath: _subtitleFilePath, // Pass subtitle file path to VideoPlayerWidget
          ),
        ),
      ),
    );
  }
}
