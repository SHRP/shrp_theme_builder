#!/bin/bash
dir="$(pwd)"
cd out
zip -r theme.zip *
mv theme.zip $dir/theme.stheme

#7za a $dir/t.zip *
#7za a theme.zip ./out/*
#mv theme.zip theme.stheme
