#!/bin/sh


### -- SETUP WORK DIR -------------------------------------------

if test -d work
then
  BUILD_PREPROC=false
else
  echo Setting up directories to build arduino...
  BUILD_PREPROC=true
  cp -r ../shared work
  rm -rf work/CVS
  rm -f work/.DS_Store 
  #cp ../../lib/*.dll work
  cp dist/*.dll work
  cp dist/run.bat work
  chmod 755 work/run.bat
  cp ../../readme.txt work
  unix2dos work/readme.txt
  
  # needs to make the dir because of packaging goofiness
  mkdir -p work/classes/arduino/app/preproc
  mkdir -p work/classes/arduino/app/syntax
  mkdir -p work/classes/arduino/app/tools

  echo Extracting reference...
  cd work
  unzip reference.zip
  # necessary for launching reference from shell/command prompt
  # which is done internally to view reference
  #chmod +x reference/*.html
  # needed by 'help' menu
  #chmod +x reference/environment/*.html
  # chmod -R +x *.html doesn't seem to work
  rm reference.zip
  cd ..

  echo Extracting enormous JRE...
  unzip -q  -d work jre.zip
  # cygwin requires this because of unknown weirdness
  # it was not formerly this anal retentive
  cd work/java/bin/
  #chmod +x *.exe *.dll 
  #chmod +x client/*.dll
  cd ../../..

  mkdir work/lib/build
  #mkdir work/classes

  echo Compiling arduino.exe
  cd launcher
  make && cp arduino.exe ../work/
  cd ..

  # get jikes and depedencies
  cp dist/jikes.exe work/
  #chmod +x work/jikes.exe

  cp dist/ICE_JNIRegistry.dll work/
  mkdir work/bootloader
  cp dist/bootloader/*.* work/bootloader
  mkdir work/drivers
  cp -r dist/drivers/* work/drivers/
  mkdir work/tools
  cp dist/avr_tools.zip .
  echo Extracting avr tools ...
  unzip -q  -d work avr_tools.zip
  rm -f avr_tools.zip

  # core/ has been replaced by targets/ and we no longer use makefiles
  #cp dist/lib/makefile.win work/Makefile
  #mkdir work/core
  #cp ../../../core/*.* work/core
  #cp dist/core/makefile.win work/core/Makefile
  cp -r ../../targets work/lib/

  # take care of the examples
  cp -r ../shared/dist/examples work/
  #mkdir work/examples
  #cd work/examples
  #cp ../../../shared/dist/examples.zip .
  #echo Extracting examples ...
  #unzip -q  -d . examples.zip
  #rm -f examples.zip
  #cd ../..

  # chmod +x the crew
  find work -name "*.dll" -exec chmod +x {} ';'
  find work -name "*.exe" -exec chmod +x {} ';'
  find work -name "*.html" -exec chmod +x {} ';'
fi

cd ../..


### -- BUILD PREPROC ---------------------------------------------

# i suck at shell scripting
#if [ $1 = "preproc" ] 
#then 
#BUILD_PREPROC=true
#fi

if $BUILD_PREPROC
then

echo Building PDE for JDK 1.4

cd app/preproc

# first build the default java goop
../../build/windows/work/java/bin/java \
    -cp "..\\..\\build\\windows\\work\\lib\\antlr.jar" antlr.Tool StdCParser.g
# now build the pde stuff that extends the java classes
../../build/windows/work/java/bin/java \
    -cp "..\\..\\build\\windows\\work\\lib\\antlr.jar" antlr.Tool -glib StdCParser.g WParser.g
../../build/windows/work/java/bin/java \
    -cp "..\\..\\build\\windows\\work\\lib\\antlr.jar" antlr.Tool WTreeParser.g
../../build/windows/work/java/bin/java \
    -cp "..\\..\\build\\windows\\work\\lib\\antlr.jar" antlr.Tool -glib WTreeParser.g WEmitter.g

# back to base arduino dir
cd ../..

fi

### -- BUILD BOOTLOADER -----------------------------------------

cd bootloader
export DIRAVR=../build/windows/work/tools/avr
make 
cp ATmegaBOOT.hex ../build/windows/work/bootloader
cd ..

### -- BUILD PDE ------------------------------------------------

cd app

CLASSPATH="..\\build\\windows\\work\\lib\\RXTXcomm.jar;..\\build\\windows\\work\\lib\\mrj.jar;..\\build\\windows\\work\\lib\antlr.jar;..\\build\\windows\\work\\lib\\oro.jar;..\\build\\windows\\work\\lib\\registry.jar;..\\build\\windows\\work\\java\\lib\\rt.jar"

# compile the code as java 1.3, so that the application will run and
# show the user an error, rather than crapping out with some strange
# "class not found" crap
# need to do this twice because otherwise dependencies aren't resolved right.
../build/windows/work/jikes -target 1.3 +D -classpath "$CLASSPATH;..\\build\\windows\\work\\classes" -d ..\\build\\windows\\work\\classes preproc/*.java syntax/*.java tools/*.java *.java
../build/windows/work/jikes -target 1.3 +D -classpath "$CLASSPATH;..\\build\\windows\\work\\classes" -d ..\\build\\windows\\work\\classes preproc/*.java syntax/*.java tools/*.java *.java

cd ../build/windows/work/classes
rm -f ../lib/pde.jar
zip -0rq ../lib/pde.jar .

# back to build/windows
cd ../..



echo
echo Done.

