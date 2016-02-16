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
package strawn.evariant.rainsorter.data.weatherstations;

/**
 *
 * @author davidstrawn
 */
public class WeatherStationRecord {
    
    public int wbanId;
    public String county;
    public String state;
    
    public WeatherStationRecord(int wbanId, String county, String state) {
        this.wbanId = wbanId;
        this.county = county;
        this.state = state;
    }
    
}
