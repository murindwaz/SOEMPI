/**
 * 
 *  Copyright (C) 2013 Vanderbilt University <csaba.toth, b.malin @vanderbilt.edu>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.openhie.openempi.stringcomparison.metrics;

public class ScalarIntegerDistanceMetric extends AbstractDistanceMetric
{
	public ScalarIntegerDistanceMetric() {
	}

	public double score(Object value1, Object value2) {
		if (missingValues(value1, value2)) {
			return handleMissingValues(value1, value2);
		}
		int v1 = 0;
		if (value1 instanceof Integer) {
			v1 = (Integer)value1;
		} else {
			try {
				v1 = Integer.valueOf(value1.toString());
			}
			catch (java.lang.NumberFormatException e) {
			}
		}
		int v2 = 0;
		if (value2 instanceof Integer) {
			v2 = (Integer)value2;
		} else {
			try {
				v2 = Integer.valueOf(value2.toString());
			}
			catch (java.lang.NumberFormatException e) {
			}
		}
		double distance = Math.abs(v1 - v2);
		log.trace("Computed the distance between :" + value1.toString() + ": and :" + value2.toString() + ": to be " + distance);
		return distance;
	}
}
