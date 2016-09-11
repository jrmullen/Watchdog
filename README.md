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



