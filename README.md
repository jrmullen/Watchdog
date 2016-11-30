# Watchdog
CSE/CIT480 Capstone course

### Tools and Configuration

   1. Install IntelliJ/Eclipse IDE latest version "https://www.jetbrains.com/student/".

   2. Install JDK (Java development Kit)and configure in environment variables.

   3. Optional: connect your IntelliJ with your GitHub account "https://www.jetbrains.com/help/idea/2016.2/registering-github-account-in-intellij-idea.html".

   4. Install Maven and configure in environment variable. Check your configure is success by running "mvn -version" command.

   5. Set up Maven in IntelliJ "http://www.tutorialspoint.com/maven/maven_intellij_idea.htm"

   6. Open IntelliJ/Eclipse and choose your workstation and import maven project from git repository.

   

### Important Urls

https://www.jetbrains.com/student/
https://eclipse.org/downloads/
https://maven.apache.org/download.cgi
http://www.tutorialspoint.com/maven/maven_intellij_idea.htm
https://www.jetbrains.com/help/idea/2016.2/registering-github-account-in-intellij-idea.html



### Tutorials

Spring: 
	https://spring.io/guides/gs/serving-web-content/
	http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#getting-started-first-application-code

Git: 
	http://rypress.com/tutorials/git/index
	
Setting Up Environmental Variables for Java and Maven:
	http://www.tutorialspoint.com/maven/maven_environment_setup.htm
	
	http://crunchify.com/how-to-setupinstall-maven-classpath-variable-on-windows-7/ 
	(This link is better for explaining how to set up Maven on Windows)
	

	
### Other

Dynamic DNS Account Setup:

	1. Sign up for a free dynamic DNS account here: https://www.noip.com/sign-up This will give you a free URL
	that will redirect to your public IP address. 
	
	2. Since your public IP is dynamic you'll need to install the DNS update client 
	https://www.noip.com/download). This will update your public IP whenever it renews so it can redirect to 
	your custom URL. Some routers come preconfigured with DDNS updaters built in and you'll just have to login
	to your No-IP account. In that case you wouldn't need the update client. 
	
	3. You'll also need to set a static IP or create a DHCP reservation for whichever device you want to remote
	into. 
	
	4. From there you would forward the port you want to use (default 3389 for RDP and 22 for SSH) to that 
	address.

RDP into Raspberry Pi (Windows):

	1. Run (win+r) -> 'mstsc' which will open the rdp client. 
	
	2. Type in username and password for aname.ddns.net

	

##Database setup

	1. You must set up a localhost MySQL schema. You can do this using WAMP/LAMP/XAMP/MySQLWorkbench/etc.

	2. Once you have your program set up, import the script on the google drive scripts page 
	'create and populate db.sql'.

	3. Create a new schema. It MUST be called bsbuckne.
	NOTE: Your login credentials must be set to username:root password:password (should be default). Port 3306

	4. Import the script to create and populate the tables.

Database URL localhost:3306/bsbuckne  localhost DB, port 3306, schema name bsbuckne
username: root
password: password

Queries for the User are written in the UserDaoImpl



