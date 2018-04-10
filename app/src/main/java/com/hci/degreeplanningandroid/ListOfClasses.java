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

import java.util.ArrayList;

public class ListOfClasses extends Fragment {

    // NOTE: Removed Some unwanted Boiler Plate Codes
    private OnFragmentInteractionListener mListener;

    public ListOfClasses() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_list_of_classes, container, false);

        // NOTE : We are calling the onFragmentInteraction() declared in the MainActivity
        // ie we are sending "Fragment 1" as title parameter when fragment1 is activated
        if (mListener != null) {
            mListener.onFragmentInteraction("List of Classes");
        }

        // Here we will can create click listners etc for all the gui elements on the fragment.
        // For eg: Button btn1= (Button) view.findViewById(R.id.frag1_btn1);
        // btn1.setOnclickListener(...

        ArrayList<String> courses = new ArrayList<String>();
        courses.add("CMSC320");
        courses.add("CMSC389N");
        courses.add("CMSC411");
        courses.add("CMSC412");
        courses.add("CMSC414");
        courses.add("CMSC417");
        courses.add("CMSC420");
        courses.add("CMSC421");
        courses.add("CMSC423");
        courses.add("CMSC424");
        courses.add("CMSC430");
        courses.add("CMSC433");
        courses.add("CMSC434");
        courses.add("CMSC435");
        courses.add("CMSC436");
        courses.add("CMSC451");
        courses.add("CMSC452");
        courses.add("CMSC456");

        ListView lv = view.findViewById(R.id.courses_available);

        ArrayAdapter<String> courseAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, courses);

        lv.setAdapter(courseAdapter);

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