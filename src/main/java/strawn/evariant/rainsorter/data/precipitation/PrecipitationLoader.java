/*
 * Copyright 2016 davidstrawn.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
 *
 * @author davidstrawn
 */
public class PrecipitationLoader {
    
    public static List<PrecipitationRecord> loadRecordsFromDisk() throws FileNotFoundException, IOException, Exception {
        ArrayList<PrecipitationRecord> toReturn = new ArrayList();
        Iterable<CSVRecord> records = getCSVRecords();
        for (CSVRecord record : records) {
            int hour = Integer.parseInt(record.get("Hour"));
            int wban = Integer.parseInt(record.get("Wban"));
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
            if(noteString.isEmpty()) {
                
            }
            toReturn.add(new PrecipitationRecord(wban, hour, inchesOfRain, isTrace, isSuspect));
        }
        return toReturn;
    }
    
    public static Iterable<CSVRecord> getCSVRecords() throws FileNotFoundException, IOException {
        Reader in = new FileReader(PrecipitationFileInfo.LOCATION);
        return CSVFormat.DEFAULT.withHeader().parse(in);
    }
}