##Install and configure Motion
	1. 	Install motion
		a) 	From raspberry pi terminal execute the following commands:
				`sudo apt-get install vim   (optional if you like Vim editor over emacs/nano)`
				`sudo apt-get update`
				`sudo apt-get upgrade`
				`sudo apt-get install motion`
				`mkdir /tmp/motion`
				`sudo raspi-config`
		b) 	Enable Pi Camera set to 'yes' (Step 3 on the Install and configure Motion list)

	2. 	Edit motion.conf
		a)	Open motion.conf file in text editor
			`sudo vim /etc/motion/motion.conf  (or use emacs/nano instead of vim)`
		b) 	make edits to motion.conf file where necessary:
				* daemon on
				* width 320
				* height 240
				* framerate 30            (video file framerate. Default is 1 and it's terrible)
				* minimum_motion_frames 5
				* max_movie_time 5       (sets max length of recorded clip to 10 seconds)
				* output_pictures off
					(if this is left on the directory where files are stored will be filled very quickly with images)
				* target_dir /tmp/motion  (this is where our motion detection video clips will be stored)
				* stream_port 8081        
					(doesn't have to be 8081 but you need to know the port for the device manager page)
				* stream_maxrate 15       (default is like 1FPS which is extremely low and laggy. 15 is good quality)
				* stream_localhost off
				* webcontrol_localhost off
		c) 	save file
		
		
	(Optional) Set up motion with multiple cameras off one Raspberry Pi
		a) 	create configuration files for cameras where the number of cameras connected to the 
			Raspberry Pi is the number of config file you create
			`sudo vim thread[CAMERA NUMBER HERE].conf`	(I.e. `sudo vim thread1.conf`)
		b)	add configuratiuon info unique to each camera to each config file
			I.e `videodevice /dev/video0
				 stream_port 8081`
		c)	Save file
		d)	set up motion.conf to use config files just created
			`sudo vim /etc/motion/motion.conf`					
		e)	uncomment camera config files for the number of cameras connected to the pi at the 
			bottom of the motion.conf file by changing:
			`;  thread /etc/motion/thread1.conf` to `thread /etc/motion/thread1.conf`

	3.	Enable motion start daemon
		a)	`sudo vim /etc/default/motion`
		b)	change no to yes
		c)	save file

	4.	Enable pi camera to use with motion
		a) 	`sudo modprobe bcm2835-v4l2`
		b) 	If you get a message saying this is not permission, your camera probably is not recognized.
			`sudo vcgencmd get_camera` to view cameras
		c) 	If pi camera is not detected here use the following:
			`sudo apt-get install lua5.2`

	5. 	Set up pi cam to be used with Motion on boot:
		a)	`vim /etc/rc.local      (emacs/nano optional)`
		b)	add line to file:
			`sudo modprobe bcm2835-v4l2`
		c)	save file

	Note: If motion does not start on boot (see Step 3 for starting motion on boot)
		Type the following every time the system is restarted to run motion:
		`sudo service motion start`

	
	
##FTP Setup on Pi
	1. First, do a `sudo apt-get install` ncftp on the Raspberry Pi.
	
	2. Install the files needed to be able to convert from .avi to .mp4 in script:
		`sudo apt-get install libavcodec-extra`
		`sudo apt-get install libav-tools`

	3. We'll be using a bash script to upload log videos via FTP. You can create it using a CLI text 
	editor such as vim/nano or you can do it in windows and transfer via sftp.

	4. The script, watchdog.sh, is as follows:
		`#!/bin/bash`
		`mac="$(cat /sys/class/net/eth0/address)"`
		`path="/tmp/motion/*.avi"`
		
		`for file in $path;`
			`do sudo mv "${file}" "${file%.avi}_$mac.avi";`
		`done;`

		`set -- *:*`
		`for file in $path; do`
			`sudo mv -- "$file" "${file//:/}"`
		`done`
		`ncftpput -R -v -u watchdog -p cit480 projectwatchdog.ddns.net /video /tmp/motion/*`
		`sudo rm -fr /tmp/motion/*`

	5. /tmp/motion is our default recording location on the pi. If you've changed it you'll need to 
	modify the script.
	
	Pick ONE of the following for running the script
	6. Option 1. edit motion.conf
		`sudo vim /etc/motion/motion.conf`
	remove comment for on_movie_end command and add script (this is w/ script in /tmp/ dir)
		`on_movie_end /tmp/script.sh %f`
		
	6. Option 2. Schedule script to run with crontab
		a. put script in directory on Raspberry Pi (/home/pi is recommended)

		b. Change permissions to allow script to be run
		(more restrictive permissions than this are recommended for security)  
		`sudo chmod 777 path/to/script/script.sh`

		c. Create crontab to schedule running the script
		 `crontab -u pi -e`

		d. Specify the schedule to run the script inside the crontab file in format:
		minute hour dayOfMonth month dayOfWeek command
		 I.e. run every minute:
		 * * * * * /home/pi/ftpupload_remote.sh`

		e. Save file
