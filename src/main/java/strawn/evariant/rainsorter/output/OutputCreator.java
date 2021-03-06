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
import strawn.evariant.rainsorter.calculation.MetropolitanStatisticalArea;
import strawn.evariant.rainsorter.calculation.WeatherStation;
import strawn.evariant.rainsorter.tools.IOTools;

/**
 * Helper class for generating the output created by the program.
 */
public class OutputCreator {
    
    public static final String WETNESS_OUTPUT_LOCATION = "output/MSAs_By_Wetness.csv";
    public static final String QUINTILES_OUTPUT_LOCATION = "output/MSA_By_Quintiles.json";
    
    /**
     * Writes a CSV file of MSAs and their population wetness, in descending order.
     * 
     * @param msas a List of all MSAs, which should already be populated with data.
     * @throws IOException 
     */
    public static void writeWetnessCsv(List<MetropolitanStatisticalArea> msas) throws IOException {
        List<String> toWrite = new ArrayList();
        for(MetropolitanStatisticalArea msa : msas) {
            toWrite.add(new MSAWetness(msa.msaName, msa.getPopulationWetness()).getOutputString());
        }
        IOTools.writeList(WETNESS_OUTPUT_LOCATION, toWrite);
    }
    
    /**
     * Writes a JSON file that sorts MSAs into quintiles based upon their Population Wetness. Also includes 
     * latitude and longitude data so they can be plotted on a map
     * 
     * @param msas a List of all MSAs, which should already be populated with data.
     * @throws IOException 
     */
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
    
    private static int getQuintile(int index, int total) {
        double boundary = (double)total/5d;
        return 1 + (int)((double)index/boundary);
    }
    
}
