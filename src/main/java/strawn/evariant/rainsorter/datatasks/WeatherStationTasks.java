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
package strawn.evariant.rainsorter.datatasks;

import java.io.IOException;
import org.apache.commons.csv.CSVRecord;
import strawn.evariant.rainsorter.data.msaboundaries.MSABoundariesLoader;
import strawn.evariant.rainsorter.data.qclcdstations.QCWeatherStationLoader;
import strawn.evariant.rainsorter.data.weatherstations.WeatherStationLoader;

/**
 *
 * @author davidstrawn
 * 
 * Weather Station data includes a field for comments, which can have any sort of information in it.
 * Comments are highly asymmetrical, but at least one refers to a mislabeled WBAN
 * 
 * This function just prints all the comments so we can look for those that might be relevant.
 * 
 */
public class WeatherStationTasks {
    
    public static void printComments() throws IOException {
        Iterable<CSVRecord> records = WeatherStationLoader.getCSVRecords();
        for(CSVRecord record : records) {
            String comment = record.get("\"COMMENTS\"").replace("\"", "");
            if(!comment.equals("")) {
                System.out.println(comment);
            }
        }
    }
    
    public static void isPortlandStationCorrect() {
        MSABoundariesLoader.loadBoundaries();
    }
    
}
