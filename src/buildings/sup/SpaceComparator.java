package buildings.sup;

import buildings.Space;

public class SpaceComparator implements java.util.Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        return -Double.compare(((Space)o1).getArea(), ((Space)o2).getArea());
    }
}
