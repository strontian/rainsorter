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
            String[] indentifierSplit = identifier.split(" ");
            String msaName = indentifierSplit[0].substring(0, indentifierSplit[0].length() - 1);
            String msaState = indentifierSplit[1];
            int population = Integer.parseInt(record.get("Population Estimate (as of July 1) - 2014"));
            int cbsaCode = Integer.parseInt(record.get("Id2"));
            toReturn.add(new MSAPopulationRecord(msaName, msaState, population, cbsaCode));
        }
        return toReturn;
    }
    
    public static Iterable<CSVRecord> getCSVRecords() throws FileNotFoundException, IOException {
        Reader in = new FileReader(MSAPopulationFileInfo.location);
        return CSVFormat.DEFAULT.withHeader().parse(in);
    }
    
}
