# <div align="center"> <u>Shape-Sketch</div></u>

## Another tool? What for?  

Shape-Sketch is a tool to enable easily (really, really easily) generating processing code consisting of simple shapes via an easy to canvas.

## How do I use Shape-Sketch?

To get going, simply paint! When the canvas looks nice, hit the "Convert to Code" button (the bottom-most button on the left Shapes toolbar) to insert a function at the end of your current sketch window. Include this function in, say, your [`setup()`](https://processing.org/reference/setup_.html) function (after calling [`size()`](https://processing.org/reference/setup_.html), of course), and... done! Easier than counting to 3 (well, almost...).

## Installation Instructions

### The Easy Way: Installing Shape-Sketch via the Contribution Manager
The easiest way to install Shape-Sketch is from within the PDE's Tools Manager:

* Go to `Tools > Add Tool...`
* Click on `Shape-Sketch`, and click on the `Install` button in the bottom pane

### The Hard Way: Manually Installing Shape-Sketch
To use Shape-Sketch from within the Processing IDE (like a normal tool), do the following:

* Create a folder named "tools" inside your Sketchbook folder (if not already present). The location of the sketchbook folder is shown in Processing's Preferences dialog.
* Download the latest release ([GitManager.zip](https://github.com/joelmoniz/Git-Manager/releases/tag/latest)) and extract it into the "tools" folder. Ensure that in the tools folder, GitManager folder has src, tools, etc. within it, and not another sub-folder named GitManager.  
* Start Processing  

## Instructions to Build Shape-Sketch
To build Shape-Sketch, do the following:

1. Clone Shape-Sketch into a location of your choice:  
Change the directory to where you would like to clone Shape-Sketch to, and the run the  following command in the Git Bash Terminal:   
`git clone https://github.com/joelmoniz/Shape-Sketch.git`  
Note: If you would like to contribute to Shape-Sketch, you'll have to fork it first, and clone your fork.  
2. Import the cloned project into Eclipse
3. Open the build.properties file in the resources folder, and change the property "sketchbook.location" to wherever your Processing sketchbook is located, as instructed in the file
4. Now, to show the Ant View, go to Window > Show View > Ant
5. Drag the resources/build.xml file onto the Ant view there
6. Press the 'Play' button, and voilà!