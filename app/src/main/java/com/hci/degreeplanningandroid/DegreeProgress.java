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

        InputStream is = getResources().openRawResource(R.raw.degree_progress);

        CSVFile csv = new CSVFile(is,"degree_progress.csv");

        // Read in CSV
        ArrayList<String[]> classList = csv.read();

        ArrayList<String> completedCourses = new ArrayList<>();
        ArrayList<String> incompleteCourses = new ArrayList<>();
        ArrayList<String> inProgressCourses = new ArrayList<>();

        // Add completed courses
        for(String[] classInfo: classList) {
            String course = classInfo[0];
            System.out.print(course);
            String status = classInfo[1];
            System.out.print(status);
            switch (status) {
                case "X": completedCourses.add(course);
                case "O": incompleteCourses.add(course);
                case "P": inProgressCourses.add(course);
            }
        }

        ListView completedList = view.findViewById(R.id.completed_list);
        ArrayAdapter<String> courseAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, completedCourses);
        completedList.setAdapter(courseAdapter);

        ListView incompleteList = view.findViewById(R.id.incomplete_list);
        courseAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, incompleteCourses);
        incompleteList.setAdapter(courseAdapter);

        ListView inProgressList = view.findViewById(R.id.progress_list);
        courseAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, inProgressCourses);
        inProgressList.setAdapter(courseAdapter);

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

}