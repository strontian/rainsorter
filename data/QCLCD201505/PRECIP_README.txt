Date Published:

May 2015

Source:

http://www.ncdc.noaa.gov/orders/qclcd/QCLCD201505.zip

Final File Name:

201505_daytime_precip.csv

Fields:

0. Wban
1. YearMonthDay
2. Hour
3. Precipitation
4. PrecipitationFlag

Notes:

Field 4, Precipitation will either be blank, 'T', or a 2 sigdig precision double representing the inches of rain
Field 5, PrecipitationFlag will be either blank or 'S' which means suspect.

Data Creation Notes:

1. Move hourly precipitation file, '201505precip.txt' from the zip folder
2. Run create_daytime_precip.sh to create 201505_daytime_precip.csv, which has only the precipitation between the hours of 7am and midnight 


