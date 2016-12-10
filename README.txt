1. 010872316 (Nguyen, Nghi)
2. 010485683 (Chau, Tsz Sum) 
3. 010301928 (Leong, Alec)

**** Bug/Problem still exist:
	1. Dline resizing not working properly

Runnable jar file: Whiteboard.jar

Methods of Execution:
	1) Ubunto Terminal via jar file and src files
	2) Eclipse Java IDE via src files


See below for instructions on how to execute the homework  assignment using either of the two methods above.

Details: 
	(1) This homework assignment was executed with VMWare Workstation 12 Player with Ubunto 64-Bit terminal
		and Eclipse Java IDE. 
		Below are the commands to execute the homework assignment using either of the two methods above.
	(2) Section4_<insert student id>_Final.zip contains the following:
	        (i)  Whiteboard.jar 
				 Whiteboard.jar is an executable jar file for this homework assignment to be executed via terminal
	        (ii) Directory 'Section4_<insert student id>_Final' which contains:
		         (1) Directory 'Whiteboard Eclipse Java IDE' which contains:
		         	 (a) Directory '.settings'
		         	 (b) Directory 'bin' which contains the class files
		         	 (b) Directory 'src' which contains the Java files:
			         	 Canvas.java
			             DLine.java
			             DLineModel.java
			             DOval.java
			             DOvalModel.java
			             DRect.java
			             DRectModel.java
			             DShape.java
			             DShapeModel.java
			             DShapeModelTable.java
			             DText.java
			             DTextModel.java
			             ModelListener.java
			             WhiteBoard.java (compile and run this file via Eclipse Java IDE)
			         (c) File '.classpath'
			         (d) File '.project'
		             Directory 'Whiteboard Eclipse Java IDE' is a folder to be imported into Eclipse Java IDE to  execute the homework assignment via Eclipse Java IDE
		         (2) Directory 'Whiteboard Terminal' which contains:
		             (a) Canvas.java
		             (b) DLine.java
		             (c) DLineModel.java
		             (d) DOval.java
		             (e) DOvalModel.java
		             (f) DRect.java
		             (g) DRectModel.java
		             (h) DShape.java
		             (i) DShapeModel.java
		             (j) DShapeModelTable.java
		             (k) DText.java
		             (l) DTextModel.java
		             (m) ModelListener.java
		             (n) WhiteBoard.java (compile and run this file via terminal)
		             Directory 'Whiteboard Terminal' is a folder to execute the homework assignment via terminal
			   
*************************How to Run Homework 6 Whiteboard Executable Jar File via Terminal*************************

alec@alec-virtual-machine:/mnt/hgfs/Desktop$ ls
Section4_010872316_Final.zip
alec@alec-virtual-machine:/mnt/hgfs/Desktop$ unzip Section4_010872316_Final.zip
Archive:  Section4_010872316_Final.zip
  inflating: Section4_010872316_Final/client_save.png  
  inflating: Section4_010872316_Final/clientsa  
  inflating: Section4_010872316_Final/README.pdf  
  inflating: Section4_010872316_Final/README.txt  
  inflating: Section4_010872316_Final/servers  
   creating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/.classpath  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/.project  
   creating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/.settings/
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/.settings/org.eclipse.jdt.core.prefs  
   creating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/bin/
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/bin/Canvas$1.class  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/bin/Canvas$2.class  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/bin/Canvas.class  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/bin/DLine.class  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/bin/DLineModel.class  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/bin/DOval.class  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/bin/DOvalModel.class  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/bin/DRect.class  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/bin/DRectModel.class  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/bin/DShape.class  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/bin/DShapeModel.class  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/bin/DShapeModelTable.class  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/bin/DText$1.class  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/bin/DText$2.class  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/bin/DText.class  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/bin/DTextModel.class  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/bin/ModelListener.class  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/bin/WhiteBoard$1.class  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/bin/WhiteBoard$ClientHandler.class  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/bin/WhiteBoard$ServerAccepter.class  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/bin/WhiteBoard.class  
   creating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/src/
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/src/Canvas.java  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/src/DLine.java  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/src/DLineModel.java  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/src/DOval.java  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/src/DOvalModel.java  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/src/DRect.java  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/src/DRectModel.java  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/src/DShape.java  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/src/DShapeModel.java  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/src/DShapeModelTable.java  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/src/DText.java  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/src/DTextModel.java  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/src/ModelListener.java  
  inflating: Section4_010872316_Final/Whiteboard Eclipse Java IDE/src/WhiteBoard.java  
   creating: Section4_010872316_Final/Whiteboard Terminal/
  inflating: Section4_010872316_Final/Whiteboard Terminal/Canvas.java  
  inflating: Section4_010872316_Final/Whiteboard Terminal/DLine.java  
  inflating: Section4_010872316_Final/Whiteboard Terminal/DLineModel.java  
  inflating: Section4_010872316_Final/Whiteboard Terminal/DOval.java  
  inflating: Section4_010872316_Final/Whiteboard Terminal/DOvalModel.java  
  inflating: Section4_010872316_Final/Whiteboard Terminal/DRect.java  
  inflating: Section4_010872316_Final/Whiteboard Terminal/DRectModel.java  
  inflating: Section4_010872316_Final/Whiteboard Terminal/DShape.java  
  inflating: Section4_010872316_Final/Whiteboard Terminal/DShapeModel.java  
  inflating: Section4_010872316_Final/Whiteboard Terminal/DShapeModelTable.java  
  inflating: Section4_010872316_Final/Whiteboard Terminal/DText.java  
  inflating: Section4_010872316_Final/Whiteboard Terminal/DTextModel.java  
  inflating: Section4_010872316_Final/Whiteboard Terminal/ModelListener.java  
  inflating: Section4_010872316_Final/Whiteboard Terminal/WhiteBoard.java  
  inflating: Section4_010872316_Final/Whiteboard.jar  
  inflating: Section4_010872316_Final/whiteboard.mdj
