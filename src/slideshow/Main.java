package slideshow;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Main {

    // Class for utility functions for reuse when necessary.
    Util util;


    public static void main(String[] args) {
        Main m;
        if(args.length == 0)
            m = new Main(null);

        for (int i = 0; i < args.length; i++) {
            m = new Main(args[i]);
        }
    }

    public Main(String arg) {

        // initializations
        int size, numTags;
        String [] lineEls;
        String text;
        String or;

        util = new Util();


        String file;
        if(arg != null)
            file = "src/slideshow/in/" + arg;
        else
            file = "src/slideshow/in/c_memorable_moments";
        Photo photo;
        ArrayList<Photo> photos = new ArrayList<>();

        LinkedList<String> lines = util.readFile(file + ".txt");
        size = Integer.parseInt(lines.get(0).split(" ")[0]);
        String [][] tags = new String[size][];

        ArrayList [] listPhotos = new ArrayList[size / 1000];  // Deal with each batch alone.

        int j = 0;
        String [] tagl;
        for (int i = 1; i < lines.size(); i++) {
            lineEls = lines.get(i).split(" ");
            or = lineEls[0];
            numTags = Integer.parseInt(lineEls[1]);
            tagl = new String[numTags];
            for(int k = 0; k < numTags; k++) {
                tagl[k] = lineEls[k+2];
            }
            tags[i-1] = Util.textSort(tagl);
            photo = new Photo(i-1, or.charAt(0), tags[i-1]);
            photos.add(photo);
            if((i) % 1000 == 0) {
                listPhotos[j] = (ArrayList) photos.clone();
                photos.clear();
                j++;
            }
        }

        System.out.println(tags.length);


        ArrayList<String> out = new ArrayList<>();

        // Dumb solution.
        // If value is a V, add it to soln as line,
        // or if not, add to pair for next H u find
        Random rand = new Random();
        int i = 1;
        Photo pic, tempPic = null;
        for(ArrayList<Photo> pics: listPhotos) {
            for(Photo pic2 : pics) {

            }
        }
        for (ArrayList<Photo> pics : listPhotos) {
            while (!pics.isEmpty()) {
                pic = (Photo) pics.get(rand.nextInt(pics.size()));
                if (pic.getOr() == 'H') {
                    out.add(Integer.toString(pic.getId()));
                } else {
                    if (tempPic == null)
                        tempPic = pic;
                    else {
                        out.add(Integer.toString(pic.getId()) + " " + Integer.toString(tempPic.getId()));
                        tempPic = null;
                    }
                }

                pics.remove(pic);
            }
        }

        int outSize = out.size();
        out.add(0, Integer.toString(outSize));
        file = "src/slideshow/out/" + arg;
        util.writeFile(file + ".out", out.toArray());
    }
}