package selmibenromdhane.sparta_v1.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.app.AppConfig;
import selmibenromdhane.sparta_v1.manager.Article;
import selmibenromdhane.sparta_v1.parser.ScheduleOwnDownloader;


public class ScheduleOwnFragment extends Fragment {

    //final static String urlAddress="http://172.16.155.135/Upload-Insert-Update-Delete-Image-PHP-MySQL/getSchedule.php";

    List<Article> imgList = null;
    String img1 = "http://www.webdo.tn/wp-content/uploads/2013/03/Slim-Riahi1.jpg";
    String img2 = "http://www.webdo.tn/wp-content/uploads/2013/03/Slim-Riahi1.jpg";
    String img3 = "http://www.webdo.tn/wp-content/uploads/2013/03/Slim-Riahi1.jpg";
    String img4 = "http://www.webdo.tn/wp-content/uploads/2013/03/Slim-Riahi1.jpg";
    String img5 = "http://www.webdo.tn/wp-content/uploads/2013/03/Slim-Riahi1.jpg";
    String img6 = "http://www.webdo.tn/wp-content/uploads/2013/03/Slim-Riahi1.jpg";
    String img7 = "http://www.webdo.tn/wp-content/uploads/2013/03/Slim-Riahi1.jpg";
    String img8 = "http://www.webdo.tn/wp-content/uploads/2013/03/Slim-Riahi1.jpg";
    String img9 = "http://www.webdo.tn/wp-content/uploads/2013/03/Slim-Riahi1.jpg";
    String img10 = "http://www.webdo.tn/wp-content/uploads/2013/03/Slim-Riahi1.jpg";
    String img11 = "http://www.webdo.tn/wp-content/uploads/2013/03/Slim-Riahi1.jpg";
    String img12 = "http://www.webdo.tn/wp-content/uploads/2013/03/Slim-Riahi1.jpg";
    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_scheduleown, container, false);

        ListView classListView = (ListView) rootView.findViewById(R.id.list2);
        imgList = new ArrayList<Article>();


        Article A1 = new Article(img1, "Titr 1", "Desc 1");
        Article A2 = new Article(img2, "Titr 2", "Desc 2");
        Article A3 = new Article(img3, "Titr 3", "Desc 3");
        Article A4 = new Article(img4, "Titr 4", "Desc 4");
        Article A5 = new Article(img5, "Titr 5", "Desc 5");
        Article A6 = new Article(img6, "Titr 6", "Desc 6");
        Article A7 = new Article(img7, "Titr 7", "Desc 7");
        Article A8 = new Article(img8, "Titr 8", "Desc 8");
        Article A9 = new Article(img9, "Titr 9", "Desc 9");
        Article A10 = new Article(img10, "Titr 10", "Desc 10");
        Article A11 = new Article(img11, "Titr 11", "Desc 11");
        Article A12 = new Article(img12, "Titr 12", "Desc 12");

        imgList.add(A1);
        imgList.add(A2);
        imgList.add(A3);
        imgList.add(A4);
        imgList.add(A5);
        imgList.add(A6);
        imgList.add(A7);
        imgList.add(A8);
        imgList.add(A9);
        imgList.add(A10);
        imgList.add(A11);
        imgList.add(A12);

       // classListView.setAdapter(new ClassesCustomAdapter(getContext(), R.layout.one_article, imgList));

       // new ScheduleDownloader(getActivity(),urlAddress,classListView).execute();

        new ScheduleOwnDownloader(getActivity(), AppConfig.URL_OWNSCHEDULE,classListView).execute();


        return rootView;



    }


}
