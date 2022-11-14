# CSE 360 Final Project Repository
Final project repository for CSE 360 Software Engineering at ASU, where we develop a desktop pizza application using modern software engineering methodology.

## Team Members
| Member Name  | Role |
| ------------- | ------------- |
| Jonathan Cranmer | Team Leader and Software Engineer |
| Hien Hong | Software Engineer |
| Eron Ristich | Software Engineer |
| Martin Pacheco | Software Engineer |
| Armando Gonzalez | Software Engineer |
| Ryan Rajesh | Software Engineer |

## Installation Instructions
Setting up this project requires a few additional steps in order to ensure a proper runtime environment. Namely, this program is written in [Java](https://www.oracle.com/java/) and built in [Maven](https://maven.apache.org/). Proper installation of these two is necessary prior to compiling and running this project.

### Install JDK
This project is built to support JDK's after and including JDK 11.
1. Download a JDK
   1. [JDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
   2. [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
2. During installation, select "Add to `PATH`" and "Setup `JAVA_HOME` Environment Variable" or their equivalents
3. After installation, verify that the destination folder has been added to the `PATH` environment variable, or is in the `JAVA_HOME` environment variable. If not, add the JDK folder to either of those variables. This can be verified in a command prompt by executing `echo %JAVA_HOME%`. Alternatively, execute `echo %PATH%`. If your installation location is displayed, then your JDK has been successfully installed.

Detailed instructions can be found [here](https://docs.oracle.com/en/java/javase/18/install/installation-jdk-microsoft-windows-platforms.html#GUID-A7E27B90-A28D-4237-9383-A58B416071CA). Specific instructions for different operating systems can be found in the table of contents on that page.

### Install Maven
This project in particular is built using Apache Maven 3.8.6, and it is strongly recommended that this version of Maven is installed to ensure accurate compilation. Additionally, it is recommended to have a JDK installation on your device (see above) prior to installation, although that is unnecessary.
1. [Download Maven](https://maven.apache.org/download.cgi) and extract the contents of the archive
2. Add the Apache Maven bin folder to the `PATH` environment variable. This can be verified in a command prompt by executing `mvn -v`. If Maven version information is displayed, then your Maven installation has been successfully installed.

Detailed instructions can be found [here](https://maven.apache.org/install.html)

### Eclipse
Assuming a stable Eclipse installation, setting up this project is fairly straightforward. Before setting up the project, verify your JDK and Maven installation.
1. Select "Open Projects from File System..." under the "File" tab, and open the pizza folder directly (as opposed to the repository folder)
2. Under the "Run as" dropdown menu under the run app dropdown menu, select "Maven build...", and input `javafx:run` as a build goal
3. Apply changes, and run the build
4. If Maven options are not displayed under the "Run as" dropdown menu, verify your Maven installation and corresponding `PATH` variable and then restart Eclipse
5. If errors occur when `javafx:run` is invoked, select under the "Run as" dropdown menu "Maven test" and then "Maven clean" and then retry step 2

### Visual Studio Code
Assuming a stable Visual Studio Code installation, setting up this project is fairly straightforward. Before setting up the project, verify your JDK and Maven installation.
1. Open the repository in Visual Studio Code
2. Under the extensions tab, install the [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack), or from the provided link
3. In the bottom left in the explorer tab, there should be a new Maven dropdown menu. If not, under the extensions tab, verify that Maven for Java has also been installed. Restarting Visual Studio Code may also be necessary.
4. Under the Maven dropdown menu, select the pizza project, then Plugins, then javafx, then javafx:run
5. If the project does not run, try selecting under the pizza project, then Lifecycle, then test. Follow relevant pop up information that Visual Studio Code provides to resolve the issue. 
