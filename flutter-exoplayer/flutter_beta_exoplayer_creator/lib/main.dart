import 'package:flutter/material.dart';
import 'package:media3_exoplayer_creator/screens/video_screen.dart';  // Import other files as needed

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
        primarySwatch: Colors.blue, // Set primary color to blue
        colorScheme: ColorScheme.fromSwatch(primarySwatch: Colors.blue).copyWith(
          secondary: Colors.blue, // Set accent (secondary) color to blue accent
        ),
        appBarTheme: AppBarTheme(
          color: Colors.blue, // Set the AppBar color to blue
          titleTextStyle: TextStyle(
            color: Colors.white, // Set text color to white for AppBar
            fontSize: 20,
            fontWeight: FontWeight.bold,
          ),
        ),
        textButtonTheme: TextButtonThemeData(
          style: TextButton.styleFrom(
            foregroundColor: Colors.blueAccent, // Set text color to blue for TextButtons
          ),
        ),
        inputDecorationTheme: InputDecorationTheme(
          filled: true, // Allow filled background for text fields
          fillColor: Colors.white, // White background for text fields
          border: OutlineInputBorder(
            borderRadius: BorderRadius.circular(8), // Rounded corners for text fields
            borderSide: BorderSide(
              color: Colors.blueAccent, // Border color
              width: 2,
            ),
          ),
          focusedBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(8),
            borderSide: BorderSide(
              color: Colors.blueAccent, // Focused border color
              width: 2,
            ),
          ),
        ),
      ),
      home: VideoScreen(),
    );
  }
}
