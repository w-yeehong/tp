# Covigent - User Guide

1. [Introduction](#1-introduction)
1. [About this Document](#2-about-this-document)<br>
   2.1. [What's in Covigent](#21-what-is-in-covigent)<br>
   2.2. [About the Guide](#22-about-the-guide)<br>
   2.3. [Formats in the Guide](#23-formats-in-the-guide)<br>
1. [Getting Started](#3-getting-started)
   3.1. [Setting Up](#31-setting-up)<br>
   3.2. [Using Covigent](#32-using-covigent)<br>
1. [Glossary](#4-glossary)
1. [Features](#5-features)<br>
    5.1. [Command Format](#51-command-format)<br>
    5.2. [Patients](#52-patients)<br>
          5.2.1. [Add a Patient: `addpatient`](#521-add-a-patient-addpatient)<br>
          5.2.2  [Delete a Patient: `deletepatient`](#522-delete-a-patient-deletepatient)<br>
          5.2.3  [Edit Patient Details: `editpatient`](#523-edit-patient-details-editpatient)<br>
          5.2.4  [Search Patients by Information: `searchpatient`](#524-search-patients-by-information-searchpatient)<br>
          5.2.5  [List all Patients: `listpatient`](#525-list-all-patients-listpatient)<br>
          5.2.6  [Allocate a Patient to a Room: `editroom`](#526-allocate-a-patient-to-a-room-editroom)<br>
    5.3. [Room](#53-room)<br>
          5.3.1  [Initialise Rooms in Hotel: `initroom`](#531-initialise-rooms-in-hotel-initroom)<br>
          5.3.2  [Edit Room: `editroom`](#532-edit-room-editroom)<br>
          5.3.3  [Search by Room Number: `searchroom`](#533-search-by-room-number-searchroom)<br>
          5.3.4  [Search for Room with Patient: `searchroom`](#534-search-for-room-with-patient-searchroom)<br>
          5.3.5  [List the Current Rooms: `listroom`](#535-list-the-current-rooms-listroom)<br>
          5.3.6  [Find the first free room: `findroom`](#536-find-the-first-free-room-findemptyroom)<br>
    5.4 [Task](#54-task)<br>
          5.4.1  [Add a Task to a Room: `addtask`](#541-add-a-task-to-a-room-addtask)<br>
          5.4.2  [Delete a Task from a Room: `deletetask`](#542-delete-a-task-from-a-room-deletetask)<br>
          5.4.3  [Edit Task Description or Due Date: `edittask`](#543-edit-task-description-or-due-date-edittask)<br>
          5.4.4  [Remove Due Date from a Task: `edittask`](#544-remove-due-date-from-a-task-edittask)<br>
          5.4.5  [Search all Tasks before the Given Date: `searchtask`](#545-search-tasks-before-a-given-date-searchtask)<br>
    5.5  [View Help: `help`](#55-view-help-help)<br>
    5.6  [Exit Covigent: `exit`](#56-exit-covigent-exit)<br>
    5.7  [Autosave](#57-autosave)<br>
1. [Command Summary](#6-command-summary)
1. [FAQ](#7-faq)


--------------------------------------------------------------------------------------------------------------------

## 1. Introduction

**Covigent** is a desktop app for managing information of quarantined individuals and the tasks to be done by staff of the quarantine facilities. 
It is optimized for use via a Command Line Interface (CLI) while retaining the benefits of a Graphical User Interface (GUI).
This means that you operate Covigent mainly by typing commands into a Command Box.
If you can type quickly, **Covigent** can improve your efficiency in managing your patients and tasks instead of using traditional GUI apps.

Interested? Jump to [Section 3, "Getting Started"](#3-getting-started) to get started.

This is what **Covigent** looks like:

<p align="center">
    <img src="images/Ui_UG.png">
    <br />
    <i>Figure 1. The Graphical User Interface for Covigent</i>
</p>

_Written by: Ming De_


--------------------------------------------------------------------------------------------------------------------

## 2. About this Document

Welcome to the **Covigent User Guide**! Choose a link in the **Feature** section, get a step-by-step instruction, and understand how to use **Covigent**. 

### 2.1 What is in Covigent

Covigent contains the following features:
* Patient Features: `addpatient`, `deletepatient`, `editpatient`, `searchpatient`, `listpatient`.
* Room Features: `initroom`, `editroom`, `searchroom`, `listroom`, `findemptyroom`.
* Task Features: `addtask`, `deletetask`, `edittask`, `searchtask`.
* Miscellaneous Features: `help`, `exit`, `autosave`.


### 2.2 About the Guide

This tutorial gives you an overview of the features in **Covigent** and shows you how to get started using **Covigent**.


### 2.3 Formats in the Guide

Note the following formatting used in this document:
* ![icon](images/infoicon.PNG) This symbol indicates important information.

* ![icon](images/commandhighlight.PNG) 
A grey highlight (called a mark-up) indicates that this is a field or command
that can be typed into the command line and executed by **Covigent**.

_Written by: Wai Lok_


--------------------------------------------------------------------------------------------------------------------

## 3. Getting Started

If you are tired of lengthy and problematic installation processes, **Covigent** is perfect for you.
The setup is minimal as the app works out of the box. Follow the steps below to try it out!

### 3.1 Setting Up

No matter whether you are using Windows, Mac OS X, or other operating systems, you can set up **Covigent** in 4 simple steps.

1. Install _Java 11_ or a later version. The latest version of _Java_ can be found [here](https://java.com/en/download/).

1. Download the latest version of Covigent from [here](https://github.com/AY2021S1-CS2103T-W12-1/tp/releases). Look for the file `covigent.jar`.

1. Copy the file to the folder you want to use as the home folder of Covigent.

1. Double-click on _covigent.jar_ to start Covigent.

### 3.2 Using Covigent

Every feature in **Covigent** has a corresponding command. Using a feature is as simple as typing a command in the command box (see Figure 2) and pressing Enter to execute it.

<p align="center">
    <img src="images/CommandBox.png" width="800" height="100">
    <br />
    <i>Figure 2. Command Box of Covigent</i>
</p>

With commands, you can add quarantined individuals, create rooms, assign tasks to rooms, and more. Some example commands you may try:

   * **`addpatient`** `n/John Doe t/37.4 d/20200910-20200924 p/98765432 a/35` : Adds a quarantined individual named _John Doe_ with age _35_, phone number _98765432_, period of stay _20200910-20200924_, and temperature _37.4_.
   * **`initroom`** `5` : Creates 5 rooms in the quarantine facility.
   * **`addtask`** `r/3 d/Restock supplies dd/20201230 2359` : Adds a task to room 3 with description _Restock supplies_ and due date _20201230 2359_.

For more details of each command, please refer to the section on [Section 5, Features](#5-features).

_Written by: Yee Hong_


--------------------------------------------------------------------------------------------------------------------

## 4. Glossary

* **Command Line Interface**: A form of user interface that processes commands to a computer program in the form of lines of text.
* **Graphical User Interface**: A form of user interface that allows users to interact with electronic devices through graphical icons.
* **Patient**: An individual who resides in the quarantine facility. 
* **Task**: A task to be completed by staff of the quarantine facility.

_Written by: Yun Qing_


--------------------------------------------------------------------------------------------------------------------

## 5. Features

<div markdown="block" class="alert alert-info">


### 5.1 Command Format
**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the fields to be supplied by the user.<br>
  e.g. in `addpatient n/NAME`, `NAME` is a field which can be used as `addpatient n/John Doe`.

* Fields in square brackets are optional.<br>
  e.g `n/NAME [c/COMMENT]` can be used as `n/John Doe c/Is vegan` or as `n/John Doe`.

* Fields can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>


### 5.2 Patients

This section contains all the commands related to patients. Scroll down to find out which feature you need!

#### 5.2.1 Add a patient: `addpatient` 

Adds the patient details (name, temperature, period of stay, phone number, age and comment) to Covigent.

Format: `addpatient n/NAME t/TEMPERATURE d/PERIOD_OF_STAY p/PHONE_NUMBER a/AGE [c/COMMENT]`

Additional Information:
* Duplicate names are not allowed. If an entry of name `John Doe` is recorded in Covigent, you should not add a patient of the name `John Doe` again.
* `PERIOD_OF_STAY` is in the format `YYYYMMDD-YYYYMMDD`.
* `TEMPERATURE` should be keyed in to 1 decimal place (e.g. 37.0 instead of 37).
* `COMMENT`for the patient is optional. 

Example(s):
* `addpatient n/John Doe p/98765432 t/37.4 d/20200910-20200924 a/35` A patient named John Doe with phone number _98765432_, temperature _37.4_, period of stay from _10 September 2020 to 24 September 2020_ and age _35_ is added to Covigent.
* `addpatient n/Betsy Crowe t/36.5 d/20201001-20201014 p/91234567 a/19 c/Is asthmatic` A patient named Betsy Crowe with temperature _36.5_, period of stay from _1 October 2020 to 14 October 2020_, phone number _91234567_, age _19_ and comment _Is asthmatic_ is added to Covigent.

Expected Outcome: <br>
* Using the first example, the result box displays the message, "New patient added: John Doe Temperature: 37.4 Period of stay: 10 Sep 2020 to 24 Sep 2020 Phone: 98765432 Age: 35 Comment: -".
* The newly added patient can now be found in the list of patients in Covigent.

_Written by: Yun Qing_


#### 5.2.2 Delete a patient: `deletepatient` 

Deletes the details of the existing patient identified by his/her name from Covigent. 

Format: `deletepatient NAME`

Additional Information:
* `NAME` **must match exactly with the name of the patient that was input into Covigent previously** and is case-insensitive.
* If the patient to be deleted was allocated a room previously, the room will be updated to unoccupied in Covigent.

Example(s):
* `deletepatient Mary Doe` The patient details of Mary Doe will be deleted from Covigent.

Expected Outcome: <br>
* Using the first example, the result box displays the message "Deleted Patient: Mary Doe Temperature: 37.0 Period of stay: 14 Sep 2020 to 28 Sep 2020 Phone: 98765432 Age: 22  Comment: Vegan,asthmatic". 
* The deleted patient can no longer be found in the list of patients in Covigent.

_Written by: Yun Qing_


#### 5.2.3 Edit Patient Details: `editpatient`

Edits an existing patient's details in Covigent.

Format: `editpatient NAME [n/NAME] [t/TEMPERATURE] [d/PERIOD_OF_STAY] [p/PHONE_NUMBER] [a/AGE] [c/COMMENT]`

Additional Information:
* Edits the patient with the specified `NAME`. 
* `NAME` **must match exactly with the name of the patient that was input into Covigent previously**.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* `NAME` is case-insensitive.
* `TEMPERATURE` must be to 1 decimal place (e.g. 37.0 instead of 37).
* `PERIOD_OF_STAY` is in the format `YYYYMMDD-YYYYMMDD`.
* `PHONE_NUMBER` consists of only 8 digits (e.g. 84321234).
* `AGE` should be a positive integer between 0 to 119.

Example(s):
*  `editpatient john doe p/91234567` The phone number of the patient named John Doe will be updated to _91234567_.
*  `editpatient alex t/36.7 a/21 d/20200303-20200315` The temperature, age and period of stay of the patient named Alex will be updated to _36.7_, _21_ and _20200303-20200315_ respectively.

Expected Outcome: <br>
* Using the first example, the result box displays the message, "Edited Patient: John Doe Temperature: 36.7 Period of stay: 08 Sep 2020 to 18 Sep 2020 Phone: 12345678 Age: 23 Comment: -". 
* Details panel will show the details of the edited patient.

_Written by: Ming De_


#### 5.2.4 Search Patients by Information: `searchpatient`

Searches the patients that match the given criteria(name or a range of temperature) in Covigent.

Format: `searchpatient [n/NAME] [tr/TEMPERATURE_RANGE]`

Additional Information:

* Only one of the fields can be provided. If the field `n\name` is entered, you should not enter the field `tr/TEMPERATURE_RANGE`.
* The `TEMPERATURE_RANGE` is inclusive of start and end temperatures. `tr/35.5-36.0` means a temperature range of 35.5-36.0 degree, celsius, both inclusive.

Example(s):

*  `searchpatient n/john` Searches patients with a name John.
*  `searchpatient tr/36.5-36.7` Searches patients with temperature range _36.5 to 36.7_ degree celsius, both inclusive.

Expected Outcome: <br>

* Using the second example, the result box displays the message, "Listed patient(s) matching the criteria.".
* Patients with a temperature between 36.5 and 36.7 degree celsius can now be found in the list of patients in Covigent.

_Written by: Wai Lok_


#### 5.2.5 List all patients: `listpatient`

Shows a list of all patients in the patient tab.

Format: `listpatient`

Example(s):

*  `listpatient` Lists all patients that are present in Covigent.

Expected Outcome: <br>

* The result box displays the message, "All patients are listed.".

_Written by: Wai Lok_


#### 5.2.6 Allocate a Patient to a Room: `editroom`

Allocates a patient to a room.

Format: `editroom ROOM_NUMBER p/PATIENT_NAME`

Additional Information:
* Allocates a person to the room with the specified `ROOM_NUMBER`.
* `PATIENT_NAME` **must match exactly with the name of the patient that was input into Covigent previously**.
* `PATIENT_NAME` is case-insensitive.
* `PATIENT_NAME` is compulsory and must be provided.
* A room with the `ROOM_NUMBER` must be present.
* This is only one of features of the editroom command. Refer to the full command [here](#532-edit-room-editroom).

Example(s):
* `editroom 1 p/alex`. The patient named Alex will be allocated to Room #1.

Expected Outcome: <br>
* Using the first example, the result box displays the message, "Edited Room: Room Number: 1 Patient: Alex Temperature: 36.7 Period of stay: 08 Sep 2020 to 18 Sep 2020
Phone: 12345678 Age: 23 Comment: - TaskList: -". 
* Details panel will show the details of the room with the allocated patient.

_Written by: Ming De_


### 5.3 Room

This section contains all the commands related to rooms. Scroll down to find out which feature you need!

#### 5.3.1 Initialise Rooms in Hotel: `initroom`

Initialises the number of rooms in the quarantine facility to the app, if there was data given previously, they would 
be stored.

Format: `initroom NUMBER_OF_ROOMS`

Additional Information:
* Adds NUMBER_OF_ROOMS rooms into the hotel system, if there were previously added information that information for respective rooms will still be there.

Example(s):
* `initroom 123`. 123 rooms are initialised in Covigent.
* `initroom 400`. 400 rooms are initialised in Covigent.

Expected Outcome: <br>
* The result display shows a success message "Initialise the number of rooms to 400 rooms in the application.".

_Written by: Noorul Azlina_


#### 5.3.2 Edit room: `editroom`

Edits an existing room in Covigent.

Format: `editroom ROOM_NUMBER [r/NEW_ROOM_NUMBER] [p/PATIENT_NAME]`

Additional Information:
* Edits the room with the specified `ROOM_NUMBER`.
* `PATIENT_NAME` **must match exactly with the name of the patient that was input into Covigent previously**.
* `PATIENT_NAME` is case-insensitive.
* Remove patient from room by inputting a `-` for `PATIENT_NAME`.
* At least one of the optional fields must be provided.
* A room with the `ROOM_NUMBER` must be present.
* Refer [here](#526-allocate-a-patient-to-a-room-editroom) for the instructions on allocating a patient to a room.


Example(s):
* `editroom 1 r/2 p/alex`. The room with room number #1 will be changed to #2. Afterwards, the previous patient in room #2 will be replaced with the patient named _Alex_.
* `editroom 1 p/-`. The patient in the room with room number #1 will be removed. 
* `editroom 1 r/3 p/-`. The room with room number #1 will be changed to #3. Afterwards, the previous patient in room #3 will be removed.

Expected Outcome: <br>
* Using the first example, the result box displays the message, "Edited Room: Room Number: 2 Patient: Alex Temperature: 37.0 Period of stay: 08 Aug 2020 to 19 Aug 2020 Phone: 99272758 Age: 37 Comment: - TaskList: -". 
* Details panel will show the details of the newly edited room.

_Written by: Ming De_


#### 5.3.3 Search by Room Number: `searchroom` 

Searches for the room details with the specified room number.

Format: `searchroom r/ROOM_NUMBER`

Example(s):
* `searchroom r/6` The room details of room number 6 will be searched.

Expected Outcome:<br>
* The result box displays a message "Room has been found and listed." 
* The room with the specified room number is shown in the room details panel.

_Written by: Yun Qing_


#### 5.3.4 Search for Room with Patient: `searchroom` 

Searches for the room that the specified patient is residing in. 

Format `searchroom n/NAME`

Addition Information:
* `NAME` **must match exactly with the name of the patient that was input into Covigent previously** and is case-insensitive.

Example(s):
* `searchroom n/Mary Doe` The room details of the room that Mary Doe resides in will be searched.

Expected Outcome: <br>
* The result box displays a message "Room has been found and listed." 
* The room that the specified patient resides in is shown in the room details panel.

_Written by: Yun Qing_


#### 5.3.5 List the Current Rooms: `listroom`

Lists all the rooms in the hotel together with informtion of whether the room is occupied or not.

Format: `listroom` All the rooms in Covigent.

Expected Outcome: <br>
* The result display shows a success message "All rooms are listed.".

_Written by: Noorul Azlina_


#### 5.3.6 Find the First Free Room: `findemptyroom`

Finds the room with the lowest room number that is free for use.

Format: `findemptyroom` The unoccupied room in Covigent with the lowest room number will be displayed on UI.

Expected Outcome: <br>
* The result display shows a success message "Room Number 1 is empty.".

_Written by: Noorul Azlina_


### 5.4 Task

This section contains all the commands related to tasks. Scroll down to find out which feature you need!

#### 5.4.1 Add a task to a room: `addtask`

Adds a task to a room.

Format: `addtask d/DESCRIPTION r/ROOM_NUMBER [dd/DUE_DATE]`

Additional Information:
* Adds a task to a room with the specified `ROOM_NUMBER`.
* `ROOM_NUMBER` refers to the number displayed beside each room under the list of rooms.
E.g. For Room #5, the `ROOM_NUMBER` is 5.
* A room with the `ROOM_NUMBER` must be present in Covigent.
* `DUE_DATE` is optional and defaults to `-` if not provided.
* `DUE_DATE` can be in the any of the following formats:
  * `YYYYMMDD` (e.g. 20210131).
  * `YYYYMMDD HHmm` (e.g. 20210131 2359).
  * `D/M/YYYY` (e.g. 31/1/2021 or 31/01/2021).
  * `D/M/YYYY HHmm` (e.g. 31/1/2021 2359 or 31/01/2021 2359).
* If you do not provide the time for a `DUE_DATE`, it defaults to `0000` (12am).

Example(s):
* `addtask r/5 d/Remind Alice to change bedsheets.` A task with description _Remind Alice to change bedsheets._ is added to Room #5.
* `addtask r/1 d/Running low on masks and needs to be restocked. dd/12/1/2021` A task with description _Running low on masks and needs to be restocked._ and due date _12 Jan 2021 0000_ is added to Room #1.

Expected Outcome: <br>
* Using the first example, the result box displays the message _New Task added to Room 5. Description: Remind Alice to change bedsheets. Due Date: -_.
* The new task can now be found in the Room #5.
* The new task can now be found in the list of tasks in Covigent.

_Written by: Yee Hong_


#### 5.4.2 Delete a task from a room: `deletetask`

Deletes an existing task from a room.

Format: `deletetask r/ROOM_NUMBER t/TASK_NUMBER`

Additional Information:
* Deletes a task with the `TASK_NUMBER` from the room with the `ROOM_NUMBER`.
* `ROOM_NUMBER` refers to the number displayed beside each room under the list of rooms.
E.g. For "Room #5", the `ROOM_NUMBER` is 5.
* A room with the `ROOM_NUMBER` must be present in Covigent.
* `TASK_NUMBER` refers to the number displayed beside each task in the details panel for rooms.
E.g. For "3. Remind Alice to change bedsheets.", the `TASK_NUMBER` is 3.
* A task with the `TASK_NUMBER` must be present in the room.

Example(s):
* `deletetask r/1 t/3` The third task (Task 3) of Room 1 is deleted.

Expected Outcome: <br>
* The result box displays the message _Task 3 deleted from Room 1._, followed by the description and due date of Task 3.
* Task 3 is no longer in Room #1.
* For tasks that come after Task 3, the `TASK_NUMBER` is decreased by 1.
E.g. The previous Task 4 will become Task 3.
* Task 3 is no longer in the list of tasks in Covigent.

_Written by: Yee Hong_


#### 5.4.3 Edit Task Description or Due Date: `edittask`

Edits the description or due date of an existing task in a room.

Format: `edittask r/ROOM_NUMBER t/TASK_NUMBER [d/DESCRIPTION] [dd/DUE_DATE]`

Additional Information:
* Edits the task with the `TASK_NUMBER` in the room with the `ROOM_NUMBER`.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* `ROOM_NUMBER` refers to the number displayed beside each room under the list of rooms.
E.g. For "Room #5", the `ROOM_NUMBER` is 5.
* A room with the `ROOM_NUMBER` must be present in Covigent.
* `TASK_NUMBER` refers to the number displayed beside each task in the details panel for rooms.
E.g. For "3. Remind Alice to change bedsheets.", the `TASK_NUMBER` is 3.
* A task with the `TASK_NUMBER` must be present in the room.
* `DUE_DATE` can be in the any of the following formats:
  * `YYYYMMDD` (e.g. 20210131).
  * `YYYYMMDD HHmm` (e.g. 20210131 2359).
  * `D/M/YYYY` (e.g. 31/1/2021 or 31/01/2021).
  * `D/M/YYYY HHmm` (e.g. 31/1/2021 2359 or 31/01/2021 2359).
* If you do not provide the time for a `DUE_DATE`, it defaults to `0000` (12am).

Example(s):
* `edittask r/3 t/1 d/Return a call to the patient. dd/12/1/2021 1500` The description and due date of the first task (Task 1) in Room #3 are updated to _Return a call to the patient._ and _12 Jan 2021 1500_ respectively.
* `edittask r/2 t/2 dd/12-1-2021` The due date for the second task (Task 2) of Room #2 is updated to _12 Jan 2021 0000_.

Expected Outcome: <br>
* Using the first example, the result box displays the message _Task 1 edited from Room 3. Description: Return a call to the patient. Due Date: 12 Jan 2021 1500_.
* The details panel for Room #3 shows the updated description and due date of Task 1.
* The list of tasks in Covigent shows the updated description and due date of Task 1.

_Written by: Yee Hong_


#### 5.4.4 Remove Due Date from a Task: `edittask`

Removes a due date from a task in a room.

Format: `edittask r/ROOM_NUMBER t/TASK_NUMBER dd/-`

Additional Information:
* Removes the due date from the task with the `TASK_NUMBER` in the room with the `ROOM_NUMBER`.
* Existing due date will be set to `-`.
* `ROOM_NUMBER` refers to the number displayed beside each room under the list of rooms.
E.g. For "Room #5", the `ROOM_NUMBER` is 5.
* A room with the `ROOM_NUMBER` must be present in Covigent.
* `TASK_NUMBER` refers to the number displayed beside each task in the details panel for rooms.
* This is only one feature of the `edittask` command. Refer to the full command [here](#543-edit-task-description-or-due-date-edittask).

Example(s):
* `edittask r/3 t/1 dd/-` The due date of the first task (Task 1) in Room #3 is removed, i.e. set to `-`.

Expected Outcome: <br>
* The result box displays the message _Task 1 edited from Room 3._ followed by the current description of Task 1 and _Due Date: -_.
* The details panel for Room #3 shows the updated description and due date of Task 1.
* The list of tasks in Covigent shows the updated description and due date of Task 1.

_Written by: Yee Hong_


#### 5.4.5 Search Tasks before a Given Date: `searchtask`

Searches all tasks before a date in Covigent.

Format: `searchtask dd/DUE_DATE`

Additional Information:
* Due date can be in the any of the following formats:
  * `YYYYMMDD` (e.g. 20210131).
  * `YYYYMMDD HHmm` (e.g. 20210131 2359).
  * `D/M/YYYY` (e.g. 31/1/2021 or 31/01/2021).
  * `D/M/YYYY HHmm` (e.g. 31/1/2021 2359 or 31/01/2021 2359).
  
* If the time is not given for a due date, it defaults to 0000 (12am).

Example(s):

* `searchtask dd/12/1/2021` Search all tasks before and including 12 January 2021.

Expected Outcome:<br>
* The result box displays the message, "Tasks before the due date found.".
* Tasks before and including 12 January 2021 can now be found in the list of tasks in Covigent

_Written by: Wai Lok_


### 5.5 View help: `help`

Shows a message explaining how to access the help page.

Format: `help`

Expected Outcome: <br>
* A new help window appears.


### 5.6 Exit Covigent: `exit`

Exits Covigent and closes it.

Format: `exit`

Expected Outcome:<br>
* Covigent exits and closes.

_Written by: MingDe_

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
**Edit Task Description or Due Date** | `edittask r/ROOM_NUMBER t/TASK_NUMBER [d/DESCRIPTION] [dd/DUE_DATE]` <br> e.g., edittask r/1 t/3 dd/12-1-2021 1500
**Remove Due Date from a Task** | `edittask r/ROOM_NUMBER t/TASK_NUMBER dd/-` <br> e.g., edittask r/1 t/3 dd/-
**Search Task** | `searchtask dd/DUE_DATE` <br> e.g., searchtask dd/12-1-2021
**Help** | `help`
**Exit** | `exit`

_Written by: Yun Qing_


--------------------------------------------------------------------------------------------------------------------

## 7. FAQ

**Q**: Why does the output sometimes appear red and sometimes black?<br>
**A**: If the command input is given in the wrong format, then the output is given in red. Also the correct format for the particular command is given.<br> 
        
        Example:<br>
        Invalid command format! 
        Please give the number of digits in numbers
        Example: addRooms 200

**Q**: Will data be stored in the system after closing the app?<br>
**A**: The data is stored in the hard disk and therefore would not be deleted even if you close the app

**Q**: How do I look at all the rooms and patient when only one is being displayed after commands such as `findroom`?<br>
**A**: Use the command `listroom` for rooms and `listpatient` for patients.

_Written by: Noorul Azlina_<br>
