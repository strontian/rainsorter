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
package strawn.evariant.rainsorter.data.precipitation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * Helper class for loading QCLCD Precipitation data form disk.
 */
public class PrecipitationLoader {
    
    public static final String QCLCD_DISK_LOCATION = "data/QCLCD201505/201505_daytime_precip.csv";
    public static final char QCLCD_DELIMITER = ',';
    
    /**
     * Loads and returns all precipitation data for May 2015
     * 
     * @return The list of precipitation records
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static List<PrecipitationRecord> loadRecordsFromDisk() throws FileNotFoundException, IOException {
        ArrayList<PrecipitationRecord> toReturn = new ArrayList();
        Iterable<CSVRecord> records = getCSVRecords();
        for (CSVRecord record : records) {
            int hour = Integer.parseInt(record.get("Hour"));
            String wban = record.get("Wban");
            double inchesOfRain = 0d;
            boolean isTrace = false;
            boolean isSuspect = false;
            String precipitationString = record.get("Precipitation").trim();
            if(precipitationString.equals("T")) {
                isTrace = true;
            }else if(precipitationString.length() != 0){
                inchesOfRain = Double.parseDouble(record.get("Precipitation"));
            }
            String noteString = record.get("PrecipitationFlag").trim();
            if(noteString.equals("S")) {
                isSuspect = true;
            }
            toReturn.add(new PrecipitationRecord(wban, hour, inchesOfRain, isTrace, isSuspect));
        }
        return toReturn;
    }
    
    public static Iterable<CSVRecord> getCSVRecords() throws FileNotFoundException, IOException {
        Reader in = new FileReader(QCLCD_DISK_LOCATION);
        return CSVFormat.DEFAULT.withHeader().parse(in);
    }
}
