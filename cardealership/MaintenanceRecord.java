// Assignment:5
// Author: Liav Lugasi, ID: 213007271

package cardealership;

// Assignment: 5
// Author: Israel Israeli, ID: 01234567

import java.time.LocalDate;

/**
 * A record representing a single maintenance line for a car.
 * @param date The date the maintenance was performed.
 * @param type The type of maintenance.
 * @param desc A brief description of the repair/service.
 */
public record MaintenanceRecord(LocalDate date, MaintenanceType type, String desc) {
}