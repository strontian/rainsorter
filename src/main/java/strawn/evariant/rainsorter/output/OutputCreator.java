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
import strawn.evariant.rainsorter.engine.WeatherStation;
import strawn.evariant.rainsorter.tools.IOTools;

/**
 *
 * @author David Strawn
 */
public class OutputCreator {
    
    public static final String WETNESS_OUTPUT_LOCATION = "output/MSAs_By_Wetness.csv";
    public static final String QUINTILES_OUTPUT_LOCATION = "output/MSA_By_Quintiles.json";
    
    public static void writeWetnessCsv(List<MetropolitanStatisticalArea> msas) throws IOException {
        List<String> toWrite = new ArrayList();
        for(MetropolitanStatisticalArea msa : msas) {
            toWrite.add(new MSAWetness(msa.msaName, msa.getWetnessRating()).getOutputString());
        }
        IOTools.writeList(WETNESS_OUTPUT_LOCATION, toWrite);
    }
    
    public static void writeMSAQuintiles(List<MetropolitanStatisticalArea> msas) throws IOException {
        List<Object> toWrite = new ArrayList();
        for(int i = 0; i < msas.size(); i++) {
            MetropolitanStatisticalArea msa = msas.get(i);
            if(msa.stations.isEmpty()) {
                continue;
            }
            WeatherStation station = msa.stations.get(0);
            toWrite.add(new MSAWithQuintileAndCoordinates(station.latitude, station.longitude, msa.msaName, getQuintile(i, msas.size())));
        }
        IOTools.writeAsJSON(QUINTILES_OUTPUT_LOCATION, toWrite);
    }
    
    public static int getQuintile(int index, int total) {
        double boundary = (double)total/5d;
        return 1 + (int)((double)index/boundary);
    }
    
}
