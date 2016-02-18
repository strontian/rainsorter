/**
 * Copyright (C) 2016  David Strawn
 * 
 * This file is part of rainsorter.
 *
 * rainsorter is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * rainsorter is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public License
 * along with rainsorter.  If not, see http://www.gnu.org/licenses.
 */
package strawn.evariant.rainsorter.tools;

import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author David Strawn
 */
public class IOTools {
    
    public static void writeList(String location, List<String> toWrite) throws IOException {
        //makeParentDirs(location);
        BufferedWriter bw = new BufferedWriter(new FileWriter(location));
        int i = 0;
        for(String s : toWrite) {
            if(i != 0) {
                bw.newLine();
            }
            i++;
            bw.write(s);
        }
        bw.close();
    }
    
    public static void writeAsJSON(String location, List<? extends Object> toWrite) throws IOException {
        //makeParentDirs(location);
        BufferedWriter bw = new BufferedWriter(new FileWriter(location));
        bw.write("[");
        boolean first = true;
        Gson g = new Gson();
        for(Object o : toWrite) {
            if(!first) {
                bw.write(',');
                bw.newLine();
            }else{
                first = false;
            }
            String s = g.toJson(o);
            bw.write(s);
        }
        bw.write("]");
        bw.close();
    }
    
}
