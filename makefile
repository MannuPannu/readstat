JCC = javac
JCH = javah

CLASSDIR = bin
SOURCEPATH = src

JFLAGS = -g -Xlint:unchecked
JHFLAGS = -jni

CFILEPATH = src/c-code/
CLIBPATH = lib/
SRC = src/

GCC = g++
CSHARED = -shared -fpic -o $(CLIBPATH)libReadStat.so -I/usr/java/include -I/usr/java/include/linux $(CFILEPATH)writedatatofile.cpp 

# typing 'make' will invoke the first target entry in the makefile 


default: javaclasses lib

javaclasses:
	$(JCC) $(JFLAGS) -d $(CLASSDIR) -sourcepath $(SOURCEPATH) src/com/Main.java

javah:	
	$(JCH) $(JHFLAGS) -d src/c-code/ -classpath $(CLASSDIR) com.control.ReadStat
.PHONY : lib

#Compiles the c++ shared library
lib:
	$(GCC) $(CSHARED)


