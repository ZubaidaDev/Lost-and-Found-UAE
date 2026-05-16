# Lost and Found UAE

Mobile Application Development Group Project

## Project Idea

Lost and Found UAE is an Android mobile application that allows users to report lost items and found items.

Users can:
- Report a lost item
- Report a found item
- Search for items using keywords
- View item details

This project will use:
- Java
- XML
- SQLite Database

---

## Development Platform

We will use Android Studio to build and test the application.

GitHub will be used to share the project files and manage teamwork.

GitHub Codespaces is not required for this project because Android apps are easier to develop and test using Android Studio with an emulator or a real Android phone.

---

## Team Work Plan

### Person 1: Main Screen, Menu, and Search

Responsible for:
- Main app structure
- MainActivity
- Options menu with 3 dots
- Search screen
- Search results list
- Item result layout

Main files:
- MainActivity.java
- SearchFragment.java
- ItemAdapter.java
- activity_main.xml
- fragment_search.xml
- item_result.xml
- res/menu/main_menu.xml

---

### Person 2: Report Lost Item and Database

Responsible for:
- Report Lost Item screen
- Lost item form
- Saving lost item data into SQLite
- DatabaseHelper class
- AndroidManifest.xml setup

Main files:
- ReportLostFragment.java
- DatabaseHelper.java
- fragment_report_lost.xml
- AndroidManifest.xml

---

### Person 3: Report Found Item and Data Sending

Responsible for:
- Report Found Item screen
- Found item form
- Sending submitted data back to SearchFragment
- Bundle and Interface communication

Main files:
- ReportFoundFragment.java
- fragment_report_found.xml

---

## App Menu Structure

The app will use a 3 dots menu with these options:

```text
Menu
├── Report Lost
├── Report Found
└── Search
```

---

## Work Balance

| Person | Java Files | XML Files | Difficulty |
|---|---:|---:|---|
| Person 1 | 3 | 3 | Medium |
| Person 2 | 2 | 2 | Medium |
| Person 3 | 1 | 1 + Interface | Medium |

---

## GitHub Workflow

### Before starting work

Always pull the latest version first:

```bash
git pull
```

This updates your laptop with the latest files from GitHub.

---

### After finishing work

Save your changes, then push your work to GitHub:

```bash
git add .
git commit -m "Write what you changed"
git push
```

Example:

```bash
git add .
git commit -m "Added report lost item screen"
git push
```

---

## Important Team Rules

1. Always use `git pull` before editing files.
2. Always use `git push` after finishing your part.
3. Write a clear commit message.
4. Do not edit the same file at the same time as another member.
5. Do not delete another member's file.
6. Tell the group before editing shared files like:
   - MainActivity.java
   - DatabaseHelper.java
   - AndroidManifest.xml
7. Test the app before pushing.
8. If there is an error, tell the group before changing many files.

---

## Suggested Screens

1. Home Screen
2. Report Lost Item Screen
3. Report Found Item Screen
4. Search Items Screen
5. Item Details Screen
6. About App Screen

---

## Final Submission Checklist

Before submitting, make sure the project includes:

- Android project files
- Java files
- XML files
- SQLite database code
- Screenshots of app output
- Final report
- Google Drive video link
- Compressed project folder

---

## Final Notes

One group member will submit the final compressed project file through Blackboard.

All members must understand their own part because a live demonstration will be required.
