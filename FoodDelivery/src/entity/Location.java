package entity;

public class Location {

    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }



    @Override
    public String toString() {
        return "Location{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    // some method to calculate distance between two locations, details are not imp
    public double distanceKmTo(Location o) {
        double R = 6371;
        double dLat = Math.toRadians(o.latitude - latitude);
        double dLon = Math.toRadians(o.longitude - longitude);

        double a = Math.sin(dLat/2)*Math.sin(dLat/2)
                + Math.cos(Math.toRadians(latitude))*Math.cos(Math.toRadians(o.latitude))
                * Math.sin(dLon/2)*Math.sin(dLon/2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return R * c;
    }


}
