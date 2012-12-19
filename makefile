JCC = javac
JCH = javah

CLASSDIR = bin
SOURCEPATH = src

JFLAGS = -g
JHFLAGS = -jni

CFILEPATH = src/c-code/
CLIBPATH = lib/
SRC = src/

GCC = g++
CFLAGS = -shared -fpic -o $(CLIBPATH)libReadStat.so -I/usr/java/include -I/usr/java/include/linux $(CFILEPATH)ReadStat.cpp 

# typing 'make' will invoke the first target entry in the makefile 


default: lib javaclasses

javaclasses:
	$(JCC) $(JFLAGS) -d $(CLASSDIR) -sourcepath $(SOURCEPATH) src/com/Main.java

javah:	
	$(JCH) $(JHFLAGS) -d src/c-code/ -classpath $(CLASSDIR) com.statistics.ReadStat
lib: 
	$(GCC) $(CFLAGS)

