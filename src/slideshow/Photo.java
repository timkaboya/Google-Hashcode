package slideshow;

import java.util.ArrayList;

public class Photo {
    private int id;
    private char or;
    private String [] tags;

    private int [] commonIdx;
    private int [] thisNotThatIdx;
    private int [] thatNotThisIdx;


    public Photo(int id, char or, String[] tags) {
        this.id = id;
        this.or = or;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getOr() {
        return or;
    }

    public void setOr(char or) {
        this.or = or;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    // interest number when paired with all others in 1000 photo grid.
    public void computeCommonIdx(ArrayList<Photo> photos) {
        for(int i = 0; i < photos.size(); i++) {
            commonIdx[i] = Util.interSectIdx(tags, photos.get(i).tags);
        }
    }

    public void computeThisNotThatIdx(ArrayList<Photo> photos) {
        for (int i = 0; i < photos.size(); i++) {
            thisNotThatIdx[i] = Util.str1Idx(tags, photos.get(i).tags);
        }
    }

    public void computeThatNotThisIdx(ArrayList<Photo> photos) {
        for (int i = 0; i < photos.size(); i++) {
            thatNotThisIdx[i] = Util.str2Idx(tags, photos.get(i).tags);
        }
    }
    // methods to get intersec, not el.
}
