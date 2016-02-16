package strawn.evariant;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import strawn.evariant.rainsorter.datatasks.WeatherStationTasks;
import strawn.evariant.rainsorter.data.weatherstations.WeatherStationLoader;
import strawn.evariant.rainsorter.data.weatherstations.WeatherStationRecord;

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
public class App {
    public static void main( String[] args ) throws IOException {
        WeatherStationTasks.printComments();
        /*
        List<WeatherStationRecord> wsrs = WeatherStationLoader.loadRecordsFromDisk();
        Gson gson = new Gson();
        for(WeatherStationRecord ws : wsrs) {
            System.out.println(gson.toJson(ws));
        }
        */
    }
}
