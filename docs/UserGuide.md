---
layout: page
title: User Guide
---

# Covigent - User Guide

1. [Introduction](#1-introduction)
2. [Quick Start](#2-quick-start)
3. [Features](#3-features)<br>
    3.1  [Command Format](#31-command-format)<br>
    3.2  [Add a patient: `addpatient`](#32-add-a-patient-addpatient)<br>
    3.3  [Delete a patient: `deletepatient`](#33-delete-a-patient-deletepatient)<br>
    3.4  [Edit a patient: `editpatient`](#34-edit-a-patient-editpatient)<br>
    3.5  [Search information: `searchpatient`](#35-search-information-searchpatient)<br>
    3.6  [List all patients: `listpatient`] (#36-list-all-patients-listpatient)<br>
    3.7  [Initialize rooms in hotel: `initRooms`](#37-initialize-rooms-in-hotel-initrooms)<br>
    3.8  [Lists the current rooms: `listRoom`](#38-lists-the-current-rooms-listroom)<br>
    3.9  [Find the first free room: `findRoom`](#39-find-the-first-free-room-findroom)<br>
    3.10 [Search room: `searchroom`] (#310-search-room-searchroom)<br>
    3.11 [Allocate patient to room or edit room: `editroom`](#311-allocate-patient-to-room-or-edit-room-editroom)<br>
    3.12 [Add a task to a room: `addtask`](#312-add-a-task-to-a-room-addtask)<br>
    3.13 [Delete a task from a room: `deletetask`](#313-delete-a-task-from-a-room-deletetask)<br>
    3.14 [Edit a task in a room: `edittask`](#314-edit-a-task-in-a-room-edittask)<br>
    3.15 [Searches all tasks before the given date: `searchtask`](#315-search-tasks-before-a-date-searchtask)<br>
    3.16 [View help: `help`](#316-view-help-help)<br>
    3.17 [Save the data](#317-save-the-data)<br>
 4. [FAQ](#4-faq)
 5. [Command Summary](#5-command-summary)


--------------------------------------------------------------------------------------------------------------------

## 1. Introduction

Covigent is a desktop app for managing information of quarantined individuals and the tasks to be done by staff of the quarantine facility. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you type fast, Covigent can improve your efficiency in managing your patients and tasks instead of using traditional GUI apps.


## 2. Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. (*Coming soon*) Download the latest `covigent.jar` from [here](https://github.com).  

1. Copy the file to the folder you want to use as the _home folder_ for the application.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. <br>
   Some example commands you can try:

   * **`addpatient`** `n/John Doe t/37.4 d/20200910-20200924 p/98765432 a/35` : Adds a quarantined individual named `John Doe` to the application.

   * **`editpatient`** `Mary t/36.7 p/91234567` : Updates the temperature and phone number of an individual named `Mary` to 36.7 and 91234567 respectively.

1. Refer to the [Features](#3-features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## 3. Features

<div markdown="block" class="alert alert-info">

### 3.1 Command Format
**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addpatient n/NAME`, `NAME` is a parameter which can be used as `addpatient n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [c/COMMENT]` can be used as `n/John Doe c/Is vegan` or as `n/John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.
  
</div>

### 3.2 Add a patient: `addpatient`

Adds a quarantined individual to the application.

Format: `addpatient n/NAME t/TEMPERATURE d/PERIOD_OF_STAY p/PHONE_NUMBER a/AGE [c/COMMENT]`

* Adds a quarantined individual with the specified name, temperature, period of stay, phone number and age.
* Temperature must be to 1 decimal place (e.g. 37.0 instead of 37).
* Period of stay is in the format YYYYMMDD-YYYYMMDD.
* It is optional to include the comment field for the quarantined individual.

Examples:
* `addpatient n/John Doe p/98765432 t/37.4 d/20200910-20200924 a/35`
* `addpatient n/Betsy Crowe t/36.5 d/20201001-20201014 p/91234567 a/19 c/Is asthmatic`

### 3.3 Delete a patient: `deletepatient`

Deletes an existing patient in the application.

Format: `deletepatient NAME`

* Deletes the patient with the specified `NAME`. The name refers to the name of the patient inputted into the application earlier. The name **must match exactly with the name of the patient**.
* `NAME` is case-insensitive.

Example:
* `deletepatient Mary Doe` Deletes the patient record of Mary Doe from the application.


### 3.4 Edit a patient: `editpatient`

Edits an existing patient in the application.

Format: `editpatient NAME [n/NAME] [t/TEMPERATURE] [d/PERIOD_OF_STAY] [p/PHONE_NUMBER] [a/AGE] [c/COMMENT]`

* Edits the patient with the specified `NAME`. The name refers to the name of the patient inputted into the application earlier. The name **must match exactly with the name of the patient**.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* `NAME` is case-insensitive.
* `TEMPERATURE` must be to 1 decimal place (e.g. 37.0 instead of 37).
* `PERIOD_OF_STAY` is in the format `YYYYMMDD-YYYYMMDD`.

Examples:
*  `editpatient john doe p/91234567` Edits the phone number of john doe to be `91234567`.
*  `editpatient alex t/36.7 a/21 d/20200303-20200315` Edits the temperature, age and period of stay of alex to be `36.7`, `21` and `20200303-20200315` respectively.

### 3.5 Search information: `searchpatient`

Searches the patients that matches the given criteria in the application.

Format: `searchpatient [n/NAME] [tr/TEMPERATURE_RANGE]`

* Only one of the fields can be provided.
* The temperature range is inclusive of start and end temperatures.

Examples:
*  `searchpatient n/john` Searches patients with a name John.
*  `searchpatient tr/36.5-36.7` Searches patients with temperature 36.5 to 36.7 degree, celsius, both inclusive.


### 3.6 List all patients: `listpatient`

Shows a list of all patients in the patient tab.

Format: `listpatient`

### 3.7 Initialize rooms in hotel: `initRooms`

Initializes the number of rooms in the quarantine facility to the app, if there was data given previously, they would
be stored.

Format: `initRooms NUMBER_OF_ROOMS`

* Adds NUMBER_OF_ROOMS rooms into the hotel system

Examples:
* `initRooms 123`
* `initRooms 400`

### 3.8 Lists the current rooms: `listRoom`

Lists all the rooms in the hotel.

Format: `listRoom`

### 3.9 Find the first free room: `findRoom`

Finds the room with the lowest room number that is free for use.

Format: `findRoom`

* Finds the room number of least value that can be safely used for accommodation

### 3.10 Search room: `searchroom`

Searches for the room with the specified room number.

Format `searchRoom ROOM_NUMBER`

Examples:
* `searchRoom 15`

### 3.11 Allocate patient to room or edit room: `editroom`

Allocates a patient to a room or edits an existing room in the application.

Format: `editroom ROOM_NUMBER [r/NEW_ROOM_NUMBER] [p/PATIENT_NAME]`

* Edits the room with the specified `ROOM_NUMBER`.
* `PATIENT_NAME` must match exactly with the patient name that was inputted into the application.
* `PATIENT_NAME` is case-insensitive.
* Remove patient from room by inputting a `-` for `PATIENT_NAME`.
* At least one of the optional fields must be provided.
* A room with the `ROOM_NUMBER` must be present.

Examples:
* `editroom 1 r/2 p/alex`. Changes the room number from #1 to #2. Afterwards, allocates alex to Room #2.
* `editroom 1 p/alex`. Allocates alex to Room #1.
* `editroom 1 p/-`. Removes the patient from the Room #1.
* `editroom 1 r/3 p/-`. Changes the room number from #1 to #3. Afterwards, removes the patient from Room #3.

### 3.12 Add a task to a room: `addtask`

Adds a task to a room.

Format: `addtask d/DESCRIPTION r/ROOM_NUMBER [dd/DUE_DATE]`

* Adds a task with the specified description to the room with the specified room number.
* Due date is optional and defaults to "-" if not provided.
* Due date can be in the any of the following formats:
  * `YYYYMMDD` (e.g. 20210131).
  * `YYYYMMDD HHmm` (e.g. 20210131 2359).
  * `D-M-YYYY` (e.g. 31-1-2021 or 31-01-2021).
  * `D-M-YYYY HHmm` (e.g. 31-1-2021 2359 or 31-01-2021 2359).
* If the time is not given for a due date, it defaults to 0000 (12am).

Examples:
* `addtask d/Remind Alice to change bedsheets. r/5` Adds a task with description "Remind Alice to change bedsheets." to Room #5.
* `addtask d/Running low on masks and needs to be restocked. r/1 dd/12-1-2021` Adds a task with description "Running low on masks and needs to be restocked." and due date "12 Jan 2021 1200" to Room #1.


### 3.13 Delete a task from a room: `deletetask`

Deletes a task from a room.

Format: `deletetask r/ROOM_NUMBER t/TASK_NUMBER`

* Deletes the task with the `TASK_NUMBER` from the room with the `ROOM_NUMBER`.
* A room with the `ROOM_NUMBER` must be present.
* A task with the `TASK_NUMBER` must be present in the room.

Examples:
* `deletetask r/1 t/3` Deletes the third task of Room #1.

### 3.14 Edit a task in a room: `edittask`

Edits a task in a room.

Format: `edittask r/ROOM_NUMBER t/TASK_NUMBER [d/DESCRIPTION] [dd/DUE_DATE]`

* Edits the task with the `TASK_NUMBER` in the room with the `ROOM_NUMBER`.

* Remove patient from room by inputting a `-` for `PATIENT_NAME`.
* At least one of the optional fields must be provided.
* A room with the `ROOM_NUMBER` must be present.
* A task with the `TASK_NUMBER` must be present in the room.
* If "-" is provided for due date, the original due date value will be cleared.
* Due date can be in the any of the following formats:
  * `YYYYMMDD` (e.g. 20210131).
  * `YYYYMMDD HHmm` (e.g. 20210131 2359).
  * `D-M-YYYY` (e.g. 31-1-2021 or 31-01-2021).
  * `D-M-YYYY HHmm` (e.g. 31-1-2021 2359 or 31-01-2021 2359).
* If the time is not given for a due date, it defaults to 0000 (12am).

Examples:
* `edittask r/5 t/1 dd/-` Removes the due date from the first task in Room #5.
* `edittask r/1 t/3 d/Running low on masks and needs to be restocked. dd/12-1-2021` Modifies the third task in Room #1 to have the description "Running low on masks and needs to be restocked." and due date "12 Jan 2021 1200".

### 3.15 Search tasks before a date: `searchtask`

Search all tasks before a date.

Format: `searchtask dd/DUE_DATE`

* Due date can be in the any of the following formats:
  * `YYYYMMDD` (e.g. 20210131).
  * `YYYYMMDD HHmm` (e.g. 20210131 2359).
  * `D-M-YYYY` (e.g. 31-1-2021 or 31-01-2021).
  * `D-M-YYYY HHmm` (e.g. 31-1-2021 2359 or 31-01-2021 2359).

Examples:
* `searchtask dd/12-1-2021` Search all tasks before 12-1-2021.

### 3.16 View help: `help`

Shows a message explaining how to access the help page.

Format: `help`

### 3.17 Save the data

Covigent data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## 4. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Covigent home folder.

--------------------------------------------------------------------------------------------------------------------

## 5. Command summary

Action | Format, Examples
--------|------------------
**Add Patient** | `addpatient n/NAME t/TEMPERATURE d/PERIOD_OF_STAY p/PHONE_NUMBER a/AGE [c/COMMENT]` <br> e.g.,`addpatient n/Betsy Crowe t/36.5 d/20201001-20201014 p/91234567 a/19 c/Is asthmatic`
**Edit Patient** | `editpatient NAME [n/NAME] [t/TEMPERATURE] [d/PERIOD_OF_STAY] [p/PHONE_NUMBER] [a/AGE] [c/COMMENT]`<br> e.g.,`editpatient James Lee t/36.5`
**Initialize Rooms** | `initRooms NUMBER_OF_ROOMS` <br> e.g., `addRooms 123`
**Search Patient** | `searchpatient [n/NAME] [tr/TEMPERATURE_RANGE]` <br> e.g., `searchpatient tr/36.5-36.7`
**List Patient** | `listpatient`<br>
**Add Rooms** | `addRooms NUMBER_OF_ROOMS` <br> e.g., `addRooms 123`
**List Room** | `listRoom` <br>
**Find Room** | `findRoom` <br>
**Edit Room** | `editroom ROOM_NUMBER [r/NEW_ROOM_NUMBER] [p/PATIENT_NAME]` <br> e.g., `editroom 1 r/2 p/alex`
**Search Room** | `searchRoom ROOM_NUMBER`<br> e.g., `searchRoom 15`
**Add Task to Room** | `addtask d/DESCRIPTION r/ROOM_NUMBER [dd/DUE_DATE]` <br>
**Delete Task from Room** | `deletetask r/ROOM_NUMBER t/TASK_NUMBER` <br>
**Edit Task in Room** | `edittask r/ROOM_NUMBER t/TASK_NUMBER [d/DESCRIPTION] [dd/DUE_DATE]` <br>
**Search Task** | `searchtask dd/DUE_DATE` <br>
**Help** | `help`
