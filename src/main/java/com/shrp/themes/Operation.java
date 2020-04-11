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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/*
Operation Class
Usage - to Execute the whole process.
Required class for execution - PNGTinter
*/
public class Operation{
  String themeName,accColor1,accColor2,backgroundColor,textColor,stextColor,dIcoColor1,dIcoColor2,dIcoColor3,dIcoColor4,dIcoColor5,dIcoColor6;
  int dashType,navType,gradientType,rMode;
  //Gradient + Random
  Operation(String themeName,String backgroundColor,String accColor1,String accColor2,String textColor,String stextColor,String dIcoColor1,String dIcoColor2,String dIcoColor3,String dIcoColor4,String dIcoColor5,String dIcoColor6,int dashType,int navType,int gradientType,int rMode){
      this.themeName=themeName;
      this.backgroundColor=backgroundColor;
      this.accColor1=accColor1;
      this.accColor2=accColor2;
      this.textColor=textColor;
      this.stextColor=stextColor;
      this.dIcoColor1=dIcoColor1;
      this.dIcoColor2=dIcoColor2;
      this.dIcoColor3=dIcoColor3;
      this.dIcoColor4=dIcoColor4;
      this.dIcoColor5=dIcoColor5;
      this.dIcoColor6=dIcoColor6;
      this.dashType=dashType;
      this.navType=navType;
      this.gradientType=gradientType;
      this.rMode=rMode;
  }
  //Gradient only
  Operation(String themeName,String backgroundColor,String accColor1,String accColor2,String textColor,String stextColor,String dIcoColor1,String dIcoColor2,int dashType,int navType,int gradientType,int rMode){
      this.themeName=themeName;
      this.backgroundColor=backgroundColor;
      this.accColor1=accColor1;
      this.accColor2=accColor2;
      this.textColor=textColor;
      this.stextColor=stextColor;
      this.dIcoColor1=dIcoColor1;
      this.dIcoColor2=dIcoColor2;
      this.dashType=dashType;
      this.navType=navType;
      this.gradientType=gradientType;
      this.rMode=rMode;
  }
  
  //Plain + Random
  Operation(String themeName,String backgroundColor,String accColor1,String textColor,String stextColor,String dIcoColor1,String dIcoColor2,String dIcoColor3,String dIcoColor4,String dIcoColor5,String dIcoColor6,int dashType,int navType,int rMode){
      this.themeName=themeName;
      this.backgroundColor=backgroundColor;
      this.accColor1=accColor1;
      this.textColor=textColor;
      this.stextColor=stextColor;
      this.dIcoColor1=dIcoColor1;
      this.dIcoColor2=dIcoColor2;
      this.dIcoColor3=dIcoColor3;
      this.dIcoColor4=dIcoColor4;
      this.dIcoColor5=dIcoColor5;
      this.dIcoColor6=dIcoColor6;
      this.dashType=dashType;
      this.navType=navType;
      this.rMode=rMode;
  }
  
  //Plain only
  Operation(String themeName,String backgroundColor,String accColor1,String textColor,String stextColor,String dIcoColor1,int dashType,int navType,int rMode){
      this.themeName=themeName;
      this.backgroundColor=backgroundColor;
      this.accColor1=accColor1;
      this.textColor=textColor;
      this.stextColor=stextColor;
      this.dIcoColor1=dIcoColor1;
      this.dashType=dashType;
      this.navType=navType;
      this.rMode=rMode;
  }
  boolean genarateResource()throws IOException{
      buildEnv();
      patchStockFile();
      finalTask();
      return true;
  }
  void finalTask() throws IOException{
      Runtime run = Runtime.getRuntime();
      run.exec("x.exe");
      run.exec("final.cmd");
  }
  void buildEnv()throws IOException{//Creates Build Env
    File dir=new File("out");
    dir.mkdir();
    dir=new File("out\\res\\");
    dir.mkdir();
    FileWriter mData = new FileWriter("out\\st.prop");
    mData.write("themeName="+themeName+"\nbgColor="+backgroundColor+"\naccColor="+accColor1+"\ntextColor="+textColor+"\nsTextColor="+stextColor);
    mData.flush();
    mData.close();
  }
  void patchStockFile()throws IOException{
    PngTinter p=new PngTinter("files\\accRes\\","out\\res\\");
    if(gradientType!=0){
        p.applyTint(accColor1,accColor2,gradientType);
    }else{
        p.applyTint(accColor1);
    }
    p=new PngTinter("files\\bgRes\\","out\\res\\");
    p.applyTint(backgroundColor);
    switch(dashType){
      case 1:
        p=new PngTinter("files\\dIco\\dt1\\","out\\res\\");
        break;
      case 2:
        p=new PngTinter("files\\dIco\\dt2\\","out\\res\\");
        break;
      case 3:
        p=new PngTinter("files\\dIco\\dt3\\","out\\res\\");
        break;
    }
    if(rMode==1&&gradientType!=0){
        p.applyTint(dIcoColor1,dIcoColor2,dIcoColor3,dIcoColor4,dIcoColor5,dIcoColor6,gradientType);
    }else if(rMode==1&&gradientType==0){
        p.applyTint(dIcoColor1,dIcoColor2,dIcoColor3,dIcoColor4,dIcoColor5,dIcoColor6);
    }else if(rMode==0&&gradientType!=0){
        p.applyTint(dIcoColor1,dIcoColor2,gradientType);
    }else{
        p.applyTint(dIcoColor1);
    }
    switch(navType){
      case 1:
        p=new PngTinter("files\\nIco\\nt1\\","out\\res\\");
        break;
      case 2:
        p=new PngTinter("files\\nIco\\nt2\\","out\\res\\");
        break;
      case 3:
        p=new PngTinter("files\\nIco\\nt3\\","out\\res\\");
        break;
      case 4:
        p=new PngTinter("files\\nIco\\nt4\\","out\\res\\");
        break;
    }
    if(gradientType!=0){
        p.applyTint(accColor1,accColor2,gradientType);
    }else{
        p.applyTint(accColor1);
    }
  }
}


