#!/bin/bash
#Deleting out dir
if [ -d "out/" ]
then
	rm -rf out
fi
#Deleting theme
if [ -f "theme.stheme" ]
then
	rm -rf theme.stheme
fi
#Deleting theme.zip if accidentally created
if [ -f "theme.zip" ]
then
	rm -rf theme.zip
fi