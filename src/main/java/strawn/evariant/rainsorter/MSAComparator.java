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
package strawn.evariant.rainsorter;

import java.util.Comparator;

/**
 *
 * @author davidstrawn
 */
public class MSAComparator implements Comparator<MetropolitanStatisticalArea> {

    public int compare(MetropolitanStatisticalArea o1, MetropolitanStatisticalArea o2) {
        double netWetness = o2.getWetnessRating() - o1.getWetnessRating();
        if(netWetness > 0) {
            return 1;
        }else if(netWetness < 0) {
            return -1;
        }
        return 0;
    }
    
}
