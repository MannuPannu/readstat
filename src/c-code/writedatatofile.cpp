#include "com_control_ReadStat.h"

#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <sys/mman.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <list>
#include <string>
#include <dirent.h> 

#include <fstream>

#include "polystatistics.h"

using namespace std;

char* getPolyDir();
list <string> getProcessFiles(char *dir);
int writeDataToFile(list<polystatistics>, list<string>);

/* Program that reads the memory files in the .polyml directory
   and outputs all the data to the terminal */

JNIEXPORT void JNICALL Java_com_control_ReadStat_writedatatofile (JNIEnv *env, jobject obj)
{
  int remMapFd = -1;
  char remMapFileName[100];
  int pageSize = getpagesize();
  size_t memSize = (sizeof(polystatistics) + pageSize-1) & ~(pageSize-1);

  polystatistics *polyS;

  //Get the file names for each poly/ML process that are running
  list <string> processNames = getProcessFiles(getPolyDir());
  
  //List to store the polystatistics structs
  list <polystatistics> processData;
  
  list<string>::iterator it;
  it = processNames.begin();
  int numberOfFiles = (int)processNames.size();

  while(it != processNames.end())
    {
      string fileName = *it;
      char *fileNamePtr;
      char *mapFileName;
      fileNamePtr = new char [fileName.size()+1];
      strcpy(fileNamePtr, fileName.c_str());

      mapFileName = (char*)malloc(strlen(getPolyDir() + 100));
      sprintf (mapFileName, "%s%s", getPolyDir(), fileNamePtr);

      remMapFd = open(mapFileName, O_RDONLY);

      polyS = (polystatistics*)mmap(0, //adress
				    memSize, // Length to map
				    PROT_READ, //protect
				    MAP_PRIVATE, //flags
				    remMapFd, //filedest
				    0); // offset
      if (polyS == MAP_FAILED)
	{
	  close(remMapFd);
	  printf("Map failed\n");
	}

      //Add the polystatitics struct to the list
      processData.push_front(*polyS);
      it++;
    }

    //Close the mapping
    munmap(polyS, memSize);
    close(remMapFd);

    //Write statistics data to file
    writeDataToFile(processData, processNames);

    if (env->ExceptionOccurred()) {
      env->ExceptionDescribe();
    }
}

list <string> getProcessFiles(char *dir)
{
  DIR *dirPtr;
  list <string> processNames;
  struct dirent *entryPtr;

  if((dirPtr = opendir(dir)) == NULL)
    {
      printf("Cannot open directory\n");
    }
  else
    {
      while (entryPtr = readdir (dirPtr))  //Read each entry in the directory
	{
	  char *entryName = entryPtr->d_name;
	  char firstC = entryName[0];
	  
	  /* Store only file names that begins with p. This to ignore the .. and
	     . entries in unix folders */
	  
	  if(firstC =='p') 
	    {
	      processNames.push_front(entryPtr->d_name);
	    } 
	}

      (void) closedir (dirPtr);
    } 

  return processNames;
}
/* Returns the directory of PolyMl statistics data
 */

char* getPolyDir()
{
  char *homeDir = getenv("HOME");
  char *polyFolder = "/.polyml/";

  if(homeDir == NULL)
    {
      printf("No home directory available\n");
    }

  char *polyDir = (char*)malloc(strlen(homeDir) + strlen(polyFolder) + 1);

  sprintf(polyDir, "%s%s", homeDir, "/.polyml/");

  return polyDir;
}

//Write statistics data to a file
int writeDataToFile(list<polystatistics> processData, list<string> processNames)
{

  list<string>::iterator nameIt;
  nameIt = processNames.begin();

  list<polystatistics>::iterator dataIt;
  dataIt = processData.begin();

  ofstream file;
  file.open ("statistics.txt");

  for(; dataIt != processData.end(); dataIt++, nameIt++)
    {
      polystatistics* polyS;
      polyS = &(*dataIt);

      string fileName = *nameIt;
      
      file << fileName << endl;

      for(int j = 0; j < N_PS_COUNTERS; j++)
	{
	  file << polyS->psCounters[j] << endl;
	}

      for(int j = 0; j < N_PS_SIZES; j++)
	{
	  file << polyS->psSizes[j] << endl;
	}

      for(int j = 0; j < N_PS_TIMES; j++)
	{
	  file << polyS->psTimers[j] << endl;
	}

    } 
  
  file.close();

  return 0;
}
