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
	http://crunchify.com/how-to-setupinstall-maven-classpath-variable-on-windows-7/ (This link is better for explaining how to set up Maven on Windows)
	


### Other

Dynamic DNS Account Setup:
	1. Sign up for a free dynamic DNS account here: https://www.noip.com/sign-up This will give you a free URL that will redirect to your public IP address. 
	2. Since your public IP is dynamic you'll need to install the DNS update client (https://www.noip.com/download). This will update your public IP whenever it renews so it can redirect to your custom URL. Some routers come preconfigured with DDNS updaters built in and you'll just have to login to your No-IP account. In that case you wouldn't need the update client. 
	3. You'll also need to set a static IP or create a DHCP reservation for whichever device you want to remote into. 
	4. From there you would forward the port you want to use (default 3389 for RDP and 22 for SSH) to that address.

RDP into Raspberry Pi (Windows):
	1. Run (win+r) -> 'mstsc' which will open the rdp client. 
	2. Type in username and password for aname.ddns.net


##Database setup
You must set up a localhost MySQL schema. You can do this using WAMP/LAMP/XAMP/MySQLWorkbench/etc.
Once you have your program set up, import the script on the google drive scripts page 'create and populate db.sql'.
Create a new schema. It MUST be called bsbuckne.
NOTE: Your login credentials must be set to username:root password:password (should be default). Port 3306
Import the script to create and populate the tables.
That's it.

Database URL localhost:3306/bsbuckne  localhost DB, port 3306, schema name bsbuckne
username: root
password: password

Queries for the User are written in the UserDaoImpl


##Install and configure Motion
from raspberry pi terminal execute the following commands:                      <br />
`sudo apt-get install vim   (optional if you like Vim editor over emacs/nano)`  <br />
`sudo apt-get update`                                                           <br />
`sudo apt-get upgrade`                                                          <br />
`sudo apt-get install motion`                                                   <br />
`mkdir /tmp/motion`                                                             <br />
`sudo raspi-config`                                                             <br />
Enable Pi Camera set to 'yes' (#5 on the list)

Open motion.conf file in text editor    <br />
`sudo vim /etc/motion/motion.conf  (or use emacs/nano instead of vim)`

make edits to motion.conf file where necessary:
* daemon on
* width 320
* height 240
* framerate 30            (video file framerate. Default is 1 and it's terrible)
* minimum_motion_frames 5
* max_movie_time 5       (sets max length of recorded clip to 10 seconds)
* output_pictures off     (if this is left on the directory where files are stored will be filled very quickly with images)
* target_dir /tmp/motion  (this is where our motion detection video clips will be stored)
* stream_port 8081        (doesn't have to be 8081 but you need to know the port for the device manager page)
* stream_maxrate 15       (default is like 1FPS which is extremely low and laggy. 15 is good quality)
* stream_localhost off
* webcontrol_localhost off                          <br />
save file

enable motion start daemon                          <br />
`sudo vim /etc/default/motion`                      <br />
change no to yes                                    <br />
save file                                           <br />

enable pi camera to use with motion:                <br />
`sudo modprobe bcm2835-v4l2`                        <br />
If you get a message saying this is not permission, your camera probably is not recognized. <br />
`sudo vcgencmd get_camera` to view cameras          <br />
if pi camera is not detected here use the following <br />
`sudo apt-get install lua-5.2`

set up pi cam to be used with Motion on boot:       <br />
`vim /etc/rc.local      (emacs/nano optional)`      <br />
add line to file:                                   <br />
`sudo modprobe bcm2835-v4l2`                        <br />
save file

##FTP setup on Pi
First, do a sudo apt-get install ncftp on the Raspberry Pi.

We'll be using a bash script to upload log videos via FTP.
You can create it using a CLI text editor such as vim/nano or you can do it in windows and transfer via sftp.

The script, watchdog.sh, is as follows: 												<br />
`#!/bin/bash`																			<br />
`ncftpput -R -v -u watchdog -p cit480 projectwatchdog.ddns.net /video /tmp/motion/*` 	<br />
`rm -fr /tmp/motion/*`																	<br />

/tmp/motion is our default recording location on the pi. If you've changed it you'll need to modify the script.
