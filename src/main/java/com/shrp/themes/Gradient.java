/*
    SHRP Theme Builder
    Copyright (C) 2020  epicX

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

package com.shrp.themes;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
/*
Gradient Class
Usage - to generate gradientData for png 
gradientData will store in 2D array.
gData[index][0] (alpha)
gData[index][1] (red)
gData[index][2] (green)
gData[index][3] (blue)
This class doesn't have any required class.
*/
class Gradient{
  String firstColor;
  String secondColor;
  int mode;
  int fAlpha,fRed,fGreen,fBlue;
  int sAlpha,sRed,sGreen,sBlue;
  int diffAlpha,diffRed,diffGreen,diffBlue;
  int dModeAlpha,dModeRed,dModeGreen,dModeBlue;
  float incAlpha,incRed,incGreen,incBlue;
  int imgHeight,imgWidth;
  BufferedImage img=null;
  int gData[][];
  Gradient(String firstColor,String secondColor,int mode){
    this.firstColor=firstColor;
    this.secondColor=secondColor;
    this.mode=mode;
  }
  void process(File x)throws IOException{
    loadImage(x);
    parseFirstColor();
    parseSecondColor();
    calculateDiff();
    calculateIncValue(mode);
    processGData(1);
  }
  void loadImage(File f)throws IOException{
    try{
      img=ImageIO.read(f);
      imgHeight=img.getHeight();
      imgWidth=img.getWidth();
    }catch(IOException e){
      System.out.println(e);
    }
  }
  void parseFirstColor(){
    if(firstColor.length()>8){
      fAlpha=Integer.parseInt(firstColor.substring(1,3),16);
      fRed=Integer.parseInt(firstColor.substring(3,5),16);
      fGreen=Integer.parseInt(firstColor.substring(5,7),16);
      fBlue=Integer.parseInt(firstColor.substring(7,9),16);
    }else{
      fAlpha=255;
      fRed=Integer.parseInt(firstColor.substring(1,3),16);
      fGreen=Integer.parseInt(firstColor.substring(3,5),16);
      fBlue=Integer.parseInt(firstColor.substring(5,7),16);
    }
  }
  void parseSecondColor(){
    if(secondColor.length()>8){
      sAlpha=Integer.parseInt(secondColor.substring(1,3),16);
      sRed=Integer.parseInt(secondColor.substring(3,5),16);
      sGreen=Integer.parseInt(secondColor.substring(5,7),16);
      sBlue=Integer.parseInt(secondColor.substring(7,9),16);
    }else{
      sAlpha=255;
      sRed=Integer.parseInt(secondColor.substring(1,3),16);
      sGreen=Integer.parseInt(secondColor.substring(3,5),16);
      sBlue=Integer.parseInt(secondColor.substring(5,7),16);
    }
  }
  void calculateDiff(){
    if(fAlpha>sAlpha){
      diffAlpha=fAlpha-sAlpha;
      dModeAlpha=1;
    }else{
      diffAlpha=sAlpha-fAlpha;
      dModeAlpha=0;
    }
    if(fRed>sRed){
      diffRed=fRed-sRed;
      dModeRed=1;
    }else{
      diffRed=sRed-fRed;
      dModeRed=0;
    }
    if(fGreen>sGreen){
      diffGreen=fGreen-sGreen;
      dModeGreen=1;
    }else{
      diffGreen=sGreen-fGreen;
      dModeGreen=0;
    }
    if(fBlue>sBlue){
      diffBlue=fBlue-sBlue;
      dModeBlue=1;
    }else{
      diffBlue=sBlue-fBlue;
      dModeBlue=0;
    }
  }
  void calculateIncValue(int incMode){
    if(incMode==1){
      incAlpha=processInc(diffAlpha,imgHeight,fAlpha,sAlpha);
      incRed=processInc(diffRed,imgHeight,fRed,sRed);
      incGreen=processInc(diffGreen,imgHeight,fGreen,sGreen);
      incBlue=processInc(diffBlue,imgHeight,fBlue,sBlue);
    }else if(incMode==2){
      incAlpha=processInc(diffAlpha,imgWidth,fAlpha,sAlpha);
      incRed=processInc(diffRed,imgWidth,fRed,sRed);
      incGreen=processInc(diffGreen,imgWidth,fGreen,sGreen);
      incBlue=processInc(diffBlue,imgWidth,fBlue,sBlue);
    }else if(incMode==3||incMode==4){
      incAlpha=processInc(diffAlpha,imgHeight+imgWidth,fAlpha,sAlpha);
      incRed=processInc(diffRed,imgHeight+imgWidth,fRed,sRed);
      incGreen=processInc(diffGreen,imgHeight+imgWidth,fGreen,sGreen);
      incBlue=processInc(diffBlue,imgHeight+imgWidth,fBlue,sBlue);
    }
  }
  float processInc(int diff,int pixelLength,int fval,int sval){
    float inc;
    if(diff==0){return 0;}
    inc=(float)diff/pixelLength;
    if(fval>sval){
      inc=inc*-1;
    }
    return inc;
  }
  void processGData()throws IOException{
    float tIncAlpha,tIncRed,tIncGreen,tIncBlue;
    tIncAlpha=fAlpha;
    tIncRed=fRed;
    tIncGreen=fGreen;
    tIncBlue=fBlue;
    gData=new int[imgWidth+1][4];
    int tmp=0;
    while(tmp<imgWidth){
      tIncAlpha+=incAlpha;
      fAlpha=(int)tIncAlpha;
      tIncRed+=incRed;
      fRed=(int)tIncRed;
      tIncGreen+=incGreen;
      fGreen=(int)tIncGreen;
      tIncBlue+=incBlue;
      fBlue=(int)tIncBlue;
      gData[tmp][0]=fAlpha;
      gData[tmp][1]=fRed;
      gData[tmp][2]=fGreen;
      gData[tmp][3]=fBlue;
      tmp++;
    }
  }
  void processGData(int dummy)throws IOException{
    float tIncAlpha,tIncRed,tIncGreen,tIncBlue;
    tIncAlpha=fAlpha;
    tIncRed=fRed;
    tIncGreen=fGreen;
    tIncBlue=fBlue;
    gData=new int[imgHeight+imgWidth+1][4];
    int tmp=0;
    while(tmp<imgHeight+imgWidth){
      tIncAlpha+=incAlpha;
      fAlpha=(int)tIncAlpha;
      tIncRed+=incRed;
      fRed=(int)tIncRed;
      tIncGreen+=incGreen;
      fGreen=(int)tIncGreen;
      tIncBlue+=incBlue;
      fBlue=(int)tIncBlue;
      gData[tmp][0]=fAlpha;
      gData[tmp][1]=fRed;
      gData[tmp][2]=fGreen;
      gData[tmp][3]=fBlue;
      tmp++;
    }
  }
}