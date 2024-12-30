import 'dart:io';
import 'package:flutter/material.dart';
import 'package:file_picker/file_picker.dart';
import 'package:http/http.dart' as http;
import 'package:keep_screen_on/keep_screen_on.dart';
import 'package:permission_handler/permission_handler.dart'; // Add this import
import '../widgets/video_player_widget.dart';

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

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: _videoUrl.isEmpty && _filePath.isEmpty
          ? AppBar(
        title: Text('ExoPlayer Creator'),
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
    );
  }
}