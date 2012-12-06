JCC = javac
JFLAGS = -g -d bin/

CFILEPATH = src/c-code/
CLIBPATH = lib/
SRC = src/

GCC = g++
CFLAGS = -shared -fpic -o $(CLIBPATH)libReadStat.so -I/usr/java/include -I/usr/java/include/linux $(CFILEPATH)ReadStat.cpp 

# typing 'make' will invoke the first target entry in the makefile 

.SUFFIXES: .java .class


.java.class:
	$(JCC) $(JFLAGS) $*.java


CLASSES = \
	$(SRC)statistics/Statistics.java \
	$(SRC)statistics/Array.java \
	$(SRC)statistics/ReadStat.java \
	$(SRC)Main.java \

default: lib classes

lib: 
	$(GCC) $(CFLAGS)

classes: $(CLASSES:.java=.class)

# To start over from scratch, type 'make clean'.  
# Removes all .class files, so that the next make rebuilds them
#
clean: 
	$(RM) src/*.class src/statistics/*.class 
