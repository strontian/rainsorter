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
package strawn.evariant.rainsorter.data.qclcdstations;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
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
            int wban = Integer.parseInt(record.get("WBAN"));
            toReturn.add(new QCWeatherStationRecord(wban, latitude, longitude));
        }
        return toReturn;
    }
    
    public static Iterable<CSVRecord> getCSVRecords() throws FileNotFoundException, IOException {
        Reader in = new FileReader(QCWeatherStationFileInfo.LOCATION);
        return CSVFormat.newFormat(QCWeatherStationFileInfo.DELIMITER).withHeader().parse(in);
    }
}
