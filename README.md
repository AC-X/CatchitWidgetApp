# CatchitWidgetApp
Example of a Widget That When Pressed Takes You From The HomeScreen To An Activity with in the app

Documentation Is Stated With In The Widget's File.  This is to be set up and installed into the app's directories and manifest file.

For this widget to work you are REQUIRED the following:

Lines from the manifest.

JAVA
WidgetProvider

(The Below: Working but disabled in code.  Pulls data from a JSON file and displays in a ListView.  Future design to pull from the database calendar dates)
WidgetService (Required For Phase Two)
NextStep (Required For Phase Two)
Prospect (Required For Phase Two)
MeetingViewsFactory (Required For Phase Two)
Meetings (Required For Phase Two)

RES
DRAWABLE
catch_it_addpros.png (Button Image, can be replaced withan image of your choice.)
catch_it_evnts.png (Button Image, can be replaced withan image of your choice.)
catch_it_sched.png (Button Image, can be replaced withan image of your choice.)
cacth_it_views.png (Button Image, can be replaced withan image of your choice.)
preview.png (REQUIRED This is a small image preview of what the widget looks like)
widget_frame_9.png (REQUIRED Background of widget)

LAYOUT
widget.xml (REQUIRED)
row.xml (Phase 2)

MIPMAP
 ic_launcher.png (OR YOUR Personal icon)
 
 RAW
 (ALL FILES IF Testing Phase 2)
 
 XML
 widget_provider.xml  (REQUIRED, Sets up intiail size for widget)
 
 **Nothing is done to the Gradle

