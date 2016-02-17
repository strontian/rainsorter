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
            identifier = identifier.replace(" Metro Area","");
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
