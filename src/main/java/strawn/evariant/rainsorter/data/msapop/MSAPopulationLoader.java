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
package strawn.evariant.rainsorter.data.msapop;

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
public class MSAPopulationLoader {
    
    public static List<MSAPopulationRecord> loadRecordsFromDisk() throws FileNotFoundException, IOException {
        ArrayList<MSAPopulationRecord> toReturn = new ArrayList();
        Iterable<CSVRecord> records = getCSVRecords();
        for (CSVRecord record : records) {
            String identifier = record.get("Geography");
            identifier.replace(" Metro Area","");
            String[] indentifierSplit = identifier.split(",");
            String msaName = indentifierSplit[0].trim();
            String msaState = indentifierSplit[1].trim();
            int population = Integer.parseInt(record.get("Population Estimate (as of July 1) - 2014"));
            toReturn.add(new MSAPopulationRecord(msaName, record.get("Id2"), population));
        }
        return toReturn;
    }
    
    public static Iterable<CSVRecord> getCSVRecords() throws FileNotFoundException, IOException {
        Reader in = new FileReader(MSAPopulationFileInfo.LOCATION);
        return CSVFormat.DEFAULT.withHeader().parse(in);
    }
    
}