alec@alec-virtual-machine:/mnt/hgfs/Desktop$ ls
Section4_010872316_Final
Section4_010872316_Final.zip
alec@alec-virtual-machine:/mnt/hgfs/Desktop$ cd Section4_010872316_Final/
alec@alec-virtual-machine:/mnt/hgfs/Desktop/Section4_010872316_Final$ ls
clientsa  client_save.png  README.pdf  README.txt  servers  Whiteboard Eclipse Java IDE  Whiteboard.jar  whiteboard.mdj  Whiteboard Terminal
alec@alec-virtual-machine:/mnt/hgfs/Desktop/Section4_010872316_Final$ java -jar Whiteboard.jar
normal

The application will now open and the Whiteboard begins
To exit or close the program either (1) hold 'Ctrl' and press 'C' on the terminal or (2) close the Java application

*************************How to Run Homework 6 Whiteboard Java Source Files via Terminal*************************

alec@alec-virtual-machine:/mnt/hgfs/Desktop/Section4_010872316_Final$ cd Whiteboard\ Terminal/
alec@alec-virtual-machine:/mnt/hgfs/Desktop/Section4_010872316_Final/Whiteboard Terminal$ ls
Canvas.java  DLineModel.java  DOvalModel.java  DRectModel.java  DShapeModel.java       DText.java       ModelListener.java
DLine.java   DOval.java       DRect.java       DShape.java      DShapeModelTable.java  DTextModel.java  WhiteBoard.java
alec@alec-virtual-machine:/mnt/hgfs/Desktop/Section4_010872316_Final/Whiteboard Terminal$ javac WhiteBoard.java 
alec@alec-virtual-machine:/mnt/hgfs/Desktop/Section4_010872316_Final/Whiteboard Terminal$ java WhiteBoard 
normal

The application will now open and the Whiteboard begins
To exit or close the program either (1) hold 'Ctrl' and press 'C' on the terminal or (2) close the Java application

***********************************How to Run Homework 6 Whiteboard via Eclipse***********************************

Prequisites:

(1) Open Eclipse (Java IDE)
(2) Go to the 'File' tab located on the upper left hand corner
(3) Go to 'Open Projects from File System...' 
	A window called 'Import Projects from File System or Archive' will appear
(4) Next to 'Import source:' go to the 'Directory...' tab and locate the directory 'Whiteboard Eclipse Java IDE'
    Once you have located the directory then select the 'OK' tab
(5) Then click 'Finish' from the 'Import Projects from File System or Archive' window
(6) A new folder called 'Whiteboard Eclipse Java IDE' will appear in 'Package Explorer' on the Eclipse IDE
(7) Under the folder 'Whiteboard Eclipse Java IDE' go to src->application->WhiteBoard.java
(8) Now double click on the file 'WhiteBoard.java' to open the file on Eclipse IDE
(9) Now go the green circular run/play tab located on the top of Eclipse IDE

The application will now open and the Whiteboard begins
To exit or close the program either (1) close Eclipse Java IDE or (2) close the Java application
