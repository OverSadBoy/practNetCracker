package building.sup;

import building.Floor;

public class FloorComparator implements java.util.Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        return -Double.compare(((Floor)o1).getAreas(), ((Floor)o2).getAreas());
    }
}
