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

/*
Random Class
Usage - to choose one or two color from multiple colors
This class doesn't have any required class.
*/
public class Random {
    static String gRandom(String i,String j,String k,String l,String m,String n){
        String strs[]=new String[6];
        strs[0]=i;
        strs[1]=j;
        strs[2]=k;
        strs[3]=l;
        strs[4]=m;
        strs[5]=n;
        int rndm=(int) (Math.random()*6);
        return strs[rndm];
    }
    static String gRandomExecpt(String i,String j,String k,String l,String m,String n,int exclude){
        String strs[]=new String[6];
        strs[0]=i;
        strs[1]=j;
        strs[2]=k;
        strs[3]=l;
        strs[4]=m;
        strs[5]=n;
        int rndm=(int) (Math.random()*6);
        while(rndm==exclude){
            rndm=(int) (Math.random()*6);
        }
        return strs[rndm];
    }
    static int gRandom(int value){
        return ((int) (Math.random()*value));
    }
}

