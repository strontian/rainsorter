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
package strawn.evariant.rainsorter.output;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import strawn.evariant.rainsorter.engine.MetropolitanStatisticalArea;
import strawn.evariant.rainsorter.tools.IOTools;

/**
 *
 * @author davidstrawn
 */
public class OutputCreator {
    
    public static String outputLocation = "output/MSAs_By_Wetness.csv";
    
    public static void writeWetnessCsv(List<MetropolitanStatisticalArea> msas) throws IOException {
        List<String> toWrite = new ArrayList();
        for(MetropolitanStatisticalArea msa : msas) {
            toWrite.add(new MSAWetness(msa.msaName, msa.getWetnessRating()).getOutputString());
        }
        IOTools.writeList(outputLocation, toWrite);
    }
    
}