Run with gradle clean build or gradle clean build test. Test cases pass.

Main Assumptions:

I noticed the seatHoldID was written as int, but I decided to go with UUID for seatHoldID

Best seat begins at the middle of the row in the front row. To emulate how real unassigned seats in a venue naturally fill up, the "best seats" bubble out from that middle point.

Expiration of hold delay set at 3000.

