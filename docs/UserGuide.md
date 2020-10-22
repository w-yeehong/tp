---
layout: page
title: User Guide
---

# Covigent - User Guide

1. [Introduction](#1-introduction)
2. [About this Document](#2-about-this-document)
3. [Getting Started](#3-getting-started)
4. [Glossary](#4-glossary)
5. [Features](#5-features)<br>
    5.1. [Command Format](#51-command-format)<br>
    5.2. [Patients](#52-patients)<br>
        5.2.1. [Add a Patient: `addpatient`](#521-add-a-patient-addpatient)<br>
        5.2.2  [Delete a Patient: `deletepatient`](#522-delete-a-patient-deletepatient)<br>
        5.2.3  [Edit Patient Details: `editpatient`](#523-edit-patient-details-editpatient)<br>
        5.2.4  [Search information: `searchpatient`](#524-search-information-searchpatient)<br>
        5.2.5  [List all Patients: `listpatient`](#525-list-all-patients-listpatient)<br>
        5.2.6  [Allocate a Patient to a Room: `editroom`](#526-allocate-a-patient-to-a-room-editroom)<br>
    5.3. [Room](#53-room)<br>
        5.3.1  [Initialize rooms in hotel: `initRooms`](#531-initialize-rooms-in-hotel-initrooms)<br>
        5.3.2  [Edit Room: `editroom`](#532-edit-room-editroom)<br>
        5.3.3  [Search by Room Number: `searchroom`](#533-search-by-room-number-searchroom)<br>
        5.3.4  [Search for Room with Patient: `searchroom`](#534-search-for-room-with-patient-searchroom)<br>
        5.3.5  [Lists the current rooms: `listRoom`](#535-lists-the-current-rooms-listroom)<br>
        5.3.6  [Find the first free room: `findRoom`](#536-find-the-first-free-room-findroom)<br>
    5.4 [Task](#54-task)<br>
        5.4.1  [Add a task to a room: `addtask`](#541-add-a-task-to-a-room-addtask)<br>
        5.4.2  [Delete a task from a room: `deletetask`](#542-delete-a-task-from-a-room-deletetask)<br>
        5.4.3  [Edit a task in a room: `edittask`](#543-edit-a-task-in-a-room-edittask)<br>
        5.4.4  [Searches all tasks before the given date: `searchtask`](#544-search-tasks-before-a-date-searchtask)<br>
    5.5  [View help: `help`](#55-view-help-help)<br>
    5.6  [Exit the application: `exit`](#56-exit-the-application-exit)<br>
    5.7  [Autosave](#57-autosave)<br>
 6. [Command Summary](#6-command-summary)
 7. [FAQ](#7-faq)



--------------------------------------------------------------------------------------------------------------------

## 1. Introduction

Covigent (CG) is a desktop app for managing information of quarantined individuals and the tasks to be done by staff of the quarantine facilities. 
It is optimized for use via a Command Line Interface (CLI) while retaining the benefits of a Graphical User Interface (GUI).
This means that you operate the application mainly by typing commands into a Command Box.
If you can type quickly, CG can improve your efficiency in managing your patients and tasks instead of using traditional GUI apps.
Interested? Jump to [Section 3, "Getting Started"](#3-getting-started) to get started.

This is what **Covigent** looks like:
![Ui](images/Ui_UG.png)
*Figure 1. The graphical User Interface for **Covigent***

--------------------------------------------------------------------------------------------------------------------

## 2. About this Document

--------------------------------------------------------------------------------------------------------------------

## 3. Getting Started

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

## 4. Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X.
* **Patient**: An individual who resides in the quarantine facility. 
* **Task**: A task to be completed by staff of the quarantine facility.

--------------------------------------------------------------------------------------------------------------------

## 5. Features

<div markdown="block" class="alert alert-info">

### 5.1 Command Format
**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addpatient n/NAME`, `NAME` is a parameter which can be used as `addpatient n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [c/COMMENT]` can be used as `n/John Doe c/Is vegan` or as `n/John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.
  
</div>

### 5.2 Patients

This section contains all the commands related to patients. Scroll down to find out which feature you need!

#### 5.2.1 Add a patient: `addpatient`

You can use this command to add the patient details (name, temperature, period of stay, phone number, age and comment) to Covigent.

Format: `addpatient n/NAME t/TEMPERATURE d/PERIOD_OF_STAY p/PHONE_NUMBER a/AGE [c/COMMENT]`

Additional Information:
* Duplicate names are not allowed. If an entry of name `John Doe` is recorded in Covigent, you should not add a patient of the name `John Doe` again.
* `PERIOD_OF_STAY` is in the format `YYYYMMDD-YYYYMMDD`.
* `TEMPERATURE` should be keyed in to 1 decimal place (e.g. 37.0 instead of 37).
* `COMMENT`for the patient is optional. 

Example(s):
* `addpatient n/John Doe p/98765432 t/37.4 d/20200910-20200924 a/35` A patient named John Doe with phone number 98765432, temperature 37.4, period of stay from 10 September 2020 to 24 September 2020 and age 35 is added to Covigent.
* `addpatient n/Betsy Crowe t/36.5 d/20201001-20201014 p/91234567 a/19 c/Is asthmatic` A patient named Betsy Crowe with temperature 36.5, period of stay from 1 October 2020 to 14 October 2020, phone number 91234567, age 19 and comment is added to Covigent.

Expected Outcome: <br>
The result display shows a success message "New patient added: John Doe" and the new details of the patient. The new patient can now be found in the patient panel in Covigent.


#### 5.2.2 Delete a patient: `deletepatient`

You can use this command to delete the details of the existing patient identified by his/her name from Covigent. 

Format: `deletepatient NAME`

Additional Information:
* `NAME` **must match exactly with the name of the patient that was input into Covigent previously** and is case-insensitive.

Example(s):
* `deletepatient Mary Doe` The patient details of Mary Doe will be deleted from Covigent.

Expected Outcome: <br>
The result display shows a success message "Deleted Patient: Mary Doe" and the details of Mary Doe. If the patient to be deleted was allocated a room previously, the room will be updated to unoccupied in Covigent.


#### 5.2.3 Edit Patient Details: `editpatient`

Edits an existing patient's details in the application.

Format: `editpatient NAME [n/NAME] [t/TEMPERATURE] [d/PERIOD_OF_STAY] [p/PHONE_NUMBER] [a/AGE] [c/COMMENT]`

Additional Information:
* Edits the patient with the specified `NAME`. The name refers to the name of the patient inputted into the application earlier. The name **must match exactly with the name of the patient**.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* `NAME` is case-insensitive.
* `TEMPERATURE` must be to 1 decimal place (e.g. 37.0 instead of 37).
* `PERIOD_OF_STAY` is in the format `YYYYMMDD-YYYYMMDD`.
* `PHONE_NUMBER` consists of only 8 digits (e.g. 84321234).
* `AGE` should be a positive integer between 0 to 119.

Example(s):
*  `editpatient john doe p/91234567` Edits the phone number of john doe to be `91234567`.
*  `editpatient alex t/36.7 a/21 d/20200303-20200315` Edits the temperature, age and period of stay of alex to be `36.7`, `21` and `20200303-20200315` respectively.

Expected Outcome: <br>
The result box displays the message, "Edited Patient: John Doe Temperature: 36.7 Period of stay: 08 Sep 2020 to 18 Sep 2020 Phone: 12345678 Age: 23 Comment: -". 
Details panel will show the details of the edited patient.

#### 5.2.4 Search information: `searchpatient`

Searches the patients that matches the given criteria in the application.

Format: `searchpatient [n/NAME] [tr/TEMPERATURE_RANGE]`

* Only one of the fields can be provided.
* The temperature range is inclusive of start and end temperatures.

Example(s):
*  `searchpatient n/john` Searches patients with a name John.
*  `searchpatient tr/36.5-36.7` Searches patients with temperature 36.5 to 36.7 degree, celsius, both inclusive. 


#### 5.2.5 List all patients: `listpatient`

Shows a list of all patients in the patient tab.

Format: `listpatient`

#### 5.2.6 Allocate a Patient to a Room: `editroom`

Allocates a patient to a room.

Format: `editroom ROOM_NUMBER p/PATIENT_NAME`

* Allocates a person to the room with the specified `ROOM_NUMBER`.
* `PATIENT_NAME` must match exactly with the patient name that was inputted into the application.
* `PATIENT_NAME` is case-insensitive.
* `PATIENT_NAME` is compulsory and must be provided.
* A room with the `ROOM_NUMBER` must be present.
* This is only one of features of the editroom command. Refer to the full command [here](#532-edit-room-editroom).

Example(s):
* `editroom 1 p/alex`. Allocates alex to Room #1.

Expected Outcome: <br>
The result box displays the message, "Edited Room: Room Number: 1 Patient: Alex Temperature: 36.7 Period of stay: 08 Sep 2020 to 18 Sep 2020
Phone: 12345678 Age: 23 Comment: - TaskList: -". Details panel will show the details of the room with the allocated patient.

### 5.3 Room

This section contains all the commands related to rooms. Scroll down to find out which feature you need!

#### 5.3.1 Initialize rooms in hotel: `initRoom`

Initializes the number of rooms in the quarantine facility to the app, if there was data given previously, they would 
be stored.

Format: `initRoom NUMBER_OF_ROOMS`

* Adds NUMBER_OF_ROOMS rooms into the hotel system, if there were previously added information that information for respective rooms will still be there

Example(s):
* `initRoom 123`. 123 rooms are initialised in the hotel facility.
* `initRoom 400`. 400 rooms are initialised in the hotel facility.

Expected Outcome:<br>
The result display shows a success message "Initialize the number of rooms to 400 rooms in the application.".

#### 5.3.2 Edit room: `editroom`

Edits an existing room in the application.

Format: `editroom ROOM_NUMBER [r/NEW_ROOM_NUMBER] [p/PATIENT_NAME]`

* Edits the room with the specified `ROOM_NUMBER`.
* `PATIENT_NAME` must match exactly with the patient name that was inputted into the application.
* `PATIENT_NAME` is case-insensitive.
* Remove patient from room by inputting a `-` for `PATIENT_NAME`.
* At least one of the optional fields must be provided.
* A room with the `ROOM_NUMBER` must be present.
* Refer [here](#526-allocate-a-patient-to-a-room-editroom) for the instructions on allocating a patient to a room.

Example(s):
* `editroom 1 r/2 p/alex`. Changes the room number from #1 to #2. Afterwards, remove the previous patient in the room and change it to alex.
* `editroom 1 p/-`. Removes the patient from the Room #1. 
* `editroom 1 r/3 p/-`. Changes the room number from #1 to #3. Afterwards, removes the patient from Room #3.

Expected Outcome: <br>
The result box displays the message, "Edited Room: Room Number: 1 Patient: Alex Temperature: 36.7 Period of stay: 08 Sep 2020 to 18 Sep 2020
Phone: 12345678 Age: 23 Comment: - TaskList: -".

#### 5.3.3 Search by Room Number: `searchroom`

You can use this command to search for the room details with the specified room number.

Format: `searchroom r/ROOM_NUMBER`

Example(s):
* `searchroom r/6` The room details of room number 6 will be searched.

Expected Outcome:<br>
The result display shows a success message "Room has been found and listed." and the specified room is shown in the room panel.


#### 5.3.4 Search for Room with Patient: `searchroom`

You can use this command to search for the room that the specified patient is residing in. 

Format `searchroom n/NAME`

Addition Information:
* `NAME` **must match exactly with the name of the patient that was input into Covigent previously** and is case-insensitive.

Example(s):
* `searchroom n/Mary Doe` The room details of the room that Mary Doe resides in will be searched.

Expected Outcome: <br>
The result display shows a success message "Room has been found and listed." and the specified room that the patient resides in is shown in the room panel.

#### 5.3.5 Lists the current rooms: `listRoom`

Lists all the rooms in the hotel together with informtion of whether the room is occupied or not.

Format: `listRoom` 

Expected Outcome:<br>
The result display shows a success message "All rooms are listed.".

#### 5.3.6 Find the first free room: `findRoom`

Finds the room with the lowest room number that is free for use.

Format: `findRoom`

* Finds the room number of least value that can be safely used for accommodation

Expected Outcome:<br>
The result display shows a success message "Room Number 1 is empty.".

### 5.4 Task

This section contains all the commands related to tasks. Scroll down to find out which feature you need!

#### 5.4.1 Add a task to a room: `addtask`

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

Example(s):
* `addtask d/Remind Alice to change bedsheets. r/5` Adds a task with description "Remind Alice to change bedsheets." to Room #5.
* `addtask d/Running low on masks and needs to be restocked. r/1 dd/12-1-2021` Adds a task with description "Running low on masks and needs to be restocked." and due date "12 Jan 2021 1200" to Room #1.

#### 5.4.2 Delete a task from a room: `deletetask`

Deletes a task from a room.

Format: `deletetask r/ROOM_NUMBER t/TASK_NUMBER`

* Deletes the task with the `TASK_NUMBER` from the room with the `ROOM_NUMBER`.
* A room with the `ROOM_NUMBER` must be present.
* A task with the `TASK_NUMBER` must be present in the room.

Example(s):
* `deletetask r/1 t/3` Deletes the third task of Room #1.

#### 5.4.3 Edit a task in a room: `edittask`

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

Example(s):
* `edittask r/5 t/1 dd/-` Removes the due date from the first task in Room #5.
* `edittask r/1 t/3 d/Running low on masks and needs to be restocked. dd/12-1-2021` Modifies the third task in Room #1 to have the description "Running low on masks and needs to be restocked." and due date "12 Jan 2021 1200".

#### 5.4.4 Search tasks before a date: `searchtask`

Search all tasks before a date.

Format: `searchtask dd/DUE_DATE`

* Due date can be in the any of the following formats:
  * `YYYYMMDD` (e.g. 20210131).
  * `YYYYMMDD HHmm` (e.g. 20210131 2359).
  * `D-M-YYYY` (e.g. 31-1-2021 or 31-01-2021).
  * `D-M-YYYY HHmm` (e.g. 31-1-2021 2359 or 31-01-2021 2359).

Example(s):
* `searchtask dd/12-1-2021` Search all tasks before 12-1-2021.

### 5.5 View help: `help`

Shows a message explaining how to access the help page.

Format: `help`

### 5.6 Exit the application: `exit`

### 5.7 Autosave

Covigent data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## 6. Command Summary

Action | Format, Examples
--------|------------------
**Add Patient** | `addpatient n/NAME t/TEMPERATURE d/PERIOD_OF_STAY p/PHONE_NUMBER a/AGE [c/COMMENT]` <br> e.g., addpatient n/Betsy Crowe t/36.5 d/20201001-20201014 p/91234567 a/19 c/Is asthmatic
**Delete Patient** | `deletepatient NAME` <br> e.g., deletepatient Mary Doe
**Edit Patient** | `editpatient NAME [n/NAME] [t/TEMPERATURE] [d/PERIOD_OF_STAY] [p/PHONE_NUMBER] [a/AGE] [c/COMMENT]`<br> e.g., editpatient James Lee t/36.5
**Search Patient** | `searchpatient [n/NAME] [tr/TEMPERATURE_RANGE]` <br> e.g., searchpatient tr/36.5-36.7
**List Patients** | `listpatient`<br>
**Allocate Patient to Room** | `editroom ROOM_NUMBER p/NAME` <br> e.g., editroom 5 p/David Li
**Initialise Room** | `initroom NUMBER_OF_ROOMS` <br> e.g., initroom 123
**Edit Room Number** | `editroom ROOM_NUMBER r/NEW_ROOM_NUMBER` <br> e.g., editroom 1 r/2
**Search by Room Number** | `searchroom r/ROOM_NUMBER`<br> e.g., searchroom r/15
**Search for Room with Patient** | `searchroom n/NAME`<br> e.g., searchroom n/Jane Doe
**List Rooms** | `listroom` <br>
**Find Empty Room** | `findemptyroom` <br>
**Add Task to Room** | `addtask d/DESCRIPTION r/ROOM_NUMBER [dd/DUE_DATE]` <br> e.g., addtask d/Running low on masks and needs to be restocked. r/1 dd/12-1-2021 
**Delete Task from Room** | `deletetask r/ROOM_NUMBER t/TASK_NUMBER` <br> e.g., deletetask r/1 t/3
**Edit Task in Room** | `edittask r/ROOM_NUMBER t/TASK_NUMBER [d/DESCRIPTION] [dd/DUE_DATE]` <br> e.g., edittask r/5 t/1 dd/-
**Search Task** | `searchtask dd/DUE_DATE` <br> e.g., searchtask dd/12-1-2021
**Help** | `help`
**Exit** | `exit`

--------------------------------------------------------------------------------------------------------------------

## 7. FAQ

**Q**: Why does the output sometimes appear red and sometimes balck?<br>
**A**: If the command input is given in the wrong format, then the output is given in red. Also the correct format for the particular command is given. 
        Example:<br>
        Invalid command format! 
        Please give the number of digits in numbers
        Example: addRooms 200

**Q**: Will data be stored in the system after closing the app<br>
**A**: The data is stored in the hard disk and therefore would not be deleted even if you close the app
