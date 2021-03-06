Arduino is an open-source Physical Computing platform based on a simple
i/o board and a development environment that implements the Processing/Wiring
language.  Arduino can be used to develop stand-alone interactive objects or
can be connected to software on your computer (e.g. Flash, Processing, MaxMSP).

Arduino is an open source project, owned by nobody and supported by many.

For more information, see the website at: http://arduino.berlios.de/ or the
forums at http://arduino.berlios.de/cgi-bin/yabb/YaBB.cgi

The Team is composed of Massimo Banzi, David Cuartielles, Tom Igoe,
David A. Mellis and Nicholas Zambetti.

Gianluca Martino works with us on many projects providing help, ideas and
taking care of the production.

Yaniv Steiner and Giorgio Olivero have been supporting the project and are
working at using it with the Instant Soup platform.

Arduino uses the GNU avr-gcc toolchain, uisp, avr-libc, avrlib, and code
from Processing and Wiring.

UPDATES

0003

API Changes
Reversed the analog input pins to correspond to newer boards.  This means
a call, for example, to analogRead(0) must be changed to analogRead(5) in
order to read the same physical pin.
Added a printNewline() function (which sends '\n' = ASCII 10).

New Stuff
Reference is included (features native to C not yet documented).
Serial monitor added (click the toolbar button to turn it on or off).  Baud
rate is controlled by the Serial Monitor Baud Rate Menu, defaults to 9600.  Icon
and implementation from Wiring.
Serial port menu now automatically refreshes when opened.
New blue color scheme and icons courtesy of Noah Shibley (colors are hardcoded
into the source to ensure consistency with image files).
Keyspan and FTDI USB drivers included with Mac and Windows distributions.

Bug Fixes
millis() now updates every millisecond instead of every second.
Bootloader included with Windows distribution (it was already in the Mac dist).
Updated icon of the Windows executable.
Now flushing the serial port before uploading (should fix some errors).
Improved accuracy of the delayMicroseconds() function.

Other
Upload rate no longer selectable from a menu within the IDE.  Instead, edit the
serial.download_rate item in the preferences.txt file.
Created Xcode project for building Arduino on the Mac (doesn't yet regenerate
the grammar files or package the distribution); active target should be "App".
Removed unused or unimplemented items from menus.

0002 - 2005.10.05
New build process no longer uses makefiles; now controlled by preferences.txt.
core/ replaced with targets/; can now link against Wiring libraries.
Replaced print() with printString, printInteger, printHex, printByte, etc.
Added menu for selecting serial port speed.
Updated icon.
Bootloader shrunk to less than 1 KB; fuses updated accordingly.
Added serialRead(), serialAvailable(), and delayMicroseconds().

0001 - 2005.08.25 
This is the first released of the unified IDE + language library
it's a terrible hack... but it works. at the moment it's in alpha stage
but it can be used to work.
The processing preprocessor is included but not used.
