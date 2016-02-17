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
package strawn.evariant.rainsorter.data.qclcdstations;

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
public class QCWeatherStationLoader {
    
    public static List<QCWeatherStationRecord> loadRecordsFromDisk() throws FileNotFoundException, IOException {
        ArrayList<QCWeatherStationRecord> toReturn = new ArrayList();
        Iterable<CSVRecord> records = getCSVRecords();
        for (CSVRecord record : records) {
            double latitude = Double.parseDouble(record.get("Latitude"));
            double longitude = Double.parseDouble(record.get("Longitude"));
            String wbanString = record.get("WBAN");
            if(wbanString.length() > 0) {
                toReturn.add(new QCWeatherStationRecord(wbanString, latitude, longitude));
            }
        }
        return toReturn;
    }
    
    public static Iterable<CSVRecord> getCSVRecords() throws FileNotFoundException, IOException {
        Reader in = new FileReader(QCWeatherStationFileInfo.LOCATION);
        return CSVFormat.newFormat(QCWeatherStationFileInfo.DELIMITER).withHeader().parse(in);
    }
}
