// Assignment:5
// Author: Liav Lugasi, ID: 213007271

package cardealership;

/**
 * Represents the types of maintenance a car can undergo, along with their base cost.
 */
public enum MaintenanceType {
    ROUTINE_SERVICE(500),
    BODY_REPAIR(1500),
    ENGINE_REPAIR(4000),
    ELECTRICAL(1000);

    private final int cost;

    /**
     * Constructor for MaintenanceType.
     * @param cost The base cost of the maintenance.
     */
    MaintenanceType(int cost){
        this.cost = cost;
    }

    /**
     * @return The base cost of the maintenance type.
     */
    public int getCost(){
        return cost;
    }
}
