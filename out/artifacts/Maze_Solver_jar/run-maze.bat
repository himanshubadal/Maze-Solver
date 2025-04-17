@echo off
set JAVAFX_LIB="C:\Users\shivs\Downloads\openjfx-17.0.15_windows-x64_bin-sdk\javafx-sdk-17.0.15\lib"
java --module-path %JAVAFX_LIB% --add-modules javafx.controls,javafx.fxml -jar Maze-Solver.jar
pause
