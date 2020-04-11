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
  int dashType,navType,gradientType,rMode,rDMode;
  String primaryAccentColor;
  //Gradient + Random
  Operation(String themeName,String backgroundColor,String accColor1,String accColor2,String textColor,String stextColor,String dIcoColor1,String dIcoColor2,String dIcoColor3,String dIcoColor4,String dIcoColor5,String dIcoColor6,int dashType,int navType,int gradientType,int rMode,int rDMode,int pAccColor){
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
      this.rDMode=rDMode;
      switch (pAccColor) {
          case 1:
              primaryAccentColor=accColor1;
              break;
          case 2:
              primaryAccentColor=accColor2;
              break;
          default:
              primaryAccentColor=getAvgColor(accColor1,accColor2);
              break;
      }
  }
  //Gradient only
  Operation(String themeName,String backgroundColor,String accColor1,String accColor2,String textColor,String stextColor,String dIcoColor1,String dIcoColor2,int dashType,int navType,int gradientType,int rMode,int pAccColor){
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
      switch (pAccColor) {
          case 1:
              primaryAccentColor=accColor1;
              break;
          case 2:
              primaryAccentColor=accColor2;
              break;
          default:
              primaryAccentColor=getAvgColor(accColor1,accColor2);
              break;
      }
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
      primaryAccentColor=accColor1;
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
      primaryAccentColor=accColor1;
  }

    Operation(String text, String text0, String text1, String text2, String text3, String text4, String text5, String text6, String text7, String text8, String text9, String text10, int dType, int nType, int gType, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    mData.write("themeName="+themeName+"\nbgColor="+backgroundColor+"\naccColor="+primaryAccentColor+"\ntextColor="+textColor+"\nsTextColor="+stextColor);
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
      case 4:
        p=new PngTinter("files\\dIco\\dt4\\","out\\res\\");
        break;
    }
    if(rMode==1&&gradientType!=0){
        p.applyTint(dIcoColor1,dIcoColor2,dIcoColor3,dIcoColor4,dIcoColor5,dIcoColor6,gradientType,rDMode);
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
  static String getAvgColor(String c1,String c2){
    int r,g,b;
    int r2,g2,b2;
    if(c1.length()>8){
      r=Integer.parseInt(c1.substring(3,5),16);
      g=Integer.parseInt(c1.substring(5,7),16);
      b=Integer.parseInt(c1.substring(7,9),16);
    }else{
      r=Integer.parseInt(c1.substring(1,3),16);
      g=Integer.parseInt(c1.substring(3,5),16);
      b=Integer.parseInt(c1.substring(5,7),16);
    }
    if(c1.length()>8){
      r2=Integer.parseInt(c2.substring(3,5),16);
      g2=Integer.parseInt(c2.substring(5,7),16);
      b2=Integer.parseInt(c2.substring(7,9),16);
    }else{
      r2=Integer.parseInt(c2.substring(1,3),16);
      g2=Integer.parseInt(c2.substring(3,5),16);
      b2=Integer.parseInt(c2.substring(5,7),16);
    }
    r=(r+r2)/2;
    g=(g+g2)/2;
    b=(b+b2)/2;
    return ("#"+Integer.toHexString(r)+Integer.toHexString(g)+Integer.toHexString(b));
  }
}


