package user;

import sportfacility.SportFacility;

public interface Observer {
    public void update(SportFacility facility, String dateHour);
}
