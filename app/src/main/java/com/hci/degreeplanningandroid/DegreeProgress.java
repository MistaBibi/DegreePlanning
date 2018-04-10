package com.hci.degreeplanningandroid;

/**
 * Created by gabri on 4/9/2018.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class DegreeProgress extends Fragment {

    // NOTE: Removed Some unwanted Boiler Plate Codes
    private OnFragmentInteractionListener mListener;

    public DegreeProgress() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_degree_progress, container, false);

        // NOTE : We are calling the onFragmentInteraction() declared in the MainActivity
        // ie we are sending "Fragment 1" as title parameter when fragment1 is activated
        if (mListener != null) {
            mListener.onFragmentInteraction("Degree Progress");
        }

        // Here we will can create click listners etc for all the gui elements on the fragment.
        // For eg: Button btn1= (Button) view.findViewById(R.id.frag1_btn1);
        // btn1.setOnclickListener(...

        ListView lv = view.findViewById(R.id.class_list);

        CSVFile csv = null;
        InputStream is = getResources().openRawResource(R.raw.degree_progress);
        csv = new CSVFile(/*new FileInputStream(/*"C:\\Users\\kozel\\OneDrive\\Documents\\GitHub\\DegreePlanning\\app\\src\\main\\res\\raw\\degree_planning"),*/
                is,"degree_progress.csv");

        ArrayList<String> classList = null;

        //if (csv != null) {
        classList = csv.read();
        //}

//        CSVFile csv = null;
//        try {
//            //InputStream is = getResources().openRawResource(R.raw.degree_progress);
//            csv = new CSVFile(new FileInputStream("C:\\Users\\kozel\\OneDrive\\Documents\\GitHub\\DegreePlanning\\app\\src\\main\\res\\raw\\degree_planning"),
//                    /*is,*/"degree_progress.csv");
//        } catch (FileNotFoundException f) {
//            System.out.print("FILE NOT FOUND");
//            exit(1);
//        }
//
//        ArrayList<String[]> classList = null;
//
//        if (csv != null) {
//            classList = csv.read();
//        }
//
        ArrayAdapter<String> courseAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, classList);

        lv.setAdapter(courseAdapter);

        //displayCourses();

        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            // NOTE: This is the part that usually gives you the error
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // NOTE : We changed the Uri to String.
        void onFragmentInteraction(String title);
    }

    public void displayCourses() {
        CSVFile csv = null;
       try {
            //InputStream is = getResources().openRawResource(R.raw.degree_progress);
            csv = new CSVFile(new FileInputStream("C:\\Users\\kozel\\OneDrive\\Documents\\GitHub\\DegreePlanning\\app\\src\\main\\res\\raw\\degree_planning"),
                    /*is,*/"degree_progress.csv");
        } catch (FileNotFoundException f) {
            System.out.print("FILE NOT FOUND");
            exit(1);
        }

        ArrayList<String> classList = null;

        if (csv != null) {
            classList = csv.read();
        }

//        if(classList != null) {
//            for (String courseInfo: classList) {
//                String course = courseInfo[;
//                if (courseInfo.equals("X")) {
//                    System.out.println(course + "TAKEN");
//                } else if (courseInfo[2].equals("X")) {
//                    System.out.println(course + "IN PROGRESS");
//                } else if (courseInfo[3].equals("X")) {
//                    System.out.println(course + "NOT TAKEN");
//                }
//            }
//        }

    }

}