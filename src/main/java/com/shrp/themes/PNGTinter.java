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
PNGTinter Class
Usage - to apply Tint pngs
Required Class for gradient - Gradient
*/
class PngTinter{
  int alpha,red,green,blue;
  String inDir,outDir;
  PngTinter(String i,String j){inDir=i;outDir=j;}
  void applyTint(String color)throws IOException{//Normal Plain Tint
    parseColor(color);
    try{
      File folder=new File(inDir);
      File files[]=folder.listFiles();
      for(File file:files){
        if(isPNG(file)){
          tinter(file,outDir);
        }
      }
    }catch(IOException e){
      System.out.println(e);
    }
  }
  void applyTint(String color1,String color2,String color3,String color4,String color5,String color6)throws IOException{//Random Plain Tint
    try{
      File folder=new File(inDir);
      File files[]=folder.listFiles();
      for(File file:files){
        if(isPNG(file)){
          parseColor(Random.gRandom(color1, color2, color3, color4, color5, color6));
          tinter(file,outDir);
        }
      }
    }catch(IOException e){
      System.out.println(e);
    }
  }
  void applyTint(String color1,String color2,int gType)throws IOException{//Normal Gradient Tint
    try{
      File folder=new File(inDir);
      File files[]=folder.listFiles();
      for(File file:files){
        if(isPNG(file)){
          gradientControl(file,outDir,color1,color2,gType);
        }
      }
    }catch(IOException e){
      System.out.println(e);
    }
  }
  void applyTint(String color1,String color2,String color3,String color4,String color5,String color6,int gType)throws IOException{//Normal Gradient Tint
    String fColor,sColor;
    try{
      File folder=new File(inDir);
      File files[]=folder.listFiles();
      for(File file:files){
        if(isPNG(file)){
          fColor=Random.gRandom(color1,color2,color3,color4,color5,color6);
          sColor=Random.gRandomExecpt(color1,color2,color3,color4,color5,color6,getIndex(fColor,color1,color2,color3,color4,color5,color6));
          gradientControl(file,outDir,fColor,sColor,gType);
        }
      }
    }catch(IOException e){
      System.out.println(e);
    }
  }
  void gradientControl(File file,String outdir,String color1,String color2,int gType)throws IOException{
      switch(gType){
        case 1: LRGradientTinter(file,outDir,color1,color2,2);
        break;
        case 2: RLGradientTinter(file,outDir,color1,color2,1);
        break;
        case 3: TLBRGradientTinter(file,outDir,color1,color2,3);
        break;
        case 4: TRBLGradientTinter(file,outDir,color1,color2,4);
        break;
      }
  }
  void parseColor(String color){
    if(color.length()>8){
      alpha=Integer.parseInt(color.substring(1,3),16);
      red=Integer.parseInt(color.substring(3,5),16);
      green=Integer.parseInt(color.substring(5,7),16);
      blue=Integer.parseInt(color.substring(7,9),16);
      System.out.println(alpha+"  "+red+"  "+green+"  "+blue);
    }else{
      alpha=255;
      red=Integer.parseInt(color.substring(1,3),16);
      green=Integer.parseInt(color.substring(3,5),16);
      blue=Integer.parseInt(color.substring(5,7),16);
      System.out.println(alpha+"  "+red+"  "+green+"  "+blue);
    }
  }
  int getIndex(String str,String color1,String color2,String color3,String color4,String color5,String color6){
    if(str.equals(color1)){
        return 0;
    }else if(str.equals(color2)){
        return 1;
    }else if(str.equals(color3)){
        return 2;
    }else if(str.equals(color4)){
        return 3;
    }else if(str.equals(color5)){
        return 4;
    }else{
        return 5;
    }
  }
  boolean isPNG(File file){
    String targetPath=file.toString();
    String extension=targetPath.substring(targetPath.lastIndexOf(".")+1,targetPath.length());
    if(extension.equals("png")||extension.equals("PNG")){
      return true;
    }
    return false;
  }
  void tinter(File file,String outDir)throws IOException{
    int p,a,r,g,b;
    BufferedImage img=null;
    img=ImageIO.read(file);
    int width=img.getWidth();
    int height=img.getHeight();
    for(int i=0;i<width;i++){
      for(int j=0;j<height;j++){  
        p=img.getRGB(i, j);
        a = (p>>24) & alpha;
        r = (p>>16) & red;
        g = (p>>8) & green;
        b = p & blue;
        p = (a<<24) | (r<<16) | (g<<8) | b;
        img.setRGB(i, j, p);
      }
    }
    try{
      File f = new File(outDir+file.getName());
      ImageIO.write(img, "png", f);
    }catch(IOException e){
      System.out.println(e);
    }
  }
  void LRGradientTinter(File file,String outDir,String color1,String color2,int mode)throws IOException{
    BufferedImage img=null;
    img=ImageIO.read(file);
    Gradient G=new Gradient(color1,color2,mode);
    G.process(file);
    int p,a,r,g,b;
    int tmp=0;
    int alpha,red,green,blue;
    int width=img.getWidth();
    int height=img.getHeight();
    for(int i=0;i<width;i++){
      alpha=G.gData[tmp][0];
      red=G.gData[tmp][1];
      green=G.gData[tmp][2];
      blue=G.gData[tmp][3];
      for(int j=0;j<height;j++){   
        p=img.getRGB(i, j);
        a = (p>>24) & alpha;
        r = (p>>16) & red;
        g = (p>>8) & green;
        b = p & blue;
        p = (a<<24) | (r<<16) | (g<<8) | b;
        img.setRGB(i, j, p);
      }
      tmp++;
    }
    try{
      File f = new File(outDir+file.getName());
      ImageIO.write(img, "png", f);
    }catch(IOException e){
      System.out.println(e);
    }
  }
  void RLGradientTinter(File file,String outDir,String color1,String color2,int mode)throws IOException{
    BufferedImage img=null;
    img=ImageIO.read(file);
    Gradient G=new Gradient(color1,color2,mode);
    G.process(file);
    int p,a,r,g,b;
    int tmp=0;
    int alpha,red,green,blue;
    int width=img.getWidth();
    int height=img.getHeight();
    for(int i=0;i<height;i++){
      alpha=G.gData[tmp][0];
      red=G.gData[tmp][1];
      green=G.gData[tmp][2];
      blue=G.gData[tmp][3];
      for(int j=0;j<width;j++){   
        p=img.getRGB(j, i);
        a = (p>>24) & alpha;
        r = (p>>16) & red;
        g = (p>>8) & green;
        b = p & blue;
        p = (a<<24) | (r<<16) | (g<<8) | b;
        img.setRGB(j, i, p);
      }
      tmp++;
    }
    try{
      File f = new File(outDir+file.getName());
      ImageIO.write(img, "png", f);
    }catch(IOException e){
      System.out.println(e);
    }
  }
  void TLBRGradientTinter(File file,String outDir,String color1,String color2,int mode)throws IOException{
    BufferedImage img=null;
    img=ImageIO.read(file);
    Gradient G=new Gradient(color1,color2,mode);
    G.process(file);
    int p,a,r,g,b;
    int alpha,red,green,blue;
    int width=img.getWidth();
    int height=img.getHeight();
    //TOP Left to Buttom Right
    int h=height;
    int w=width;
    int j=0;
    while(h>=0){
      alpha=G.gData[h][0];
      red=G.gData[h][1];
      green=G.gData[h][2];
      blue=G.gData[h][3];
      for(int i=h-1;i>=0&&j<w;i--){
        //Set RGB
        p=img.getRGB(j, i);
        a = (p>>24) & alpha;
        r = (p>>16) & red;
        g = (p>>8) & green;
        b = p & blue;
        p = (a<<24) | (r<<16) | (g<<8) | b;
        img.setRGB(j, i, p);
        //Set RGB
        j++;
      }
      h--;
      j=0;
    }
    //Next Part
    int tmp=width;
    h=height;
    j=1;
    int extmp=height;
    w=1;
    while(w<=width){
      alpha=G.gData[extmp][0];
      red=G.gData[extmp][1];
      green=G.gData[extmp][2];
      blue=G.gData[extmp++][3];
      for(int i=h-1;i>=0&&j<tmp;i--){
        p=img.getRGB(j, i);
        a = (p>>24) & alpha;
        r = (p>>16) & red;
        g = (p>>8) & green;
        b = p & blue;
        p = (a<<24) | (r<<16) | (g<<8) | b;
        img.setRGB(j, i, p);
        j++;
      }
      w++;
      j=w;
    }
    try{
      File f = new File(outDir+file.getName());
      ImageIO.write(img, "png", f);
    }catch(IOException e){
      System.out.println(e);
    }
  }
  void TRBLGradientTinter(File file,String outDir,String color1,String color2,int mode)throws IOException{
    BufferedImage img=null;
    img=ImageIO.read(file);
    Gradient G=new Gradient(color1,color2,mode);
    G.process(file);
    int p,a,r,g,b;
    int alpha,red,green,blue;
    int width=img.getWidth();
    int height=img.getHeight();
    //TOP Right to Buttom Left
    int extmp;
    extmp=width;
    int h=0;
    int w=0;
    int j=0;
    while(h<=height){
      alpha=G.gData[extmp][0];
      red=G.gData[extmp][1];
      green=G.gData[extmp][2];
      blue=G.gData[extmp++][3];
      for(int i=h;i<height&&j<width;i++){
        p=img.getRGB(j, i);
        a = (p>>24) & alpha;
        r = (p>>16) & red;
        g = (p>>8) & green;
        b = p & blue;
        p = (a<<24) | (r<<16) | (g<<8) | b;
        img.setRGB(j, i, p);
        j++;
      }
      h++;
      j=0;
    }
    extmp=width-1;
    h=0;
    w=1;
    j=1;
    while(w<=width){
      alpha=G.gData[extmp][0];
      red=G.gData[extmp][1];
      green=G.gData[extmp][2];
      blue=G.gData[extmp--][3];
      for(int i=h;i<height&&j<width;i++){
        p=img.getRGB(j, i);
        a = (p>>24) & alpha;
        r = (p>>16) & red;
        g = (p>>8) & green;
        b = p & blue;
        p = (a<<24) | (r<<16) | (g<<8) | b;
        img.setRGB(j, i, p);
        j++;
      }
      w++;
      j=w;
    }
    try{
      File f = new File(outDir+file.getName());
      ImageIO.write(img, "png", f);
    }catch(IOException e){
      System.out.println(e);
    }
  }
}